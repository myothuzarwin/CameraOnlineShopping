package myApp;

import javax.servlet.*;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.hibernate.HibernateException;

public class Struts2Dispatcher extends StrutsPrepareAndExecuteFilter
{

    /**
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        super.init(filterConfig);
        try
        {
            HibernateUtil.createSessionFactory();
            System.out.println("*** Application initializing successfully ***");
        }
        catch (HibernateException e)
        {
            System.out.println("*** Hibernate Hexception***" + e.getMessage() +"    " + e.getStackTrace());
            throw new ServletException(e);
        }
    }
}
