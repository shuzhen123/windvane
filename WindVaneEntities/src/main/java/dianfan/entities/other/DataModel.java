package dianfan.entities.other;

/**
 * @ClassName: DataModel 
 * @Description: 注册 两字段返回模型
 * @author sz
 * @date 2018年9月20日 上午9:37:49
 */
public class DataModel {
	
	private String telno; // 手机号码
	private String applyFlag; // 审核状态
	
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getApplyFlag() {
		return applyFlag;
	}
	public void setApplyFlag(String applyFlag) {
		this.applyFlag = applyFlag;
	}
	@Override
	public String toString() {
		return "DataModel [telno=" + telno + ", applyFlag=" + applyFlag + "]";
	}
	

	

}
