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
        <title>VinaVidai - Publish Quiz Confirmation - ${sessionScope.LOGGED_IN_USER.loginName}</title>
    </head>
    <body>
        <div id="rootContainer">
			<%@ include file="/common/Header.jsp" %>
			<div id="bodyContent">
			    <h3>Publish Quiz Confirmation</h3>		
				<p>Your quiz has been published successfully with the below configured settings as a Test. Below are the details of your Published Quiz.</p>
					<table id="ahpFormTable">
						<tr>
							<td>
								Test Name
							</td>
							<td>
							    <bean:write name="${form.formName}" property="test.testName"/>
							</td>
						</tr>
					</table>
					<div>
					   You can access this test with the following URL
					   <html:textarea name="${form.formName}"
					       property="testPublishUrl" 
					       rows="1" 
					       cols="100"		        
					       readonly="true">
					   </html:textarea>
					</div>
			</div>
        </div>
    </body>
</html>