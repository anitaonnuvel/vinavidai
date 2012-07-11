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

import static org.ahp.vinavidai.constants.HttpRequestAttributeConstants.QUIZ_UNDER_PUBLISH;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahp.commons.action.AhpAbstractProcessAction;
import org.ahp.commons.securiy.messagedigest.AhpMessageDigest;
import org.ahp.core.actions.AhpActionHelper;
import org.ahp.core.businessdelegate.AhpBusinessDelegate;
import org.ahp.core.pojo.Audit;
import org.ahp.core.pojo.User;
import org.ahp.vinavidai.enums.DurationType;
import org.ahp.vinavidai.enums.NavigateActions;
import org.ahp.vinavidai.enums.QuizConfigEnum;
import org.ahp.vinavidai.enums.Status;
import org.ahp.vinavidai.enums.SubmitActions;
import org.ahp.vinavidai.pojo.Quiz;
import org.ahp.vinavidai.pojo.Test;
import org.ahp.vinavidai.pojo.TestConfig;
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
 * @struts.action path="/ProcessPublishQuiz" name="PublishQuizForm"
 *                input="/quiz/PublishQuiz.jsp" scope="session" validate="true"
 * 
 * @struts.action-forward name="DisplayPublishQuiz"
 *                        path="/DisplayPublishQuiz.do" redirect="false"
 * 
 * @spring.bean name="/ProcessPublishQuiz"
 * 
 * @spring.property name="quizService" ref="quizService"
 * 
 */
public class ProcessPublishQuiz extends AhpAbstractProcessAction {

	final static Logger LOGGER = LoggerFactory
			.getLogger(ProcessPublishQuiz.class);

	private QuizService mQuizService;

	public void setQuizService(QuizService pQuizService) {
		this.mQuizService = pQuizService;
	}

	@Override
	public ActionForward process(ActionMapping pActionMapping,
			ActionForm pActionForm, HttpServletRequest pHttpServletRequest,
			HttpServletResponse pHttpServletResponse) {
		User lLoggedInUser = AhpActionHelper
				.getLoggedInUser(pHttpServletRequest);
		PublishQuizForm lPublishQuizForm = (PublishQuizForm) pActionForm;
		Quiz lQuizUnderPublish = (Quiz) (pHttpServletRequest
				.getAttribute(QUIZ_UNDER_PUBLISH));
		if (lQuizUnderPublish != null) {
			lPublishQuizForm.setNextPage(NavigateActions.DisplayPublishQuiz
					.toString());
			lPublishQuizForm.setQuizUnderPublish(lQuizUnderPublish);
		} else if (lPublishQuizForm.isSubmitAction(SubmitActions.PUBLISH_QUIZ)) {
			Test lTest = this.storePublishQuiz(lPublishQuizForm, lLoggedInUser);
			try {
				lPublishQuizForm
						.setTestPublishUrl("http://localhost:8080/vinavidai/TakeTest.do?accessKey="
								+ URLEncoder.encode(lTest.getTestAccessKey(),
										"UTF-8"));
			} catch (UnsupportedEncodingException exUnsupportedEncoding) {
				LOGGER.error("", exUnsupportedEncoding);
			}
			lPublishQuizForm
					.setNextPage(NavigateActions.DisplayPublishQuizConfirmation
							.toString());
		} else {
			lPublishQuizForm.setNextPage(NavigateActions.DisplayPublishQuiz
					.toString());
		}
		return pActionMapping.findForward(NavigateActions.DisplayPublishQuiz
				.toString());
	}

	/**
	 * 
	 * @param pPublishQuizForm
	 * @return
	 * @throws Exception
	 */
	private Test storePublishQuiz(PublishQuizForm pPublishQuizForm,
			User pLoggedInUser) {
		Audit lAudit = AhpBusinessDelegate.createAudit(pLoggedInUser);
		Test lTest = new Test();
		lTest.setTestName(pPublishQuizForm.getTestName());
		lTest.setTestDescription(pPublishQuizForm.getTestDescription());
		if (pPublishQuizForm.getTestDurationType().equals(
				DurationType.Timed.toString())) {
			Calendar lQuizDurationCalendar = Calendar.getInstance();
			lQuizDurationCalendar.set(Calendar.HOUR_OF_DAY,
					Integer.parseInt(pPublishQuizForm.getFixedDurationHours()));
			lQuizDurationCalendar.set(Calendar.MINUTE, Integer
					.parseInt(pPublishQuizForm.getFixedDurationMinutes()));
			lTest.setTestDuration(lQuizDurationCalendar.getTime());
		} else {
			lTest.setTestDuration(null);
		}
		if (pPublishQuizForm.getResponseDurationPerQuestionType().equals(
				DurationType.Timed.toString())) {
			Calendar lQuestionDurationCalendar = Calendar.getInstance();
			lQuestionDurationCalendar.set(Calendar.HOUR_OF_DAY, Integer
					.parseInt(pPublishQuizForm
							.getResponseDurationPerQuestionHours()));
			lQuestionDurationCalendar.set(Calendar.MINUTE, Integer
					.parseInt(pPublishQuizForm
							.getResponseDurationPerQuestionMinutes()));
			lQuestionDurationCalendar.set(Calendar.SECOND, Integer
					.parseInt(pPublishQuizForm
							.getResponseDurationPerQuestionSeconds()));
			lTest.setQuestionDuration(lQuestionDurationCalendar.getTime());
		} else {
			lTest.setQuestionDuration(null);
		}
		if (pPublishQuizForm.getTestAccessTimeType().equals(
				DurationType.Timed.toString())) {
			SimpleDateFormat lDateTimeFormatter = new SimpleDateFormat(
					"MM/dd/yyyy H:m");
			try {
				lTest.setTestAccessStartTime(lDateTimeFormatter
						.parse(pPublishQuizForm.getTestAccessStartTime()));
				lTest.setTestAccessEndTime(lDateTimeFormatter
						.parse(pPublishQuizForm.getTestAccessEndTime()));
			} catch (ParseException exParse) {
				throw new RuntimeException();
			}
		}
		lTest.setTestPassPercentile(Integer.parseInt(pPublishQuizForm
				.getTestPassPercentile()));
		lTest.setTestStatus(Status.Enabled);
		lTest.setAudit(lAudit);

		// All optional fields go to QuizConfig
		Set<TestConfig> lQuizConfigSet = new LinkedHashSet<TestConfig>();

		TestConfig lQuestionListStyleConfig = new TestConfig();
		lQuestionListStyleConfig.setConfigName(QuizConfigEnum.QuestionListStyle
				.toString());
		lQuestionListStyleConfig.setConfigValue(pPublishQuizForm
				.getSelectedListStyle());
		lQuestionListStyleConfig.setAudit(lAudit);
		lQuestionListStyleConfig.setTest(lTest);
		lQuizConfigSet.add(lQuestionListStyleConfig);

		TestConfig lQuestionDisplayStyleConfig = new TestConfig();
		lQuestionDisplayStyleConfig
				.setConfigName(QuizConfigEnum.QuestionDisplayStyle.toString());
		lQuestionDisplayStyleConfig.setConfigValue(pPublishQuizForm
				.getSelectedDisplayStyle());
		lQuestionDisplayStyleConfig.setAudit(lAudit);
		lQuestionDisplayStyleConfig.setTest(lTest);
		lQuizConfigSet.add(lQuestionDisplayStyleConfig);

		String lTestAccessKey = AhpMessageDigest.createDigest(lTest
				.getTestName());
		lTest.setTestConfig(lQuizConfigSet);
		lTest.setTestAccessKey(lTestAccessKey);

		this.mQuizService.createPublishQuiz(lTest);
		pPublishQuizForm.setTest(lTest);
		LOGGER.debug("Publishd Quiz with Id: " + lTest.getTestId());
		return lTest;
	}

}