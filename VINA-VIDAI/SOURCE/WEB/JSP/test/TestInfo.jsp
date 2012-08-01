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

<%@ include file="/common/TagLibHeader.jsp"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value='${styleSheet}'/>"/>
        <%@ include file="/common/JQueryUi.jsp" %>
        <%@ include file="/common/JQueryUiDateTimePicker.jsp" %>
        <title>VinaVidai - TestInfo</title>
        <script type="text/javascript">
        </script>
    </head>
    <body>
        <%@ include file="/common/Header.jsp" %>
        <div id="rootContainer">            
            <div id="bodyContent">
                <h3>Take Test</h3>               
                <form:errors path="*" cssClass="ahpError" element="div"/>
                <form:form action="TestInfo.ahp" method="post" commandName="testInfo">
                    <table id="ahpFormTable">
                        <tr>
                            <td>
                                Test Name*
                            </td>
                            <td>
                                <form:input path="test.testName"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Quiz Description*
                            </td>
                            <td>
                                <form:input path="test.testDescription"/>
                            </td>
                        </tr>
                    </table>
                    <div id="formSubmit">
                        <input type="submit" value="Begin Test" name="submitAction"/>
                    </div>
                </form:form>
            </div>
        </div>
        <%@ include file="/common/Footer.jsp" %>
    </body>
</html>