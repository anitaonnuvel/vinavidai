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
        <c:if test="${form.quizOperationType eq 'Create'}">
            <title>VinaVidai - Create Quiz - ${sessionScope.LOGGED_IN_USER.loginName}</title>
        </c:if>                               
        <c:if test="${form.quizOperationType eq 'Edit'}">
            <title>VinaVidai - Edit Quiz - ${sessionScope.LOGGED_IN_USER.loginName}</title>
        </c:if> 
        <script type="text/javascript">
            /*function addSkill(){
                newSkillLevel = $('#skillLevelTxt').val();
                $('#skillLevels').append(new Option(newSkillLevel, newSkillLevel, false, false));
            }
            function removeSkill(){
                $("#skillLevels option:selected").remove();
            }
            function addCategory(){
                newQuestionCategory = $('#questionCategoryTxt').val();
                $('#categories').append(new Option(newQuestionCategory, newQuestionCategory, false, false));
            }
            function removeCategory(){
                $("#categories option:selected").remove();
            }
            $(document).ready(function() {
                $("#questionCategoryTxt").autocomplete({
                    source: [${form.categoriesAutoComplete}]
                });
                $("#skillLevelTxt").autocomplete({
                    source: [${form.skillLevelsAutoComplete}]
                });                
             });
             function selectCategoriesAndSkillLevels(){
                $('#skillLevels *').attr('selected','');
                $('#categories *').attr('selected','');
             }*/
        </script>
    </head>
    <body>
        <%@ include file="/common/Header.jsp" %>
        <div id="rootContainer">
            <bean:size id="categorySize" property="category" name="${form.formName}"/>
            <bean:size id="skillLevelSize" property="skillLevel" name="${form.formName}"/>            
            <div id="bodyContent">
                <c:if test="${form.quizOperationType eq 'Create'}">
                    <h3>Create Quiz</h3>
                </c:if>                               
                <c:if test="${form.quizOperationType eq 'Edit'}">
                    <h3>Edit Quiz</h3>
                </c:if>               
                <html:errors bundle="vinavidai"/>
                <html:form action="${form.processAction}" method="post">
                    <table id="ahpFormTable">
                        <tr>
                            <td>
                                Quiz Name*
                            </td>
                            <td>
                                <html:text name="${form.formName}"
                                    property="quizName"
                                    styleId="quizName"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Quiz Description*
                            </td>
                            <td>
                                <html:textarea name="${form.formName}"
                                    property="quizDescription"
                                    styleId="quizDescription"/>                       
                            </td>
                        </tr> 
                        <tr>
                            <td>Category for Questions
                            </td>
                            <td>
                                <logic:iterate name="${form.formName}"
                                    property="category"
                                    id="categoryItem"
                                    indexId="categoryCounter">
                                    <logic:notEqual name="categoryItem" property="category" value="Default">
                                        <div>
                                            <html:text name="categoryItem"
                                                property="category"
                                                styleId="categoryItem"
                                                indexed="true"/>                                                
                                            &nbsp;
                                            <c:choose>
                                                <c:when test="${categorySize == 1}">  
	                                                <button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only" name="submitAction" value="Add Category">
	                                                    <span class="ui-button-icon-primary ui-icon ui-icon-plusthick"></span><span class="ui-button-text">Add Category</span>
	                                                </button>   
                                                </c:when> 
                                                 <c:when test="${(categorySize-1) == categoryCounter}">                                              
		                                            <button type="submit" onclick="$('#hiddenDeleteCategoryIndex').val(${categoryCounter});${form.formName}.submit();" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only" name="submitAction" value="Delete Category">
		                                                <span class="ui-button-icon-primary ui-icon ui-icon-minusthick"></span><span class="ui-button-text">Delete Category</span>
		                                            </button>                                             
	                                                <button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only" name="submitAction" value="Add Category">
	                                                    <span class="ui-button-icon-primary ui-icon ui-icon-plusthick"></span><span class="ui-button-text">Add Category</span>
	                                                </button>                                   
                                                </c:when>  
	                                            <c:otherwise>  
	                                                <button type="submit" onclick="$('#hiddenDeleteCategoryIndex').val(${categoryCounter});${form.formName}.submit();" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only" name="submitAction" value="Delete Category">
	                                                    <span class="ui-button-icon-primary ui-icon ui-icon-minusthick"></span><span class="ui-button-text">Delete Category</span>
	                                                </button>
	                                            </c:otherwise>                                           
                                            </c:choose>
                                        </div>
                                    </logic:notEqual>                                             
                                </logic:iterate>
                            </td>
                        </tr>
                        <tr>
                            <td>Skill Levels
                            </td>
                            <td>
                                <logic:iterate name="${form.formName}"
                                    property="skillLevel"
                                    id="skillLevelItem"
                                    indexId="skillLevelCounter">
                                    <logic:notEqual name="skillLevelItem" property="skillLevel" value="Default">
                                    <div>
                                        <html:text name="skillLevelItem"
                                            property="skillLevel"
                                            styleId="skillLevelItem"
                                            indexed="true"/>                                                
                                        &nbsp;
                                        <c:choose>
	                                        <c:when test="${skillLevelSize == 1}">
	                                            <button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only" name="submitAction" value="Add Skill Level">
	                                                <span class="ui-button-icon-primary ui-icon ui-icon-plusthick"></span><span class="ui-button-text">Add Skill Level</span>
	                                            </button>   
	                                        </c:when>
	                                         <c:when test="${(skillLevelSize-1) == skillLevelCounter}"> 
		                                        <button type="submit" onclick="$('#hiddenDeleteSkillLevelIndex').val('${skillLevelCounter}');${form.formName}.submit();" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only" name="submitAction" value="Delete Skill Level">
		                                            <span class="ui-button-icon-primary ui-icon ui-icon-minusthick"></span><span class="ui-button-text">Delete Skill Level</span>
		                                        </button> 
	                                            <button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only" name="submitAction" value="Add Skill Level">
	                                                <span class="ui-button-icon-primary ui-icon ui-icon-plusthick"></span><span class="ui-button-text">Add Skill Level</span>
	                                            </button>                                   
	                                        </c:when>  
	                                        <c:otherwise>
		                                        <button type="submit" onclick="$('#hiddenDeleteSkillLevelIndex').val('${skillLevelCounter}');${form.formName}.submit();" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only" name="submitAction" value="Delete Skill Level">
		                                            <span class="ui-button-icon-primary ui-icon ui-icon-minusthick"></span><span class="ui-button-text">Delete Skill Level</span>
		                                        </button>
	                                        </c:otherwise>                                        
                                        </c:choose>
                                    </div>
                                    </logic:notEqual>                                             
                                </logic:iterate>
                            </td>
                        </tr>
                    </table>
                    <div id="formSubmit">
                        <c:if test="${form.quizOperationType eq 'Create'}">
                            <html:submit property="submitAction" value="Save and Add Questions" styleClass="submitButton"/>
                        </c:if>
                        <c:if test="${form.quizOperationType eq 'Edit'}">
                            <span style="float:left">
                                <html:link action="ManageQuiz.do">Manage Quiz</html:link>
                            </span>
                            <span style="float:right">
	                           <html:submit property="submitAction" value="Save Changes" styleClass="submitButton"/>
	                           <html:submit property="submitAction" value="Manage Questions" styleClass="submitButton"/>
	                        </span>
                        </c:if>
                    </div>
                    <html:hidden name="${form.formName}" property="hiddenDeleteCategoryIndex" styleId="hiddenDeleteCategoryIndex" value="-1"></html:hidden>
                    <html:hidden name="${form.formName}" property="hiddenDeleteSkillLevelIndex" styleId="hiddenDeleteSkillLevelIndex" value="-1"></html:hidden>
                </html:form>
            </div>
        </div>
        <%@ include file="/common/Footer.jsp" %>
    </body>
</html>