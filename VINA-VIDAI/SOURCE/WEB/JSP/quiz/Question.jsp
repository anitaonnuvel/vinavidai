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
        <link rel="stylesheet" type="text/css" href="<c:url value='${styleSheet}'/>" />
        <%@ include file="/common/JQueryUi.jsp"%>
        <%@ include file="/common/JQueryUiVerticalTabs.jsp"%>
        <title>VinaVidai - Create Question - ${sessionScope.LOGGED_IN_USER.loginName}</title>
        <script type="text/javascript">
            $(document).ready(function() {
                var $tabs = $("#tabs").tabs({cookie:{}}).addClass('ui-tabs-vertical ui-helper-clearfix');
                $("#tabs li").removeClass('ui-corner-top').addClass('ui-corner-left');
                //$( "#tabs" ).tabs({cookie:{expires:1}});
            });
            $(document).ready(function() {
                $("#ordering").sortable();
                $("#sortable1").sortable();
                $("#sortable2").sortable();
            });
        </script>
    </head>
    <body>
        <%@ include file="/common/Header.jsp" %>
        <div id="rootContainer">
            <div id="bodyContent">
                <h3>Create Question</h3>
                <h1>Quiz Name: <bean:write name="${form.formName}" property="quiz.quizName"/></h1>                    
                    <html:errors bundle="vinavidai"></html:errors>
                    <logic:notEmpty name="${form.formName}" property="quiz.questions">
                        <bean:size id="questionSize" property="quiz.questions" name="${form.formName}"/>
                    </logic:notEmpty>
                    <bean:size id="optionsSize" property="options" name="${form.formName}"/>
                    <bean:size id="categorySize" property="quiz.categories" name="${form.formName}"/>
                    <bean:size id="skillLevelSize" property="quiz.skillLevels" name="${form.formName}"/>
                    <div id="tabs">
                        <ul>
                            <li><a href="#MultipleChoice"><span>Multiple Choice</span><br/><span style="text-align:center;">or</span><br/><span>Multiple Options</span></a></li>
                            <li><a href="#TrueOrFalse">True or False</a></li>
                            <li><a href="#FillInTheBlanks">Fill in the blanks</a></li>
                            <li><a href="#WordList">WordList</a></li>
                            <li><a href="#Matching">Matching</a></li>
                            <li><a href="#Ordering">Ordering</a></li>
                            <li><a href="#Descriptive">Descriptive</a></li>
                        </ul>                        
                        <div id="MultipleChoice">
                            <%@ include file="/quiz/questionTypes/MultipleChoice.jsp"%>
                        </div>
                        <div id="TrueOrFalse">
                            <%@ include file="/quiz/questionTypes/TrueOrFalse.jsp"%>
                        </div>
                        <div id="FillInTheBlanks">
                            <%@ include file="/quiz/questionTypes/FillInTheBlanks.jsp"%>
                        </div>
                        <div id="WordList">
                            <%@ include file="/quiz/questionTypes/WordList.jsp"%>
                        </div>
                        <div id="Matching">
                            <%@ include file="/quiz/questionTypes/Matching.jsp"%>
                        </div>
                        <div id="Ordering">
                            <%@ include file="/quiz/questionTypes/Ordering.jsp"%>
                        </div>
                        <div id="Descriptive">
                            <%@ include file="/quiz/questionTypes/Descriptive.jsp"%>
                        </div>
                    </div>                 
            </div>
        </div>
    <%@ include file="/common/Footer.jsp" %>
    </body>
</html>