package dianfan.service.records.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dianfan.dao.mapper.records.EstateShowMapper;
import dianfan.entities.other.EstateInfo;
import dianfan.models.ResultBean;
import dianfan.service.records.EstateShowService;
import dianfan.util.PropertyUtil;

/**
 * @ClassName EstateShowServiceImpl
 * @Description 楼盘相关
 * @author sz
 * @date 2018年8月31日 上午10:55:21
 */
@Service
public class EstateShowServiceImpl implements EstateShowService {
	
	/**
	 * 注入: #EstateShowMapper
	 */
	@Autowired
	private EstateShowMapper estateShowMapper;

	/*
	 * (non-Javadoc)
	 * <p>Title: findEstateShowList</p>
	 * <p>Description: </p>
	 * @param userid userid
	 * @param page 起始页
	 * @param pageSize 每页条数
	 * @return
	 * link: @see dianfan.service.records.EstateShowService#findEstateShowList(java.lang.String, int, int)
	 */
	@Override
	public ResultBean findEstateShowList(int page, int pageSize) {
		// 返回参数容器
		Map<String, Object> data = new HashMap<>();
		
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		param.put("start", (page - 1) * pageSize);
		param.put("pageSize", pageSize);
		// 获取总条数
		int count = estateShowMapper.findEstateShowCount();
		data.put("count", count);
		if (count == 0) {
			data.put("estateShowList", new ArrayList<>());
			return new ResultBean(data);
		}
		List<EstateInfo> estateShowList = estateShowMapper.findEstateShowList(param);
		for (EstateInfo es :  estateShowList) {
			es.setEstateBanner(PropertyUtil.getProperty("domain") + es.getEstateBanner());
			es.setEstateIcon(PropertyUtil.getProperty("domain") + es.getEstateIcon());
		}
		
		data.put("estateShowList", estateShowList);
		return new ResultBean(data);
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: getEstateShowInfo</p>
	 * <p>Description: </p>
	 * @param userid userid
	 * @param id 楼盘 id
	 * @return
	 * link: @see dianfan.service.records.EstateShowService#getEstateShowInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public ResultBean getEstateShowInfo(String id) {
		// 获取楼盘详情
		EstateInfo data = estateShowMapper.getEstateShowInfo(id);
		
		//  PropertyUtil.getProperty("domain")
		data.setEstateIcon(PropertyUtil.getProperty("domain")+data.getEstateIcon());
		data.setEstateBanner(PropertyUtil.getProperty("domain")+data.getEstateBanner());
		// 成功返回
		return new ResultBean(data);
	}
	
	

}
