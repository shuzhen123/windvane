package dianfan.dao.mapper.other;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import dianfan.entities.other.AppRole;
import dianfan.entities.other.Company;
import dianfan.entities.other.EstateInfo;

/**
 * @ClassName OtherMapper
 * @Description app辅助接口 dao 
 * @author sz
 * @date 2018年8月22日 下午5:04:21
 */
@Repository
public interface OtherMapper {

	/**
	 * @Title: findRoleList
	 * @Description: 获取角色列表
	 * @return
	 * @throws:
	 * @time: 2018年8月22日 下午5:15:30
	 */
	@Select("SELECT id, role_id roleId,descption FROM m_role WHERE role_id = '02' or role_id = '03' or role_id = '07' ")
	List<AppRole> findRoleList();

	/**
	 * @Title: findEstateList
	 * @Description: 获取楼盘列表
	 * @return EstateInfo
	 * @throws:
	 * @time: 2018年8月22日 下午5:29:41
	 */
	@Select("SELECT id, estate_icon estateIcon, estate_banner estateBanner, estate_name estateName, des, money, commission_rate commissionRate, user_info_id userInfoId, address FROM t_estate_info WHERE entkbn = 0 ")
	List<EstateInfo> findEstateList();

	/**
	 * @Title: findCompanyList
	 * @Description: 获取公司列表
	 * @return EstateInfo
	 * @throws:
	 * @time: 2018年8月22日 下午5:48:25
	 */
	@Select("SELECT id, company_name companyName, header_name headerName FROM t_company WHERE entkbn = 0")
	List<Company> findCompanyList();

	
}
