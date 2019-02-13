package dianfan.service.company;

import java.text.ParseException;

import dianfan.models.ResultBean;

/**
 * @ClassName BackupsService
 * @Description 报备相关
 * @author sz
 * @date 2018年8月25日 下午2:39:08
 */
public interface BackupsService {

	/**
	 * @Title: findBackupsList
	 * @Description: 获取报备列表
	 * @param userid userid
	 * @param page 请求页面
	 * @param pageSize 每页条数
	 * @param telno 手机号码
	 * @param cusName 客户姓名
	 * @param applyFlag 审核状态
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 * @throws:
	 * @time: 2018年8月25日 下午3:01:54
	 */
	ResultBean findBackupsList(String userid, int page, int pageSize, String telno, String cusName, String applyFlag,
			String starttime, String endtime,String towerId,String companyId,String status,String userName,String commissionStatus);

	/**
	 * @Title: updataBackupsApprove
	 * @Description: 报备信息审核
	 * @param userid userid
	 * @param ids 报备的IDs
	 * @param flag 状态
	 * @return
	 * @throws:
	 * @time: 2018年8月25日 下午5:44:22
	 */
	ResultBean updataBackupsApprove(String userid, String id, String flag);

	/**
	 * @Title: getBackupsInfo
	 * @Description: 获取报备的详情
	 * @param userid userid
	 * @param id 报备的得id
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 上午10:31:24
	 */
	ResultBean getBackupsInfo(String userid, String id);

	/**
	 * @Title: findStatisticsList
	 * @Description: 获取统计列表
	 * @param userid userid
	 * @param flag 区分 (0: 按楼盘； 1： 按中介公司)
	 * @param status 报备状态 (01: 报备； 04： 成交)
	 * @param starttime 按开始时间
	 * @param endtime 按结束时间
	 * @return
	 * @throws ParseException 
	 * @throws:
	 * @time: 2018年8月31日 下午4:17:07
	 */
	ResultBean findStatisticsList(String userid, String flag, String starttime, String endtime, String status) throws ParseException;

	/**
	 * @Title: findBackupsListData
	 * @Description: 获取报备的列表（数据统计中使用）
	 * @param userid userid
	 * @param page 请求页
	 * @param pageSize 请求数量
	 * @param telno 手机号码
	 * @param cusName 名字搜索
	 * @param id 楼盘ID 或者 公司ID
	 * @return
	 * @throws:
	 * @time: 2018年9月3日 下午12:37:08
	 */
	ResultBean findBackupsListData(String userid, int page, int pageSize, String id, String time, String telno,
			String flag, String status, String cusName);

}
