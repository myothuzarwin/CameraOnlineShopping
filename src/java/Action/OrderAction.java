/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import static Action.ActionResult.FAIL;
import static Action.ActionResult.FORWARD;
import EntityCollection.OrderHeaderInfo;
import EntityCollection.OrderdetailEntity;
import Hibernate.OrderDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author myothuzar
 */
public class OrderAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request;
    String result;
    OrderDAO order = new OrderDAO();

    public String order() {
        HttpSession session = request.getSession();

        int customerid = 0;
        String strCustID = "";
        String sessionID = "";
        int qty = 0;

        try {
            strCustID = session.getAttribute("customerid").toString();
            customerid = Integer.parseInt(strCustID);
        } catch (Exception ex) {
            customerid = 0;
        }

        if (customerid != 0) {

            String orderid = request.getParameter("orderid");

            if (orderid != null) {

                ArrayList<OrderdetailEntity> dborderdetailentity = order.ShowDetail(Integer.parseInt(orderid));
                request.setAttribute("orderdetail", dborderdetailentity);

                result = FORWARD;
            } else {
                ArrayList<OrderHeaderInfo> dborderheaderinfo = order.History(customerid);
                request.setAttribute("orderhistory", dborderheaderinfo);
                result = SUCCESS;
            }

        } else {

            result = FAIL;
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
