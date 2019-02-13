package dianfan.service.login;

import dianfan.models.ResultBean;

/**
 * @ClassName LoginService
 * @Description 
 * @author sz
 * @date 2018年8月23日 上午9:56:24
 */
public interface LoginService {

	/**
	 * @Title: getLoginAction
	 * @Description: 后台登录
	 * @param request request
	 * @param account 账号
	 * @param password 密码
	 * @return
	 * @throws:
	 * @time: 2018年8月23日 上午10:06:50
	 */
	ResultBean getLoginAction(String account, String password);

	
	/**
	 * @Title: findAdminList
	 * @Description: 获取管理员列表
	 * @param userid userid
	 * @param page 请求页
	 * @param pageSize 每页条数
	 * @param roleId 角色搜索
	 * @param account 账号搜索
	 * @param companyId 公司搜索
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 * @throws:
	 * @time: 2018年8月23日 下午3:36:49
	 */
	ResultBean findAdminList(String userid, int page, int pageSize,String account);


	/**
	 * @Title: addAdminPerson
	 * @Description: 添加管理员
	 * @param userid  userid
	 * @param account 账号
	 * @param password 密码
	 * @param roleid 角色ID
	 * @param companyid 公司ID
	 * @return
	 * @throws:
	 * @time: 2018年8月23日 下午5:12:13
	 */
	ResultBean addAdminPerson(String userid, String account, String password);


	/**
	 * @Title: editAdminPerson
	 * @Description: 修改管理员信息
	 * @param userid userid
	 * @param id 账号ID
	 * @param password 密码
	 * @param roleid 角色ID
	 * @param companyid 公司ID
	 * @return
	 * @throws:
	 * @time: 2018年8月23日 下午5:38:07
	 */
	ResultBean editAdminPerson(String userid, String id, String password);


	/**
	 * @Title: delAdminPerson
	 * @Description: 删除管理员
	 * @param userid userid
	 * @param ids 管理员ID
	 * @return
	 * @throws:
	 * @time: 2018年8月23日 下午6:07:07
	 */
	ResultBean delAdminPerson(String userid, String ids);


	/**
	 * @Title: findRoleList
	 * @Description: 获取角色列表
	 * @return
	 * @throws:
	 * @time: 2018年8月24日 下午12:46:53
	 */
	ResultBean findRoleList();



	
}
