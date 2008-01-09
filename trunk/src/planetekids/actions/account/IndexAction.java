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
    
    public String getParameter(String parameter) throws Exception {
        if (parameters.get(parameter) == null)
            return null;
        return ((String[])parameters.get(parameter))[0];
    }
    
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
    
    public String redirect_modify() throws Exception {
        session.put("content_action", "modify_content");
        session.put("content_namespace", "/account");
        session.put("location_action", "modify_location");
        session.put("location_namespace", "/account");
        return execute();
    }
    
    public String submit_modify() throws Exception {
        String result = execute();
        String firstName = getParameter("firstName");
        String lastName = getParameter("lastName");
        String addressLine1 = getParameter("addressLine1");
        String addressLine2 = getParameter("addressLine2");
        String addressLine3 = getParameter("addressLine3");
        int zipCode = 0;
        try {
            zipCode= Integer.parseInt(getParameter("zipCode"));
        } catch (Exception e) {
        }
        String city = getParameter("city");
        String phoneNumber = getParameter("phoneNumber");
        String faxNumber = getParameter("faxNumber");
        if (result.compareTo(ActionSupport.ERROR) != 0) {
            getCustomer().setAccountFirstName(firstName);
            getCustomer().setAccountLastName(lastName);
            getCustomer().setAccountAddressLine1(addressLine1);
            getCustomer().setAccountAddressLine2(addressLine2);
            getCustomer().setAccountAddressLine3(addressLine3);
            getCustomer().setAccountZipCode(zipCode);
            getCustomer().setAccountCity(city);
            getCustomer().setAccountPhoneNumber(phoneNumber);
            getCustomer().setAccountFaxNumber(faxNumber);
        }
        return result;
    }
    
    public String redirect_create() throws Exception {
        session.put("content_action", "create_content");
        session.put("content_namespace", "/account");
        session.put("location_action", "create_location");
        session.put("location_namespace", "/account");
        return execute();
    }
    
    public String submit_create() throws Exception {
        String result = execute();
        String email = getParameter("email");
        String password = getParameter("password");
        String firstName = getParameter("firstName");
        String lastName = getParameter("lastName");
        String addressLine1 = getParameter("addressLine1");
        String addressLine2 = getParameter("addressLine2");
        String addressLine3 = getParameter("addressLine3");
        int zipCode = 0;
        try {
            zipCode= Integer.parseInt(getParameter("zipCode"));
        } catch (Exception e) {
        }
        String city = getParameter("city");
        String phoneNumber = getParameter("phoneNumber");
        String faxNumber = getParameter("faxNumber");
        if (result.compareTo(ActionSupport.ERROR) != 0) {
            if (email == null || password == null) {
                return("badinput");
            }
            getCustomer().createAccount(email, password, firstName, lastName, addressLine1, addressLine2, addressLine3, zipCode, city, phoneNumber, faxNumber);
        }
        return result;
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
