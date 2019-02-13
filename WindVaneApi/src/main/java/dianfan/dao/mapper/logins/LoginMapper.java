package dianfan.dao.mapper.logins;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import dianfan.entities.other.DataModel;
import dianfan.entities.user.UserInfos;

/**
 * @ClassName LoginMapper
 * @Description 登陆相关接口
 * @author sz
 * @date 2018年8月17日 下午4:02:29
 */
@Repository
public interface LoginMapper {

	/**
	 * @Title: getCheckPhone
	 * @Description: 查看手机号码是否注册
	 * @param telno
	 * @return
	 * @throws:
	 * @time: 2018年8月20日 上午10:21:40
	 */
	@Select( "SELECT telno , apply_flag applyFlag FROM t_user_userinfo WHERE telno = #{telno} AND apply_flag != '03' AND entkbn = 0" )
	DataModel getCheckPhones(String telno);
	@Select( "SELECT count(*) FROM t_user_userinfo WHERE telno = #{telno} AND entkbn = 0 AND apply_flag = '02' " )
	int getCheckPhone(String telno);

	/**
	 * @Title: addRegister
	 * @Description: 注册
	 * @param param 注册参数
	 * @throws:
	 * @time: 2018年8月20日 上午11:22:41
	 */
	@Insert( "INSERT INTO t_user_userinfo ( id, telno, pwd, apply_flag,	create_time, role_id, company_id, create_by, header_name,name) VALUES (#{id}, #{telno}, #{md5pwd}, '01', NOW(), #{sort}, #{company}, #{id}, #{principal},#{name} )" )
	void addRegister(Map<String, Object> param);

	/**
	 * @Title: updateNewPassword
	 * @Description: 重置密码
	 * @param param
	 * @throws:
	 * @time: 2018年8月20日 下午12:20:04
	 */
	@Update( "UPDATE t_user_userinfo SET pwd = #{newPassword} , update_time = NOW() WHERE telno = #{telno} AND apply_flag = '02' " )
	void updateNewPassword(Map<String, Object> param);

	/**
	 * @Title: getLoginInfo
	 * @Description: 验证登陆信息
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月20日 下午1:54:20
	 */
	@Select( "SELECT id , telno,locked , apply_flag applyFlag, role_id roleId, company_id companyId, header_name headerName FROM t_user_userinfo WHERE telno = #{telno} AND apply_flag = '02' AND pwd = #{password} AND entkbn != 9 " )
	UserInfos getLoginInfo(Map<String, Object> param);

	/**
	 * @Title: findLoginRole
	 * @Description: 获取可登陆的角色ID
	 * @return 
	 * @throws:
	 * @time: 2018年8月20日 下午2:44:02
	 */
	@Select("SELECT id FROM m_role WHERE role_id = '01' OR  role_id = '02' OR role_id = '03' OR role_id = '04' OR role_id = '07' ")
	List<String> findLoginRole();

	/**
	 * @Title: getRoleNameById
	 * @Description: 通过ID 获取角色
	 * @param roleid
	 * @return
	 * @throws:
	 * @time: 2018年8月25日 上午9:54:09
	 */
	@Select("SELECT role_id FROM m_role WHERE id = #{roleid}")
	String getRoleNameById(String roleid);
	
	
	
	
	

}
