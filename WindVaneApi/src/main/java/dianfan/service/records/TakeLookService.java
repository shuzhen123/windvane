package dianfan.service.records;

import dianfan.models.ResultBean;

/**
 * @ClassName TakeLookService
 * @Description 带看接口
 * @author sz
 * @date 2018年8月22日 上午10:51:39
 */
public interface TakeLookService {

	/**
	 * @Title: findTakeLookList
	 * @Description: 查看带看信息列表
	 * @param userid userid
	 * @return
	 * @throws:
	 * @time: 2018年8月22日 上午11:23:18
	 */
	ResultBean findTakeLookList(String userid,int page,int pageSize);

	
	/**
	 * @Title: upAffirmKnockdown
	 * @Description: 确认成交
	 * @param userid userid
	 * @param lookId 带看ID
	 * @param estateId 楼盘ID
	 * @param roomNum 成交房号
	 * @param area 面积
	 * @param totalMoney 成交总价
	 * @param commissionMoney 成交佣金
	 * @param tsDes 成交描述
	 * @param specificDes 具体描述 
	 * @param intentionDes  客户描述
	 * @return
	 * @throws:
	 * @time: 2018年8月22日 下午12:27:29
	 */
	ResultBean upAffirmKnockdown(String userid, String lookId, String estateId, String roomNum, String area,
			String totalMoney, String commissionMoney, String intentionDes, String specificDes , String tsDes,String commissionStatus,String commissionDes);
	
	
	/**
	 * @Title: upAffirmKnockdownInfo   
	 * @Description:  成交详情
	 * @param userid
	 * @param lookId
	 * @return         
	 * @throws
	 */
	ResultBean upAffirmKnockdownInfo(String userid, String lookId);


	/**
	 * @Title: findknockdownList
	 * @Description: 成交列表
	 * @param userid userid
	 * @return
	 * @throws:
	 * @time: 2018年8月22日 下午3:31:32
	 */
	ResultBean findknockdownList(String userid,int page,int pageSize,String type);


	/**
	 * @Title: editKnockdown
	 * @Description: 修改成交信息
	 * @param userid userid
	 * @param lookId 带看ID
	 * @param estateId 楼盘ID
	 * @param roomNum 成交房号
	 * @param area 面积
	 * @param totalMoney 成交总价
	 * @param commissionMoney 成交佣金
	 * @param tsDes 成交描述
	 * @param specificDes 具体描述 
	 * @param intentionDes  客户描述
	 * @return
	 * @throws:
	 * @time: 2018年8月28日 下午3:32:03
	 */
	ResultBean editKnockdown(String userid, String lookId, String estateId, String roomNum, String area,
			String totalMoney, String commissionMoney, String intentionDes, String specificDes, String tsDes
			,String commissionStatus,String commissionDes);

	
}
