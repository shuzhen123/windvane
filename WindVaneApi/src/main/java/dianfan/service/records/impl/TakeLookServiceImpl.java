package dianfan.service.records.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dianfan.dao.mapper.records.RecordsMapper;
import dianfan.dao.mapper.records.TakeLookMapper;
import dianfan.entities.user.Records;
import dianfan.entities.user.TakeLook;
import dianfan.models.ResultBean;
import dianfan.service.records.TakeLookService;

/**
 * @ClassName TakeLookServiceImpl
 * @Description 带看相关
 * @author sz
 * @date 2018年8月22日 上午10:52:13
 */
@Service
public class TakeLookServiceImpl implements TakeLookService {

	/**
	 * 注入: #TakeLookMapper
	 */
	@Autowired
	private TakeLookMapper takeLookMapper;
	/**
	 * 注入： #RecordsMapper
	 */
	@Autowired
	private RecordsMapper recordsMapper;

	/*
	 * (non-Javadoc)
	 * <p>Title: findTakeLookList</p>
	 * <p>Description: 获取带看信息列表</p>
	 * @param userid
	 * @return
	 * link: @see dianfan.service.records.TakeLookService#findTakeLookList(java.lang.String)
	 */
	@Override
	public ResultBean findTakeLookList(String userid,int page,int pageSize) {
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("start", (page-1)*pageSize);
		param.put("pageSize", pageSize);
		
		// 创建返回参数模型
		Map<String, Object> data = new HashMap<>();
		// 查看登陆者的角色
		String role = recordsMapper.findLoginRole(userid);
		// 角色时04的话是案场
		if ("04".equals(role)) {
			// 获取总条数
			int count = takeLookMapper.findTakeLookCount(userid);
			// 添加总数
			data.put("count", count);
			if (count == 0 ) {
				// 如果没有数据，则返回一个空的数组
				data.put("takeLookList", new ArrayList<>());
				return new ResultBean(data);
			}
			List<TakeLook> takeLookList = takeLookMapper.findTakeLookList(param);
			data.put("takeLookList", takeLookList);
		}else {
			// 报备者登陆，可以看到自己的报备且已经带看的 带看信息列表
			int count = takeLookMapper.findTakeLookCountBb(userid); 
			// 添加总数
			data.put("count", count);
			if (count == 0 ) {
				// 如果没有数据，则返回一个空的数组
				data.put("takeLookList", new ArrayList<>());
				return new ResultBean(data);
			}
			List<TakeLook> takeLookList = takeLookMapper.findTakeLookListBb(param);
			data.put("takeLookList", takeLookList);
		}
		
		return new ResultBean(data);
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: upAffirmKnockdown</p>
	 * <p>Description: 确认成交</p>
	 * @param userid userid
	 * @param lookId 带看ID
	 * @param estateId 楼盘ID
	 * @param roomNum 成交房号
	 * @param area 面积
	 * @param totalMoney 成交总价
	 * @param commissionMoney 成交佣金
	 * @return
	 * link: @see dianfan.service.records.TakeLookService#upAffirmKnockdown(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public ResultBean upAffirmKnockdown(String userid, String lookId, String estateId, String roomNum, String area,
			String totalMoney, String commissionMoney,String intentionDes, String specificDes , String tsDes,String commissionStatus,String commissionDes) {
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		// 通过带看ID，获取报备的ID
		String preparationId = takeLookMapper.findPreparationId(lookId);
		// 添加报备的ID
		param.put("preparationId", preparationId);
		// 更新报备表
		param.put("estateId", estateId);
		param.put("userid", userid);
		param.put("intentionDes", intentionDes);
		param.put("commissionStatus", commissionStatus);
		param.put("commissionDes", commissionDes);
		// 更新客户意向描述，报备状态,楼盘id等
		takeLookMapper.upPreparation(param);
		
		// 更新带看表
		param.put("lookId", lookId);
		param.put("roomNum", roomNum);
		param.put("area", area);
		param.put("totalMoney", totalMoney);
		param.put("commissionMoney", commissionMoney);
		param.put("specificDes", specificDes);
		param.put("tsDes", tsDes);
		takeLookMapper.updateAffirmKnockdown(param);
		
		
		return new ResultBean();
	}
	/*
	 * (non-Javadoc)
	 * <p>Title: upAffirmKnockdownInfo</p>
	 * <p>Description: 确认成交后的详情</p>
	 * @param userid userid
	 * @param lookId 带看ID
	 * @return
	 * link: @see dianfan.service.records.TakeLookService#upAffirmKnockdown(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultBean upAffirmKnockdownInfo(String userid, String lookId) {
		
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("lookId", lookId);
		
		Records KnockdownInfo = takeLookMapper.getAffirmKnockdownInfo(param);
		
		return new ResultBean(KnockdownInfo);
	}

	
	/*
	 * (non-Javadoc)
	 * <p>Title: findknockdownList</p>
	 * <p>Description: 成交列表</p>
	 * @param userid userid
	 * @return
	 * link: @see dianfan.service.records.TakeLookService#findknockdownList(java.lang.String)
	 */
	@Override
	public ResultBean findknockdownList(String userid,int page,int pageSize,String type) {
		// 创建返回参数模型
		Map<String, Object> data = new HashMap<>();
		
		// 创建一个入参容器
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("start", (page - 1) * pageSize);
		param.put("pageSize", pageSize);
		// 佣金状态
		param.put("type", type);
		
		
		// 查看一下登陆者的角色
		String role = recordsMapper.findLoginRole(userid);
		if ("04".equals(role) || "01".equals(role)) {
			// 获取总数量
			int count = takeLookMapper.findknockdownCount(param);
			data.put("count", count);
			if (count == 0) {
				data.put("knockdownList", new ArrayList<>());
				return new ResultBean(data);
			}
			List<Records> knockdownList = takeLookMapper.findknockdownList(param);
			data.put("knockdownList",knockdownList);
		} else {
			// 报备者登陆可以看到自己报备，切已经成交的列表
			// 获取总数量
			int count = takeLookMapper.findknockdownCountBb(param);
			data.put("count", count);
			if (count == 0) {
				data.put("knockdownList", new ArrayList<>());
				return new ResultBean(data);
			}
			List<Records> knockdownList = takeLookMapper.findknockdownListBb(param);
			data.put("knockdownList",knockdownList);
		}
		
		return new ResultBean(data);
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: editKnockdown</p>
	 * <p>Description: 修改成交</p>
	 * @param userid userid
	 * @param lookId 带看ID
	 * @param estateId 楼盘ID
	 * @param roomNum 成交房号
	 * @param area 面积
	 * @param totalMoney 成交总价
	 * @param commissionMoney 成交佣金
	 * @param tsDes 成交描述
	 * @param specificDes 具体描述 
	 * @param intentionDes  客户描述
	 * @return
	 * link: @see dianfan.service.records.TakeLookService#editKnockdown(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultBean editKnockdown(String userid, String lookId, String estateId, String roomNum, String area,
			String totalMoney, String commissionMoney, String intentionDes, String specificDes, String tsDes
			,String commissionStatus,String commissionDes) {
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		// 通过带看ID，获取报备的ID
		String preparationId = takeLookMapper.findPreparationId(lookId);
		// 添加报备的ID
		param.put("preparationId", preparationId);
		
		// 查看一下登陆者的角色 04表示是案场
		String role = recordsMapper.findLoginRole(userid);
		if ("04".equals(role) || "01".equals(role)) {
			// 更新报备表
			param.put("estateId", estateId);
			param.put("userid", userid);
			param.put("intentionDes", intentionDes);
			param.put("commissionStatus", commissionStatus);
			param.put("commissionDes", commissionDes);
			// 更新客户意向描述，报备状态,楼盘id等
			takeLookMapper.upPreparation(param);
			
			// 更新带看表
			param.put("lookId", lookId);
			param.put("roomNum", roomNum);
			param.put("area", area);
			param.put("totalMoney", totalMoney);
			param.put("commissionMoney", commissionMoney);
			param.put("specificDes", specificDes);
			param.put("tsDes", tsDes);
			takeLookMapper.updateAffirmKnockdown(param);
		} else {
			// 更新报备表
			param.put("estateId", estateId);
			param.put("userid", userid);
			param.put("intentionDes", intentionDes);
			// 更新客户意向描述，报备状态,楼盘id等
			takeLookMapper.upPreparationBb(param);
		}
		
		return new ResultBean();
	}
	
	
	
	
	
	
	
	
	
	
	
}
