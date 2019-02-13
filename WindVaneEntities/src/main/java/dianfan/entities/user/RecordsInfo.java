package dianfan.entities.user;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @ClassName Records
 * @Description 报备表
 * @author sz
 * @date 2018年8月20日 下午5:00:39
 */
public class RecordsInfo {
	private String id; // varchar(50) NOT NULL COMMENT '主键id',
	private String telno; // varchar(50) DEFAULT NULL COMMENT '报备电话',
	private String cusName; // varchar(50) DEFAULT NULL COMMENT '客户姓名',
	private String preparationUserId; // varchar(50) DEFAULT NULL COMMENT '报备者',
	private String backupsId; // 报备者的电话号码(账号)
	private String preparationCompany; // 报备者的公司
	private String status; //varchar(2) DEFAULT '01' COMMENT '状态表（01：未带看02：确认到达带看03：已带看04：确认成交）',
	private String applyFlag; //varchar(2) DEFAULT NULL COMMENT '审核标志（01:待审核 02：审核通过 03 ：审核不通过）',
	private Date appointmentDate; //date DEFAULT NULL COMMENT '预约日期',
	private String appointmentTime; //varchar(50) DEFAULT NULL COMMENT '预约时间范围',
	private String intentionDes; //varchar(1000) DEFAULT NULL COMMENT '客户意向描述',
	private Timestamp createTime; //timestamp NOT NULL DEFAULT CURESTAMP COMMENT '更新时间',
	//private TakeLook takeLook;// 一对一带看表
	/* 带看表里面的需要显示的内容 */
	private Timestamp takeLookTime; //datetime DEFAULT NULL COMMENT '带看时间',
	private String roomNum; //varchar(50) DEFAULT NULL COMMENT '房号',
	private String specificDes; //text COMMENT '具体描述',
	private String tsDes; //text COMMENT '成交描述',
	private String area; //varchar(50) DEFAULT NULL COMMENT '面积',
	private String totalMoney; //varchar(50) DEFAULT NULL COMMENT '总价',
	private String commissionMoney; //varchar(50) DEFAULT NULL COMMENT '佣金',
	private Timestamp tsDate; // 成交时间
	
	/* 楼盘的具体信息 */ 
	private String estateId; //varchar(50) DEFAULT NULL COMMENT '楼盘id',
	private String estateName; //楼盘名字',
	private String estateDes; //text COMMENT '描述',
	private String estateAddress; //varchar(100) DEFAULT NULL COMMENT '楼盘地址',
	private String estateMoney; //varchar(50) DEFAULT NULL COMMENT '金额',
//	private String estateUserInfoId; //varchar(50) DEFAULT NULL COMMENT '案场id',
//	private String estateUserInfoName; //案场的账号
	private String commissionRate; //varchar(50) DEFAULT NULL COMMENT '佣金比例',
	
	
	
	
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getPreparationUserId() {
		return preparationUserId;
	}
	public void setPreparationUserId(String preparationUserId) {
		this.preparationUserId = preparationUserId;
	}
	public String getBackupsId() {
		return backupsId;
	}
	public void setBackupsId(String backupsId) {
		this.backupsId = backupsId;
	}
	public String getPreparationCompany() {
		return preparationCompany;
	}
	public void setPreparationCompany(String preparationCompany) {
		this.preparationCompany = preparationCompany;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApplyFlag() {
		return applyFlag;
	}
	public void setApplyFlag(String applyFlag) {
		this.applyFlag = applyFlag;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public String getIntentionDes() {
		return intentionDes;
	}
	public void setIntentionDes(String intentionDes) {
		this.intentionDes = intentionDes;
	}
	public Timestamp getTakeLookTime() {
		return takeLookTime;
	}
	public void setTakeLookTime(Timestamp takeLookTime) {
		this.takeLookTime = takeLookTime;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getSpecificDes() {
		return specificDes;
	}
	public void setSpecificDes(String specificDes) {
		this.specificDes = specificDes;
	}
	public String getTsDes() {
		return tsDes;
	}
	public void setTsDes(String tsDes) {
		this.tsDes = tsDes;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getCommissionMoney() {
		return commissionMoney;
	}
	public void setCommissionMoney(String commissionMoney) {
		this.commissionMoney = commissionMoney;
	}

	
	public Timestamp getTsDate() {
		return tsDate;
	}
	public void setTsDate(Timestamp tsDate) {
		this.tsDate = tsDate;
	}
	public String getEstateId() {
		return estateId;
	}
	public void setEstateId(String estateId) {
		this.estateId = estateId;
	}
	public String getEstateName() {
		return estateName;
	}
	public void setEstateName(String estateName) {
		this.estateName = estateName;
	}
	public String getEstateDes() {
		return estateDes;
	}
	public void setEstateDes(String estateDes) {
		this.estateDes = estateDes;
	}
	public String getEstateAddress() {
		return estateAddress;
	}
	public void setEstateAddress(String estateAddress) {
		this.estateAddress = estateAddress;
	}
	public String getEstateMoney() {
		return estateMoney;
	}
	public void setEstateMoney(String estateMoney) {
		this.estateMoney = estateMoney;
	}
	
	public String getCommissionRate() {
		return commissionRate;
	}
	public void setCommissionRate(String commissionRate) {
		this.commissionRate = commissionRate;
	}
	@Override
	public String toString() {
		return "RecordsInfo [id=" + id + ", telno=" + telno + ", cusName=" + cusName + ", preparationUserId="
				+ preparationUserId + ", backupsId=" + backupsId + ", preparationCompany=" + preparationCompany
				+ ", status=" + status + ", applyFlag=" + applyFlag + ", appointmentDate=" + appointmentDate
				+ ", appointmentTime=" + appointmentTime + ", intentionDes=" + intentionDes + ", createTime="
				+ createTime + ", takeLookTime=" + takeLookTime + ", roomNum=" + roomNum + ", specificDes="
				+ specificDes + ", tsDes=" + tsDes + ", area=" + area + ", totalMoney=" + totalMoney
				+ ", commissionMoney=" + commissionMoney + ", tsDate=" + tsDate + ", estateId=" + estateId
				+ ", estateName=" + estateName + ", estateDes=" + estateDes + ", estateAddress=" + estateAddress
				+ ", estateMoney=" + estateMoney + ", commissionRate=" + commissionRate + "]";
	}


	
	
	
	
	
	
}
