package planetekids.actions;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.interceptor.SessionAware;
import planetekids.beans.stateful.CustomerBean;
import planetekids.beans.stateful.CustomerRemote;

public class IndexAction extends ActionSupport implements SessionAware {

    private Map session;

    private List<List<String>> callback;
    
    public void setSession(Map session) {
        this.session = session;
    }

    private CustomerRemote getCustomer() {
        return (CustomerRemote) session.get("customer");
    }

    @Override
    public String execute() throws Exception {
        try {
            if (getCustomer() == null) {
                session.put("customer", new InitialContext().lookup(CustomerBean.class.getName() + "_" + CustomerRemote.class.getName() + "@Remote"));
            }
            return ActionSupport.SUCCESS;
        } catch (NamingException ex) {
            return ActionSupport.ERROR;
        }
    }
    
    public String callback() throws Exception {
        buildCallback();
        return execute();
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
    
    private void buildCallback() {
        String action;
        String namespace;
        List<String> elem;
        callback = new ArrayList<List<String>>();

        action = (String) session.remove("cart_action");
        namespace = (String) session.remove("cart_namespace");
        if (action != null && namespace != null) {
            elem = new ArrayList<String>();
            elem.add("cart");
            elem.add(namespace);
            elem.add(action);
            callback.add(elem);
        }

        action = (String) session.remove("catalogue_action");
        namespace = (String) session.remove("catalogue_namespace");
        if (action != null && namespace != null) {
            elem = new ArrayList<String>();
            elem.add("catalogue");
            elem.add(namespace);
            elem.add(action);
            callback.add(elem);
        }

        action = (String) session.remove("content_action");
        namespace = (String) session.remove("content_namespace");
        if (action != null && namespace != null) {
            elem = new ArrayList<String>();
            elem.add("content");
            elem.add(namespace);
            elem.add(action);
            callback.add(elem);
        }

        action = (String) session.remove("footer_action");
        namespace = (String) session.remove("footer_namespace");
        if (action != null && namespace != null) {
            elem = new ArrayList<String>();
            elem.add("footer");
            elem.add(namespace);
            elem.add(action);
            callback.add(elem);
        }

        action = (String) session.remove("generalmenu_action");
        namespace = (String) session.remove("generalmenu_namespace");
        if (action != null && namespace != null) {
            elem = new ArrayList<String>();
            elem.add("generalmenu");
            elem.add(namespace);
            elem.add(action);
            callback.add(elem);
        }

        action = (String) session.remove("header_action");
        namespace = (String) session.remove("header_namespace");
        if (action != null && namespace != null) {
            elem = new ArrayList<String>();
            elem.add("header");
            elem.add(namespace);
            elem.add(action);
            callback.add(elem);
        }

        action = (String) session.remove("location_action");
        namespace = (String) session.remove("location_namespace");
        if (action != null && namespace != null) {
            elem = new ArrayList<String>();
            elem.add("location");
            elem.add(namespace);
            elem.add(action);
            callback.add(elem);
        }
    }

    public List<List<String>> getCallback() {
        return callback;
    }
}
