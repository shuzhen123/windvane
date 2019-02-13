package dianfan.service.login.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.parser.MacOsPeterFTPEntryParser;
import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mysql.fabric.xmlrpc.base.Array;

import dianfan.annotations.SystemServiceLog;
import dianfan.constant.ResultBgMsg;
import dianfan.dao.mapper.login.LoginMapper;
import dianfan.entities.admin.Admin;
import dianfan.entities.user.Role;
import dianfan.models.ResultBean;
import dianfan.service.common.impl.RedisService;
import dianfan.service.common.impl.RedisTokenService;
import dianfan.service.login.LoginService;

/**
 * @ClassName LoginServiceImpl
 * @Description 后台登录相关
 * @author sz
 * @date 2018年8月23日 上午9:58:23
 */
@Service
public class LoginServiceImpl implements LoginService {

	/**
	 * 注入: #redisService
	 */
	@Autowired
	private RedisService<Object,Object> redisService;
	
	/**
	 * 注入: #redisService
	 */
	@Autowired
	private RedisTokenService redisTokenService;
	
	/**
	 * 注入: #LoginMapper
	 */
	@Autowired
	private LoginMapper loginMapper;
	
	
	/*
	 * (non-Javadoc)
	 * <p>Title: getLoginAction</p>
	 * <p>Description: 后台登录验证</p>
	 * @param request request
	 * @param account account
	 * @param password password
	 * @return
	 * link: @see dianfan.service.login.LoginService#getLoginAction(javax.servlet.http.HttpServletRequest, java.lang.String, java.lang.String)
	 */
	@Override
	@SystemServiceLog(method = "getLoginAction", description = "后台登录验证 ")
	public ResultBean getLoginAction( String account, String password) {
		// 创建返回容器
		Map<String, Object> data = new HashMap<>();
		
		// 创建入参容器
		Map<String, Object> parma = new HashMap<>();
		parma.put("account", account);
		parma.put("password", password);
		// 验证账号是否正确
		Admin infos = loginMapper.getCheckLogin(parma);
		if (StringUtils.isEmpty(infos)) {
			// 账号或密码不正确
			return new ResultBean("4002",ResultBgMsg.C_4002);
		}
		// 添加到返回容器中
		data.put("infos", infos);
		
		// 角色判断
		// ......
		
		// 创建一个token，并添加到返回容器中
		String accesstoken = redisTokenService.createToken(infos.getId());
		data.put("accesstoken", accesstoken);
		
		// 返回
		return new ResultBean(data);
	}


	/*
	 * (non-Javadoc)
	 * <p>Title: findAdminList</p>
	 * <p>Description: 获取管理员列表</p>
	 * @param userid userid
	 * @param page 请求页
	 * @param pageSize 每页条数
	 * @param roleId 角色搜索
	 * @param account 账号搜索
	 * @param companyId 公司搜索
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 * link: @see dianfan.service.login.LoginService#findAdminList(java.lang.String, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@SystemServiceLog(method = "findAdminList", description = "获取管理员列表")
	public ResultBean findAdminList(String userid, int page, int pageSize,String account) {
		// 创建返回数据实体
		Map<String, Object> data = new HashMap<>();
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		// 添加入参
		param.put("userid", userid);
		param.put("account", account);
		// 查询总条数
		int count = loginMapper.findAdminListCount(param);
		data.put("count", count);
		if (count == 0 || count < (page - 1) * pageSize) {
			// 如果没有数据，则返回前端一个空的数组
			data.put("adminList", new ArrayList<>());
			return new ResultBean(data);
		}
		/* 添加分页条件 */
		// 开始条数
		param.put("start", (page - 1) * pageSize);
		// 每页几条
		param.put("pageSize", pageSize);
		// 获取管理员数据列表
		List<Admin> adminList = loginMapper.findAdminList(param);
		data.put("adminList", adminList);
		// 返回
		return new ResultBean(data);
	}


	/*
	 * (non-Javadoc)
	 * <p>Title: addAdminPerson</p>
	 * <p>Description: 添加管理员</p>
	 * @param userid  userid
	 * @param account 账号
	 * @param password 密码
	 * @param roleid 角色ID
	 * @param companyid 公司ID
	 * @return
	 * link: @see dianfan.service.login.LoginService#addAdminPerson(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	@SystemServiceLog(method = "addAdminPerson", description = "添加管理员")
	public ResultBean addAdminPerson(String userid, String account, String password) {
		// 验证当前账号是否被注册过
		int count = loginMapper.getCheckAccount(account);
		if (count != 0) {
			// 添加的账号已经存在
			return new ResultBean("4008",ResultBgMsg.C_4008);
		}
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("account", account);
		param.put("password", password);
		
		// 添加管理员
		loginMapper.addAdminPerson(param);
		
		// 返回
		return new ResultBean();
	}


	/*
	 * (non-Javadoc)
	 * <p>Title: editAdminPerson</p>
	 * <p>Description:  修改管理员信息</p>
	 * @param userid userid
	 * @param id 账号ID
	 * @param password 密码
	 * @param roleid 角色ID
	 * @param companyid 公司ID
	 * @return
	 * link: @see dianfan.service.login.LoginService#editAdminPerson(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@SystemServiceLog(method = "editAdminPerson", description = "修改管理员信息")
	public ResultBean editAdminPerson(String userid, String id, String password) {
		// 检测修改的账号的名字数据库中是否存在
		int count = loginMapper.getExcludeAccount(id);
		if (count == 0 ) {
			// 当前账号不存在
			return new ResultBean("1001",ResultBgMsg.C_1001);
		}
		// 创建入参容器
		Map<String, Object> parma = new HashMap<>();
		parma.put("userid", userid);
		parma.put("id", id);
		parma.put("password", password);
		
		// 更新操作
		loginMapper.editAdminPerson(parma);
		
		// 返回
		return new ResultBean();
	}


	/*
	 * (non-Javadoc)
	 * <p>Title: delAdminPerson</p>
	 * <p>Description: 删除管理员</p>
	 * @param userid userid
	 * @param ids 管理员ids
	 * @return
	 * link: @see dianfan.service.login.LoginService#delAdminPerson(java.lang.String, java.lang.String)
	 */
	@Override
	@SystemServiceLog(method = "delAdminPerson", description = "删除管理员")
	public ResultBean delAdminPerson(String userid, String ids) {
		// 处理ids
		List<String> list = Arrays.asList(ids.split(","));
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		param.put("list", list);
		param.put("userid", userid);
		
		// 删除操作
		loginMapper.delAdminPerson(param);
		
		// 返回
		return new ResultBean();
	}


	/*
	 * (non-Javadoc)
	 * <p>Title: findRoleList</p>
	 * <p>Description: 获取角色列表</p>
	 * @return
	 * link: @see dianfan.service.login.LoginService#findRoleList()
	 */
	@Override
	@SystemServiceLog(method = "findRoleList", description = "获取角色列表")
	public ResultBean findRoleList() {
		// 获取角色列表
		List<Role> lists = loginMapper.findRoleList();
		// 将角色转成字符串
		/*String role = "";
		for (Role ro : lists) {
			String ls = ro.getDescption();
			role += ls + ",";
		}
		role = role.substring(0, role.length()-1);*/
		
		/*Map<String, Object> role = new HashMap<>();
		for (Role ro : lists) {
			role.put(ro.getId(), ro.getDescption());
		}*/
		
		return new ResultBean(lists);
	}


	
	
	
}
