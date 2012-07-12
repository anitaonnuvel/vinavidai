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

/**
 * 
 * @author Anita Onnuvel
 * 
 */
public enum ListStyle {

    Numeric( 1, "1 2 3 ..." ), AlphaUpper( 2, "A B C ..." ), AlphaLower( 3,
            "a b c ..." ), RomanUpper( 4, "I II III IV V" ), RomanLower( 5,
            "i ii iii iv v" );

    private int mListStyleType;
    private String mListStyle;

    private ListStyle( int pListStyleType, String pListStyle ) {
        mListStyleType = pListStyleType;
        mListStyle = pListStyle;
    }

    public int getListStyleType() {
        return this.mListStyleType;
    }

    public String getListStyle() {
        return this.mListStyle;
    }

    public String toString() {
        return this.mListStyle;
    }
}
