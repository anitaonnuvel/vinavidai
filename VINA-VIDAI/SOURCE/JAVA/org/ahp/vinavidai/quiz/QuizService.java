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
package org.ahp.vinavidai.quiz;

import java.util.List;

import org.ahp.core.businessdelegate.AhpBusinessDelegate;
import org.ahp.core.pojo.User;
import org.ahp.vinavidai.dao.IQuizDao;
import org.ahp.vinavidai.enums.Status;
import org.ahp.vinavidai.pojo.Category;
import org.ahp.vinavidai.pojo.Quiz;
import org.ahp.vinavidai.pojo.QuizGroup;
import org.ahp.vinavidai.pojo.SkillLevel;
import org.ahp.vinavidai.pojo.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Anita Onnuvel
 * 
 * @spring.bean id="quizService"
 * 
 * @spring.property name="quizDao" ref="quizDao"
 */
public class QuizService extends AhpBusinessDelegate {

	final static Logger LOGGER = LoggerFactory.getLogger(QuizService.class);

	private IQuizDao mQuizDao;

	/**
	 * 
	 * @param pUser
	 */
	public QuizService() {
	}

	/**
	 * 
	 * @param pUser
	 */
	public QuizService(User pUser) {
		super(pUser);
	}

	public void setQuizDao(IQuizDao pQuizDao) {
		mQuizDao = pQuizDao;
	}

	/**
	 * 
	 * @param pQuiz
	 */
	public void createQuiz(Quiz pQuiz) {
		if (pQuiz.getQuizStatus() == null) {
			pQuiz.setQuizStatus(Status.Disabled);
		}
		this.mQuizDao.createQuiz(pQuiz);
	}

	/**
	 * 
	 * @param pQuiz
	 */
	public Quiz loadQuiz(long pQuizId) {
		return this.loadQuiz(pQuizId, false);
	}

	/**
	 * 
	 * @param pQuiz
	 */
	public Quiz loadQuiz(long pQuizId, boolean pLoadAssociations) {
		Quiz lLoadedQuiz = this.mQuizDao.loadQuiz(pQuizId);
		return lLoadedQuiz;
	}

	/**
	 * 
	 * @param pQuiz
	 */
	public Quiz updateQuiz(Quiz pQuiz) {

		return this.mQuizDao.updateQuiz(pQuiz);
	}

	/**
	 * 
	 * @param pQuiz
	 */
	public void createQuizGroups(QuizGroup pQuizGroups) {
		this.mQuizDao.createQuizGroups(pQuizGroups);
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getAllCategoryNames(User pUser) {
		return this.mQuizDao.getAllCategoryNames(pUser);
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getAllSkillLevelNames(User pUser) {
		return this.mQuizDao.getAllSkillLevelNames(pUser);
	}

	/**
	 * 
	 * @return
	 */
	public void createPublishQuiz(Test pPublishQuiz) {
		this.mQuizDao.createPublishQuiz(pPublishQuiz);
	}

	/**
     * 
     */
	public Category getDefaultCategory(Quiz pQuiz) {
		return this.mQuizDao.getDefaultCategory(pQuiz);
	}

	/**
     * 
     */
	public SkillLevel getDefaultSkillLevel(Quiz pQuiz) {
		return this.mQuizDao.getDefaultSkillLevel(pQuiz);
	}
}
