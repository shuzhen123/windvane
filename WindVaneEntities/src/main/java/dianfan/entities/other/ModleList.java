package dianfan.entities.other;

import java.sql.Timestamp;

public class ModleList {

	private String id; // ����¥�̻��߹�˾ ��ID
	private String name ; // ¥�̻��߹�˾ 
	private String time ; // ʱ��
	private Integer count ; // ����
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "ModleList [id=" + id + ", name=" + name + ", time=" + time + ", count=" + count + "]";
	}
	
	
	

	
	
}
