package com.ems.EmployeeMGTsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id")
	private Long cid;

	@NotNull(message = "country name cannot be blank")
	@Column(unique = true)
	private String cname;

	 @NotNull(message = "country code cannot be blank")
	@Column(unique = true)
	 long countryCode;

	@OneToOne
	@JoinColumn(name = "country_id", unique = true)
	@JsonIgnore
	private Employee employee;

	// Getters and setters...

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	@Override
	public String toString() {
		return "Country{" +
				"cid=" + cid +
				", cname='" + cname + '\'' +
				", countryCode=" + countryCode +
				", employee=" + employee +
				'}';
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Employee getEmployee() {
		return employee;
	}

	public long getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(long countryCode) {
		this.countryCode = countryCode;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}

