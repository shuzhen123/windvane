package dianfan.service.company;

import dianfan.models.ResultBean;

/**
 * @ClassName StationedService
 * @Description 驻场相关Service
 * @author sz
 * @date 2018年8月27日 上午11:32:25
 */
public interface StationedService {

	/**
	 * @Title: findStationedList
	 * @Description: 获取驻场人员的列表
	 * @param userid userid
	 * @param page 请求页
	 * @param pageSize 每页条数
	 * @param telno 电话搜索
	 * @param company 公司搜索
	 * @param starttime 按开始时间
	 * @param endtime 按结束时间
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午3:30:53
	 */
	ResultBean findStationedList(String userid, int page, int pageSize, String telno, String company, String starttime,
			String endtime);

	/**
	 * @Title: addStationed
	 * @Description: 添加驻场人员信息
	 * @param userid userid
	 * @param telno 手机号
	 * @param pwd 密码
	 * @param roleId 角色
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午4:43:10
	 */
	ResultBean addStationed(String userid, String telno, String pwd, String roleId,String name);

	/**
	 * @Title: editStationed
	 * @Description: 修改驻场人员信息
	 * @param userid userid
	 * @param id id
	 * @param pwd 密码
	 * @param roleId 角色
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午5:30:14
	 */
	ResultBean editStationed(String userid, String id, String pwd, String roleId,String name);

	/**
	 * @Title: bindingStationed
	 * @Description: 绑定驻场到楼盘
	 * @param userid userid
	 * @param id 人员ID
	 * @param estateId 楼盘ID
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午5:42:37
	 */
	ResultBean bindingStationed(String userid, String id, String estateId);

	/**
	 * @Title: relieveBinding
	 * @Description: 解除楼盘的绑定驻场
	 * @param userid userid
	 * @param estateId 楼盘ID
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午6:43:03
	 */
	ResultBean relieveBinding(String userid, String estateId);

	/**
	 * @Title: updataLockedAdmin
	 * @Description: 冻结或者解冻驻场
	 * @param userid userid
	 * @param id 驻场ID
	 * @param flag 状态
	 * @return
	 * @throws:
	 * @time: 2018年8月30日 下午3:19:08
	 */
	ResultBean updataLockedAdmin(String userid, String id, String flag);

	/**
	 * @Title: findEstateListInStationed
	 * @Description: 添加驻场的时候获取楼盘列表
	 * @return
	 * @throws:
	 * @time: 2018年8月30日 下午3:55:04
	 */
	ResultBean findEstateListInStationed();

}
