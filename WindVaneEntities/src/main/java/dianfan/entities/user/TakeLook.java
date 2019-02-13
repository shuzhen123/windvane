package dianfan.entities.user;

import java.sql.Timestamp;

public class TakeLook {

	private String id; //varchar(50) NOT NULL COMMENT '����id',
	private String preparationId; //varchar(50) DEFAULT NULL COMMENT '����������id',
	private String takeLookUserId; //varchar(50) DEFAULT NULL COMMENT '�����߰���id',
	private Timestamp takeLookTime; //datetime DEFAULT NULL COMMENT '����ʱ��',
	private String preparationUserId; //varchar(50) DEFAULT NULL COMMENT '������',
	private String roomNum; //varchar(50) DEFAULT NULL COMMENT '����',
	private String specificDes; //text COMMENT '��������',
	private String tsDes; //text COMMENT '�ɽ�����',
	private String createBy; //varchar(50) DEFAULT NULL COMMENT '������',
	private Timestamp updateTime; //timestamp NOT NULL DEFAULT CU'����ʱ��',
	private String updateBy; //varchar(50) DEFAULT NULL COMMENT '������',
	private int entkbn; //int(1) DEFAULT '0' COMMENT '������Ч����(0:������Ч1:������Ч9:�߼�ɾ��)',
	private int version; //int(11) NOT NULL DEFAULT '0' COMMENT '�汾��',
	private String area; //varchar(50) DEFAULT NULL COMMENT '���',
	private String totalMoney; //varchar(50) DEFAULT NULL COMMENT '�ܼ�',
	private String commissionMoney; //varchar(50) DEFAULT NULL COMMENT 'Ӷ��',
	
	private Timestamp tsDate; // �ɽ�ʱ��
	private Timestamp createTime; // ����ʱ��
	
	private String cusName; // �ͻ�����
	private String telno;  // �����Ŀͻ��ĵ绰
	
	private String userName;  // 申请者的名字
	private String companyName;  // 申请者的名字
	
	
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPreparationId() {
		return preparationId;
	}
	public void setPreparationId(String preparationId) {
		this.preparationId = preparationId;
	}
	public String getTakeLookUserId() {
		return takeLookUserId;
	}
	public void setTakeLookUserId(String takeLookUserId) {
		this.takeLookUserId = takeLookUserId;
	}
	public Timestamp getTakeLookTime() {
		return takeLookTime;
	}
	public void setTakeLookTime(Timestamp takeLookTime) {
		this.takeLookTime = takeLookTime;
	}
	public String getPreparationUserId() {
		return preparationUserId;
	}
	public void setPreparationUserId(String preparationUserId) {
		this.preparationUserId = preparationUserId;
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
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public int getEntkbn() {
		return entkbn;
	}
	public void setEntkbn(int entkbn) {
		this.entkbn = entkbn;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
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
	
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "TakeLook [id=" + id + ", preparationId=" + preparationId + ", takeLookUserId=" + takeLookUserId
				+ ", takeLookTime=" + takeLookTime + ", preparationUserId=" + preparationUserId + ", roomNum=" + roomNum
				+ ", specificDes=" + specificDes + ", tsDes=" + tsDes + ", createBy=" + createBy + ", updateTime="
				+ updateTime + ", updateBy=" + updateBy + ", entkbn=" + entkbn + ", version=" + version + ", area="
				+ area + ", totalMoney=" + totalMoney + ", commissionMoney=" + commissionMoney + ", tsDate=" + tsDate
				+ ", createTime=" + createTime + ", cusName=" + cusName + ", telno=" + telno + ", userName=" + userName
				+ ", companyName=" + companyName + "]";
	}
	





	
	
}
