package com.sunlife.vn.models.entity.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class EvtMstr {
	private String evt_mastr_id;
    private String case_id;
    private String msg_id;
    private String queue_nm;
	private String queue_msg;
	private String creat_by;
	private String creat_dt;
	private String updt_by;
	private String updt_dt ;
	private String stat;
	private String grp_id;
	private String dedup_id;

	@DynamoDbPartitionKey
	@DynamoDbAttribute("evt_mastr_id")
	public String getEvt_mastr_id() {
		return evt_mastr_id;
	}

	public void setEvt_mastr_id(String evt_mastr_id) {
		this.evt_mastr_id = evt_mastr_id;
	}

	@DynamoDbSortKey
	@DynamoDbAttribute("case_id")
	public String getCase_id() {
		return case_id;
	}

	public void setCase_id(String case_id) {
		this.case_id = case_id;
	}

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}

	public String getQueue_nm() {
		return queue_nm;
	}

	public void setQueue_nm(String queue_nm) {
		this.queue_nm = queue_nm;
	}

	public String getQueue_msg() {
		return queue_msg;
	}

	public void setQueue_msg(String queue_msg) {
		this.queue_msg = queue_msg;
	}

	public String getCreat_by() {
		return creat_by;
	}

	public void setCreat_by(String creat_by) {
		this.creat_by = creat_by;
	}

	public String getCreat_dt() {
		return creat_dt;
	}

	public void setCreat_dt(String creat_dt) {
		this.creat_dt = creat_dt;
	}

	public String getUpdt_by() {
		return updt_by;
	}

	public void setUpdt_by(String updt_by) {
		this.updt_by = updt_by;
	}

	public String getUpdt_dt() {
		return updt_dt;
	}

	public void setUpdt_dt(String updt_dt) {
		this.updt_dt = updt_dt;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getGrp_id() {
		return grp_id;
	}

	public void setGrp_id(String grp_id) {
		this.grp_id = grp_id;
	}

	public String getDedup_id() {
		return dedup_id;
	}

	public void setDedup_id(String dedup_id) {
		this.dedup_id = dedup_id;
	}
}
