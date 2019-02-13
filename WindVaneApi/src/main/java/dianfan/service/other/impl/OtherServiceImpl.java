package dianfan.service.other.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dianfan.dao.mapper.other.OtherMapper;
import dianfan.entities.other.AppRole;
import dianfan.entities.other.Company;
import dianfan.entities.other.EstateInfo;
import dianfan.models.ResultBean;
import dianfan.service.other.OtherService;

/**
 * @ClassName OtherServiceImpl
 * @Description 辅助接口
 * @author sz
 * @date 2018年8月22日 下午5:02:42
 */
@Service
public class OtherServiceImpl implements OtherService {

	/**
	 * 注入： #OtherMapper
	 */
	@Autowired
	private OtherMapper otherMapper;

	/*
	 * (non-Javadoc)
	 * <p>Title: findRoleList</p>
	 * <p>Description: 获取可注册角色列表</p>
	 * @return
	 * link: @see dianfan.service.other.OtherService#findRoleList()
	 */
	@Override
	public ResultBean findRoleList() {
		// 获取角色列表
		List<AppRole> roleList = otherMapper.findRoleList();
		
		return new ResultBean(roleList);
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: findEstateList</p>
	 * <p>Description: 获取楼盘列表</p>
	 * @return
	 * link: @see dianfan.service.other.OtherService#findEstateList()
	 */
	@Override
	public ResultBean findEstateList() {
		// 获取楼盘列表
		List<EstateInfo> estateList = otherMapper.findEstateList();
		
	/*	String list = "";
		
		for (EstateInfo o : estateList) {
			String estateName = o.getEstateName();
			list += estateName + ",";
		}
		list = list.substring(0,list.length() - 1);*/
		Map<String, Object> data = new HashMap<>();
		
		for (EstateInfo info : estateList) {
			data.put(info.getId(), info.getEstateName());
		}
		
		return new ResultBean(data);
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: findCompany</p>
	 * <p>Description: 获取公司列表</p>
	 * @return
	 * link: @see dianfan.service.other.OtherService#findCompany()
	 */
	@Override
	public ResultBean findCompany() {
		// 获取公司列表
		List<Company> companyList = otherMapper.findCompanyList();
		
		return new ResultBean(companyList);
	}
	
	
	
}
