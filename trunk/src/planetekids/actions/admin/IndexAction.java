package planetekids.actions.admin;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import planetekids.beans.entity.LabelBean;
import planetekids.beans.stateful.AdminBean;
import planetekids.beans.stateful.AdminRemote;

public class IndexAction extends ActionSupport implements SessionAware, ParameterAware {

    private Map session;
    private Map parameters;

    public void setSession(Map session) {
	this.session = session;
    }

    public AdminRemote getAdmin() {
	return (AdminRemote) session.get("admin");
    }

    @Override
    public String execute() throws Exception {
	try {
	    if (getAdmin() == null) {
		session.put("admin", new InitialContext().lookup(AdminBean.class.getName() + "_" + AdminRemote.class.getName() + "@Remote"));
	    }
	    return ActionSupport.SUCCESS;
	} catch (NamingException ex) {
	    return ActionSupport.ERROR;
	}
    }

    public int getNumLabels() throws Exception {
	int numLabels = getAdmin().getLabels().size();

	return numLabels;
    }

    public List getLabels() throws Exception {
	return getAdmin().getLabels();
    }

    public void setParameters(Map arg0) {
	this.parameters = arg0;
    }

    /* public String labels_modify () throws Exception {
    String result = execute();
    if (parameters.get("label_id") != null) {
    int label_id = Integer.getInteger(((String[])parameters.get("label_id"))[0]).intValue();
    System.out.println("\n\n" + label_id + "\n\n");
    }
    return result;
    }
     */
    public LabelBean getLabel(int id) throws Exception {
	return getAdmin().getLabel(id);
    }

    public int getLabelId() throws Exception {
	int label_id = -1;

	if (parameters.get("label_id") != null) {
	    String label_id_s = ((String[]) parameters.get("label_id"))[0];
	    label_id = Integer.valueOf(label_id_s).intValue();
	}

	return label_id;
    }
}

