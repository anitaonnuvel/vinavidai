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
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.ahp.commons.util.AhpStringUtil;
import org.ahp.core.pojo.BaseEntity;
import org.ahp.vinavidai.enums.QuestionType;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
@Entity
@Table(name = "QUESTION", schema = "VINAVIDAI")
public class Question extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long mQuestionId;
    private Quiz mQuiz;
    private String mQuestionDescription;
    private String mQuestionObjective;
    private Category mCategory;
    private SkillLevel mSkillLevel;
    private int mQuestionPoints;
    private long mQuestionDuration;
    private QuestionType mQuestionType;
    private Set<Option> mOptions;
    private int mQuestionOrder;

    @Id
    @GeneratedValue
    @Column(name = "QUESTION_ID")
    public Long getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId( Long pQuestionId ) {
        mQuestionId = pQuestionId;
    }

    @ManyToOne
    @JoinColumn(name = "QUIZ_ID", nullable = false)
    public Quiz getQuiz() {
        return mQuiz;
    }

    public void setQuiz( Quiz pQuiz ) {
        mQuiz = pQuiz;
    }

    @Column(name = "DESCRIPTION")
    public String getQuestionDescription() {
        return mQuestionDescription;
    }

    public void setQuestionDescription( String pQuestionDescription ) {
        mQuestionDescription = pQuestionDescription;
    }

    @Column(name = "OBJECTIVE")
    public String getQuestionObjective() {
        return mQuestionObjective;
    }

    public void setQuestionObjective( String pQuestionObjective ) {
        mQuestionObjective = pQuestionObjective;
    }

    @OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID")
    public Category getCategory() {
        return mCategory;
    }

    public void setCategory( Category pCategory ) {
        mCategory = pCategory;
    }

    @OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "SKILL_LEVEL_ID")
    public SkillLevel getSkillLevel() {
        return mSkillLevel;
    }

    public void setSkillLevel( SkillLevel pSkillLevel ) {
        mSkillLevel = pSkillLevel;
    }

    @Column(name = "QUESTION_TYPE")
    @Enumerated(EnumType.STRING)
    public QuestionType getQuestionType() {
        return mQuestionType;
    }

    public void setQuestionType( QuestionType pQuestionType ) {
        mQuestionType = pQuestionType;
    }

    @OneToMany(targetEntity = org.ahp.vinavidai.pojo.Option.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "question")
    public Set<Option> getOptions() {
        return mOptions;
    }

    public void setOptions( Set<Option> pOptions ) {
        mOptions = pOptions;
    }

    @Column(name = "QUESTION_POINTS")
    public int getQuestionPoints() {
        return mQuestionPoints;
    }

    public void setQuestionPoints( int pQuestionPoints ) {
        mQuestionPoints = pQuestionPoints;
    }

    @Column(name = "QUESTION_DURATION")
    public long getQuestionDuration() {
        return mQuestionDuration;
    }

    public void setQuestionDuration( long pQuestionDuration ) {
        mQuestionDuration = pQuestionDuration;
    }

    @Column(name = "QUESTION_ORDER")
    public int getQuestionOrder() {
        return mQuestionOrder;
    }

    public void setQuestionOrder( int pQuestionOrder ) {
        mQuestionOrder = pQuestionOrder;
    }

    /**
     * @return String representation of the object
     */
    public String toString() {
        return AhpStringUtil.reflectionToString( this );
    }
}
