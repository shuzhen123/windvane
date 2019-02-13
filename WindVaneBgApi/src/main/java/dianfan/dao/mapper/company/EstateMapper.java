package dianfan.dao.mapper.company;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import dianfan.entities.other.EstateInfo;

/**
 * @ClassName EstateMapper
 * @Description 楼盘相关
 * @author sz
 * @date 2018年8月27日 上午11:52:50
 */
@Repository
public interface EstateMapper {

	/**
	 * @Title: findEstateCount
	 * @Description: 获取楼盘数量
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午12:15:04
	 */
	int findEstateCount(Map<String, Object> param);

	/**
	 * @Title: findEstateList
	 * @Description: 获取楼盘列表
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午12:15:11
	 */
	List<EstateInfo> findEstateList(Map<String, Object> param);

	/**
	 * @Title: getCheckEstate
	 * @Description: 查看添加的楼盘是否存在
	 * @param estateName
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午1:10:20
	 */
	@Select("SELECT COUNT(*) FROM t_estate_info WHERE estate_name = #{estateName} AND entkbn = 0")
	int getCheckEstate(String estateName);

	/**
	 * @Title: addEstate
	 * @Description: 添加楼盘
	 * @param param
	 * @throws:
	 * @time: 2018年8月27日 下午1:15:35
	 */
	@Insert("INSERT INTO t_estate_info (id,estate_icon,estate_banner,estate_name,des,money,commission_rate,address,create_time,create_by) "
			+ "VALUES (#{id},#{estateIcon},#{estateBanner},#{estateName},#{des},#{money},#{commissionRate},#{address},now(),#{userid})")
	void addEstate(Map<String, Object> param);

	/**
	 * @Title: editEstate
	 * @Description: 修改楼盘
	 * @param param
	 * @throws:
	 * @time: 2018年8月27日 下午2:20:32
	 */
	@Update("UPDATE t_estate_info SET estate_name = #{estateName} ,estate_icon = #{estateIcon}, estate_banner=#{estateBanner},des=#{des},money=#{money},"
			+ "commission_rate=#{commissionRate},update_time=NOW(),update_by=#{userid},address=#{address} WHERE id = #{estateId}")
	void editEstate(Map<String, Object> param);

	/**
	 * @Title: delEstate
	 * @Description: 删除楼盘
	 * @param param
	 * @throws:
	 * @time: 2018年8月27日 下午2:33:13
	 */
	void delEstate(Map<String, Object> param);

	/**
	 * @Title: getEstateInfo
	 * @Description: 获取楼盘的详情
	 * @param id 楼盘的ID
	 * @return
	 * @throws:
	 * @time: 2018年8月27日 下午2:42:50
	 */
	@Select("SELECT tei.id id, tei.estate_icon estateIcon, tei.estate_banner estateBanner, tei.estate_name estateName,tei.des , tei.money, tei.commission_rate commissionRate,tei.user_info_id userInfoId,tei.address ,IFNULL(tuu.telno, '暂无案场') userInfoName "
			+ "FROM t_estate_info tei LEFT JOIN t_user_userinfo tuu ON tuu.id = tei.user_info_id WHERE tei.id = #{id} AND tei.entkbn = 0")
	EstateInfo getEstateInfo(String id);

	/**
	 * @Title: getCheckEstateCount
	 * @Description: 判断修改的楼盘名字是否重复
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年9月4日 下午2:22:43
	 */
	@Select("SELECT COUNT(*) FROM t_estate_info WHERE estate_name = #{estateName} AND entkbn = 0 AND id != #{estateId} ")
	int getCheckEstateCount(Map<String, Object> param);

}
