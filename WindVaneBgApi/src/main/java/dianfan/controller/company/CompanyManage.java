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
import dianfan.md5.MD5;
import dianfan.models.ResultBean;
import dianfan.models.TokenModel;
import dianfan.service.common.impl.RedisService;
import dianfan.service.common.impl.RedisTokenService;
import dianfan.service.company.CompanyService;
import dianfan.util.StringUtility;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName CompanyManage
 * @Description 公司相关管理
 * @author sz
 * @date 2018年8月23日 下午6:53:37
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/company")
public class CompanyManage {

	/**
	 * 注入：#CompanyService
	 */
	@Autowired
	private CompanyService companyService;

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
	 * @Title: findAdminList
	 * @Description: 获取管理员列表
	 * @param accesstoken
	 *            accesstoken
	 * @param page
	 *            请求页
	 * @param pageSize
	 *            每页条数
	 * @param companyId
	 *            公司名字搜索
	 * @param headerId
	 *            公司负责人名字搜索
	 * @param starttime
	 *            开始时间
	 * @param endtime
	 *            结束时间
	 * @return ResultBean
	 * @throws:
	 * @time: 2018年8月23日 下午3:33:56
	 */
	@SystemControllerLog(method = "findCompanyList", logtype = ConstantIF.LOG_TYPE_1, description = "获取公司列表")
	@RequestMapping(value = "/findCompanyList", method = RequestMethod.POST)
	public @ResponseBody ResultBean findCompanyList(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "请求页") @RequestParam(value = ConstantIF.PAGE, defaultValue = ConstantIF.PAGE_START_STR) int page,
			@ApiParam(value = "每页条数") @RequestParam(value = ConstantIF.PAGE_SIZE, defaultValue = ConstantIF.PAGE_OFFSET_STR) int pageSize,
			@ApiParam(value = "公司名字搜索") @RequestParam(value = "companyName", required = false) String companyName,
			@ApiParam(value = "公司负责人名字搜索") @RequestParam(value = "headerName", required = false) String headerName,
			@ApiParam(value = "按开始时间") @RequestParam(value = "starttime", required = false) String starttime,
			@ApiParam(value = "按结束时间") @RequestParam(value = "endtime", required = false) String endtime) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		result = companyService.findCompanyList(token.getUserid(), page, pageSize, companyName, headerName, starttime,
				endtime);
		// 返回
		return result;
	}

	/**
	 * @Title: addCompanyInfo
	 * @Description: 添加公司账号
	 * @param accesstoken
	 *            accesstoken
	 * @param companyName
	 *            公司名字
	 * @param headerName
	 *            负责人名字
	 * @param account
	 *            账号
	 * @param password
	 *            密码
	 * @return
	 * @throws:
	 * @time: 2018年8月24日 下午4:16:04
	 */
	@SystemControllerLog(method = "addCompanyInfo", logtype = ConstantIF.LOG_TYPE_1, description = "添加公司")
	@RequestMapping(value = "/addCompanyInfo", method = RequestMethod.POST)
	public @ResponseBody ResultBean addCompanyInfo(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "公司名字") @RequestParam(value = "companyName") String companyName,
			@ApiParam(value = "账号") @RequestParam(value = "account") String account,
			@ApiParam(value = "公司负责人名字") @RequestParam(value = "headerName") String headerName,
			@ApiParam(value = "密码") @RequestParam(value = "password") String password) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		// 入参验证
		if (StringUtility.isNull(companyName) || StringUtility.isNull(headerName) || StringUtility.isNull(account)
				|| StringUtility.isNull(password)) {
			return new ResultBean("501", ResultBgMsg.C_501);
		}

		password = MD5.string2MD5(password);
		result = companyService.addCompanyInfo(token.getUserid(), companyName, headerName, account, password);

		// 返回
		return result;
	}

	/**
	 * @Title: editCompanyInfo
	 * @Description: 修改公司信息账号密码
	 * @param id
	 *            公司id
	 * @param companyName
	 *            公司名字
	 * @param headerName
	 *            负责人名字
	 * @param password
	 *            密码
	 * @return
	 * @throws:
	 * @time: 2018年8月24日 下午4:16:04
	 */
	@SystemControllerLog(method = "editCompanyInfo", logtype = ConstantIF.LOG_TYPE_1, description = "修改公司信息账号密码")
	@RequestMapping(value = "/editCompanyInfo", method = RequestMethod.POST)
	public @ResponseBody ResultBean editCompanyInfo(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "公司id") @RequestParam(value = "id") String id,
			@ApiParam(value = "公司名字") @RequestParam(value = "companyName") String companyName,
			@ApiParam(value = "公司负责人名字") @RequestParam(value = "headerName") String headerName,
			@ApiParam(value = "账号") @RequestParam(value = "account") String account,
			@ApiParam(value = "密码") @RequestParam(value = "password", required = false) String password) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		// 入参验证
		if (StringUtility.isNull(id) || StringUtility.isNull(headerName)) {
			return new ResultBean("501", ResultBgMsg.C_501);
		}
		if (!StringUtility.isNull(password)) {
			password = MD5.string2MD5(password);
		}

		result = companyService.editCompanyInfo(token.getUserid(), id, headerName, password, account, companyName);

		// 返回
		return result;
	}

	/**
	 * @Title: delCompanyInfo
	 * @Description: 删除公司信息和公司账号
	 * @param accesstoken
	 *            accesstoken
	 * @param ids
	 *            公司IDs
	 * @return
	 * @throws:
	 * @time: 2018年8月25日 上午11:42:30
	 */
	@SystemControllerLog(method = "delCompanyInfo", logtype = ConstantIF.LOG_TYPE_1, description = "删除公司信息和公司账号")
	@RequestMapping(value = "/delCompanyInfo", method = RequestMethod.POST)
	public @ResponseBody ResultBean delCompanyInfo(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "公司ids") @RequestParam(value = "ids") String ids) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		if (StringUtility.isNull(ids)) {
			return new ResultBean("501", ResultBgMsg.C_501);
		}
		result = companyService.delCompanyInfo(token.getUserid(), ids);
		// 返回
		return result;
	}

	/**
	 * @Title: getCompanyInfo
	 * @Description: 获取公司信息和公司账号
	 * @param accesstoken
	 *            accesstoken
	 * @param ids
	 *            公司ID
	 * @return
	 * @throws:
	 * @time: 2018年8月25日 上午11:42:30
	 */
	@SystemControllerLog(method = "getCompanyInfo", logtype = ConstantIF.LOG_TYPE_1, description = "获取公司信息和公司账号")
	@RequestMapping(value = "/getCompanyInfo", method = RequestMethod.POST)
	public @ResponseBody ResultBean getCompanyInfo(
			@ApiParam(value = "accesstoken") @RequestParam(value = ConstantIF.ACCESSTOKEN) String accesstoken,
			@ApiParam(value = "公司id") @RequestParam(value = "id") String id) {
		ResultBean result = new ResultBean();
		// token验证
		TokenModel token = redisTokenService.getToken(accesstoken);
		if (StringUtility.isNull(id)) {
			return new ResultBean("501", ResultBgMsg.C_501);
		}
		result = companyService.getCompanyInfo(token.getUserid(), id);
		// 返回
		return result;
	}

}
