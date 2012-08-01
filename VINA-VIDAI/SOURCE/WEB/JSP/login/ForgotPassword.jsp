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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- http://css-tricks.com/6206-resolution-specific-stylesheets/
http://css-tricks.com/video-screencasts/10-fixed-width-fluid-width-elastic-width/
http://jontangerine.com/log/2007/09/the-incredible-em-and-elastic-layouts-with-css
 --%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" type="text/css" href="<c:url value='${styleSheet}'/>"/>
        <title>VinaVidai - Registration</title>
        <%@ include file="/common/JQueryUi.jsp" %>
    </head>
    <body>
        <%@ include file="/common/Header.jsp" %>
        <div id="rootContainer">
            <div id="bodyContent">
                <p class="validateTips">All * fields are required.</p> 
                <form:form method="post" commandName="user" action="ForgotPassword.ahp">
                    <div id="registrationBox">                        
                        <div class="loginText">Email *</div>
                        <div class="signin-box">
                            <form:errors path="loginName" cssClass="ahpError"/>
                            <form:input path="loginName"/>
                        </div>
                        <div class="loginText">Password *</div>
                        <div class="signin-box">
                            <form:errors path="password" cssClass="ahpError"/>
                            <form:password path="password"/>                            
                        </div>
                        <div class="loginText">Confirm Password *</div>
                        <div class="signin-box">
                            <form:errors path="confirmPassword" cssClass="ahpError"/>
                            <form:password path="confirmPassword"/>                            
                        </div>
                        <div class="loginText">
                            <html:submit 
                                property="submitAction" 
                                value="Reset Password"/>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </body>
</html>