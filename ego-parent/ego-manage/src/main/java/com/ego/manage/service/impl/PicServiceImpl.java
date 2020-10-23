package com.ego.manage.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ego.commons.utils.FtpUtil;
import com.ego.manage.service.PicService;

@Service
public class PicServiceImpl implements PicService{
	@Value("${ftpClient.host}")
	private String host;
	@Value("${ftpClient.port}")
	private int port;
	@Value("${ftpClient.username}")
	private String username;
	@Value("${ftpClient.password}")
	private String password;
	@Value("${ftpClient.basePath}")
	private String basePath;
	@Value("${ftpClient.filePath}")
	private String filePath;
	@Override
	public Map<String, Object> upload(MultipartFile uploadFile) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		String oldName = uploadFile.getOriginalFilename();
		String filename = UUID.randomUUID()+oldName.substring(oldName.lastIndexOf("."));
//		System.out.println("url:http://"+host+":80"+filePath+filename);
//		boolean result = false;
//		try {
//			 result = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, filename, uploadFile.getInputStream());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally {
		boolean result = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, filename, uploadFile.getInputStream());
			if(result) {
				map.put("error",0);
				map.put("url","http://"+host+":80"+filePath+filename);
			}else {
				map.put("error",1);
				map.put("msg","图片上传失败");
			}
//		}
		return map;
	}
	
}
