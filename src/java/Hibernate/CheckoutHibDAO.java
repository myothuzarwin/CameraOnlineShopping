/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate;

import EntityCollection.OrderEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import myApp.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author myothuzar
 */
public class CheckoutHibDAO {

    private Connection conn = null;
    private ResultSet rs;
    private Statement stmt = null;

    public CheckoutHibDAO() {
        /*    try {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         conn = DriverManager.getConnection("jdbc:mysql://localhost/onlineshopping", "root", "");
         } catch (Exception e) {
         System.err.println("Failed to connect to the Database from onlineshopping" + e.getMessage());
         }
         */

    }

    public int Placeorder(OrderEntity order) {
        int insertedID = 0;

        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            insertedID = (Integer) session.save(order);
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

    /*    public int Placeorderdetail(int orderid, int customerid) {
     int insertedID = 0;

     String sql = "INSERT INTO onlineshopping.orderdetail(pid , pname , qty , price,oid) SELECT  pid , pname , qty , price , " + orderid + " FROM onlineshopping.shoppingcart WHERE customerid=" + customerid + "   ";

     String delSql = "DELETE FROM onlineshopping.shoppingcart WHERE customerid=" + customerid + "   ";

     String delSqlcust = "DELETE FROM onlineshopping.discountapplycustomer WHERE customerid=" + customerid + "   ";

     try {
     stmt = conn.createStatement();
     insertedID = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

     if (insertedID > 0) {
     stmt.executeUpdate(delSql, Statement.SUCCESS_NO_INFO); //clear the shopping cart
     stmt.executeUpdate(delSqlcust, Statement.SUCCESS_NO_INFO);//clear the discountapplycustomer
     }

     } catch (Exception ex) {
     System.err.println("Failed to INSERT User " + ex.getMessage());
     }

     return insertedID;
     }
     */
}
