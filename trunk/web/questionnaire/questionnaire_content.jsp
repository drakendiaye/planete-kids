<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h2><s:text name="questionnaire"/></h2>
<s:div cssClass="main">
    <h4><s:property value="getQuestionnaire().getTitle(getLocale())" /></h4>
    <p><s:property value="getQuestionnaire().getDescription(getLocale())" /></p>
    
    <s:if test="getQuestionnaire().getQuestions().size() > 0">
        <form id="questionnaire_form" method="post">
            <s:iterator id="test" value="getQuestionnaire().getQuestions()">
                <s:div cssClass="question">
                    <s:property value="getText(getLocale())" />
                    <s:div>
                        <s:if test="getPattern().toString() == 'SINGLE_CHOICE'">
                            <s:if test="getAnswers().size() > 0">
                                <script type="text/javascript">combo_load('question_<s:property value="getId()" />');</script>
                                <s:hidden id="question_%{getId()}" value="{ init : false, select_img : 'images/radio_selected.png', deselect_img : 'images/radio_deselected.png', items : %{getAnswerIds('answer_')}}"/>
                                
                                <s:iterator id="answer" value="getAnswers()">
                                    <s:hidden id="answer_%{getId()}" name="answer_%{getId()}"/>
                                    <img id="answer_<s:property value="getId()"/>_img" width="20px" onclick="combo_click('question_<s:property value="getQuestion().getId()" />','answer_<s:property value="getId()"/>')"/>
                                    <s:property value="%{getText(getLocale())}"/>
                                    <s:if test="getCommentable()">
                                        <script type="text/javascript">
                                            dojo.byId('answer_<s:property value="getId()"/>_comment_field').value = dojo.byId('answer_<s:property value="getId()"/>_comment').value;
                                        </script>
                                        <s:hidden id="answer_%{getId()}_comment" name="answer_%{getId()}_comment"/>
                                        <s:textfield id="answer_%{getId()}_comment_field" onchange="dojo.byId('answer_%{getId()}_comment').value = this.value"/>
                                    </s:if>
                                    <br/>
                                </s:iterator>
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                <!--<script type="text/javascript">
                                    function radioClick_<s:property value="getId()"/>(id) {
                                        <s:iterator value="getAnswers()">
                                            dojo.byId("answer_<s:property value="getId()"/>").value = "false";
                                            dojo.byId("answer_<s:property value="getId()"/>_img").src = "images/radio_deselected.png";
                                        </s:iterator>
                                        dojo.byId(id).value = "true";
                                        dojo.byId(id+"_img").src = "images/radio_selected.png";
                                    }
                                    
                                    if(<s:iterator value="getAnswers()"> dojo.byId("answer_<s:property value="getId()"/>").value == "false" && </s:iterator>true)
                                        radioClick_<s:property value="getId()"/>("answer_<s:property value="getAnswers().first().getId()"/>");
                                </script>
                                <s:iterator id="answer" value="getAnswers()">
                                    <s:hidden id="answer_%{getId()}" name="answer_%{getId()}" value="false"/>
                                    <img id="answer_<s:property value="getId()"/>_img" src="images/radio_deselected.png" width="20px" onclick="radioClick_<s:property value="getQuestion().getId()"/>('answer_<s:property value="getId()"/>')"/>
                                    <s:property value="%{getText(getLocale())}"/>
                                    <s:if test="getCommentable()">
                                        <script type="text/javascript">
                                            dojo.byId('answer_<s:property value="getId()"/>_comment_field').value = dojo.byId('answer_<s:property value="getId()"/>_comment').value;
                                        </script>
                                        <s:hidden id="answer_%{getId()}_comment" name="answer_%{getId()}_comment"/>
                                        <s:textfield id="answer_%{getId()}_comment_field" onchange="dojo.byId('answer_%{getId()}_comment').value = this.value"/>
                                    </s:if>
                                    <br/>
                                </s:iterator>-->
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
                                        <script type="text/javascript">
                                            dojo.byId('answer_<s:property value="getId()"/>_comment_field').value = dojo.byId('answer_<s:property value="getId()"/>_comment').value;
                                        </script>
                                        <s:hidden id="answer_%{getId()}_comment" name="answer_%{getId()}_comment"/>
                                        <s:textfield id="answer_%{getId()}_comment_field" onchange="dojo.byId('answer_%{getId()}_comment').value = this.value"/>
                                    </s:if>
                                    <br/>
                                </s:iterator>
                            </s:if>
                        </s:elseif>
                        <s:elseif test="getPattern().toString() == 'SORT'">
                            <!--<s:if test="getAnswers().size() > 0">
                                <script type="text/javascript">
                                    function sortUpClick_<s:property value="getId()"/>(id) {
                                        var temp = dojo.byId(id).value
                                        <s:iterator value="getAnswers()">
                                            dojo.byId("answer_<s:property value="getId()"/>").value = "false";
                                            dojo.byId("answer_<s:property value="getId()"/>_img").src = "images/radio_deselected.png";
                                        </s:iterator>
                                        dojo.byId(id).value = "true";
                                        dojo.byId(id+"_img").src = "images/radio_selected.png";
                                    }
                                    function sortDownClick_<s:property value="getId()"/>(id) {
                                        <s:iterator value="getAnswers()">
                                            dojo.byId("answer_<s:property value="getId()"/>").value = "false";
                                            dojo.byId("answer_<s:property value="getId()"/>_img").src = "images/radio_deselected.png";
                                        </s:iterator>
                                        dojo.byId(id).value = "true";
                                        dojo.byId(id+"_img").src = "images/radio_selected.png";
                                    }
                                </script>
                                <s:iterator id="answer" value="getAnswers()">
                                    <s:hidden id="answer_%{getId()}" name="answer_%{getId()}" value="false"/>
                                    <img id="answer_<s:property value="getId()"/>_img" src="images/radio_deselected.png" width="20px" onclick="radioClick_<s:property value="getQuestion().getId()"/>('answer_<s:property value="getId()"/>')"/>
                                    <s:property value="%{getText(getLocale())}"/>
                                    <s:if test="getCommentable()">
                                        <script type="text/javascript">
                                            dojo.byId('answer_<s:property value="getId()"/>_comment_field').value = dojo.byId('answer_<s:property value="getId()"/>_comment').value;
                                        </script>
                                        <s:hidden id="answer_%{getId()}_comment" name="answer_%{getId()}_comment"/>
                                        <s:textfield id="answer_%{getId()}_comment_field" onchange="dojo.byId('answer_%{getId()}_comment').value = this.value"/>
                                    </s:if>
                                    <br/>
                                </s:iterator>
                            </s:if>-->
                        </s:elseif>
                        <s:elseif test="getPattern().toString() == 'VALUE'">
                            <s:if test="getAnswers().size() > 0">
                                <s:iterator value="getAnswers()">
                                    <script type="text/javascript">
                                        dojo.byId('answer_<s:property value="getId()"/>_field').value = dojo.byId('answer_<s:property value="getId()"/>').value;
                                    </script>
                                    <s:hidden id="answer_%{getId()}" name="answer_%{getId()}"/>
                                    <s:textfield id="answer_%{getId()}_field" onchange="dojo.byId('answer_%{getId()}').value = this.value"/>
                                    <s:property value="%{getText(getLocale())}" />
                                    <s:if test="getCommentable()">
                                        <script type="text/javascript">
                                            dojo.byId('answer_<s:property value="getId()"/>_comment_field').value = dojo.byId('answer_<s:property value="getId()"/>_comment').value;
                                        </script>
                                        <s:hidden id="answer_%{getId()}_comment" name="answer_%{getId()}_comment"/>
                                        <s:textfield id="answer_%{getId()}_comment_field" onchange="dojo.byId('answer_%{getId()}_comment').value = this.value"/>
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