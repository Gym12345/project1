//package com.GymCompany.myfirstApp;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//
//@Controller
//public class LogController {
//	private static final Log  logger = LogFactory.getLog(LogController.class);
//
//	@RequestMapping("/log")
//	@ResponseBody
//	public String logExam() {
//		logger.debug( "#ex1 - debug log" );
//		logger.info( "#ex1 - info log" );	
//		logger.warn( "#ex1 - warn log" );	
//		logger.error( "#ex1 - error log" );
//		
//		return "콘솔 또는 파일경로 확인";
//	}	
//}
