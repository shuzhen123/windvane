package dianfan.dao.mapper.records;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import dianfan.entities.user.Records;
import dianfan.entities.user.TakeLook;

/**
 * @ClassName TakeLookMapper
 * @Description 带看相关dao
 * @author sz
 * @date 2018年8月22日 上午10:53:45
 */
@Repository
public interface TakeLookMapper {

	/**
	 * @Title: findTakeLookCount
	 * @Description: 获取带看列表数量
	 * @param userid
	 * @return
	 * @throws:
	 * @time: 2018年8月22日 上午11:25:42
	 */
	@Select("SELECT COUNT(*) FROM t_take_look tl LEFT JOIN t_preparation tp ON tl.preparation_id = tp.id WHERE tl.take_look_user_id = #{userid} AND tl.entkbn = 0 and tp.status = '03'")
	int findTakeLookCount(String userid);

	/**
	 * @Title: findTakeLookCountBb
	 * @Description: 报备者的ID登陆，可查看到自己报备的 而且已经发生带看的记录
	 * @param userid
	 * @return
	 * @throws:
	 * @time: 2018年8月28日 下午12:04:26
	 */
	@Select("SELECT COUNT(*) FROM t_take_look tl LEFT JOIN t_preparation tp ON tl.preparation_id = tp.id WHERE tl.preparation_user_id = #{userid} AND tl.entkbn = 0 and tp.status = '03'")
	int findTakeLookCountBb(String userid);
	
	
	/**
	 * @Title: findTakeLookList
	 * @Description: 获取带看列表
	 * @param userid
	 * @return
	 * @throws:
	 * @time: 2018年8月22日 上午11:34:35
	 */
	@Select("SELECT tl.id , tl.preparation_id preparationId, tl.take_look_time takeLookTime, tl.preparation_user_id preparationUserId, tp.cus_name cusName, tp.telno telno,tuu.name userName,tc.company_name companyName,tp.create_time createTime FROM t_take_look tl LEFT JOIN t_preparation tp ON tl.preparation_id = tp.id LEFT JOIN t_user_userinfo tuu ON tuu.id = tl.preparation_user_id LEFT JOIN t_company tc ON tuu.company_id = tc.id WHERE tl.take_look_user_id = #{userid} AND tp.status = '03'  ORDER BY tl.take_look_time DESC LIMIT #{start},#{pageSize} ")
	List<TakeLook> findTakeLookList(Map<String, Object> param);


	@Select("SELECT tl.id , tl.preparation_id preparationId, tl.take_look_time takeLookTime, tl.preparation_user_id preparationUserId, tp.cus_name cusName, tp.telno telno,tuu.name userName,tc.company_name companyName,tp.create_time createTime FROM t_take_look tl LEFT JOIN t_preparation tp ON tl.preparation_id = tp.id LEFT JOIN t_user_userinfo tuu ON tuu.id = tl.preparation_user_id LEFT JOIN t_company tc ON tuu.company_id = tc.id WHERE tl.preparation_user_id = #{userid} AND tp.status = '03'  ORDER BY tl.take_look_time DESC LIMIT #{start},#{pageSize} ")
	List<TakeLook> findTakeLookListBb(Map<String, Object> param);
	
	
	
	
	/**
	 * @Title: upPreparation
	 * @Description: 确认成交时更新报备表
	 * @param param
	 * @throws:
	 * @time: 2018年8月22日 下午1:58:48
	 */
	//@Update("UPDATE t_preparation SET status = '04' , intention_des = #{intentionDes},estate_id = #{estateId} , update_time = NOW(), update_by = #{userid} , commission_status = #{commissionStatus} WHERE id = #{preparationId} ")
	void upPreparation(Map<String, Object> param);
	@Update("UPDATE t_preparation SET intention_des = #{intentionDes}, update_time = NOW(), update_by = #{userid} WHERE id = #{preparationId} ")
	void upPreparationBb(Map<String, Object> param);

	/**
	 * @Title: findPreparationId
	 * @Description: 获取报备的ID 
	 * @param lookId
	 * @return
	 * @throws:
	 * @time: 2018年8月22日 下午2:09:13
	 */
	@Select("SELECT preparation_id FROM t_take_look WHERE id = #{lookId} AND entkbn = 0")
	String findPreparationId(String lookId);


	/**
	 * @Title: updateAffirmKnockdown
	 * @Description: 成交时 更新带看表
	 * @param param
	 * @throws:
	 * @time: 2018年8月22日 下午2:46:15
	 */
	@Update("UPDATE t_take_look SET room_num = #{roomNum}, specific_des=#{specificDes},ts_des=#{tsDes},update_by=#{userid},update_time=NOW(), area=#{area},"
			+ "total_money=#{totalMoney},commission_money=#{commissionMoney},ts_date = now() WHERE id = #{lookId} ")
	void updateAffirmKnockdown(Map<String, Object> param);


	/**
	 * @Title: findknockdownCount
	 * @Description: 获取成交列表的总数
	 * @param userid userid
	 * @return 
	 * @throws:
	 * @time: 2018年8月22日 下午3:33:43
	 */
	int findknockdownCount(Map<String, Object> param);
	
	/**
	 * @Title: findknockdownCount
	 * @Description: 获取成交列表的总数(报备者)
	 * @param userid userid
	 * @return 
	 * @throws:
	 * @time: 2018年8月22日 下午3:33:43
	 */
	int findknockdownCountBb(Map<String, Object> param);


	/**
	 * @Title: findknockdownList
	 * @Description: 获取成交列表
	 * @param userid userid
	 * @return
	 * @throws:
	 * @time: 2018年8月22日 下午3:39:54
	 */
	List<Records> findknockdownList(Map<String, Object> param);
	/**
	 * @Title: findknockdownList
	 * @Description: 获取成交列表 （报备者）
	 * @param userid userid
	 * @return
	 * @throws:
	 * @time: 2018年8月22日 下午3:39:54
	 */
	List<Records> findknockdownListBb(Map<String, Object> param);


	/**
	 * @Title: getAffirmKnockdownInfo
	 * @Description: 确认成交的详细
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月24日 上午10:07:36
	 */
	Records getAffirmKnockdownInfo(Map<String, Object> param);

	





	



	
}
