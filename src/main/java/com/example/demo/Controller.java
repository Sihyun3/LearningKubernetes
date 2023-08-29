package com.example.demo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class Controller {
	@GetMapping("/")
	public ResponseEntity<String> Main() throws Exception {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			String strIpAdress = inetAddress.getHostAddress();
			String html = "2번 서비스 현재 내부 아이피 주소는 <b>" + strIpAdress+"</b> 입니다.";
			return ResponseEntity.status(HttpStatus.OK).body(html);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("아이피 주소를 불러 올 수 없습니다");
		}
			
	}
	
	@GetMapping("/image.jpg")
	public void getMusic(  HttpServletResponse response) throws Exception {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String UPLOAD_PATH = "C:/upload/";
		try {
			response.setHeader("Content-Disposition", "inline;");
			byte[] buf = new byte[1024];
			fis = new FileInputStream(UPLOAD_PATH + "img.heic");
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(response.getOutputStream());
			int read;
			while ((read = bis.read(buf, 0, 1024)) != -1) {
				bos.write(buf, 0, read);
			}
		} finally {
			bos.close();
			bis.close();
			fis.close();
		}
	}
	

}
