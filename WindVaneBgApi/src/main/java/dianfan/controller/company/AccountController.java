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
import dianfan.service.company.AccountService;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName BackupsManage
 * @Description 账号相关管理
 * @author sz
 * @date 2018年8月25日 下午2:37:48
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/account")
public class AccountController {

	/**
	 * 注入: #RedisTokenService
	 */
	@Autowired
	private RedisTokenService redisTokenService;
	@Autowired
	private AccountService accountService;

	/**
	 * @Title: findAccountList
	 * @Description: 获取账号列表
	 * @param accesstoken
	 *            accesstoken
	 * @param page
	 *            请求页面
	 * @param pageSize
	 *            每页请求条数
	 * @param telno
	 *            手机号
	 * @param flag
	 *            状态
	 * @param starttime
	 *            开始时间
	 * @param endtime
	 *            结束时间
	 * @return ResultBean
	 * @throws:
	 * @time: 2018年8月28日 下午6:48:47
	 */
	@SystemControllerLog(method = "findAccountList", logtype = ConstantIF.LOG_TYPE_1, description = "获取账号列表")
	@RequestMapping(value = "/findAccountList", method = RequestMethod.POST)
	public @ResponseBody ResultBean findAccountList(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "请求页") @RequestParam(value = ConstantIF.PAGE, defaultValue = ConstantIF.PAGE_START_STR) int page,
			@ApiParam(value = "每页条数") @RequestParam(value = ConstantIF.PAGE_SIZE, defaultValue = ConstantIF.PAGE_OFFSET_STR) int pageSize,
			@ApiParam(value = "电话搜索") @RequestParam(value = "telno", required = false) String telno,
			@ApiParam(value = "审核标志搜索") @RequestParam(value = "flag", required = false) String flag,
			@ApiParam(value = "公司名称搜索") @RequestParam(value = "companyName", required = false) String companyName,
			@ApiParam(value = "申请人名字搜索") @RequestParam(value = "applyName", required = false) String applyName,
			@ApiParam(value = "按开始时间") @RequestParam(value = "starttime", required = false) String starttime,
			@ApiParam(value = "按结束时间") @RequestParam(value = "endtime", required = false) String endtime) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);

		result = accountService.findAccountList(token.getUserid(), page, pageSize, telno, flag, starttime, endtime,companyName,applyName);
		// 返回
		return result;
	}

	/**
	 * @Title: editAccountFlag
	 * @Description: 账号审批
	 * @param accesstoken
	 *            accesstoken
	 * @param flag
	 *            状态
	 * @param ids
	 *            账号ids
	 * @return
	 * @throws:
	 * @time: 2018年8月29日 上午11:15:45
	 */
	@SystemControllerLog(method = "editAccountFlag", logtype = ConstantIF.LOG_TYPE_1, description = "账号审批")
	@RequestMapping(value = "/editAccountFlag", method = RequestMethod.POST)
	public @ResponseBody ResultBean editAccountFlag(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "审核状态(02：审核通过 03 ：审核不通过)") @RequestParam(value = "flag") String flag,
			@ApiParam(value = "审核账号的id") @RequestParam(value = "ids") String ids) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);

		result = accountService.editAccountFlag(token.getUserid(), flag, ids);
		// 返回
		return result;
	}

}
