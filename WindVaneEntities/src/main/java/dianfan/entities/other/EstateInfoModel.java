package dianfan.entities.other;

/**
 * @ClassName EstateInfoModel
 * @Description 楼盘下路框使用的
 * @author sz
 * @date 2018年8月30日 下午4:27:36
 */
public class EstateInfoModel {

	private String id; //varchar(50) NOT NULL COMMENT '主键id',
	private String estateName; //varchar(50) DEFAULT NULL COMMENT '楼盘名称',
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
