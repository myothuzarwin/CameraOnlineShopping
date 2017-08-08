/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate;

import EntityCollection.ShoppingCartEntity;
import freemarker.template.utility.Execute;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import myApp.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author myothuzar
 */
public class CartHibDAO {

    private Connection conn = null;
    private ResultSet rs;
    private Statement stmt = null;

    public CartHibDAO() {

    }

    public int AddShoppingCart(ShoppingCartEntity cart) {

        int insertedID = 0;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            insertedID = (Integer) session.save(cart);
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
        return insertedID;

    }

    public ArrayList<ShoppingCartEntity> ShowShoppingCart(int custID) {

        ArrayList<ShoppingCartEntity> cartList = new ArrayList<ShoppingCartEntity>();

        try {

            String sql = "SELECT S.*,P.photopath FROM onlineshopping.shoppingcart  AS S INNER JOIN "
                    + "Product AS P ON S.pid=P.pid WHERE customerid=" + custID;
            ResultSet rs = null;

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ShoppingCartEntity shoppingcart = new ShoppingCartEntity();
                shoppingcart.setCustomerid(rs.getInt("customerid"));
                shoppingcart.setProductid(rs.getInt("pid"));
                shoppingcart.setProductname(rs.getString("pname"));
                shoppingcart.setQty(rs.getInt("qty"));
                shoppingcart.setPrice(rs.getInt("price"));
                shoppingcart.setCreatedtime(rs.getDate("createdtime"));
                shoppingcart.setUpdatedtime(rs.getDate("updatedtime"));
                shoppingcart.setImageURL(rs.getString("photopath"));

                cartList.add(shoppingcart);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return cartList;
    }

    /*
     public ShoppingCartEntity GetProductInShoppingCart(int customerid, int productid) {
 
     ShoppingCartEntity result = new ShoppingCartEntity();
     Session session = HibernateUtil.getSession();
        

     try {

           
     String hql = "FROM ShoppingCartEntity S WHERE S.customerid=:customerid AND S.productid=:productid";
     Query query = session.createQuery(hql);
     query.setParameter("customerid", customerid);
     query.setParameter("productid", productid);
     result = (ShoppingCartEntity) query.uniqueResult();
             
            
     } catch (Exception e) {
     System.out.print("Error while fetching " + e);
     return null;
     } finally {
     session.close();
     }
     return result;
     }
     */
    public int RemoveShoppingCart(int customerid, int productid) {
        int removeID = 0;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        ShoppingCartEntity delcartprod = new ShoppingCartEntity();
        try {

            tx = session.beginTransaction();

            String hql = "FROM ShoppingCartEntity S WHERE customerid = :customerid and productid = :productid";

            Query query = session.createQuery(hql);
            query.setParameter("customerid", customerid);
            query.setParameter("productid", productid);
            delcartprod = (ShoppingCartEntity) query.uniqueResult();
            session.delete(delcartprod);
            tx.commit();

        } catch (Exception e) {
            System.out.print("Error while fetching " + e);

        } finally {
            session.close();
        }
        return removeID;
    }

    public int AddQtyUpdateCart(int customerid, int productid, int qty) {
        int AddQty = 0;
        qty++;

        Session session = HibernateUtil.getSession();

        Transaction tx = null;
        try {

            tx = session.beginTransaction();
            String hql = "update ShoppingCartEntity set qty = :qty" + " where productid = :productid";
            Query query = session.createQuery(hql);
            query.setParameter("qty", qty);
            query.setParameter("productid", productid);
            AddQty = query.executeUpdate();
            tx.commit();

        } catch (Exception e) {
            System.out.print("Error while fetching " + e);
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return AddQty;
    }

    public int ReduceQtyUpdateCart(int customerid, int productid, int qty) {
        int ReduceQty = 0;
        qty--;

        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            String hql = "update ShoppingCartEntity set qty = :qty" + " where productid = :productid";
            Query query = session.createQuery(hql);
            query.setParameter("qty", qty);
            query.setParameter("productid", productid);
            ReduceQty = query.executeUpdate();
            tx.commit();

        } catch (Exception e) {
            System.out.print("Error while fetching " + e);
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return ReduceQty;
    }

    public int GetDiscount(int customerID) throws SQLException {
        int DistPer = 0;
        String query = "SELECT d.DistPer FROM Discount AS D INNER JOIN DiscountApplyCustomer AS U ON U.DistID=D.DistID WHERE U.CustomerID=" + customerID;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                DistPer = rs.getInt(DistPer);
            }
        } catch (Exception e) {
            System.err.println("Failed to insert values. " + e.getMessage());
        }
        return DistPer;
    }

}
