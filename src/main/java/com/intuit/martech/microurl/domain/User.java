package com.intuit.martech.microurl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "type")
	private TypeEnum role = null;
	
	@JsonIgnore
	@Column(name = "requestCounter")
	private Long requestCounter;

	public static TypeEnum fromValue(String text) {
		for (TypeEnum b : TypeEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	@JsonCreator
	public User role(TypeEnum role) {
		this.role = role;
		return this;
	}

	public enum TypeEnum {
		BASIC("basic"),
		PRIME("prime");

		private String value;

		TypeEnum(String value) {
			this.value = value;
		}

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public TypeEnum getRole() {
		return role;
	}

	public void setRole(TypeEnum role) {
		this.role = role;
	}

	public Long getRequestCounter() {
		return requestCounter;
	}

	public void setRequestCounter(Long requestCounter) {
		this.requestCounter = requestCounter;
	}
}
