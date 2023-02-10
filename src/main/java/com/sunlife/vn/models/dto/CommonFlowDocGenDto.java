package com.sunlife.vn.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonFlowDocGenDto {
	private String docType;
	private Boolean actSign;
	private Boolean actUpload;
	private Boolean actEmail;
	private Boolean actSms;
	private Boolean actNoti;
	
	private String smsType;
	private String smsTo;
	
	private String mailType;
	private String to;
	
	private String notiType;
	private String userId;
	
}
