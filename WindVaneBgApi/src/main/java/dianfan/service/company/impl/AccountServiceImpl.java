package dianfan.service.company.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dianfan.dao.mapper.company.AccountMapper;
import dianfan.entities.user.UserInfos;
import dianfan.models.ResultBean;
import dianfan.service.company.AccountService;

/**
 * @ClassName AccountServiceImpl
 * @Description 账号相关管理
 * @author sz
 * @date 2018年8月28日 上午10:06:16
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountMapper accountMapper;
	
	/*
	 * (non-Javadoc)
	 * <p>Title: findAccountList</p>
	 * <p>Description: </p>
	 * @param userid
	 * @param page
	 * @param pageSize
	 * @param telno
	 * @param flag
	 * @param starttime
	 * @param endtime
	 * @return
	 * link: @see dianfan.service.company.AccountService#findAccountList(java.lang.String, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultBean findAccountList(String userid, int page, int pageSize, String telno, String flag,
			String starttime, String endtime,String companyName,String applyName) {
		// 创建返回容器
		Map<String, Object> data = new HashMap<>();
		
		// 创建入参模型 
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("start", (page - 1 ) * pageSize);
		param.put("pageSize", pageSize);
		param.put("telno", telno);
		param.put("flag", flag);
		param.put("starttime", starttime);
		param.put("endtime", endtime);
		param.put("companyName", companyName);
		param.put("applyName", applyName);
		// 查找总数量
		int count = accountMapper.findAccountCount(param);
		data.put("count", count);
		if (count == 0) {
			data.put("accountList", new ArrayList<>());
			return new ResultBean(data);
		}
		List<UserInfos> accountList = accountMapper.findAccountList(param);
		data.put("accountList", accountList);
		
		return new ResultBean(data);
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: findAccountList</p>
	 * <p>Description: </p>
	 * @param userid
	 * @param flag
	 * @param ids
	 * @return
	 * link: @see dianfan.service.company.AccountService#findAccountList(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultBean editAccountFlag(String userid, String flag, String ids) {
		// 整理ids
		List<String> list = Arrays.asList(ids.split(","));
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("flag", flag);
		param.put("list", list);
		// 修改操作
		accountMapper.editAccountFlag(param);
		
		return new ResultBean();
	}

}
