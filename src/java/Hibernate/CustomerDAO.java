/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate;

import EntityCollection.CustomerEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author myothuzar
 */
public class CustomerDAO {

    private Connection conn = null;
    private ResultSet rs;
    private Statement stmt = null;

    public CustomerDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
         //   conn = DriverManager.getConnection("jdbc:mysql://localhost/onlineshopping", "root", "");
         //   conn = DriverManager.getConnection("jdbc:mysql://node156672-onlineshopping.j.layershift.co.uk/user", "root", "QTIdby95790");
            conn = DriverManager.getConnection("jdbc:mysql://node156672-onlineshopping.j.layershift.co.uk/user", "user", "Voxc4WE3RdhzUrgf");
        } catch (Exception e) {
            System.err.println("Failed to connect to the Database from onlineshopping" + e.getMessage());
        }
    }

    public ArrayList<CustomerEntity> GetAllCustomer() {

        ArrayList<CustomerEntity> results = new ArrayList<CustomerEntity>();
        String sql = "SELECT * FROM customer";

        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                CustomerEntity result1 = new CustomerEntity();
                result1.setCustomername(rs.getString("customername"));

                //results.add(result1);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return results;
    }

    // Get Customer Information By customerID
    public CustomerEntity GetCustomerByID(int customerid) {

        CustomerEntity result = new CustomerEntity();
        String sql = "SELECT * FROM onlineshopping.customer WHERE customerid = " + customerid;

        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                result.setCustomerid(rs.getInt("customerid"));
                result.setCustomername(rs.getString("customername"));
                result.setBirthday(rs.getDate("birthday"));
                result.setGender(rs.getString("gender"));
                result.setAddress(rs.getString("address"));
                result.setPostalcode(rs.getString("postalcode"));
                result.setPhoneno(rs.getString("phoneno"));
                result.setEmailaddress(rs.getString("emailaddress"));
                result.setPassword(rs.getString("password"));
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return result;
    }

    // Check customer login.
    public CustomerEntity CheckCustomerLogin(String customername, String password) {
        CustomerEntity result = new CustomerEntity();

        String sql = "SELECT * FROM onlineshopping.customer WHERE customername = '" + customername + "' AND password = '" + password + "'";

        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                result.setCustomerid(rs.getInt("customerid"));
                result.setCustomername(rs.getString("customername"));
                result.setBirthday(rs.getDate("birthday"));
                result.setGender(rs.getString("gender"));
                result.setAddress(rs.getString("address"));
                result.setPostalcode(rs.getString("postalcode"));
                result.setPhoneno(rs.getString("phoneno"));
                result.setEmailaddress(rs.getString("emailaddress"));
                result.setPassword(rs.getString("password"));
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return result;
    }

    public int InsertCustomer(String customername, Date birthday, String gender, String address, String postal_code, String phone_no, String e_mail, String password) {
        int insertedID = 0;

        String sql = "INSERT INTO onlineshopping.customer(customername , birthday , gender , address , postalcode,phoneno,emailaddress,password) VALUES('"
                + customername + "','2016-02-03','" + gender + "','" + address + "','" + postal_code + "','" + phone_no + "','" + e_mail + "','" + password + "' )";

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

}
