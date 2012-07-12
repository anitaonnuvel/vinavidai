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
        <title>VinaVidai - Publish Quiz - ${sessionScope.LOGGED_IN_USER.loginName}</title> 
        <script type="text/javascript">
            $(document).ready(function() {
                $('#testAccessStartTime').datetimepicker({
                 onClose: function(dateText, inst) {
                     var endDateTextBox = $('#testAccessEndTime');
                     if (endDateTextBox.val() != '') {
                         var testStartDate = new Date(dateText);
                         var testEndDate = new Date(endDateTextBox.val());
                         if (testStartDate > testEndDate)
                             endDateTextBox.val(dateText);
                     }
                     else {
                         endDateTextBox.val(dateText);
                     }
                 },
                 onSelect: function (selectedDateTime){
                     var start = $(this).datetimepicker('getDate');
                     $('#testAccessEndTime').datetimepicker('option', 'minDate', new Date(start.getTime()));
                 }
                });
                
                $('#testAccessEndTime').datetimepicker({
                 onClose: function(dateText, inst) {
                     var startDateTextBox = $('#testAccessStartTime');
                     if (startDateTextBox.val() != '') {
                         var testStartDate = new Date(startDateTextBox.val());
                         var testEndDate = new Date(dateText);
                         if (testStartDate > testEndDate)
                             startDateTextBox.val(dateText);
                     }
                     else {
                         startDateTextBox.val(dateText);
                     }
                 },
                 onSelect: function (selectedDateTime){
                     var end = $(this).datetimepicker('getDate');
                     $('#testAccessStartTime').datetimepicker('option', 'maxDate', new Date(end.getTime()) );
                 }
                });
                
             });
        </script>
    </head>
    <body>
        <%@ include file="/common/Header.jsp" %>
        <div id="rootContainer">
            
            <div id="bodyContent">
                <h3>Publish Quiz</h3>
                <html:errors bundle="vinavidai"/>
                <html:form action="${form.processAction}" method="post" onsubmit="return selectCategoriesAndSkillLevels()">
                    <table id="ahpFormTable">
                        <tr>
                            <td>
                                Quiz Name*
                            </td>
                            <td>
                                <bean:write name="${form.formName}" property="quizUnderPublish.quizName"></bean:write>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Test Name*
                            </td>
                            <td>
                                <html:text name="${form.formName}"
                                    property="testName"
                                    styleId="testName"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Test Description*
                            </td>
                            <td>
                                <html:textarea name="${form.formName}"
                                    property="testDescription"
                                    styleId="testDescription"/>                       
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Test Duration*
                            </td>
                            <td>
                                <label>
                                    <html:radio name="${form.formName}" property="testDurationType" value="Timed"/>
                                    Timed
                                </label>&nbsp;
                                Hours:
                                <html:select name="${form.formName}"
                                             property="fixedDurationHours"
                                             styleId="fixedDurationHours">
                                    <html:options name="${form.formName}" property="hoursDisplaySet"/>
                                </html:select>&nbsp;
                                Minutes:
                                <html:select name="${form.formName}"
                                             property="fixedDurationMinutes"
                                             styleId="fixedDurationMinutes">
                                    <html:options name="${form.formName}" property="minutesDisplaySet"/>
                                </html:select>
                                <br/>
                                <label>
                                    <html:radio name="${form.formName}" property="testDurationType" value="Indefinite"/>
                                Indefinite
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Quiz Group Name
                            </td>
                            <td>
                                <input type="text" name="quizGroupName" id="quizGroupName" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Quiz Group Description
                            </td>
                            <td>
                                <input type="text" name="quizGroupDescription" id="quizGroupDescription"  disabled="true"/>
                            </td>
                        </tr>                        
                        <tr>
                            <td>
                                Test Access Time*
                            </td>
                            <td>
                                <label><html:radio name="${form.formName}" property="testAccessTimeType" value="Timed"/>Timed</label>&nbsp;
                                Start Time:<html:text name="${form.formName}" property="testAccessStartTime" styleId="testAccessStartTime"/>
                                &nbsp;End Time:<html:text name="${form.formName}" property="testAccessEndTime" styleId="testAccessEndTime"/>
                                <br/>
                                <label><html:radio name="${form.formName}" property="testAccessTimeType" value="Indefinite"/>Any Time</label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Response Duration Per Question
                            </td>
                            <td>
                                <label>
                                    <html:radio name="${form.formName}" property="responseDurationPerQuestionType" value="Timed"/>Timed
                                </label>&nbsp;
                                Hours:
                                <html:select name="${form.formName}"
                                    property="responseDurationPerQuestionHours"
                                    styleId="responseDurationPerQuestionHours">
                                    <html:options name="${form.formName}" property="hoursDisplaySet"/>
                                </html:select>&nbsp;
                                Minutes:
                                <html:select name="${form.formName}"
                                    property="responseDurationPerQuestionMinutes"
                                    styleId="responseDurationPerQuestionMinutes">
                                    <html:options name="${form.formName}" property="minutesDisplaySet"/>
                                </html:select>&nbsp;
                                Seconds:
                                <html:select name="${form.formName}"
                                    property="responseDurationPerQuestionSeconds"
                                    styleId="responseDurationPerQuestionSeconds">
                                    <html:options name="${form.formName}" property="secondsDisplaySet"/>
                                </html:select>                                
                                <br/>
                                <label>
                                    <html:radio name="${form.formName}" property="responseDurationPerQuestionType" value="Indefinite"/>Indefinite
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Test Pass Percentile*
                            </td>
                            <td>
                                <html:text name="${form.formName}"
                                    property="testPassPercentile"
                                    styleId="testPassPercentile"/>&nbsp;(defaults to 60%)
                            </td>
                        </tr>
                        <tr>
                            <td>Option List Style
                            </td>
                            <td>
                                <html:select name="${form.formName}"
                                    property="selectedListStyle"
                                    size="1">
                                    <html:options name="${form.formName}"
                                        property="listStyleSet"/>
                                </html:select> 
                            </td>
                        </tr>
                        <tr>
                            <td>Option Display Style
                            </td>
                            <td><html:select name="${form.formName}"
                                    property="selectedDisplayStyle"
                                    size="1"><html:options name="${form.formName}"
                                        property="displayStyleSet"/>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>Question Order*
                            </td>
                            <td>
                                <select name="questionOrder"
                                    disabled="true">
                                    <option value="AsCreated">AsCreated</option>
                                    <option value="Random">Random</option>
                                </select>
                            </td>
                        </tr>    
                        <tr>
                            <td>Quiz Retake Policy*
                            </td>
                            <td>
                                <select name="testRetakePolicy"
                                    disabled="true">
                                    <option value="0">0</option>
                                    <option value="1">1</option>
                                    <option value="1">No Limit</option>
                                </select>
                            </td>
                        </tr>                        
                        <tr>
                            <td>Inform user if answer is Correct*
                            </td>
                            <td>
                                <label><input type="radio" name="temp" value="true"/>Yes</label>
                                <label><input type="radio" name="temp" value="false"/>No</label>
                            </td>
                        </tr>                        
                        <tr>
                            <td>Display Correct Answer to user
                            </td>
                            <td>
                                <label><input type="radio" name="temp" value="true"/>Yes</label>
                                <label><input type="radio" name="temp" value="false"/>No</label>
                            </td>
                        </tr>                        
                        <tr>
                            <td>Display Test Score at the end of Quiz
                            </td>
                            <td>
                                <label><input type="radio" name="temp" value="true"/>Yes</label>
                                <label><input type="radio" name="temp" value="false"/>No</label>
                            </td>
                        </tr>                        
                    </table>
                    <div id="formSubmit">
                        <html:submit property="submitAction" value="Publish Quiz" styleClass="submitButton"/>
                    </div>
                </html:form>
            </div>
        </div>
        <%@ include file="/common/Footer.jsp" %>
    </body>
</html>