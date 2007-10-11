/**
* eCommerce Application Sample for J2EE Training 
* Remote interface for the EcomCustomer bean
* EJB3.0 
* @author Fabienne Boyer - Didier Donsez - may 2006
*/

package planetekids.beans.stateful;

import javax.ejb.Remote;

@Remote
public interface CustomerRemote {
  public void init();
}
