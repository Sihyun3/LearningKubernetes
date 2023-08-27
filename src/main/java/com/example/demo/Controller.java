package com.example.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@GetMapping("/")
	public ResponseEntity<String> Main() throws Exception {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			String strIpAdress = inetAddress.getHostAddress();
			String html = "1번 서비스 현재 내부 아이피 주소는 <b>" + strIpAdress+"</b> 입니다.";
			return ResponseEntity.status(HttpStatus.OK).body(html);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("아이피 주소를 불러 올 수 없습니다");
		}
			
	}

}
