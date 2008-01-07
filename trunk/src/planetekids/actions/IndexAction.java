package planetekids.actions;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import planetekids.beans.stateful.CustomerBean;
import planetekids.beans.stateful.CustomerRemote;

public class IndexAction extends ActionSupport implements SessionAware, ParameterAware {

    private Map session;
    private Map parameters;
    
    public void setSession(Map session) {
        this.session = session;
    }
    
    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }

    public CustomerRemote getCustomer() {
        return (CustomerRemote) session.get("customer");
    }
    
    public String getCallback() {
        return (String)session.remove("callback");
    }

    @Override
    public String execute() throws Exception {
        try {
            if (getCustomer() == null) session.put("customer", new InitialContext().lookup(CustomerBean.class.getName() + "_" + CustomerRemote.class.getName() + "@Remote"));
            if(parameters.get("callback") != null) {
                session.remove("callback");
                session.put("callback", ((String[])parameters.get("callback"))[0]);
            }
            return ActionSupport.SUCCESS;
        } catch (NamingException ex) {
            return ActionSupport.ERROR;
        }
    }
    
    public String logout() throws Exception {
        String result = execute();
        if(getCustomer() != null) getCustomer().LogOut();
        return result;
    }

    public String getCart_action() {
        String action = (String) session.remove("cart_action");
        if (action == null) {
            return "index_cart";
        } else {
            return action;
        }
    }

    public String getCart_namespace() {
        String namespace = (String) session.remove("cart_namespace");
        if (namespace == null) {
            return "/";
        } else {
            return namespace;
        }
    }

    public String getCatalogue_action() {
        String action = (String) session.remove("catalogue_action");
        if (action == null) {
            return "index_catalogue";
        } else {
            return action;
        }
    }

    public String getCatalogue_namespace() {
        String namespace = (String) session.remove("catalogue_namespace");
        if (namespace == null) {
            return "/";
        } else {
            return namespace;
        }
    }

    public String getContent_action() {
        String action = (String) session.remove("content_action");
        if (action == null) {
            return "index_content";
        } else {
            return action;
        }
    }

    public String getContent_namespace() {
        String namespace = (String) session.remove("content_namespace");
        if (namespace == null) {
            return "/";
        } else {
            return namespace;
        }
    }

    public String getFooter_action() {
        String action = (String) session.remove("footer_action");
        if (action == null) {
            return "index_footer";
        } else {
            return action;
        }
    }

    public String getFooter_namespace() {
        String namespace = (String) session.remove("footer_namespace");
        if (namespace == null) {
            return "/";
        } else {
            return namespace;
        }
    }

    public String getGeneralmenu_action() {
        String action = (String) session.remove("generalmenu_action");
        if (action == null) {
            return "index_generalmenu";
        } else {
            return action;
        }
    }

    public String getGeneralmenu_namespace() {
        String namespace = (String) session.remove("generalmenu_namespace");
        if (namespace == null) {
            return "/";
        } else {
            return namespace;
        }
    }

    public String getHeader_action() {
        String action = (String) session.remove("header_action");
        if (action == null) {
            return "index_header";
        } else {
            return action;
        }
    }

    public String getHeader_namespace() {
        String namespace = (String) session.remove("header_namespace");
        if (namespace == null) {
            return "/";
        } else {
            return namespace;
        }
    }

    public String getLocation_action() {
        String action = (String) session.remove("location_action");
        if (action == null) {
            return "index_location";
        } else {
            return action;
        }
    }

    public String getLocation_namespace() {
        String namespace = (String) session.remove("location_namespace");
        if (namespace == null) {
            return "/";
        } else {
            return namespace;
        }
    }

}
