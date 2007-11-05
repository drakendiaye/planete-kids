<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:div cssClass="questionnaire">
    <h3><s:property value="getQuestionnaire().getTitle(getLocale())" /></h3>
    <p><s:property value="getQuestionnaire().getDescription(getLocale())" /></p>
    
    <s:if test="getQuestionnaire().getQuestions().size() > 0">
        <form id="questionnaire" method="post" action="<s:url namespace="/questionnaire" action="questionnaire_submit" includeParams="none"/>">
            <s:iterator value="getQuestionnaire().getQuestions()">
                <s:div cssClass="question">
                    <s:property value="getText(getLocale())" />
                    <s:div cssClass="answer">
                        <s:if test="getPattern().toString() == 'SINGLE_CHOICE'">
                            <s:if test="getAnswers().size() > 0">
                                <script type="text/javascript">
                                    function radioClick_<s:property value="getId()"/>(id) {
                                        <s:iterator value="getAnswers()">
                                            dojo.byId("answer_<s:property value="getId()"/>").value = "false";
                                            dojo.byId("answer_<s:property value="getId()"/>_img").src = "images/radio_deselected.png";
                                        </s:iterator>
                                        dojo.byId(id).value = "true";
                                        dojo.byId(id+"_img").src = "images/radio_selected.png";
                                    }
                                </script>
                            </s:if>
                            <s:if test="getAnswers().size() > 0">
                                <s:iterator id="answer" value="getAnswers()">
                                    <input id="answer_<s:property value="getId()"/>" name="answer_<s:property value="getId()"/>" type="hidden" value="false"/>
                                    <img id="answer_<s:property value="getId()"/>_img" src="images/radio_deselected.png" width="20px" onclick="radioClick_<s:property value="getQuestion().getId()"/>('answer_<s:property value="getId()"/>')"/>
                                    <s:property value="%{getText(getLocale())}"/>
                                    <s:if test="getCommentable()">
                                        <s:textfield name="answer_%{getId()}_comment" />
                                    </s:if>
                                    <br/>
                                </s:iterator>
                            </s:if>
                        </s:if>
                        <s:elseif test="getPattern().toString() == 'MULTI_CHOICE'">
                            <s:if test="getAnswers().size() > 0">
                                <s:iterator id="answer" value="getAnswers()">
                                    <script type="text/javascript">
                                        function checkClick_<s:property value="getId()"/>() {
                                            if(dojo.byId("answer_<s:property value="getId()"/>").value == "false") {
                                                dojo.byId("answer_<s:property value="getId()"/>").value = "true";
                                                dojo.byId("answer_<s:property value="getId()"/>_img").src = "images/check_selected.png";
                                            }
                                            else {
                                                dojo.byId("answer_<s:property value="getId()"/>").value = "false";
                                                dojo.byId("answer_<s:property value="getId()"/>_img").src = "images/check_deselected.png";
                                            }
                                        }
                                    </script>
                                    <input id="answer_<s:property value="getId()"/>" name="answer_<s:property value="getId()"/>" type="hidden" value="false"/>
                                    <img id="answer_<s:property value="getId()"/>_img" src="images/check_deselected.png" width="20px" onclick="checkClick_<s:property value="getId()"/>()"/>
                                    <s:property value="%{getText(getLocale())}"/>
                                    <s:if test="getCommentable()">
                                        <s:textfield name="answer_%{getId()}_comment" />
                                    </s:if>
                                    <br/>
                                </s:iterator>
                            </s:if>
                        </s:elseif>
                        <s:elseif test="getPattern().toString() == 'SORT'">
                        </s:elseif>
                        <s:elseif test="getPattern().toString() == 'VALUE'">
                            <s:if test="getAnswers().size() > 0">
                                <s:iterator value="getAnswers()">
                                    <input id="answer_<s:property value="getId()"/>_value" name="answer_<s:property value="getId()"/>_value" type="text"/>
                                    <s:property value="%{getText(getLocale())}" />
                                    <s:if test="getCommentable()">
                                        <input id="answer_<s:property value="getId()"/>_comment" name="answer_<s:property value="getId()"/>_comment" type="text"/>
                                    </s:if>
                                    <br/>
                                </s:iterator>
                            </s:if>
                        </s:elseif>
                    </s:div>
                </s:div>
            </s:iterator>
        </form>
    </s:if>
</s:div>