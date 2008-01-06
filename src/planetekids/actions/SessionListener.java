package planetekids.actions;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import planetekids.beans.stateful.CustomerBean;

public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent arg0) {
        arg0.getSession().setMaxInactiveInterval(10);
    }

    public void sessionDestroyed(HttpSessionEvent arg0) {

        try {
            CustomerBean customer = (CustomerBean) arg0.getSession().getAttribute("customer");
            if (customer != null) {
                customer.LogOut();
                //todo : customer.EmptyBasket();
            }
        } catch (Exception ex) {

        }
    }
}
