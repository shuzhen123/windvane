package dianfan.entities.other;

/**
 * @ClassName AppRole
 * @Description app角色列表
 * @author sz
 * @date 2018年8月22日 下午5:14:20
 */
public class AppRole {
	
	private String id; //varchar(50) NOT NULL COMMENT '主键id',
	private String roleId; //varchar(50) DEFAULT NULL COMMENT '角色(01:销售经理,02：中介 03：分销 04：案场 05：公司06：总管)',
	private String descption; //varchar(100) DEFAULT NULL COMMENT '描述',
	
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
		return "AppRole [id=" + id + ", roleId=" + roleId + ", descption=" + descption + "]";
	}
	
	
}
