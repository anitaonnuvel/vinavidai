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
<%@ include file="/common/JQuery.jsp" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value='${styleSheet}'/>"/>
        <title>VinaVidai - Console - ${sessionScope.LOGGED_IN_USER.loginName}</title>
    </head>
    <body>
        <%@ include file="/common/Header.jsp" %>
        <div id="rootContainer">
			<div id="bodyContent">
                    <h3>Administer your Quiz</h3>
                    <div>
                        <html:link page="/ProcessCreateQuiz.do">Create Quiz</html:link>
                    </div>
                    <div>
                        <html:link page="/ProcessCreateQuiz.do">Complete Quiz</html:link>
                    </div>
                    <div>
                        <html:link page="/ManageQuiz.do">Manage Quiz</html:link>
                    </div>
			</div>
        </div>
        <%@ include file="/common/Footer.jsp" %>
    </body>
</html>