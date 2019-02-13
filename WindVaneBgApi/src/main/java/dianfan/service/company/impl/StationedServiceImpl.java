package dianfan.service.company.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dianfan.annotations.SystemServiceLog;
import dianfan.constant.ResultBgMsg;
import dianfan.dao.mapper.company.StationedMapper;
import dianfan.entities.other.EstateInfoModel;
import dianfan.entities.user.UserInfos;
import dianfan.models.ResultBean;
import dianfan.service.company.StationedService;
import dianfan.util.StringUtility;

/**
 * @ClassName StationedController
 * @Description 驻场相关Service
 * @author sz
 * @date 2018年8月27日 上午11:30:43
 */
@Service
public class StationedServiceImpl implements StationedService {

	/**
	 * 注入：StationedMapper 
	 */
	@Autowired
	private StationedMapper stationedMapper;

	/*
	 * (non-Javadoc)
	 * <p>Title: findStationedList</p>
	 * <p>Description: 获取驻场人员的列表</p>
	 * @param userid userid
	 * @param page 请求页
	 * @param pageSize 每页条数
	 * @param telno 电话搜索
	 * @param company 公司搜索
	 * @param starttime 按开始时间
	 * @param endtime 按结束时间
	 * @return
	 * link: @see dianfan.service.company.StationedService#findStationedList(java.lang.String, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@SystemServiceLog(method = "findStationedList", description = "获取驻场人员列表")
	public ResultBean findStationedList(String userid, int page, int pageSize, String telno, String company,
			String starttime, String endtime) {
		// 创建返回容器
		Map<String, Object> data = new HashMap<>();
		
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("start", (page - 1) * pageSize);
		param.put("pageSize", pageSize);
		param.put("telno", telno);
		param.put("company", company);
		param.put("starttime", starttime);
		param.put("endtime", endtime);
		// 查询总条数
		int count = stationedMapper.findStationedCount(param);
		data.put("count", count);
		if (count == 0) {
			data.put("stationedList", new ArrayList<>());
			return new ResultBean(data);
		}
		// 获取驻场人员列表
		List<UserInfos> stationedList = stationedMapper.findStationedList(param);
		data.put("stationedList", stationedList);
		return new ResultBean(data);
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: addStationed</p>
	 * <p>Description: 添加驻场人员</p>
	 * @param userid userid
	 * @param telno 手机号
	 * @param pwd 密码
	 * @param roleId 角色
	 * @return
	 * link: @see dianfan.service.company.StationedService#addStationed(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	@SystemServiceLog(method = "addStationed", description = "添加驻场人员")
	public ResultBean addStationed(String userid, String telno, String pwd, String roleId,String name) {
		// 验证手机号是否存在
		int count = stationedMapper.getCheckStationedTelno(telno);
		if (count != 0) {
			// 当前账号已存在
			return new ResultBean("1008",ResultBgMsg.C_1008);
		}
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("telno", telno);
		param.put("pwd", pwd);
		param.put("roleId", roleId);
		param.put("name", name);
		// 添加操作
		stationedMapper.addStationed(param);
		
		return new ResultBean();
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: editStationed</p>
	 * <p>Description: 修改驻场人员的信息</p>
	 * @param userid userid
	 * @param id id
	 * @param pwd 密码
	 * @param roleId 角色
	 * @return
	 * link: @see dianfan.service.company.StationedService#editStationed(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	@SystemServiceLog(method = "editStationed", description = "修改驻场人员的信息")
	public ResultBean editStationed(String userid, String id, String pwd, String roleId,String name) {
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("id", id);
		param.put("pwd", pwd);
		param.put("roleId", roleId);
		param.put("name", name);
		stationedMapper.editStationed(param);
		return new ResultBean();
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: bindingStationed</p>
	 * <p>Description: 绑定驻场到楼盘</p>
	 * @param userid userid
	 * @param id 人员ID
	 * @param estateId 楼盘ID
	 * @return
	 * link: @see dianfan.service.company.StationedService#bindingStationed(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	@SystemServiceLog(method = "bindingStationed", description = "绑定驻场到楼盘")
	public ResultBean bindingStationed(String userid, String id, String estateId) {
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("id", id);
		param.put("estateId", estateId);
		// 判断需要绑定的楼盘是否已经有了驻场
		String count = stationedMapper.getCheckEstate(param);
		if (!StringUtility.isNull(count)) {
			// 该楼盘已经存在过驻场了
			return new ResultBean("1006",ResultBgMsg.C_1006);
		}
		
		// 判断该驻场是否已经绑定过其他楼盘
		String counts = stationedMapper.getCheckStationed(param);
		if (!StringUtility.isNull(counts)) {
			// 该楼盘已经存在过驻场了
			return new ResultBean("1007",ResultBgMsg.C_1007);
		}
		
		stationedMapper.bindingStationed(param);
		
		return new ResultBean();
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: relieveBinding</p>
	 * <p>Description: 解除楼盘绑定的驻场</p>
	 * @param userid
	 * @param estateId
	 * @return
	 * link: @see dianfan.service.company.StationedService#relieveBinding(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	@SystemServiceLog(method = "relieveBinding", description = "解除楼盘绑定的驻场")
	public ResultBean relieveBinding(String userid, String estateId) {
		// 创建入参容器 
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("estateId", estateId);
		
		stationedMapper.relieveBinding(param);
		
		return new ResultBean();
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: updataLockedAdmin</p>
	 * <p>Description: 冻结或者解冻驻场</p>
	 * @param userid userid
	 * @param id 驻场ID
	 * @param flag 状态
	 * @return
	 * link: @see dianfan.service.company.StationedService#updataLockedAdmin(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	@SystemServiceLog(method = "updataLockedAdmin", description = "冻结或解冻驻场")
	public ResultBean updataLockedAdmin(String userid, String id, String flag) {
		// 创建入参模型
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("id", id);
		param.put("flag", flag);
		
		stationedMapper.updataLockedAdmin(param);
		
		// 如果是冻结 1 判断当前驻场是否存在 对应的楼盘 如果有就置空
		if ("1".equals(flag)) {
			stationedMapper.updataEstateUser(id);
		}
		
		return new ResultBean();
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: findEstateListInStationed</p>
	 * <p>Description: 添加驻场的页面 所使用到的列表</p>
	 * @return
	 * link: @see dianfan.service.company.StationedService#findEstateListInStationed()
	 */
	@Override
	@SystemServiceLog(method = "findEstateListInStationed", description = "添加驻场的页面 所使用到的列表")
	public ResultBean findEstateListInStationed() {
		//  添加驻场的页面 所使用到的列表
		List<EstateInfoModel> list = stationedMapper.findEstateListInStationed();
		return new ResultBean(list);
	}
	
	
	
}
