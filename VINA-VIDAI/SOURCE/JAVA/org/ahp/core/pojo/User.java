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
package org.ahp.core.pojo;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.ahp.commons.util.AhpStringUtil;
import org.ahp.vinavidai.pojo.QuizGroup;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
@Entity
//@Table( name = "USER", schema = "VINAVIDAI" )
@Table( name = "USER" )
@NamedQuery(name = "doesUserExist", query = "SELECT userBean FROM User userBean WHERE userBean.loginName LIKE :registrationName")
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long mUserId;
    private String mLoginName;
    private String mFirstName;
    private String mLastName;
    private String mPassword;
    private String mConfirmPassword;
    private Map<Long, QuizGroup> mQuizGroup;

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    public Long getUserId() {
        return mUserId;
    }

    public void setUserId( Long pUserId ) {
        this.mUserId = pUserId;
    }

    @Column(name = "LOGIN_NAME")
    public String getLoginName() {
        return mLoginName;
    }

    public void setLoginName( String pLoginName ) {
        this.mLoginName = pLoginName;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return mPassword;
    }

    public void setPassword( String pPassword ) {
        this.mPassword = pPassword;
    }

    @OneToMany(targetEntity = org.ahp.vinavidai.pojo.Quiz.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    @MapKey()
    public Map<Long, QuizGroup> getQuizGroup() {
        return mQuizGroup;
    }

    public void setQuizGroup( Map<Long, QuizGroup> pQuizGroup ) {
        mQuizGroup = pQuizGroup;
    }

    /**
     * @return the firstName
     */
    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return mFirstName;
    }

    /**
     * @param pFirstName
     *            the firstName to set
     */
    public void setFirstName( String pFirstName ) {
        mFirstName = pFirstName;
    }

    /**
     * @return the lastName
     */
    @Column(name = "LAST_NAME")
    public String getLastName() {
        return mLastName;
    }

    /**
     * @param pLastName
     *            the lastName to set
     */
    public void setLastName( String pLastName ) {
        mLastName = pLastName;
    }

    /**
     * @return the confirmPassword
     */
    @Transient
    public String getConfirmPassword() {
        return mConfirmPassword;
    }

    /**
     * @param pConfirmPassword
     *            the confirmPassword to set
     */
    public void setConfirmPassword( String pConfirmPassword ) {
        mConfirmPassword = pConfirmPassword;
    }

    /**
     * @return String representation of the object
     */
    public String toString() {
        return AhpStringUtil.reflectionToString( this );
    }
}