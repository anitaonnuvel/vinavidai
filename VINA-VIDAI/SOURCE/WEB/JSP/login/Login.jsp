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
<%-- http://css-tricks.com/6206-resolution-specific-stylesheets/
http://css-tricks.com/video-screencasts/10-fixed-width-fluid-width-elastic-width/
http://jontangerine.com/log/2007/09/the-incredible-em-and-elastic-layouts-with-css
 --%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" type="text/css" href="<c:url value='${styleSheet}'/>"/>
        <title>VinaVidai - Login</title>
    </head>
    <body>
        <div id="rootContainer">
            <div id="bodyContent">
                
                <html:form action="${form.processAction}" method="post">
                    <div id="homePageContentBox">
                        <p>
                           VinaVidai is a open-source Quiz Software intended to be used in home schooling and for academic purposes. VinaVidai is platform independent and and can be integrated with any RDBMS/SQL database compatible with Java. VinaVidai also features with a Embedded database and WebApp server - download and start using right away!
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
                        <div class="loginText"><a href="#">Forgot Password?</a></div>
                        <hr/>
                        <br/>
                        <div class="loginTextBold">New Users</div>
                        <div class="loginText"><a href="#">Register Now</a></div>
                    </div>
                </html:form>
            </div>
        </div>
    </body>
</html>