<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h2><s:text name="generalmenu"/></h2>
<s:url id="url" action="welcome"/>
<s:a theme="ajax" href="%{url}" targets="content"><s:text name="welcome"/></s:a>
<s:url id="url" action="questionnaire"/>
<s:a theme="ajax" href="%{url}" targets="content"><s:text name="questionnaire"/></s:a>