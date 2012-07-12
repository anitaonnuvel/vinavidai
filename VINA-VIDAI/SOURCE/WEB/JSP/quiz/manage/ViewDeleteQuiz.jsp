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
        <c:choose>
            <c:when test="${ form.deleteQuiz eq 'true'}">
                <title>VinaVidai - Delete Quiz - ${sessionScope.LOGGED_IN_USER.loginName}</title>
            </c:when>
            <c:otherwise>
                <title>VinaVidai - View Quiz - ${sessionScope.LOGGED_IN_USER.loginName}</title>
            </c:otherwise>
        </c:choose>
    </head>
    <body>
        <%@ include file="/common/Header.jsp" %>
        <div id="rootContainer">
            <div id="bodyContent">
                    <c:choose>
                        <c:when test="${ form.deleteQuiz eq 'true'}">
                            <h3>Delete Quiz</h3>
                            <p>You are attempting to delete the below Quiz. This action cannot be un-done. Please confirm if you would like to delete.</p>
                            <c:set var="viewDeleteAction" value="${form.processAction}"/>
                        </c:when>
                        <c:otherwise>
                            <h3>View Quiz</h3>
                            <c:set var="viewDeleteAction" value="${form.displayAction}"/>                            
                        </c:otherwise>
                    </c:choose>
                    <html:form action="${viewDeleteAction}" method="post">                               
                    <table id="ahpFormTable">
                        <tr>
                            <td>
                                Quiz Name
                            </td>
                            <td>
                                <bean:write name="${form.formName}" property="selectedQuiz.quizName"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Quiz Description
                            </td>
                            <td>
                                <bean:write name="${form.formName}" property="selectedQuiz.quizDescription"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Categories Tested
                            </td>
                            <td>
                                <ol>
                                <logic:iterate name="${form.formName}"
                                    property="selectedQuiz.categories"
                                    id="categoryItem">
                                    <li><bean:write name="categoryItem" property="category"/></li>
                                </logic:iterate>
                                </ol>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Skill Level
                            </td>
                            <td>
                                <ol>
                                <logic:iterate name="${form.formName}"
                                    property="selectedQuiz.skillLevels"
                                    id="skillLevelItem">
                                    <li><bean:write name="skillLevelItem" property="skillLevel"/></li>
                                </logic:iterate>
                                </ol>
                            </td>
                        </tr>                       
                    </table>
                    <c:choose>
	                    <c:when test="${ form.deleteQuiz eq 'true'}">
		                    <div id="formSubmit">
		                        <html:submit property="submitAction" value="Confirm Delete" styleClass="submitButton"/>
		                        <html:submit property="submitAction" value="Return to Manage Quiz" styleClass="submitButton"/>
		                    </div>
	                    </c:when>
	                    <c:otherwise>
		                    <div id="formSubmit">
		                        <html:submit property="submitAction" value="Delete" styleClass="submitButton"/>
		                        <html:submit property="submitAction" value="Edit" styleClass="submitButton"/>
		                        <html:submit property="submitAction" value="Return to Manage Quiz" styleClass="submitButton"/>
		                    </div>
	                    </c:otherwise>
                    </c:choose>
                </html:form>
            </div>
        </div>
        <%@ include file="/common/Footer.jsp" %>
    </body>
</html>