package planetekids.actions.questionnaire;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.struts2.interceptor.SessionAware;
import planetekids.beans.entity.QuestionnaireBean;
import planetekids.beans.stateful.CustomerBean;
import planetekids.beans.stateful.CustomerRemote;

public class IndexAction extends ActionSupport implements SessionAware {
    
    private Map session;
    
    public void setSession(Map session) {
        this.session = session;
    }
    
    private CustomerRemote getCustomer() {
        return (CustomerRemote)session.get("customer");
    }
    
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
        session.put("content_namespace", "/questionnaire");
        session.put("location_action", "index_location");
        session.put("location_namespace", "/questionnaire");
        session.put("contextualmenu_action", "index_contextualmenu");
        session.put("contextualmenu_namespace", "/questionnaire");
        return execute();
    }
    
    
    public List<QuestionnaireBean> getQuestionnaires() {
        return getCustomer().getQuestionnaires();
    }
            
}
