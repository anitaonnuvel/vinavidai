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
//@Table(name = "TEST_CONFIG", schema = "VINAVIDAI")
@Table(name = "TEST_CONFIG")
public class TestConfig extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long mTestConfigId;
    private Test mTest;
    private String mConfigName;
    private String mConfigValue;
    private String mDescription;

    @Id
    @GeneratedValue
    @Column(name = "TEST_CONFIG_ID")
    public Long getTestConfigId() {
        return mTestConfigId;
    }

    public void setTestConfigId( Long pTestConfigId ) {
        mTestConfigId = pTestConfigId;
    }

    @ManyToOne
    public Test getTest() {
        return mTest;
    }

    public void setTest( Test pTest ) {
        mTest = pTest;
    }

    @Column(name = "CONFIG_NAME")
    public String getConfigName() {
        return mConfigName;
    }

    public void setConfigName( String pConfigName ) {
        mConfigName = pConfigName;
    }

    @Column(name = "CONFIG_VALUE")
    public String getConfigValue() {
        return mConfigValue;
    }

    public void setConfigValue( String pConfigValue ) {
        mConfigValue = pConfigValue;
    }

    @Column(name = "CONFIG_DESCRIPTION")
    public String getDescription() {
        return mDescription;
    }

    public void setDescription( String pDesciption ) {
        mDescription = pDesciption;
    }

    /**
     * @return String representation of the object
     */
    public String toString() {
        return AhpStringUtil.reflectionToString( this );
    }

}
