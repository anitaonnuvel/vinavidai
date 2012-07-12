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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.ahp.commons.util.AhpStringUtil;
import org.ahp.core.pojo.BaseEntity;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
@Entity
@Table(name = "SKILL_LEVEL", schema = "VINAVIDAI")
public class SkillLevel extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long mSkillLevelId;
    private Quiz mQuiz;
    private String mSkillLevel;

    @Id
    @GeneratedValue
    @Column(name = "SKILL_LEVEL_ID")
    public Long getSkillLevelId() {
        return mSkillLevelId;
    }

    public void setSkillLevelId( Long pSkillLevelId ) {
        mSkillLevelId = pSkillLevelId;
    }

    @ManyToOne
    @JoinColumn(name = "QUIZ_ID", nullable = false)
    public Quiz getQuiz() {
        return mQuiz;
    }

    public void setQuiz( Quiz pQuiz ) {
        mQuiz = pQuiz;
    }

    @Column(name = "SKILL_LEVEL")
    public String getSkillLevel() {
        return mSkillLevel;
    }

    public void setSkillLevel( String pSkillLevel ) {
        mSkillLevel = pSkillLevel;
    }

    /**
     * @return String representation of the object
     */
    public String toString() {
        return AhpStringUtil.reflectionToString( this );
    }

}
