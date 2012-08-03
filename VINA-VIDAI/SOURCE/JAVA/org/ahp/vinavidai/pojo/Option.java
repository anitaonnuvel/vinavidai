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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.ahp.commons.util.AhpStringUtil;
import org.ahp.core.pojo.BaseEntity;
import org.ahp.vinavidai.enums.DescriptionQuestionMaximumSizeType;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
@Entity
//@Table(name = "OPTION", schema = "VINAVIDAI")
@Table(name = "OPTIONS")
public class Option extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long mOptionId;
    private Question mQuestion;
    private String mOptionDescription;
    private String mOptionMatch;
    private String mOptionJustification;
    private int mOptionOrder = -1;
    private DescriptionQuestionMaximumSizeType mDescriptionQuestionMaximumSizeType;
    private String mDescriptionQuestionMaximumSizeTypeStr;
    private int mDescriptionQuestionMaximumSize = -1;
    private boolean mAnswer;

    @Id
    @GeneratedValue
    @Column(name = "OPTION_ID")
    public Long getOptionId() {
        return mOptionId;
    }

    public void setOptionId( Long pOptionId ) {
        mOptionId = pOptionId;
    }

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID", nullable = false)
    public Question getQuestion() {
        return mQuestion;
    }

    public void setQuestion( Question pQuestion ) {
        mQuestion = pQuestion;
    }

    @Column(name = "OPTION_DESCRIPTION")
    public String getOptionDescription() {
        return mOptionDescription;
    }

    public void setOptionDescription( String pOptionDescription ) {
        mOptionDescription = pOptionDescription;
    }

    @Column(name = "OPTION_JUSTIFICATION")
    public String getOptionJustification() {
        return mOptionJustification;
    }

    public void setOptionJustification( String pOptionJustification ) {
        mOptionJustification = pOptionJustification;
    }

    @Column(name = "IS_ANSWER")
    public boolean getAnswer() {
        return mAnswer;
    }

    public void setAnswer( boolean pAnswer ) {
        mAnswer = pAnswer;
    }

    @Column(name = "OPTION_MATCH")
    public String getOptionMatch() {
        return mOptionMatch;
    }

    public void setOptionMatch( String pOptionMatch ) {
        mOptionMatch = pOptionMatch;
    }

    @Column(name = "OPTION_ORDER")
    public int getOptionOrder() {
        return mOptionOrder;
    }

    public void setOptionOrder( int pOptionOrder ) {
        mOptionOrder = pOptionOrder;
    }

    @Column(name = "DESCRIPTIVE_SIZE_TYPE")
    @Enumerated(EnumType.STRING)
    public DescriptionQuestionMaximumSizeType getDescriptionQuestionMaximumSizeType() {
        return mDescriptionQuestionMaximumSizeType;
    }

    public void setDescriptionQuestionMaximumSizeType(
            DescriptionQuestionMaximumSizeType pDescriptionQuestionMaximumSizeType ) {
        mDescriptionQuestionMaximumSizeType = pDescriptionQuestionMaximumSizeType;
    }

    @Column(name = "DESCRIPTIVE_SIZE_MAX")
    public int getDescriptionQuestionMaximumSize() {
        return mDescriptionQuestionMaximumSize;
    }

    public void setDescriptionQuestionMaximumSize(
            int pDescriptionQuestionMaximumSize ) {
        mDescriptionQuestionMaximumSize = pDescriptionQuestionMaximumSize;
    }

    /**
     * @return String representation of the object
     */
    public String toString() {
        return AhpStringUtil.reflectionToString( this );
    }

    @Transient
    public String getDescriptionQuestionMaximumSizeTypeStr() {
        return mDescriptionQuestionMaximumSizeTypeStr;
    }

    public void setDescriptionQuestionMaximumSizeTypeStr(
            String pDescriptionQuestionMaximumSizeTypeStr ) {
        mDescriptionQuestionMaximumSizeTypeStr = pDescriptionQuestionMaximumSizeTypeStr;
    }

}