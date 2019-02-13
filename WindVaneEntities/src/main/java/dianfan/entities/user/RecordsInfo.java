package dianfan.entities.user;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @ClassName Records
 * @Description ������
 * @author sz
 * @date 2018��8��20�� ����5:00:39
 */
public class RecordsInfo {
	private String id; // varchar(50) NOT NULL COMMENT '����id',
	private String telno; // varchar(50) DEFAULT NULL COMMENT '�����绰',
	private String cusName; // varchar(50) DEFAULT NULL COMMENT '�ͻ�����',
	private String preparationUserId; // varchar(50) DEFAULT NULL COMMENT '������',
	private String backupsId; // �����ߵĵ绰����(�˺�)
	private String preparationCompany; // �����ߵĹ�˾
	private String status; //varchar(2) DEFAULT '01' COMMENT '״̬��01��δ����02��ȷ�ϵ������03���Ѵ���04��ȷ�ϳɽ���',
	private String applyFlag; //varchar(2) DEFAULT NULL COMMENT '��˱�־��01:����� 02�����ͨ�� 03 ����˲�ͨ����',
	private Date appointmentDate; //date DEFAULT NULL COMMENT 'ԤԼ����',
	private String appointmentTime; //varchar(50) DEFAULT NULL COMMENT 'ԤԼʱ�䷶Χ',
	private String intentionDes; //varchar(1000) DEFAULT NULL COMMENT '�ͻ���������',
	private Timestamp createTime; //timestamp NOT NULL DEFAULT CURESTAMP COMMENT '����ʱ��',
	//private TakeLook takeLook;// һ��һ������
	/* �������������Ҫ��ʾ������ */
	private Timestamp takeLookTime; //datetime DEFAULT NULL COMMENT '����ʱ��',
	private String roomNum; //varchar(50) DEFAULT NULL COMMENT '����',
	private String specificDes; //text COMMENT '��������',
	private String tsDes; //text COMMENT '�ɽ�����',
	private String area; //varchar(50) DEFAULT NULL COMMENT '���',
	private String totalMoney; //varchar(50) DEFAULT NULL COMMENT '�ܼ�',
	private String commissionMoney; //varchar(50) DEFAULT NULL COMMENT 'Ӷ��',
	private Timestamp tsDate; // �ɽ�ʱ��
	
	/* ¥�̵ľ�����Ϣ */ 
	private String estateId; //varchar(50) DEFAULT NULL COMMENT '¥��id',
	private String estateName; //¥������',
	private String estateDes; //text COMMENT '����',
	private String estateAddress; //varchar(100) DEFAULT NULL COMMENT '¥�̵�ַ',
	private String estateMoney; //varchar(50) DEFAULT NULL COMMENT '���',
//	private String estateUserInfoId; //varchar(50) DEFAULT NULL COMMENT '����id',
//	private String estateUserInfoName; //�������˺�
	private String commissionRate; //varchar(50) DEFAULT NULL COMMENT 'Ӷ�����',
	
	
	
	
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
