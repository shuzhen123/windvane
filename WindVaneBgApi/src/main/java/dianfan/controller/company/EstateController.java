package dianfan.controller.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;


import dianfan.annotations.SystemControllerLog;
import dianfan.constant.ConstantIF;
import dianfan.constant.ResultBgMsg;
import dianfan.models.ResultBean;
import dianfan.models.TokenModel;
import dianfan.service.common.impl.RedisTokenService;
import dianfan.service.company.EstateService;
import dianfan.util.StringUtility;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName EstateController
 * @Description 楼盘相关
 * @author sz
 * @date 2018年8月27日 上午11:52:50
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/estate")
public class EstateController {

	/**
	 * 注入: #EstateService
	 */
	@Autowired
	private EstateService estateService;
	/**
	 * 注入: #RedisTokenService
	 */
	@Autowired
	private RedisTokenService redisTokenService;

	/**
	 * @Title: findEstateList
	 * @Description: 获取楼盘列表
	 * @param accesstoken
	 *            accesstoken
	 * @param page
	 *            请求页
	 * @param pageSize
	 *            每页条数
	 * @param estateName
	 *            按楼盘名字搜索
	 * @param starttime
	 *            开始时间
	 * @param endtime
	 *            结束时间
	 * @return ResultBean
	 * @throws:
	 * @time: 2018年8月27日 下午12:00:11
	 */
	@SystemControllerLog(method = "findEstateList", logtype = ConstantIF.LOG_TYPE_1, description = "获取楼盘列表")
	@RequestMapping(value = "/findEstateList", method = RequestMethod.POST)
	public @ResponseBody ResultBean findEstateList(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "请求页") @RequestParam(value = ConstantIF.PAGE, defaultValue = ConstantIF.PAGE_START_STR) int page,
			@ApiParam(value = "每页条数") @RequestParam(value = ConstantIF.PAGE_SIZE, defaultValue = ConstantIF.PAGE_OFFSET_STR) int pageSize,
			@ApiParam(value = "报楼盘名搜索") @RequestParam(value = "estateName", required = false) String estateName,
			@ApiParam(value = "按开始时间") @RequestParam(value = "starttime", required = false) String starttime,
			@ApiParam(value = "按结束时间") @RequestParam(value = "endtime", required = false) String endtime) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		result = estateService.findEstateList(token.getUserid(), page, pageSize, estateName, starttime, endtime);
		// 返回
		return result;

	}

	/**
	 * @Title: addEstate
	 * @Description: 添加楼盘
	 * @param accesstoken
	 *            accesstoken
	 * @param estateIcon
	 *            楼盘icon
	 * @param estateBanner
	 *            楼盘banner
	 * @param estateName
	 *            楼盘名称
	 * @param des
	 *            描述
	 * @param commissionRate
	 *            佣金
	 * @param money
	 *            金额
	 * @param address
	 *            楼盘地址
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午1:25:37
	 */
	@SystemControllerLog(method = "addEstate", logtype = ConstantIF.LOG_TYPE_1, description = "添加楼盘")
	@RequestMapping(value = "/addEstate", method = RequestMethod.POST)
	public @ResponseBody ResultBean addEstate(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "楼盘icon") @RequestParam(value = "estateIcon") String estateIcon,
			@ApiParam(value = "楼盘banner") @RequestParam(value = "estateBanner") String estateBanner,
			@ApiParam(value = "楼盘名称") @RequestParam(value = "estateName") String estateName,
			@ApiParam(value = "描述") @RequestParam(value = "des") String des,
			@ApiParam(value = "佣金") @RequestParam(value = "commissionRate") String commissionRate,
			@ApiParam(value = "金额") @RequestParam(value = "money") String money,
			@ApiParam(value = "楼盘地址") @RequestParam(value = "address") String address) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		// 参数错误
		if (StringUtility.isNull(estateIcon) || StringUtility.isNull(estateBanner) || StringUtility.isNull(estateName)
				|| StringUtility.isNull(des) || StringUtility.isNull(commissionRate) || StringUtility.isNull(address)) {
			return new ResultBean("501", ResultBgMsg.C_501);
		}
		result = estateService.addEstate(token.getUserid(), estateIcon, estateBanner, estateName, des, commissionRate,
				address, money);
		// 返回
		return result;
	}

	/**
	 * @Title: editEstate
	 * @Description:
	 * @param accesstoken
	 *            accesstoken
	 * @param estateId
	 *            楼盘id
	 * @param estateIcon
	 *            楼盘icon
	 * @param estateBanner
	 *            楼盘banner
	 * @param des
	 *            描述
	 * @param commissionRate
	 *            佣金
	 * @param money
	 *            金额
	 * @param address
	 *            楼盘地址
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午2:08:28
	 */
	@SystemControllerLog(method = "editEstate", logtype = ConstantIF.LOG_TYPE_1, description = "修改楼盘")
	@RequestMapping(value = "/editEstate", method = RequestMethod.POST)
	public @ResponseBody ResultBean editEstate(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "楼盘id") @RequestParam(value = "estateId") String estateId,
			@ApiParam(value = "楼盘名称") @RequestParam(value = "estateName") String estateName,
			@ApiParam(value = "楼盘icon") @RequestParam(value = "estateIcon") String estateIcon,
			@ApiParam(value = "楼盘banner") @RequestParam(value = "estateBanner") String estateBanner,
			@ApiParam(value = "描述") @RequestParam(value = "des") String des,
			@ApiParam(value = "佣金") @RequestParam(value = "commissionRate") String commissionRate,
			@ApiParam(value = "金额") @RequestParam(value = "money") String money,
			@ApiParam(value = "楼盘地址") @RequestParam(value = "address") String address) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		// 参数错误
		if (StringUtility.isNull(estateIcon) || StringUtility.isNull(estateBanner) || StringUtility.isNull(estateId)
				|| StringUtility.isNull(des) || StringUtility.isNull(commissionRate) || StringUtility.isNull(address)) {
			return new ResultBean("501", ResultBgMsg.C_501);
		}
		result = estateService.editEstate(token.getUserid(), estateIcon, estateBanner, estateId, des, commissionRate,
				address, money, estateName);

		// 返回
		return result;
	}

	/**
	 * @Title: delEstate
	 * @Description: 删除楼盘
	 * @param accesstoken
	 *            accesstoken
	 * @param ids
	 *            楼盘ids
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午2:28:52
	 */
	@SystemControllerLog(method = "delEstate", logtype = ConstantIF.LOG_TYPE_1, description = "删除楼盘")
	@RequestMapping(value = "/delEstate", method = RequestMethod.POST)
	public @ResponseBody ResultBean delEstate(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "楼盘ids") @RequestParam(value = "ids") String ids) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		// 参数错误
		if (StringUtility.isNull(ids)) {
			return new ResultBean("501", ResultBgMsg.C_501);
		}
		result = estateService.delEstate(token.getUserid(), ids);

		// 返回
		return result;
	}

	/**
	 * @Title: getEstateInfo
	 * @Description: 获取楼盘详情
	 * @param accesstoken
	 *            accesstoken
	 * @param id
	 *            楼盘id
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午2:28:52
	 */
	@SystemControllerLog(method = "getEstateInfo", logtype = ConstantIF.LOG_TYPE_1, description = "获取楼盘详情")
	@RequestMapping(value = "/getEstateInfo", method = RequestMethod.POST)
	public @ResponseBody ResultBean getEstateInfo(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "楼盘id") @RequestParam(value = "id") String id) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		// 参数错误
		if (StringUtility.isNull(id)) {
			return new ResultBean("501", ResultBgMsg.C_501);
		}
		result = estateService.getEstateInfo(token.getUserid(), id);

		// 返回
		return result;
	}

}
