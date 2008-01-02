/**
* eCommerce Application Sample for J2EE Training 
* Remote interface for the EcomAdmin bean
* EJB3.0 
* @author Fabienne Boyer - Didier Donsez - may 2006
*/

package planetekids.beans.stateful;

public interface AdminRemote {
        public void modifyMark(int id, String name, String description, String site, String image) throws Exception;
        public void createMark(String name, String description, String site, String image) throws Exception;
	void createAccount(int accountId, String accountOwner, double balance);
	void createProductStore(int productStoreId, String city, int productStoreAccountId, String productStoreName);
	void createProduct(int productId, String productName, double productPrice, int productStoreId, String city);
    //	 to be completed as needed
}
