/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate;

import EntityCollection.OrderEntity;
import EntityCollection.OrderdetailEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author myothuzar
 */
public class CheckoutDAO {

    private Connection conn = null;
    private ResultSet rs;
    private Statement stmt = null;

    public CheckoutDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
          //  conn = DriverManager.getConnection("jdbc:mysql://localhost/onlineshopping", "root", "");
          //  conn = DriverManager.getConnection("jdbc:mysql://node156672-onlineshopping.j.layershift.co.uk/user", "root", "QTIdby95790");
            conn = DriverManager.getConnection("jdbc:mysql://node156672-onlineshopping.j.layershift.co.uk/user", "user", "Voxc4WE3RdhzUrgf");
        } catch (Exception e) {
            System.err.println("Failed to connect to the Database from onlineshopping" + e.getMessage());
        }

    }

    public int Placeorder(OrderEntity ord) {
        int insertedID = 0;

        String sql = "INSERT INTO onlineshopping.order(customerid , receivername , receiveraddress, receiverpostalcode, receiverphno, receiveremail, delivery, payment,SubTotal,DiscPer,DiscAmt,TotAmount) VALUES('"
                + ord.getCustomerid() + "','" + ord.getReceivername() + "','" + ord.getReceiveraddress() + "','" + ord.getReceiverpostalcode() + "','" + ord.getReceiverphno() + "','" + ord.getReceiveremail() + "','" + ord.getDelivery() + "','" + ord.getPayment() + "',"
                + ord.getSubTotal() + "," + ord.getDiscPer() + ", " + ord.getDiscAmt() + " ," + ord.getTotAmount() + ")";

        try {
            stmt = conn.createStatement();
            insertedID = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                insertedID = rs.getInt(1);
                System.out.println("Entry is successful");
            }
        } catch (Exception ex) {
            System.err.println("Failed to INSERT User " + ex.getMessage());
        }

        return insertedID;
    }

    public int Placeorderdetail(int orderid, int customerid) {
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

}
