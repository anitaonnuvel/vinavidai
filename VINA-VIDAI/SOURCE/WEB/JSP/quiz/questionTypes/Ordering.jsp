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

                            <html:form action="${form.processAction}" method="post">
                                <table id="ahpFormTablePlain">
                                    <tr>
                                        <td>
                                            <strong>Question ${questionSize+1} Description*</strong>
                                        </td>
                                        <td>
                                            <html:textarea name="${form.formName}"
                                               property="questionDescription"
                                               rows="5" 
                                               cols="50"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Question objective
                                        </td>
                                        <td>
                                            <html:textarea name="${form.formName}"
                                                property="questionObjective"
                                                rows="2" 
                                                cols="50"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><strong>Ordering Items*</strong>
                                        </td>
                                        <td>                                    
		                                    <c:if test="${optionsSize-1 != 0 }">
		                                    <logic:iterate name="${form.formName}"
		                                        property="options"
		                                        id="option"
		                                        indexId="optionCounter">
		                                                <div>  
		                                                <html:text name="option"
		                                                   property="optionDescription"
		                                                   indexed="true"/>                                                
		                                                    &nbsp;
		                                                    <c:if test="${(optionsSize-1) == optionCounter}">    
		                                                        <button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only" name="submitAction" value="Add Option">
		                                                            <span class="ui-button-icon-primary ui-icon ui-icon-plusthick"></span><span class="ui-button-text">Add Option</span>
		                                                        </button>                                   
		                                                    </c:if>  
		                                                    <button type="submit" onclick="$('#hiddenDeleteOptionIndex').val('${optionCounter}');${form.formName}.submit();" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only" name="submitAction" value="Delete Option">
		                                                        <span class="ui-button-icon-primary ui-icon ui-icon-minusthick"></span><span class="ui-button-text">Delete Option</span>
		                                                    </button>
		                                                </div>                                             
		                                    </logic:iterate>
                                        </td>
                                    </tr>
                                    </c:if>                                    
                                    <%-- <tr>
                                        <td>Please enter your Entries (You can drag on the items to order)&nbsp;</td>
                                        <td>
                                            <div class="sortable-wrapper">
                                                <ul id="ordering" style="list-style-type: none; margin: 0; padding: 0;">
                                                    <div style="border: 0.063em solid #CCCCCC; padding:4px; 0px; 4px; 0px;">
                                                        Item 1<input type="text" name="item1" size="40"/>
                                                    </div>
                                                    <div style="border: 0.063em solid #CCCCCC; padding:4px; 0px; 4px; 0px;">
                                                        Item 2<input type="text" name="item2" size="40"/>
                                                    </div>
                                                    <div style="border: 0.063em solid #CCCCCC; padding:4px; 0px; 4px; 0px;">
                                                        Item 3<input type="text" name="item3" size="40"/>
                                                    </div>
                                                    <div style="border: 0.063em solid #CCCCCC; padding:4px; 0px; 4px; 0px;">
                                                        Item 4<input type="text" name="item4" size="40"/>
                                                    </div>
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>--%>
                                    <tr>
                                        <td>
                                            <strong>Question Category*</strong>
                                        </td>
                                        <td>
                                            <html:select name="${form.formName}"
                                                property="selectedQuestionCategory" 
                                                size="1">
                                                <c:if test="${categorySize > 1}">
                                                    <html:option value="">Select Question Category</html:option>
                                                </c:if>
                                                <html:optionsCollection name="${form.formName}"
                                                    property="quiz.categories"
                                                    label="category" 
                                                    value="categoryId"/>                                        
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <strong>Skill Level*</strong>
                                        </td>
                                        <td>
                                            <html:select name="${form.formName}" 
                                                property="selectedQuestionSkillLevel" 
                                                size="1">
                                                <c:if test="${skillLevelSize > 1}">
                                                    <html:option value="">Select Question SkillLevel</html:option>
                                                </c:if>
                                                <html:optionsCollection name="${form.formName}" 
                                                    property="quiz.skillLevels"
                                                    label="skillLevel" 
                                                    value="skillLevelId"/>                                        
                                            </html:select> 
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Duration
                                        </td>
                                        <td>
                                            Hours:
                                            <html:select name="${form.formName}"
                                                property="responseDurationInHours"
                                                styleId="responseDurationInHours">
                                                <html:options name="${form.formName}" property="hoursDisplaySet"/>
                                            </html:select>&nbsp;
                                            Minutes:
                                            <html:select name="${form.formName}"
                                                property="responseDurationInMinutes"
                                                styleId="responseDurationInMinutes">
                                                <html:options name="${form.formName}" property="minutesDisplaySet"/>
                                            </html:select>&nbsp;
                                            Seconds:
                                            <html:select name="${form.formName}"
                                                property="responseDurationInSeconds"
                                                styleId="responseDurationInSeconds">
                                                <html:options name="${form.formName}" property="secondsDisplaySet"/>
                                            </html:select>

                                        </td>
                                    </tr>
                                </table>
                                <html:hidden name="${form.formName}" property="questionType" value="Ordering"></html:hidden>
                                <div id="formSubmit">
                                    <span style="float:left">
                                        <button type="submit" name="submitAction" value="Cancel">Cancel</button>
                                        <button type="submit" name="submitAction" value="Reset">Reset</button>
                                    </span>
                                    <span style="float:right">
                                        <button type="submit" name="submitAction" value="Next">Next</button>
                                        <button type="submit" name="submitAction" value="Complete">Complete</button>
                                    </span>
                                </div>
                            </html:form>