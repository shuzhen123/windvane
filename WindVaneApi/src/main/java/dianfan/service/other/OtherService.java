package dianfan.service.other;

import dianfan.models.ResultBean;

public interface OtherService {

	/**
	 * @Title: findRoleList
	 * @Description: 获取可注册角色列表
	 * @return
	 * @throws:
	 * @time: 2018年8月22日 下午5:11:36
	 */
	ResultBean findRoleList();

	/**
	 * @Title: findEstateList
	 * @Description:  获取楼盘列表
	 * @return
	 * @throws:
	 * @time: 2018年8月22日 下午5:23:36
	 */
	ResultBean findEstateList();

	/**
	 * @Title: findCompany
	 * @Description: 获取公司列表
	 * @return
	 * @throws:
	 * @time: 2018年8月22日 下午5:46:39
	 */
	ResultBean findCompany();

}
