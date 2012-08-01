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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.ahp.commons.util.AhpStringUtil;
import org.ahp.core.pojo.BaseEntity;
import org.ahp.core.pojo.User;
import org.ahp.vinavidai.enums.Status;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
@Entity
//@Table(name = "QUIZ_GROUP", schema = "VINAVIDAI")
@Table(name = "QUIZ_GROUP")
public class QuizGroup extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long mGroupId;
    private String mGroupName;
    private String mGroupDescription;
    private Status mGroupStatus;
    private User mUser;

    @Id
    @GeneratedValue
    @Column(name = "GROUP_ID")
    public Long getGroupId() {
        return mGroupId;
    }

    public void setGroupId( Long pGroupId ) {
        mGroupId = pGroupId;
    }

    @Column(name = "GROUP_NAME", nullable = false, length = 255)
    public String getGroupName() {
        return mGroupName;
    }

    public void setGroupName( String pGroupName ) {
        mGroupName = pGroupName;
    }

    @Column(name = "GROUP_DESCRIPTION", nullable = false, length = 2000)
    public String getGroupDescription() {
        return mGroupDescription;
    }

    public void setGroupDescription( String pGroupDescription ) {
        mGroupDescription = pGroupDescription;
    }

    @Column(name = "GROUP_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    public Status getGroupStatus() {
        return mGroupStatus;
    }

    public void setGroupStatus( Status pGroupStatus ) {
        mGroupStatus = pGroupStatus;
    }

    /**
     * @return String representation of the object
     */
    public String toString() {
        return AhpStringUtil.reflectionToString( this );
    }

    @ManyToOne
    public User getUser() {
        return mUser;
    }

    public void setUser( User pUser ) {
        mUser = pUser;
    }
}