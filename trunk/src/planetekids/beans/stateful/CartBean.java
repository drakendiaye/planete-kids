/**
* eCommerce Application Sample for J2EE Training 
* Implementation of CartBean
* @author Fabienne Boyer - Didier Donsez
* may 2006
*/

package planetekids.beans.stateful;


import javax.ejb.Local;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;

@Stateful
@Local(CartLocal.class)
public class CartBean implements CartLocal{

  // to be completed
}
