package com.goodee.mvcBoard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // 서블릿에서 사용하던 filter, listener 사용하기 위해 추가 ->
public class MvcBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcBoardApplication.class, args);
	}

}
