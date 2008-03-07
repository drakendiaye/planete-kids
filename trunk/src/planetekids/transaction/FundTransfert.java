package planetekids.transaction;

import gicom.generated.bankServices.Account;
import gicom.generated.bankServices.Bank;
import gicom.generated.bankServices.BankHelper;
import gicom.generated.bankServices.Branch;
import gicom.generated.bankServices.Customer;
import gicom.generated.transaction.GlobalTransactionManager;
import gicom.generated.transaction.LocalTransactionManager;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import com.sun.corba.se.impl.orbutil.ORBConstants;

public class FundTransfert {

    private String banckOut;
    private String branchOut;
    private String clientOut;
    private String accountOut;

    private String banckIn;
    private String branchIn;
    private String clientIn;
    private String accountIn;

    private GlobalTransactionManager globalTM;

    private float amount;

    ORB orb;

    public FundTransfert(String bankIn, String branchIn, String clientIn, String accountIn, String bankOut, String branchOut,
	    String clientOut, String accountOut, float amount) {

	this.accountIn = accountIn;
	this.branchIn = branchIn;
	this.clientIn = clientIn;
	this.banckIn = bankIn;

	this.accountOut = accountOut;
	this.branchOut = branchOut;
	this.clientOut = clientOut;
	this.banckOut = bankOut;

	this.amount = amount;

    }

    public boolean Transfert() {

	int transactionId;
	boolean state = false;

	try {
	    Properties prop = new Properties();
	    prop.setProperty(ORBConstants.INITIAL_HOST_PROPERTY, "192.168.0.1");
	    prop.setProperty(ORBConstants.INITIAL_PORT_PROPERTY, "4711");
	    prop.setProperty(ORBConstants.SERVER_HOST_PROPERTY, "192.168.0.1");

	    orb = org.omg.CORBA.ORB.init(new String[0], prop);

	    NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

	    Bank bank_in = BankHelper.narrow(nc.resolve(nc.to_name(this.banckIn)));
	    Bank bank_out = BankHelper.narrow(nc.resolve(nc.to_name(this.banckOut)));

	    Branch branch_in = bank_in.findBranch(this.branchIn);
	    Branch branch_out = bank_out.findBranch(this.branchOut);
	    /*
	     * Customer client_in = branch_in.findCustomer(this.clientIn);
	     * Customer client_out = branch_in.findCustomer(this.clientOut);
	     * 
	     * Account account_in =
	     * client_in.findAccountByPrimaryKey(this.accountIn); Account
	     * account_out = client_in.findAccountByPrimaryKey(this.accountOut);
	     */
	    globalTM = branch_in.localTM().globalTM();

	    transactionId = globalTM.begin();

	    LocalTransactionManager localM_in = branch_in.localTM();
	    LocalTransactionManager localM_out = branch_out.localTM();

	    localM_in.credit(transactionId, this.amount, this.clientIn, this.accountIn);
	    localM_in.debit(transactionId, this.amount, this.clientOut, this.accountOut);

	    state = globalTM.commit(transactionId);

	    if (state)
		System.out.println("Commited, ALL RIGHT !!!");
	    else
		System.out.println("Rollbacked :( ... :( !!! ");

	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}

	return state;
    }

}
