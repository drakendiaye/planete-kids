package planetekids.beans.stateful;

import gicom.generated.bankServices.Bank;
import gicom.generated.bankServices.BankHelper;
import gicom.generated.bankServices.BankOperations;
import gicom.generated.transaction.GlobalTransactionManager;
import gicom.generated.transaction.GlobalTransactionManagerHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.BindingType;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;

import com.sun.corba.se.impl.orbutil.ORBConstants;

import planetekids.beans.entity.AccountBean;
import planetekids.beans.entity.CommandBean;
import planetekids.beans.entity.CommandLineBean;
import planetekids.beans.entity.LocaleBean;
import planetekids.beans.entity.ProductBean;
import planetekids.transaction.FundTransfert;

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

	FundTransfert fundTManager = new FundTransfert("LaPoste","Lyon","laposte_lyon:123","321","CreditMutuel","Le Mans","creditmutuel_lemans:012","210",3600);
	
	fundTManager.Transfert();
	
	// TODO : fundTransfert !!!!!!!!!!!
	return 0;
	// throw new UnsupportedOperationException("Not supported yet.");
    }

    public Hashtable getHashtable() throws Exception {
	return cart;
    }

    public float getCartPrice() throws Exception {
	float price = 0;
	Enumeration<Integer> enumeration = cart.keys();
	while (enumeration.hasMoreElements()) {
	    Integer key = enumeration.nextElement();
	    ProductBean product = entityManager.find(ProductBean.class, key.intValue());
	    if (product == null)
		cart.remove(key);
	    else
		price += product.getPrice() * cart.get(key);
	}
	return price;
    }

    public List<ProductBean> getCartProducts() throws Exception {
	List<ProductBean> products = new ArrayList<ProductBean>();
	Enumeration<Integer> enumeration = cart.keys();
	while (enumeration.hasMoreElements()) {
	    Integer key = enumeration.nextElement();
	    ProductBean product = entityManager.find(ProductBean.class, key.intValue());
	    if (product == null)
		cart.remove(key);
	    else
		products.add(product);
	}
	return (products);
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
