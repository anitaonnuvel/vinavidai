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
        <title>VinaVidai - Login</title>
        <%@ include file="/common/JQueryUi.jsp" %>
        <script>
            $(document).ready(function(){
                $( "#dialog-form" ).dialog({
                    autoOpen: false,
                    height: 450,
                    width: 450,
                    modal: true,
                    resizable :false,
                    buttons: {
                        "Create an account": function() {
                            $( "#registrationForm" ).submit();
                        },
                        Cancel: function() {
                            $( this ).dialog( "close" );
                        }
                    },
                    close: function() {
                        allFields.val( "" ).removeClass( "ui-state-error" );
                    }
                }); //end dialog                                     
           }); // end document.ready
           function showRegistrationForm() {
                $( "#dialog-form" ).dialog( "open" );
           } 
        </script>
        <style>
            .dialog-block { display:block; }
            .dialog-text { margin-bottom:5px; width:95%; padding: .4em; }
            fieldset { padding:0; border:0; margin-top:10px; }
            .ui-dialog .ui-state-error { padding: .3em; }
            .validateTips { border: 1px solid transparent; padding: 0.3em; }
        </style>
    </head>
    <body>
        <div id="rootContainer">
            <div id="bodyContent">
                <html:form action="${form.processAction}" method="post">
                    <div id="homePageContentBox">
                        <p>
                           VinaVidai is a open-source Quiz Software intended to be used in home schooling and for academic purposes. VinaVidai is platform independent and and can be integrated with any RDBMS/SQL database compatible with Java. 
                           <%--
                           VinaVidai also features with a Embedded database and WebApp server - download and start using right away! (To Do)
                           --%>
                        </p>
                        <p>
                            VinaVidai has the following features
                            <ol> 
                                <li>Exhaustive Question Types
                                   <ol>
                                       <li>Multiple Choice</li>
                                       <li>Multiple Options</li>
                                       <li>True or False</li>
                                       <li>Fill in the Blanks</li>
                                       <li>Word List</li>
                                       <li>Matching</li>
                                       <li>Ordering</li>
                                       <li>Essay</li>
                                   </ol>
                                </li>
                                <li>Extremely easy to install</li>
                                <li>Publish unlimited instances (Tests) of your Quiz with variable settings.</li>
                                <li>Web Based - Quiz can be published over Web and instantly available.</li>                                
                                <li>Create and assign groups to your Quiz.</li>
                                <li>Detailed Reports view of your tests</li>
                                <li>Bulk upload Users for registering with a Quiz. (To Do)</li>
                                <li>Bulk Import/Export Questions.(To Do)</li>
                                <li>Save Quiz in Word or Pdf format.(To Do)</li>                                
                                <li>E-mail notifications. (To Do)</li>                   
                            </ol>
                        </p>
                        <p> 
                            VinaVidai is release under <a href="http://www.apache.org/licenses/LICENSE-2.0.html">Apache License</a>.
                        </p>
                    </div>
                    <div id="loginBox">
                        <html:errors bundle="login"/>
                        <div class="loginTextBold">Existing Users</div>
                        <div class="loginText">Email</div>
                        <div class="signin-box">
                            <html:text name="${form.formName}"
                                property="loginName"
                                styleId="loginName"
                                styleClass="loginTextField"/>
                        </div>
                        <div class="loginText">Password</div>
                        <div class="signin-box">
                            <html:password name="${form.formName}"
                                property="password"
                                styleId="password"
                                styleClass="loginTextField"/>
                        </div>
                        <div class="loginText">
                            <html:submit 
                                property="submitAction" 
                                value="Login"
                                styleClass="loginButton"/>
                        </div>
                        <div class="loginText"><a href="/vinavidai/ForgotPassword.ahp" >Forgot Password?</a></div>
                        <hr/>
                        <br/>
                        <div class="loginTextBold">New Users</div>
                        <div class="loginText"><a href="#" onclick="showRegistrationForm();">Register Now</a></div>
                    </div>
                </html:form>
                <%-- Dialog Form --%>
                <div id="dialog-form" title="Create new user">
                    <form:errors path="*" cssClass="ahpError" element="div"/>
                    <p class="validateTips">All form fields are required.</p>
                    <form:form method="post" commandName="user" action="RegisterUser.ahp" id="registrationForm">
	                    <fieldset>
	                        <label for="firstName" class="dialog-block">First Name</label>
	                        <input type="text" name="firstName" id="newUserFirstName" class="dialog-block dialog-text ui-widget-content ui-corner-all"/>
	                        <label for="lastName" class="dialog-block">Last Name</label>
	                        <input type="text" name="lastName" id="newUserLastName" class="dialog-block dialog-text ui-widget-content ui-corner-all"/>                        
	                        <label for="loginName" class="dialog-block">Email</label>
	                        <input type="text" name="loginName" id="loginName" value="" class="dialog-block dialog-text ui-widget-content ui-corner-all"/>
	                        <label for="password" class="dialog-block">Password</label>
	                        <input type="password" name="password" id="password" value="" class="dialog-block dialog-text ui-widget-content ui-corner-all"/>
                            <label for="confirmPassword" class="dialog-block">Confirm Password</label>
                            <input type="password" name="confirmPassword" id="confirmPassword" value="" class="dialog-block dialog-text ui-widget-content ui-corner-all"/>
	                    </fieldset>
                    </form:form>
                </div>
                <%-- Dialog Form --%>
            </div>
        </div>
    </body>
</html>