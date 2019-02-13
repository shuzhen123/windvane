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
import dianfan.annotations.UnCheckedFilter;
import dianfan.constant.ConstantIF;
import dianfan.constant.ResultApiMsg;
import dianfan.constant.ResultBgMsg;
import dianfan.md5.MD5;
import dianfan.models.ResultBean;
import dianfan.models.TokenModel;
import dianfan.service.common.impl.RedisTokenService;
import dianfan.service.company.StationedService;
import dianfan.util.RegexUtils;
import dianfan.util.StringUtility;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName StationedController
 * @Description 驻场相关Controller
 * @author sz
 * @date 2018年8月27日 上午11:30:43
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/stationed")
public class StationedController {

	/**
	 * 注入: #RedisTokenService
	 */
	@Autowired
	private RedisTokenService redisTokenService;

	/**
	 * 注入: #StationedService
	 */
	@Autowired
	private StationedService stationedService;

	/**
	 * @Title: findStationedList
	 * @Description: 获取驻场人员列表
	 * @param accesstoken
	 *            accesstoken
	 * @param page
	 *            请求页
	 * @param pageSize
	 *            每页条数
	 * @param telno
	 *            电话搜索
	 * @param company
	 *            公司搜索
	 * @param starttime
	 *            按开始时间
	 * @param endtime
	 *            按结束时间
	 * @return ResultBean
	 * @throws:
	 * @time: 2018年8月27日 下午3:28:47
	 */
	@SystemControllerLog(method = "findStationedList", logtype = ConstantIF.LOG_TYPE_1, description = "获取驻场人员列表")
	@RequestMapping(value = "/findStationedList", method = RequestMethod.POST)
	public @ResponseBody ResultBean findStationedList(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "请求页") @RequestParam(value = ConstantIF.PAGE, defaultValue = ConstantIF.PAGE_START_STR) int page,
			@ApiParam(value = "每页条数") @RequestParam(value = ConstantIF.PAGE_SIZE, defaultValue = ConstantIF.PAGE_OFFSET_STR) int pageSize,
			@ApiParam(value = "电话搜索") @RequestParam(value = "telno", required = false) String telno,
			@ApiParam(value = "公司搜索") @RequestParam(value = "company", required = false) String company,
			@ApiParam(value = "按开始时间") @RequestParam(value = "starttime", required = false) String starttime,
			@ApiParam(value = "按结束时间") @RequestParam(value = "endtime", required = false) String endtime) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		result = stationedService.findStationedList(token.getUserid(), page, pageSize, telno, company, starttime,
				endtime);
		// 返回
		return result;

	}

	/**
	 * @Title: addStationed
	 * @Description: 添加驻场人员
	 * @param accesstoken
	 *            accesstoken
	 * @param telno
	 *            手机号
	 * @param pwd
	 *            密码
	 * @param roleId
	 *            角色
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午4:44:02
	 */
	@SystemControllerLog(method = "addStationed", logtype = ConstantIF.LOG_TYPE_1, description = "添加驻场人员")
	@RequestMapping(value = "/addStationed", method = RequestMethod.POST)
	public @ResponseBody ResultBean addStationed(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "电话号码") @RequestParam(value = "telno") String telno,
			@ApiParam(value = "密码") @RequestParam(value = "pwd") String pwd,
			@ApiParam(value = "名字") @RequestParam(value = "name") String name,
			@ApiParam(value = "角色id") @RequestParam(value = "roleId") String roleId) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		if (StringUtility.isNull(telno) || StringUtility.isNull(pwd) || StringUtility.isNull(roleId)) {
			// 参数错误
			return new ResultBean("501", ResultBgMsg.C_501);
		}
		// 验证手机号码格式
		if (!RegexUtils.phoneRegex(telno)) {
			// !手机号码格式不对
			return new ResultBean("002", ResultBgMsg.C_002);
		}
		pwd = MD5.string2MD5(pwd);
		result = stationedService.addStationed(token.getUserid(), telno, pwd, roleId,name);

		// 返回
		return result;
	}

	/**
	 * @Title: editStationed
	 * @Description: 修改驻场人员
	 * @param accesstoken
	 *            accesstoken
	 * @param id
	 *            ID
	 * @param pwd
	 *            密码
	 * @param roleId
	 *            角色ID
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午5:28:49
	 */
	@SystemControllerLog(method = "editStationed", logtype = ConstantIF.LOG_TYPE_1, description = "修改驻场人员")
	@RequestMapping(value = "/editStationed", method = RequestMethod.POST)
	public @ResponseBody ResultBean editStationed(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "人员ID") @RequestParam(value = "id") String id,
			@ApiParam(value = "名字") @RequestParam(value = "name") String name,
			@ApiParam(value = "密码") @RequestParam(value = "pwd", required = false) String pwd,
			@ApiParam(value = "角色id") @RequestParam(value = "roleId") String roleId) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		if (StringUtility.isNull(id) || StringUtility.isNull(roleId)) {
			// 参数错误
			return new ResultBean("501", ResultBgMsg.C_501);
		}
		if (!StringUtility.isNull(pwd)) {
			pwd = MD5.string2MD5(pwd);
		}
		result = stationedService.editStationed(token.getUserid(), id, pwd, roleId,name);

		// 返回
		return result;
	}

	/**
	 * @Title: bindingStationed
	 * @Description: 绑定驻场到楼盘
	 * @param accesstoken
	 *            accesstoken
	 * @param id
	 *            人员ID
	 * @param estateId
	 *            楼盘ID
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午5:42:01
	 */
	@SystemControllerLog(method = "bindingStationed", logtype = ConstantIF.LOG_TYPE_1, description = "绑定驻场人员和楼盘")
	@RequestMapping(value = "/bindingStationed", method = RequestMethod.POST)
	public @ResponseBody ResultBean bindingStationed(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "人员ID") @RequestParam(value = "id") String id,
			@ApiParam(value = "楼盘id") @RequestParam(value = "estateId") String estateId) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		if (StringUtility.isNull(id) || StringUtility.isNull(estateId)) {
			// 参数错误
			return new ResultBean("501", ResultBgMsg.C_501);
		}
		result = stationedService.bindingStationed(token.getUserid(), id, estateId);

		// 返回
		return result;
	}

	/**
	 * @Title: relieveBinding
	 * @Description: 解除楼盘的绑定驻场
	 * @param accesstoken
	 * @param estateId
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午6:42:47
	 */
	@SystemControllerLog(method = "relieveBinding", logtype = ConstantIF.LOG_TYPE_1, description = "解除楼盘的绑定驻场")
	@RequestMapping(value = "/relieveBinding", method = RequestMethod.POST)
	public @ResponseBody ResultBean relieveBinding(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "楼盘id") @RequestParam(value = "estateId") String estateId) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		if (StringUtility.isNull(estateId)) {
			// 参数错误
			return new ResultBean("501", ResultBgMsg.C_501);
		}
		result = stationedService.relieveBinding(token.getUserid(), estateId);

		// 返回
		return result;
	}

	/**
	 * @Title: lockedAdmin
	 * @Description: 冻结/解冻驻场
	 * @param accesstoken
	 *            accesstoken
	 * @param id
	 *            驻场ID
	 * @param flag
	 *            状态(0解冻，1冻结)
	 * @return
	 * @throws:
	 * @time: 2018年8月30日 下午3:18:24
	 */
	@SystemControllerLog(method = "lockedAdmin", logtype = ConstantIF.LOG_TYPE_1, description = "冻结/解冻驻场")
	@RequestMapping(value = "/lockedAdmin", method = RequestMethod.POST)
	public @ResponseBody ResultBean lockedAdmin(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "驻场id") @RequestParam(value = "id") String id,
			@ApiParam(value = "状态(0解冻，1冻结)") @RequestParam(value = "flag") String flag) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		if (StringUtility.isNull(id) || StringUtility.isNull(flag)) {
			// 参数错误
			return new ResultBean("501", ResultBgMsg.C_501);
		}
		result = stationedService.updataLockedAdmin(token.getUserid(), id, flag);

		// 返回
		return result;
	}

	/**
	 * @Title: findEstateListInStationed
	 * @Description: 添加驻场的时候，获取楼盘列表的接口
	 * @return
	 * @throws:
	 * @time: 2018年8月30日 下午3:53:05
	 */
	@UnCheckedFilter
	@SystemControllerLog(method = "findEstateListInStationed", logtype = ConstantIF.LOG_TYPE_1, description = "冻结/解冻驻场")
	@RequestMapping(value = "/findEstateListInStationed", method = RequestMethod.POST)
	public @ResponseBody ResultBean findEstateListInStationed() {
		ResultBean result = new ResultBean();
		result = stationedService.findEstateListInStationed();
		return result;

	}

}
