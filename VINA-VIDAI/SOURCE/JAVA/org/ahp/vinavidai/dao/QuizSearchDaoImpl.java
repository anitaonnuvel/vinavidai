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

import org.ahp.vinavidai.enums.SortOrderAscDesc;
import org.ahp.vinavidai.pojo.Quiz;
import org.ahp.vinavidai.quiz.manage.ManageQuizSearchCriteria;
import org.ahp.vinavidai.quiz.manage.ManageQuizSearchResults;
import org.ahp.vinavidai.quiz.manage.ManageQuizSortByParams;

/**
 * 
 * @author Anita Onnuvel
 * 
 * @spring.bean id="quizSearchDao"
 * 
 */
public class QuizSearchDaoImpl implements IQuizSearchDao {

    @PersistenceContext( type = PersistenceContextType.TRANSACTION )
    private EntityManager mEntityManager;

    private static final String SEARCH_QUIZ = "SELECT quizBean FROM Quiz quizBean where quizBean.user.userId = ?1 ORDER BY ";
    private static final String SEARCH_QUIZ_COUNT = "SELECT COUNT(quizBean) FROM Quiz quizBean where quizBean.user.userId = ?1 ";

    @Override
    public ManageQuizSearchResults searchQuiz(
            ManageQuizSearchCriteria pManageQuizSearchCriteria ) {
        ManageQuizSearchResults lManageQuizSearchResults = new ManageQuizSearchResults();
        StringBuilder lDataQueryStr = new StringBuilder();
        StringBuilder lCountQueryStr = new StringBuilder();
        lDataQueryStr.append( SEARCH_QUIZ );
        lCountQueryStr.append( SEARCH_QUIZ_COUNT );
        if ( pManageQuizSearchCriteria.getOrderByParam().equals(
                ManageQuizSortByParams.QuizName ) ) {
            lDataQueryStr.append( " quizBean.quizName " );
        }
        if ( pManageQuizSearchCriteria.getOrderByParam().equals(
                ManageQuizSortByParams.CreatedDate ) ) {
            lDataQueryStr.append( " quizBean.audit.createdDate " );
        }
        if ( pManageQuizSearchCriteria.getOrderBy().equals(
                SortOrderAscDesc.Ascending ) ) {
            lDataQueryStr.append( " ASC " );
        } else {
            lDataQueryStr.append( " DESC " );
        }
        Query lDataQuery = mEntityManager
                .createQuery( lDataQueryStr.toString() );
        lDataQuery.setParameter( 1, pManageQuizSearchCriteria.getUser()
                .getUserId() );
        lDataQuery.setFirstResult( pManageQuizSearchCriteria.getStartIndex() );
        lDataQuery.setMaxResults( pManageQuizSearchCriteria.getEndIndex() );
        List<Quiz> lQuizList = new ArrayList<Quiz>();
        List lResultList = lDataQuery.getResultList();
        if ( lResultList.size() != 0 ) {
            Iterator lResultListIterator = lResultList.iterator();
            while ( lResultListIterator.hasNext() ) {
                lQuizList.add( ( Quiz ) lResultListIterator.next() );
            }
        }
        Query lCountQuery = mEntityManager.createQuery( lCountQueryStr
                .toString() );
        lCountQuery.setParameter( 1, pManageQuizSearchCriteria.getUser()
                .getUserId() );
        Long lTotalRecords = ( Long ) lCountQuery.getSingleResult();
        lManageQuizSearchResults.setTotalRecords( lTotalRecords.intValue() );
        lManageQuizSearchResults.setQuizResults( lQuizList );
        return lManageQuizSearchResults;
    }

}
