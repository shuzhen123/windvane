package dianfan.controller.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import dianfan.annotations.SystemControllerLog;
import dianfan.annotations.UnCheckedFilter;
import dianfan.constant.ConstantIF;
import dianfan.models.ResultBean;
import dianfan.service.other.OtherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName OtherController
 * @Description app端 辅助接口
 * @author sz
 * @date 2018年8月22日 下午5:00:09
 */
@Api(description = "辅助接口相关")
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/other")
public class OtherController {

	/**
	 * 注入： #OtherService
	 */
	@Autowired
	private OtherService otherService;

	/**
	 * @Title: findRoleList
	 * @Description: 获取可注册的角色列表
	 * @return ResultBean
	 * @throws:
	 * @time: 2018年8月22日 下午5:06:13
	 */
	@UnCheckedFilter
	@SystemControllerLog(method = "findRoleList", logtype = ConstantIF.LOG_TYPE_1, description = "获取可注册的角色列表")
	@ApiOperation(value = "findRoleList", httpMethod = "POST", notes = "获取可注册的角色列表", response = ResultBean.class)
	@RequestMapping(value = "/findRoleList", method = RequestMethod.GET)
	public @ResponseBody ResultBean findRoleList() {
		ResultBean result = new ResultBean();

		result = otherService.findRoleList();

		return result;
	}

	/**
	 * @Title: findEstateList
	 * @Description: 获取楼盘列表
	 * @return ResultBean
	 * @throws:
	 * @time: 2018年8月22日 下午5:06:13
	 */
	@UnCheckedFilter
	@SystemControllerLog(method = "findEstateList", logtype = ConstantIF.LOG_TYPE_1, description = "获取楼盘列表")
	@ApiOperation(value = "findEstateList", httpMethod = "POST", notes = "获取楼盘列表", response = ResultBean.class)
	@RequestMapping(value = "/findEstateList", method = RequestMethod.GET)
	public @ResponseBody ResultBean findEstateList() {
		ResultBean result = new ResultBean();

		result = otherService.findEstateList();

		return result;
	}

	/**
	 * @Title: findCompany
	 * @Description: 获取公司列表
	 * @return ResultBean
	 * @throws:
	 * @time: 2018年8月22日 下午5:06:13
	 */
	@UnCheckedFilter
	@SystemControllerLog(method = "findCompany", logtype = ConstantIF.LOG_TYPE_1, description = "获取公司列表")
	@ApiOperation(value = "findCompany", httpMethod = "POST", notes = "获取公司列表", response = ResultBean.class)
	@RequestMapping(value = "/findCompany", method = RequestMethod.GET)
	public @ResponseBody ResultBean findCompany() {
		ResultBean result = new ResultBean();

		result = otherService.findCompany();

		return result;
	}

}
