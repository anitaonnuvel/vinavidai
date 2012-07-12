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
@Table(name = "QUESTION_RESPONSE", schema = "VINAVIDAI")
public class QuestionResponse extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long mId;
    private Long mQuizResponseId;
    private Long mQuizId;
    private Long mQuestionId;
    private Long mResponseDuration;
    private boolean mResponseCorrect;

    @Id
    @GeneratedValue
    @Column(name = "QUESTION_RESPONSE_ID")
    public Long getId() {
        return mId;
    }

    public void setId( Long pId ) {
        mId = pId;
    }

    @Column(name = "QUIZ_RESPONSE_ID")
    public Long getQuizResponseId() {
        return mQuizResponseId;
    }

    public void setQuizResponseId( Long pQuizResponseId ) {
        mQuizResponseId = pQuizResponseId;
    }

    @Column(name = "QUIZ_ID")
    public Long getQuizId() {
        return mQuizId;
    }

    public void setQuizId( Long pQuizId ) {
        mQuizId = pQuizId;
    }

    @Column(name = "QUESTION_ID")
    public Long getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId( Long pQuestionId ) {
        mQuestionId = pQuestionId;
    }

    @Column(name = "RESPONSE_DURATION")
    public Long getResponseDuration() {
        return mResponseDuration;
    }

    public void setResponseDuration( Long pResponseDuration ) {
        mResponseDuration = pResponseDuration;
    }

    @Column(name = "IS_RESPONSE_CORRECT")
    public boolean isResponseCorrect() {
        return mResponseCorrect;
    }

    public void setResponseCorrect( boolean pResponseCorrect ) {
        mResponseCorrect = pResponseCorrect;
    }

    /**
     * @return String representation of the object
     */
    public String toString() {
        return AhpStringUtil.reflectionToString( this );
    }

}
