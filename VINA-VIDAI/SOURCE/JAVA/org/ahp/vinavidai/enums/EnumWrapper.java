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
package org.ahp.vinavidai.enums;

import java.util.LinkedHashSet;
import java.util.Set;

import org.ahp.vinavidai.quiz.manage.ManageQuizSortByParams;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public final class EnumWrapper {

    /**
     * 
     * @return
     */
    public static Set<String> enumerateListStyle() {
        Set<String> lListStyleEnumSet = new LinkedHashSet<String>();
        for ( ListStyle lListStyle : ListStyle.values() ) {
            lListStyleEnumSet.add( lListStyle.getListStyle() );
        }
        return lListStyleEnumSet;
    }

    /**
     * 
     * @return
     */
    public static Set<String> enumerateQuestionType() {
        Set<String> lQuestionTypeEnumSet = new LinkedHashSet<String>();
        for ( QuestionType lQuestionType : QuestionType.values() ) {
            lQuestionTypeEnumSet.add( lQuestionType.toString() );
        }
        return lQuestionTypeEnumSet;
    }

    /**
     * 
     * @return
     */
    public static Set<String> enumerateDescriptionQuestionMaximumSizeDisplayStyle() {
        Set<String> lDescriptionQuestionMaximumSizeList = new LinkedHashSet<String>();
        for ( DescriptionQuestionMaximumSizeType lDescriptionQuestionMaximumSizeType : DescriptionQuestionMaximumSizeType
                .values() ) {
            lDescriptionQuestionMaximumSizeList
                    .add( lDescriptionQuestionMaximumSizeType.toString() );
        }
        return lDescriptionQuestionMaximumSizeList;
    }

    /**
     * 
     * @return
     */
    public static Set<String> enumerateDisplayStyle() {
        Set<String> lDisplayStyleEnumSet = new LinkedHashSet<String>();
        for ( DisplayStyle lDisplayStyle : DisplayStyle.values() ) {
            lDisplayStyleEnumSet.add( lDisplayStyle.toString() );
        }
        return lDisplayStyleEnumSet;
    }

    /**
     * 
     * @return
     */
    public static Set<String> enumerateSortOrderAscDesc() {
        Set<String> lSortOrderAscDescEnumSet = new LinkedHashSet<String>();
        for ( SortOrderAscDesc lSortOrderAscDesc : SortOrderAscDesc.values() ) {
            lSortOrderAscDescEnumSet.add( lSortOrderAscDesc.toString() );
        }
        return lSortOrderAscDescEnumSet;
    }

    /**
     * 
     * @return
     */
    public static Set<String> enumerateManageQuizSortByParams() {
        Set<String> lManageQuizSortByParamsEnumSet = new LinkedHashSet<String>();
        for ( ManageQuizSortByParams lManageQuizSortByParams : ManageQuizSortByParams
                .values() ) {
            lManageQuizSortByParamsEnumSet.add( lManageQuizSortByParams
                    .toString() );
        }
        return lManageQuizSortByParamsEnumSet;
    }

}
