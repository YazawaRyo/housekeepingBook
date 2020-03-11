package com.example.domain.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.example.domain.exception.ValidGroup1;
import com.example.domain.exception.ValidGroup2;
import com.example.domain.exception.ValidGroup3;

public class MemberForm {

	@NotBlank(groups = ValidGroup1.class)
	@Length(max = 10, groups = ValidGroup2.class)
	@Pattern(regexp = "^[__a-zA-Z0-9]+$", groups = ValidGroup3.class)
	private String id;
	@NotBlank(groups = ValidGroup1.class)
	@Length(max = 20, groups = ValidGroup2.class)
	private String name;
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 4, max = 10, groups = ValidGroup2.class)
	@Pattern(regexp = "^[__a-zA-Z0-9]+$", groups = ValidGroup3.class)
	private String password;
	@NotNull(groups = ValidGroup1.class)
	private Integer authority;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

}