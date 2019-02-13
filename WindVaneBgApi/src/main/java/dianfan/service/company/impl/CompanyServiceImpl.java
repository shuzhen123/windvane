package dianfan.service.company.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.base.Param;

import dianfan.annotations.SystemServiceLog;
import dianfan.constant.ResultBgMsg;
import dianfan.dao.mapper.company.CompanyMapper;
import dianfan.entities.other.Company;
import dianfan.models.ResultBean;
import dianfan.service.company.CompanyService;
import dianfan.util.UUIDUtil;
/**
 * @ClassName CompanyServiceImpl
 * @Description 
 * @author sz
 * @date 2018年8月23日 下午6:55:20
 */
@Service
public class CompanyServiceImpl implements CompanyService {

	/**
	 * 注入：#CompanyMapper
	 */
	@Autowired
	private CompanyMapper companyMapper;

	
	/*
	 * (non-Javadoc)
	 * <p>Title: findCompanyList</p>
	 * <p>Description: 获取公司列表</p>
	 * @param userid   userid
	 * @param page 请求页面
	 * @param pageSize 每条数量
	 * @param companyName 公司名字
	 * @param headerName 负责人名字
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 * link: @see dianfan.service.company.CompanyService#findCompanyList(java.lang.String, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@SystemServiceLog(method = "findCompanyList", description = "获取公司列表")
	public ResultBean findCompanyList(String userid, int page, int pageSize, String companyName, String headerName,
			String starttime, String endtime) {
		// 创建返回参数模型
		Map<String, Object> data = new HashMap<>();
		
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("companyName", companyName);
		param.put("headerName", headerName);
		param.put("pageSize", pageSize);
		param.put("start", (page - 1) * pageSize);
		param.put("starttime", starttime);
		param.put("endtime", endtime);
		// 获取列表数量
		int count = companyMapper.findCompanyCount(param);

		// 添加到返回参数
		data.put("count", count);
		
		if (count == 0 ) {
			data.put("companyList", new ArrayList<>());
			return new ResultBean(data);
		}
		// 获取公司的列表
		List<Company> companyList = companyMapper.findCompanyList(param);
		// 添加到返回参数
		data.put("companyList", companyList);
		
		return new ResultBean(data);
	}


	/*
	 * (non-Javadoc)
	 * <p>Title: addCompanyInfo</p>
	 * <p>Description: 添加公司信息和公司账号 </p>
	 * @param userid userid
	 * @param companyName 公司名字
	 * @param headerName 负责人名字
	 * @param account 账号
	 * @param password 密码
	 * @return
	 * link: @see dianfan.service.company.CompanyService#addCompanyInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	@Override
	@SystemServiceLog(method = "addCompanyInfo", description = "添加公司信息和公司账号")
	public ResultBean addCompanyInfo(String userid, String companyName, String headerName, String account,
			String password) {
		// 验证需要添加的公司 是否存在
		int count = companyMapper.getCheckCompanyName(companyName);
		if (count != 0) {
			// 该公司已经注册过
			return new ResultBean("1002",ResultBgMsg.C_1002);
		}
		// 检测添加的账号是否存在
		int counts = companyMapper.getCheckAccount(account);
		if (counts != 0) {
			// 该账号已经注册过
			return new ResultBean("1003",ResultBgMsg.C_1003);
		}
		// 创建入参模型
		Map<String,Object> param = new HashMap<>();
		// 创建一个新的uuid
		param.put("id", UUIDUtil.getUUID());
		param.put("userid", userid);
		param.put("companyName", companyName);
		param.put("headerName", headerName);
		// 创建公司信息
		companyMapper.addCompanyInfo(param);
		
		param.put("account", account);
		param.put("password", password);
		// 添加账号
		companyMapper.addAdminInfo(param);
		
		return new ResultBean();
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: editCompanyInfo</p>
	 * <p>Description: 修改公司信息</p>
	 * @param userid
	 * @param id
	 * @param headerName
	 * @param password
	 * @return
	 * link: @see dianfan.service.company.CompanyService#editCompanyInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	@SystemServiceLog(method = "editCompanyInfo", description = "修改公司信息")
	public ResultBean editCompanyInfo(String userid, String id, String headerName, String password, String account , String companyName) {
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("id", id);
		param.put("headerName", headerName);
		param.put("password", password);
		param.put("companyName", companyName);
		param.put("account", account);
		
		// 判断要修改的公司名字是否存在
		int nameCount = companyMapper.getCheckCompanyname(param);
		if (nameCount != 0 ) {
			// 该公司已经存在
			return new ResultBean("1009",ResultBgMsg.C_1009);
		}
		// 判断一下 要修改的账号名 是否存在
		int accountCount = companyMapper.getCheckAccountCount(param);
		if (accountCount != 0 ) {
			// 当前账号已存在
			return new ResultBean("1009",ResultBgMsg.C_1008);
		}
		
		// 更新公司信息
		companyMapper.editCompanyInfo(param);
		// 更新公司账号的密码
		companyMapper.editCompanyAccountInfo(param);
		
		return new ResultBean();
	}


	/*
	 * (non-Javadoc)
	 * <p>Title: delCompanyInfo</p>
	 * <p>Description: 删除公司</p>
	 * @param userid
	 * @param ids
	 * @return
	 * link: @see dianfan.service.company.CompanyService#delCompanyInfo(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	@SystemServiceLog(method = "delCompanyInfo", description = "删除公司信息")
	public ResultBean delCompanyInfo(String userid, String ids) {
		// 将ids整理
		List<String> list = Arrays.asList(ids.split(","));
		
		// 创建入参模型
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("list", list);
		// 删除公司信息操作
		companyMapper.delCompanyInfo(param);
		
		// 删除公司账号信息操作
		companyMapper.delCompanyAccountInfo(param);
		
		return new ResultBean();
	}


	/*
	 * (non-Javadoc)
	 * <p>Title: getCompanyInfo</p>
	 * <p>Description: 获取公司信息</p>
	 * @param userid userid
	 * @param id 公司的ID
	 * @return
	 * link: @see dianfan.service.company.CompanyService#getCompanyInfo(java.lang.String, java.lang.String)
	 */
	@Override
	@SystemServiceLog(method = "getCompanyInfo", description = "获取公司信息")
	public ResultBean getCompanyInfo(String userid, String id) {
		// 创建入参容器
		Map<String, Object> parma = new HashMap<>();
		parma.put("userid", userid);
		parma.put("id", id);
		// 获取信息
		Company info = companyMapper.getCompanyInfo(parma);
		
		return new ResultBean(info);
	}
	
}
