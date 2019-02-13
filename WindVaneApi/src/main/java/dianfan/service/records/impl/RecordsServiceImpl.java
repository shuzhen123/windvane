package dianfan.service.records.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.parser.MacOsPeterFTPEntryParser;
import org.jaxen.function.IdFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mysql.fabric.xmlrpc.base.Param;

import dianfan.constant.ConstantIF;
import dianfan.constant.ResultApiMsg;
import dianfan.dao.mapper.records.RecordsMapper;
import dianfan.date.DateUtility;
import dianfan.date.StringToDate;
import dianfan.entities.user.Records;
import dianfan.models.ResultBean;
import dianfan.service.records.RecordsService;
import dianfan.util.StringUtility;
import dianfan.util.UUIDUtil;
import net.sf.ehcache.search.expression.And;

/**
 * @ClassName RecordsServiceImpl
 * @Description 备案相关
 * @author sz
 * @date 2018年8月20日 下午4:02:27
 */
@Service
public class RecordsServiceImpl implements RecordsService {

	/**
	 * 注入： #RecordsMapper
	 */
	@Autowired
	private RecordsMapper recordsMapper;

	/*
	 * (non-Javadoc)
	 * <p>Title: addRecords</p>
	 * <p>Description: 添加备案信息</p>
	 * @param userid 登陆者ID
	 * @param name 姓名
	 * @param telno 手机号码
	 * @param tower 楼盘
	 * @param time 时间
	 * @param describe 描述
	 * @return
	 * link: @see dianfan.service.records.RecordsService#addRecords(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public ResultBean addRecords(String userid, String name, String telno, String tower, String time, String describe) throws ParseException {
		// 添加该客户的信息备案之前先看一下改用户是否已经被别人备案了
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("telno", telno);
		param.put("tower", tower);
		/*==报备者本人==*/ 
		//报备者,本人受楼盘的限制
		List<Records> diff = recordsMapper.getCheckOneself(param);
		if (null != diff && !diff.isEmpty()) {
			for (Records re : diff) {
				// 判断是否有带看时间
				if (StringUtils.isEmpty(re.getLooktime())) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					// 当前的日期
					String times = df.format(new Date());
					Date date=(Date) df.parse(times);
					// 预约的日期
					Date appointmentDate = re.getCreateTime();
					boolean after = date.after(appointmentDate);
					// 如果是 本人报备的还是这个楼盘,且状态是未带看，备份的时间是当天，就不能再报备
					if ("01".equals(re.getStatus()) && after == true ) {
					  // 该楼盘今日不可重复报备
					  return new ResultBean("1107",ResultApiMsg.C_1107);
					}
				} else {
					// 如果是有带看时间的，而且楼盘是当前楼盘 判断是否是在30天以内
					Date looktime = re.getLooktime();
					Date deliveryTime = DateUtility.getAddDayToTimeEnd(looktime, ConstantIF.PUT_ON_REDORCDS_SECONDS);
					// 获取当前时间 Date 类型
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					String times = df.format(new Date());
					Date date=(Date) df.parse(times);
					// 比较大小
					int i = deliveryTime.compareTo(date); 
					if (i > 0) {
						// 时间还没有超过30天
						return new ResultBean("1006",ResultApiMsg.C_1006);
					}
				}
			}
		} else {
			/*==非报备者本人==*/ 
			// 获取这个手机号码 （除登陆者ID） 之外的 30 天的报备记录 ，状态是确认到达带看的数据，不受楼盘限制
			List<Records> count = recordsMapper.getRecordsInfo(param);
			// 该手机号30天内存在备案信息
			if (null != count && !count.isEmpty()) {
				for (Records rec : count ) {
					// 获取该手机号的带看时间，在带上时间基础上 + 30 天，如果大于现在则可以重新报备
					Date looktime = rec.getLooktime();
					Date deliveryTime = DateUtility.getAddDayToTimeEnd(looktime, ConstantIF.PUT_ON_REDORCDS_SECONDS);
					// 获取当前时间
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					String times = df.format(new Date());
					Date date=(Date) df.parse(times);
					// 比较大小
					int i = deliveryTime.compareTo(date); 
					if (i > 0) {
						// 时间还没有超过30天
						return new ResultBean("1006",ResultApiMsg.C_1006);
					}
				}
			}
		}
		// 时间格式转换
		Date times = StringToDate.StringToDate(time, "HH:mm");
		param.put("time", times);
		param.put("describe", describe);
		// 创建一个新的UUID
		param.put("id", UUIDUtil.getUUID());
		param.put("name", name);
		// 添加备案信息
		recordsMapper.addRecords(param);
		/* 因为在编辑报备完整信息的时候，会涉及带看信息的添加，所以在添加完报备信息后，创建一个报备的带看表 */
		recordsMapper.addTakeLookInfo(param);
		// 成功返回
		return new ResultBean();
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: findRecordsList</p>
	 * <p>Description: 获取该登陆者当天的报备列表</p>
	 * @param userid
	 * @return
	 * link: @see dianfan.service.records.RecordsService#findRecordsList(java.lang.String)
	 */
	@Override
	@Transactional
	public ResultBean findRecordsList(String userid,int page,int pageSize) {
		// 构建返回数据模型
		Map<String, Object> data = new HashMap<>();
		
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		param.put("userid", userid);
		param.put("start", ( page-1 ) * pageSize);
		param.put("pageSize", pageSize);
		
		// 查看一下登陆者的角色
		String role = recordsMapper.findLoginRole(userid);
		if ("04".equals(role) || "01".equals(role)) {
			// 获取案场所对应的楼盘的ID
			String estateId = recordsMapper.getEstateId(userid);
			if (StringUtility.isNull(estateId)) {
				return new ResultBean("1001",ResultApiMsg.C_1010);
			}
			param.put("estateId", estateId);
			
			// 角色时 04 说明是案场  案场只能查看到自己楼盘的报备信息
			int count = recordsMapper.findRecordsCountFoAc(param);
			// 添加总条数
			data.put("count", count);
			if (count == 0) {
				data.put("findRecordsList", new ArrayList<>());
				return new ResultBean(data);
			}
			List<Records> findRecordsList =  recordsMapper.findRecordsListFoAc(param);
			data.put("findRecordsList", findRecordsList);
		} else {
			int count = recordsMapper.findRecordsCount(param);
			// 添加总条数
			data.put("count", count);
			if (count == 0) {
				data.put("findRecordsList", new ArrayList<>());
				
			}
			// 获取该登陆者当天的报备列表
			List<Records> findRecordsList =  recordsMapper.findRecordsList(param);
			
			data.put("findRecordsList", findRecordsList);
			
		}
		
		// 返回
		return new ResultBean(data);
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: editrecords</p>
	 * <p>Description: 编辑完整报备信息</p>
	 * @param userid userid
	 * @param time 带看时间
	 * @param roomnum 房间号码
	 * @param intention 客户意向描述
	 * @param specific 具体描述
	 * @param tsdes 成交描述
	 * @return
	 * link: @see dianfan.service.records.RecordsService#editrecords(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public ResultBean editrecords(String userid, String time, String roomnum, String intention, String specific,
			String recordsid) throws ParseException {
		// 创建入参容器
		Map<String, Object> parma = new HashMap<>();
		parma.put("userid", userid);
		parma.put("intention", intention);
		parma.put("recordsid", recordsid);
		// 更新用户的意向描述
		recordsMapper.updateIntention(parma);
		// 时间类型装换
		// 获取当前时间 Date 类型
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date times=(Date) df.parse(time);
		parma.put("time", times);
		parma.put("roomnum", roomnum);
		parma.put("specific", specific);
		// 更新带看表
		recordsMapper.updateTakeLook(parma);
		return new ResultBean();
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: getRecordsInfo</p>
	 * <p>Description: 获取报备的详情信息</p>
	 * @param userid 登陆者ID
	 * @param recordsid 报备ID
	 * @return
	 * link: @see dianfan.service.records.RecordsService#getRecordsInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public ResultBean getRecordsInfo(String userid, String recordsid) {
		// 创建入参容器
		Map<String, Object> parma = new HashMap<>();
		parma.put("userid", userid);
		parma.put("recordsid", recordsid);
		// 获取详情
		Records info = recordsMapper.getRecordsInfotoRecordsid(parma);;
		// 返回
		return new ResultBean(info);
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: getLookInfo</p>
	 * <p>Description: 带看详情</p>
	 * @param userid
	 * @param lookId
	 * @return
	 * link: @see dianfan.service.records.RecordsService#getLookInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public ResultBean getLookInfo(String userid, String lookId) {
		// 创建入参容器
		Map<String, Object> parma = new HashMap<>();
		parma.put("userid", userid);
		parma.put("lookId", lookId);
		Records info = recordsMapper.getLookInfos(parma);
		// 返回
		return new ResultBean(info);
	}
	
	
	/*
	 * (non-Javadoc)
	 * <p>Title: upAffirmTakeLook</p>
	 * <p>Description: 确认带看</p>
	 * @param userid 用户ID
	 * @param recordsid 报备ID
	 * @return
	 * link: @see dianfan.service.records.RecordsService#upAffirmTakeLook(java.lang.String, java.lang.String)
	 */
	@Transactional
	@Override
	public ResultBean upAffirmTakeLook(String userid, String recordsid,String time,String roomnum,String intention,String specific) throws ParseException {
		// 创建入参容器
		Map<String, Object> parma = new HashMap<>();
		parma.put("userid", userid);
		parma.put("intention", intention);
		parma.put("recordsid", recordsid);
		// 更新 报备表中的带看状态 02,更新客户的意向描述
		recordsMapper.upAffirmTakeLookqz(parma);
		
		// 时间类型装换
		// 获取当前时间 Date 类型
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date times=(Date) df.parse(time);
		parma.put("time", times);
		parma.put("roomnum", roomnum);
		parma.put("specific", specific);
		// 更新带看表
		recordsMapper.updateTakeLookQR(parma);
		// 返回
		return new ResultBean();
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: editRecords</p>
	 * <p>Description: </p>
	 * @param userid userid
	 * @param id id
	 * @param flag 状态
	 * @param name 姓名
	 * @param telno 手机号码
	 * @param tower 楼盘
	 * @param time 时间
	 * @param describe 描述
	 * @return
	 * link: @see dianfan.service.records.RecordsService#editRecords(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultBean editRecords(String userid, String id, String flag, String name, String telno, String tower,
			String time, String describe) {
		// 创建入参容器
		Map<String, Object> parma = new HashMap<>();
		
		// 确认当前报备的状态
		if ("01".equals(flag)) {
			// 当前报备还是未带看的状态
			parma.put("id", id);
			parma.put("userid", userid);
			parma.put("name", name);
			parma.put("telno", telno);
			parma.put("tower", tower);
			parma.put("time", time);
			parma.put("describe", describe);
			if (!StringUtility.isNull(telno) || !StringUtility.isNull(tower) ) {
				// 修改客户的手机 或者楼盘的时候，先确认一下  改手机号当天有没有报备过该楼盘
				int sum = recordsMapper.getCheckInfoBytelno(parma);
				if (sum != 0) {
					// 今日不可重复带看
					return new ResultBean("1007",ResultApiMsg.C_1007);
				}
			}
			recordsMapper.editRecordsAll(parma);			
		} else {
			// 不是未带看的状态了 只能修改客户的意向
			parma.put("id", id);
			parma.put("userid", userid);
			parma.put("describe", describe);
			recordsMapper.editRecordsBf(parma);			
		}
		
		return new ResultBean();
	}

	/*
	 * (non-Javadoc)
	 * <p>Title: editTakeLook</p>
	 * <p>Description: 修改带看信息</p>
	 * @param userid  userid
	 * @param recordsid
	 * @param time
	 * @param roomnum
	 * @param intention
	 * @param specific
	 * @return
	 * link: @see dianfan.service.records.RecordsService#editTakeLook(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultBean editTakeLook(String userid, String recordsid, String time, String roomnum, String intention,
			String specific) throws ParseException {
		// 创建入参容器
		Map<String, Object> parma = new HashMap<>();
		// 通过带看的ID 找到报备的ID
		String preparationId = recordsMapper.getpreparationId(recordsid);
		parma.put("preparationId", preparationId);
		
		// 查看一下登陆者的角色 04表示是案场 01表示销售经理 
		String role = recordsMapper.findLoginRole(userid);
		if ("04".equals(role) || "01".equals(role)) {
			// 案场 全部都可以修改
			parma.put("userid", userid);
			parma.put("intention", intention);
			parma.put("recordsid", recordsid);
			// 更新 报备表中的带看状态 02,更新客户的意向描述
			recordsMapper.upAffirmTakeLook(parma);
			// 时间类型装换
			// 获取当前时间 Date 类型
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			Date times=(Date) df.parse(time);
			parma.put("time", times);
			parma.put("roomnum", roomnum);
			parma.put("specific", specific);
			// 更新带看表
			recordsMapper.updateTakeLook(parma);
			
		} else {	
			// 报备者登陆只能修改客户意向
			parma.put("userid", userid);
			parma.put("intention", intention);
			parma.put("recordsid", recordsid);
			// 更新 报备表中的带看状态 02,更新客户的意向描述
			recordsMapper.upAffirmTakeLookBb(parma);
		}
		
		return new ResultBean();
	}

	
	
	
	
}
