package dianfan.service.company;

import dianfan.models.ResultBean;

/**
 * @ClassName CompanyService
 * @Description 公司相关管理
 * @author sz
 * @date 2018年8月23日 下午6:53:32
 */
public interface CompanyService {

	/**
	 * @Title: findCompanyList
	 * @Description: 获取公司列表
	 * @param userid userid
	 * @param page 请求页
	 * @param pageSize 每页条数
	 * @param companyId 公司名字搜索
	 * @param headerId 公司负责人名字搜索
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 * @throws:
	 * @time: 2018年8月24日 下午1:53:55
	 */
	ResultBean findCompanyList(String userid, int page, int pageSize, String companyName, String headerName,
			String starttime, String endtime);

	/**
	 * @Title: addCompanyInfo
	 * @Description: 添加公司，已经账号
	 * @param userid userid
	 * @param companyName 公司名字
	 * @param headerName 负责人名字
	 * @param account 账号
	 * @param password 密码
	 * @return
	 * @throws:
	 * @time: 2018年8月24日 下午4:14:04
	 */
	ResultBean addCompanyInfo(String userid, String companyName, String headerName, String account, String password);

	/**
	 * @Title: editCompanyInfo
	 * @Description: 修改公司信息
	 * @param userid userid
	 * @param id id
	 * @param headerName 公司负责人
	 * @param password 密码
	 * @return
	 * @throws:
	 * @time: 2018年8月25日 上午10:39:57
	 */
	ResultBean editCompanyInfo(String userid, String id, String headerName, String password, String account , String companyName);

	/**
	 * @Title: delCompanyInfo
	 * @Description: 删除公司
	 * @param userid userid
	 * @param ids 公司IDs
	 * @return
	 * @throws:
	 * @time: 2018年8月25日 上午11:21:58
	 */
	ResultBean delCompanyInfo(String userid, String ids);

	/**
	 * @Title: getCompanyInfo
	 * @Description: 获取公司详情
	 * @param userid userid
	 * @param id 公司的ID
	 * @return
	 * @throws:
	 * @time: 2018年8月25日 下午12:00:47
	 */
	ResultBean getCompanyInfo(String userid, String id);


	
}
