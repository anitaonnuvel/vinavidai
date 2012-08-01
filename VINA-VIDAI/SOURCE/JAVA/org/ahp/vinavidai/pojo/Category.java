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
@Table(name = "CATEGORY" )
//@Table(name = "CATEGORY", schema = "VINAVIDAI")
public class Category extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long mCategoryId;
    private Quiz mQuiz;
    private String mCategory;

    @Id
    @GeneratedValue
    @Column(name = "CATEGORY_ID")
    public Long getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId( Long pCategoryId ) {
        mCategoryId = pCategoryId;
    }

    @ManyToOne
    @JoinColumn(name = "QUIZ_ID", nullable = false)
    public Quiz getQuiz() {
        return mQuiz;
    }

    public void setQuiz( Quiz pQuiz ) {
        mQuiz = pQuiz;
    }

    @Column(name = "CATEGORY")
    public String getCategory() {
        return mCategory;
    }

    public void setCategory( String pCategory ) {
        mCategory = pCategory;
    }

    /**
     * @return String representation of the object
     */
    public String toString() {
        return AhpStringUtil.reflectionToString( this );
    }

}
