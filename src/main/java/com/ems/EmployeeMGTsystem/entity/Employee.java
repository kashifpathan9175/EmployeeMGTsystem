package com.ems.EmployeeMGTsystem.entity;

import java.sql.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Employee {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "employee_Id")
        private Long eid;

         @Column(nullable = false)
         @NotEmpty(message = "Employee name cannot be blank")
        private String ename;
         @Column(nullable = false,unique = true)
         @NotEmpty(message = "Email cannot be blank")
         @Email(message = "enter proper email address")
        private String emailId;
        @Column(nullable = false,unique = true)
        @NotEmpty(message = "Phone number cannot be blank & should be 10 digits")
        @Size(max = 10, min = 10, message="mobile number must be of 10 digits")
        private String phoneNo;

        @NotEmpty(message = "password cannot be blank")
        @Column(nullable = false,unique = true)
        private String password;


        @Column(name = "created_date", updatable = false, nullable = false)
        @NotNull(message = "date is mandatory")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private Date createdDate;

        @Column(name = "created_by", updatable = false, nullable = false)
        @NotEmpty(message = "name is mandatory")
        private String createdBy;

        @Column(nullable = false)
        @NotNull(message = "date is mandatory")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private String updatedDate;
        @Column(nullable = false)
        @NotEmpty(message = "name is mandatory")
        private String updatedBy;
        @Column(nullable = false)
        @NotEmpty(message = "department is mandatory")
        private String department;
        @Column(nullable = false)
        @NotEmpty(message = "status is mandatory")
        private String status;

        @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
        @JoinColumn(name = "employee_id", nullable = false)
        @NotNull(message = "country cannot be blank")
        private Country country;

        // Getters and setters...

        public Long getEid() {
            return eid;
        }

        public void setEid(Long eid) {
            this.eid = eid;
        }

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public String getEmailId() {
            return emailId;
        }

        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }

        @Column(nullable = false,unique = true)
        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        @Column(nullable = false,unique = true)
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Date getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getUpdatedDate() {
            return updatedDate;
        }

        public void setUpdatedDate(String updatedDate) {
            this.updatedDate = updatedDate;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
        }

    public Employee(Long eid, String ename, String emailId, String phoneNo, String password, Date createdDate, String createdBy, String updatedDate, String updatedBy, String department, String status, Country country) {
        this.eid = eid;
        this.ename = ename;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
        this.password = password;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
        this.department = department;
        this.status = status;
        this.country = country;
    }
    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eid=" + eid +
                ", ename='" + ename + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", password='" + password + '\'' +
                ", createdDate=" + createdDate +
                ", createdBy='" + createdBy + '\'' +
                ", updatedDate='" + updatedDate + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", department='" + department + '\'' +
                ", status='" + status + '\'' +
                ", country=" + country +
                '}';
    }


}



