package dianfan.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "返回实体bean")
public class ResultBean {

	@ApiModelProperty(value = "状态码,200表示成功 其他表示失败", example = "200")
	private String code;// 返回码
	@ApiModelProperty(value = "返回信息", example = "操作成功")
	private String msg;// 返回信息
	@ApiModelProperty(value = "返回的实体数据")
	private Object data;// 返回的实体数据

	public ResultBean() {
		this.code = "200";
		this.msg = "OK";
	}

	public ResultBean(Object data) {
		this.code = "200";
		this.msg = "OK";
		this.data = data;
	}

	public ResultBean(String code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public ResultBean(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultBean[code=" + code + ",msg=" + msg + ",data=" + data + "]";
	}

}
