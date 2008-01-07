package planetekids.actions.category;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.struts2.interceptor.SessionAware;
import planetekids.beans.entity.CategoryBean;
import planetekids.beans.stateful.CustomerBean;
import planetekids.beans.stateful.CustomerRemote;

public class IndexAction extends ActionSupport implements SessionAware {
    
    private Map session;
    
    public void setSession(Map session) {
        this.session = session;
    }
    
    public CustomerRemote getCustomer() {
        return (CustomerRemote)session.get("customer");
    }
    
    @Override
    public String execute() throws Exception {
        try {
            if(getCustomer() == null) session.put("customer", new InitialContext().lookup(CustomerBean.class.getName() + "_" + CustomerRemote.class.getName() + "@Remote"));
            return ActionSupport.SUCCESS;
        } catch (NamingException ex) {
            return ActionSupport.ERROR;
        }
    }
    
    public String redirect() throws Exception {
        session.put("content_action", "index_content");
        session.put("content_namespace", "/category");
        session.put("location_action", "index_location");
        session.put("location_namespace", "/category");
        return execute();
    }
    
    public List getCategories() throws Exception {
        return getCustomer().getCategories();
    }
    
    public CategoryBean getCategory(int id) throws Exception {
        return getCustomer().getCategory(id);
    }       
}
