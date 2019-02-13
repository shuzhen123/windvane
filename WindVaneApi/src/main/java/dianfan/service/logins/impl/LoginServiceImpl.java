package dianfan.service.logins.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import dianfan.constant.ResultApiMsg;
import dianfan.dao.mapper.logins.LoginMapper;
import dianfan.entities.other.DataModel;
import dianfan.entities.user.UserInfos;
import dianfan.models.ResultBean;
import dianfan.service.common.impl.RedisService;
import dianfan.service.common.impl.RedisTokenService;
import dianfan.service.logins.LoginService;
import dianfan.service.sms.SmsService;
import dianfan.tencent.sms.SmsSingleSender;
import dianfan.tencent.sms.SmsSingleSenderResult;
import dianfan.util.GenRandomNumUtil;
import dianfan.util.PropertyUtil;
import dianfan.util.UUIDUtil;

/**
 * @ClassName LoginService
 * @Description app登陆相关
 * @author sz
 * @date 2018年8月17日 下午3:58:19
 */
@Service
public class LoginServiceImpl implements LoginService {

	/**
	 * 注入: #RedisTokenService
	 */
	@Autowired
	private RedisTokenService redisTokenService;
	
	/**
	 * 注入：#RedisService
	 */
	@Autowired
	private RedisService<Object,Object> redisService;
	
	/**
	 * 注入： #LoginMapper
	 */
	@Autowired
	private LoginMapper loginMapper;
	
	/**
	 * 注入：#SmsService
	 */
	@Autowired
	private SmsService smsService;
	
	

	/*
	 * (non-Javadoc)
	 * <p>Title: registerSMS</p>
	 * <p>Description: 发送手机注册验证码</p>
	 * @param telno
	 * @return
	 * link: @see dianfan.service.logins.LoginService#registerSMS(java.lang.String)
	 */
	@Override
	public ResultBean registerSMS(String telno) throws Exception {
		// 查看手机号是否注册
		DataModel count = loginMapper.getCheckPhones(telno);
		if (!StringUtils.isEmpty(count) ) {
			if ("01".equals(count.getApplyFlag())) {
				// 账号待审核
				return new ResultBean("1002" , ResultApiMsg.C_1002);
			} else if ("02".equals(count.getApplyFlag())) {
				// !用户已存在，请直接登录
				return new ResultBean("4009" , ResultApiMsg.C_4009);
			}
		}
		
		
		// 查看是否有验证码缓存
		String last_sms = (String) redisService.get(telno+PropertyUtil.getProperty("tx_tplCode_2"));
		
		// 验证码有效时间(秒)
		int invalidSecs = Integer.parseInt(PropertyUtil.getProperty("alisms_alidity")) * 60;
		
		// 随机验证码
		String codeRadom = null;
		int sendInterval = 300;
		if (last_sms != null) {
			// 查看剩余时间(秒)
			Long time = redisService.getExpire(telno+PropertyUtil.getProperty("tx_tplCode_2"));
			// 验证码发送间隔(秒)
			sendInterval = Integer.parseInt(PropertyUtil.getProperty("smssendinterval")) * 60;
			//时间判断
			if(invalidSecs - time < sendInterval) {
				//发送频率为间隔时间内，无须重发
				return new ResultBean();
			}
			// 上次的验证码.
			codeRadom = last_sms;
		} else {
			// 无记录，创建新的随机验证码
			codeRadom = GenRandomNumUtil.getRandomNum(6);
		}
		// 将获取到的验证码存入redis 
		redisService.set(telno+PropertyUtil.getProperty("tx_tplCode_2"), codeRadom, sendInterval);
		
		/*发送短信*/
		// 获取 appid
		int appid = Integer.valueOf(PropertyUtil.getProperty("smsappid")).intValue();
		SmsSingleSender singleSender = new SmsSingleSender(appid,PropertyUtil.getProperty("smsappkey"));
		// 创建一个短信的模板
		ArrayList<String> params = new ArrayList<>();
		// 添加随机数
		params.add(codeRadom);
		params.add(PropertyUtil.getProperty("alisms_alidity"));
		int tmplId = Integer.valueOf(PropertyUtil.getProperty("tx_tplCode_2"));
		// 发送验证码
		SmsSingleSenderResult singleSenderResult = singleSender.sendWithParam("86", telno, tmplId, params, "", "", "");
		// 判断短信是否发送成功
		String sms = singleSenderResult.toString();
		if (sms.contains("OK")) {
			// 短信发送成功
			return new ResultBean();
		} else {
			// ！短信发送失败
			return new ResultBean("4010",ResultApiMsg.C_4010);
		}
		
		
	}


	/*
	 * (non-Javadoc)
	 * <p>Title: addRegister</p>
	 * <p>Description: 注册</p>
	 * @param telno 手机号码
	 * @param smscode 短信验证码
	 * @param md5pwd 密码
	 * @param sort 分类
	 * @param company 公司
	 * @param principal 负责人
	 * @return
	 * link: @see dianfan.service.logins.LoginService#addRegister(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public ResultBean addRegister(String telno, String smscode, String md5pwd, String sort, String company,
			String principal,String name) {
		// 验证验证码是否正确
		String rediscode = redisService.get(telno+PropertyUtil.getProperty("tx_tplCode_2"));
		if (!smscode.equals(rediscode)) {
			// 错误：短信验证码不正确
			return new ResultBean("4011", ResultApiMsg.C_4011);
		}
		
		// 查看手机号是否注册
		DataModel count = loginMapper.getCheckPhones(telno);
		if (!StringUtils.isEmpty(count) ) {
			if ("01".equals(count.getApplyFlag())) {
				// 账号待审核
				return new ResultBean("1002" , ResultApiMsg.C_1002);
			} else if ("02".equals(count.getApplyFlag())) {
				// !用户已存在，请直接登录
				return new ResultBean("4009" , ResultApiMsg.C_4009);
			}
		}
		
		
		// 创建入参容器
		Map<String, Object> param = new HashMap<>();
		// 添加入参
		param.put("telno", telno);
		param.put("md5pwd", md5pwd);
		param.put("sort", sort);
		param.put("name", name);
		param.put("company", company);
		param.put("principal", principal);
		// 创建一个新的UUID
		param.put("id", UUIDUtil.getUUID());
		// 添加初测信息
		loginMapper.addRegister(param);
		
		// 成功
		return new ResultBean();
	}


	/*
	 * (non-Javadoc)
	 * <p>Title: getResetSMS</p>
	 * <p>Description: 重置密码</p>
	 * @param telno 手机号码
	 * @return 
	 * link: @see dianfan.service.logins.LoginService#getResetSMS(java.lang.String)
	 */
	@Override
	public ResultBean getResetSMS(String telno) throws Exception {
		
		// 查看手机号是否注册
		DataModel count = loginMapper.getCheckPhones(telno);
		if (!StringUtils.isEmpty(count) ) {
			if ("01".equals(count.getApplyFlag())) {
				// 账号待审核
				return new ResultBean("1002" , ResultApiMsg.C_1002);
			} 
		} else {
			// !用户不存在
			return new ResultBean("4001",ResultApiMsg.C_4001);
		}
		
		
		// 查看是否有验证码缓存
		String last_sms = (String) redisService.get(telno+PropertyUtil.getProperty("tx_tplCode_1"));
		
		// 验证码有效时间(秒)
		int invalidSecs = Integer.parseInt(PropertyUtil.getProperty("alisms_alidity")) * 60;
		
		// 随机验证码
		String codeRadom = null;
		int sendInterval = 300;
		if (last_sms != null) {
			// 查看剩余时间(秒)
			Long time = redisService.getExpire(telno+PropertyUtil.getProperty("tx_tplCode_1"));
			// 验证码发送间隔(秒)
			sendInterval = Integer.parseInt(PropertyUtil.getProperty("smssendinterval")) * 60;
			//时间判断
			if(invalidSecs - time < sendInterval) {
				//发送频率为间隔时间内，无须重发
				return new ResultBean();
			}
			// 上次的验证码
			codeRadom = last_sms;
		} else {
			// 无记录，创建新的随机验证码
			codeRadom = GenRandomNumUtil.getRandomNum(6);
		}
		// 将获取到的验证码存入redis 
		redisService.set(telno+PropertyUtil.getProperty("tx_tplCode_1"), codeRadom, sendInterval);
		
		/*发送短信*/
		int appid = Integer.valueOf(PropertyUtil.getProperty("smsappid")).intValue();
		SmsSingleSender singleSender = new SmsSingleSender(appid,PropertyUtil.getProperty("smsappkey"));
		
		ArrayList<String> params = new ArrayList<>();
		params.add(codeRadom);
		params.add(PropertyUtil.getProperty("alisms_alidity"));
		int tmplId = Integer.valueOf(PropertyUtil.getProperty("tx_tplCode_1"));
		SmsSingleSenderResult singleSenderResult = singleSender.sendWithParam("86", telno, tmplId, params, "", "", "");
		String sms = singleSenderResult.toString();
		System.err.println(sms);
		if (sms.contains("OK")) {
			// 短信发送成功
			return new ResultBean();
		} else {
			// ！短信发送失败
			return new ResultBean("4010",ResultApiMsg.C_4010);
		}
		
	}


	/*
	 * (non-Javadoc)
	 * <p>Title: updatePasswordBysms</p>
	 * <p>Description: 重置密码</p>
	 * @param telno
	 * @param smscode
	 * @param md5pwd
	 * @return
	 * link: @see dianfan.service.logins.LoginService#updatePasswordBysms(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public ResultBean updatePasswordBysms(String telno, String smscode, String md5pwd) {
		// 1.验证 输入的短信验证码是否正确
		String rediscode = redisService.get(telno+PropertyUtil.getProperty("tx_tplCode_1"));
		//String rediscode = "123";
		if (!smscode.equals(rediscode)) {
			// ！短信验证码不正确
			return new ResultBean("4011", ResultApiMsg.C_4011);
		}
		// 2.短信发送成功，创建入参模型
		Map<String, Object> param = new HashMap<>();
		// 添加用户的新密码
		param.put("newPassword", md5pwd);
		// 添加用户的手机号码
		param.put("telno", telno);
		// 3.修改密码操作
		loginMapper.updateNewPassword(param);
		// 4.成功返回
		return new ResultBean();
	}


	/*
	 * (non-Javadoc)
	 * <p>Title: appLogin</p>
	 * <p>Description: app登陆</p>
	 * @param telno 手机号
	 * @param password 密码
	 * @return
	 * link: @see dianfan.service.logins.LoginService#appLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public ResultBean appLogin(String telno, String password) {
		// 入参模型
		Map<String, Object> param = new HashMap<>();
		param.put("telno", telno);
		param.put("password", password);
		// 检查账号正确性
		UserInfos infos = loginMapper.getLoginInfo(param);
		if (StringUtils.isEmpty(infos)) {
			// 错误：手机号或者密码不正确
			return new ResultBean("4006",ResultApiMsg.C_4006);
		}
		if ("01".equals(infos.getApplyFlag())) {
			// 账号待审核
			return new ResultBean("1002",ResultApiMsg.C_1002);
		}
		if ("03".equals(infos.getApplyFlag())) {
			// 账号审核不通过
			return new ResultBean("1003",ResultApiMsg.C_1003);
		}
		if (0 != infos.getLocked()) {
			// 账号已被冻结
			return new ResultBean("1011",ResultApiMsg.C_1011);
		}
		//获取可登陆ID
		List<String> role = loginMapper.findLoginRole();
		if (!role.contains(infos.getRoleId())) {
			// 错误：当前角色没有登陆权限
			return new ResultBean("1004",ResultApiMsg.C_1004);
		}
		//登录成功
		String accesstoken = redisTokenService.createToken(infos.getId());
		
		// 获取用户的角色ID
		String roleid = infos.getRoleId();
		// 整理返回参数模型
		Map<String, Object> data = new HashMap<>();
		data.put("accesstoken", accesstoken);
		data.put("roleid", roleid);
		// 获取角色 (01,02,03,04)
		String roleName = loginMapper.getRoleNameById(roleid);
		data.put("roleName", roleName);
		// 成功
		return new ResultBean(data);
	}
	
	
}
