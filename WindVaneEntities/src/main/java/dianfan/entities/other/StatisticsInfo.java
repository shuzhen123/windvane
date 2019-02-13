package dianfan.entities.other;

import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName Statistics
 * @Description 统计返回参数
 * @author sz
 * @date 2018年8月31日 下午4:31:51
 */
public class StatisticsInfo {

	
	private Timestamp time ; //时间
	private List<Model> infor  ; //数据
	
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public List<Model> getInfor() {
		return infor;
	}
	public void setInfor(List<Model> infor) {
		this.infor = infor;
	}
	@Override
	public String toString() {
		return "StatisticsByPreparation [time=" + time + ", infor=" + infor + "]";
	}
	
	
}
