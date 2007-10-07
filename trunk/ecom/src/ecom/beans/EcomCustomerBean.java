/**
* eCommerce Application Sample for J2EE Training 
* Implementation of EcomCustomerBean
* @author Fabienne Boyer - Didier Donsez
* may 2006
*/

package ecom.beans;

import javax.ejb.Remote;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
@Remote(EcomCustomerRemote.class)
public class EcomCustomerBean implements EcomCustomerRemote{

    @PersistenceContext
    private EntityManager entityManager;
    
    public void init() {
        AccountBean account = new AccountBean(2);
        entityManager.persist(account);
    }
}
