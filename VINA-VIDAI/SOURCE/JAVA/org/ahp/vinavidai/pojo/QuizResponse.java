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
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.ahp.commons.util.AhpStringUtil;
import org.ahp.core.pojo.BaseEntity;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
@Entity
//@Table(name = "QUIZ_RESPONSE", schema = "VINAVIDAI")
@Table(name = "QUIZ_RESPONSE")
public class QuizResponse extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long mId;
    private Long mUserId;
    private Long mQuizId;
    private boolean mIsQuizPaused;
    private Long mQuizDuration;
    private Map<Long, QuestionResponse> mQuestionResponse;

    @Id
    @GeneratedValue
    @Column(name = "QUIZ_RESPONSE_ID")
    public Long getId() {
        return mId;
    }

    public void setId( Long pId ) {
        mId = pId;
    }

    @Column(name = "USER_ID")
    public Long getUserId() {
        return mUserId;
    }

    public void setUserId( Long pUserId ) {
        mUserId = pUserId;
    }

    @Column(name = "QUIZ_ID")
    public Long getQuizId() {
        return mQuizId;
    }

    public void setQuizId( Long pQuizId ) {
        mQuizId = pQuizId;
    }

    @Column(name = "IS_QUIZ_PAUSED")
    public boolean isIsQuizPaused() {
        return mIsQuizPaused;
    }

    public void setIsQuizPaused( boolean pIsQuizPaused ) {
        mIsQuizPaused = pIsQuizPaused;
    }

    @Column(name = "QUIZ_DURATION")
    public Long getQuizDuration() {
        return mQuizDuration;
    }

    public void setQuizDuration( Long pQuizDuration ) {
        mQuizDuration = pQuizDuration;
    }

    /*
     * public Map<Long, QuestionResponse> getQuestionResponse() { return
     * mQuestionResponse; } public void setQuestionResponse( Map<Long,
     * QuestionResponse> pQuestionResponse ) { mQuestionResponse =
     * pQuestionResponse; }
     */

    /**
     * @return String representation of the object
     */
    public String toString() {
        return AhpStringUtil.reflectionToString( this );
    }
}
