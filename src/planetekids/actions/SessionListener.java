package planetekids.actions;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import planetekids.beans.stateful.CustomerRemote;

public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent arg0) {
        arg0.getSession().setMaxInactiveInterval(300);
    }

    public void sessionDestroyed(HttpSessionEvent arg0) {
        try {
            CustomerRemote customer = (CustomerRemote) arg0.getSession().getAttribute("customer");
            if (customer != null) {
                customer.LogOut();
                customer.flushCart();
                //TODO : delete non-validated command
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
