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
package org.ahp.vinavidai.dao;

import java.util.List;

import org.ahp.core.pojo.User;
import org.ahp.vinavidai.pojo.Category;
import org.ahp.vinavidai.pojo.Quiz;
import org.ahp.vinavidai.pojo.QuizGroup;
import org.ahp.vinavidai.pojo.SkillLevel;
import org.ahp.vinavidai.pojo.Test;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public interface IQuizDao {

    /**
     * 
     * @param pQuiz
     */
    public void createQuiz( Quiz pQuiz );

    /**
     * 
     * @param pQuizId
     * @return
     */
    public Quiz loadQuiz( Long pQuizId );

    /**
     * 
     * @param pQuiz
     */
    public Quiz updateQuiz( Quiz pQuiz );

    /**
     * 
     * @param pQuiz
     */
    public void deleteQuiz( Quiz pQuiz );

    /**
     * 
     * @return
     */
    public List<String> getAllCategoryNames( User pUser );

    /**
     * 
     * @return
     */
    public List<String> getAllSkillLevelNames( User pUser );

    /**
     * 
     * @param pQuizGroups
     */
    public void createQuizGroups( QuizGroup pQuizGroups );

    /**
     * 
     * @param pQuizGroups
     */
    public void createPublishQuiz( Test pPublishQuiz );

    /**
     * 
     * @param pQuiz
     * @return
     */
    public Category getDefaultCategory( Quiz pQuiz );

    /**
     * 
     * @param pQuiz
     * @return
     */
    public SkillLevel getDefaultSkillLevel( Quiz pQuiz );

}
