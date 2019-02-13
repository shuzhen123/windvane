package dianfan.controller.records;

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
import dianfan.models.ResultBean;
import dianfan.service.common.impl.RedisTokenService;
import dianfan.service.records.EstateShowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description = "楼盘相关")
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/estateShow")
public class EstateShowController {

	/**
	 * 注入: #RedisTokenService
	 */
	@Autowired
	private RedisTokenService redisTokenService;
	/**
	 * 注入: #EstateShowService
	 */
	@Autowired
	private EstateShowService estateShowService;

	/**
	 * @Title: estateShowList
	 * @Description: 楼盘列表
	 * @param accesstoken
	 *            accesstoken
	 * @return ResultBean
	 * @throws:
	 * @time: 2018年8月21日 下午2:17:11
	 */
	@UnCheckedFilter
	@SystemControllerLog(method = "estateShowList", logtype = ConstantIF.LOG_TYPE_1, description = "楼盘列表")
	@ApiOperation(value = "estateShowList", httpMethod = "POST", notes = "楼盘列表", response = ResultBean.class)
	@RequestMapping(value = "estateShowList", method = RequestMethod.POST)
	public @ResponseBody ResultBean estateShowList(
			@ApiParam(value = "请求页") @RequestParam(value = ConstantIF.PAGE, defaultValue = ConstantIF.PAGE_START_STR) int page,
			@ApiParam(value = "每页条数") @RequestParam(value = ConstantIF.PAGE_SIZE, defaultValue = ConstantIF.PAGE_OFFSET_STR) int pageSize) {
		ResultBean result = new ResultBean();

		result = estateShowService.findEstateShowList(page, pageSize);

		return result;
	}

	/**
	 * @Title: estateShowInfo
	 * @Description: 楼盘详情
	 * @param accesstoken
	 *            accesstoken
	 * @param ID
	 *            楼盘ID
	 * @return
	 * @throws:
	 * @time: 2018年8月31日 上午11:29:29
	 */
	@UnCheckedFilter
	@SystemControllerLog(method = "estateShowInfo", logtype = ConstantIF.LOG_TYPE_1, description = "楼盘详情")
	@ApiOperation(value = "estateShowInfo", httpMethod = "POST", notes = "楼盘详情", response = ResultBean.class)
	@RequestMapping(value = "estateShowInfo", method = RequestMethod.POST)
	public @ResponseBody ResultBean estateShowInfo(
			@ApiParam(value = "楼盘ID") @RequestParam(value = "id") String id) {
		ResultBean result = new ResultBean();

		result = estateShowService.getEstateShowInfo(id);

		return result;
	}

}
