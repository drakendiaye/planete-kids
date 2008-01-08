package planetekids.actions.admin;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import planetekids.beans.entity.AccountBean;
import planetekids.beans.entity.AgeBean;
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
    
    public List getAges() throws Exception {
	return getAdmin().getAges();
    }
    
    public AgeBean getAge(int id) throws Exception {
	return getAdmin().getAge(id);
    }
    
    public int getAgeId() throws Exception {
	int id = -1;

	if (parameters.get("age_id") != null) {
	    String id_s = ((String[]) parameters.get("age_id"))[0];
	    id = Integer.valueOf(id_s).intValue();
	}

	return id;
    }
    
    public String labelValid() throws Exception {
	String ret = execute();

	int id = getLabelId();

	if (id < 0)
	    return ret;
	
	if (parameters.get("name") != null) {
	    String name = ((String[]) parameters.get("name"))[0];
	    getAdmin().setLabelName(id, name);
	}
	
	if (parameters.get("description_en") != null) {
	    String name = ((String[]) parameters.get("description_en"))[0];
	    getAdmin().setLabelDescriptionEn(id, name);
	}
	
	if (parameters.get("description_fr") != null) {
	    String name = ((String[]) parameters.get("description_fr"))[0];
	    getAdmin().setLabelDescriptionFr(id, name);
	}
	
	if (parameters.get("site") != null) {
	    String name = ((String[]) parameters.get("site"))[0];
	    getAdmin().setLabelSite(id, name);
	}
	
	if (parameters.get("image_large") != null) {
	    String name = ((String[]) parameters.get("image_large"))[0];
	    getAdmin().setLabelImageLarge(id, name);
	}
	
	if (parameters.get("image_medium") != null) {
	    String name = ((String[]) parameters.get("image_medium"))[0];
	    getAdmin().setLabelImageMedium(id, name);
	}
	
	if (parameters.get("image_small") != null) {
	    String name = ((String[]) parameters.get("image_small"))[0];
	    getAdmin().setLabelImageSmall(id, name);
	}
	
	return ret;
    }
    
    public String labelDelete() throws Exception {
	String ret = execute();

	int id = getLabelId();

	if (id < 0)
	    return ret;
	
	getAdmin().deleteLabel(id);
	
	return ret;
    }

    public String categoryValid() throws Exception {
	String ret = execute();

	int id = getCategoryId();

	if (id < 0)
	    return ret;
	
	if (parameters.get("name_fr") != null) {
	    String name = ((String[]) parameters.get("name_fr"))[0];
	    getAdmin().setCategoryNameFr(id, name);
	}
	
	if (parameters.get("name_en") != null) {
	    String name = ((String[]) parameters.get("name_en"))[0];
	    getAdmin().setCategoryNameEn(id, name);
	}
	
	if (parameters.get("description_fr") != null) {
	    String name = ((String[]) parameters.get("description_fr"))[0];
	    getAdmin().setCategoryDescriptionFr(id, name);
	}

	if (parameters.get("description_en") != null) {
	    String name = ((String[]) parameters.get("description_en"))[0];
	    getAdmin().setCategoryDescriptionEn(id, name);
	}
	
	
	if (parameters.get("image_large") != null) {
	    String name = ((String[]) parameters.get("image_large"))[0];
	    getAdmin().setCategoryImageLarge(id, name);
	}
	
	if (parameters.get("image_medium") != null) {
	    String name = ((String[]) parameters.get("image_medium"))[0];
	    getAdmin().setCategoryImageMedium(id, name);
	}
	
	if (parameters.get("image_small") != null) {
	    String name = ((String[]) parameters.get("image_small"))[0];
	    getAdmin().setCategoryImageSmall(id, name);
	}
	
	return ret;
    }
    
    public String categoryDelete() throws Exception {
	String ret = execute();

	int id = getCategoryId();

	if (id < 0)
	    return ret;
	
	getAdmin().deleteCategory(id);
	
	return ret;
    }
    
    public String colorDelete() throws Exception {
	String ret = execute();

	int id = getColorId();

	if (id < 0)
	    return ret;
	
	getAdmin().deleteColor(id);
	
	return ret;
    }
    
    public String productDelete() throws Exception {
	String ret = execute();

	int id = getProductId();

	if (id < 0)
	    return ret;
	
	getAdmin().deleteProduct(id);
	
	return ret;
    }
    
    public String customerDelete() throws Exception {
	String ret = execute();

	String id = getCustomerId();

	if (id.compareTo("") == 0)
	    return ret;
	
	getAdmin().deleteAccount(id);
	
	return ret;
    }
    
    public String ageDelete() throws Exception {
	String ret = execute();

	int id = getAgeId();

	if (id < 0)
	    return ret;
	
	getAdmin().deleteAge(id);
	
	return ret;
    }
}

