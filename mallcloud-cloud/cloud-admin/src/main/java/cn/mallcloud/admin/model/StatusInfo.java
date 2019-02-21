/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.mallcloud.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 服务状态
 *
 * @author zscat
 */
@Getter
@EqualsAndHashCode
@ToString
public class StatusInfo implements Serializable {

	private static final long serialVersionUID = -1108780560907132217L;
	public static final String STATUS_KEY = "status";

	private final String status;
	private final long timestamp;

	public StatusInfo(String status, long timestamp) {
		this.status = status.toUpperCase();
		this.timestamp = timestamp;
	}

	public static StatusInfo valueOf(String statusCode) {
		return new StatusInfo(statusCode, System.currentTimeMillis());
	}

	public static StatusInfo ofUnknown() {
		return valueOf("UNKNOWN");
	}

	public static StatusInfo ofUp() {
		return valueOf("UP");
	}

	public static StatusInfo ofDown() {
        return valueOf("DOWN");
	}

	public static StatusInfo ofOffline() {
        return valueOf("OFFLINE");
	}

	@JsonIgnore
	public boolean isUp() {
		return "UP".equals(status);
	}

	@JsonIgnore
	public boolean isOffline() {
		return "OFFLINE".equals(status);
	}

	@JsonIgnore
	public boolean isDown() {
		return "DOWN".equals(status);
	}

	@JsonIgnore
	public boolean isUnknown() {
		return "UNKNOWN".equals(status);
	}

}
