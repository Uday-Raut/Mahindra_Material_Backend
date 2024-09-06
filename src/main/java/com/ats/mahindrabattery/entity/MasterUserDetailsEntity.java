package com.ats.mahindrabattery.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ats_wms_master_user_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterUserDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "USER_TITLE")
	private String userTitle;

	@Column(name = "USER_NAME",unique = true)
	private String userName;

	@Column(name = "USER_PASSWORD")
	private String userPassword;

	@Column(name = "CONTACT_NUMBER")
	private String contactNumber;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "USER_IMAGE")
	private byte[] userImage;

	@Column(name = "ROLE_ID")
	private int roleId;

	@Column(name = "ROLE_NAME")
	private String roleName;

	@Column(name = "CDATETIME")
	private LocalDateTime cDatetime=LocalDateTime.now();

	@Column(name = "USER_IS_DELETED")
	private int userIsDeleted;
	
	@Transient
	protected String userPhotoImageIn64Base;
	
	@Transient
	protected String jwtToken;
	

	

}
