package dianfan.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import dianfan.constant.ResultBgMsg;
import dianfan.md5.MD5;
import dianfan.models.ResultBean;
import dianfan.models.TokenModel;
import dianfan.service.common.impl.RedisService;
import dianfan.service.common.impl.RedisTokenService;
import dianfan.service.login.LoginService;
import dianfan.util.StringUtility;
import dianfan.util.VerifyCodeUtils;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName LoginManage
 * @Description
 * @author sz
 * @date 2018年8月23日 上午9:25:25
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/adminlogin")
public class LoginManage {

	/**
	 * 注入: #redisService
	 */
	@Autowired
	private RedisService<Object, Object> redisService;

	/**
	 * 注入: #RedisTokenService
	 */
	@Autowired
	private RedisTokenService redisTokenService;

	/**
	 * 注入: #LoginService
	 */
	@Autowired
	private LoginService loginService;

	/**
	 * @Title: getVerifyCode
	 * @Description: 获取图片验证码
	 * @param request
	 * @param response
	 * @throws:
	 * @time: 2018年7月16日 上午11:46:12
	 */
	@UnCheckedFilter
	@SystemControllerLog(method = "getVerifyCode", logtype = ConstantIF.LOG_TYPE_5, description = "获取图片验证码")
	@RequestMapping(value = "/getVerifyCode", method = RequestMethod.GET)
	public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) {

		String random = request.getParameter("random");

		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);

		// 生成图片
		int w = 100, h = 30;
		try {
			VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
			redisService.set(random, verifyCode, 300L);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @Title: loginAction
	 * @Description: 登录操作
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @param attributes
	 *            重定向控制器
	 * @return
	 * @throws:
	 * @time: 2018年7月16日 下午12:00:29
	 */
	@UnCheckedFilter
	@SystemControllerLog(method = "loginAction", logtype = ConstantIF.LOG_TYPE_5, description = "登录操作")
	@RequestMapping(value = "/loginAction", method = RequestMethod.POST)
	public @ResponseBody ResultBean loginAction(HttpServletRequest request,
			@ApiParam(value = "用户名") @RequestParam(value = "account") String account,
			@ApiParam(value = "密码") @RequestParam(value = "password") String password,
			@ApiParam(value = "图片验证码") @RequestParam(value = "verifycode") String verifycode,
			@ApiParam(value = "图片随机数") @RequestParam(value = "random") String random) {
		ResultBean result = new ResultBean();

		// 验证码验证
		if (verifycode == null) {
			// 验证码不正确
			return new ResultBean("4001", ResultBgMsg.C_4001);
		}
		// 取验证码
		String vcode = redisService.get(random);

		if (vcode == null || !StringUtils.equals(vcode.toLowerCase(), verifycode.toLowerCase())) {
			// 验证码不正确
			return new ResultBean("4001", ResultBgMsg.C_4001);
		}

		// 密码md5 加密
		password = MD5.string2MD5(password);

		result = loginService.getLoginAction(account, password);

		return result;

	}

	/**
	 * @Title: findAdminList
	 * @Description: 获取管理员列表
	 * @param accesstoken
	 *            accesstoken
	 * @param page
	 *            请求页
	 * @param pageSize
	 *            每页条数
	 * @param roleId
	 *            角色搜索
	 * @param account
	 *            账号搜索
	 * @param companyId
	 *            公司搜索
	 * @param starttime
	 *            开始时间
	 * @param endtime
	 *            结束时间
	 * @return ResultBean
	 * @throws:
	 * @time: 2018年8月23日 下午3:33:56
	 */
	@SystemControllerLog(method = "findAdminList", logtype = ConstantIF.LOG_TYPE_5, description = "获取管理员列表")
	@RequestMapping(value = "/findAdminList", method = RequestMethod.POST)
	public @ResponseBody ResultBean findAdminList(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "请求页") @RequestParam(value = ConstantIF.PAGE, defaultValue = ConstantIF.PAGE_START_STR) int page,
			@ApiParam(value = "每页条数") @RequestParam(value = ConstantIF.PAGE_SIZE, defaultValue = ConstantIF.PAGE_OFFSET_STR) int pageSize,
			@ApiParam(value = "账号搜索") @RequestParam(value = "account", required = false) String account) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);

		result = loginService.findAdminList(token.getUserid(), page, pageSize, account);
		// 返回
		return result;
	}

	/**
	 * @Title: addAdminPerson
	 * @Description: 添加管理员
	 * @param accesstoken
	 *            accesstoken
	 * @param account
	 *            账号
	 * @param password
	 *            密码
	 * @param roleid
	 *            角色ID
	 * @param companyid
	 *            公司ID
	 * @return
	 * @throws:
	 * @time: 2018年8月23日 下午5:09:07
	 */
	@SystemControllerLog(method = "addAdminPerson", logtype = ConstantIF.LOG_TYPE_5, description = "添加管理员")
	@RequestMapping(value = "/addAdminPerson", method = RequestMethod.POST)
	public @ResponseBody ResultBean addAdminPerson(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "账号") @RequestParam(value = "account") String account,
			@ApiParam(value = "账号密码") @RequestParam(value = "password") String password) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);

		// 入参验证
		if (StringUtility.isNull(account) || StringUtility.isNull(password)) {
			// 信息不完整
			return new ResultBean("1000", ResultBgMsg.C_1000);
		}

		// 密码md5 加密
		password = MD5.string2MD5(password);

		result = loginService.addAdminPerson(token.getUserid(), account, password);

		// 返回
		return result;
	}

	/**
	 * @Title: editAdminPerson
	 * @Description: 修改管理员
	 * @param accesstoken
	 *            accesstoken
	 * @param id
	 *            账号ID
	 * @param password
	 *            密码
	 * @param roleid
	 *            角色ID
	 * @param companyid
	 *            公司ID
	 * @return
	 * @throws:
	 * @time: 2018年8月23日 下午5:09:07
	 */
	@SystemControllerLog(method = "editAdminPerson", logtype = ConstantIF.LOG_TYPE_5, description = "修改管理员")
	@RequestMapping(value = "/editAdminPerson", method = RequestMethod.POST)
	public @ResponseBody ResultBean editAdminPerson(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "账号id") @RequestParam(value = "id") String id,
			@ApiParam(value = "账号密码") @RequestParam(value = "password") String password) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);

		// 入参验证
		if (StringUtility.isNull(id) || StringUtility.isNull(password)) {
			// 信息不完整
			return new ResultBean("1000", ResultBgMsg.C_1000);
		}

		// 密码md5 加密
		password = MD5.string2MD5(password);

		result = loginService.editAdminPerson(token.getUserid(), id, password);

		// 返回
		return result;
	}

	/**
	 * @Title: delAdminPerson
	 * @Description: 删除管理员
	 * @param accesstoken
	 *            accesstoken
	 * @param ids
	 *            账号IDs
	 * @return
	 * @throws:
	 * @time: 2018年8月23日 下午6:01:19
	 */
	@SystemControllerLog(method = "delAdminPerson", logtype = ConstantIF.LOG_TYPE_5, description = "删除管理员")
	@RequestMapping(value = "/delAdminPerson", method = RequestMethod.POST)
	public @ResponseBody ResultBean delAdminPerson(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "账号ids") @RequestParam(value = "ids") String ids) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);

		if (StringUtility.isNull(ids)) {
			// 参数错误
			return new ResultBean("501", ResultBgMsg.C_501);
		}
		result = loginService.delAdminPerson(token.getUserid(), ids);
		// 返回
		return result;
	}

	/**
	 * @Title: findRoleList
	 * @Description: 获取角色列表
	 * @return
	 * @throws:
	 * @time: 2018年8月24日 下午12:45:27
	 */
	@UnCheckedFilter
	@SystemControllerLog(method = "findRoleList", logtype = ConstantIF.LOG_TYPE_5, description = "获取角色列表")
	@RequestMapping(value = "/findRoleList", method = RequestMethod.POST)
	public @ResponseBody ResultBean findRoleList() {
		ResultBean result = new ResultBean();
		// 获取角色列表

		result = loginService.findRoleList();

		// 返回
		return result;
	}

}
