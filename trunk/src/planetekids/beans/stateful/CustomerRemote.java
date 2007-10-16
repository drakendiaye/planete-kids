/**
* eCommerce Application Sample for J2EE Training 
* Remote interface for the EcomCustomer bean
* EJB3.0 
* @author Fabienne Boyer - Didier Donsez - may 2006
*/

package planetekids.beans.stateful;

import java.util.List;
import javax.ejb.Remote;
import planetekids.beans.entity.QuestionnaireBean;

@Remote
public interface CustomerRemote {
  public void init();
  public List<QuestionnaireBean> getQuestionnaires();
}
