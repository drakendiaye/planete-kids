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
                                <script type="text/javascript">radio_load('question_<s:property value="getId()" />');</script>
                                <s:hidden id="question_%{getId()}" value="{ init : false, select_img : 'images/radio_selected.png', deselect_img : 'images/radio_deselected.png', items : %{getAnswerIds('answer_')}}"/>
                                <s:iterator id="answer" value="getAnswers()">
                                    <s:hidden id="answer_%{getId()}" name="answer_%{getId()}"/>
                                    <img id="answer_<s:property value="getId()"/>_img" width="20px" onclick="radio_click('question_<s:property value="getQuestion().getId()" />','answer_<s:property value="getId()"/>')"/>
                                    <s:property value="%{getText(getLocale())}"/>
                                    <s:if test="getCommentable()">
                                        <script type="text/javascript">text_load('answer_<s:property value="getId()" />_comment');</script>
                                        <s:hidden id="answer_%{getId()}_comment" name="answer_%{getId()}_comment" value="{ init : false, value : ''}"/>
                                        <s:textfield id="answer_%{getId()}_comment_field" onkeyup="text_press('answer_%{getId()}_comment')"/>
                                    </s:if>
                                    <br/>
                                </s:iterator>
                            </s:if>
                        </s:if>
                        <s:elseif test="getPattern().toString() == 'MULTI_CHOICE'">
                            <s:if test="getAnswers().size() > 0">
                                <script type="text/javascript">check_load('question_<s:property value="getId()" />');</script>
                                <s:hidden id="question_%{getId()}" value="{ init : false, select_img : 'images/check_selected.png', deselect_img : 'images/check_deselected.png', items : %{getAnswerIds('answer_')}}"/>
                                <s:iterator id="answer" value="getAnswers()">
                                    <s:hidden id="answer_%{getId()}" name="answer_%{getId()}"/>
                                    <img id="answer_<s:property value="getId()"/>_img" width="20px" onclick="check_click('question_<s:property value="getQuestion().getId()" />','answer_<s:property value="getId()"/>')"/>
                                    <s:property value="%{getText(getLocale())}"/>
                                    <s:if test="getCommentable()">
                                        <script type="text/javascript">text_load('answer_<s:property value="getId()" />_comment');</script>
                                        <s:hidden id="answer_%{getId()}_comment" name="answer_%{getId()}_comment" value="{ init : false, value : ''}"/>
                                        <s:textfield id="answer_%{getId()}_comment_field" onkeyup="text_press('answer_%{getId()}_comment')"/>
                                    </s:if>
                                    <br/>
                                </s:iterator>
                            </s:if>
                        </s:elseif>
                        <s:elseif test="getPattern().toString() == 'SORT'">
                            <s:if test="getAnswers().size() > 0">
                                <script type="text/javascript">sort_load('question_<s:property value="getId()" />');</script>
                                <s:hidden id="question_%{getId()}" value="{ init : false, up_enable_img : 'images/up_enable.png', up_disable_img : 'images/up_disable.png', down_enable_img : 'images/down_enable.png', down_disable_img : 'images/down_disable.png', items : %{getAnswerIds('answer_')}}"/>
                                <s:iterator id="answer" value="getAnswers()">
                                    <s:div id="answer_%{getId()}_div">
                                        <s:hidden id="answer_%{getId()}" name="answer_%{getId()}"/>
                                        <img id="answer_<s:property value="getId()"/>_up_img" width="20px" onclick="sort_up_click('question_<s:property value="getQuestion().getId()" />','answer_<s:property value="getId()"/>')"/>
                                        <img id="answer_<s:property value="getId()"/>_down_img" width="20px" onclick="sort_down_click('question_<s:property value="getQuestion().getId()" />','answer_<s:property value="getId()"/>')"/>
                                        <s:property id="answer_%{getId()}_lbl" value="%{getText(getLocale())}"/>
                                        <s:if test="getCommentable()">
                                            <script type="text/javascript">text_load('answer_<s:property value="getId()" />_comment');</script>
                                            <s:hidden id="answer_%{getId()}_comment" name="answer_%{getId()}_comment" value="{ init : false, value : ''}"/>
                                            <s:textfield id="answer_%{getId()}_comment_field" onkeyup="text_press('answer_%{getId()}_comment')"/>
                                        </s:if>
                                    </s:div>
                                    <br/>
                                </s:iterator>
                            </s:if>
                        </s:elseif>
                        <s:elseif test="getPattern().toString() == 'VALUE'">
                            <s:if test="getAnswers().size() > 0">
                                <s:iterator value="getAnswers()">
                                    <script type="text/javascript">text_load('answer_<s:property value="getId()" />');</script>
                                    <s:hidden id="answer_%{getId()}" name="answer_%{getId()}" value="{ init : false, value : ''}"/>
                                    <s:textfield id="answer_%{getId()}_field" onkeyup="text_press('answer_%{getId()}')"/>
                                    <s:property value="%{getText(getLocale())}" />
                                    <s:if test="getCommentable()">
                                        <script type="text/javascript">text_load('answer_<s:property value="getId()" />_comment');</script>
                                        <s:hidden id="answer_%{getId()}_comment" name="answer_%{getId()}_comment" value="{ init : false, value : ''}"/>
                                        <s:textfield id="answer_%{getId()}_comment_field" onkeyup="text_press('answer_%{getId()}_comment')"/>
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