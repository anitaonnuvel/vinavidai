/*
 * Copyright 2012 Anita Onnuvel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ahp.vinavidai.quiz.action;

import static org.ahp.vinavidai.constants.HttpRequestAttributeConstants.QUIZ_UNDER_DELETE;
import static org.ahp.vinavidai.constants.HttpRequestAttributeConstants.QUIZ_UNDER_EDIT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahp.commons.action.AhpAbstractDisplayAction;
import org.ahp.vinavidai.enums.NavigateActions;
import org.ahp.vinavidai.enums.PaginationOperations;
import org.ahp.vinavidai.enums.SubmitActions;
import org.ahp.vinavidai.pojo.Quiz;
import org.ahp.vinavidai.quiz.manage.form.ManageQuizForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * @struts.action path="/DisplayViewQuiz" name="ManageQuizForm" scope="session"
 *                validate="false"
 * 
 * @struts.action-forward name="DisplayViewQuiz"
 *                        path="/quiz/manage/ViewDeleteQuiz.jsp"
 * 
 * @struts.action-forward name="DisplayManageQuiz" path="/ManageQuiz.do"
 * 
 * @struts.action-forward name="DisplayEditQuiz" path="/ProcessEditQuiz.do"
 * 
 * @struts.action-forward name="DisplayDeleteQuiz" path="/ProcessDeleteQuiz.do"
 * 
 */
public class DisplayViewQuiz extends AhpAbstractDisplayAction {

    final static Logger LOGGER = LoggerFactory
            .getLogger( DisplayViewQuiz.class );

    @Override
    public ActionForward display( ActionMapping pActionMapping,
            ActionForm pActionForm, HttpServletRequest pHttpServletRequest,
            HttpServletResponse pHttpServletResponse ) {
        ManageQuizForm lManageQuizForm = ( ManageQuizForm ) pActionForm;
        ActionForward lActionForward = pActionMapping
                .findForward( NavigateActions.DisplayViewQuiz.toString() );
        String lSubmitAction = StringUtils.trimToEmpty( lManageQuizForm
                .getSubmitAction() );
        String lHiddenEditOperation = StringUtils.trimToEmpty( lManageQuizForm
                .getHiddenEditOperation() );
        if ( lManageQuizForm
                .isSubmitAction( SubmitActions.RETURN_TO_MANAGE_QUIZ ) ) {
            lManageQuizForm.getPaginationData().setOperation(
                    PaginationOperations.ComboSubmit.toString() );
            lManageQuizForm.getPaginationData().setHiddenSelectedComboValue(
                    lManageQuizForm.getPaginationData().getSelectedPage() );
            lActionForward = pActionMapping
                    .findForward( NavigateActions.DisplayManageQuiz.toString() );
        }
        if ( lManageQuizForm.isSubmitAction( SubmitActions.EDIT ) ) {
            lActionForward = pActionMapping
                    .findForward( NavigateActions.DisplayEditQuiz.toString() );
            pHttpServletRequest.setAttribute( QUIZ_UNDER_EDIT,
                    lManageQuizForm.getSelectedQuiz() );
        }
        if ( lManageQuizForm.isSubmitAction( SubmitActions.DELETE ) ) {
            pHttpServletRequest.setAttribute( QUIZ_UNDER_DELETE,
                    lManageQuizForm.getSelectedQuiz() );
            lActionForward = pActionMapping
                    .findForward( NavigateActions.DisplayDeleteQuiz.toString() );
        }
        if ( lHiddenEditOperation.equals( "View Quiz" ) ) {
            for ( Quiz lViewQuiz : lManageQuizForm.getQuizResults() ) {
                if ( lViewQuiz.getQuizId().longValue() == ( lManageQuizForm
                        .getHiddenSelectedItemId() ) ) {
                    lManageQuizForm.setSelectedQuiz( lViewQuiz );
                    break;
                }
            }
        }
        lManageQuizForm.setHiddenEditOperation( null );
        lManageQuizForm.setHiddenSelectedItemId( 0 );
        return lActionForward;
    }
}
