package dianfan.entities.other;

import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName Statistics
 * @Description ͳ�Ʒ��ز���
 * @author sz
 * @date 2018��8��31�� ����4:31:51
 */
public class StatisticsInfo {

	
	private Timestamp time ; //ʱ��
	private List<Model> infor  ; //����
	
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
