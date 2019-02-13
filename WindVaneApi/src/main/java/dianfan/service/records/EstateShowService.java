package dianfan.service.records;

import dianfan.models.ResultBean;

/**
 * @ClassName EstateShowService
 * @Description 楼盘相关
 * @author sz
 * @date 2018年8月31日 上午10:54:51
 */
public interface EstateShowService {

	/**
	 * @Title: findEstateShowList
	 * @Description: 获取楼盘列表
	 * @param userid userid
	 * @param page 起始页
	 * @param pageSize 每页条数
	 * @return
	 * @throws:
	 * @time: 2018年8月31日 上午11:00:22
	 */
	ResultBean findEstateShowList(int page, int pageSize);

	/**
	 * @Title: getEstateShowInfo
	 * @Description: 获取楼盘详情
	 * @param userid userid
	 * @param id 楼盘 id
	 * @return
	 * @throws:
	 * @time: 2018年8月31日 上午11:37:17
	 */
	ResultBean getEstateShowInfo(String id);

}
