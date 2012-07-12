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
public enum SubmitActions {

    CANCEL( "Cancel" ), GO( "Go" ), CONTINUE( "Continue" ), NEXT( "Next" ), SAVE(
            "Save" ), MANAGE_QUESTIONS( "Manage Questions" ), DELETE_OPTION(
            "Delete Option" ), ADD_OPTION( "Add Option" ), COMPLETE( "Complete" ), RESET(
            "Reset" ), RETURN_TO_CONSOLE( "Return To Console" ), RETURN_TO_MANAGE_QUIZ(
            "Return To Manage Quiz" ), CONFIRM_DELETE( "Confirm Delete" ), DELETE(
            "Delete" ), EDIT( "Edit" ), ADD_QUESTIONS( "Add Questions" ), SAVE_AND_ADD_QUESTIONS(
            "Save and Add Questions" ), PUBLISH( "Publish" ), PUBLISH_TEST(
            "Publish Test" ), PUBLISH_QUIZ( "Publish Quiz" ), REPORTS(
            "Reports" ), VIEW_QUIZ( "View Quiz" ), SAVE_CHANGES( "Save Changes" ), EMPTY(
            "" ), ADD_CATEGORY( "Add Category" ), DELETE_CATEGORY(
            "Delete Category" ), ADD_SKILL_LEVEL( "Add Skill Level" ), DELETE_SKILL_LEVEL(
            "Delete Skill Level" );

    private String mSubmitActionLiteral;

    private SubmitActions( String pSubmitActionLiteral ) {
        this.mSubmitActionLiteral = pSubmitActionLiteral;
    }

    public String toString() {
        return this.mSubmitActionLiteral;
    }

    public String getValue() {
        return this.mSubmitActionLiteral;
    }

}
