package dianfan.entities.other;

/**
 * @ClassName AppRole
 * @Description app��ɫ�б�
 * @author sz
 * @date 2018��8��22�� ����5:14:20
 */
public class AppRole {
	
	private String id; //varchar(50) NOT NULL COMMENT '����id',
	private String roleId; //varchar(50) DEFAULT NULL COMMENT '��ɫ(01:���۾���,02���н� 03������ 04������ 05����˾06���ܹ�)',
	private String descption; //varchar(100) DEFAULT NULL COMMENT '����',
	
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
