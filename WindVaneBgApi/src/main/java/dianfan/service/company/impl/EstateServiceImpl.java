package dianfan.service.company.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PrimitiveIterator.OfDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dianfan.annotations.SystemServiceLog;
import dianfan.constant.ResultBgMsg;
import dianfan.dao.mapper.company.EstateMapper;
import dianfan.entities.other.EstateInfo;
import dianfan.models.ResultBean;
import dianfan.service.company.EstateService;
import dianfan.util.PropertyUtil;
import dianfan.util.UUIDUtil;

/**
 * @ClassName EstateServiceImpl
 * @Description 楼盘相关
 * @author sz
 * @date 2018年8月27日 上午11:52:50
 */
@Service
public class EstateServiceImpl implements EstateService {
	
	/**
	 * 注入: #EstateMapper
	 */
	@Autowired
 	private EstateMapper estateMapper;

	/*
	 * (non-Javadoc)
	 * <p>Title: findEstateList</p>
	 * <p>Description: 获取楼盘的列表</p>
	 * @param userid userid
	 * @param page 请求页
	 * @param pageSize 每页条数
	 * @param estateName 按楼盘名字搜索
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 * link: @see dianfan.service.company.EstateService#findEstateList(java.lang.String, int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@SystemServiceLog(method = "findEstateList", description = "获取楼盘列表")
	public ResultBean findEstateList(String userid, int page, int pageSize, String estateName, String starttime,
			String endtime) {
		// 构建返回数据模型
		Map<String, Object> data = new HashMap<>();
		
		// 创建参数容器
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("estateName", estateName);
		param.put("starttime", starttime);
		param.put("endtime", endtime);
		param.put("start", (page - 1) * pageSize);
		param.put("pageSize", pageSize);
		// 获取列表数量
		int count = estateMapper.findEstateCount(param);
		// 添加总数量
		data.put("count", count);
		if (count == 0) {
			data.put("estateList", new ArrayList<>());
			return new ResultBean(data);
		}
		// 添加列表
		List<EstateInfo> estateList = estateMapper.findEstateList(param);
		for (EstateInfo info : estateList) {
			String property = PropertyUtil.getProperty("domain");
			info.setEstateIcon(PropertyUtil.getProperty("domain") + info.getEstateIcon());
			info.setEstateBanner(PropertyUtil.getProperty("domain") + info.getEstateBanner());
		}
		
		data.put("estateList", estateList);
		return new ResultBean(data);
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: addEstate</p>
	 * <p>Description: 添加楼盘</p>
	 * @param userid userid
	 * @param estateIcon 楼盘icon
	 * @param estateBanner 楼盘banner
	 * @param estateName 楼盘名称
	 * @param des 描述
	 * @param commissionRate 金额
	 * @param address 楼盘地址
	 * @return
	 * link: @see dianfan.service.company.EstateService#addEstate(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	@SystemServiceLog(method = "addEstate", description = "添加楼盘")
	public ResultBean addEstate(String userid, String estateIcon, String estateBanner, String estateName, String des,
			String commissionRate, String address,String money) {
		// 判断添加的楼盘是否已经存在
		int count = estateMapper.getCheckEstate(estateName);
		if (count != 0) {
			// 添加的楼盘已经存在
			return new ResultBean("1005",ResultBgMsg.C_1005);
		}
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		param.put("id", UUIDUtil.getUUID());
		param.put("userid", userid);
		param.put("estateIcon", estateIcon);
		param.put("estateBanner", estateBanner);
		param.put("estateName", estateName);
		param.put("des", des);
		param.put("commissionRate", commissionRate);
		param.put("address", address);
		param.put("money", money);
		
		// 添加操作
		estateMapper.addEstate(param);
		
		return new ResultBean();
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: editEstate</p>
	 * <p>Description: 修改楼盘</p>
	 * @param userid userid
	 * @param estateId 楼盘id
	 * @param estateIcon 楼盘icon
	 * @param estateBanner 楼盘banner
	 * @param des 描述
	 * @param commissionRate 佣金
	 * @param money 金额
	 * @param address 楼盘地址
	 * @return
	 * link: @see dianfan.service.company.EstateService#editEstate(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	@SystemServiceLog(method = "editEstate", description = "修改楼盘")
	public ResultBean editEstate(String userid, String estateIcon, String estateBanner, String estateId, String des,
			String commissionRate, String address, String money,String estateName) {
		// 修改图片的路径
		int indexOf = estateIcon.indexOf("upload");
		String estateIcons = estateIcon.substring(indexOf);
		int indexOfs = estateBanner.indexOf("upload");
		String estateBanners = estateBanner.substring(indexOfs);
		
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("estateIcon", estateIcons);
		param.put("estateBanner", estateBanners);
		param.put("estateId", estateId);
		param.put("des", des);
		param.put("commissionRate", commissionRate);
		param.put("address", address);
		param.put("money", money);
		param.put("estateName", estateName);
		
		// 判断需要修改的楼盘的名字是否存在
		int estateCount = estateMapper.getCheckEstateCount(param);
		if (estateCount != 0) {
			// 修改的楼盘已经存在
			return new ResultBean("1011",ResultBgMsg.C_1011);
		}
		// 更新操作
		estateMapper.editEstate(param);
		return new ResultBean();
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: delEstate</p>
	 * <p>Description: 删除楼盘</p>
	 * @param userid
	 * @param ids
	 * @return
	 * link: @see dianfan.service.company.EstateService#delEstate(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	@SystemServiceLog(method = "delEstate", description = "删除楼盘")
	public ResultBean delEstate(String userid, String ids) {
		// 整理ids
		List<String> list = Arrays.asList(ids.split(","));
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("list", list);

		estateMapper.delEstate(param);
		return new ResultBean();
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: getEstateInfo</p>
	 * <p>Description: 获取楼盘信息</p>
	 * @param userid userid
	 * @param id 楼盘ID
	 * @return
	 * link: @see dianfan.service.company.EstateService#getEstateInfo(java.lang.String, java.lang.String)
	 */
	@Override
	@SystemServiceLog(method = "getEstateInfo", description = "获取楼盘信息")
	public ResultBean getEstateInfo(String userid, String id) {
		// 获取楼盘详情
		EstateInfo info = estateMapper.getEstateInfo(id);
		// 添加图片的系统域名
		info.setEstateIcon(PropertyUtil.getProperty("domain") + info.getEstateIcon());
		// 添加图片的系统域名
		info.setEstateBanner(PropertyUtil.getProperty("domain") + info.getEstateBanner());
		
		return new ResultBean(info);
	}

}
