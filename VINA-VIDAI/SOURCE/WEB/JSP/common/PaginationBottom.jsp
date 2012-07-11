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

<tr>
	<!-- Start Pagination -->
	<td colspan="${form.paginationData.tableColSpan}"
		style="background: #FFF;" class="ahpPaginationTableWhite">
		<c:if test="${form.paginationData.totalRecords > form.paginationData.maxRecordsPerPage}">
			<span style="float: right"> 
			    <c:if test="${form.paginationData.showPreviousPageFlag}">
					<html:link href="#" onclick="submitPageForHyperLink('ShowPrevious')">
						<bean:message bundle="vinavidai" key="common.link.previous" />
					</html:link>
				</c:if> 
				<c:if test="${not form.paginationData.showPreviousPageFlag}">
					<bean:message bundle="vinavidai" key="common.link.previous" />
				</c:if>
				<html:select styleId="resultsRangeComboBottom" 
				    name="${form.formName}" 
				    property="paginationData.selectedPage"
					onchange="javascript:submitPage(document.getElementById('resultsRangeComboBottom').value)">
	               <html:optionsCollection name="${form.formName}" 
	                   property="paginationData.comboPageRange"
						label="pageRange" value="pageIndex" />
				</html:select> 
				of <bean:write bundle="vinavidai" name="${form.formName}" property="paginationData.totalRecords" /> 
			    <c:if test="${form.paginationData.showNextPageFlag}">
					<html:link href="#" onclick="submitPageForHyperLink('ShowNext');">
						<bean:message bundle="vinavidai" key="common.link.next" />
					</html:link>
				</c:if> 
				<c:if test="${not form.paginationData.showNextPageFlag}">
					<bean:message bundle="vinavidai" key="common.link.next" />
				</c:if> 
			</span>
		</c:if>
	</td>
	<!-- End Pagination -->
</tr>
