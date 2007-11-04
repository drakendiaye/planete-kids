package planetekids.actions.questionnaire;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.struts2.interceptor.SessionAware;
import planetekids.beans.entity.QuestionnaireBean;
import planetekids.beans.stateful.CustomerBean;
import planetekids.beans.stateful.CustomerRemote;

public class QuestionnaireAction extends ActionSupport implements SessionAware {

    private Map session;
    private int id;

    public void setSession(Map session) {
        this.session = session;
    }

    private CustomerRemote getCustomer() {
        return (CustomerRemote) session.get("customer");
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuestionnaireBean getQuestionnaire() {
        return getCustomer().getQuestionnaire(id);
    }
}
