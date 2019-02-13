package dianfan.dao.mapper.company;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import dianfan.entities.user.UserInfos;

/**
 * @ClassName AccountMapper
 * @Description 账号相关管理
 * @author sz
 * @date 2018年8月29日 上午9:10:58
 */
@Repository
public interface AccountMapper {

	/**
	 * @Title: findAccountCount
	 * @Description: 获取账号数量
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月29日 上午9:19:22
	 */
	int findAccountCount(Map<String, Object> param);

	/**
	 * @Title: findAccountList
	 * @Description: 获取账号列表
	 * @param param
	 * @return
	 * @throws:
	 * @time: 2018年8月29日 上午9:23:42
	 */
	List<UserInfos> findAccountList(Map<String, Object> param);

	/**
	 * @Title: editAccountFlag
	 * @Description: 审批账号
	 * @param param
	 * @throws:
	 * @time: 2018年8月29日 上午11:25:36
	 */
	void editAccountFlag(Map<String, Object> param);
	
	

}
