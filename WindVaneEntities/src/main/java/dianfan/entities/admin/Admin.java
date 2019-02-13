package dianfan.entities.admin;

import java.sql.Timestamp;

/**
 * @ClassName Admin
 * @Description ����Ա
 * @author sz
 * @date 2018��8��23�� ����11:40:58
 */
public class Admin {

	private String id; //varchar(50) NOT NULL COMMENT '����id',
	private String account; //varchar(50) DEFAULT NULL COMMENT '�˺�',
	private String password; //varchar(64) DEFAULT NULL COMMENT '����',
	
	private String roleId; //varchar(50) DEFAULT NULL COMMENT '��ɫid',
	private String roleName; //varchar(50) DEFAULT NULL COMMENT '��ɫid',
	
	private String companyId; //varchar(50) DEFAULT NULL COMMENT '��˾id',
	private String companyName; //varchar(50) DEFAULT NULL COMMENT '��˾id',
	
	private Timestamp createTime; //varchar(50) DEFAULT NULL COMMENT '��˾id',
	
	
	
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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	@Override
	public String toString() {
		return "Admin [id=" + id + ", account=" + account + ", password=" + password + ", roleId=" + roleId
				+ ", roleName=" + roleName + ", companyId=" + companyId + ", companyName=" + companyName
				+ ", createTime=" + createTime + "]";
	}



	
	
}
