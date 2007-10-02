/**
* eCommerce Application Sample for J2EE Training 
* Implementation of EcomAdminBean
* @author Fabienne Boyer - Didier Donsez
* may 2006
*/

package ecom.beans;

import javax.ejb.Remote;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;


@Stateful
@Remote(EcomAdminRemote.class)
public class EcomAdminBean implements EcomAdminRemote{

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
