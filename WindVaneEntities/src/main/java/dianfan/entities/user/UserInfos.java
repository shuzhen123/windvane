package dianfan.entities.user;

import java.sql.Timestamp;

/**
 * @ClassName UserInfo
 * @Description �û���Ϣ��
 * @author sz
 * @date 2018��8��20�� ����2:03:17
 */
public class UserInfos {

	private String id; //varchar(50) NOT NULL COMMENT '����id',
	private String telno; //varchar(20) DEFAULT NULL COMMENT '�ֻ�����',
	private Integer locked; //tinyint(1) DEFAULT '0' COMMENT '�˻�����(falseĬ�ϲ�����)',
	private String pwd; //varchar(64) DEFAULT NULL COMMENT '����',
	private String applyFlag; //varchar(2) DEFAULT NULL COMMENT '��˱�־��01:����� 02�����ͨ�� 03 ����˲�ͨ����',
	private String applyUserId; //varchar(50) DEFAULT NULL COMMENT '��˹����û�id',
	private String applyUser; //varchar(50) DEFAULT NULL COMMENT '��˹����û�id',
	private Timestamp applyDate; //datetime DEFAULT NULL COMMENT '���ʱ��',
	private Timestamp createTime; //datetime DEFAULT NULL COMMENT '����ʱ��',
	
	private String roleId; //varchar(50) DEFAULT NULL COMMENT '��ɫid',
	private String roleName; //varchar(50) DEFAULT NULL COMMENT '��ɫid',
	
	private String companyId; //varchar(50) DEFAULT NULL COMMENT '��˾id',
	private String companyName; //��˾��
	
	private String estateId; //������Ӧ��¥����,
	private String estateName; //������Ӧ��¥����,
	
	private String createBy; //varchar(50) DEFAULT NULL COMMENT '������',
	private Timestamp updateTime; //timestamp NOT NULL DEFAULT CURRENT_TIESTAMP COMMENT '����ʱ��',
	private String updateBy; //varchar(50) DEFAULT NULL COMMENT '������',
	private int entkbn; //int(1) DEFAULT '0' COMMENT '������Ч����(0:������Ч1:������Ч9:�߼�ɾ��)',
	private String headerName; //varchar(50) DEFAULT NULL COMMENT '����������',
	
	private String userName; //用户的名字
	
	
	
	
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEstateName() {
		return estateName;
	}
	public void setEstateName(String estateName) {
		this.estateName = estateName;
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
	public Integer getLocked() {
		return locked;
	}
	public void setLocked(Integer locked) {
		this.locked = locked;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getApplyFlag() {
		return applyFlag;
	}
	public void setApplyFlag(String applyFlag) {
		this.applyFlag = applyFlag;
	}
	public String getApplyUserId() {
		return applyUserId;
	}
	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}
	public Timestamp getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Timestamp applyDate) {
		this.applyDate = applyDate;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getEstateId() {
		return estateId;
	}
	public void setEstateId(String estateId) {
		this.estateId = estateId;
	}
	public String getApplyUser() {
		return applyUser;
	}
	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}
	@Override
	public String toString() {
		return "UserInfos [id=" + id + ", telno=" + telno + ", locked=" + locked + ", pwd=" + pwd + ", applyFlag="
				+ applyFlag + ", applyUserId=" + applyUserId + ", applyUser=" + applyUser + ", applyDate=" + applyDate
				+ ", createTime=" + createTime + ", roleId=" + roleId + ", roleName=" + roleName + ", companyId="
				+ companyId + ", companyName=" + companyName + ", estateId=" + estateId + ", estateName=" + estateName
				+ ", createBy=" + createBy + ", updateTime=" + updateTime + ", updateBy=" + updateBy + ", entkbn="
				+ entkbn + ", headerName=" + headerName + ", userName=" + userName + "]";
	}


	
	
}
