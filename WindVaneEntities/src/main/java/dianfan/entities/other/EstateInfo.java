package dianfan.entities.other;

import java.sql.Timestamp;

/**
 * @ClassName EstateInfo
 * @Description ¥����Ϣ
 * @author sz
 * @date 2018��8��22�� ����5:26:45
 */
public class EstateInfo {

	private String id; //varchar(50) NOT NULL COMMENT '����id',
	private String estateIcon; //varchar(50) DEFAULT NULL COMMENT '¥��icon',
	private String estateBanner; //varchar(500) DEFAULT NULL COMMENT '¥��banner',
	private String estateName; //varchar(50) DEFAULT NULL COMMENT '¥������',
	private String des; //text COMMENT '����',
	private String money; //varchar(50) DEFAULT NULL COMMENT '���',
	private String commissionRate; //varchar(50) DEFAULT NULL COMMENT 'Ӷ�����',
	private Timestamp updateTime; //timestamp NOT NULL DEFAULT CURRENT_TIMT '����ʱ��',
	private String updateBy; //varchar(50) DEFAULT NULL COMMENT '������',
	private String userInfoId; //varchar(50) DEFAULT NULL COMMENT '����id',
	private String address; //varchar(100) DEFAULT NULL COMMENT '¥�̵�ַ',
	private String userInfoName; //varchar(100) DEFAULT NULL COMMENT '¥�̵�ַ',
	private String userName; //驻场人员名字
	private Timestamp createTime; //datetime DEFAULT NULL COMMENT '����ʱ��',
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEstateIcon() {
		return estateIcon;
	}
	public void setEstateIcon(String estateIcon) {
		this.estateIcon = estateIcon;
	}
	public String getEstateBanner() {
		return estateBanner;
	}
	public void setEstateBanner(String estateBanner) {
		this.estateBanner = estateBanner;
	}
	public String getEstateName() {
		return estateName;
	}
	public void setEstateName(String estateName) {
		this.estateName = estateName;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCommissionRate() {
		return commissionRate;
	}
	public void setCommissionRate(String commissionRate) {
		this.commissionRate = commissionRate;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUserInfoId() {
		return userInfoId;
	}
	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getUserInfoName() {
		return userInfoName;
	}
	public void setUserInfoName(String userInfoName) {
		this.userInfoName = userInfoName;
	}
	@Override
	public String toString() {
		return "EstateInfo [id=" + id + ", estateIcon=" + estateIcon + ", estateBanner=" + estateBanner
				+ ", estateName=" + estateName + ", des=" + des + ", money=" + money + ", commissionRate="
				+ commissionRate + ", updateTime=" + updateTime + ", updateBy=" + updateBy + ", userInfoId="
				+ userInfoId + ", address=" + address + ", userInfoName=" + userInfoName + ", userName=" + userName
				+ ", createTime=" + createTime + "]";
	}


	
}
