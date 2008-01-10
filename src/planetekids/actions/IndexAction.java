package planetekids.actions;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import planetekids.beans.entity.ProductBean;
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

    @Override
    public String execute() throws Exception {
        try {
            if (getCustomer() == null) {
                session.put("customer", new InitialContext().lookup(CustomerBean.class.getName() + "_" + CustomerRemote.class.getName() + "@Remote"));
            }
            if (parameters.get("callback") != null) {
                session.remove("callback");
                session.put("callback", ((String[]) parameters.get("callback"))[0]);
            }
            return ActionSupport.SUCCESS;
        } catch (Exception ex) {
            return ActionSupport.ERROR;
        }
    }

    public String updateCart() throws Exception {
        String result = execute();
        Iterator<String> iterator = parameters.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String[] command = key.split("_");
            try {
                if (command[0].equals("add")) {
                    getCustomer().setCartProductNumber(new Integer(command[1]), 1);
                } else if (command[0].equals("delete")) {
                    getCustomer().setCartProductNumber(new Integer(command[1]), 0);
                } else if (command[0].equals("update")) {
                    getCustomer().setCartProductNumber(new Integer(command[1]), new Integer(command[2]));
                } else if (command[0].equals("flush")) {
                    getCustomer().flushCart();
                } else if (command[0].equals("validate")) {
                    getCustomer().validateCart();
                }
            } catch (Exception ex) {

            }
        }
        return result;
    }

    public void flushCart() throws Exception {
        getCustomer().flushCart();
    }

    public String logout() throws Exception {
        String result = execute();
        if (getCustomer() != null) {
            getCustomer().LogOut();
        }
        return result;
    }

    public String getCallback() {
        return (String) session.remove("callback");
    }

    public int validateCart() throws Exception {
        return getCustomer().validateCart();
    }

    public float getCartPrice() throws Exception {
        return getCustomer().getCartPrice();
    }

    public List<ProductBean> getCartProducts() throws Exception {
        return getCustomer().getCartProducts();
    }

    public void setCartProductNumber(int product_id, int product_number) throws Exception {
        getCustomer().setCartProductNumber(product_id, product_number);
    }

    public int getCartProductNumber(int product_id) throws Exception {
        return getCustomer().getCartProductNumber(product_id);
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

    public String getCart_parameters() {
        return (String) session.remove("cart_parameters");
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
            return "/product";
        } else {
            return namespace;
        }
    }
    
    public String getCatalogue_parameters() {
        return (String) session.remove("catalogue_parameters");
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
    
    public String getContent_parameters() {
        return (String) session.remove("content_parameters");
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
    
    public String getFooter_parameters() {
        return (String) session.remove("footer_parameters");
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
    
    public String getGeneralmenu_parameters() {
        return (String) session.remove("generalmenu_parameters");
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
    
    public String getHeader_parameters() {
        return (String) session.remove("header_parameters");
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
    
    public String getLocation_parameters() {
        return (String) session.remove("location_parameters");
    }
}
