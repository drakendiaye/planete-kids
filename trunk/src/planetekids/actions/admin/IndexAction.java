package planetekids.actions.admin;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import planetekids.beans.entity.AccountBean;
import planetekids.beans.entity.AgeBean;
import planetekids.beans.entity.CategoryBean;
import planetekids.beans.entity.ColorBean;
import planetekids.beans.entity.CommandBean;
import planetekids.beans.entity.CommandBean.State;
import planetekids.beans.entity.CommandLineBean;
import planetekids.beans.entity.LabelBean;
import planetekids.beans.entity.ProductBean;
import planetekids.beans.stateful.AdminRemote;
import planetekids.beans.stateful.CustomerBean;
import planetekids.beans.stateful.CustomerRemote;

public class IndexAction extends ActionSupport implements SessionAware, ParameterAware {

    private Map session;
    private Map parameters;
    
    // On garde en cache le nombre de produits
    private int nbProds = -1;
    
    // Nombre de produits affichés par page par défaut
    private static int prodsbypagemax = 100;

    public void setSession(Map session) {
        this.session = session;
    }

    public AdminRemote getAdmin() {
        return (AdminRemote) session.get("admin");
    }

    @Override
    public String execute() throws Exception {
        try {
            int ok = 5;
            while (ok > 0) {
                try {
                    if (getAdmin().test()) break;
                } catch (Exception ex) {
                    session.put("admin", new InitialContext().lookup(CustomerBean.class.getName() + "_" + CustomerRemote.class.getName() + "@Remote"));
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

    public void setParameters(Map arg0) {
        this.parameters = arg0;
    }

    private String getStringParameter(String name) {
        String value = null;

        if (parameters.get(name) != null) {
            value = ((String[]) parameters.get(name))[0];
        }

        return value;
    }

    private int getIntParameter(String name) {
	int value = -1;

	if (parameters.get(name) != null) {
	    String value_s = ((String[]) parameters.get(name))[0];
	    if (value_s.compareTo("") != 0) {
		try {
		    value = Integer.valueOf(value_s).intValue();
		} catch (NumberFormatException e) {
		    value = -1;
		}
	    }
	}

	return value;
    }

    private float getFloatParameter(String name) {
        float value = -1;

        if (parameters.get(name) != null) {
            String value_s = ((String[]) parameters.get(name))[0];
            if (value_s.compareTo("") != 0) {
                try {
                    value = Float.valueOf(value_s).floatValue();
                } catch (NumberFormatException e) {
                    value = -1;
                }
            }
        }

        return value;
    }

    // Renvoie la liste des marques
    public List getLabels() throws Exception {
        return getAdmin().getLabels();
    }

    // Renvoie une marque correspondante à l'ID id
    public LabelBean getLabel(int id) throws Exception {
        return getAdmin().getLabel(id);
    }

    // Renvoie l'ID de la marque passé en paramètre
    public int getLabelId() throws Exception {
        int id = -1;

        if (parameters.get("label_id") != null) {
            String id_s = ((String[]) parameters.get("label_id"))[0];
            if (id_s.compareTo("") != 0) {
                id = Integer.valueOf(id_s).intValue();
            }
        }

        return id;
    }

    // Valide les modifications effectuées sur une marque (modif et création)
    public String labelValid() throws Exception {

        String ret = execute();

        int id = getLabelId();

        String name = getStringParameter("name");
        String description_fr = getStringParameter("description_fr");
        String description_en = getStringParameter("description_en");
        String site = getStringParameter("site");
        String image_large = getStringParameter("image_large");
        String image_medium = getStringParameter("image_medium");
        String image_small = getStringParameter("image_small");

        if (id < 0) {
            getAdmin().createLabel(name, description_fr, description_en, site, image_large, image_medium, image_small);
        } else {
            getAdmin().setLabelName(id, name);
            getAdmin().setLabelDescriptionFr(id, description_fr);
            getAdmin().setLabelDescriptionEn(id, description_en);
            getAdmin().setLabelSite(id, site);
            getAdmin().setLabelImageLarge(id, image_large);
            getAdmin().setLabelImageMedium(id, image_medium);
            getAdmin().setLabelImageSmall(id, image_small);
        }

        return ret;
    }

    // Supprime une marque
    public String labelDelete() throws Exception {
        String ret = execute();

        int id = getLabelId();

        if (id < 0) {
            return ret;
        }

        getAdmin().deleteLabel(id);

        return ret;
    }

    // même chose que pour les marques...
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
            if (id_s.compareTo("") != 0) {
                id = Integer.valueOf(id_s).intValue();
            }
        }

        return id;
    }

    public String colorValid() throws Exception {

        String ret = execute();

        int id = getColorId();

        String name_fr = getStringParameter("name_fr");
        String name_en = getStringParameter("name_en");
        String description_fr = getStringParameter("description_fr");
        String description_en = getStringParameter("description_en");
        String image_large = getStringParameter("image_large");
        String image_medium = getStringParameter("image_medium");
        String image_small = getStringParameter("image_small");

        if (id < 0) {
            getAdmin().createColor(name_fr, name_en, description_fr, description_en, image_large, image_medium, image_small);
        } else {
            getAdmin().setColorNameFr(id, name_fr);
            getAdmin().setColorNameEn(id, name_en);
            getAdmin().setColorDescriptionFr(id, description_fr);
            getAdmin().setColorDescriptionEn(id, description_en);
            getAdmin().setColorImageLarge(id, image_large);
            getAdmin().setColorImageMedium(id, image_medium);
            getAdmin().setColorImageSmall(id, image_small);
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
            if (id_s.compareTo("") != 0) {
                id = Integer.valueOf(id_s).intValue();
            }
        }

        return id;
    }

    public String categoryValid() throws Exception {

        String ret = execute();

        int id = getCategoryId();

        String name_fr = getStringParameter("name_fr");
        String name_en = getStringParameter("name_en");
        String description_fr = getStringParameter("description_fr");
        String description_en = getStringParameter("description_en");
        String image_large = getStringParameter("image_large");
        String image_medium = getStringParameter("image_medium");
        String image_small = getStringParameter("image_small");

        if (id < 0) {
            getAdmin().createCategory(name_fr, name_en, description_fr, description_en, image_large, image_medium, image_small);
        } else {
            getAdmin().setCategoryNameFr(id, name_fr);
            getAdmin().setCategoryNameEn(id, name_en);
            getAdmin().setCategoryDescriptionFr(id, description_fr);
            getAdmin().setCategoryDescriptionEn(id, description_en);
            getAdmin().setCategoryImageLarge(id, image_large);
            getAdmin().setCategoryImageMedium(id, image_medium);
            getAdmin().setCategoryImageSmall(id, image_small);
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

    public List getProducts() throws Exception {
        return getAdmin().getProducts();
    }

    // Récupère le nombre de produits et met la valeur en cache
    public int getNbProducts() throws Exception {
        if (nbProds < 0) {
            nbProds = getAdmin().getProducts().size();
        }

        return nbProds;
    }

    // Retourne une sous-liste de produits correspondante à la page courante
    public List getPagedProducts() throws Exception {
        List<ProductBean> products = getProducts();

        int prodsbypage = prodsbypagemax;
        int pagenum = getProductPagenum();
        int nbprods = getNbProducts();

        int from = (pagenum - 1) * prodsbypage;
        int to = from + prodsbypage;

        if (to > nbprods) {
            to = nbprods;
        }

        return products.subList(from, to);
    }

    // Retourne le nombre de pages de produits
    public int getNbProductPage() throws Exception {
	int prodsbypage = prodsbypagemax;
	int nbprods = getNbProducts();
	
	int n = (nbprods - 1)/prodsbypage + 1;

	return n;
    }

    public ProductBean getProduct(int id) throws Exception {
        return getAdmin().getProduct(id);
    }

    // Renvoie le numéro de page courante
    public int getProductPagenum() throws Exception {
        int pagenum = getIntParameter("pagenum");
        int nbprods = getNbProducts();
        int prodsbypage = prodsbypagemax;

        if (pagenum < 1) {
            pagenum = 1;
        }

        if (pagenum > ((nbprods - 1) / prodsbypage + 1)) {
            pagenum = ((nbprods - 1) / prodsbypage + 1);
        }

        return pagenum;
    }

    public int getProductId() throws Exception {
        int id = -1;

        if (parameters.get("product_id") != null) {
            String id_s = ((String[]) parameters.get("product_id"))[0];
            if (id_s.compareTo("") != 0) {
                id = Integer.valueOf(id_s).intValue();
            }
        }

        return id;
    }

    public String productValid() throws Exception {
        String ret = execute();

        int id = getProductId();

        String name_fr = getStringParameter("name_fr");
        String name_en = getStringParameter("name_en");
        String description_fr = getStringParameter("description_fr");
        String description_en = getStringParameter("description_en");
        int category_id = getIntParameter("category_id");
        int color_id = getIntParameter("color_id");
        int label_id = getIntParameter("label_id");
        int age_id = getIntParameter("age_id");
        float price = getFloatParameter("price");
        int stock = getIntParameter("stock");
        String image_large = getStringParameter("image_large");
        String image_medium = getStringParameter("image_medium");
        String image_small = getStringParameter("image_small");

        if (id < 0) {
            getAdmin().createProduct(name_fr, name_en, description_fr, description_en, category_id, color_id, label_id, age_id, price, stock, image_large, image_medium, image_small);
        } else {
            getAdmin().setProductNameFr(id, name_fr);
            getAdmin().setProductNameEn(id, name_en);
            getAdmin().setProductDescriptionFr(id, description_fr);
            getAdmin().setProductDescriptionEn(id, description_en);
            getAdmin().setProductCategory(id, category_id);
            getAdmin().setProductColor(id, color_id);
            getAdmin().setProductLabel(id, label_id);
            getAdmin().setProductAge(id, age_id);
            getAdmin().setProductPrice(id, price);
            getAdmin().setProductStock(id, stock);
            getAdmin().setProductImageLarge(id, image_large);
            getAdmin().setProductImageMedium(id, image_medium);
            getAdmin().setProductImageSmall(id, image_small);
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

    public String customerValid() throws Exception {
        String ret = execute();

        String email = getCustomerId();
        String password = getStringParameter("password");
        String firstname = getStringParameter("firstname");
        String lastname = getStringParameter("lastname");
        String addr1 = getStringParameter("addr1");
        String addr2 = getStringParameter("addr2");
        String addr3 = getStringParameter("addr3");
        int zipcode = getIntParameter("zipcode");
        String city = getStringParameter("city");
        String phone = getStringParameter("phone");
        String fax = getStringParameter("fax");

        if (email.compareTo("") == 0) {
            getAdmin().createAccount(email, password, firstname, lastname, addr1, addr2, addr3, zipcode, city, phone, fax);
        } else {
            getAdmin().setAccountPassword(email, password);
            getAdmin().setAccountFirstName(email, firstname);
            getAdmin().setAccountLastName(email, lastname);
            getAdmin().setAccountAddressLine1(email, addr1);
            getAdmin().setAccountAddressLine2(email, addr2);
            getAdmin().setAccountAddressLine3(email, addr3);
            getAdmin().setAccountZipCode(email, zipcode);
            getAdmin().setAccountCity(email, city);
            getAdmin().setAccountPhoneNumber(email, phone);
            getAdmin().setAccountFaxNumber(email, fax);
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
            if (id_s.compareTo("") != 0) {
                id = Integer.valueOf(id_s).intValue();
            }
        }

        return id;
    }

    public String ageValid() throws Exception {

        String ret = execute();

        int id = getAgeId();

        String name_fr = getStringParameter("name_fr");
        String name_en = getStringParameter("name_en");
        String description_fr = getStringParameter("description_fr");
        String description_en = getStringParameter("description_en");
        String image_large = getStringParameter("image_large");
        String image_medium = getStringParameter("image_medium");
        String image_small = getStringParameter("image_small");

        if (id < 0) {
            getAdmin().createAge(name_fr, name_en, description_fr, description_en, image_large, image_medium, image_small);
        } else {
            getAdmin().setAgeNameFr(id, name_fr);
            getAdmin().setAgeNameEn(id, name_en);
            getAdmin().setAgeDescriptionFr(id, description_fr);
            getAdmin().setAgeDescriptionEn(id, description_en);
            getAdmin().setAgeImageLarge(id, image_large);
            getAdmin().setAgeImageMedium(id, image_medium);
            getAdmin().setAgeImageSmall(id, image_small);
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

    public int getCommandId() throws Exception {
        int id = -1;

        if (parameters.get("command_id") != null) {
            String id_s = ((String[]) parameters.get("command_id"))[0];
            if (id_s.compareTo("") != 0) {
                id = Integer.valueOf(id_s).intValue();
            }
        }

        return id;
    }

    public List getCommands() throws Exception {
        return getAdmin().getCommands();
    }

    public CommandBean getCommand(int id) throws Exception {
        return getAdmin().getCommand(id);
    }

    public float getCommandTotal(int id) throws Exception {
        return getAdmin().getCommandTotal(id);
    }

    public List getCommandLines() throws Exception {
        return getAdmin().getCommandLines();
    }

    public State getCommandState(int id) throws Exception {
        return getAdmin().getCommandState(id);
    }

    public void setCommandState(int id, State state) throws Exception {
        getAdmin().setCommandState(id, state);
    }

    public List getCommandLinesByCommand(int id) throws Exception {
        return getAdmin().getCommandLinesByCommand(id);
    }

    public CommandLineBean getCommandLine(int id) throws Exception {
        return getAdmin().getCommandLine(id);
    }

    public String commandDelete() throws Exception {
        String ret = execute();

        int id = getCommandId();

        if (id < 0) {
            return ret;
        }

        getAdmin().deleteCommand(id);

        return ret;
    }

    public String commandValid() throws Exception {

        String ret = execute();

        int id = getCommandId();

        String state = getStringParameter("state");

        if (id < 0) {
            return ret;
        } else {
            getAdmin().setCommandState(id, State.valueOf(state));
        }

        return ret;
    }
}

