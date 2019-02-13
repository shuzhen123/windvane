package dianfan.service.records;

import java.text.ParseException;

import dianfan.models.ResultBean;

/**
 * @ClassName RecordsService
 * @Description 备案相关
 * @author sz
 * @date 2018年8月20日 下午4:01:21
 */
public interface RecordsService {

	/**
	 * @Title: addRecords
	 * @Description: 添加备案信息
	 * @param userid 登陆者ID
	 * @param name 姓名
	 * @param telno 手机号码
	 * @param tower 楼盘
	 * @param time 时间
	 * @param describe 描述
	 * @return
	 * @throws ParseException 
	 * @throws:
	 * @time: 2018年8月20日 下午4:17:56
	 */
	ResultBean addRecords(String userid, String name, String telno, String tower, String time, String describe) throws ParseException;

	/**
	 * @Title: findRecordsList
	 * @Description: 获取报备信息列表
	 * @param userid 用户ID
	 * @return
	 * @throws:
	 * @time: 2018年8月21日 下午2:17:35
	 */
	ResultBean findRecordsList(String userid,int page,int pageSize);

	/**
	 * @Title: editrecords
	 * @Description: 编辑完整报备信息
	 * @param userid 登陆者ID
	 * @param time 带看时间
	 * @param roomnum 房间号码
	 * @param intention 客户意向描述
	 * @param specific 具体描述
	 * @param tsdes 成交描述
	 * @return
	 * @throws ParseException 
	 * @throws:
	 * @time: 2018年8月21日 下午3:11:58
	 */
	ResultBean editrecords(String userid, String time, String roomnum, String intention, String specific, String recordsid) throws ParseException;

	/**
	 * @Title: getRecordsInfo
	 * @Description: 报备信息详情
	 * @param userid 用户ID
	 * @param recordsid 报备ID
	 * @return
	 * @throws:
	 * @time: 2018年8月21日 下午4:45:02
	 */
	ResultBean getRecordsInfo(String userid, String recordsid);

	/**
	 * @Title: upAffirmTakeLook
	 * @Description: 确认带看
	 * @param userid 用户ID
	 * @param recordsid 报备ID
	 * @return
	 * @throws ParseException 
	 * @throws:
	 * @time: 2018年8月22日 上午10:13:12
	 */
	ResultBean upAffirmTakeLook(String userid, String recordsid,String time,String roomnum,String intention,String specific) throws ParseException;

	/**
	 * @Title: getLookInfo
	 * @Description: 
	 * @param userid
	 * @param lookId
	 * @param roleid
	 * @return
	 * @throws:
	 * @time: 2018年8月25日 下午1:45:20
	 */
	ResultBean getLookInfo(String userid, String lookId);

	/**
	 * @Title: editRecords
	 * @Description: 修改报备列表
	 * @param userid userid
	 * @param id id
	 * @param flag 状态
	 * @param name 姓名
	 * @param telno 手机号码
	 * @param tower 楼盘
	 * @param time 时间
	 * @param describe 描述
	 * @return
	 * @throws:
	 * @time: 2018年8月28日 下午2:02:38
	 */
	ResultBean editRecords(String userid, String id, String flag, String name, String telno, String tower, String time,
			String describe);

	/**
	 * @Title: editTakeLook
	 * @Description: 
	 * @param userid
	 * @param recordsid
	 * @param time
	 * @param roomnum
	 * @param intention
	 * @param specific
	 * @return
	 * @throws ParseException 
	 * @throws:
	 * @time: 2018年8月28日 下午3:10:18
	 */
	ResultBean editTakeLook(String userid, String recordsid, String time, String roomnum, String intention,
			String specific) throws ParseException;

	

}
