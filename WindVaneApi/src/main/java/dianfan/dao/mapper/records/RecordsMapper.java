package dianfan.dao.mapper.records;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import dianfan.entities.user.Records;

/**
 * @ClassName RecordsMapper
 * @Description 备案相关
 * @author sz
 * @date 2018年8月20日 下午4:04:13
 */
@Repository
public interface RecordsMapper {

	/**
	 * @Title: getRecordsInfo
	 * @Description: 获取这个手机号码 （除登陆者ID），状态是确认到达带看的数据
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月20日 下午5:20:28
	 */
	@Select("SELECT tp.id, tp.telno, tp.cus_name cusName, tp.preparation_user_id preparationUserId, tp.estate_id estateId, tp.apply_flag applyFlag, tp. STATUS, tp.appointment_date appointmentDate, tl.take_look_time looktime "
			+ "FROM t_preparation tp LEFT JOIN t_take_look tl ON tl.preparation_id = tp.id WHERE tp.preparation_user_id != #{userid} AND tp.telno = #{telno} AND tp.apply_flag = '02' AND tp. STATUS != '01' AND tp.entkbn = 0 ")
	List<Records> getRecordsInfo(Map<String, Object> param);

	/**
	 * @Title: getCheckOneself
	 * @Description: 本人的带看信息
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月21日 上午11:58:34
	 */
	@Select("SELECT tp.id, tp.telno, tp.cus_name cusName, tp.preparation_user_id preparationUserId, tp.estate_id estateId, tp.apply_flag applyFlag, tp. STATUS, tp.appointment_date appointmentDate, tl.take_look_time looktime, tp.create_time createTime FROM t_preparation tp LEFT JOIN t_take_look tl ON tl.preparation_id = tp.id WHERE tp.preparation_user_id = #{userid} AND tp.telno = #{telno} AND tp.estate_id = #{tower} AND tp.entkbn = 0  ")
	List<Records> getCheckOneself(Map<String, Object> param);
	
	
	/**
	 * @Title: addRecords
	 * @Description: 添加报备信息
	 * @param param
	 * @throws:
	 * @time: 2018年8月20日 下午5:35:07
	 */
	@Insert("INSERT INTO t_preparation (id,telno,cus_name,preparation_user_id,estate_id,apply_flag,status,appointment_date,appointment_time,intention_des,create_by, create_time) "
			+ "VALUES (#{id},#{telno},#{name},#{userid},#{tower},'01','01',now(),#{time},#{describe},#{userid},now() )")
	void addRecords(Map<String, Object> param);
	
	/**
	 * @Title: getLooktime
	 * @Description: 查案带看时间
	 * @param id 报备id
	 * @return
	 * @throws:
	 * @time: 2018年8月20日 下午6:17:58
	 */
	@Select("SELECT take_look_time FROM t_take_look WHERE preparation_id = #{id} AND entkbn = 0")
	Date getLooktime(String id);

	/**
	 * @Title: findRecordsCount
	 * @Description: 获取已报备数量
	 * @param userid 登陆者ID
	 * @return int
	 * @throws:
	 * @time: 2018年8月21日 下午2:21:08
	 */
	@Select("SELECT COUNT(*) FROM t_preparation WHERE status = '01' AND entkbn = 0 AND date(appointment_date)=date(now()) AND preparation_user_id = #{userid} ")
	int findRecordsCount(Map<String, Object> parma);
	/**
	 * @Title: findRecordsCountFoAc
	 * @Description: 楼盘对应的报备信息
	 * @param estateId
	 * @return
	 * @throws:
	 * @time: 2018年8月25日 下午6:36:02
	 */
	@Select("SELECT COUNT(*) FROM t_preparation WHERE status = '01' AND apply_flag = '02' AND entkbn = 0 AND date(appointment_date)=date(now()) AND estate_id = #{estateId} ")
	int findRecordsCountFoAc(Map<String, Object> parma);
	
	/**
	 * @Title: findRecordsList
	 * @Description: 获取登陆者的报备列表
	 * @param userid 登陆者ID
	 * @return
	 * @throws:
	 * @time: 2018年8月21日 下午2:24:51
	 */
	@Select("SELECT tp.id, tp.telno,tp.estate_id estateId, tp.cus_name cusName, tp.appointment_date appointmentDate, tp.appointment_time appointmentTime, tp.status, tp.apply_flag applyFlag, tp.intention_des intentionDes , tei.estate_name estateName,tuu.name userName,tc.company_name companyName ,tp.create_time createTime FROM t_preparation tp LEFT JOIN t_estate_info tei ON tp.estate_id = tei.id LEFT JOIN t_user_userinfo tuu ON tuu.id = tp.preparation_user_id LEFT JOIN t_company tc ON tuu.company_id = tc.id WHERE tp.entkbn = 0 AND tp.STATUS = '01' AND date(tp.appointment_date)=date(now()) AND tp.preparation_user_id = #{userid} ORDER BY tp.create_time DESC LIMIT #{start},#{pageSize} ")
	List<Records> findRecordsList(Map<String, Object> parma);
	@Select("SELECT tp.id, tp.telno,tp.estate_id estateId, tp.cus_name cusName, tp.appointment_date appointmentDate, tp.appointment_time appointmentTime, tp.status, tp.apply_flag applyFlag, tp.intention_des intentionDes , tei.estate_name estateName,tuu.name userName,tc.company_name companyName ,tp.create_time createTime FROM t_preparation tp LEFT JOIN t_estate_info tei ON tp.estate_id = tei.id LEFT JOIN t_user_userinfo tuu ON tuu.id = tp.preparation_user_id LEFT JOIN t_company tc ON tuu.company_id = tc.id WHERE tp.entkbn = 0 AND tp.STATUS = '01' AND tp.apply_flag = '02' AND date(tp.appointment_date)=date(now()) AND tp.estate_id = #{estateId} ORDER BY tp.create_time DESC LIMIT #{start},#{pageSize}")
	List<Records> findRecordsListFoAc(Map<String, Object> parma);
	
	/**
	 * @Title: updateIntention
	 * @Description: 更新客户的意向描述
	 * @param parma
	 * @throws:
	 * @time: 2018年8月21日 下午3:32:26
	 */
	@Update("UPDATE t_preparation SET intention_des = #{intention} , update_time = NOW(), update_by = #{userid} WHERE id = #{recordsid} ")
	void updateIntention(Map<String, Object> parma);

	/**
	 * @Title: addTakeLookInfo
	 * @Description: 创建一个带看表
	 * @param param
	 * @throws:
	 * @time: 2018年8月21日 下午4:07:04
	 */
	@Insert("INSERT INTO t_take_look SET id = REPLACE(uuid(),'-','') ,preparation_id = #{id} , preparation_user_id = #{userid} , create_by = #{userid} , create_time = NOW()")
	void addTakeLookInfo(Map<String, Object> param);

	/**
	 * @Title: updateTakeLook
	 * @Description: 更新带看表信息
	 * @param parma
	 * @throws:
	 * @time: 2018年8月21日 下午4:16:01
	 */
	@Update("UPDATE t_take_look SET take_look_time = #{time},take_look_user_id = #{userid} ,room_num = #{roomnum}, specific_des =#{specific}, "
			+ "update_time= NOW(), update_by = #{userid} WHERE id = #{recordsid}")
	void updateTakeLook(Map<String, Object> parma);
	@Update("UPDATE t_take_look SET take_look_time = #{time},take_look_user_id = #{userid} ,room_num = #{roomnum}, specific_des =#{specific}, "
			+ "update_time= NOW(), update_by = #{userid} WHERE preparation_id = #{recordsid}")
	void updateTakeLookQR(Map<String, Object> parma);

	/**
	 * @Title: getRecordsInfotoRecordsid
	 * @Description: 获取报备的完整信息
	 * @param parma
	 * @return
	 * @throws:
	 * @time: 2018年8月21日 下午5:03:39
	 */
	Records getRecordsInfotoRecordsid(Map<String, Object> parma);
	
	/**
	 * @Title: getRecordsInfotoRecordsid
	 * @Description: 获取带看的完整信息
	 * @param parma
	 * @return
	 * @throws:
	 * @time: 2018年8月21日 下午5:03:39
	 */
	Records getLookInfos(Map<String, Object> parma);
	
	
	/**
	 * @Title: upAffirmTakeLook
	 * @Description: 确认带看
	 * @param parma
	 * @throws:
	 * @time: 2018年8月22日 上午10:16:37
	 */
	@Update("UPDATE t_preparation SET intention_des = #{intention} , status = '03' , update_time = NOW(), update_by = #{userid} WHERE id = #{preparationId}")
	void upAffirmTakeLook(Map<String, Object> parma);
	@Update("UPDATE t_preparation SET intention_des = #{intention} , status = '03' , update_time = NOW(), update_by = #{userid} WHERE id = #{recordsid}")
	void upAffirmTakeLookqz(Map<String, Object> parma);
	/**
	 * @Title: upAffirmTakeLookBb
	 * @Description: 报备者看成交信息
	 * @param parma
	 * @throws:
	 * @time: 2018年8月28日 下午3:43:46
	 */
	@Update("UPDATE t_preparation SET intention_des = #{intention} , update_time = NOW(), update_by = #{userid} WHERE id = #{preparationId}")
	void upAffirmTakeLookBb(Map<String, Object> parma);

	/**
	 * @Title: getroleNameById
	 * @Description: 根据
	 * @param roleid
	 * @return
	 * @throws:
	 * @time: 2018年8月25日 上午9:31:36
	 */
	@Select("SELECT role_id FROM m_role WHERE id = #{roleid} ")
	String getroleNameById(String roleid);

	/**
	 * @Title: findLoginRole
	 * @Description: 查看登陆者的角色
	 * @param userid
	 * @return
	 * @throws:
	 * @time: 2018年8月25日 下午6:14:34
	 */
	@Select("SELECT mr.role_id FROM t_user_userinfo info, m_role mr WHERE info.id = #{userid} AND info.role_id = mr.id")
	String findLoginRole(String userid);

	/**
	 * @Title: getEstateId
	 * @Description: 获取管理员对应的楼盘的ID
	 * @param userid
	 * @return
	 * @throws:
	 * @time: 2018年8月25日 下午6:28:28
	 */
	@Select("SELECT tei.id FROM t_user_userinfo tuu, t_estate_info tei WHERE tuu.id = #{userid} AND tuu.id = tei.user_info_id")
	String getEstateId(String userid);

	/**
	 * @Title: editRecordsAll
	 * @Description: 修改报备
	 * @param parma
	 * @throws:
	 * @time: 2018年8月28日 下午2:16:51
	 */
	//@Update("UPDATE t_preparation SET telno = #{telno}, cus_name = #{name}, appointment_date = NOW(), appointment_time = #{time},intention_des = #{describe},estate_id = #{tower} , update_time = NOW(),update_by = #{userid} WHERE id = #{id} ")
	void editRecordsAll(Map<String, Object> parma);

	/**
	 * @Title: editRecordsBf
	 * @Description: 审批后，只能修改客户描述
	 * @param parma
	 * @throws:
	 * @time: 2018年8月28日 下午2:40:11
	 */
	@Update("UPDATE t_preparation SET intention_des = #{describe},update_time = NOW(),update_by = #{userid} WHERE id = #{id} ")
	void editRecordsBf(Map<String, Object> parma);

	/**
	 * @Title: getpreparationId
	 * @Description: 获取报备的id
	 * @param recordsid
	 * @return
	 * @throws:
	 * @time: 2018年8月29日 上午11:54:01
	 */
	@Select("SELECT preparation_id from t_take_look WHERE id = #{recordsid} AND entkbn = 0")
	String getpreparationId(String recordsid);

	/**
	 * @Title: getCheckInfoBytelno
	 * @Description: 查看这个楼盘这个手机号 是否已经报备过
	 * @param parma
	 * @return
	 * @throws:
	 * @time: 2018年8月30日 上午10:05:45
	 */
	@Select("SELECT COUNT(*) FROM t_preparation WHERE telno = #{telno} AND estate_id = #{tower} "
			+ "AND date(appointment_date)=date(now()) AND apply_flag != '03' AND id != #{id} ")
	int getCheckInfoBytelno(Map<String, Object> parma);

	


	



}
