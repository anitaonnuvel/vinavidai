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
package org.ahp.vinavidai.quiz.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ahp.commons.form.AhpAbstractForm;
import org.ahp.vinavidai.pojo.Category;
import org.ahp.vinavidai.pojo.SkillLevel;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anita Onnuvel
 * 
 */
public abstract class QuizForm extends AhpAbstractForm {

    private static final long serialVersionUID = 1L;

    final static Logger LOGGER = LoggerFactory.getLogger( QuizForm.class );

    // Form Fields
    private String mQuizName;
    private String mQuizDescription;
    private List<Category> mCategory = new ArrayList<Category>();
    private List<SkillLevel> mSkillLevel = new ArrayList<SkillLevel>();

    // Hidden Fields
    private int mHiddenDeleteCategoryIndex;
    private int mHiddenDeleteSkillLevelIndex;

    // For Display
    private String mCategoriesAutoComplete;
    private String mSkillLevelsAutoComplete;

    // For Business Logic
    private String mQuizOperationType;

    @Override
    protected void resetForm( ActionMapping pActionMapping,
            HttpServletRequest pHttpServletRequest ) {
        this.setQuizName( null );
        this.setQuizDescription( null );
    }

    public String getQuizName() {
        return mQuizName;
    }

    public void setQuizName( String pQuizName ) {
        mQuizName = pQuizName;
    }

    public String getQuizDescription() {
        return mQuizDescription;
    }

    public void setQuizDescription( String pQuizDescription ) {
        mQuizDescription = pQuizDescription;
    }

    public String getCategoriesAutoComplete() {
        return mCategoriesAutoComplete;
    }

    public void setCategoriesAutoComplete( String pCategoriesAutoComplete ) {
        mCategoriesAutoComplete = pCategoriesAutoComplete;
    }

    public String getSkillLevelsAutoComplete() {
        return mSkillLevelsAutoComplete;
    }

    public void setSkillLevelsAutoComplete( String pSkillLevelsAutoComplete ) {
        mSkillLevelsAutoComplete = pSkillLevelsAutoComplete;
    }

    public String getQuizOperationType() {
        return mQuizOperationType;
    }

    public void setQuizOperationType( String pQuizOperationType ) {
        mQuizOperationType = pQuizOperationType;
    }

    public List<Category> getCategory() {
        return mCategory;
    }

    public void setCategory( List<Category> pCategory ) {
        mCategory = pCategory;
    }

    public List<SkillLevel> getSkillLevel() {
        return mSkillLevel;
    }

    public void setSkillLevel( List<SkillLevel> pSkillLevel ) {
        mSkillLevel = pSkillLevel;
    }

    public Category getCategoryItem( int pIndex ) {
        while ( pIndex >= this.getCategory().size() ) {
            this.mCategory.add( new Category() );
        }
        return mCategory.get( pIndex );
    }

    public void setCategoryItem( int pIndex, Category pCategory ) {
        this.mCategory.add( pIndex, pCategory );
    }

    public SkillLevel getSkillLevelItem( int pIndex ) {
        while ( pIndex >= this.mSkillLevel.size() )
            this.mSkillLevel.add( new SkillLevel() );
        return mSkillLevel.get( pIndex );
    }

    public void setSkillLevelItem( int pIndex, SkillLevel pSkillLevel ) {
        this.mSkillLevel.add( pIndex, pSkillLevel );
    }

    public int getHiddenDeleteCategoryIndex() {
        return mHiddenDeleteCategoryIndex;
    }

    public void setHiddenDeleteCategoryIndex( int pHiddenDeleteCategoryIndex ) {
        mHiddenDeleteCategoryIndex = pHiddenDeleteCategoryIndex;
    }

    public int getHiddenDeleteSkillLevelIndex() {
        return mHiddenDeleteSkillLevelIndex;
    }

    public void setHiddenDeleteSkillLevelIndex( int pHiddenDeleteSkillLevelIndex ) {
        mHiddenDeleteSkillLevelIndex = pHiddenDeleteSkillLevelIndex;
    }

}
