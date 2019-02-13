package dianfan.entities.other;

import java.sql.Timestamp;

public class ModleList {

	private String id; // 主键楼盘或者公司 的ID
	private String name ; // 楼盘或者公司 
	private String time ; // 时间
	private Integer count ; // 计数
	
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
