/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate;

import EntityCollection.ProductEntity;
import EntityCollection.ReviewEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import myApp.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;



/**
 *
 * @author myothuzar
 */
public class ProductHibDAO {

//  List<ProductEntity> results; 

    public ProductHibDAO() {
       
    }
    
/*    public ArrayList<ProductEntity> Checkcategory(int cid) {

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
                result1.setPrice(rs.getDouble("price"));
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
    */


    public List<ProductEntity> ShowAllProduct() {

        
List<ProductEntity> results; 
Session session = HibernateUtil.getSession();
        try {
          
            results = session.createQuery("from ProductEntity").list();
            return results;
            
            
            }
         
            catch (Exception e) {
            System.out.print("Error while fetching " + e);
            return null;
        } finally {
            session.close();
        }
       
    }
    
 

    public ProductEntity ShowDetail(int productid) {
        
         

        ProductEntity result = new ProductEntity();
        Session session = HibernateUtil.getSession();
        try {
            String hql = "FROM ProductEntity P WHERE P.productid=:productid";
            Query query = session.createQuery(hql);
            query.setParameter("productid", productid);
            result = (ProductEntity) query.uniqueResult();
            
        }catch (Exception e) {
            System.out.print("Error while fetching " + e);
            return null;
        } finally {
            session.close();
        }

        return result;
    }
    /**
    public int InsertComment(ReviewEntity review) {
        int rateID = 0;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            rateID = (Integer) session.save(review);
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
        return rateID;
    }
    **/


}
