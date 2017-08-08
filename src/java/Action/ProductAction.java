/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

/**
 *
 * @author myothuzar
 */
import static Action.ActionResult.FAIL;
import EntityCollection.BestproductEntity;
import EntityCollection.CommentEntity;
import EntityCollection.ProductEntity;
import EntityCollection.ReviewEntity;
import Hibernate.ProductDAO;
import Hibernate.ProductHibDAO;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

public class ProductAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request;
    HttpServletResponse response;
    String result;
    ProductDAO product = new ProductDAO();
    ProductHibDAO productHib = new ProductHibDAO();

    public String productcategory() {
        String cid = request.getParameter("cid");
        result = FAIL;
        ArrayList<ProductEntity> productEntities = new ArrayList<ProductEntity>();

        try {
            if (cid != null) {

                productEntities = product.Checkcategory(Integer.parseInt(cid));
                //productbean.ShowAllProductString();
                request.setAttribute("productlist", productEntities);
            }
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }

        return SUCCESS;
    }

    public String productlist() {
        String pid = request.getParameter("pid");
        // req.setAttribute("myList", yourArrayList);       
        List<ProductEntity> productEntities;
       
        try {
            productEntities = productHib.ShowAllProduct();
            
        
            //productbean.ShowAllProductString();
            request.setAttribute("productlist", productEntities);
           
        //    request.setAttribute("ratecount", rate);
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }

        return SUCCESS;
    }

    public String productdetail() {
        String pid = request.getParameter("pid");
        
        result = FAIL;
        double rate;
        try {
            if (pid != null) {

                ProductEntity dbproductentity = productHib.ShowDetail(Integer.parseInt(pid));
                  rate = product.ShowRate(Integer.parseInt(pid));
                   List<CommentEntity> commentEntities = product.ShowComments(Integer.parseInt(pid));
                  
                if (dbproductentity.getProductid() != null && dbproductentity.getProductid() != 0) {
                    request.setAttribute("myProductDetail", dbproductentity);
                    request.setAttribute("ratecount", rate);
                    request.setAttribute("showcomment", commentEntities);
                    result = SUCCESS;
                }

            }
            

        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }

        return result;
    }

    public String bestproduct() {

        List<BestproductEntity> bestproductEntities;
        try {
            bestproductEntities = product.ShowBestProduct();
            //productbean.ShowAllProductString();
            request.setAttribute("bestproductlist", bestproductEntities);
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }

        return SUCCESS;
    }

    public String reviewrating() {

        HttpSession session = request.getSession();
        int customerid = 0;
        
        String sessionID = "";
        String comment = (String) request.getParameter("comment");
        String strProductID = (String) request.getParameter("productid");
        int productid = Integer.parseInt(strProductID);
        String strRating = (String) request.getParameter("rating");
        int rating = Integer.parseInt(strRating);
        String strtype = (String) request.getParameter("type");
        int type2 = Integer.parseInt(strtype);

        try {
            String strCustID = session.getAttribute("customerid").toString();
            customerid = Integer.parseInt(strCustID);

        } catch (Exception ex) {
            customerid = 0;
        }
        if (customerid == 0) {
            sessionID = session.getId();

        }

        try {
            if (comment != null) {

       
                int customerID=product.CheckType2(customerid,productid,type2);
                if(customerID!=customerid)
                {
                int ratingID = product.InsertComment(productid, customerid, rating, comment,type2);
                request.setAttribute("ratecount", ratingID);
                 
                }
                else{
                    int update2 = product.UpdateType2(rating,customerid,productid,comment,type2);
                }
            //    int ratingID = product.InsertComment(productid, customerid, rating, comment,type2);
            //    List<CommentEntity> commentEntities = product.ShowComments(productid);
                
            //    request.setAttribute("showcomment", commentEntities);
                ProductEntity dbproductentity = productHib.ShowDetail(Integer.parseInt(strProductID));

            request.setAttribute("myProductDetail", dbproductentity);
            }

        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }

        return SUCCESS;
    }

    public String rating() {

        HttpSession session = request.getSession();
        int customerid = 0;

    //    String emailaddress = "";

        String sessionID = "";

    //    emailaddress = session.getAttribute("customeremail").toString();

        //  String comment = (String) request.getParameter("comment");
        String strProductID = (String) request.getParameter("pid");
        int productid = Integer.parseInt(strProductID);
        String strRating = (String) request.getParameter("rate");
         String strtype = (String) request.getParameter("type");
        int type1 = Integer.parseInt(strtype);

        try {
            String strCustID = session.getAttribute("customerid").toString();
            customerid = Integer.parseInt(strCustID);

        } catch (Exception ex) {
            customerid = 0;
        }
        if (customerid == 0) {
            sessionID = session.getId();

        }

        try {
            if (strRating != null) {
                int rating = Integer.parseInt(strRating);
                int customerID=product.CheckType1(customerid,productid,type1);
                if(customerID!=customerid)
                {
                int ratingID = product.InsertRate(productid, customerid, rating,type1);
                request.setAttribute("ratecount", ratingID);
                 
                }
                else{
                    int update1 = product.UpdateType1(rating,customerid,productid,type1);
                }
                ProductEntity dbproductentity = productHib.ShowDetail(Integer.parseInt(strProductID));

            request.setAttribute("myProductDetail", dbproductentity);
            }

        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }
        request.setAttribute("pid", productid);
        
        return SUCCESS;
    }
    
    public String viewcomment() {

        String pid = request.getParameter("pid");
        
        List<CommentEntity> commentEntities;
        try {
          
                commentEntities = product.ShowComments(Integer.parseInt(pid));
                
                request.setAttribute("showcomment", commentEntities);
                
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }

        return SUCCESS;

        
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public HttpServletRequest getServletRequest() {
        return this.request;
    }
}
