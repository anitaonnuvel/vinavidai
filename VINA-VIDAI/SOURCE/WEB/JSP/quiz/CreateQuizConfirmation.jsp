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
        <title>VinaVidai - Quiz Confirmation - ${sessionScope.LOGGED_IN_USER.loginName}</title>
    </head>
    <body>
        <div id="rootContainer">
			<%@ include file="/common/Header.jsp" %>
			<div id="bodyContent">
			    <h3>Create Quiz Confirmation</h3>		
				<p>Your quiz has been saved successfully. Below are the details of your Quiz. Please go to your <html:link action="ProcessQuizConsole.do">Admin Console</html:link> and publish your quiz.</p>
					<table id="ahpFormTable">
						<tr>
							<td>
								Quiz Name
							</td>
							<td>
							    <bean:write name="${form.formName}" property="quiz.quizName"/>
							</td>
						</tr>
						<tr>
							<td>
								Categories Tested
							</td>
							<td>
							    <ol>
								<logic:iterate name="${form.formName}"
								    property="quiz.categories"
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
                                    property="quiz.skillLevels"
                                    id="skillLevelItem">
                                    <li><bean:write name="skillLevelItem" property="skillLevel"/></li>
                                </logic:iterate>
                                </ol>
							</td>
						</tr>						
					</table>
			</div>
        </div>
    </body>
</html>