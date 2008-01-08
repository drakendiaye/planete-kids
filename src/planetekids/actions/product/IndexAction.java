package planetekids.actions.product;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.axis.wsdl.symbolTable.Parameters;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import planetekids.beans.entity.ProductBean;
import planetekids.beans.stateful.CustomerBean;
import planetekids.beans.stateful.CustomerRemote;

public class IndexAction extends ActionSupport implements SessionAware, ParameterAware {
    
    static int PAGESIZE = 10;
    static int PAGENB = 6;
    private Map session;
    private Map parameters;
    
    private int page;
    private int nbpage;
    private List<ProductBean> products;
    
    public void setSession(Map session) {
        this.session = session;
    }
    
    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }
    
    public CustomerRemote getCustomer() {
        return (CustomerRemote)session.get("customer");
    }
    
    @Override
    public String execute() throws Exception {
        try {
            if(getCustomer() == null) session.put("customer", new InitialContext().lookup(CustomerBean.class.getName() + "_" + CustomerRemote.class.getName() + "@Remote"));
            products = getCustomer().getProducts();
            page = 1;
            nbpage = products.size() / PAGESIZE + 1;
            if (parameters.get("page") != null) {
                try {
                    page = new Integer(((String[])parameters.get("page"))[0]).intValue();
                    System.out.println("\n\n\n\n\n\n\nPage fin : " + page + "\n\n\n\n\n\n\n\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (page < 1) page = 1;
            if (page > nbpage) page = nbpage;
            
            
            return ActionSupport.SUCCESS;
        } catch (NamingException ex) {
            return ActionSupport.ERROR;
        }
    }
    
    public String redirect() throws Exception {
        session.put("content_action", "index_content");
        session.put("content_namespace", "/color");
        session.put("location_action", "index_location");
        session.put("location_namespace", "/color");
        return execute();
    }
    
    public List<ProductBean> getProducts() throws Exception {
        int lastElement = page * PAGESIZE;
        if (lastElement > products.size())
            lastElement = products.size();
        return products.subList((page - 1) * PAGESIZE, lastElement);
    }
    
    public ProductBean getProduct(int id) throws Exception {
        return getCustomer().getProduct(id);
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
        if(first < 1) {
            last += 1 - first;
            first = 1;
        }
        if(last > nbpage) {
            first -= last - nbpage;
            last = nbpage;
        }
        if(first < 1) first = 1;
        
        for(int i=first;i<=last;i++) {
            pages.add(new Integer(i));
        }
        return pages;
    }
}
