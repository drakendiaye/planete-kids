package planetekids.actions.age;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import org.apache.struts2.interceptor.SessionAware;
import planetekids.beans.entity.AgeBean;
import planetekids.beans.stateful.CustomerBean;
import planetekids.beans.stateful.CustomerRemote;

public class IndexAction extends ActionSupport implements SessionAware {

    private Map session;

    public void setSession(Map session) {
        this.session = session;
    }

    public CustomerRemote getCustomer() {
        return (CustomerRemote) session.get("customer");
    }

    @Override
    public String execute() throws Exception {
        try {
            int ok = 5;
            while (ok > 0) {
                try {
                    if (getCustomer().test()) break;
                } catch (Exception ex) {
                    session.put("customer", new InitialContext().lookup(CustomerBean.class.getName() + "_" + CustomerRemote.class.getName() + "@Remote"));
                }
                ok--;
            }
            if (ok == 0) {
                throw new Exception();
            }

            return ActionSupport.SUCCESS;
        } catch (Exception ex) {
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

    public List getAges() throws Exception {
        return getCustomer().getAges();
    }

    public AgeBean getAge(int id) throws Exception {
        return getCustomer().getAge(id);
    }
}
