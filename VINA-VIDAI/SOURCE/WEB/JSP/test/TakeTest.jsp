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
        <script type="text/javascript">
        </script>
    </head>
    <body>
        <%@ include file="/common/Header.jsp" %>
        <div id="rootContainer">            
            <div id="bodyContent">
                <h3>Take Test</h3>               
                <html:errors bundle="vinavidai"/>
                <html:form action="${form.processAction}" method="post">
                    <table id="ahpFormTable">
                        <tr>
                            <td>
                                Test Name*
                            </td>
                            <td>
                                <html:text name="${form.formName}"
                                    property="takeTest.testName"
                                    styleId="testName"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Quiz Description*
                            </td>
                            <td>
                                <html:textarea name="${form.formName}"
                                    property="takeTest.testDescription"
                                    styleId="testDescription"/>                       
                            </td>
                        </tr>
                    </table>
                    <div id="formSubmit">
                        <html:submit property="submitAction" value="Begin Test" styleClass="submitButton"/>
                    </div>
                </html:form>
            </div>
        </div>
        <%@ include file="/common/Footer.jsp" %>
    </body>
</html>