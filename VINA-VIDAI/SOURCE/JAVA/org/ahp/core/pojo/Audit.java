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
package org.ahp.core.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.ahp.commons.util.AhpStringUtil;

/**
 * 
 * @author Anita Onnuvel
 * 
 */
@Embeddable
public class Audit implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mCreatedBy;
	private java.util.Date mCreatedDate;
	private Long mLastUpdatedBy;
	private java.util.Date mLastUpdatedDate;

	@Column(name = "CREATED_BY", updatable = false, nullable = false)
	public Long getCreatedBy() {
		return mCreatedBy;
	}

	public void setCreatedBy(Long pCreatedBy) {
		this.mCreatedBy = pCreatedBy;
	}

	@Column(name = "CREATED_DATE", updatable = false, nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public java.util.Date getCreatedDate() {
		return mCreatedDate;
	}

	public void setCreatedDate(java.util.Date pCreatedDate) {
		this.mCreatedDate = pCreatedDate;
	}

	@Column(name = "LAST_UPDATED_BY", nullable = false)
	public Long getLastUpdatedBy() {
		return mLastUpdatedBy;
	}

	public void setLastUpdatedBy(Long pUpdatedBy) {
		this.mLastUpdatedBy = pUpdatedBy;
	}

	@Column(name = "LAST_UPDATED_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public java.util.Date getLastUpdatedDate() {
		return mLastUpdatedDate;
	}

	public void setLastUpdatedDate(java.util.Date updatedDate) {
		this.mLastUpdatedDate = updatedDate;
	}

	/**
	 * @return String representation of the object
	 */
	public String toString() {
		return AhpStringUtil.reflectionToString(this);
	}
}
