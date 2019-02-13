package dianfan.service.company.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.ptg.LessEqualPtg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dianfan.annotations.SystemServiceLog;
import dianfan.constant.ResultBgMsg;
import dianfan.dao.mapper.company.BackupsMapper;
import dianfan.entities.other.ModleList;
import dianfan.entities.user.Records;
import dianfan.entities.user.RecordsInfo;
import dianfan.models.ResultBean;
import dianfan.service.company.BackupsService;
import dianfan.util.StringUtility;

/**
 * @ClassName BackupsServiceImpl
 * @Description 报备相关
 * @author sz
 * @date 2018年8月25日 下午2:39:43
 */
@Service
public class BackupsServiceImpl implements BackupsService {

	/**
	 * 注入: #BackupsMapper
	 */
	@Autowired
	private BackupsMapper backupsMapper;

	/*
	 * (non-Javadoc)
	 * <p>Title: findBackupsList</p>
	 * <p>Description: 获取报备列表</p>
	 * @param userid userid
	 * @param page 请求页面
	 * @param pageSize 每页条数
	 * @param telno 手机号码
	 * @param cusName 客户姓名
	 * @param applyFlag 审核状态
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 * link: @see dianfan.service.company.BackupsService#findBackupsList(java.lang.String, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@SystemServiceLog(method = "findBackupsList", description = "获取报备列表")
	public ResultBean findBackupsList(String userid, int page, int pageSize, String telno, String cusName,
			String applyFlag, String starttime, String endtime,String towerId,String companyId,String status
			,String userName,String commissionStatus) {
		// 创建返回参数模型
		Map<String, Object> data = new HashMap<>(); 
		
		// 创建入参模型
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("telno", telno);
		param.put("cusName", cusName);
		param.put("applyFlag", applyFlag);
		param.put("starttime", starttime);
		param.put("endtime", endtime);
		param.put("start", (page - 1 ) * pageSize);
		param.put("pageSize", pageSize);
		// 楼盘搜
		param.put("towerId", towerId);
		// 公司搜
		param.put("companyId", companyId);
		// 报备状态搜
		param.put("status", status);
		param.put("userName", userName);
		param.put("commissionStatus", commissionStatus);
		
		// 获取报备的总数量
		int count = backupsMapper.findBackupsCount(param);
		data.put("count", count);
		if (count == 0) {
			data.put("backupsList", new ArrayList<>() );
			return new ResultBean(data);
		}
		// 获取列表
		List<Records> backupsList = backupsMapper.findBackupsList(param);
		
		data.put("backupsList", backupsList);
		
		return new ResultBean(data);
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: updataBackupsApprove</p>
	 * <p>Description: 修改报备状态</p>
	 * @param userid userid
	 * @param ids 报备的ID
	 * @param flag 状态
	 * @return
	 * link: @see dianfan.service.company.BackupsService#updataBackupsApprove(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	@SystemServiceLog(method = "updataBackupsApprove", description = "修改报备状态")
	public ResultBean updataBackupsApprove(String userid, String id, String flag) {
		// 判断flag
		if("02".equals(flag)) {
			// 状态修改为02 审核通过，需验证报备的楼盘ID下有没有对应的暗场，没有则报错
			String userInfoId = backupsMapper.getCheckEstateUserInfo(id);
			
			if (StringUtility.isNull(userInfoId)) {
				// 当前楼盘没有对应暗场，审批失败
				return new ResultBean("1004",ResultBgMsg.C_1004);
			}
		}
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("id", id);
		param.put("flag", flag);
		// 修改状态
		backupsMapper.updataBackupsApprove(param);
		
		return new ResultBean();
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: getBackupsInfo</p>
	 * <p>Description: 获取报备的详情</p>
	 * @param userid
	 * @param id
	 * @return
	 * link: @see dianfan.service.company.BackupsService#getBackupsInfo(java.lang.String, java.lang.String)
	 */
	@Override
	@SystemServiceLog(method = "getBackupsInfo", description = "获取报备的详情报备状态")
	public ResultBean getBackupsInfo(String userid, String id) {
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("id", id);
		// 获取报备的详情
		RecordsInfo records =  backupsMapper.getBackupsInfo(param);
		// 成功返回
		return new ResultBean(records);
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: findStatisticsList</p>
	 * <p>Description: 获取统计列表</p>
	 * @param userid userid
	 * @param flag 区分 (0: 按楼盘； 1： 按中介公司)
	 * @param status 报备状态 (01: 报备； 04： 成交)
	 * @param starttime 按开始时间
	 * @param endtime 按结束时间
	 * @return
	 * link: @see dianfan.service.company.BackupsService#findStatisticsList(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultBean findStatisticsList(String userid, String flag, String starttime, String endtime, String status) throws ParseException {
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("starttime", starttime);
		param.put("endtime", endtime);
		param.put("status", status);
		
		List<ModleList> list = new ArrayList<>();
		// 通过传入的flag 区分 是按公司给数据 还是按楼盘给数据
		if ("0".equals(flag)) {
			// 按楼盘
			if ("01".equals(status)) {
				// 报备数据 (报备时间)
				list = backupsMapper.findStatisticsList(param);
			} else {
				// 成交数据(成交时间)
				list = backupsMapper.findStatisticsListCj(param);
			}
		} else {
			// 按中介公司
			if ("01".equals(status)) {
				// 报备数据 (报备时间)
				list = backupsMapper.findStatisticsListGsBb(param);
			} else {
				// 成交数据(成交时间)
				list = backupsMapper.findStatisticsListGsCj(param);
			}
		}
		// 整理数据
		Map<Object, Object> data = new HashMap<>();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟

		/*String fromDate = sdf.format(starttime);
		String toDate = sdf.format(endtime);*/
		long from = sdf.parse(starttime).getTime();
		long to = sdf.parse(endtime).getTime();
		int days = (int) ((to - from)/(1000 * 60 * 60 * 24));
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(starttime));//设置起时间
		
		List<Map<String, Object>> returnList = new ArrayList<>();
		
		for(int i = 0 ; i < days+1 ; i++) {
			Map<String, Object> map = new HashMap<>(); 
			map.put("time", sdf.format(cal.getTime()));
			map.put("data", new ArrayList<Map<String, Object>>());
			returnList.add(map);
			cal.add(Calendar.DATE, 1);//增加一天 
		}
		
		for (ModleList ml : list) {
			for(Map<String, Object> map : returnList) {
				if(StringUtils.equals(ml.getTime(), (String)map.get("time"))) {
					List<Map<String, Object>> l = (List<Map<String, Object>>) map.get("data");
					Map<String, Object> m = new HashMap<>(); 
					m.put("id", ml.getId());
					m.put("name", ml.getName());
					m.put("count", ml.getCount());
					l.add(m);
				}
			}
		}		
		
		return new ResultBean(returnList);
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: findBackupsListData</p>
	 * <p>Description: </p>
	 * @param userid userid
	 * @param page 请求页
	 * @param pageSize 请求数量
	 * @param telno 手机号码
	 * @param cusName 名字搜索
	 * @param id 楼盘ID 或者 公司ID
	 * @return
	 * link: @see dianfan.service.company.BackupsService#findBackupsListData(java.lang.String, int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultBean findBackupsListData(String userid, int page, int pageSize, String id, String time, String telno,
			String flag, String status, String cusName) {
		// 创建返回参数模型
		Map<String, Object> data = new HashMap<>();
		
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("start", (page - 1) * pageSize);
		param.put("pageSize", pageSize);
		param.put("telno", telno);
		param.put("cusName", cusName);
		param.put("id", id);
		param.put("time", time);
		
		int count = 0 ;
		List<Records> backupsList = new ArrayList<>();
		
		if ("0".equals(flag)) {
			// 主键ID是楼盘的ID
			if ("01".equals(status)) {
				// 查询 对应楼盘 当天的报备数量列表
				// 时间是报备的创建时间
				
				count = backupsMapper.getBackupsListDataCount(param);
				data.put("count", count);
				if (count == 0) {
					data.put("backupsList", new ArrayList<>());
					return new ResultBean(data);
				} 
				// 查询符合条件的列表数量
				backupsList = backupsMapper.getBackupsListData(param);
				data.put("backupsList", backupsList);
			} else {
				// 查询 对应楼盘 当天的成交数量列表
				// 时间是成交的时间
				
				count = backupsMapper.getBackupsListDataCountCj(param);
				data.put("count", count);
				if (count == 0) {
					data.put("backupsList", new ArrayList<>());
					return new ResultBean(data);
				} 
				// 查询符合条件的列表数量
				backupsList = backupsMapper.getBackupsListDataCj(param);
				data.put("backupsList", backupsList);
			}
		} else {
			// 主键ID是公司的ID
			if ("01".equals(status)) {
				// 查询 对应公司 当天的报备数量列表
				// 时间是报备的创建时间
				
				count = backupsMapper.getBackupsListDataCountGs(param);
				data.put("count", count);
				if (count == 0) {
					data.put("backupsList", new ArrayList<>());
					return new ResultBean(data);
				} 
				// 查询符合条件的列表数量
				backupsList = backupsMapper.getBackupsListDataGs(param);
				data.put("backupsList", backupsList);
				
				
			} else {
				// 查询 对应公司 当天的成交数量列表
				// 时间是成交的时间
				
				count = backupsMapper.getBackupsListDataCountGsCj(param);
				data.put("count", count);
				if (count == 0) {
					data.put("backupsList", new ArrayList<>());
					return new ResultBean(data);
				} 
				// 查询符合条件的列表数量
				backupsList = backupsMapper.getBackupsListDataGsCj(param);
				data.put("backupsList", backupsList);
				
			}
		}
		
		// 返回
		return new ResultBean(data);
	} 
	
}
