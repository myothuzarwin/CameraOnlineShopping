/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate;

import EntityCollection.OrderHeaderInfo;
import EntityCollection.OrderdetailEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author myothuzar
 */
public class OrderDAO {

    private Connection conn = null;
    private ResultSet rs;
    private Statement stmt = null;

    public OrderDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
          //  conn = DriverManager.getConnection("jdbc:mysql://localhost/onlineshopping", "root", "");
         //   conn = DriverManager.getConnection("jdbc:mysql://node156672-onlineshopping.j.layershift.co.uk/user", "root", "QTIdby95790");
            conn = DriverManager.getConnection("jdbc:mysql://node156672-onlineshopping.j.layershift.co.uk/user", "user", "Voxc4WE3RdhzUrgf");
        } catch (Exception e) {
            System.err.println("Failed to connect to the Database from onlineshopping" + e.getMessage());
        }

    }

    public ArrayList<OrderHeaderInfo> History(int customerid) {

        ArrayList<OrderHeaderInfo> history = new ArrayList<OrderHeaderInfo>();

        try {

            String sql = "SELECT o.orderdate as Orderdate,o.oid as Oid,SUM(od.qty) as No_Of_Product,o.SubTotal as SubTotal,o.DiscPer as Discount_Percentage,o.DiscAmt as Discount_Amount,o.TotAmount as Total_Amount FROM onlineshopping.`order` as o INNER JOIN onlineshopping.orderdetail as od ON o.oid = od.oid and o.customerid = " + customerid + " group by o.orderdate,o.oid";
            ResultSet rs = null;

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                OrderHeaderInfo orderinfo = new OrderHeaderInfo();

                orderinfo.setOrderdate(rs.getDate("Orderdate"));
                orderinfo.setOrderid(rs.getInt("Oid"));
                orderinfo.setNo_of_product(rs.getInt("No_Of_Product"));
                orderinfo.setSubTotal(rs.getInt("SubTotal"));
                orderinfo.setDiscount_Percentage(rs.getDouble("Discount_Percentage"));
                orderinfo.setDiscount_Amount(rs.getDouble("Discount_Amount"));
                orderinfo.setTotal_Amount(rs.getDouble("Total_Amount"));

                history.add(orderinfo);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return history;

    }

    public ArrayList<OrderdetailEntity> ShowDetail(int orderid) {

        ArrayList<OrderdetailEntity> showdetail = new ArrayList<OrderdetailEntity>();

        try {

            String sql = "select P.photopath as photopath,od.pname as Productname,od.qty as No_Of_Qty,od.price as Price from onlineshopping.orderdetail as od INNER JOIN Product AS P ON od.pid=P.pid where oid = " + orderid;
            ResultSet rs = null;

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                OrderdetailEntity orderdetail = new OrderdetailEntity();

                orderdetail.setImageURL(rs.getString("photopath"));
                orderdetail.setProductname(rs.getString("Productname"));
                orderdetail.setQuantity(rs.getInt("No_Of_Qty"));
                orderdetail.setPrice(rs.getInt("Price"));

                showdetail.add(orderdetail);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return showdetail;

    }

}
