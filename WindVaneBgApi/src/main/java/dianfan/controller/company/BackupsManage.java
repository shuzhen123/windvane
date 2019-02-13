package dianfan.controller.company;

import java.text.ParseException;

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
import dianfan.service.company.BackupsService;
import dianfan.util.StringUtility;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName BackupsManage
 * @Description 报备相关管理
 * @author sz
 * @date 2018年8月25日 下午2:37:48
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/backups")
public class BackupsManage {

	/**
	 * 注入: #RedisTokenService
	 */
	@Autowired
	private RedisTokenService redisTokenService;

	/**
	 * 注入: #BackupsService
	 */
	@Autowired
	private BackupsService backupsService;

	/**
	 * @Title: findBackupsList
	 * @Description: 获取报备列表
	 * @param accesstoken
	 *            accesstoken
	 * @param page
	 *            请求页面
	 * @param pageSize
	 *            每页条数
	 * @param telno
	 *            手机号码
	 * @param cusName
	 *            客户姓名
	 * @param applyFlag
	 *            审核状态
	 * @param starttime
	 *            开始时间
	 * @param endtime
	 *            结束时间
	 * @return
	 * @throws:
	 * @time: 2018年8月25日 下午2:49:24
	 */
	@SystemControllerLog(method = "findBackupsList", logtype = ConstantIF.LOG_TYPE_1, description = "获取报备列表")
	@RequestMapping(value = "/findBackupsList", method = RequestMethod.POST)
	public @ResponseBody ResultBean findBackupsList(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "请求页") @RequestParam(value = ConstantIF.PAGE, defaultValue = ConstantIF.PAGE_START_STR) int page,
			@ApiParam(value = "每页条数") @RequestParam(value = ConstantIF.PAGE_SIZE, defaultValue = ConstantIF.PAGE_OFFSET_STR) int pageSize,
			@ApiParam(value = "报备电话搜索") @RequestParam(value = "telno", required = false) String telno,
			@ApiParam(value = "报备人名字搜索") @RequestParam(value = "userName", required = false) String userName,
			@ApiParam(value = "客户姓名搜索") @RequestParam(value = "cusName", required = false) String cusName,
			@ApiParam(value = "审核标志搜索") @RequestParam(value = "applyFlag", required = false) String applyFlag,
			@ApiParam(value = "按开始时间") @RequestParam(value = "starttime", required = false) String starttime,
			@ApiParam(value = "按结束时间") @RequestParam(value = "endtime", required = false) String endtime,
			@ApiParam(value = "结佣状态(默认0:未结佣1:已结佣)") @RequestParam(value = "commissionStatus", required = false) String commissionStatus,
			@ApiParam(value = "楼盘搜索") @RequestParam(value = "towerId", required = false) String towerId,
			@ApiParam(value = "中介公司搜索") @RequestParam(value = "companyId", required = false) String companyId,
			@ApiParam(value = "报备状态搜索") @RequestParam(value = "status", required = false) String status) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		result = backupsService.findBackupsList(token.getUserid(), page, pageSize, telno, cusName, applyFlag, starttime,
				endtime, towerId, companyId, status,userName,commissionStatus);
		// 返回
		return result;

	}

	/**
	 * @Title: findBackupsListData
	 * @Description: 获取报备列表 (数据统计)
	 * @param accesstoken
	 *            accesstoken
	 * @param page
	 *            请求页面
	 * @param pageSize
	 *            每页条数
	 * @param telno
	 *            手机号码
	 * @param cusName
	 *            客户姓名
	 * @param applyFlag
	 *            审核状态
	 * @param starttime
	 *            开始时间
	 * @param endtime
	 *            结束时间
	 * @return
	 * @throws:
	 * @time: 2018年8月25日 下午2:49:24
	 */
	@SystemControllerLog(method = "findBackupsListData", logtype = ConstantIF.LOG_TYPE_1, description = "获取报备列表(数据统计)")
	@RequestMapping(value = "/findBackupsListData", method = RequestMethod.POST)
	public @ResponseBody ResultBean findBackupsListData(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "请求页") @RequestParam(value = ConstantIF.PAGE, defaultValue = ConstantIF.PAGE_START_STR) int page,
			@ApiParam(value = "每页条数") @RequestParam(value = ConstantIF.PAGE_SIZE, defaultValue = ConstantIF.PAGE_OFFSET_STR) int pageSize,
			@ApiParam(value = "楼盘/公司的id") @RequestParam(value = "id") String id,
			@ApiParam(value = "当天时间") @RequestParam(value = "time") String time,
			@ApiParam(value = "区分 (0: 按楼盘； 1： 按中介公司)") @RequestParam(value = "flag") String flag,
			@ApiParam(value = "报备状态 (01: 报备； 04： 成交)") @RequestParam(value = "status") String status,
			@ApiParam(value = "客户姓名搜索") @RequestParam(value = "cusName", required = false) String cusName,
			@ApiParam(value = "报备电话搜索") @RequestParam(value = "telno", required = false) String telno) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		result = backupsService.findBackupsListData(token.getUserid(), page, pageSize, id, time, telno, flag, status,
				cusName);
		// 返回
		return result;

	}

	/**
	 * @Title: findStatisticsList
	 * @Description: 获取统计列表数量
	 * @param accesstoken
	 *            accesstoken
	 * @param flag
	 *            区分 (0: 按楼盘； 1： 按中介公司)
	 * @param status
	 *            报备状态 (01: 报备； 04： 成交)
	 * @param starttime
	 *            按开始时间
	 * @param endtime
	 *            按结束时间
	 * @return
	 * @throws ParseException
	 * @throws:
	 * @time: 2018年8月31日 下午4:16:37
	 */
	@SystemControllerLog(method = "findStatisticsList", logtype = ConstantIF.LOG_TYPE_1, description = "获取统计列表")
	@RequestMapping(value = "/findStatisticsList", method = RequestMethod.POST)
	public @ResponseBody ResultBean findStatisticsList(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "区分 (0: 按楼盘； 1： 按中介公司)") @RequestParam(value = "flag") String flag,
			@ApiParam(value = "报备状态 (01: 报备； 04： 成交)") @RequestParam(value = "status") String status,
			@ApiParam(value = "按开始时间") @RequestParam(value = "starttime") String starttime,
			@ApiParam(value = "按结束时间") @RequestParam(value = "endtime") String endtime) throws ParseException {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		result = backupsService.findStatisticsList(token.getUserid(), flag, starttime, endtime, status);
		// 返回
		return result;

	}

	/**
	 * @Title: getBackupsInfo
	 * @Description: 获取报备的详情
	 * @param accesstoken
	 *            accesstoken
	 * @param id
	 *            id 报备的ID
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 上午10:29:56
	 */
	@SystemControllerLog(method = "getBackupsInfo", logtype = ConstantIF.LOG_TYPE_1, description = "获取报备的详情")
	@RequestMapping(value = "/getBackupsInfo", method = RequestMethod.POST)
	public @ResponseBody ResultBean getBackupsInfo(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "报备的ID") @RequestParam(value = "id") String id) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		if (StringUtility.isNull(id)) {
			// 参数错误
			return new ResultBean("501", ResultBgMsg.C_501);
		}
		//
		result = backupsService.getBackupsInfo(token.getUserid(), id);
		// 返回
		return result;

	}

	/**
	 * @Title: updataBackupsApprove
	 * @Description: 报备信息审核
	 * @param accesstoken
	 *            accesstoken
	 * @param ids
	 *            报备IDs
	 * @param flag
	 *            审核状态
	 * @return
	 * @throws:
	 * @time: 2018年8月25日 下午5:12:47
	 */
	@SystemControllerLog(method = "updataBackupsApprove", logtype = ConstantIF.LOG_TYPE_1, description = "报备信息审核")
	@RequestMapping(value = "/updataBackupsApprove", method = RequestMethod.POST)
	public @ResponseBody ResultBean updataBackupsApprove(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "报备的ID") @RequestParam(value = "id") String id,
			@ApiParam(value = "审核状态") @RequestParam(value = "flag") String flag) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		if (org.springframework.util.StringUtils.isEmpty(token)) {
			// 登陆信息已经过期
			return new ResultBean("001", ResultBgMsg.C_001);
		}
		if (StringUtility.isNull(id) || StringUtility.isNull(flag)) {
			// 参数错误
			return new ResultBean("501", ResultBgMsg.C_501);
		}
		//
		result = backupsService.updataBackupsApprove(token.getUserid(), id, flag);
		// 返回
		return result;
	}

}
