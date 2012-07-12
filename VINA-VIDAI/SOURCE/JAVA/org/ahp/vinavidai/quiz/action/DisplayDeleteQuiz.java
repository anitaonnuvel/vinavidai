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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahp.commons.action.AhpAbstractDisplayAction;
import org.ahp.core.actions.AhpActionHelper;
import org.ahp.core.pojo.User;
import org.ahp.vinavidai.enums.NavigateActions;
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
 * @struts.action path="/DisplayDeleteQuiz" name="ManageQuizForm"
 *                scope="session" validate="false"
 * 
 * @struts.action-forward name="DisplayDeleteQuiz"
 *                        path="/quiz/manage/ViewDeleteQuiz.jsp"
 * 
 * @struts.action-forward name="DisplayManageQuiz" path="/ManageQuiz.do"
 * 
 */
public class DisplayDeleteQuiz extends AhpAbstractDisplayAction {

    final static Logger LOGGER = LoggerFactory
            .getLogger( DisplayDeleteQuiz.class );

    @Override
    public ActionForward display( ActionMapping pActionMapping,
            ActionForm pActionForm, HttpServletRequest pHttpServletRequest,
            HttpServletResponse pHttpServletResponse ) {
        User lLoggedInUser = AhpActionHelper
                .getLoggedInUser( pHttpServletRequest );
        ManageQuizForm lManageQuizForm = ( ManageQuizForm ) pActionForm;
        lManageQuizForm.setDeleteQuiz( true );
        String lNextPage = StringUtils.trimToEmpty( lManageQuizForm
                .getNextPage() );
        ActionForward lActionForward = pActionMapping
                .findForward( NavigateActions.DisplayDeleteQuiz.toString() );
        if ( NavigateActions.DisplayManageQuiz.toString().equals( lNextPage ) ) {
            lActionForward = pActionMapping
                    .findForward( NavigateActions.DisplayManageQuiz.toString() );
        }
        if ( NavigateActions.DisplayDeleteQuiz.toString().equals( lNextPage ) ) {
            for ( Quiz lViewQuiz : lManageQuizForm.getQuizResults() ) {
                if ( lViewQuiz.getQuizId().longValue() == ( lManageQuizForm
                        .getSelectedQuizId().longValue() ) ) {
                    lManageQuizForm.setSelectedQuiz( lViewQuiz );
                    break;
                }
            }
            lActionForward = pActionMapping
                    .findForward( NavigateActions.DisplayDeleteQuiz.toString() );
        }
        return lActionForward;
    }
}
