package dianfan.entities.other;

/**
 * @ClassName EstateInfoModel
 * @Description ¥����·��ʹ�õ�
 * @author sz
 * @date 2018��8��30�� ����4:27:36
 */
public class EstateInfoModel {

	private String id; //varchar(50) NOT NULL COMMENT '����id',
	private String estateName; //varchar(50) DEFAULT NULL COMMENT '¥������',
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEstateName() {
		return estateName;
	}
	public void setEstateName(String estateName) {
		this.estateName = estateName;
	}
	@Override
	public String toString() {
		return "EstateInfoModel [id=" + id + ", estateName=" + estateName + "]";
	}
	
	
}
