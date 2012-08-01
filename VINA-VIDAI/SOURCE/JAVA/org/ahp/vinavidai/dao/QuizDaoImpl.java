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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.ahp.core.pojo.User;
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
 * @spring.bean id="quizDao"
 * 
 */
public class QuizDaoImpl implements IQuizDao {

    final static Logger LOGGER = LoggerFactory.getLogger( QuizDaoImpl.class );

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager mEntityManager;

    final String GET_ALL_CATEGORIES_BY_USER = " select category.category from category, quiz, user "
            + " where user.login_name = ? "
            + " and quiz.user_user_id = user.user_id "
            + " and category.quiz_id = quiz.quiz_id ";

    final String GET_ALL_SKILL_LEVELS_BY_USER = " select skill_level.skill_level from skill_level, quiz, user "
            + " where user.login_name = ? "
            + " and quiz.user_user_id = user.user_id "
            + " and skill_level.quiz_id = quiz.quiz_id ";

    /**
     * @return pQuiz
     */
    public void createQuiz( Quiz pQuiz ) {
        mEntityManager.persist( pQuiz );
    }

    /**
	 *
	 */
    public Quiz loadQuiz( Long pQuizId ) {
        Quiz lQuiz = ( Quiz ) mEntityManager.find(
                org.ahp.vinavidai.pojo.Quiz.class, pQuizId );
        mEntityManager.refresh( lQuiz );
        lQuiz.getCategories();
        lQuiz.getSkillLevels();
        lQuiz.getQuestions();
        lQuiz.getTest();
        return lQuiz;

    }

    public Quiz updateQuiz( Quiz pDetatchedQuiz ) {
        // Quiz lManagedQuiz = loadQuiz( pDetatchedQuiz.getQuizId());
        // lManagedQuiz.setQuizName( pDetatchedQuiz.getQuizName() );
        // lManagedQuiz.setQuizDescription(pDetatchedQuiz.getQuizDescription());
        // lManagedQuiz.setQuizName( pDetatchedQuiz.getQuizName() );
        // lManagedQuiz.getCategories().clear();
        // Set<Category> categoryList = new HashSet<Category>();
        // categoryList.addAll( pDetatchedQuiz.getCategories());
        // lManagedQuiz.setCategories( categoryList );
        // lManagedQuiz.getSkillLevels().clear();
        // Set<SkillLevel> skillList = new HashSet<SkillLevel>();
        // skillList.addAll( pDetatchedQuiz.getSkillLevels());
        // lManagedQuiz.setSkillLevels( skillList );
        return mEntityManager.merge( pDetatchedQuiz );
    }

    public void deleteQuiz( Quiz pQuiz ) {
        mEntityManager.remove( pQuiz );
    }

    public List<Quiz> loadQuizByUser( User pUser ) {
        /*
         * Quiz lQuiz = ( Quiz ) mEntityManager.createQuery( "" ).find(
         * org.vv.pojo.Quiz.class, new Long( "1" ) ); System.out.println( lQuiz
         * );
         */
        return null;
    }

    public List<String> getAllCategoryNames( Quiz pQuiz ) {
        Query lQuery = mEntityManager
                .createQuery( "SELECT DISTINCT categoryBean.category FROM Category categoryBean where categoryBean.quizId = ?1 ORDER BY categoryBean.category ASC" );
        lQuery.setParameter( 1, pQuiz.getQuizId() );
        List<String> lCategoryList = new ArrayList<String>();
        List lResultList = lQuery.getResultList();
        if ( lResultList.size() != 0 ) {
            Iterator lResultListIterator = lResultList.iterator();
            while ( lResultListIterator.hasNext() ) {
                lCategoryList.add( ( String ) lResultListIterator.next() );
            }
        }
        return lCategoryList;
    }

    public List<String> getAllSkillLevelNames( Quiz pQuiz ) {
        Query lQuery = mEntityManager
                .createQuery( "SELECT DISTINCT skillLevelBean.skillLevel FROM SkillLevel skillLevelBean where skillLevelBean.quizId = ?1 ORDER BY skillLevelBean.skillLevel ASC" );
        lQuery.setParameter( 1, pQuiz.getQuizId() );
        List<String> lSkillLevelList = new ArrayList<String>();
        List lResultList = lQuery.getResultList();
        if ( lResultList.size() != 0 ) {
            Iterator lResultListIterator = lResultList.iterator();
            while ( lResultListIterator.hasNext() ) {
                lSkillLevelList.add( ( String ) lResultListIterator.next() );
            }
        }
        return lSkillLevelList;
    }

    /**
     * 
     * @return
     */
    public List<String> getAllCategoryNames( User pUser ) {
        Query lQuery = mEntityManager
                .createNativeQuery( GET_ALL_CATEGORIES_BY_USER );
        lQuery.setParameter( 1, pUser.getLoginName() );
        List lResultList = lQuery.getResultList();
        List<String> lCategoryList = new ArrayList<String>();
        if ( lResultList.size() != 0 ) {
            Iterator lResultListIterator = lResultList.iterator();
            while ( lResultListIterator.hasNext() ) {
                lCategoryList.add( ( String ) lResultListIterator.next() );
            }
        }
        return lCategoryList;
    }

    /**
     * 
     * @return
     */
    public List<String> getAllSkillLevelNames( User pUser ) {
        Query lQuery = mEntityManager
                .createNativeQuery( GET_ALL_SKILL_LEVELS_BY_USER );
        lQuery.setParameter( 1, pUser.getLoginName() );
        List<String> lSkillLevelList = new ArrayList<String>();
        List lResultList = lQuery.getResultList();
        if ( lResultList.size() != 0 ) {
            Iterator lResultListIterator = lResultList.iterator();
            while ( lResultListIterator.hasNext() ) {
                lSkillLevelList.add( ( String ) lResultListIterator.next() );
            }
        }
        return lSkillLevelList;
    }

    public List<Category> getAllCategories( Quiz pQuiz ) {
        Query lQuery = mEntityManager
                .createQuery( "SELECT categoryBean FROM Category categoryBean ORDER BY categoryBean.category ASC" );
        List<Category> lCategoryList = new ArrayList<Category>();
        List lResultList = lQuery.getResultList();
        if ( lResultList.size() != 0 ) {
            Iterator lResultListIterator = lResultList.iterator();
            while ( lResultListIterator.hasNext() ) {
                lCategoryList.add( ( Category ) lResultListIterator.next() );
            }
        }
        return lCategoryList;
    }

    public List<SkillLevel> getAllSkillLevels( Quiz pQuiz ) {
        Query lQuery = mEntityManager
                .createQuery( "SELECT skillLevelBean FROM SkillLevel skillLevelBean ORDER BY skillLevelBean.skillLevel ASC" );
        List<SkillLevel> lSkillLevelList = new ArrayList<SkillLevel>();
        List lResultList = lQuery.getResultList();
        if ( lResultList.size() != 0 ) {
            Iterator lResultListIterator = lResultList.iterator();
            while ( lResultListIterator.hasNext() ) {
                lSkillLevelList.add( ( SkillLevel ) lResultListIterator.next() );
            }
        }
        return lSkillLevelList;
    }

    /**
     *
     */
    public void createQuizGroups( QuizGroup pQuizGroups ) {
        mEntityManager.persist( pQuizGroups );
    }

    /**
     *
     */
    public void createPublishQuiz( Test pTest ) {
        mEntityManager.persist( pTest );
    }

    /**
     * 
     */
    public Category getDefaultCategory( Quiz pQuiz ) {
        Query lQuery = mEntityManager
                .createQuery( "SELECT categoryBean FROM Category categoryBean WHERE categoryBean.quiz.quizId = ?1 AND categoryBean.category = 'default'" );
        lQuery.setParameter( 1, pQuiz.getQuizId() );
        Category lCategory = ( Category ) lQuery.getSingleResult();
        return lCategory;
    }

    /**
     * 
     */
    public SkillLevel getDefaultSkillLevel( Quiz pQuiz ) {
        Query lQuery = mEntityManager
                .createQuery( "SELECT skillLevelBean FROM SkillLevel skillLevelBean WHERE skillLevelBean.quiz.quizId = ?1 AND skillLevelBean.skillLevel = 'default'" );
        lQuery.setParameter( 1, pQuiz.getQuizId() );
        SkillLevel lSkillLevel = ( SkillLevel ) lQuery.getSingleResult();
        return lSkillLevel;
    }

}
