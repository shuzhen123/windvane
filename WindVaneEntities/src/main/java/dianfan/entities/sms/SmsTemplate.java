package dianfan.entities.sms;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName SmsTemplate
 * @Description ����ģ������
 * @author cjy
 * @date 2018��6��28�� ����6:42:36
 */
public class SmsTemplate implements Serializable{
	/** @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1608080169651724709L;
	/*
	 * ���Ž��պ���,֧���Զ��ŷָ�����ʽ�����������ã���������Ϊ1000���ֻ�����,
	 * ������������ڵ������ü�ʱ�������ӳ�,��֤�����͵Ķ����Ƽ�ʹ�õ������õķ�ʽ��
	 * ���͹���/�۰�̨��Ϣʱ�����պ����ʽΪ00+��������+���룬�硰0085200000000��
	 */
	private String phoneNumbers;
	private String templateCode; //����ģ��ID
	private Map<String, String> templateParam; //����ģ������滻JSON��,������ʾ:���JSON����Ҫ�����з�,����ձ�׼��JSONЭ�顣
	private String smsUpExtendCode; //���ж�����չ��,��������Ҫ���ֶε��û�����Դ��ֶ�
	private String outId; //�ⲿ��ˮ��չ�ֶ�
	public String getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public Map<String, String> getTemplateParam() {
		return templateParam;
	}
	public void setTemplateParam(Map<String, String> templateParam) {
		this.templateParam = templateParam;
	}
	public String getSmsUpExtendCode() {
		return smsUpExtendCode;
	}
	public void setSmsUpExtendCode(String smsUpExtendCode) {
		this.smsUpExtendCode = smsUpExtendCode;
	}
	public String getOutId() {
		return outId;
	}
	public void setOutId(String outId) {
		this.outId = outId;
	}
	@Override
	public String toString() {
		return "SmsTemplate [phoneNumbers=" + phoneNumbers + ", templateCode=" + templateCode + ", templateParam="
				+ templateParam + ", smsUpExtendCode=" + smsUpExtendCode + ", outId=" + outId + "]";
	}
}