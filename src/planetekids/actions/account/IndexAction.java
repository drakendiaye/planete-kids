package planetekids.actions.account;

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

    @Override
    public String execute() throws Exception {
        try {
            if(getCustomer() == null) session.put("customer", new InitialContext().lookup(CustomerBean.class.getName() + "_" + CustomerRemote.class.getName() + "@Remote"));
            if(parameters.get("callback") != null) {
                session.remove("callback");
                session.put("callback", ((String[])parameters.get("callback"))[0]);
            }
            return ActionSupport.SUCCESS;
        } catch (NamingException ex) {
            return ActionSupport.ERROR;
        }
    }

    public String redirect() throws Exception {
        session.put("content_action", "index_content");
        session.put("content_namespace", "/account");
        session.put("location_action", "index_location");
        session.put("location_namespace", "/account");
        return execute();
    }

    public String submit() throws Exception {
        //todo
        return execute();
    }

    public String redirect_create() throws Exception {
        session.put("content_action", "create_content");
        session.put("content_namespace", "/account");
        session.put("location_action", "create_location");
        session.put("location_namespace", "/account");
        return execute();
    }

    public String submit_create() throws Exception {
        getCustomer().LogIn("missog@club-internet.fr", "azerty");
        return execute();
    }
    
    public String redirect_identify() throws Exception {
        session.put("content_action", "identify_content");
        session.put("content_namespace", "/account");
        session.put("location_action", "identify_location");
        session.put("location_namespace", "/account");
        return execute();
    }
    
    public String submit_identify() throws Exception {
        String result = execute();
        if (result.compareTo(ActionSupport.ERROR) != 0) {
            if(((String[])parameters.get("email"))[0] == null || ((String[])parameters.get("password"))[0] == null) {
                return("badinput");
            }
            if(!getCustomer().LogIn(((String[])parameters.get("email"))[0], ((String[])parameters.get("password"))[0])) {
                return("badinput");
            }
        }
        return result;
    }
}
