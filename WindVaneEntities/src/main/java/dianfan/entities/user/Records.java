package dianfan.entities.user;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @ClassName Records
 * @Description ������
 * @author sz
 * @date 2018��8��20�� ����5:00:39
 */
public class Records {
	private String id; // varchar(50) NOT NULL COMMENT '����id',
	private String telno; // varchar(50) DEFAULT NULL COMMENT '�����绰',
	private String cusName; // varchar(50) DEFAULT NULL COMMENT '�ͻ�����',
	
	private String preparationUserId; // varchar(50) DEFAULT NULL COMMENT '������',
	private String backupsId; // �����ߵĵ绰����(�˺�)
	
	private String status; //varchar(2) DEFAULT '01' COMMENT '״̬��01��δ����02��ȷ�ϵ������03���Ѵ���04��ȷ�ϳɽ���',
	
	private String estateId; //varchar(50) DEFAULT NULL COMMENT '¥��id',
	private String estateName; //¥������',
	
	private String applyFlag; //varchar(2) DEFAULT NULL COMMENT '��˱�־��01:����� 02�����ͨ�� 03 ����˲�ͨ����',
	private Date appointmentDate; //date DEFAULT NULL COMMENT 'ԤԼ����',
	private String appointmentTime; //varchar(50) DEFAULT NULL COMMENT 'ԤԼʱ�䷶Χ',
	private String intentionDes; //varchar(1000) DEFAULT NULL COMMENT '�ͻ���������',
	private String createBy; //varchar(50) DEFAULT NULL COMMENT '������',
	private Timestamp updateTime; //timestamp NOT NULL DEFAULT CURESTAMP COMMENT '����ʱ��',
	private Timestamp createTime; //timestamp NOT NULL DEFAULT CURESTAMP COMMENT '����ʱ��',
	private String updateBy; //varchar(50) DEFAULT NULL COMMENT '������',
	private int entkbn; //int(1) DEFAULT '0' COMMENT '������Ч����(0:������Ч1:������Ч9:�߼�ɾ��)',
	private int version; //int(11) NOT NULL DEFAULT '0' COMMENT '�汾��',
	private Timestamp looktime; // ����ʱ��
	private TakeLook takeLook;// һ��һ������
	

	private String preparationCompany; // �����ߵĹ�˾
	private Timestamp tsDate; //�ɽ�ʱ��
	
	private String userName; // 人员姓名
	private String companyName; // 公司的名字
	private String acName; // 楼盘对应案场的名字
	private String acTelno; // 楼盘对应案场的的电话
	
	// 佣金相关显示
	private String commissionStatus; // 佣金状态
	private String commissionDes; //  佣金描述





	@Override
	public String toString() {
		return "Records [id=" + id + ", telno=" + telno + ", cusName=" + cusName + ", preparationUserId="
				+ preparationUserId + ", backupsId=" + backupsId + ", status=" + status + ", estateId=" + estateId
				+ ", estateName=" + estateName + ", applyFlag=" + applyFlag + ", appointmentDate=" + appointmentDate
				+ ", appointmentTime=" + appointmentTime + ", intentionDes=" + intentionDes + ", createBy=" + createBy
				+ ", updateTime=" + updateTime + ", createTime=" + createTime + ", updateBy=" + updateBy + ", entkbn="
				+ entkbn + ", version=" + version + ", looktime=" + looktime + ", takeLook=" + takeLook
				+ ", preparationCompany=" + preparationCompany + ", tsDate=" + tsDate + ", userName=" + userName
				+ ", companyName=" + companyName + ", acName=" + acName + ", acTelno=" + acTelno + ", commissionStatus="
				+ commissionStatus + ", commissionDes=" + commissionDes + "]";
	}



	public String getCommissionStatus() {
		return commissionStatus;
	}



	public void setCommissionStatus(String commissionStatus) {
		this.commissionStatus = commissionStatus;
	}



	public String getCommissionDes() {
		return commissionDes;
	}



	public void setCommissionDes(String commissionDes) {
		this.commissionDes = commissionDes;
	}



	public String getAcName() {
		return acName;
	}



	public void setAcName(String acName) {
		this.acName = acName;
	}



	public String getAcTelno() {
		return acTelno;
	}



	public void setAcTelno(String acTelno) {
		this.acTelno = acTelno;
	}



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
	public Timestamp getTsDate() {
		return tsDate;
	}
	public void setTsDate(Timestamp tsDate) {
		this.tsDate = tsDate;
	}
	public String getBackupsId() {
		return backupsId;
	}
	public void setBackupsId(String backupsId) {
		this.backupsId = backupsId;
	}
	public TakeLook getTakeLook() {
		return takeLook;
	}
	public void setTakeLook(TakeLook takeLook) {
		this.takeLook = takeLook;
	}
	public Timestamp getLooktime() {
		return looktime;
	}
	public void setLooktime(Timestamp looktime) {
		this.looktime = looktime;
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
	public String getEstateId() {
		return estateId;
	}
	public void setEstateId(String estateId) {
		this.estateId = estateId;
	}
	public String getApplyFlag() {
		return applyFlag;
	}
	public void setApplyFlag(String applyFlag) {
		this.applyFlag = applyFlag;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getEstateName() {
		return estateName;
	}
	public void setEstateName(String estateName) {
		this.estateName = estateName;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getPreparationCompany() {
		return preparationCompany;
	}
	public void setPreparationCompany(String preparationCompany) {
		this.preparationCompany = preparationCompany;
	}




	
}
