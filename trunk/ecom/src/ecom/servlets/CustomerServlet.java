/**
 * CustomerServlet
 * Servlet for an Ecom customer.
 * @author    Fabienne Boyer - Didier Donsez - may 2006
 */

package ecom.servlets;

import ecom.beans.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;

public class CustomerServlet extends HttpServlet {

  /**
     * Serializable class uid.
     */
    private static final long serialVersionUID = 6893863749912962928L;

    /**
     * Called by the server (via the service method) to allow a servlet to
     * handle a GET request.
     * @param request an HttpServletRequest object that contains the request
     * the client has made of the servlet
     * @param response an HttpServletResponse object that contains the
     * response the servlet sends to the client
     * @throws IOException if an input or output error is detected when the
     * servlet handles the GET request
     * @throws ServletException if the request for the GET could not be handled
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<link type=\"text/css\" href=\"../ow_jonas.css\" rel=\"stylesheet\" id=\"stylesheet\">");
        out.println("<title>");
        out.println("Customer Servlet</title>");
        out.println("</head>");
        out.println("<body style=\"background : white; color : black;\">");
        
        out.println("<h1>Trying to create an account</h1>");
        
        try {
            Context initialContext = new InitialContext();
            EcomCustomerRemote customer = (EcomCustomerRemote) initialContext.lookup("ecom.beans.EcomCustomerBean" + "_" + EcomCustomerRemote.class.getName() + "@Remote");
            customer.init();
            out.println("<p>Successful</p>");
        } catch (NamingException ex) {
            out.println("<p>Erreur : " + ex.getMessage() + "</p>");
        }
        
        out.println("</body>");
        out.println("</html>");
        
    }
}
