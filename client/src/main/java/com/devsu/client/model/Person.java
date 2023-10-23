package com.devsu.client.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class Person implements Serializable {

	private String name;
	private String gender;
	private String age;
	@Column(unique = true)
	private String nationalId;
	private String address;
	private String phone;

}
