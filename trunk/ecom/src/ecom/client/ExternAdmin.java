/*-----------------------------------------------------------------------------*/
/* ExternAdmin.java                                                              */
/* external administrator for ecom application                                 */
/* Fabienne Boyer - Didier Donsez may 2006                                     */
/*-----------------------------------------------------------------------------*/

package ecom.client;

import ecom.beans.*;
import javax.transaction.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

// RMI administrator for the ecom application.

public class ExternAdmin {

   int accountId = 0;
   Context initialContext = null;
   UserTransaction utx = null;


  public static void main(String[] args) {
  try {

     System.out.println("Begining Client...");

     // if user don't use jclient/client container
     System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");

     parseArgs(args);

    // get JNDI initial context
	Context initialContext = null;
	try {
	    initialContext = new InitialContext();
	} catch (Exception e) {
	    System.err.println("Cannot get initial context for JNDI: " + e);
	    System.exit(2);
	}

        // We want to start transactions from client: get UserTransaction
        UserTransaction utx = null;
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
        } catch (NamingException e) {
            System.err.println("Cannot get EcomAdminBean : " + e);
            System.exit(2);
        }

    while (true) {
	     System.out.println("------------------------------------------------------");
	     System.out.println("Enter a command :");
	     System.out.println("1 = create an account ");
	     System.out.println("2 = create a productStore");
	     System.out.println("3 = create a product ");
	     System.out.println("0 = quit the application ");

         int choice = Tx.readInt();

        if (choice == 1) {
                System.out.println("-- not yet implemented\n");
	} else if (choice == 2) {
               System.out.println("-- not yet implemented\n");
	} else if (choice == 3) {
               System.out.println("-- not yet implemented\n");
	} else if (choice == 0) {
               System.out.println("-- not yet implemented\n");
	} else  {
            System.out.println("Bad choice");
	  	}
    }
  } catch (Exception e) {
	 System.err.println("Client get an exception " + e);
	 System.exit(2);
  }
  }

 

  /**
   * Parses an argument list for an acountId
   *
   * @param args[]            String array of arguments
   */

  static void parseArgs(String args[]) {
	  System.out.println("ParseArgs not yet implemented");
  }
}
