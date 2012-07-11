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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.ahp.commons.form.AhpAbstractForm;
import org.ahp.vinavidai.pojo.Option;
import org.ahp.vinavidai.pojo.Quiz;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 */
public abstract class QuestionForm extends AhpAbstractForm {

	private static final long serialVersionUID = 1L;

	final static Logger LOGGER = LoggerFactory.getLogger(QuestionForm.class);

	private String mQuestionDescription;
	private String mQuestionObjective;
	private long mSelectedQuestionCategory;
	private long mSelectedQuestionSkillLevel;
	private int mQuestionPoints;
	private String mResponseDurationInHours;
	private String mResponseDurationInMinutes;
	private String mResponseDurationInSeconds;
	private List<Option> mOptions;
	private String mQuestionType;
	private Quiz mQuiz;

	/** Display **/
	private Set<String> mMaximumSizeTypeDisplaySet;
	private Set<String> mHoursDisplaySet;
	private Set<String> mMinutesDisplaySet;
	private Set<String> mSecondsDisplaySet;

	/** Hidden Fields **/
	private String mHiddenDeleteOptionIndex;

	// For Business Logic
	private String mQuestionOperationType;

	@Override
	protected void resetForm(ActionMapping pActionMapping,
			HttpServletRequest pHttpServletRequest) {
	}

	public String getQuestionDescription() {
		return mQuestionDescription;
	}

	public void setQuestionDescription(String pQuestionDescription) {
		mQuestionDescription = pQuestionDescription;
	}

	public String getQuestionObjective() {
		return mQuestionObjective;
	}

	public void setQuestionObjective(String pQuestionObjective) {
		mQuestionObjective = pQuestionObjective;
	}

	public long getSelectedQuestionCategory() {
		return mSelectedQuestionCategory;
	}

	public void setSelectedQuestionCategory(long pSelectedQuestionCategory) {
		mSelectedQuestionCategory = pSelectedQuestionCategory;
	}

	public long getSelectedQuestionSkillLevel() {
		return mSelectedQuestionSkillLevel;
	}

	public void setSelectedQuestionSkillLevel(long pSelectedQuestionSkillLevel) {
		mSelectedQuestionSkillLevel = pSelectedQuestionSkillLevel;
	}

	public String getResponseDurationInHours() {
		return mResponseDurationInHours;
	}

	public void setResponseDurationInHours(String pResponseDurationInHours) {
		mResponseDurationInHours = pResponseDurationInHours;
	}

	public String getResponseDurationInMinutes() {
		return mResponseDurationInMinutes;
	}

	public void setResponseDurationInMinutes(String pResponseDurationInMinutes) {
		mResponseDurationInMinutes = pResponseDurationInMinutes;
	}

	public String getResponseDurationInSeconds() {
		return mResponseDurationInSeconds;
	}

	public void setResponseDurationInSeconds(String pResponseDurationInSeconds) {
		mResponseDurationInSeconds = pResponseDurationInSeconds;
	}

	public List<Option> getOptions() {
		return mOptions;
	}

	public void setOptions(List<Option> pOptions) {
		mOptions = pOptions;
	}

	/**
	 * This is sort of LazyList implementation
	 * 
	 * @param pIndex
	 * @return
	 */
	public Option getOption(int pIndex) {
		if (this.getOptions() == null) {
			this.setOptions(new ArrayList<Option>());
		}
		while (pIndex >= this.getOptions().size()) {
			this.getOptions().add(new Option());
		}
		Option lOption = (Option) this.getOptions().get(pIndex);
		// return the requested item
		LOGGER.debug("pIndex : " + pIndex + " Option - ");// +
															// lOption.toString()
															// );
		return lOption;
	}

	public void setOption(int pIndex, Option pOption) {
		this.getOptions().add(pIndex, pOption);
	}

	public String getQuestionType() {
		return mQuestionType;
	}

	public void setQuestionType(String pQuestionType) {
		mQuestionType = pQuestionType;
	}

	public Set<String> getMaximumSizeTypeDisplaySet() {
		return mMaximumSizeTypeDisplaySet;
	}

	public void setMaximumSizeTypeDisplaySet(
			Set<String> pMaximumSizeTypeDisplaySet) {
		mMaximumSizeTypeDisplaySet = pMaximumSizeTypeDisplaySet;
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

	public String getHiddenDeleteOptionIndex() {
		return mHiddenDeleteOptionIndex;
	}

	public void setHiddenDeleteOptionIndex(String pHiddenDeleteOptionIndex) {
		mHiddenDeleteOptionIndex = pHiddenDeleteOptionIndex;
	}

	public Quiz getQuiz() {
		return mQuiz;
	}

	public void setQuiz(Quiz pQuiz) {
		mQuiz = pQuiz;
	}

	public int getQuestionPoints() {
		return mQuestionPoints;
	}

	public void setQuestionPoints(int pQuestionPoints) {
		mQuestionPoints = pQuestionPoints;
	}

	public String getQuestionOperationType() {
		return mQuestionOperationType;
	}

	public void setQuestionOperationType(String pQuestionOperationType) {
		mQuestionOperationType = pQuestionOperationType;
	}

}
