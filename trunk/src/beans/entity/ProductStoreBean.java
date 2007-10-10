/**
* eCommerce Application Sample for J2EE Training 
* Implementation of ProductStoreBean
* @author Fabienne Boyer - Didier Donsez
* may 2006
*/

package beans.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
public class ProductStoreBean implements java.io.Serializable{

    @Id
    private int product_store_id;
  
    public ProductStoreBean() {
        
    }
    
    public ProductStoreBean(int product_store_id) {
        this();
        this.setProductStoreId(product_store_id);
    }

    public int getProductStoreId() {
        return product_store_id;
    }

    public void setProductStoreId(int product_store_id) {
        this.product_store_id = product_store_id;
    }

}
