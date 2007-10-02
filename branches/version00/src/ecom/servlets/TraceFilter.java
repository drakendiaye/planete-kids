/**
 *  eCommerce Application Sample for J2EE Training
 *  definition
 *
 *@author    Fabienne Boyer - Didier Donsez - may 2006
 */

package ecom.servlets;

import java.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class TraceFilter implements javax.servlet.Filter {
  private FilterConfig filterConfig = null;
  private Vector traceContent;
  private String fieldSeparator;
  
  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;

    String _traceContent=filterConfig.getInitParameter("tracecontent");
    if(_traceContent==null) _traceContent="date,path";
    StringTokenizer st=new StringTokenizer(_traceContent,",");
    traceContent=new Vector();
    while (st.hasMoreTokens()) {
         traceContent.add(st.nextToken());
    }

    fieldSeparator=filterConfig.getInitParameter("fieldseparator");
    if(fieldSeparator==null) fieldSeparator=":";
  }

  public void destroy() {
    this.filterConfig = null;
  }

  public void doFilter(ServletRequest req,ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
      HttpServletRequest hreq = (HttpServletRequest)req;
      
      HttpSession session=hreq.getSession(false);
      StringBuffer sb=new StringBuffer();
      String field=null;
      for (Enumeration e = traceContent.elements() ; e.hasMoreElements() ;) {
         field=(String)e.nextElement();
	 if(field.equals("date")) {
		 Date date=new Date(System.currentTimeMillis());
		 sb.append(date.toString());
	 } else if(field.equals("path")) {
		 sb.append(hreq.getServletPath());
	 } else if(field.equals("session")) {
		 Date date=new Date(System.currentTimeMillis());
		 sb.append((session==null)?"No session":(session.getId()+""));
	 } else { // remotehost,remoteaddress,uri,method,locale, header(Content-Type) ... could be added !
		 sb.append(field);
	 }
	 if(e.hasMoreElements()) sb.append(fieldSeparator);
      }

      trace(sb.toString());
      chain.doFilter(req, res); // invoke the next filter or servlet in the chain
  }
  private void trace(String msg){ System.err.println(msg); }
}

