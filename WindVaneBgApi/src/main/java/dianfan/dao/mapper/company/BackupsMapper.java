package dianfan.dao.mapper.company;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import dianfan.entities.other.ModleList;
import dianfan.entities.user.Records;
import dianfan.entities.user.RecordsInfo;

/**
 * @ClassName BackupsMapper
 * @Description 
 * @author sz
 * @date 2018年8月25日 下午2:42:26
 */
@Repository
public interface BackupsMapper {

	/**
	 * @Title: findBackupsCount
	 * @Description: 获取报备列表数量
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月25日 下午3:39:03
	 */
	int findBackupsCount(Map<String, Object> param);

	/**
	 * @Title: findBackupsList
	 * @Description: 获取报备列表
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月25日 下午3:39:22
	 */
	List<Records> findBackupsList(Map<String, Object> param);

	/**
	 * @Title: updataBackupsApprove
	 * @Description: 修改审批状态
	 * @param param 
	 * @throws:
	 * @time: 2018年8月25日 下午5:48:26
	 */
	@Update("UPDATE t_preparation SET apply_flag = #{flag} , update_time = NOW(), update_by = #{userid} WHERE id = #{id}")
	void updataBackupsApprove(Map<String, Object> param);

	/**
	 * @Title: getCheckEstateUserInfo
	 * @Description: 查看楼盘下有没有对应暗厂
	 * @param id
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 上午9:53:59
	 */
	@Select("SELECT tei.user_info_id FROM t_estate_info tei, t_preparation tp WHERE tei.id = tp.estate_id AND tp.id = #{id} AND tp.entkbn = 0 ")
	String getCheckEstateUserInfo(String id);

	/**
	 * @Title: getBackupsInfo
	 * @Description: 获取报备的详情
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 上午10:37:03
	 */
	RecordsInfo getBackupsInfo(Map<String, Object> param);

	/**
	 * @Title: findStatisticsList
	 * @Description: 获取统计数据 按楼盘 报备
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月31日 下午4:47:27
	 */
	List<ModleList> findStatisticsList(Map<String, Object> param);

	/**
	 * @Title: findStatisticsList
	 * @Description: 获取统计数据 按楼盘 成交
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月31日 下午4:47:27
	 */
	List<ModleList> findStatisticsListCj(Map<String, Object> param);
	/**
	 * @Title: findStatisticsList
	 * @Description: 获取统计数据 按公司 报备
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月31日 下午4:47:27
	 */
	List<ModleList> findStatisticsListGsBb(Map<String, Object> param);
	/**
	 * @Title: findStatisticsList
	 * @Description: 获取统计数据 按公司 成交
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月31日 下午4:47:27
	 */
	List<ModleList> findStatisticsListGsCj(Map<String, Object> param);

	/**
	 * @Title: getBackupsListDataCount
	 * @Description: 统计页面使用的 报备列表总数量
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年9月3日 下午2:10:53
	 */
	int getBackupsListDataCount(Map<String, Object> param);
	/**
	 * @Title: getBackupsListDataCount
	 * @Description: 统计页面使用的 报备列表
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年9月3日 下午2:10:53
	 */
	List<Records> getBackupsListData(Map<String, Object> param);

	/**
	 * @Title: getBackupsListDataCountCj
	 * @Description: 统计页面使用的 成交列表总数量
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年9月3日 下午3:07:09
	 */
	int getBackupsListDataCountCj(Map<String, Object> param);
	/**
	 * @Title: getBackupsListDataCountCj
	 * @Description: 统计页面使用的 成交列表
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年9月3日 下午3:07:09
	 */
	List<Records> getBackupsListDataCj(Map<String, Object> param);
	/**
	 * @Title: getBackupsListDataCountCj
	 * @Description: 统计页面使用的公司 报备列表
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年9月3日 下午3:07:09
	 */
	int getBackupsListDataCountGs(Map<String, Object> param);
	/**
	 * @Title: getBackupsListDataCountCj
	 * @Description: 统计页面使用的公司 成交列表
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年9月3日 下午3:07:09
	 */
	List<Records> getBackupsListDataGs(Map<String, Object> param);

	int getBackupsListDataCountGsCj(Map<String, Object> param);

	List<Records> getBackupsListDataGsCj(Map<String, Object> param);

	
}
