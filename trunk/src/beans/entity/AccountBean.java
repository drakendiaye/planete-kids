/**
* eCommerce Application Sample for J2EE Training 
* Implementation of AccountBean
* @author Fabienne Boyer - Didier Donsez
* may 2006
*/

package beans.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
public class AccountBean implements java.io.Serializable{

    @Id
    private int account_id;
  
    public AccountBean() {
        
    }
    
    public AccountBean(int account_id) {
        this();
        this.setAccountId(account_id);
    }

    public int getAccountId() {
        return account_id;
    }

    public void setAccountId(int account_id) {
        this.account_id = account_id;
    }

}
