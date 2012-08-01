<%--
  Copyright 2012 Anita Onnuvel
  
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  
  http://www.apache.org/licenses/LICENSE-2.0
  
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
--%>

<%@ include file="/common/TagLibHeader.jsp" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value='${styleSheet}'/>"/>
        <%@ include file="/common/JQueryUi.jsp" %>
        <%@ include file="/common/JQueryUiDateTimePicker.jsp" %>
        <title>VinaVidai - TakeTest</title>
    </head>
    <body>
        <%@ include file="/common/Header.jsp" %>
        <div id="rootContainer">            
            <div id="bodyContent">
                <h3>${takeTest.test.testDescription}</h3>               
                <form:errors path="*" cssClass="ahpError" element="div"/>
                <form:form method="post" commandName="takeTest">                    
                    <table id="ahpFormTable">
                        <tr>
                            <td align="left">
                                Question : <b><c:out value="${takeTest.currentQuestionNumber}"/> of  <c:out value="${takeTest.totalQuestions}"/></b>
                            </td>
                            <td align="left">
                                Question Category : <b><c:out value="${takeTest.currentQuestion.category.category}"/>
                            </td>
                            <td align="left">
                                Question SkillLevel : <b><c:out value="${takeTest.currentQuestion.skillLevel.skillLevel}"/>
                            </td>
                            <td align="left">
                                Time : <b><c:out value="${takeTest.currentQuestion.questionDuration}"/>
                            </td>
                        </tr>
                    </table>
                    <br/>
                    <br/>
                    <table id="ahpFormTable">
                        <tr>
                            <td align="left">
                                <c:out value="${takeTest.currentQuestion.questionDescription}"/>
                            </td>
                        </tr>
                        <c:if test="${takeTest.currentQuestion.questionType eq 'MultipleChoice'}">
                        <tr>    
                            <td align="left">
                                Please select options below
                            </td>
                        </tr>
                        <tr>    
                            <td align="left">
                                <form:checkboxes delimiter="<br/>" items="${takeTest.currentQuestion.options}" itemLabel="optionDescription" path=""/>
                            </td>
                        </tr>
                        </c:if>
                    </table>                    
                    <div id="formSubmit">
                        <input type="submit" value="Pause Test" name="submitAction"/>
                        <input type="submit" value="Cancel Test" name="submitAction"/>
                        <input type="submit" value="Next Question" name="submitAction"/>
                        <input type="submit" value="Complete Test" name="submitAction"/>                        
                    </div>
                </form:form>
            </div>
        </div>
        <%@ include file="/common/Footer.jsp" %>
    </body>
</html>