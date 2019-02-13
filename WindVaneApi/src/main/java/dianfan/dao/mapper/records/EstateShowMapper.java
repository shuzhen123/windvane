package dianfan.dao.mapper.records;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import dianfan.entities.other.EstateInfo;

/**
 * @ClassName EstateShowMapper
 * @Description 楼盘相关dao
 * @author sz
 * @date 2018年8月31日 上午10:55:56
 */
@Repository
public interface EstateShowMapper {

	/**
	 * @Title: findEstateShowCount
	 * @Description: 楼盘总条数
	 * @return 
	 * @throws:
	 * @time: 2018年8月31日 上午11:09:19
	 */
	@Select("SELECT COUNT(*) FROM t_estate_info WHERE entkbn = 0")
	int findEstateShowCount();

	/**
	 * @Title: findEstateShowList
	 * @Description: 楼盘列表 
	 * @param param 
	 * @return
	 * @throws:
	 * @time: 2018年8月31日 上午11:09:22
	 */
	@Select("SELECT id,estate_banner estateBanner, estate_icon estateIcon,estate_name estateName, des,money,address "
			+ "FROM t_estate_info WHERE entkbn = 0 ORDER BY create_time DESC LIMIT #{start},#{pageSize}")
	List<EstateInfo> findEstateShowList(Map<String, Object> param);

	/**
	 * @Title: getEstateShowInfo
	 * @Description: 获取楼盘详情
	 * @param id
	 * @return
	 * @throws:
	 * @time: 2018年8月31日 上午11:39:05
	 */
	@Select("SELECT id,estate_icon estateIcon,estate_banner estateBanner,estate_name estateName, des,money, address FROM t_estate_info WHERE entkbn = 0 AND id = #{id} ")
	EstateInfo getEstateShowInfo(String id);
	
	

}
