package planetekids.actions.customer;


import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import planetekids.beans.entity.QuestionnaireBean;
import planetekids.beans.stateful.CustomerBean;
import planetekids.beans.stateful.CustomerRemote;

public class IndexAction extends ActionSupport {

    private CustomerRemote customer;
    
    public String execute() throws Exception {
        try {
            Context initialContext = new InitialContext();
            setCustomer((CustomerRemote)initialContext.lookup(CustomerBean.class.getName() + "_" + CustomerRemote.class.getName() + "@Remote"));
            return ActionSupport.SUCCESS;
        } catch (NamingException ex) {
            return ActionSupport.ERROR;
        }
    }

    public CustomerRemote getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerRemote customer) {
        this.customer = customer;
    }

}
