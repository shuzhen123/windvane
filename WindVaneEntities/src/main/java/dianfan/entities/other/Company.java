package dianfan.entities.other;

import java.sql.Timestamp;

/**
 * @ClassName Company
 * @Description 公司信息
 * @author sz
 * @date 2018年8月22日 下午5:44:24
 */
public class Company {
	
	private String id; // varchar(50) NOT NULL COMMENT '主键id',
	private String companyName; // varchar(50) DEFAULT NULL COMMENT '公司名称',
	private String headerName; // varchar(50) DEFAULT NULL COMMENT '负责人姓名',
	private String createBy; // 创建者
	private Timestamp createTime; // 创建时间
	
	private String account; //公司账号
	private String roleName; // 账号角色
	
	
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
