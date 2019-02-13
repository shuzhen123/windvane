package dianfan.controller.records;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import dianfan.annotations.SystemControllerLog;
import dianfan.constant.ConstantIF;
import dianfan.constant.ResultApiMsg;
import dianfan.models.ResultBean;
import dianfan.models.TokenModel;
import dianfan.service.common.impl.RedisTokenService;
import dianfan.service.records.TakeLookService;
import dianfan.util.StringUtility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName TakeLookController
 * @Description 带看相关
 * @author sz
 * @date 2018年8月22日 上午10:41:45
 */
@Api(description = "带看相关")
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/takeLook")
public class TakeLookController {

	/**
	 * 注入: #RedisTokenService
	 */
	@Autowired
	private RedisTokenService redisTokenService;
	/**
	 * 注入: #TakeLookService
	 */
	@Autowired
	private TakeLookService takeLookService;

	/**
	 * @Title: takeLookList
	 * @Description: 带看信息列表
	 * @param accesstoken
	 *            accesstoken
	 * @return ResultBean
	 * @throws:
	 * @time: 2018年8月21日 下午2:17:11
	 */
	@SystemControllerLog(method = "takeLookList", logtype = ConstantIF.LOG_TYPE_1, description = "带看信息列表")
	@ApiOperation(value = "takeLookList", httpMethod = "POST", notes = "带看信息列表", response = ResultBean.class)
	@RequestMapping(value = "takeLookList", method = RequestMethod.POST)
	public @ResponseBody ResultBean takeLookList(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "请求页") @RequestParam(value = ConstantIF.PAGE, defaultValue = ConstantIF.PAGE_START_STR) int page,
			@ApiParam(value = "每页条数") @RequestParam(value = ConstantIF.PAGE_SIZE, defaultValue = ConstantIF.PAGE_OFFSET_STR) int pageSize) {
		ResultBean result = new ResultBean();
		// 1.获取token
		TokenModel token = redisTokenService.getToken(accesstoken);

		result = takeLookService.findTakeLookList(token.getUserid(), page, pageSize);

		return result;
	}

	/**
	 * @Title: affirmKnockdown
	 * @Description: 确认成交
	 * @param accesstoken
	 *            accesstoken
	 * @param lookId
	 *            带看ID
	 * @param estateId
	 *            楼盘ID
	 * @param roomNum
	 *            成交房号
	 * @param area
	 *            面积
	 * @param totalMoney
	 *            成交总价
	 * @param commissionMoney
	 *            成交佣金
	 * @return
	 * @throws:
	 * @time: 2018年8月22日 下午12:23:24
	 */
	@SystemControllerLog(method = "affirmKnockdown", logtype = ConstantIF.LOG_TYPE_1, description = "确认成交")
	@ApiOperation(value = "affirmKnockdown", httpMethod = "POST", notes = "确认成交", response = ResultBean.class)
	@RequestMapping(value = "affirmKnockdown", method = RequestMethod.POST)
	public @ResponseBody ResultBean affirmKnockdown(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "带看的ID") @RequestParam(value = "lookId") String lookId,
			@ApiParam(value = "楼盘的ID") @RequestParam(value = "estateId") String estateId,
			@ApiParam(value = "成交房号") @RequestParam(value = "roomNum") String roomNum,
			@ApiParam(value = "面积") @RequestParam(value = "area") String area,
			@ApiParam(value = "成交总价") @RequestParam(value = "totalMoney") String totalMoney,
			@ApiParam(value = "成交佣金") @RequestParam(value = "commissionMoney") String commissionMoney,
			@ApiParam(value = "客户描述") @RequestParam(value = "intentionDes") String intentionDes,
			@ApiParam(value = "具体描述") @RequestParam(value = "specificDes") String specificDes,
			@ApiParam(value = "结佣状态(默认0:未结佣1:已结佣)") @RequestParam(value = "commissionStatus", required = false) String commissionStatus,
			@ApiParam(value = "结佣描述") @RequestParam(value = "commissionDes", required = false) String commissionDes,
			@ApiParam(value = "成交描述") @RequestParam(value = "tsDes") String tsDes) {
		ResultBean result = new ResultBean();
		// 1.获取token
		TokenModel token = redisTokenService.getToken(accesstoken);
		if (StringUtility.isNull(lookId) || StringUtility.isNull(estateId) || StringUtility.isNull(roomNum)
				|| StringUtility.isNull(area) || StringUtility.isNull(totalMoney)
				|| StringUtility.isNull(commissionMoney)) {
			// 成交信息需填写完整
			return new ResultBean("1008", ResultApiMsg.C_1008);
		}
		
		result = takeLookService.upAffirmKnockdown(token.getUserid(), lookId, estateId, roomNum, area, totalMoney,
				commissionMoney, intentionDes, specificDes, tsDes,commissionStatus,commissionDes);

		return result;
	}

	/**
	 * @Title: editKnockdown
	 * @Description: 修改成交
	 * @param accesstoken
	 *            accesstoken
	 * @param lookId
	 *            带看ID
	 * @param estateId
	 *            楼盘ID
	 * @param roomNum
	 *            成交房号
	 * @param area
	 *            面积
	 * @param totalMoney
	 *            成交总价
	 * @param commissionMoney
	 *            成交佣金
	 * @return
	 * @throws:
	 * @time: 2018年8月22日 下午12:23:24
	 */
	@SystemControllerLog(method = "editKnockdown", logtype = ConstantIF.LOG_TYPE_1, description = "修改成交")
	@ApiOperation(value = "editKnockdown", httpMethod = "POST", notes = "修改成交", response = ResultBean.class)
	@RequestMapping(value = "editKnockdown", method = RequestMethod.POST)
	public @ResponseBody ResultBean editKnockdown(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "带看的ID") @RequestParam(value = "lookId") String lookId,
			@ApiParam(value = "楼盘的ID") @RequestParam(value = "estateId", required = false) String estateId,
			@ApiParam(value = "成交房号") @RequestParam(value = "roomNum", required = false) String roomNum,
			@ApiParam(value = "面积") @RequestParam(value = "area", required = false) String area,
			@ApiParam(value = "成交总价") @RequestParam(value = "totalMoney", required = false) String totalMoney,
			@ApiParam(value = "成交佣金") @RequestParam(value = "commissionMoney", required = false) String commissionMoney,
			@ApiParam(value = "客户描述") @RequestParam(value = "intentionDes", required = false) String intentionDes,
			@ApiParam(value = "结佣状态(默认0:未结佣1:已结佣)") @RequestParam(value = "commissionStatus", required = false) String commissionStatus,
			@ApiParam(value = "结佣描述") @RequestParam(value = "commissionDes", required = false) String commissionDes,
			@ApiParam(value = "具体描述") @RequestParam(value = "specificDes", required = false) String specificDes,
			@ApiParam(value = "成交描述") @RequestParam(value = "tsDes") String tsDes) {
		ResultBean result = new ResultBean();
		// 1.获取token
		TokenModel token = redisTokenService.getToken(accesstoken);
		if (StringUtility.isNull(lookId) || StringUtility.isNull(estateId) || StringUtility.isNull(roomNum)
				|| StringUtility.isNull(area) || StringUtility.isNull(totalMoney)
				|| StringUtility.isNull(commissionMoney)) {
			// 成交信息需填写完整
			return new ResultBean("1008", ResultApiMsg.C_1008);
		}

		result = takeLookService.editKnockdown(token.getUserid(), lookId, estateId, roomNum, area, totalMoney,
				commissionMoney, intentionDes, specificDes, tsDes,commissionStatus,commissionDes);

		return result;
	}

	/**
	 * @Title: knockdownList
	 * @Description: 成交列表
	 * @param accesstoken
	 *            accesstoken
	 * @return
	 * @throws:
	 * @time: 2018年8月22日 下午3:30:42
	 */
	@SystemControllerLog(method = "knockdownList", logtype = ConstantIF.LOG_TYPE_1, description = "成交列表")
	@ApiOperation(value = "knockdownList", httpMethod = "POST", notes = "成交列表", response = ResultBean.class)
	@RequestMapping(value = "knockdownList", method = RequestMethod.POST)
	public @ResponseBody ResultBean knockdownList(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "请求页") @RequestParam(value = ConstantIF.PAGE, defaultValue = ConstantIF.PAGE_START_STR) int page,
			@ApiParam(value = "结佣状态(默认0:未结佣1:已结佣)") @RequestParam(value = "type", required = false) String type,
			@ApiParam(value = "每页条数") @RequestParam(value = ConstantIF.PAGE_SIZE, defaultValue = ConstantIF.PAGE_OFFSET_STR) int pageSize) {
		ResultBean result = new ResultBean();
		// 1.获取token
		TokenModel token = redisTokenService.getToken(accesstoken);

		result = takeLookService.findknockdownList(token.getUserid(), page, pageSize,type);

		return result;

	}

	/**
	 * @Title: affirmKnockdownInfo
	 * @Description: 确认成交后的详情
	 * @param accesstoken
	 *            accesstoken
	 * @param lookId
	 *            带看ID
	 * @param estateId
	 *            楼盘ID
	 * @param roomNum
	 *            成交房号
	 * @param area
	 *            面积
	 * @param totalMoney
	 *            成交总价
	 * @param commissionMoney
	 *            成交佣金
	 * @return
	 * @throws:
	 * @time: 2018年8月22日 下午12:23:24
	 */
	@SystemControllerLog(method = "affirmKnockdownInfo", logtype = ConstantIF.LOG_TYPE_1, description = "确认成交")
	@ApiOperation(value = "affirmKnockdownInfo", httpMethod = "POST", notes = "确认成交", response = ResultBean.class)
	@RequestMapping(value = "affirmKnockdownInfo", method = RequestMethod.POST)
	public @ResponseBody ResultBean affirmKnockdownInfo(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "带看的ID") @RequestParam(value = "lookId") String lookId) {
		ResultBean result = new ResultBean();
		// 1.获取token
		TokenModel token = redisTokenService.getToken(accesstoken);
		if (StringUtility.isNull(lookId)) {
			// 成交信息需填写完整
			return new ResultBean("1008", ResultApiMsg.C_1008);
		}

		result = takeLookService.upAffirmKnockdownInfo(token.getUserid(), lookId);

		return result;
	}

}
