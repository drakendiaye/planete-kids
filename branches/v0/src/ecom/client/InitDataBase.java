/*-----------------------------------------------------------------------------*/
/* InitDataBase.java                                                           */
/* Program that initializes the ecom databaseby creating beans			 */
/* Fabienne Boyer - Didier Donsez may 2006                                     */
/*-----------------------------------------------------------------------------*/

package ecom.client;

import ecom.beans.*;
import javax.transaction.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class InitDataBase {

  public static void main(String[] args) throws Exception {
  Context initialContext = null;
  UserTransaction utx = null;
  
  try {
    System.out.println("Initializing database...");

    // if user don't use jclient/client container
    System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");

    // get JNDI initial context
	try {
	    initialContext = new InitialContext();
	} catch (Exception e) {
	    System.err.println("Cannot get initial context for JNDI: " + e);
	    System.exit(2);
	}

        // We want to start transactions from client: get UserTransaction
        try {
            utx = (UserTransaction) initialContext.lookup("javax.transaction.UserTransaction");
        } catch (NamingException e) {
            System.err.println("Cannot lookup UserTransaction: " + e);
            System.exit(2);
        }

        EcomAdminRemote ecomAdminBean = null;
        try {
            ecomAdminBean = (EcomAdminRemote) initialContext
                    .lookup("ecom.beans.EcomAdminBean" + "_"
                            + EcomAdminRemote.class.getName() + "@Remote");
		System.out.println("Creation of admEcom bean done");

        } catch (NamingException e) {
            System.err.println("Cannot get EcomAdminBean : " + e);
            System.exit(2);
        }
		
	utx.begin();  
	ecomAdminBean.createAccount(101,"Antoine de St Exupery", 200.00);
	ecomAdminBean.createAccount(102,"Alexandre Dumas fils", 400.00);
	ecomAdminBean.createAccount(103,"Conan Doyle", 500.00);
	ecomAdminBean.createAccount(104,"Alfred de Musset", 100.00);
	ecomAdminBean.createAccount(105,"Phileas Lebegue", 350.00);
	ecomAdminBean.createAccount(106,"Alphonse de Lamartine", 650.00);
	ecomAdminBean.createAccount(107,"Galeries Farfouillette", 1670.00);
	ecomAdminBean.createAccount(108,"Aux bonheurs des Dames", 4532.00);
	ecomAdminBean.createAccount(109,"Bazar de la Maire", 2330.00);
	
	ecomAdminBean.createProductStore(1001, "Grenoble", 107, "Galeries Farfouillette");
	ecomAdminBean.createProductStore(2001, "Lyon", 107, "Galeries Farfouillette");
	ecomAdminBean.createProductStore(1002, "Grenoble", 109, "Bazar de la Maire");
	ecomAdminBean.createProductStore(2002, "Lyon", 109, "Bazar de la Maire");
	ecomAdminBean.createProductStore(1003, "Lyon", 108, "Aux bonheurs des Dames");

	ecomAdminBean.createProduct(10, "couteau", 10.00, 1001, "Grenoble");
	ecomAdminBean.createProduct(11, "serviette", 20.00, 1001, "Grenoble");
	ecomAdminBean.createProduct(12, "serviette", 18.00, 2001, "Lyon");
	ecomAdminBean.createProduct(13, "assiette", 25.00, 1002, "Grenoble");
	ecomAdminBean.createProduct(14, "assiette", 30.00, 1002, "Grenoble");
	ecomAdminBean.createProduct(15, "nappe", 130.00, 2002, "Lyon");
	utx.commit(); 
	
  } catch (Exception e) {
	 System.err.println("InitDataBase program get an exception " + e);
       utx.rollback(); 
	 System.exit(2);
  }
  }

 

}
