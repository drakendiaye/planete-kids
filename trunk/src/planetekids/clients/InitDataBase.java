/*-----------------------------------------------------------------------------*/
/* InitDataBase.java                                                           */
/* Program that initializes the ecom databaseby creating beans			 */
/* Fabienne Boyer - Didier Donsez may 2006                                     */
/*-----------------------------------------------------------------------------*/

package planetekids.clients;

import javax.transaction.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import planetekids.beans.stateful.AdminRemote;

public class InitDataBase {

  public static void main(String[] args) throws Exception {
  Context initialContext = null;
  UserTransaction utx = null;
  
  try {
    System.out.println("Initializing database...");

    // if user don't use jclient/client container
    System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.ow2.easybeans.component.smartclient.spi.SmartContextFactory");

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

        AdminRemote admin = null;
        try {
            admin = (AdminRemote) initialContext
                    .lookup("planetekids.beans.EcomAdminBean" + "_"
                            + AdminRemote.class.getName() + "@Remote");
		System.out.println("Creation of admEcom bean done");

        } catch (NamingException e) {
            System.err.println("Cannot get AdminBean : " + e);
            System.exit(2);
        }
		
	/*utx.begin();  
	admin.createAccount(101,"Antoine de St Exupery", 200.00);
	admin.createAccount(102,"Alexandre Dumas fils", 400.00);
	admin.createAccount(103,"Conan Doyle", 500.00);
	admin.createAccount(104,"Alfred de Musset", 100.00);
	admin.createAccount(105,"Phileas Lebegue", 350.00);
	admin.createAccount(106,"Alphonse de Lamartine", 650.00);
	admin.createAccount(107,"Galeries Farfouillette", 1670.00);
	admin.createAccount(108,"Aux bonheurs des Dames", 4532.00);
	admin.createAccount(109,"Bazar de la Maire", 2330.00);
	
	admin.createProductStore(1001, "Grenoble", 107, "Galeries Farfouillette");
	admin.createProductStore(2001, "Lyon", 107, "Galeries Farfouillette");
	admin.createProductStore(1002, "Grenoble", 109, "Bazar de la Maire");
	admin.createProductStore(2002, "Lyon", 109, "Bazar de la Maire");
	admin.createProductStore(1003, "Lyon", 108, "Aux bonheurs des Dames");

	admin.createProduct(10, "couteau", 10.00, 1001, "Grenoble");
	admin.createProduct(11, "serviette", 20.00, 1001, "Grenoble");
	admin.createProduct(12, "serviette", 18.00, 2001, "Lyon");
	admin.createProduct(13, "assiette", 25.00, 1002, "Grenoble");
	admin.createProduct(14, "assiette", 30.00, 1002, "Grenoble");
	admin.createProduct(15, "nappe", 130.00, 2002, "Lyon");
	utx.commit();*/
	
  } catch (Exception e) {
	 System.err.println("InitDataBase program get an exception " + e);
       utx.rollback(); 
	 System.exit(2);
  }
  }

 

}
