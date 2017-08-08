/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate;

import EntityCollection.BestproductEntity;
import EntityCollection.CommentEntity;
import EntityCollection.ProductEntity;
import EntityCollection.ReviewEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import myApp.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author myothuzar
 */
public class ProductDAO {

    private Connection conn = null;
    private ResultSet rs;
    private Statement stmt = null;

    public ProductDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
         //   conn = DriverManager.getConnection("jdbc:mysql://localhost/onlineshopping", "root", "");
         //   conn = DriverManager.getConnection("jdbc:mysql://node156672-onlineshopping.j.layershift.co.uk/user", "root", "QTIdby95790");
            conn = DriverManager.getConnection("jdbc:mysql://node156672-onlineshopping.j.layershift.co.uk/user", "user", "Voxc4WE3RdhzUrgf");
        } catch (Exception e) {
            System.err.println("Failed to connect to the Database from onlineshopping" + e.getMessage());
        }
    }

    public ArrayList<ProductEntity> Checkcategory(int cid) {

        ArrayList<ProductEntity> results = new ArrayList<ProductEntity>();

        try {

            String sql = " SELECT product.pname,product.description,product.price,product.photopath,product.pid,product.qty "
                    + "    FROM onlineshopping.category,onlineshopping.product  WHERE category.cid=product.cid and category.cid= " + cid + " ";
            ResultSet rs = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ProductEntity result1 = new ProductEntity();
                result1.setProductname(rs.getString("pname"));
                result1.setDescription(rs.getString("description"));
                result1.setPrice(rs.getInt("price"));
                result1.setPhotopath(rs.getString("photopath"));
                result1.setProductid(rs.getInt("pid"));
                result1.setQty(rs.getInt("qty"));

                results.add(result1);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return results;
    }

    public ArrayList<ProductEntity> ShowAllProduct() {

        ArrayList<ProductEntity> results = new ArrayList<ProductEntity>();

        try {

            String sql = "SELECT * FROM onlineshopping.product";
            ResultSet rs = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ProductEntity result1 = new ProductEntity();
                result1.setProductname(rs.getString("pname"));
                result1.setDescription(rs.getString("description"));
                result1.setPrice(rs.getInt("price"));
                result1.setPhotopath(rs.getString("photopath"));
                result1.setProductid(rs.getInt("pid"));
                result1.setQty(rs.getInt("qty"));

                results.add(result1);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return results;
    }

    public ProductEntity ShowDetail(int pid) {

        ProductEntity result = new ProductEntity();

        try {

            String sql = "SELECT * FROM onlineshopping.product WHERE PID=" + pid;
            ResultSet rs = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                result.setProductid(rs.getInt("pid"));
                result.setProductname(rs.getString("pname"));
                result.setDescription(rs.getString("description"));
                result.setPrice(rs.getInt("price"));
                result.setPhotopath(rs.getString("photopath"));
                result.setQty(rs.getInt("qty"));

            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return result;
    }
    
    public ArrayList<BestproductEntity> ShowBestProduct() {

        ArrayList<BestproductEntity> results = new ArrayList<BestproductEntity>();

        try {

            String sql = "select y.pid,y.pname,p.photopath,p.description from (select pname,pid,oid,SUM(price*qty)as totprice " +
                         " from onlineshopping.orderdetail group by pid order by totprice desc LIMIT 10 OFFSET 0)as y,product as p where y.pid=p.pid ";
            ResultSet rs = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                BestproductEntity result1 = new BestproductEntity();
                result1.setProductname(rs.getString("pname"));
                result1.setDescription(rs.getString("description"));
                result1.setPhotopath(rs.getString("photopath"));
                result1.setProductid(rs.getInt("pid"));
                
                
                results.add(result1);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return results;
    }
    
     public int InsertComment(int productid,int customerid,int rating,String comment,int type2) throws SQLException {
        int insertedID = 0;

        String sql = "INSERT INTO onlineshopping.rating(pid,customerid,rating,comments,type) VALUES("+ productid +"," + customerid + "," + rating + ",'" + comment + "'," + type2 + ")";

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

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
    
      public int InsertRate(int productid,int customerid,int rating,int type1) throws SQLException {
        int insertedID = 0;
       
        

        String sql = "INSERT INTO onlineshopping.rating(pid,customerid,rating,type) VALUES("+ productid +"," + customerid + "," + rating + "," + type1 + ")";
    //    String selSql="select customerid from rating where customerid= " + customerid + "and type= " + type1 ;
       
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
      
      public ArrayList<CommentEntity> ShowComments(int pid) {
          
          ArrayList<CommentEntity> results = new ArrayList<CommentEntity>();

        

        try {

            String sql = "select c.customerid,c.customername,r.comments,r.pid,r.rating,r.createtime from rating as r,customer as c " +
"where pid= " + pid + " and c.customerid=r.customerid and r.comments IS NOT NULL order by customerid";
            ResultSet rs = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                CommentEntity result1 = new CommentEntity();
                result1.setProductid(rs.getInt("pid"));
                result1.setComment(rs.getString("comments"));
                result1.setCustomername(rs.getString("customername"));
                result1.setRating(rs.getInt("rating"));
                result1.setCreatetime(rs.getDate("createtime"));
                results.add(result1);
                

            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return results;
    }
       public double ShowRate(int pid) {
          double ratecounts=0.0; 
        try {
        
            String sql = "select AVG(rating) as ratecount from rating where pid= " + pid + " group by pid ";
            ResultSet rs = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
               
		ratecounts=(rs.getDouble("ratecount"));
                
               
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return ratecounts;
    }
    
        public int CheckType1(int customerid,int productid,int type1) {
          int custID=0; 
        try {
        String sql="select r.customerid from (select pid,customerid from rating where customerid= " + customerid + " and type= " + type1 + ") as r where r.pid= " + productid ;
            
            ResultSet rs = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
               
		custID=(rs.getInt("customerid"));
                
               
            }
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return custID;
    }
    
         public int CheckType2(int customerid,int productid,int type2) {
          int custID=0; 
        try {
        String sql="select r.customerid from (select pid,customerid from rating where customerid= " + customerid + " and type= " + type2 + ") as r where r.pid= " + productid ;
            
            ResultSet rs = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
               
		custID=(rs.getInt("customerid"));
                
               
            }
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return custID;
    }
         
    public int UpdateType1(int rating, int customerid, int productid, int type1) throws SQLException {
        int updateT1 = 0;
      
        String query = "UPDATE rating SET rating = " + rating + ",createtime=CURRENT_TIMESTAMP WHERE customerid= " + customerid + " and type= " + type1 + " and pid= " + productid ;

        try {
            stmt = conn.createStatement();
            updateT1 = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        } catch (Exception e) {
            System.err.println("Failed to insert values. " + e.getMessage());
        }
        return updateT1;
    }
    
    public int UpdateType2(int rating, int customerid, int productid, String comment, int type2) throws SQLException {
        int updateT2 = 0;
      
        String query = "UPDATE rating SET rating = " + rating + ",comments = '" + comment + "',createtime=CURRENT_TIMESTAMP WHERE customerid= " + customerid + " and type= " + type2 + " and pid= " + productid ;

        try {
            stmt = conn.createStatement();
            updateT2 = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        } catch (Exception e) {
            System.err.println("Failed to insert values. " + e.getMessage());
        }
        return updateT2;
    }



}
