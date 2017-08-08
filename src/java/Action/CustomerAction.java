/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import java.util.Date;
import static Action.ActionResult.FAIL;
import EntityCollection.CustomerEntity;
import Hibernate.CustomerDAO;
import Hibernate.CustomerHibDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author myothuzar
 */
public class CustomerAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request;
    CustomerDAO customer = new CustomerDAO();
    CustomerHibDAO customerHib = new CustomerHibDAO();

    @Override
    public String execute() {
        request.getParameter("");
        return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public HttpServletRequest getServletRequest() {
        return this.request;
    }

    public String Authenticate() {
        HttpSession session = request.getSession();
        String userid = (String) request.getParameter("userid");
        String password = (String) request.getParameter("password");
        String retStatus = INPUT;
        try {

            CustomerEntity custinfo = customerHib.CheckCustomerLogin(userid, password);

            if (custinfo != null && custinfo.getCustomerid() > 0) {
                session.setAttribute("customerid", custinfo.getCustomerid());
                session.setAttribute("customername", custinfo.getCustomername());
                session.setAttribute("customeremail", custinfo.getEmailaddress());
                retStatus = SUCCESS;
            } else {
                //no userinformation and redirect to login page with validation message
                request.setAttribute("loginmsg", "Login ID or password is incorrect!");
                retStatus = INPUT;
            }
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }
        return retStatus;
    }

    public String signout() {
        HttpSession session = request.getSession();
        String result = FAIL;
        try {
            if (session != null) {
                session.invalidate();
                result = SUCCESS;
            }

        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }

        return result;
    }

    public String singnup() {
        String retStatus = INPUT;
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        CustomerEntity newCustomer = new CustomerEntity();

        try {

            String customername = (String) request.getParameter("customer_name");

            String startDateStr = request.getParameter("birthday");
            Date dob = format.parse(startDateStr);

            String gender = (String) request.getParameter("gender");
            String address = (String) request.getParameter("address");
            String postal_code = (String) request.getParameter("postal_code");
            String phone_no = (String) request.getParameter("phone_no");
            String e_mail = (String) request.getParameter("e_mail");
            String password = (String) request.getParameter("password");

            newCustomer.setCustomername(customername);
            newCustomer.setBirthday(dob);
            newCustomer.setGender(gender);
            newCustomer.setAddress(address);
            newCustomer.setPostalcode(postal_code);
            newCustomer.setPhoneno(phone_no);
            newCustomer.setEmailaddress(e_mail);
            newCustomer.setPassword(password);
            int custID = customerHib.InsertCustomer(newCustomer);

            retStatus = SUCCESS;
        } catch (ParseException ex) {
            Logger.getLogger(CustomerAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Successful");

        return retStatus;
    }
}
