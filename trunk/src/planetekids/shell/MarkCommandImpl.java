/**
* eCommerce Application Sample for J2EE Training 
* @author Fabienne Boyer - july 2000
* @author Didier Donsez - november 2002  
*/

package planetekids.shell;

import java.io.PrintStream;
import java.util.StringTokenizer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import planetekids.beans.stateful.AdminBean;
import planetekids.beans.stateful.AdminRemote;

import shell.ShellCommand;
import shell.ShellContext;

public class MarkCommandImpl implements ShellCommand, PlaneteKidsShellConstantes {
	private ShellContext context = null;
        
	public MarkCommandImpl(ShellContext context) {
		this.context = context;
	}

	public String getName() {
		return "mark";
	}

	public String getUsage() {
		return "mark [-m id [-n name] [-d desciption] [-s site -i image]] | [-c -n name -d desciption -s site -i image] | [-l]";
	}

	public String getShortDescription() {
		return "modify, create or list trade marks.";
	}

	public void execute(String cmdline, PrintStream out, PrintStream err) {
		StringTokenizer st = new StringTokenizer(cmdline, " ");

		// Ignore the command name.
		st.nextToken();
                
                if(st.countTokens() == 0) {
                     err.println("Wrong arguments");
                     return;
                }
                
                String option = st.nextToken();
                
                if(option.equals("-m")) {
                    if(st.countTokens() == 0) {
                         err.println("Wrong arguments");
                         return;
                    }
                    String id = st.nextToken();

                    String name = null;
                    String description = null;
                    String site = null;
                    String image = null;
                    String arg = "";
                    while(st.countTokens() != 0) {
                        String token = st.nextToken();
                        if((token.equals("-n") && name.equals("")) || (token.equals("-d") && description.equals("")) || (token.equals("-s") && site.equals("")) || (token.equals("-i") && image.equals(""))) {
                            arg = token;
                        } else if(token.equals("-n") || token.equals("-d") || token.equals("-s") || token.equals("-i") || arg.equals("")) {
                            err.println("Wrong arguments");
                            return;
                        } else if(arg.equals("-n")) {
                            if(name == null) {
                                name = token;
                            } else {
                                name += " " + token;
                            }
                        } else if(arg.equals("-d")) {
                            if(description == null) {
                                description = token;
                            } else {
                                description += " " + token;
                            }
                        } else if(arg.equals("-s")) {
                            if(site == null) {
                                site = token;
                            } else {
                                site += " " + token;
                            }
                        } else if(arg.equals("-i")) {
                            if(image == null) {
                                image = token;
                            } else {
                                image += " " + token;
                            }
                        }
                    }
                    
                    try {
                        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.ow2.easybeans.component.smartclient.spi.SmartContextFactory");
                        Context initialContext = new InitialContext();
                        AdminRemote admin = (AdminRemote)initialContext.lookup(AdminBean.class.getName() + "_" + AdminRemote.class.getName() + "@Remote");
                        admin.modifyLabel(Integer.parseInt(id), name, description, site, image);
                    } catch (Exception ex) {
                        ex.printStackTrace(err);
                        return;
                    }
                } else if(option.equals("-c") ) {
                    String name = "";
                    String description = "";
                    String site = "";
                    String image = "";
                    String arg = "";
                    while(st.countTokens() != 0) {
                        String token = st.nextToken();
                        if((token.equals("-n") && name.equals("")) || (token.equals("-d") && description.equals("")) || (token.equals("-s") && site.equals("")) || (token.equals("-i") && image.equals(""))) {
                            arg = token;
                        } else if(token.equals("-n") || token.equals("-d") || token.equals("-s") || token.equals("-i") || arg.equals("")) {
                            err.println("Wrong arguments");
                            return;
                        } else if(arg.equals("-n")) {
                            if(name == null) {
                                name = token;
                            } else {
                                name += " " + token;
                            }
                        } else if(arg.equals("-d")) {
                            if(description == null) {
                                description = token;
                            } else {
                                description += " " + token;
                            }
                        } else if(arg.equals("-s")) {
                            if(site == null) {
                                site = token;
                            } else {
                                site += " " + token;
                            }
                        } else if(arg.equals("-i")) {
                            if(image == null) {
                                image = token;
                            } else {
                                image += " " + token;
                            }
                        }
                    }
                    
                    try {
                        Context initialContext = new InitialContext();
                        AdminRemote admin = (AdminRemote)initialContext.lookup(AdminBean.class.getName() + "_" + AdminRemote.class.getName() + "@Remote");
                        admin.createLabel(name, description, site, image);
                    } catch (Exception ex) {
                        ex.printStackTrace(err);
                        return;
                    }
                } else if(option.equals("-l") ) {
                    //TODO show all trade marks
                } else {
                    err.println("wrong arguments");
                    return;
                }
	}

}
