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
package org.ahp.core.exceptions;

/**
 * 
 * @author Anita Onnuvel
 *
 */
public enum AhpExceptionCodes {

	AHP_00_000("AHP.00.000"), AHP_00_001("AHP.00.001");

	private String mExceptionCodeLiteral;

	/**
	 * 
	 * @param pExceptionCodeLiteral
	 */
	private AhpExceptionCodes(String pExceptionCodeLiteral) {
		this.mExceptionCodeLiteral = pExceptionCodeLiteral;
	}

	public String getValue() {
		return this.mExceptionCodeLiteral;
	}

	public String toString() {
		return this.mExceptionCodeLiteral;
	}

}
