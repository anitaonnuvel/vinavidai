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
public enum DisplayStyle {

    SquareBraces( 1, "[1] [b]" ), CloseBraces( 2, "1) B)" ), Dot( 3,
            "1. I. iv." );

    private int mDisplayStyleType;
    private String mDisplayStyle;

    private DisplayStyle( int pDisplayStyleType, String pDisplayStyle ) {
        mDisplayStyleType = pDisplayStyleType;
        mDisplayStyle = pDisplayStyle;
    }

    public int getDisplayStyleType() {
        return this.mDisplayStyleType;
    }

    public String getDisplayStyle() {
        return this.mDisplayStyle;
    }

    public String toString() {
        return this.mDisplayStyle;
    }

}
