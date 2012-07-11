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
package org.ahp.vinavidai.quiz.form;

import static org.ahp.vinavidai.constants.HttpRequestAttributeConstants.QUIZ_UNDER_PUBLISH;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.ahp.commons.form.AhpAbstractForm;
import org.ahp.vinavidai.enums.SubmitActions;
import org.ahp.vinavidai.pojo.Quiz;
import org.ahp.vinavidai.pojo.Test;
import org.ahp.vinavidai.validator.QuizValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 * @struts.form name="PublishQuizForm"
 */
public class PublishQuizForm extends AhpAbstractForm {

	private static final long serialVersionUID = 1L;

	final static Logger LOGGER = LoggerFactory.getLogger(PublishQuizForm.class);

	// Form Fields
	private String mTestName;
	private String mTestDescription;
	private String mTestDurationType;
	private String mFixedDurationHours;
	private String mFixedDurationMinutes;
	private String mQuizGroupName;
	private String mQuizGroupDescription;
	private String mTestAccessTimeType;
	private String mTestAccessStartTime;
	private String mTestAccessEndTime;
	private String mResponseDurationPerQuestionType;
	private String mResponseDurationPerQuestionHours;
	private String mResponseDurationPerQuestionMinutes;
	private String mResponseDurationPerQuestionSeconds;
	private String mTestPassPercentile;
	private String mSelectedListStyle;
	private String mSelectedDisplayStyle;
	private String mTestPublishUrl;

	// For Display
	private Set<String> mListStyleSet;
	private Set<String> mDisplayStyleSet;
	private Set<String> mHoursDisplaySet;
	private Set<String> mMinutesDisplaySet;
	private Set<String> mSecondsDisplaySet;

	// Business Logic
	private Quiz mQuizUnderPublish;
	private Test mTest;

	@Override
	protected void resetForm(ActionMapping pActionMapping,
			HttpServletRequest pHttpServletRequest) {
		this.setTestName(null);
		this.setTestDescription(null);
		this.setTestPassPercentile(null);
	}

	@Override
	public ActionErrors validate(ActionMapping pActionMapping,
			HttpServletRequest pHttpServletRequest) {
		ActionErrors lActionErrors = new ActionErrors();
		if (pHttpServletRequest.getAttribute(QUIZ_UNDER_PUBLISH) == null
				&& isSubmitAction(SubmitActions.PUBLISH_QUIZ)) {
			QuizValidator.validatePublishQuizForm(this, lActionErrors);
		}
		return lActionErrors;
	}

	public String getTestName() {
		return mTestName;
	}

	public void setTestName(String pTestName) {
		mTestName = pTestName;
	}

	public String getTestDescription() {
		return mTestDescription;
	}

	public void setTestDescription(String pTestDescription) {
		mTestDescription = pTestDescription;
	}

	public String getTestDurationType() {
		return mTestDurationType;
	}

	public void setTestDurationType(String pTestDurationType) {
		mTestDurationType = pTestDurationType;
	}

	public String getFixedDurationHours() {
		return mFixedDurationHours;
	}

	public void setFixedDurationHours(String pFixedDurationHours) {
		mFixedDurationHours = pFixedDurationHours;
	}

	public String getFixedDurationMinutes() {
		return mFixedDurationMinutes;
	}

	public void setFixedDurationMinutes(String pFixedDurationMinutes) {
		mFixedDurationMinutes = pFixedDurationMinutes;
	}

	public String getQuizGroupName() {
		return mQuizGroupName;
	}

	public void setQuizGroupName(String pQuizGroupName) {
		mQuizGroupName = pQuizGroupName;
	}

	public String getQuizGroupDescription() {
		return mQuizGroupDescription;
	}

	public void setQuizGroupDescription(String pQuizGroupDescription) {
		mQuizGroupDescription = pQuizGroupDescription;
	}

	public String getTestAccessTimeType() {
		return mTestAccessTimeType;
	}

	public void setTestAccessTimeType(String pTestAccessTimeType) {
		mTestAccessTimeType = pTestAccessTimeType;
	}

	public String getTestAccessStartTime() {
		return mTestAccessStartTime;
	}

	public void setTestAccessStartTime(String pTestAccessStartTime) {
		mTestAccessStartTime = pTestAccessStartTime;
	}

	public String getTestAccessEndTime() {
		return mTestAccessEndTime;
	}

	public void setTestAccessEndTime(String pTestAccessEndTime) {
		mTestAccessEndTime = pTestAccessEndTime;
	}

	public String getResponseDurationPerQuestionType() {
		return mResponseDurationPerQuestionType;
	}

	public void setResponseDurationPerQuestionType(
			String pResponseDurationPerQuestionType) {
		mResponseDurationPerQuestionType = pResponseDurationPerQuestionType;
	}

	public String getResponseDurationPerQuestionHours() {
		return mResponseDurationPerQuestionHours;
	}

	public void setResponseDurationPerQuestionHours(
			String pResponseDurationPerQuestionHours) {
		mResponseDurationPerQuestionHours = pResponseDurationPerQuestionHours;
	}

	public String getResponseDurationPerQuestionMinutes() {
		return mResponseDurationPerQuestionMinutes;
	}

	public void setResponseDurationPerQuestionMinutes(
			String pResponseDurationPerQuestionMinutes) {
		mResponseDurationPerQuestionMinutes = pResponseDurationPerQuestionMinutes;
	}

	public String getResponseDurationPerQuestionSeconds() {
		return mResponseDurationPerQuestionSeconds;
	}

	public void setResponseDurationPerQuestionSeconds(
			String pResponseDurationPerQuestionSeconds) {
		mResponseDurationPerQuestionSeconds = pResponseDurationPerQuestionSeconds;
	}

	public String getTestPassPercentile() {
		return mTestPassPercentile;
	}

	public void setTestPassPercentile(String pTestPassPercentile) {
		mTestPassPercentile = pTestPassPercentile;
	}

	public String getSelectedListStyle() {
		return mSelectedListStyle;
	}

	public void setSelectedListStyle(String pSelectedListStyle) {
		mSelectedListStyle = pSelectedListStyle;
	}

	public String getSelectedDisplayStyle() {
		return mSelectedDisplayStyle;
	}

	public void setSelectedDisplayStyle(String pSelectedDisplayStyle) {
		mSelectedDisplayStyle = pSelectedDisplayStyle;
	}

	public String getTestPublishUrl() {
		return mTestPublishUrl;
	}

	public void setTestPublishUrl(String pTestPublishUrl) {
		mTestPublishUrl = pTestPublishUrl;
	}

	public Set<String> getListStyleSet() {
		return mListStyleSet;
	}

	public void setListStyleSet(Set<String> pListStyleSet) {
		mListStyleSet = pListStyleSet;
	}

	public Set<String> getDisplayStyleSet() {
		return mDisplayStyleSet;
	}

	public void setDisplayStyleSet(Set<String> pDisplayStyleSet) {
		mDisplayStyleSet = pDisplayStyleSet;
	}

	public Set<String> getHoursDisplaySet() {
		return mHoursDisplaySet;
	}

	public void setHoursDisplaySet(Set<String> pHoursDisplaySet) {
		mHoursDisplaySet = pHoursDisplaySet;
	}

	public Set<String> getMinutesDisplaySet() {
		return mMinutesDisplaySet;
	}

	public void setMinutesDisplaySet(Set<String> pMinutesDisplaySet) {
		mMinutesDisplaySet = pMinutesDisplaySet;
	}

	public Set<String> getSecondsDisplaySet() {
		return mSecondsDisplaySet;
	}

	public void setSecondsDisplaySet(Set<String> pSecondsDisplaySet) {
		mSecondsDisplaySet = pSecondsDisplaySet;
	}

	public Quiz getQuizUnderPublish() {
		return mQuizUnderPublish;
	}

	public void setQuizUnderPublish(Quiz pQuizUnderPublish) {
		mQuizUnderPublish = pQuizUnderPublish;
	}

	public Test getTest() {
		return mTest;
	}

	public void setTest(Test pTest) {
		mTest = pTest;
	}

}
