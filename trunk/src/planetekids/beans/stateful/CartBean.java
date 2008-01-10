package planetekids.beans.stateful;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import planetekids.beans.entity.AccountBean;
import planetekids.beans.entity.CommandBean;
import planetekids.beans.entity.CommandLineBean;
import planetekids.beans.entity.LocaleBean;
import planetekids.beans.entity.ProductBean;

@Stateful
public class CartBean implements CartRemote {

    @PersistenceContext
    private EntityManager entityManager;
    private Hashtable<Integer, Integer> cart = new Hashtable<Integer, Integer>();

    public void init() {

    }

    public void flushCart() throws Exception {
        cart.clear();
    }

    public int validateCart() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Hashtable getHashtable() throws Exception {
        return cart;
    }
    
    public float getCartPrice() throws Exception {
        float price = 0;
        Enumeration<Integer> enumeration = cart.keys();
        while(enumeration.hasMoreElements()) {
            Integer key = enumeration.nextElement();
            ProductBean product = entityManager.find(ProductBean.class, key.intValue());
            if(product == null) cart.remove(key);
            else price += product.getPrice() * cart.get(key);
        }
        return price;
    }

    public List<ProductBean> getCartProducts()  throws Exception {
        List<ProductBean> products = new ArrayList<ProductBean>();
        Enumeration<Integer> enumeration = cart.keys();
        while(enumeration.hasMoreElements()) {
            Integer key = enumeration.nextElement();
            ProductBean product = entityManager.find(ProductBean.class, key.intValue());
            if(product == null) cart.remove(key);
            else products.add(product);
        }
        return(products);
    }
    
    public void setCartProductNumber(int product_id, int product_number) throws Exception {
        if (product_number > 0) {
            cart.put(new Integer(product_id), new Integer(product_number));
        } else {
            cart.remove(new Integer(product_id));
        }
    }

    public int getCartProductNumber(int product_id) throws Exception {
        return cart.get(new Integer(product_id)).intValue();
    }

    @PostConstruct
    private void postConstruct() {
    }

    @PreDestroy
    private void preDestroy() {
    }

    @PostActivate
    private void postActivate() {
    }

    @PrePassivate
    private void prePassivate() {
    }

    @Remove
    private void remove() {
    }
}
