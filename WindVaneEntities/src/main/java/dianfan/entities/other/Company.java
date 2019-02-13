package dianfan.entities.other;

import java.sql.Timestamp;

/**
 * @ClassName Company
 * @Description ��˾��Ϣ
 * @author sz
 * @date 2018��8��22�� ����5:44:24
 */
public class Company {
	
	private String id; // varchar(50) NOT NULL COMMENT '����id',
	private String companyName; // varchar(50) DEFAULT NULL COMMENT '��˾����',
	private String headerName; // varchar(50) DEFAULT NULL COMMENT '����������',
	private String createBy; // ������
	private Timestamp createTime; // ����ʱ��
	
	private String account; //��˾�˺�
	private String roleName; // �˺Ž�ɫ
	
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName + ", headerName=" + headerName + ", createBy="
				+ createBy + ", createTime=" + createTime + ", account=" + account + ", roleName=" + roleName + "]";
	}


	
	
	 
}
