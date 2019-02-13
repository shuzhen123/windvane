package dianfan.dao.mapper.company;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import dianfan.entities.other.EstateInfoModel;
import dianfan.entities.user.UserInfos;

/**
 * @ClassName StationedController
 * @Description 驻场相关dao
 * @author sz
 * @date 2018年8月27日 上午11:30:43
 */
@Repository
public interface StationedMapper {

	/**
	 * @Title: findStationedCount
	 * @Description: 获取驻场人员数量
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午3:50:21
	 */
	int findStationedCount(Map<String, Object> param);

	/**
	 * @Title: findStationedList
	 * @Description: 获取驻场人员列表
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午3:50:58
	 */
	List<UserInfos> findStationedList(Map<String, Object> param);

	/**
	 * @Title: getCheckStationedTelno
	 * @Description: 查看添加的账号在数据库中是否存在
	 * @param telno
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午4:53:42
	 */
	@Select("SELECT COUNT(*) FROM t_user_userinfo WHERE telno = #{telno} AND entkbn = 0")
	int getCheckStationedTelno(String telno);

	/**
	 * @Title: addStationed
	 * @Description: 添加驻场人员 
	 * @param param
	 * @throws:
	 * @time: 2018年8月27日 下午5:12:57
	 */
	@Insert("INSERT INTO t_user_userinfo (id,telno,pwd,role_id ,create_time,create_by,name) "
			+ "VALUES (REPLACE(uuid(),'-',''),#{telno},#{pwd},#{roleId},NOW(),#{userid},#{name})")
	void addStationed(Map<String, Object> param);

	/**
	 * @Title: editStationed
	 * @Description: 修改驻场人员
	 * @param param
	 * @throws:
	 * @time: 2018年8月27日 下午5:33:14
	 */
	void editStationed(Map<String, Object> param);

	/**
	 * @Title: getCheckEstate
	 * @Description: 查看楼盘是否已经有驻场了
	 * @param estateId
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午5:45:50
	 */
	@Select("SELECT user_info_id FROM t_estate_info WHERE id = #{estateId} AND entkbn = 0 AND user_info_id != #{id} ")
	String getCheckEstate(Map<String, Object> param);

	/**
	 * @Title: getCheckStationed
	 * @Description: 
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午5:55:57
	 */
	@Select("SELECT id FROM t_estate_info WHERE user_info_id = #{id} AND entkbn = 0 AND id != #{estateId}")
	String getCheckStationed(Map<String, Object> param);

	/**
	 * @Title: bindingStationed
	 * @Description:绑定驻场和楼盘的关系 
	 * @param param
	 * @throws:
	 * @time: 2018年8月27日 下午6:00:21
	 */
	@Update("UPDATE t_estate_info SET user_info_id = #{id} ,update_time = NOW(),update_by = #{userid} WHERE id = #{estateId}")
	void bindingStationed(Map<String, Object> param);

	/**
	 * @Title: relieveBinding
	 * @Description: 解除绑定
	 * @param param
	 * @throws:
	 * @time: 2018年8月27日 下午6:46:39
	 */
	@Update("UPDATE t_estate_info SET user_info_id = NULL,update_time = NOW(),update_by=#{userid}  WHERE id = #{estateId} ")
	void relieveBinding(Map<String, Object> param);

	/**
	 * @Title: updataLockedAdmin
	 * @Description: 驻场解冻或者冻结
	 * @param param
	 * @throws:
	 * @time: 2018年8月30日 下午3:26:07
	 */
	@Update("UPDATE t_user_userinfo SET locked = #{flag} , update_time = NOW() , update_by = #{userid} WHERE id = #{id} ")
	void updataLockedAdmin(Map<String, Object> param);

	/**
	 * @Title: findEstateListInStationed
	 * @Description: 获取楼盘的列表
	 * @return
	 * @throws:
	 * @time: 2018年8月30日 下午4:05:27
	 */
	@Select("SELECT id,estate_name estateName FROM t_estate_info WHERE entkbn = 0")
	List<EstateInfoModel> findEstateListInStationed();

	/**
	 * @Title: updataEstateUser   
	 * @Description:   
	 * @param userid         
	 * @throws
	 */
	@Update("UPDATE t_estate_info SET user_info_id = null,update_time = NOW() , update_by = #{userid}  WHERE user_info_id = #{id}")
	void updataEstateUser(String id);

}
