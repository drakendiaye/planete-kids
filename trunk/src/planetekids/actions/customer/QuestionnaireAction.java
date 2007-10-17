package planetekids.actions.customer;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.struts2.interceptor.SessionAware;
import planetekids.beans.entity.QuestionBean;
import planetekids.beans.entity.QuestionnaireBean;
import planetekids.beans.stateful.CustomerBean;
import planetekids.beans.stateful.CustomerRemote;

public class QuestionnaireAction extends ActionSupport implements SessionAware {

    private Map session;
    private CustomerRemote customer;
    private int id;
    
    public void setSession(Map session) {
        this.session = session;
    }
    
    public String execute() throws Exception {
        try {
            customer = (CustomerRemote)session.get("customer");
            if(customer == null) {
                Context initialContext = new InitialContext();
                customer = (CustomerRemote)initialContext.lookup(CustomerBean.class.getName() + "_" + CustomerRemote.class.getName() + "@Remote");
                session.put("customer", customer);
            }
            customer.setCurrent_action("questionnaire");
            return ActionSupport.SUCCESS;
        } catch (NamingException ex) {
            return ActionSupport.ERROR;
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<QuestionnaireBean> getQuestionnaires() {
        return customer.getQuestionnaires();
    }
    
    public QuestionnaireBean getQuestionnaire() {
        return customer.getQuestionnaire(id);
    }
    
    public Set<QuestionBean> getQuestions() {
        return customer.getQuestionnaire(id).getQuestions();
    }
}
