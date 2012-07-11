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
package org.ahp.vinavidai.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.ahp.commons.util.AhpStringUtil;
import org.ahp.core.pojo.BaseEntity;
import org.ahp.vinavidai.enums.Status;

/**
 * 
 * @author Anita Onnuvel
 *
 */
@Entity
@Table(name = "TEST", schema = "VINAVIDAI")
public class Test extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mTestId;
	private String mTestName;
	private String mTestDescription;
	private Status mTestStatus;
	private Date mTestDuration;
	private Date mQuestionDuration;
	private Date mTestAccessStartTime;
	private Date mTestAccessEndTime;
	private Integer mTestPercentile;
	private String mTestAccessKey;
	private Set<TestConfig> mTestConfig;
	private Quiz mQuiz;

	@Id
	@GeneratedValue
	@Column(name = "TEST_ID")
	public Long getTestId() {
		return mTestId;
	}

	public void setTestId(Long pQuizId) {
		mTestId = pQuizId;
	}

	@OneToMany(targetEntity = org.ahp.vinavidai.pojo.TestConfig.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "test")
	public Set<TestConfig> getTestConfig() {
		return mTestConfig;
	}

	public void setTestConfig(Set<TestConfig> pPublishQuizConfig) {
		mTestConfig = pPublishQuizConfig;
	}

	@Column(name = "TEST_DURATION", nullable = true)
	@Temporal(TemporalType.TIME)
	public Date getTestDuration() {
		return mTestDuration;
	}

	public void setTestDuration(Date pQuizDuration) {
		mTestDuration = pQuizDuration;
	}

	@Column(name = "QUESTION_DURATION", nullable = true)
	@Temporal(TemporalType.TIME)
	public Date getQuestionDuration() {
		return mQuestionDuration;
	}

	public void setQuestionDuration(Date pQuestionDuration) {
		mQuestionDuration = pQuestionDuration;
	}

	@Column(name = "TEST_ACCESS_START_TIME", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTestAccessStartTime() {
		return mTestAccessStartTime;
	}

	public void setTestAccessStartTime(Date pQuizAccessStartTime) {
		mTestAccessStartTime = pQuizAccessStartTime;
	}

	@Column(name = "TEST_ACCESS_END_TIME", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTestAccessEndTime() {
		return mTestAccessEndTime;
	}

	public void setTestAccessEndTime(Date pQuizAccessEndTime) {
		mTestAccessEndTime = pQuizAccessEndTime;
	}

	@Column(name = "TEST_PASS_PERCENTILE", nullable = true)
	public Integer getTestPassPercentile() {
		return mTestPercentile;
	}

	public void setTestPassPercentile(Integer pPassPercentile) {
		mTestPercentile = pPassPercentile;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Quiz getQuiz() {
		return mQuiz;
	}

	public void setQuiz(Quiz pQuiz) {
		mQuiz = pQuiz;
	}

	@Column(name = "TEST_NAME", nullable = false)
	public String getTestName() {
		return mTestName;
	}

	public void setTestName(String pPublishQuizName) {
		mTestName = pPublishQuizName;
	}

	@Column(name = "TEST_DESCRIPTION", nullable = false)
	public String getTestDescription() {
		return mTestDescription;
	}

	public void setTestDescription(String pPublishQuizDescription) {
		mTestDescription = pPublishQuizDescription;
	}

	@Column(name = "TEST_STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	public Status getTestStatus() {
		return mTestStatus;
	}

	public void setTestStatus(Status pPublishQuizStatus) {
		mTestStatus = pPublishQuizStatus;
	}

	@Column(name = "TEST_ACCESS_KEY", nullable = true)
	public String getTestAccessKey() {
		return mTestAccessKey;
	}

	public void setTestAccessKey(String pTestAccessKey) {
		mTestAccessKey = pTestAccessKey;
	}

	/**
	 * @return String representation of the object
	 */
	public String toString() {
		return AhpStringUtil.reflectionToString(this);
	}
}