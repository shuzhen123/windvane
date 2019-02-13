package dianfan.dao.mapper.company;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import dianfan.entities.other.Company;

/**
 * @ClassName CompanyMapper
 * @Description 
 * @author sz
 * @date 2018年8月23日 下午6:57:14
 */
@Repository
public interface CompanyMapper {

	/**
	 * @Title: findCompanyCount
	 * @Description: 获取公司列表数量
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月24日 下午2:06:29
	 */
	int findCompanyCount(Map<String, Object> param);

	/**
	 * @Title: findCompanyList
	 * @Description: 获取公司列表
	 * @return
	 * @throws:
	 * @time: 2018年8月24日 下午2:45:41
	 */
	List<Company> findCompanyList(Map<String, Object> param);

	
	/**
	 * @Title: getCheckCompanyName
	 * @Description: 验证注册名字是否重复
	 * @param companyName 公司名
	 * @return
	 * @throws:
	 * @time: 2018年8月24日 下午4:31:29
	 */
	@Select("SELECT COUNT(*) FROM t_company WHERE company_name = #{companyName} AND entkbn = 0")
	int getCheckCompanyName(String companyName);

	/**
	 * @Title: getCheckAccount
	 * @Description: 检测注册的账号是否存在
	 * @param account 账号名
	 * @return
	 * @throws:
	 * @time: 2018年8月24日 下午4:39:48
	 */
	@Select("SELECT COUNT(*) FROM t_admin WHERE account = #{account} AND entkbn = 0")
	int getCheckAccount(String account);

	/**
	 * @Title: addCompanyInfo
	 * @Description: 添加公司
	 * @param param
	 * @throws:
	 * @time: 2018年8月24日 下午4:44:16
	 */
	@Insert("INSERT INTO t_company (id,company_name,header_name,create_by,create_time) "
			+ "VALUES (#{id},#{companyName},#{headerName},#{userid},NOW())")
	void addCompanyInfo(Map<String, Object> param);

	/**
	 * @Title: addAdminInfo
	 * @Description: 账号的添加的
	 * @param param
	 * @throws:
	 * @time: 2018年8月24日 下午4:47:00
	 */
	@Insert("INSERT INTO t_admin (id,account,password,role_id,company_id,create_time,create_by) "
			+ "VALUES (REPLACE(uuid(),'-',''),#{account},#{password},'2377f36ca80611e88dd352540054a904',#{id},NOW(),#{userid})")
	void addAdminInfo(Map<String, Object> param);

	/**
	 * @Title: editCompanyInfo
	 * @Description: 修改公司信息
	 * @param param
	 * @throws:
	 * @time: 2018年8月25日 上午10:46:26
	 */
	@Update("UPDATE t_company SET header_name = #{headerName},company_name = #{companyName} ,update_time = NOW() , update_by = #{userid} WHERE id = #{id} ")
	void editCompanyInfo(Map<String, Object> param);

	/**
	 * @Title: editCompanyAccountInfo
	 * @Description: 修改公司账号数据
	 * @param param
	 * @throws:
	 * @time: 2018年8月25日 上午10:53:11
	 */
	void editCompanyAccountInfo(Map<String, Object> param);

	/**
	 * @Title: delCompanyInfo
	 * @Description: 删除公司
	 * @param param
	 * @throws:
	 * @time: 2018年8月25日 上午11:50:48
	 */
	void delCompanyInfo(Map<String, Object> param);

	/**
	 * @Title: delCompanyAccountInfo
	 * @Description: 删除公司账号信息
	 * @param param
	 * @throws:
	 * @time: 2018年8月25日 上午11:55:18
	 */
	void delCompanyAccountInfo(Map<String, Object> param);

	/**
	 * @Title: getCompanyInfo
	 * @Description: 获取公司信息
	 * @param parma
	 * @return
	 * @throws:
	 * @time: 2018年8月25日 下午12:20:10
	 */
	@Select("SELECT tc.id, tc.company_name companyName, tc.header_name headerName, tc.create_time createTime, ta.account , mr.role_id roleName "
			+ "FROM t_company tc, t_admin ta, m_role mr WHERE tc.id = #{id} AND tc.id = ta.company_id AND ta.role_id = mr.id")
	Company getCompanyInfo(Map<String, Object> parma);

	/**
	 * @Title: getCheckCompanyname
	 * @Description: 判断要修改的名字是否在数据库中已经存在 
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年9月4日 下午2:01:17
	 */
	@Select("SELECT COUNT(*) from t_company WHERE company_name = #{companyName} AND id != #{id}  AND entkbn = 0 ")
	int getCheckCompanyname(Map<String, Object> param);

	/**
	 * @Title: getCheckAccountCount
	 * @Description: 判断要修改的账号是否在数据库中已经存在 
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年9月4日 下午2:07:55
	 */
	@Select("SELECT COUNT(*) FROM t_admin WHERE account = #{account} AND entkbn = 0 AND company_id != #{id} ")
	int getCheckAccountCount(Map<String, Object> param);
	

}
