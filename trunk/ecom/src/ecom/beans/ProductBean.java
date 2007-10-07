/**
* eCommerce Application Sample for J2EE Training 
* Implementation of ProductBean
* @author Fabienne Boyer - Didier Donsez
* may 2006
*/

package ecom.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
public class ProductBean implements java.io.Serializable{

    @Id
    private int product_id;
  
    public ProductBean() {
        
    }
    
    public ProductBean(int product_id) {
        this();
        this.setProductId(product_id);
    }

    public int getProductId() {
        return product_id;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

}
