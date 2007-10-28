<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:div cssClass="questionnaire">
    <h3><s:property value="getQuestionnaire().getTitle(getLocale())" /></h3>
    <p><s:property value="getQuestionnaire().getDescription(getLocale())" /></p>
    
    <s:if test="getQuestionnaire().getQuestions().size() > 0">
        <form id="questionnaire" name="questionnaire" action="questionnaire_submit" >
            <s:iterator value="getQuestionnaire().getQuestions()">
                <s:div cssClass="question">
                    <s:property value="getText(getLocale())" />
                    <s:if test="getPattern().toString() == 'SINGLE_CHOICE'">
                    </s:if>
                    <s:elseif test="getPattern().toString() == 'MULTI_CHOICE'">
                    </s:elseif>
                    <s:elseif test="getPattern().toString() == 'SORT'">
                    </s:elseif>
                    <s:elseif test="getPattern().toString() == 'VALUE'">
                        <s:if test="getAnswers().size() > 0">
                            <s:iterator value="getAnswers()">
                                <s:textfield name="answer_%{getId()}_value" />
                                <s:property value="%{getText(getLocale())}" />
                                <s:if test="getCommentable()">
                                    <s:textfield name="answer_%{getId()}_comment" />
                                </s:if>
                            </s:iterator>
                        </s:if>
                    </s:elseif>
                </s:div>
            </s:iterator>
            <s:submit value="submit" />
        </form>
    </s:if>
</s:div>