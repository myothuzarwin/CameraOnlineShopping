/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import Hibernate.CheckoutDAO;
import Hibernate.CheckoutHibDAO;
import static Action.ActionResult.FAIL;
import EntityCollection.CustomerEntity;
import EntityCollection.DiscountEntity;
import EntityCollection.OrderEntity;
import EntityCollection.ProductEntity;
import Hibernate.CartDAO;
import EntityCollection.ShoppingCartEntity;
import Hibernate.CustomerHibDAO;
import Hibernate.ProductDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author myothuzar
 */
public class CheckoutAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request;

    String result;
    CheckoutDAO checkout = new CheckoutDAO();
    CartDAO cart = new CartDAO();
    ProductDAO product = new ProductDAO();
    CheckoutHibDAO checkoutHib = new CheckoutHibDAO();
    CustomerHibDAO custhib = new CustomerHibDAO();

    public String OrderAddressInit() {
        HttpSession session = request.getSession();
        int customerid = 0;
        String strCustID = "";
        String sessionID = "";
        result = SUCCESS;

        CustomerEntity customer = new CustomerEntity();

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
            customer = custhib.GetCustomerByID(customerid);
            request.setAttribute("customer", customer);
            request.setAttribute("customertest", "HELLO");

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + " " + ex.getStackTrace());
        }

        return result;
    }

    public String orderaddress() {
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

        String formstatus = (String) request.getParameter("formstatus");

        try {
            String receivername = (String) request.getParameter("receiver_name");
            //    String companyname = (String) request.getParameter("company");
            String address = (String) request.getParameter("address");
            String postal_code = (String) request.getParameter("postalcode");
            String telephoneno = (String) request.getParameter("telephoneno");
            String e_mail = (String) request.getParameter("email");
            // int result = checkoutbean.InsertReceiver(receivername, companyname, address, address, postal_code, telephoneno, e_mail);
            session.setAttribute("add_receivername", receivername);
            //    session.setAttribute("add_companyname", companyname);
            session.setAttribute("add_address", address);
            session.setAttribute("add_postalcode", postal_code);
            session.setAttribute("add_telephoneno", telephoneno);
            session.setAttribute("add_email", e_mail);

        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }

        return SUCCESS;
    }

    public String deliverymethod() {
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

        try {
            String delivery = (String) request.getParameter("delivery");
            //    boolean submitButtonPressed = request.getParameter("submit") != null;
            if (delivery != null) {
                session.setAttribute("del_delivery", delivery);

            }
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }

        return SUCCESS;

    }

    public String paymentmethod() {
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
            String payment = (String) request.getParameter("payment");
            session.setAttribute("pay_payment", payment);
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
            request.setAttribute("distamount", distAmount);
            request.setAttribute("totalamt", totAmount);
            request.setAttribute("couponcode", disEntity.getDcouponno());

            request.setAttribute("shoppingcartdetail", dbshoppingcartentity);
            session.setAttribute("sub_Total", subTotal);
            session.setAttribute("discount_Per", discountPer);
            session.setAttribute("dist_Amount", distAmount);
            session.setAttribute("tot_Amount", totAmount);
        } catch (Exception ex) {

            System.err.println(ex.getStackTrace());
        }

        return SUCCESS;

    }

    public String placeorder() {
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

        try {

            String receivername = (String) session.getAttribute("add_receivername");
            //    String companyname = (String) session.getAttribute("add_companyname");
            String address = (String) session.getAttribute("add_address");
            String postalcode = (String) session.getAttribute("add_postalcode");
            String telephoneno = (String) session.getAttribute("add_telephoneno");
            String email = (String) session.getAttribute("add_email");
            String delivery = (String) session.getAttribute("del_delivery");
            String payment = (String) session.getAttribute("pay_payment");
            int subTotal = (Integer) session.getAttribute("sub_Total");
            double discountPer = (Double) session.getAttribute("discount_Per");
            double distAmount = (Double) session.getAttribute("dist_Amount");
            double totAmount = (Double) session.getAttribute("tot_Amount");

            OrderEntity ord = new OrderEntity();
            ord.setCustomerid(customerid);
            ord.setReceivername(receivername);
            ord.setReceiveraddress(address);
            ord.setReceiverpostalcode(postalcode);
            ord.setReceiverphno(telephoneno);
            ord.setReceiveremail(email);
            ord.setDelivery(delivery);
            ord.setPayment(payment);
            ord.setSubTotal(subTotal);
            ord.setDiscPer(discountPer);
            ord.setDiscAmt(distAmount);
            ord.setTotAmount(totAmount);

            //customerid, receivername,address, postalcode, telephoneno, email, delivery, payment,subTotal,discountPer,totAmount);
            //int result = checkoutHib.Placeorder(ord);
            int result = checkout.Placeorder(ord);

            System.out.println("Successful");

            checkout.Placeorderdetail(result, customerid);

            ArrayList<ProductEntity> productEntities = new ArrayList<ProductEntity>();
            productEntities = product.ShowAllProduct();
            //productbean.ShowAllProductString();
            request.setAttribute("productlist", productEntities);

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
