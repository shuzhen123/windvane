package dianfan.service.company;

import dianfan.models.ResultBean;

/**
 * @ClassName EstateService
 * @Description 楼盘相关
 * @author sz
 * @date 2018年8月27日 上午11:52:50
 */
public interface EstateService {

	/**
	 * @Title: findEstateList
	 * @Description: 获取楼盘列表
	 * @param userid userid
	 * @param page 请求页
	 * @param pageSize 每页条数
	 * @param estateName 按楼盘名字搜索
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return ResultBean
	 * @throws:
	 * @time: 2018年8月27日 下午12:02:03
	 */
	ResultBean findEstateList(String userid, int page, int pageSize, String estateName, String starttime,
			String endtime);

	/**
	 * @Title: addEstate
	 * @Description: 添加楼盘
	 * @param userid userid
	 * @param estateIcon 楼盘icon
	 * @param estateBanner 楼盘banner
	 * @param estateName 楼盘名称
	 * @param des 描述
	 * @param commissionRate 金额
	 * @param address 楼盘地址
	 * @return 
	 * @throws:
	 * @time: 2018年8月27日 下午1:07:55
	 */
	ResultBean addEstate(String userid, String estateIcon, String estateBanner, String estateName, String des,
			String commissionRate, String address,String money);

	/**
	 * @Title: editEstate
	 * @Description: 修改楼盘信息 
	 * @param userid userid
	 * @param estateId 楼盘id
	 * @param estateIcon 楼盘icon
	 * @param estateBanner 楼盘banner
	 * @param des 描述
	 * @param commissionRate 佣金
	 * @param money 金额
	 * @param address 楼盘地址
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午2:12:54
	 */
	ResultBean editEstate(String userid, String estateIcon, String estateBanner, String estateId, String des,
			String commissionRate, String address, String money,String estateName);

	/**
	 * @Title: delEstate
	 * @Description: 删除楼盘
	 * @param userid userid
	 * @param ids ids
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午2:29:19
	 */
	ResultBean delEstate(String userid, String ids);

	/**
	 * @Title: getEstateInfo
	 * @Description: 获取楼盘详情
	 * @param userid userid
	 * @param id 楼盘ID
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午2:40:51
	 */
	ResultBean getEstateInfo(String userid, String id);



}
