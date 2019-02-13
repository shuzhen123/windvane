package dianfan.service.logins;

import java.io.UnsupportedEncodingException;

import com.aliyuncs.exceptions.ClientException;
import com.fasterxml.jackson.core.JsonProcessingException;

import dianfan.models.ResultBean;

/**
 * @ClassName LoginService
 * @Description app登陆相关
 * @author sz
 * @date 2018年8月17日 下午3:58:19
 */
public interface LoginService {

	/**
	 * @Title: registerSMS
	 * @Description: 发送手机注册验证码
	 * @param telno telno 手机号码
	 * @return
	 * @throws ClientException 
	 * @throws JsonProcessingException 
	 * @throws UnsupportedEncodingException 
	 * @throws Exception 
	 * @throws:
	 * @time: 2018年8月20日 上午10:03:57
	 */
	ResultBean registerSMS(String telno) throws UnsupportedEncodingException, JsonProcessingException, ClientException, Exception;

	/**
	 * @Title: addRegister
	 * @Description: app注册
	 * @param telno 手机号
	 * @param smscode 验证码
	 * @param md5pwd 密码
	 * @param sort 类型
	 * @param company 公司
	 * @param principal 负责人
	 * @return
	 * @throws:
	 * @time: 2018年8月20日 上午11:14:45
	 */
	ResultBean addRegister(String telno, String smscode, String md5pwd, String sort, String company, String principal,String name);

	/**
	 * @Title: getResetSMS
	 * @Description: 获取重置验证码 
	 * @param telno
	 * @return
	 * @throws ClientException 
	 * @throws JsonProcessingException 
	 * @throws UnsupportedEncodingException 
	 * @throws Exception 
	 * @throws:
	 * @time: 2018年8月20日 下午12:09:39
	 */
	ResultBean getResetSMS(String telno) throws UnsupportedEncodingException, JsonProcessingException, ClientException, Exception;

	/**
	 * @Title: updatePasswordBysms
	 * @Description: 重置密码 
	 * @param telno 手机号码
	 * @param smscode 验证码
	 * @param md5pwd 新密码
	 * @return 
	 * @throws:
	 * @time: 2018年8月20日 下午12:17:28
	 */
	ResultBean updatePasswordBysms(String telno, String smscode, String md5pwd);

	/**
	 * @Title: appLogin
	 * @Description: app登陆
	 * @param telno 手机号
	 * @param password 密码
	 * @return
	 * @throws:
	 * @time: 2018年8月20日 下午1:51:16
	 */
	ResultBean appLogin(String telno, String password);

}
