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

import org.ahp.commons.action.AhpAbstractProcessAction;
import org.ahp.core.actions.AhpActionHelper;
import org.ahp.core.pojo.User;
import org.ahp.vinavidai.enums.NavigateActions;
import org.ahp.vinavidai.quiz.form.CreateQuestionForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * @struts.action path="/ProcessCreateQuizConfirmation"
 *                name="CreateQuestionForm"
 *                input="/quiz/CreateQuizConfirmation.jsp" scope="session"
 *                validate="true"
 * 
 * @struts.action-forward name="DisplayCreateQuizConfirmation"
 *                        path="/DisplayCreateQuizConfirmation.do"
 *                        redirect="false"
 * 
 */
public class ProcessCreateQuizConfirmation extends AhpAbstractProcessAction {

    final static Logger LOGGER = LoggerFactory
            .getLogger( ProcessCreateQuizConfirmation.class );

    @Override
    public ActionForward process( ActionMapping pActionMapping,
            ActionForm pActionForm, HttpServletRequest pHttpServletRequest,
            HttpServletResponse pHttpServletResponse ) {
        User lLoggedInUser = AhpActionHelper
                .getLoggedInUser( pHttpServletRequest );
        CreateQuestionForm lCreateQuestionForm = ( CreateQuestionForm ) pActionForm;
        String lSubmitAction = StringUtils.trimToNull( lCreateQuestionForm
                .getSubmitAction() );
        lCreateQuestionForm
                .setNextPage( NavigateActions.DisplayCreateQuizConfirmation
                        .toString() );
        return pActionMapping
                .findForward( NavigateActions.DisplayCreateQuizConfirmation
                        .toString() );
    }
}