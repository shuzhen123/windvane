package dianfan.dao.mapper.login;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import dianfan.entities.admin.Admin;
import dianfan.entities.user.Role;

/**
 * @ClassName LoginMapper
 * @Description 
 * @author sz
 * @date 2018年8月23日 上午10:55:25
 */
@Repository
public interface LoginMapper {

	/**
	 * @Title: getCheckLogin
	 * @Description: 后台登录验证
	 * @param parma
	 * @return
	 * @throws:
	 * @time: 2018年8月23日 上午11:03:18
	 */
	//@Select("SELECT id,account,password,role_id role_Id,company_id companyId FROM t_admin WHERE account = #{account} AND password = #{password} AND entkbn = 0")
	Admin getCheckLogin(Map<String, Object> parma);

	/**
	 * @Title: findAdminListCount
	 * @Description: 获取管理员列表数量
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月23日 下午3:53:15
	 */
	int findAdminListCount(Map<String, Object> param);

	/**
	 * @Title: findAdminList
	 * @Description: 获取管理员列表
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月23日 下午4:03:41
	 */
	List<Admin> findAdminList(Map<String, Object> param);

	/**
	 * @Title: getCheckAccount
	 * @Description: 检测账号是否已经存在
	 * @param account 账号
	 * @return
	 * @throws:
	 * @time: 2018年8月23日 下午5:15:09
	 */
	@Select("SELECT COUNT(*) FROM t_admin WHERE entkbn = 0 AND account = #{account} ")
	int getCheckAccount(String account);

	/**
	 * @Title: addAdminPerson
	 * @Description: 添加管理员
	 * @param param
	 * @throws:
	 * @time: 2018年8月23日 下午5:18:54
	 */
	@Insert("INSERT INTO t_admin (id,account,password,role_id,create_time,create_by) "
			+ "VALUES (REPLACE(UUID(),'-',''),#{account},#{password},'2418eeaca80611e88dd352540054a904',now(),#{userid}) ")
	void addAdminPerson(Map<String, Object> param);

	/**
	 * @Title: getExcludeAccount
	 * @Description: 检测账号是否存在
	 * @param id 账号的ID
	 * @return
	 * @throws:
	 * @time: 2018年8月23日 下午5:42:43
	 */
	@Select("SELECT COUNT(*) FROM t_admin WHERE id = #{id} AND entkbn = 0")
	int getExcludeAccount(String id);

	/**
	 * @Title: editAdminPerson
	 * @Description: 修改管理员
	 * @param parma
	 * @throws:
	 * @time: 2018年8月23日 下午5:52:48
	 */
	@Select("UPDATE t_admin SET password = #{password}, update_time = NOW(),update_by = #{userid} WHERE id = #{id}")
	void editAdminPerson(Map<String, Object> parma);

	/**
	 * @Title: delAdminPerson
	 * @Description: 删除管理员
	 * @param param
	 * @throws:
	 * @time: 2018年8月23日 下午6:27:56
	 */
	void delAdminPerson(Map<String, Object> param);

	/**
	 * @Title: findRoleList
	 * @Description: 
	 * @return
	 * @throws:
	 * @time: 2018年8月24日 下午12:48:06
	 */
	@Select("SELECT id , role_id roleId, descption FROM m_role ")
	List<Role> findRoleList();

}
