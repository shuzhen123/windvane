package dianfan.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

/**
 * 
 * @Title: AliOSSUtil.java
 * @Package dianfan.util
 * @Description: 阿里云OSS存储工具类
 * @author Administrator
 * @date 2018年4月16日 下午12:51:06
 * @version V1.0
 */
public class AliOSSUtil {

	/**
	 * 阿里云API的内或外网域名
	 */
	private final static String ALI_OSS_ENDPOINT = PropertyUtil.getProperty("ali_oss_endpoint", "");
	/**
	 * 阿里云API的密钥Access Key ID
	 */
	private final static String ALI_OSS_ACCESS_KEY_ID = PropertyUtil.getProperty("ali_oss_access_key_id", "");
	/**
	 * 阿里云API的密钥Access Key Secret
	 */
	private final static String ALI_OSS_ACCESS_KEY_SECRET = PropertyUtil.getProperty("ali_oss_access_key_secret", "");

	/**
	 * 获取阿里云OSS客户端对象
	 * 
	 * @return oss对象
	 */
	public static final OSSClient getOSSClient() {
		return new OSSClient(ALI_OSS_ENDPOINT, ALI_OSS_ACCESS_KEY_ID, ALI_OSS_ACCESS_KEY_SECRET);
	}

	/**
	 * 新建Bucket --Bucket权限:私有
	 * 
	 * @param bucketName
	 *            bucket名称
	 * @return true 新建Bucket成功
	 */
	public static final boolean createBucket(String bucketName) {
		Bucket bucket = getOSSClient().createBucket(bucketName);
		return bucketName.equals(bucket.getName());
	}

	/**
	 * 删除Bucket
	 * 
	 * @param bucketName
	 *            bucket名称
	 */
	public static final void deleteBucket(String bucketName) {
		getOSSClient().deleteBucket(bucketName);
	}

	/**
	 * 向阿里云的OSS存储中存储文件 --file也可以用InputStream替代
	 * 
	 * @param file
	 *            上传文件
	 * @param bucketName
	 *            bucket名称
	 * @param diskName
	 *            上传文件的目录 --bucket下文件的路径
	 * @return String 唯一MD5数字签名
	 */
	public static final String uploadObject2OSS(File file, String bucketName, String diskName) {
		String resultStr = null;
		try {
			InputStream is = new FileInputStream(file);
			String fileName = file.getName();
			Long fileSize = file.length();
			// 创建上传Object的Metadata
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(is.available());
			metadata.setCacheControl("no-cache");
			metadata.setHeader("Pragma", "no-cache");
			metadata.setContentEncoding("utf-8");
			metadata.setContentType(getContentType(fileName));
			metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
			// 上传文件
			PutObjectResult putResult = getOSSClient().putObject(bucketName, diskName + fileName, is, metadata);
			// 解析结果
			resultStr = putResult.getETag();
		} catch (Exception e) {
		}
		return resultStr;
	}

	/**
	 * 根据key获取OSS服务器上的文件输入流
	 * 
	 * @param bucketName
	 *            bucket名称
	 * @param diskName
	 *            文件路径
	 * @param key
	 *            Bucket下的文件的路径名+文件名
	 * @return 获取链接对象
	 */
	public static final InputStream getOSS2InputStream(String bucketName, String diskName, String key) {
		OSSObject ossObj = getOSSClient().getObject(bucketName, diskName + key);
		return ossObj.getObjectContent();
	}

	/**
	 * 根据key删除OSS服务器上的文件
	 * 
	 * @param bucketName
	 *            bucket名称
	 * @param diskName
	 *            文件路径
	 * @param key
	 *            Bucket下的文件的路径名+文件名
	 */
	public static void deleteFile(String bucketName, String diskName, String key) {
		getOSSClient().deleteObject(bucketName, diskName + key);
	}

	/**
	 * @Title: getAllFiles
	 * @Description: 获取所有文件
	 * @param bucketName
	 *            节点名称
	 * @param dir
	 *            目录
	 * @return 所有文件列表
	 * @throws:
	 * @time: 2018年4月16日 下午12:57:11
	 */
	public static List<String> getAllFiles(String bucketName, String dir) {
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
		List<String> ls = new ArrayList<String>();
		if (!StringUtility.isNull(dir)) {
			listObjectsRequest.setDelimiter("/");
			listObjectsRequest.setPrefix(dir + "/");
		}

		ObjectListing listing = getOSSClient().listObjects(listObjectsRequest);

		boolean flag = false;
		for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
			if (!flag) {
				flag = true;
				continue;
			} else {
				ls.add(PropertyUtil.getProperty("ali_oss_out_href_prefix", "") + "/" + objectSummary.getKey());
			}
		}
		return ls;
	}

	/**
	 * 通过文件名判断并获取OSS服务文件上传时文件的contentType
	 * 
	 * @param fileName
	 *            文件名
	 * @return 文件的contentType
	 */
	public static final String getContentType(String fileName) {
		String name = "text/html";
		String fileExtension = fileName.substring(fileName.lastIndexOf("."));
		if ("bmp".equalsIgnoreCase(fileExtension))
			name = "image/bmp";
		if ("gif".equalsIgnoreCase(fileExtension))
			name = "image/gif";
		if ("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension)
				|| "png".equalsIgnoreCase(fileExtension))
			name = "image/jpeg";
		if ("html".equalsIgnoreCase(fileExtension))
			name = "text/html";
		if ("txt".equalsIgnoreCase(fileExtension))
			name = "text/plain";
		if ("vsd".equalsIgnoreCase(fileExtension))
			name = "application/vnd.visio";
		if ("ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension))
			name = "application/vnd.ms-powerpoint";
		if ("doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension))
			name = "application/msword";
		if ("xml".equalsIgnoreCase(fileExtension))
			name = "text/xml";
		return name;
	}
}
