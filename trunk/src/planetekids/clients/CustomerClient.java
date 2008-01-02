/*
 *  ------------------------------------------------------------------------------
 */
/*
 *  ExternCustomer.java
 */
/*
 *  external customer for ecom application
 */
/*
 *  Fabienne Boyer - Didier Donsez may 2006
 */
/*
 *  ------------------------------------------------------------------------------
 */

package planetekids.clients;

import javax.transaction.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import planetekids.beans.stateful.CustomerRemote;


/**
 *  Simple customer for the ecom application.
 *
 *@author     Fabienne Boyer
 *@created    10 septembre 2006
 */
public class CustomerClient {

   int accountId = 0;
   Context initialContext = null;
   UserTransaction utx = null;


  public static void main(String[] args) {
  try {
    System.out.println("Begining Client...");
    parseArgs(args);

    // if user don't use jclient/client container
    System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.ow2.easybeans.component.smartclient.spi.SmartContextFactory");


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

        CustomerRemote ecomCustomerBean = null;
        try {
            ecomCustomerBean = (CustomerRemote) initialContext
                    .lookup("planetekids.beans.EcomCustomerBean" + "_"
                            + CustomerRemote.class.getName() + "@Remote");
        } catch (NamingException e) {
            System.err.println("Cannot get EcomCustomerBean : " + e);
            System.exit(2);
        }
	
        while (true) {
				try {
					System.out.println("------------------------------------------------------");
					System.out.println("Enter a command :");
					System.out.println("1 = list stores");
					System.out.println("2 = list products");
					System.out.println("3 = add a product in your cart");
					System.out.println("4 = list the products in your cart ");
					System.out.println("5 = buy the products in your cart ");
					System.out.println("6 = get the balance of an account ");
					System.out.println("0 = quit the application ");

					int choice = Tx.readInt();

					switch (choice) {
									case 1:
										listProductStores(ecomCustomerBean );
										break;
									case 2:
										System.out.print("Enter a productStore identifier :");
										int productStoreId = Tx.readInt();
										listProducts(ecomCustomerBean , productStoreId);
										break;
									case 3:
										System.out.print("Enter a product identifier :");
										int productId = Tx.readInt();
										addProductToCart(ecomCustomerBean , productId);
										break;
									case 4:
										listProductsInCart(ecomCustomerBean );
										break;
									case 5:
										System.out.print("Give your account number ");
										int accountId = Tx.readInt();
										buyCart(ecomCustomerBean , accountId);
										break;
									case 6:
										System.out.print("Give your account number ");
										accountId = Tx.readInt();
										getAccountBalance(ecomCustomerBean , accountId);
										break;
									case 0:
										System.exit(0);
										break;
									default:
								    	System.out.println("unknown operation");
					}
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		} catch (Exception e) {
			System.err.println("Client get an exception " + e);
			System.exit(2);
		}
	}

	/**
	 *  list the stores
	 *
	 *@param  customer  Description of the Parameter
	 */

	private static void listProductStores(CustomerRemote ecomCustomerBean) {
		try {
			// to be implemented
		} catch (Exception e) {
			System.err.println("Exception getting productStore list: " + e);
		}
	}


	/**
	 *  list the product of a store
	 *
	 *@param  ecomCustomerBean      Description of the Parameter
	 *@param  productStoreId  Description of the Parameter
	 */
	private static void listProducts(CustomerRemote ecomCustomerBean, int productStoreId) {
		try {
			// to be implemented
		} catch (Exception e) {
			System.err.println("Exception getting product list: " + e);
		}
	}


	/**
	 *  list the products added in the cart
	 *
	 *@param  ecomCustomerBean  the bean
	 */
	private static void listProductsInCart(CustomerRemote ecomCustomerBean) {
		try {
			// to be implemented
		} catch (Exception e) {
			System.err.println("Exception getting product list: " + e);
		}
	}


	/**
	 *  add a product in the cart
	 *
	 *@param  ecomCustomerBean  the bean
	 *@param  productId   the id of the product to add
	 */
	private static void addProductToCart(CustomerRemote ecomCustomerBean, int productId) {
		try {
			// to be implemented
		} catch (Exception e) {
			System.err.println("Exception adding product : "  + productId + " " + e);
		}

	}


	/**
	 *  buy the cart
	 *
	 *@param  ecomCustomerBean  Description of the Parameter
	 *@param  accountId   Description of the Parameter
	 */
	private static void buyCart(CustomerRemote ecomCustomerBean, int accountId) {
		try {
			// to be implemented
		} catch (Exception e) {
			System.err.println("Exception buying product : "  + e);
		}

	}


	/**
	 *  get the balance of a account
	 *
	 *@param  ecomCustomerBean  Description of the Parameter
	 *@param  accountId   Description of the Parameter
	 */
	private static void getAccountBalance(CustomerRemote ecomCustomerBean, int accountId){
	// to be implemented
	}


	/**
	 *  Parses an argument list for an acountId
	 *
	 *@param  args  Description of the Parameter
	 */

	static void parseArgs(String args[]) {
		if ((args == null) || (args.length == 0)) {
			return;
		}
//		for (int i = 0; i < args.length; i++) {
//			if (args[i].equals("-id")) {
//				accountId = new Integer(args[++i]);
//			}
		}


	/**
	 *  Description of the Method
	 *
	 *@param  sb    Description of the Parameter
	 *@param  objs  Description of the Parameter
	 */
	static void formatObjectArray(StringBuffer sb, Object[] objs) {
		if ((objs == null) || (objs.length == 0)) {
			return;
		}
		for (int i = 0; i < objs.length; i++) {
			if (i != 0) {
				sb.append(':');
			}
			sb.append(objs[i].toString());
		}
		sb.append('\n');
	}


	/**
	 *  Description of the Method
	 *
	 *@param  sb      Description of the Parameter
	 *@param  objs    Description of the Parameter
	 *@param  format  Description of the Parameter
	 */
	static void formatObjectArray(StringBuffer sb, Object[] objs, String format) {
		if ((objs == null) || (objs.length == 0)) {
			return;
		}
		sb.append(java.text.MessageFormat.format(format, objs));
	}
}

