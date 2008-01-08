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

	if (id < 0) {
	    return ret;
	}

	if (parameters.get("name") != null) {
	    String value = ((String[]) parameters.get("name"))[0];
	    getAdmin().setLabelName(id, value);
	}

	if (parameters.get("description_en") != null) {
	    String value = ((String[]) parameters.get("description_en"))[0];
	    getAdmin().setLabelDescriptionEn(id, value);
	}

	if (parameters.get("description_fr") != null) {
	    String value = ((String[]) parameters.get("description_fr"))[0];
	    getAdmin().setLabelDescriptionFr(id, value);
	}

	if (parameters.get("site") != null) {
	    String value = ((String[]) parameters.get("site"))[0];
	    getAdmin().setLabelSite(id, value);
	}

	if (parameters.get("image_large") != null) {
	    String value = ((String[]) parameters.get("image_large"))[0];
	    getAdmin().setLabelImageLarge(id, value);
	}

	if (parameters.get("image_medium") != null) {
	    String value = ((String[]) parameters.get("image_medium"))[0];
	    getAdmin().setLabelImageMedium(id, value);
	}

	if (parameters.get("image_small") != null) {
	    String value = ((String[]) parameters.get("image_small"))[0];
	    getAdmin().setLabelImageSmall(id, value);
	}

	return ret;
    }

    public String labelDelete() throws Exception {
	String ret = execute();

	int id = getLabelId();

	if (id < 0) {
	    return ret;
	}

	getAdmin().deleteLabel(id);

	return ret;
    }

    public String categoryValid() throws Exception {
	String ret = execute();

	int id = getCategoryId();

	if (id < 0) {
	    return ret;
	}

	if (parameters.get("name_fr") != null) {
	    String value = ((String[]) parameters.get("name_fr"))[0];
	    getAdmin().setCategoryNameFr(id, value);
	}

	if (parameters.get("name_en") != null) {
	    String value = ((String[]) parameters.get("name_en"))[0];
	    getAdmin().setCategoryNameEn(id, value);
	}

	if (parameters.get("description_fr") != null) {
	    String value = ((String[]) parameters.get("description_fr"))[0];
	    getAdmin().setCategoryDescriptionFr(id, value);
	}

	if (parameters.get("description_en") != null) {
	    String value = ((String[]) parameters.get("description_en"))[0];
	    getAdmin().setCategoryDescriptionEn(id, value);
	}

	if (parameters.get("image_large") != null) {
	    String value = ((String[]) parameters.get("image_large"))[0];
	    getAdmin().setCategoryImageLarge(id, value);
	}

	if (parameters.get("image_medium") != null) {
	    String value = ((String[]) parameters.get("image_medium"))[0];
	    getAdmin().setCategoryImageMedium(id, value);
	}

	if (parameters.get("image_small") != null) {
	    String value = ((String[]) parameters.get("image_small"))[0];
	    getAdmin().setCategoryImageSmall(id, value);
	}

	return ret;
    }

    public String categoryDelete() throws Exception {
	String ret = execute();

	int id = getCategoryId();

	if (id < 0) {
	    return ret;
	}

	getAdmin().deleteCategory(id);

	return ret;
    }

    public String colorValid() throws Exception {
	String ret = execute();

	int id = getColorId();

	if (id < 0) {
	    return ret;
	}

	if (parameters.get("name_fr") != null) {
	    String value = ((String[]) parameters.get("name_fr"))[0];
	    getAdmin().setColorNameFr(id, value);
	}

	if (parameters.get("name_en") != null) {
	    String value = ((String[]) parameters.get("name_en"))[0];
	    getAdmin().setColorNameEn(id, value);
	}

	if (parameters.get("description_fr") != null) {
	    String value = ((String[]) parameters.get("description_fr"))[0];
	    getAdmin().setColorDescriptionFr(id, value);
	}

	if (parameters.get("description_en") != null) {
	    String value = ((String[]) parameters.get("description_en"))[0];
	    getAdmin().setColorDescriptionEn(id, value);
	}

	if (parameters.get("image_large") != null) {
	    String value = ((String[]) parameters.get("image_large"))[0];
	    getAdmin().setColorImageLarge(id, value);
	}

	if (parameters.get("image_medium") != null) {
	    String value = ((String[]) parameters.get("image_medium"))[0];
	    getAdmin().setColorImageMedium(id, value);
	}

	if (parameters.get("image_small") != null) {
	    String value = ((String[]) parameters.get("image_small"))[0];
	    getAdmin().setColorImageSmall(id, value);
	}

	return ret;
    }

    public String colorDelete() throws Exception {
	String ret = execute();

	int id = getColorId();

	if (id < 0) {
	    return ret;
	}

	getAdmin().deleteColor(id);

	return ret;
    }

    public String productValid() throws Exception {
	String ret = execute();

	int id = getProductId();

	if (id < 0) {
	    return ret;
	}

	if (parameters.get("name_fr") != null) {
	    String value = ((String[]) parameters.get("name_fr"))[0];
	    getAdmin().setProductNameFr(id, value);
	}

	if (parameters.get("name_en") != null) {
	    String value = ((String[]) parameters.get("name_en"))[0];
	    getAdmin().setProductNameEn(id, value);
	}

	if (parameters.get("description_fr") != null) {
	    String value = ((String[]) parameters.get("description_fr"))[0];
	    getAdmin().setProductDescriptionFr(id, value);
	}

	if (parameters.get("description_en") != null) {
	    String value = ((String[]) parameters.get("description_en"))[0];
	    getAdmin().setProductDescriptionEn(id, value);
	}

	if (parameters.get("category_id") != null) {
	    String value = ((String[]) parameters.get("category_id"))[0];
	    int intvalue = Integer.valueOf(value).intValue();
	    System.out.println("cat_id = " + intvalue);
	    getAdmin().setProductCategory(id, intvalue);
	}

	if (parameters.get("color_id") != null) {
	    String value = ((String[]) parameters.get("color_id"))[0];
	    getAdmin().setProductColor(id, Integer.valueOf(value).intValue());
	}

	if (parameters.get("label_id") != null) {
	    String value = ((String[]) parameters.get("label_id"))[0];
	    getAdmin().setProductLabel(id, Integer.valueOf(value).intValue());
	}

	if (parameters.get("price") != null) {
	    String value = ((String[]) parameters.get("price"))[0];
	    getAdmin().setProductPrice(id, Float.valueOf(value));
	}

	if (parameters.get("stock") != null) {
	    String value = ((String[]) parameters.get("stock"))[0];
	    getAdmin().setProductStock(id, Integer.valueOf(value).intValue());
	}

	if (parameters.get("image_large") != null) {
	    String value = ((String[]) parameters.get("image_large"))[0];
	    getAdmin().setProductImageLarge(id, value);
	}

	if (parameters.get("image_medium") != null) {
	    String value = ((String[]) parameters.get("image_medium"))[0];
	    getAdmin().setProductImageMedium(id, value);
	}

	if (parameters.get("image_small") != null) {
	    String value = ((String[]) parameters.get("image_small"))[0];
	    getAdmin().setProductImageSmall(id, value);
	}

	return ret;
    }

    public String productDelete() throws Exception {
	String ret = execute();

	int id = getProductId();

	if (id < 0) {
	    return ret;
	}

	getAdmin().deleteProduct(id);

	return ret;
    }

    public String customerValid() throws Exception {
	String ret = execute();

	String email = getCustomerId();

	if (email.compareTo("") == 0) {
	    return ret;
	}

	if (parameters.get("password") != null) {
	    String value = ((String[]) parameters.get("password"))[0];
	    getAdmin().setAccountPassword(email, value);
	}

	if (parameters.get("firstname") != null) {
	    String value = ((String[]) parameters.get("firstname"))[0];
	    getAdmin().setAccountFirstName(email, value);
	}

	if (parameters.get("lastname") != null) {
	    String value = ((String[]) parameters.get("lastname"))[0];
	    getAdmin().setAccountLastName(email, value);
	}

	if (parameters.get("addr1") != null) {
	    String value = ((String[]) parameters.get("addr1"))[0];
	    getAdmin().setAccountAddressLine1(email, value);
	}

	if (parameters.get("addr2") != null) {
	    String value = ((String[]) parameters.get("addr2"))[0];
	    getAdmin().setAccountAddressLine2(email, value);
	}

	if (parameters.get("addr3") != null) {
	    String value = ((String[]) parameters.get("addr3"))[0];
	    getAdmin().setAccountAddressLine3(email, value);
	}

	if (parameters.get("zipcode") != null) {
	    String value = ((String[]) parameters.get("zipcode"))[0];
	    int value_i = Integer.valueOf(value).intValue();
	    getAdmin().setAccountZipCode(email, value_i);
	}

	if (parameters.get("city") != null) {
	    String value = ((String[]) parameters.get("city"))[0];
	    getAdmin().setAccountCity(email, value);
	}

	if (parameters.get("phone") != null) {
	    String value = ((String[]) parameters.get("phone"))[0];
	    getAdmin().setAccountPhoneNumber(email, value);
	}
	if (parameters.get("fax") != null) {
	    String value = ((String[]) parameters.get("fax"))[0];
	    getAdmin().setAccountFaxNumber(email, value);
	}

	return ret;
    }

    public String customerDelete() throws Exception {
	String ret = execute();

	String id = getCustomerId();

	if (id.compareTo("") == 0) {
	    return ret;
	}

	getAdmin().deleteAccount(id);

	return ret;
    }

    public String ageValid() throws Exception {
	String ret = execute();

	int id = getAgeId();

	if (id < 0) {
	    return ret;
	}

	if (parameters.get("name_fr") != null) {
	    String value = ((String[]) parameters.get("name_fr"))[0];
	    getAdmin().setColorNameFr(id, value);
	}

	if (parameters.get("name_en") != null) {
	    String value = ((String[]) parameters.get("name_en"))[0];
	    getAdmin().setColorNameEn(id, value);
	}

	if (parameters.get("description_fr") != null) {
	    String value = ((String[]) parameters.get("description_fr"))[0];
	    getAdmin().setColorDescriptionFr(id, value);
	}

	if (parameters.get("description_en") != null) {
	    String value = ((String[]) parameters.get("description_en"))[0];
	    getAdmin().setColorDescriptionEn(id, value);
	}

	return ret;
    }

    public String ageDelete() throws Exception {
	String ret = execute();

	int id = getAgeId();

	if (id < 0) {
	    return ret;
	}

	getAdmin().deleteAge(id);

	return ret;
    }
}

