package planetekids.actions.product;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import planetekids.beans.entity.AgeBean;
import planetekids.beans.entity.CategoryBean;
import planetekids.beans.entity.ColorBean;
import planetekids.beans.entity.LabelBean;
import planetekids.beans.entity.ProductBean;
import planetekids.beans.stateful.CustomerBean;
import planetekids.beans.stateful.CustomerRemote;

public class IndexAction extends ActionSupport implements SessionAware, ParameterAware {

    static int PAGESIZE = 8;
    static int PAGENB = 6;
    private Map session;
    private Map parameters;
    private int page;
    private String filtersString;
    private String ageFilterString;
    private String categoryFilterString;
    private String colorFilterString;
    private String labelFilterString;
    private String andFilterString;
    private List<Integer> ageFilter;
    private List<Integer> categoryFilter;
    private List<Integer> labelFilter;
    private List<Integer> colorFilter;
    private boolean andFilter;
    private int nbpage;
    private List<ProductBean> products;

    public void setSession(Map session) {
        this.session = session;
    }

    public void setParameters(Map parameters) {
        this.parameters = parameters;
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

            buildFilters();
            
            return ActionSupport.SUCCESS;
        } catch (Exception ex) {
            return ActionSupport.ERROR;
        }
    }

    public String redirect() throws Exception {
        String result = execute();
        session.put("content_action", "index_content");
        session.put("content_namespace", "/product");
        session.put("content_parameters", filtersString);
        session.put("location_action", "index_location");
        session.put("location_namespace", "/product");
        session.put("content_parameters", filtersString);
        session.put("catalogue_action", "index_catalogue");
        session.put("catalogue_namespace", "/product");
        session.put("catalogue_parameters", filtersString);
        return result;
    }

    public String catalogue() throws Exception {
        String result = execute();
        System.out.println("Catalogue : " + parameters.hashCode() + " : " + getFiltersString() + " : " + getPage() + "\n\n\n\n\n\n\n\n\n\n");
        return result;
    }
    
    public String content() throws Exception {
        String result = execute();
        System.out.println("Content : " + parameters.hashCode() + " : " + getFiltersString() + " : " + getPage() + "\n\n\n\n\n\n\n\n\n\n");
        if (ageFilter.size() == 0 && labelFilter.size() == 0 && categoryFilter.size() == 0 && colorFilter.size() == 0) {
            products = getCustomer().getProducts();
        } else {
            products = getCustomer().getProductsByFilter(categoryFilter, colorFilter, labelFilter, ageFilter, andFilter);
        }


        page = 1;
        nbpage = products.size() / PAGESIZE + 1;
        if (parameters.get("page") != null) {
            try {
                page = new Integer(((String[]) parameters.get("page"))[0]).intValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (page < 1) {
            page = 1;
        }
        if (page > nbpage) {
            page = nbpage;
        }

        return result;
    }

    public List<ProductBean> getProducts() throws Exception {
        int lastElement = page * PAGESIZE;
        if (lastElement > products.size()) {
            lastElement = products.size();
        }
        return products.subList((page - 1) * PAGESIZE, lastElement);
    }

    public ProductBean getProduct(int id) throws Exception {
        return getCustomer().getProduct(id);
    }

    public List<AgeBean> getAges() throws Exception {
        return getCustomer().getAges();
    }

    public AgeBean getAge(int id) throws Exception {
        return getCustomer().getAge(id);
    }

    public List<CategoryBean> getCategories() throws Exception {
        return getCustomer().getCategories();
    }

    public CategoryBean getCategory(int id) throws Exception {
        return getCustomer().getCategory(id);
    }

    public List<ColorBean> getColors() throws Exception {
        return getCustomer().getColors();
    }

    public ColorBean getColor(int id) throws Exception {
        return getCustomer().getColor(id);
    }

    public List<LabelBean> getLabels() throws Exception {
        return getCustomer().getLabels();
    }

    public LabelBean getLabel(int id) throws Exception {
        return getCustomer().getLabel(id);
    }

    public boolean isAgeFiltered(int id) throws Exception {
        return getAgeFilter().contains(new Integer(getAge(id).getId()));
    }

    public boolean isCategoryFiltered(int id) throws Exception {
        return getCategoryFilter().contains(new Integer(getCategory(id).getId()));
    }

    public boolean isColorFiltered(int id) throws Exception {
        return getColorFilter().contains(new Integer(getColor(id).getId()));
    }

    public boolean isLabelFiltered(int id) throws Exception {
        return getLabelFilter().contains(new Integer(getLabel(id).getId()));
    }

    public List<Integer> getAgeFilter() throws Exception {
        return ageFilter;
    }

    public List<Integer> getCategoryFilter() throws Exception {
        return categoryFilter;
    }

    public List<Integer> getColorFilter() throws Exception {
        return colorFilter;
    }

    public List<Integer> getLabelFilter() throws Exception {
        return labelFilter;
    }

    public boolean getAndFilter() throws Exception {
        return andFilter;
    }

    public String getFiltersString() {
        return filtersString;
    }

    public String getAgeFilterString() {
        return ageFilterString;
    }

    public String getCategoryFilterString() {
        return categoryFilterString;
    }

    public String getColorFilterString() {
        return colorFilterString;
    }

    public String getLabelFilterString() {
        return labelFilterString;
    }

    public String getAndFilterString() {
        return andFilterString;
    }

    public int getPage() throws Exception {
        return page;
    }

    public int getNbPage() throws Exception {
        return nbpage;
    }

    public List<Integer> getPages() throws Exception {
        List<Integer> pages = new ArrayList<Integer>();
        int first = page - PAGENB / 2;
        int last = first + PAGENB - 1;
        if (first < 1) {
            last += 1 - first;
            first = 1;
        }
        if (last > nbpage) {
            first -= last - nbpage;
            last = nbpage;
        }
        if (first < 1) {
            first = 1;
        }

        for (int i = first; i <= last; i++) {
            pages.add(new Integer(i));
        }
        return pages;
    }

    private void buildFilters() {
        System.out.println("coucou1 : "+this);
        filtersString = "";
        ageFilterString = "";
        categoryFilterString = "";
        labelFilterString = "";
        colorFilterString = "";
        andFilterString = "true";
        ageFilter = new ArrayList<Integer>();
        categoryFilter = new ArrayList<Integer>();
        labelFilter = new ArrayList<Integer>();
        colorFilter = new ArrayList<Integer>();
        andFilter = true;

        if (parameters.get("ageFilter") != null) {
            try {
                ageFilterString = ((String[]) parameters.get("ageFilter"))[0];
                if (!ageFilterString.equals("")) {
                    if (!filtersString.equals("")) {
                        filtersString += "&";
                    }
                    filtersString += "ageFilter=" + ageFilterString;
                    String[] ages = ageFilterString.split(",");
                    for (int i = 0; i < ages.length; i++) {
                        ageFilter.add(new Integer(ages[i]));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (parameters.get("categoryFilter") != null) {
            try {
                categoryFilterString = ((String[]) parameters.get("categoryFilter"))[0];
                if (!categoryFilterString.equals("")) {
                    if (!filtersString.equals("")) {
                        filtersString += "&";
                    }
                    filtersString += "categoryFilter=" + categoryFilterString;
                    String[] categories = categoryFilterString.split(",");
                    for (int i = 0; i < categories.length; i++) {
                        categoryFilter.add(new Integer(categories[i]));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (parameters.get("labelFilter") != null) {
            try {
                labelFilterString = ((String[]) parameters.get("labelFilter"))[0];
                if (!labelFilterString.equals("")) {
                    if (!filtersString.equals("")) {
                        filtersString += "&";
                    }
                    filtersString += "labelFilter=" + labelFilterString;
                    String[] labels = labelFilterString.split(",");
                    for (int i = 0; i < labels.length; i++) {
                        labelFilter.add(new Integer(labels[i]));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (parameters.get("colorFilter") != null) {
            try {
                colorFilterString = ((String[]) parameters.get("colorFilter"))[0];
                if (!colorFilterString.equals("")) {
                    if (!filtersString.equals("")) {
                        filtersString += "&";
                    }
                    filtersString += "colorFilter=" + colorFilterString;
                    String[] colors = colorFilterString.split(",");
                    for (int i = 0; i < colors.length; i++) {
                        colorFilter.add(new Integer(colors[i]));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (parameters.get("andFilter") != null) {
            try {
                andFilterString = ((String[]) parameters.get("andFilter"))[0];
                if (!andFilterString.equals("")) {
                    if (!filtersString.equals("")) {
                        filtersString += "&";
                    }
                    filtersString += "andFilter=" + andFilterString;
                    andFilter = new Boolean(andFilterString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("coucou2 : "+this);
    }
}
