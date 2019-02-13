package dianfan.controller.records;

import java.text.ParseException;

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
import dianfan.service.records.RecordsService;
import dianfan.util.RegexUtils;
import dianfan.util.StringUtility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName RecordsController
 * @Description 备案相关
 * @author sz
 * @date 2018年8月20日 下午3:59:33
 */
@Api(description = "备案相关")
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/records")
public class RecordsController {

	/**
	 * 注入： #RecordsService
	 */
	@Autowired
	private RecordsService recordsService;
	/**
	 * 注入: #RedisTokenService
	 */
	@Autowired
	private RedisTokenService redisTokenService;

	/**
	 * @Title: addRecords
	 * @Description: 添加备案信息
	 * @param accesstoken
	 * @param name
	 *            姓名
	 * @param telno
	 *            手机号码
	 * @param tower
	 *            楼盘
	 * @param time
	 *            时间
	 * @param describe
	 *            描述
	 * @return
	 * @throws ParseException
	 * @throws:
	 * @time: 2018年8月20日 下午4:15:15
	 */
	@SystemControllerLog(method = "addRecords", logtype = ConstantIF.LOG_TYPE_1, description = "添加备案")
	@ApiOperation(value = "addRecords", httpMethod = "POST", notes = "添加备案", response = ResultBean.class)
	@RequestMapping(value = "/addRecords", method = RequestMethod.POST)
	public @ResponseBody ResultBean addRecords(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "姓名") @RequestParam(value = "name") String name,
			@ApiParam(value = "手机号码") @RequestParam(value = "telno") String telno,
			@ApiParam(value = "楼盘") @RequestParam(value = "tower") String tower,
			@ApiParam(value = "预约时间") @RequestParam(value = "time") String time,
			@ApiParam(value = "客户描述") @RequestParam(value = "describe") String describe) throws ParseException {
		ResultBean result = new ResultBean();
		// 1.获取token
		TokenModel token = redisTokenService.getToken(accesstoken);
		// 验证入参
		if (!RegexUtils.phoneRegex(telno)) {
			// ！请输入正确的手机号码
			return new ResultBean("4008", ResultApiMsg.C_4008);
		}
		// 3.入参空值验证
		if (StringUtility.isNull(name) || StringUtility.isNull(tower) || StringUtility.isNull(time)
				|| StringUtility.isNull(describe)) {
			// ！备案信息请填写完整
			return new ResultBean("1005", ResultApiMsg.C_1005);
		}

		result = recordsService.addRecords(token.getUserid(), name, telno, tower, time, describe);

		return result;
	}

	/**
	 * @Title: editRecordsInfo
	 * @Description: 修改报备
	 * @param accesstoken
	 *            accesstoken
	 * @param id
	 *            id
	 * @param flag
	 *            状态
	 * @param name
	 *            姓名
	 * @param telno
	 *            手机号码
	 * @param tower
	 *            楼盘
	 * @param time
	 *            时间
	 * @param describe
	 *            描述
	 * @return
	 * @throws ParseException
	 * @throws:
	 * @time: 2018年8月28日 下午2:02:53
	 */
	@SystemControllerLog(method = "editRecordsInfo", logtype = ConstantIF.LOG_TYPE_1, description = "修改报备")
	@ApiOperation(value = "editRecordsInfo", httpMethod = "POST", notes = "添加备案", response = ResultBean.class)
	@RequestMapping(value = "/editRecordsInfo", method = RequestMethod.POST)
	public @ResponseBody ResultBean editRecordsInfo(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "主键ID") @RequestParam(value = "id") String id,
			@ApiParam(value = "报备的状态") @RequestParam(value = "flag") String flag,
			@ApiParam(value = "姓名") @RequestParam(value = "name", required = false) String name,
			@ApiParam(value = "手机号码") @RequestParam(value = "telno", required = false) String telno,
			@ApiParam(value = "楼盘") @RequestParam(value = "tower", required = false) String tower,
			@ApiParam(value = "预约时间") @RequestParam(value = "time", required = false) String time,
			@ApiParam(value = "客户描述") @RequestParam(value = "describe") String describe) throws ParseException {
		ResultBean result = new ResultBean();
		// 1.获取token
		TokenModel token = redisTokenService.getToken(accesstoken);
		// 验证入参
		if (!StringUtility.isNull(telno)) {
			if (!RegexUtils.phoneRegex(telno)) {
				// ！请输入正确的手机号码
				return new ResultBean("4008", ResultApiMsg.C_4008);
			}
		}

		// 3.入参空值验证
		if (StringUtility.isNull(id) || StringUtility.isNull(flag) || StringUtility.isNull(describe)) {
			// ！备案信息请填写完整
			return new ResultBean("1005", ResultApiMsg.C_1005);
		}

		result = recordsService.editRecords(token.getUserid(), id, flag, name, telno, tower, time, describe);

		return result;
	}

	/**
	 * @Title: recordsList
	 * @Description: 已报备信息列表
	 * @param accesstoken
	 *            accesstoken
	 * @return ResultBean
	 * @throws:
	 * @time: 2018年8月21日 下午2:17:11
	 */
	@SystemControllerLog(method = "recordsList", logtype = ConstantIF.LOG_TYPE_1, description = "已报备信息列表")
	@ApiOperation(value = "recordsList", httpMethod = "POST", notes = "已报备信息列表", response = ResultBean.class)
	@RequestMapping(value = "recordsList", method = RequestMethod.POST)
	public @ResponseBody ResultBean recordsList(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "请求页") @RequestParam(value = ConstantIF.PAGE, defaultValue = ConstantIF.PAGE_START_STR) int page,
			@ApiParam(value = "每页条数") @RequestParam(value = ConstantIF.PAGE_SIZE, defaultValue = ConstantIF.PAGE_OFFSET_STR) int pageSize) {
		ResultBean result = new ResultBean();
		// 1.获取token
		TokenModel token = redisTokenService.getToken(accesstoken);
		result = recordsService.findRecordsList(token.getUserid(), page, pageSize);

		return result;
	}

	/**
	 * @Title: editrecords
	 * @Description: 编辑完整报备信息
	 * @param accesstoken
	 *            accesstoken
	 * @param time
	 *            带看时间
	 * @param roomnum
	 *            房间号码
	 * @param intention
	 *            客户意向描述
	 * @param specific
	 *            具体描述
	 * @return
	 * @throws ParseException
	 * @throws:
	 * @time: 2018年8月21日 下午3:12:08
	 */
	@SystemControllerLog(method = "editrecords", logtype = ConstantIF.LOG_TYPE_1, description = "编辑完整报备信息")
	@ApiOperation(value = "editrecords", httpMethod = "POST", notes = "编辑完整报备信息", response = ResultBean.class)
	@RequestMapping(value = "editrecords", method = RequestMethod.POST)
	public @ResponseBody ResultBean editrecords(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "报备id") @RequestParam(value = "recordsid") String recordsid,
			@ApiParam(value = "带看时间") @RequestParam(value = "time") String time,
			@ApiParam(value = "房间号码") @RequestParam(value = "roomnum") String roomnum,
			@ApiParam(value = "客户意向描述") @RequestParam(value = "intention") String intention,
			@ApiParam(value = "具体描述") @RequestParam(value = "specific", required = false) String specific)
			throws ParseException {
		ResultBean result = new ResultBean();
		// 1.获取token
		TokenModel token = redisTokenService.getToken(accesstoken);

		result = recordsService.editrecords(token.getUserid(), time, roomnum, intention, specific, recordsid);

		return result;
	}

	/**
	 * @Title: recordsInfos
	 * @Description: 报备信息详情
	 * @param accesstoken
	 *            accesstoken
	 * @param recordsid
	 *            报备id
	 * @return
	 * @throws:
	 * @time: 2018年8月21日 下午4:42:01
	 */
	@SystemControllerLog(method = "recordsInfos", logtype = ConstantIF.LOG_TYPE_1, description = "报备信息详情")
	@ApiOperation(value = "recordsInfos", httpMethod = "POST", notes = "报备信息详情", response = ResultBean.class)
	@RequestMapping(value = "recordsInfos", method = RequestMethod.POST)
	public @ResponseBody ResultBean recordsInfos(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "报备id") @RequestParam(value = "recordsid") String recordsid) {
		ResultBean result = new ResultBean();
		// 1.获取token
		TokenModel token = redisTokenService.getToken(accesstoken);

		result = recordsService.getRecordsInfo(token.getUserid(), recordsid);

		return result;

	}

	/**
	 * @Title: lookInfos
	 * @Description: 信息详情
	 * @param accesstoken
	 *            accesstoken
	 * @param recordsid
	 *            报备id
	 * @return
	 * @throws:
	 * @time: 2018年8月21日 下午4:42:01
	 */
	@SystemControllerLog(method = "lookInfos", logtype = ConstantIF.LOG_TYPE_1, description = "报备信息详情")
	@ApiOperation(value = "lookInfos", httpMethod = "POST", notes = "报备信息详情", response = ResultBean.class)
	@RequestMapping(value = "lookInfos", method = RequestMethod.POST)
	public @ResponseBody ResultBean lookInfos(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "带看id") @RequestParam(value = "lookId") String lookId) {
		ResultBean result = new ResultBean();
		// 1.获取token
		TokenModel token = redisTokenService.getToken(accesstoken);
		if (StringUtils.isEmpty(token)) {
			// 登录信息已过期，请重新登录
			return new ResultBean("001", ResultApiMsg.C_001);
		}

		result = recordsService.getLookInfo(token.getUserid(), lookId);

		return result;

	}

	/**
	 * @Title: affirmTakeLook
	 * @Description: 确认带看
	 * @param accesstoken
	 *            accesstoken
	 * @param recordsid
	 *            报备id
	 * @return
	 * @throws ParseException
	 * @throws:
	 * @time: 2018年8月21日 下午4:42:01
	 */
	@SystemControllerLog(method = "affirmTakeLook", logtype = ConstantIF.LOG_TYPE_1, description = "确认带看")
	@ApiOperation(value = "affirmTakeLook", httpMethod = "POST", notes = "确认带看", response = ResultBean.class)
	@RequestMapping(value = "affirmTakeLook", method = RequestMethod.POST)
	public @ResponseBody ResultBean affirmTakeLook(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "报备id") @RequestParam(value = "recordsid") String recordsid,
			@ApiParam(value = "带看时间") @RequestParam(value = "time") String time,
			@ApiParam(value = "房间号码") @RequestParam(value = "roomnum") String roomnum,
			@ApiParam(value = "客户意向描述") @RequestParam(value = "intention") String intention,
			@ApiParam(value = "具体描述") @RequestParam(value = "specific", required = false) String specific)
			throws ParseException {
		ResultBean result = new ResultBean();
		// 1.获取token
		TokenModel token = redisTokenService.getToken(accesstoken);
		if (StringUtility.isNull(recordsid) || StringUtility.isNull(time) || StringUtility.isNull(roomnum)
				|| StringUtility.isNull(intention)) {
			// 请将信息填写完整
			return new ResultBean("1009", ResultApiMsg.C_1009);
		}

		result = recordsService.upAffirmTakeLook(token.getUserid(), recordsid, time, roomnum, intention, specific);

		return result;
	}

	/**
	 * @Title: editTakeLookInfo
	 * @Description: 修改带看
	 * @param accesstoken
	 *            accesstoken
	 * @param recordsid
	 *            报备的ID
	 * @param time
	 *            带看时间
	 * @param roomnum
	 *            房间号码
	 * @param intention
	 *            客户意向描述
	 * @param specific
	 *            具体描述
	 * @return
	 * @throws ParseException
	 * @throws:
	 * @time: 2018年8月28日 下午3:01:53
	 */
	@SystemControllerLog(method = "editTakeLookInfo", logtype = ConstantIF.LOG_TYPE_1, description = "修改带看")
	@ApiOperation(value = "editTakeLookInfo", httpMethod = "POST", notes = "修改带看", response = ResultBean.class)
	@RequestMapping(value = "editTakeLookInfo", method = RequestMethod.POST)
	public @ResponseBody ResultBean editTakeLookInfo(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "报备id") @RequestParam(value = "recordsid") String recordsid,
			@ApiParam(value = "带看时间") @RequestParam(value = "time", required = false) String time,
			@ApiParam(value = "房间号码") @RequestParam(value = "roomnum", required = false) String roomnum,
			@ApiParam(value = "客户意向描述") @RequestParam(value = "intention", required = false) String intention,
			@ApiParam(value = "具体描述") @RequestParam(value = "specific") String specific) throws ParseException {
		ResultBean result = new ResultBean();
		// 1.获取token
		TokenModel token = redisTokenService.getToken(accesstoken);
		if (StringUtility.isNull(recordsid) || StringUtility.isNull(time) || StringUtility.isNull(roomnum)
				|| StringUtility.isNull(intention)) {
			// 请将信息填写完整
			return new ResultBean("1009", ResultApiMsg.C_1009);
		}

		result = recordsService.editTakeLook(token.getUserid(), recordsid, time, roomnum, intention, specific);

		return result;
	}

}
