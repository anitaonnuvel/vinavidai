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

import static org.ahp.vinavidai.constants.VinaVidaiConstants.DEFAULT_PASS_PERCENTILE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahp.commons.action.AhpAbstractDisplayAction;
import org.ahp.commons.util.AhpTimeUtil;
import org.ahp.vinavidai.enums.DisplayStyle;
import org.ahp.vinavidai.enums.DurationType;
import org.ahp.vinavidai.enums.EnumWrapper;
import org.ahp.vinavidai.enums.ListStyle;
import org.ahp.vinavidai.enums.NavigateActions;
import org.ahp.vinavidai.quiz.QuizService;
import org.ahp.vinavidai.quiz.form.PublishQuizForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * @struts.action path="/DisplayPublishQuiz" name="PublishQuizForm"
 *                scope="session" validate="false"
 * 
 * @struts.action-forward name="DisplayPublishQuiz" path="/quiz/PublishQuiz.jsp"
 * 
 * @struts.action-forward name="DisplayPublishQuizConfirmation"
 *                        path="/quiz/PublishQuizConfirmation.jsp"
 * 
 * @spring.bean name="/DisplayPublishQuiz"
 * 
 * @spring.property name="quizService" ref="quizService"
 * 
 */
public class DisplayPublishQuiz extends AhpAbstractDisplayAction {

	final static Logger LOGGER = LoggerFactory
			.getLogger(DisplayPublishQuiz.class);

	private QuizService mQuizService;

	public void setQuizService(QuizService pQuizService) {
		this.mQuizService = pQuizService;
	}

	@Override
	public ActionForward display(ActionMapping pActionMapping,
			ActionForm pActionForm, HttpServletRequest pHttpServletRequest,
			HttpServletResponse pHttpServletResponse) {
		PublishQuizForm lPublishQuizForm = (PublishQuizForm) pActionForm;
		ActionForward lActionForward = pActionMapping
				.findForward(NavigateActions.DisplayPublishQuiz.toString());
		if (lPublishQuizForm.isNextPage(NavigateActions.DisplayPublishQuiz)) {
			lPublishQuizForm.setHoursDisplaySet(AhpTimeUtil.getHourInDay());
			lPublishQuizForm.setMinutesDisplaySet(AhpTimeUtil.getMinutes());
			lPublishQuizForm.setSecondsDisplaySet(AhpTimeUtil.getSeconds());
			lPublishQuizForm.setListStyleSet(EnumWrapper.enumerateListStyle());
			lPublishQuizForm.setDisplayStyleSet(EnumWrapper
					.enumerateDisplayStyle());
			lPublishQuizForm.setTestDurationType(DurationType.Indefinite
					.toString());
			lPublishQuizForm
					.setResponseDurationPerQuestionType(DurationType.Indefinite
							.toString());
			lPublishQuizForm.setTestAccessTimeType(DurationType.Indefinite
					.toString());
			lPublishQuizForm.setSelectedListStyle(ListStyle.Numeric.toString());
			lPublishQuizForm.setSelectedDisplayStyle(DisplayStyle.Dot
					.toString());
			lPublishQuizForm
					.setTestPassPercentile("" + DEFAULT_PASS_PERCENTILE);
			lActionForward = pActionMapping
					.findForward(NavigateActions.DisplayPublishQuiz.toString());
		}
		if (lPublishQuizForm.isNextPage(NavigateActions.DisplayPublishQuiz)) {
			lActionForward = pActionMapping
					.findForward(NavigateActions.DisplayPublishQuiz.toString());
		}
		if (lPublishQuizForm
				.isNextPage(NavigateActions.DisplayPublishQuizConfirmation)) {
			lActionForward = pActionMapping
					.findForward(NavigateActions.DisplayPublishQuizConfirmation
							.toString());
		}
		return lActionForward;
	}
}
