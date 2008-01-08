package planetekids.actions.admin;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import planetekids.beans.entity.AccountBean;
import planetekids.beans.entity.CategoryBean;
import planetekids.beans.entity.ColorBean;
import planetekids.beans.entity.LabelBean;
import planetekids.beans.entity.ProductBean;
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

    public void setParameters(Map arg0) {
	this.parameters = arg0;
    }

    public List getLabels() throws Exception {
	return getAdmin().getLabels();
    }

    public LabelBean getLabel(int id) throws Exception {
	return getAdmin().getLabel(id);
    }

    public int getLabelId() throws Exception {
	int id = -1;

	if (parameters.get("label_id") != null) {
	    String id_s = ((String[]) parameters.get("label_id"))[0];
	    id = Integer.valueOf(id_s).intValue();
	}

	return id;
    }

    public List getColors() throws Exception {
	return getAdmin().getColors();
    }

    public ColorBean getColor(int id) throws Exception {
	return getAdmin().getColor(id);
    }

    public int getColorId() throws Exception {
	int id = -1;

	if (parameters.get("color_id") != null) {
	    String id_s = ((String[]) parameters.get("color_id"))[0];
	    id = Integer.valueOf(id_s).intValue();
	}

	return id;
    }

    public List getCategories() throws Exception {
	return getAdmin().getCategories();
    }

    public CategoryBean getCategory(int id) throws Exception {
	return getAdmin().getCategory(id);
    }

    public int getCategoryId() throws Exception {
	int id = -1;

	if (parameters.get("category_id") != null) {
	    String id_s = ((String[]) parameters.get("category_id"))[0];
	    id = Integer.valueOf(id_s).intValue();
	}

	return id;
    }

    public List getProducts() throws Exception {
	return getAdmin().getProducts();
    }

    public ProductBean getProduct(int id) throws Exception {
	return getAdmin().getProduct(id);
    }

    public int getProductId() throws Exception {
	int id = -1;

	if (parameters.get("product_id") != null) {
	    String id_s = ((String[]) parameters.get("product_id"))[0];
	    id = Integer.valueOf(id_s).intValue();
	}

	return id;
    }

    public List getCustomers() throws Exception {
	return getAdmin().getAccounts();
    }

    public AccountBean getCustomer(String email) throws Exception {
	return getAdmin().getAccount(email);
    }

    public String getCustomerId() throws Exception {
	String id = "";

	if (parameters.get("customer_id") != null) {
	    id = ((String[]) parameters.get("customer_id"))[0];
	}

	return id;
    }
}

