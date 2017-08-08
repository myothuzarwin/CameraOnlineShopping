/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate;

import EntityCollection.CustomerEntity;
import myApp.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author myothuzar
 */
public class CustomerHibDAO {

    List<CustomerEntity> results;

    public List<CustomerEntity> GetAllCustomer() {

        Session session = HibernateUtil.getSession();
        try {
            results = session.createQuery("from CustomerEntity").list();
            return results;
        } catch (Exception e) {
            System.out.print("Error while fetching " + e);
            return null;
        } finally {
            session.close();
        }

    }

    // Get Customer Information By customerID
    public CustomerEntity GetCustomerByID(int customerid) {

        CustomerEntity result = new CustomerEntity();
        Session session = HibernateUtil.getSession();
        try {
            String hql = "FROM CustomerEntity C WHERE C.customerid=:customerid";
            Query query = session.createQuery(hql);
            query.setParameter("customerid", customerid);
            result = (CustomerEntity) query.uniqueResult();

        } catch (Exception e) {
            System.out.print("Error while fetching " + e);
            return null;
        } finally {
            session.close();
        }
        return result;
    }

    // Check customer login.
    public CustomerEntity CheckCustomerLogin(String customername, String password) {
        CustomerEntity result = new CustomerEntity();
        Session session = HibernateUtil.getSession();
        try {
            String hql = "FROM CustomerEntity C WHERE C.customername=:customername and C.password=:password";
            Query query = session.createQuery(hql);
            query.setParameter("customername", customername);
            query.setParameter("password", password);
            result = (CustomerEntity) query.uniqueResult();
        } catch (Exception e) {
            System.out.print("Error while fetching " + e);
            return null;
        } finally {
            session.close();
        }
        return result;

    }

    public int InsertCustomer(CustomerEntity cust) {
        int custID = 0;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            custID = (Integer) session.save(cust);
            tx.commit();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + " " + e.getStackTrace());
            if (tx != null) {
                tx.rollback();
            }

            throw e;
        } finally {
            session.close();
        }
        return custID;
    }

}
