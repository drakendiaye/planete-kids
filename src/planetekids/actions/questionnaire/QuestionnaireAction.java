package planetekids.actions.questionnaire;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Iterator;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import planetekids.beans.entity.QuestionnaireBean;
import planetekids.beans.stateful.CustomerBean;
import planetekids.beans.stateful.CustomerRemote;

public class QuestionnaireAction extends ActionSupport implements SessionAware, ParameterAware {

    private Map session;
    private Map parameters;
    private int id;

    public void setSession(Map session) {
        this.session = session;
    }

    public void setParameters(Map parameters) {
        this.parameters = parameters;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuestionnaireBean getQuestionnaire() throws Exception {
        return getCustomer().getQuestionnaire(id);
    }

    public String submitQuestionnaire() throws Exception {
        String result = execute();
        if (result.compareTo(ActionSupport.ERROR) != 0) {
            Iterator<String> iter = parameters.keySet().iterator();
            while(iter.hasNext()) {
                String key = iter.next();
                if(key.startsWith("answer_") && !key.endsWith("_comment")) {
                    try {
                        int answer_id = Integer.decode(key.split("_")[1]);
                        String value = ((String[])parameters.get(key))[0];
                        String comment = "";
                        if(parameters.get(key+"_comment") != null) {
                            comment = ((String[])parameters.get(key+"_comment"))[0];
                        }
                        getCustomer().createResult(answer_id, value, comment);
                    } catch(Exception ex) {
                        ex.printStackTrace(System.err);
                        result = ActionSupport.ERROR;
                    }
                }
            }
        }
        return result;
    }
}
