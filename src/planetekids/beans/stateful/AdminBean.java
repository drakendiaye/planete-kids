/**
* eCommerce Application Sample for J2EE Training 
* Implementation of EcomAdminBean
* @author Fabienne Boyer - Didier Donsez
* may 2006
*/

package planetekids.beans.stateful;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import planetekids.beans.entity.MarkBean;


@Stateful
@Remote(AdminRemote.class)
public class AdminBean implements AdminRemote{

    @PersistenceContext
    private EntityManager entityManager;
    
    public void modifyMark(int id, String name, String description, String site, String image) throws Exception {
       MarkBean mark = entityManager.find(MarkBean.class, id);
       if(mark == null) {
           throw new Exception("No such Id");
       }
       if(name != null) mark.setName(name);
       if(description != null) mark.setDescription(description);
       if(site != null) mark.setSite(site);
       if(image != null) mark.setImage(image);
    }

    public void createMark(String name, String description, String site, String image) throws Exception {
        if(name == null || description == null || site == null || image == null) {
            throw new Exception("arguments should not be null");
        }
        MarkBean mark = new MarkBean(name, description, site, image);
        entityManager.persist(mark);
    }

    public void createAccount(int accountId, String accountOwner, double balance) {
       // to be completed
    }
    public void createProductStore(int productStoreId, String city, int productStoreAccountId, String productStoreName){
            // to be completed
    }
    public void createProduct(int productId, String productName, double productPrice, int productStoreId, String city){
            // to be completed
    }

// to be completed as needed
}
