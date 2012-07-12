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

import org.ahp.commons.util.AhpStringUtil;
import org.ahp.core.pojo.BaseEntity;
import org.ahp.core.pojo.User;
import org.ahp.vinavidai.enums.Status;

/**
 * 
 * @author Anita Onnuvel
 * 
 *         https://forum.hibernate.org/viewtopic.php?t=959478
 *         http://blog.eyallupu
 *         .com/2010/06/hibernate-exception-simultaneously.html
 */
@Entity
@Table(name = "QUIZ", schema = "VINAVIDAI")
public class Quiz extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long mQuizId;
    private String mQuizName;
    private String mQuizDescription;
    private Status mQuizStatus;
    private Set<Category> mCategories;
    private Set<SkillLevel> mSkillLevels;
    private Set<Question> mQuestions;
    private User mUser;
    private Set<Test> mTest;

    @Id
    @GeneratedValue
    @Column(name = "QUIZ_ID")
    public Long getQuizId() {
        return mQuizId;
    }

    public void setQuizId( Long pQuizId ) {
        mQuizId = pQuizId;
    }

    @Column(name = "QUIZ_NAME", nullable = false, length = 255)
    public String getQuizName() {
        return mQuizName;
    }

    public void setQuizName( String pQuizName ) {
        mQuizName = pQuizName;
    }

    @Column(name = "QUIZ_DESCRIPTION", nullable = false, length = 2000)
    public String getQuizDescription() {
        return mQuizDescription;
    }

    public void setQuizDescription( String pQuizDescription ) {
        mQuizDescription = pQuizDescription;
    }

    @OneToMany(targetEntity = org.ahp.vinavidai.pojo.Question.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "quiz")
    public Set<Question> getQuestions() {
        return mQuestions;
    }

    public void setQuestions( Set<Question> pQuestions ) {
        mQuestions = pQuestions;
    }

    @OneToMany(targetEntity = org.ahp.vinavidai.pojo.Category.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "quiz")
    public Set<Category> getCategories() {
        return mCategories;
    }

    public void setCategories( Set<Category> pCategories ) {
        mCategories = pCategories;
    }

    @OneToMany(targetEntity = org.ahp.vinavidai.pojo.SkillLevel.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "quiz")
    public Set<SkillLevel> getSkillLevels() {
        return mSkillLevels;
    }

    public void setSkillLevels( Set<SkillLevel> pSkillLevels ) {
        mSkillLevels = pSkillLevels;
    }

    @Column(name = "QUIZ_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    public Status getQuizStatus() {
        return mQuizStatus;
    }

    public void setQuizStatus( Status pQuizStatus ) {
        mQuizStatus = pQuizStatus;
    }

    @ManyToOne
    public User getUser() {
        return mUser;
    }

    public void setUser( User pUser ) {
        mUser = pUser;
    }

    @OneToMany(mappedBy = "quiz", targetEntity = org.ahp.vinavidai.pojo.Test.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Test> getTest() {
        return mTest;
    }

    public void setTest( Set<Test> pTest ) {
        mTest = pTest;
    }

    /**
     * @return String representation of the object
     */
    public String toString() {
        return AhpStringUtil.reflectionToString( this );
    }

}