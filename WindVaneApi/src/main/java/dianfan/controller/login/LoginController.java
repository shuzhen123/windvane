package dianfan.controller.login;

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
import dianfan.constant.ResultApiMsg;
import dianfan.md5.MD5;
import dianfan.models.ResultBean;
import dianfan.service.logins.LoginService;
import dianfan.util.RegexUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName LoginController
 * @Description app登陆相关
 * @author sz
 * @date 2018年8月17日 下午3:49:08
 */
@Api(description = "登陆相关")
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/login")
public class LoginController {

	/**
	 * 注入： #LoginService
	 */
	@Autowired
	private LoginService loginService;

	/**
	 * @Title: registerSMS
	 * @Description: 发送注册手机验证码
	 * @param telno
	 *            手机号码
	 * @return
	 * @throws Exception
	 * @throws:
	 * @time: 2018年6月29日 下午3:16:51
	 */
	@SystemControllerLog(method = "registerSMS", logtype = ConstantIF.LOG_TYPE_1, description = "发送注册手机验证码")
	@ApiOperation(value = "发送注册手机验证码", httpMethod = "GET", notes = "发送注册手机验证码", response = ResultBean.class)
	@RequestMapping(value = "/registerSMS", method = RequestMethod.GET)
	@UnCheckedFilter
	public @ResponseBody ResultBean registerSMS(@ApiParam("手机号码") @RequestParam(value = "telno") String telno)
			throws Exception {

		// 1.验证手机号码格式
		if (!RegexUtils.phoneRegex(telno)) {
			// !手机号码格式不对
			return new ResultBean("4008", ResultApiMsg.C_4008);
		}
		// 2.调库验证，成功返回
		return loginService.registerSMS(telno);

	}

	/**
	 * @Title: register
	 * @Description: app注册
	 * @param telno
	 *            手机号
	 * @param smscode
	 *            验证码
	 * @param md5pwd
	 *            密码
	 * @param sort
	 *            类型
	 * @param company
	 *            公司
	 * @param principal
	 *            负责人
	 * @return
	 * @throws:
	 * @time: 2018年8月20日 上午11:16:48
	 */
	@UnCheckedFilter
	@SystemControllerLog(method = "register", logtype = ConstantIF.LOG_TYPE_1, description = "注册")
	@ApiOperation(value = "register", httpMethod = "POST", notes = "注册", response = ResultBean.class)
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody ResultBean register(@ApiParam("手机号码") @RequestParam(value = "telno") String telno,
			@ApiParam("验证码") @RequestParam(value = "smscode") String smscode,
			@ApiParam("设置的密码") @RequestParam(value = "pwd") String pwd,
			@ApiParam("名字") @RequestParam(value = "name") String name,
			@ApiParam("类别") @RequestParam(value = "sort") String sort,
			@ApiParam("公司名称") @RequestParam(value = "company") String company,
			@ApiParam("公司负责人") @RequestParam(value = "principal", required = false) String principal) {

		// 1.验证手机号码格式
		if (!RegexUtils.phoneRegex(telno)) {
			// !错误：手机号码格式不对
			return new ResultBean("4008", ResultApiMsg.C_4008);
		}
		if (StringUtils.isEmpty(smscode)) {
			// !错误：短信验证码不正确
			return new ResultBean("4011", ResultApiMsg.C_4011);
		}
		if (pwd.length() < 5 || pwd.length() > 16) {
			// !错误：密码长度应该为6~15位
			return new ResultBean("4012", ResultApiMsg.C_4012);
		}
		/* -密码MD5加密- */
		String md5pwd = MD5.string2MD5(pwd);

		// 3.注册
		return loginService.addRegister(telno, smscode, md5pwd, sort, company, principal,name);
	}

	/**
	 * @Title: getResetSMS
	 * @Description: 获取重置密码短信验证码
	 * @param telno
	 *            手机号码
	 * @return
	 * @throws Exception
	 * @throws:
	 * @time: 2018年6月29日 下午4:54:13
	 */
	@UnCheckedFilter
	@SystemControllerLog(method = "getResetSMS", logtype = ConstantIF.LOG_TYPE_1, description = "获取重置密码短信验证码")
	@ApiOperation(value = "getResetSMS", httpMethod = "GET", notes = "获取重置密码短信验证码", response = ResultBean.class)
	@RequestMapping(value = "/getResetSMS", method = RequestMethod.GET)
	public @ResponseBody ResultBean getResetSMS(@ApiParam("手机号码") @RequestParam(value = "telno") String telno)
			throws Exception {
		// 1.验证手机号码格式
		if (!RegexUtils.phoneRegex(telno)) {
			// !手机号码格式
			return new ResultBean("4008", ResultApiMsg.C_4008);
		}
		// 2.调库验证，成功返回
		return loginService.getResetSMS(telno);
	}

	/**
	 * @Title: resetPassword
	 * @Description: 验证码方式重置密码 (忘记密码)
	 * @param telno
	 *            手机号
	 * @param smscode
	 *            收到的重置密码
	 * @return
	 * @throws:
	 * @time: 2018年6月29日 下午10:34:14
	 */
	@UnCheckedFilter
	@SystemControllerLog(method = "resetPassword", logtype = ConstantIF.LOG_TYPE_1, description = "验证码方式重置密码")
	@ApiOperation(value = "resetPassword", httpMethod = "POST", notes = "验证码方式重置密码", response = ResultBean.class)
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public @ResponseBody ResultBean resetPassword(@ApiParam("手机号码") @RequestParam(value = "telno") String telno,
			@ApiParam("验证码") @RequestParam(value = "smscode") String smscode,
			@ApiParam("新密码") @RequestParam(value = "newPassword") String newPassword) {

		// 1.验证手机号码格式
		if (!RegexUtils.phoneRegex(telno)) {
			// !手机号码格式
			return new ResultBean("4008", ResultApiMsg.C_4008);
		}
		if (StringUtils.isEmpty(smscode)) {
			// !短信验证码不正确
			return new ResultBean("4011", ResultApiMsg.C_4011);
		}

		/* -密码MD5加密- */
		String md5pwd = MD5.string2MD5(newPassword);

		// 4.调库验证，成功返回
		return loginService.updatePasswordBysms(telno, smscode, md5pwd);

	}

	/**
	 * @Title: appLogin
	 * @Description: app登陆
	 * @param telno
	 *            手机号码
	 * @param password
	 *            密码
	 * @return
	 * @throws:
	 * @time: 2018年8月20日 下午2:35:05
	 */
	@UnCheckedFilter
	@SystemControllerLog(method = "appLogin", logtype = ConstantIF.LOG_TYPE_1, description = "app登陆")
	@ApiOperation(value = "appLogin", httpMethod = "POST", notes = "app登陆", response = ResultBean.class)
	@RequestMapping(value = "/appLogin", method = RequestMethod.POST)
	public @ResponseBody ResultBean appLogin(@ApiParam("手机号码") @RequestParam(value = "telno") String telno,
			@ApiParam("密码") @RequestParam(value = "password") String password) {
		ResultBean result = new ResultBean();

		/* -密码MD5加密- */
		String md5pwd = MD5.string2MD5(password);

		result = loginService.appLogin(telno, md5pwd);

		return result;
	}

}
