package dianfan.service.company;

import dianfan.models.ResultBean;

/**
 * @ClassName AccountService
 * @Description 账号相关管理
 * @author sz
 * @date 2018年8月28日 上午10:05:08
 */
public interface AccountService {

	/**
	 * @Title: findAccountList
	 * @Description: 获取账号列表
	 * @param userid 用户ID
	 * @param page 请求页面
	 * @param pageSize 请求条数
	 * @param telno 手机号
	 * @param flag 状态
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return ResultBean
	 * @throws:
	 * @time: 2018年8月28日 下午6:51:18
	 */
	ResultBean findAccountList(String userid, int page, int pageSize, String telno, String flag, String starttime,
			String endtime,String companyName,String applyName);

	/**
	 * @Title: findAccountList
	 * @Description: 
	 * @param userid
	 * @param flag
	 * @param ids
	 * @return
	 * @throws:
	 * @time: 2018年8月29日 上午11:16:18
	 */
	ResultBean editAccountFlag(String userid, String flag, String ids);

}
