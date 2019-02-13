package dianfan.constant;

/**
 * @ClassName ResultApiMsg
 * @Description 前端接口返回码
 * @author cjy
 * @date 2018年6月28日 下午6:52:37
 */
public class ResultApiMsg {
	public static final String C_200 = "OK";
	public static final String C_500 = "服务器忙";
	public static final String C_501 = "参数错误";
	public static final String C_404 = "服务器不存在该资源";

	public static final String C_900 = "文件资源不存在";

	public static final String C_001 = "登录信息已过期，请重新登录";
	public static final String C_002 = "手机号码格式不正确";
	
	
	public static final String C_1002 = "账号待审核";
	public static final String C_1003 = "账号审核不通过";
	
	public static final String C_1004 = "当前角色没有登陆权限";
	public static final String C_1005 = "备案信息请填写完整";
	public static final String C_1006 = "该手机号30天内不可重复报备";
	public static final String C_1007 = "该手机号今日不可重复报备";
	public static final String C_1107 = "该楼盘今日不可重复报备";
	
	public static final String C_1008 = "请将成交信息填写完整";
	public static final String C_1009 = "请将信息填写完整";
	
	public static final String C_1010 = "当前案场未分配楼盘";
	public static final String C_1011 = "账号已被冻结";
	
	public static final String C_4001 = "用户不存在";
	public static final String C_4002 = "code为空";
	public static final String C_4004 = "原密码不正确";
	public static final String C_4005 = "手机号码格式不对或密码为空";
	public static final String C_4006 = "手机号码或密码错误";
	public static final String C_4007 = "手机号码已经注册，请直接登录";
	public static final String C_4008 = "手机号码格式不对";
	public static final String C_4009 = "用户已存在，请直接登录";
	public static final String C_4010 = "短信发送失败";
	public static final String C_4011 = "短信验证码不正确";
	public static final String C_4012 = "：密码长度应该为6~15位";
	
	/* 一下返回码仅供京东物流使用 */
//	public static final String C_7000 = "寄件人手机和座机必须填一个";
//	public static final String C_7001 = "收货人手机和座机必须填一个";
//	public static final String C_7002 = "易碎标志填写错误，请填写1（是）或2（否）";
	
}
