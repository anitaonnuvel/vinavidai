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
package org.ahp.commons.enums;

/**
 * 
 * @author Anita Onnuvel
 *
 */
public enum AhpSubmitActions {
	
	LOGIN( "Login" ),
	CANCEL( "Cancel" ),
	GO( "Go" ),
	CONTINUE( "Continue" ),
	NEXT( "Next" ),
	SAVE( "Save" ),
	COMPLETE_QUIZ( "Complete Quiz" );
	
	private String mSubmitActionLiteral;
	
	private AhpSubmitActions( String pSubmitActionLiteral ) {
	    this.mSubmitActionLiteral = pSubmitActionLiteral;	    
	}

	public String toString(){
	    return this.mSubmitActionLiteral;
	}

    public String getValue(){
        return this.mSubmitActionLiteral;
    }

}
