package dianfan.entities.user;

public class Role {
	private String id; //varchar(50) NOT NULL COMMENT 'Ö÷¼üid',
	private String roleId; //varchar(50) DEFAULT NULL COMMENT ,
	private String descption; //varchar(100) DEFAULT NULL COMMENT 'ÃèÊö',
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getDescption() {
		return descption;
	}
	public void setDescption(String descption) {
		this.descption = descption;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", roleId=" + roleId + ", descption=" + descption + "]";
	}

	
}
