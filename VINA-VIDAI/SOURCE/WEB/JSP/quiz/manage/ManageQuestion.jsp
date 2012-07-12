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
        <title>VinaVidai - Manage Question - ${sessionScope.LOGGED_IN_USER.loginName}</title>
        <script type="text/javascript">
            $(document).ready(function(){   
                $('#operation').val('');
                $('#hiddenSelectedComboValue').val('');
            });
            function submitPage(val){
                $('#hiddenSelectedComboValue').val(val);
                $('#operation').val('ComboSubmit');
                $('form[name=${form.formName}]').submit();
            } 
            function submitPageForHyperLink(val){
                $('#operation').val(val);
                $('form[name=${form.formName}]').submit();
            }
            function viewQuiz(val){
                alert('view Quiz');
                $('#hiddenSelectedItemId').val(val);
                $('#hiddenEditOperation').val('View Quiz');
                $('form[name=${form.formName}]').submit();
            } 
        </script>
    </head>
    <body>
        <div id="rootContainer">
            <%@ include file="/common/Header.jsp" %>
            <div id="bodyContent">                               
                <div style="margin:8px 0px 8px 0px;">
                    <h3 style="display:inline">Manage Quiz</h3>
	                <span class="ahpHyperLinkBox" style="float:right">
	                    <html:link action="ProcessCreateQuiz.do">Create New Quiz</html:link>
	                </span>
                </div>
                <html:errors bundle="vinavidai"/>
                <html:form action="${form.processAction}" method="post">
                    <table id="ahpPaginationTable">
                        <%@ include file="/common/PaginationTop.jsp" %>
                        <tr>
                            <th>Select</th>
                            <th>Quiz Name</th>
                            <th>Quiz Status</th>
                            <th>Created Date</th>
                        </tr>
                        <logic:iterate name="${form.formName}"
                            property="quizResults"
                            id="quiz">
                            <tr>
                            <td width="5%">
                                <html:radio name="${form.formName}" property="selectedQuizId" value="${quiz.quizId}"></html:radio>
                            </td>
                            <td>
                                <html:link href="#" onclick="viewQuiz(${quiz.quizId});">${quiz.quizName}</html:link>
                            </td>
                            <td>
                                
                            </td>
                            <td>a
                                <fmt:formatDate type="both" dateStyle="default" timeStyle="default" value="${quiz.audit.createdDate}" />
                            </td>
                            </tr>
                        </logic:iterate>
                        <%@ include file="/common/PaginationBottom.jsp" %>
                    </table>    
                    <div>
                        <span style="float:right">
                            <html:submit property="submitAction" value="Edit" styleClass="submitButton"/>
                            <html:submit property="submitAction" value="Publish" styleClass="submitButton"/>
                            <html:submit property="submitAction" value="Reports" styleClass="submitButton"/>
                        </span>
                        <span style="float:left">
                            <html:submit property="submitAction" value="Delete" styleClass="submitButton"/>
                            <html:submit property="submitAction" value="Return to Console" styleClass="submitButton"/>
                        </span>
                    </div>
                    <br/>
                    <br/>
                    <html:hidden property="paginationData.operation" styleId="operation"/>
                    <html:hidden property="paginationData.hiddenSelectedComboValue" styleId="hiddenSelectedComboValue"/>
                    <html:hidden property="hiddenSelectedItemId" styleId="hiddenSelectedItemId"/>
                    <html:hidden property="hiddenEditOperation" styleId="hiddenEditOperation"/>
                </html:form>
            </div>
            <%@ include file="/common/Footer.jsp" %>
        </div>
    </body>
</html>