package Action;

import static Action.ActionResult.FAIL;
import EntityCollection.DiscountApplyCustomerEntity;
import EntityCollection.DiscountEntity;
import Hibernate.CartDAO;
import Hibernate.CartHibDAO;
import EntityCollection.ShoppingCartEntity;
import com.opensymphony.xwork2.ActionSupport;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author myothuzar
 */
public class ShoppingCartAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request;
    String result;
    CartDAO cart = new CartDAO();
    CartHibDAO cartHib = new CartHibDAO();
    DecimalFormat formatter = new DecimalFormat("###,###,###.##");

    public String shoppingcartlist() {
        HttpSession session = request.getSession();

        int customerid = 0;
        String strCustID = "";
        String sessionID = "";
        int subTotal = 0;
        double discountPer = 0.0;
        double totAmount = 0.0;
        double distAmount = 0.0;

        try {
            strCustID = session.getAttribute("customerid").toString();
            customerid = Integer.parseInt(strCustID);

        } catch (Exception ex) {
            customerid = 0;
        }

        if (customerid == 0) {
            sessionID = session.getId();
        }

        try {
            ArrayList<ShoppingCartEntity> dbshoppingcartentity = new ArrayList<ShoppingCartEntity>();
            DiscountEntity disEntity = new DiscountEntity();
            dbshoppingcartentity = cart.ShowShoppingCart(customerid);

            for (ShoppingCartEntity s : dbshoppingcartentity) {
                subTotal = subTotal + (s.getQty() * s.getPrice());
            }
            disEntity = cart.GetAppliedDiscount(customerid);
            int discPer = disEntity.getDistper();
            discountPer = discPer;

            totAmount = subTotal - (subTotal * (discountPer / 100.00));
            distAmount = (subTotal * (discountPer / 100.00));
            request.setAttribute("subtotal", subTotal);
            request.setAttribute("discountper", discountPer);
            request.setAttribute("distamount", formatter.format(distAmount));
            request.setAttribute("totalamt", formatter.format(totAmount));
            request.setAttribute("couponcode", disEntity.getDcouponno());

            request.setAttribute("shoppingcartdetail", dbshoppingcartentity);
            // request.getRequestDispatcher("shoppingcart.jsp").forward(request, response);
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }
        return SUCCESS;
    }

    public String Addtocart() {
        HttpSession session = request.getSession();

        int customerid = 0;
        String strCustID = "";
        String sessionID = "";
        int qty = 0;
        int price = 0;
        String productname = "";
        String photopath = "";
        int productid = 0;
        result = FAIL;
        Date currDate = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        ShoppingCartEntity newCart = new ShoppingCartEntity();
        try {
            strCustID = session.getAttribute("customerid").toString();
            customerid = Integer.parseInt(strCustID);

        } catch (Exception ex) {
            customerid = 0;
        }

        if (customerid == 0) {
            sessionID = session.getId();
        }

        productid = Integer.parseInt(request.getParameter("productid"));

        qty = Integer.parseInt(request.getParameter("qty"));
        price = Integer.parseInt(request.getParameter("price"));
        productname = (String) request.getParameter("productname");
        photopath = (String) request.getParameter("photopath");

        try {

            ShoppingCartEntity mProduct = cart.GetProductInShoppingCart(customerid, productid);
            int result;
            if (mProduct.getProductid() == null) {

                newCart.setCustomerid(customerid);
                newCart.setProductid(productid);
                newCart.setProductname(productname);
                newCart.setQty(1);
                newCart.setPrice(price);
                newCart.setImageURL(photopath);
                newCart.setCreatedtime(currDate);
                newCart.setUpdatedtime(currDate);

                int insertedID = cartHib.AddShoppingCart(newCart);

            } else {

                int insertedID = cartHib.AddQtyUpdateCart(customerid, productid, mProduct.getQty());

            }

            ArrayList<ShoppingCartEntity> dbshoppingcartentity = cartHib.ShowShoppingCart(customerid);
            request.setAttribute("shoppingcartdetail", dbshoppingcartentity);

        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }

        return result;
    }

    public String delete() {
        HttpSession session = request.getSession();

        int customerid = 0;
        String strCustID = "";
        String sessionID = "";
        int qty = 0;
        int price = 0;
        String productname = "";
        int productid = 0;
        result = FAIL;
        try {
            strCustID = session.getAttribute("customerid").toString();
            customerid = Integer.parseInt(strCustID);

        } catch (Exception ex) {
            customerid = 0;
        }

        if (customerid == 0) {
            sessionID = session.getId();
        }

        productid = Integer.parseInt(request.getParameter("productid"));

        qty = Integer.parseInt(request.getParameter("qty"));
        price = Integer.parseInt(request.getParameter("price"));

        try {

            int result = cartHib.RemoveShoppingCart(customerid, productid);
            ArrayList<ShoppingCartEntity> dbshoppingcartentity = cart.ShowShoppingCart(customerid);

            request.setAttribute("shoppingcartdetail", dbshoppingcartentity);

        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }

        return result;
    }

    public String update1() {
        HttpSession session = request.getSession();
        int customerid = 0;
        String strCustID = "";
        String sessionID = "";
        int qty = 0;
        int price = 0;
        String productname = "";
        int productid = 0;
        result = FAIL;
        try {
            strCustID = session.getAttribute("customerid").toString();
            customerid = Integer.parseInt(strCustID);

        } catch (Exception ex) {
            customerid = 0;
        }

        if (customerid == 0) {
            sessionID = session.getId();
        }

        productid = Integer.parseInt(request.getParameter("productid"));

        qty = Integer.parseInt(request.getParameter("qty"));
        price = Integer.parseInt(request.getParameter("price"));
        //if ((productid!=(Integer)dbshoppingcartentity.getProductid())&& (customerid!=(Integer)dbshoppingcartentity.getCustomerid())
        productname = (String) request.getParameter("productname"); //if ((productid!=(Integer)dbshoppingcartentity.getProductid())&& (customerid!=(Integer)dbshoppingcartentity.getCustomerid())

        try {
            //    ShoppingCartEntity dbshoppingcartentity = shoppingcartbean.AddShoppingCart(Integer.parseInt(customerID),Integer.parseInt(productid),Integer.parseInt(qty));
            int result = cartHib.AddQtyUpdateCart(customerid, productid, qty);
            ArrayList<ShoppingCartEntity> dbshoppingcartentity = cart.ShowShoppingCart(customerid);

            request.setAttribute("shoppingcartdetail", dbshoppingcartentity);

            //Integer.parseInt(pid)
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }

        return result;
    }

    public String update2() {

        int customerid = 0;
        String strCustID = "";
        String sessionID = "";
        int qty = 0;
        int price = 0;
        String productname = "";
        int productid = 0;
        result = FAIL;

        productid = Integer.parseInt(request.getParameter("productid"));

        qty = Integer.parseInt(request.getParameter("qty"));
        price = Integer.parseInt(request.getParameter("price"));
        //if ((productid!=(Integer)dbshoppingcartentity.getProductid())&& (customerid!=(Integer)dbshoppingcartentity.getCustomerid())
        productname = (String) request.getParameter("productname") //if ((productid!=(Integer)dbshoppingcartentity.getProductid())&& (customerid!=(Integer)dbshoppingcartentity.getCustomerid())
                ;

        //    int productid1 = Integer.parseInt(request.getParameter("productid"));
        try {
            //    ShoppingCartEntity dbshoppingcartentity = shoppingcartbean.AddShoppingCart(Integer.parseInt(customerID),Integer.parseInt(productid),Integer.parseInt(qty));
            int result = cartHib.ReduceQtyUpdateCart(customerid, productid, qty);
            ArrayList<ShoppingCartEntity> dbshoppingcartentity = cart.ShowShoppingCart(customerid);

            request.setAttribute("shoppingcartdetail", dbshoppingcartentity);

            //Integer.parseInt(pid)
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }

        return result;
    }

    public String discountcustomer() {
        HttpSession session = request.getSession();
        int customerid = 0;
        String strCustID = "";
        String sessionID = "";
        result = FAIL;
        try {
            strCustID = session.getAttribute("customerid").toString();
            customerid = Integer.parseInt(strCustID);

        } catch (Exception ex) {
            customerid = 0;
        }
        if (customerid == 0) {
            sessionID = session.getId();

        }

        DiscountEntity entity = new DiscountEntity();
        DiscountEntity appliedentity = new DiscountEntity();
        DiscountApplyCustomerEntity discCustomer = new DiscountApplyCustomerEntity();
        try {
            String strcouponNo = (String) request.getParameter("txtCouponNo");
            //    boolean submitButtonPressed = request.getParameter("submit") != null;
            if (strcouponNo != null) {
                entity = cart.GetDiscount(Integer.parseInt(strcouponNo));

                if (entity.getDistID() > 0) {
                    appliedentity = cart.GetAppliedDiscount(customerid);

                    if (appliedentity.getDistID() == 0) {
                        discCustomer.setCustomerid(customerid);
                        discCustomer.setDistID(entity.getDistID());
                        cart.InsertDiscountCustomer(discCustomer);
                    }
                }
                /*
                 session.setAttribute("sub_total", subTotal);
                 session.setAttribute("discount_per", discountPer);
                 session.setAttribute("total_amt", totAmount);
                 */
                result = SUCCESS;
            }
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }

        return result;

    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public HttpServletRequest getServletRequest() {
        return this.request;
    }
}
