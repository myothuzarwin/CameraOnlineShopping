/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate;

import EntityCollection.DiscountApplyCustomerEntity;
import EntityCollection.DiscountEntity;
import EntityCollection.ShoppingCartEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author myothuzar
 */
public class CartDAO {

    private Connection conn = null;
    private ResultSet rs;
    private Statement stmt = null;

    public CartDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        //    conn = DriverManager.getConnection("jdbc:mysql://localhost/onlineshopping", "root", "");
        //   conn = DriverManager.getConnection("jdbc:mysql://node156672-onlineshopping.j.layershift.co.uk/user", "root", "QTIdby95790");
            conn = DriverManager.getConnection("jdbc:mysql://node156672-onlineshopping.j.layershift.co.uk/user", "user", "Voxc4WE3RdhzUrgf");
        } catch (Exception e) {
            System.err.println("Failed to connect to the Database from onlineshopping" + e.getMessage());
        }
    }

    public int AddShoppingCart(int customerID, int productid, String productname, int qty, double price) throws SQLException {
        int insertedID = 0;
        String query = "INSERT INTO onlineshopping.shoppingcart (customerid , pid ,pname, qty,price ) VALUES(" + customerID + "," + productid + ", '" + productname + "' ," + qty + "," + price + " )";

        try {

            stmt = conn.createStatement();

            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                insertedID = rs.getInt(1);
                System.out.println("Entry is successful");
            }

        } catch (Exception e) {
            System.err.println("Failed to insert values. " + e.getMessage());
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

    public ShoppingCartEntity GetProductInShoppingCart(int custID, int ProdcutID) {

        ShoppingCartEntity shoppingcart = new ShoppingCartEntity();

        try {

            String sql = "SELECT * FROM onlineshopping.shoppingcart WHERE customerid=" + custID + " and pid=" + ProdcutID;
            ResultSet rs = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                shoppingcart.setCustomerid(rs.getInt("customerid"));
                shoppingcart.setProductid(rs.getInt("pid"));
                shoppingcart.setProductname(rs.getString("pname"));
                shoppingcart.setQty(rs.getInt("qty"));
                shoppingcart.setPrice(rs.getInt("price"));
                shoppingcart.setCreatedtime(rs.getDate("createdtime"));
                shoppingcart.setUpdatedtime(rs.getDate("updatedtime"));
                shoppingcart.setUpdatedtime(rs.getDate("updatedtime"));

            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return shoppingcart;
    }

    public int RemoveShoppingCart(int customerID, int productid) throws SQLException {
        int removeID = 0;
        String query = "DELETE FROM onlineshopping.shoppingcart WHERE customerid=" + customerID + " AND pid=" + productid;

        try {
            stmt = conn.createStatement();
            removeID = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        } catch (Exception e) {
            System.err.println("Failed to insert values. " + e.getMessage());
        }
        return removeID;
    }

    public int AddQtyUpdateCart(int customerID, int productid, int qty) throws SQLException {
        int AddQty = 0;
        qty++;
        String query = "UPDATE onlineshopping.shoppingcart SET qty = " + qty + " WHERE pid=" + productid;

        try {
            stmt = conn.createStatement();
            AddQty = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        } catch (Exception e) {
            System.err.println("Failed to insert values. " + e.getMessage());
        }
        return AddQty;
    }

    public int ReduceQtyUpdateCart(int customerID, int productid, int qty) throws SQLException {
        int ReduceQty = 0;
        qty--;
        String query = "UPDATE onlineshopping.shoppingcart SET qty = " + qty + " WHERE pid=" + productid;

        try {
            stmt = conn.createStatement();
            ReduceQty = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        } catch (Exception e) {
            System.err.println("Failed to insert values. " + e.getMessage());
        }
        return ReduceQty;
    }

    public DiscountEntity GetAppliedDiscount(int customerID) throws SQLException {
        DiscountEntity entity = new DiscountEntity();
        String query = "SELECT D.DistID,D.Dcouponno,D.DistPer FROM discount AS D INNER JOIN discountapplycustomer AS U ON U.DistID=D.DistID WHERE U.CustomerID=" + customerID;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                entity.setDistID(rs.getInt("DistID"));
                entity.setDcouponno(rs.getInt("Dcouponno"));
                entity.setDistper(rs.getInt("DistPer"));
            }
        } catch (Exception e) {
            System.err.println("Failed to insert values. " + e.getMessage());
        }
        return entity;
    }

    public int InsertDiscountCustomer(DiscountApplyCustomerEntity entity) throws SQLException {
        int insertedID = 0;
        String query = "INSERT INTO discountapplycustomer( DistID,customerid) VALUES ( " + entity.getDistID() + ", " + entity.getCustomerid() + " )";

        try {
            stmt = conn.createStatement();
            insertedID = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                insertedID = rs.getInt(1);
                System.out.println("Entry is successful");
            }
        } catch (Exception e) {
            System.err.println("Failed to insert values. " + e.getMessage());
        }
        return insertedID;
    }

    public DiscountEntity GetDiscount(int DisCode) throws SQLException {
        int DistPer = 0;
        DiscountEntity disc = new DiscountEntity();
        String query = "SELECT * FROM discount  WHERE Dcouponno=" + DisCode;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                disc.setDcouponno(rs.getInt("Dcouponno"));
                disc.setDistID(rs.getInt("DistID"));
                disc.setDistper(rs.getInt("Distper"));
            }
        } catch (Exception e) {
            System.err.println("Failed to insert values. " + e.getMessage());
        }
        return disc;
    }

}
