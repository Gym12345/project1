package com.GymCompany.myfirstApp;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import userList.UserListDAO;
import userList.UserListDTO;


// 함수 사용 및 리디렉션 , Controller
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
    private UserListDAO userListDAO;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "home";
	}
	
	
	
	
	
	@RequestMapping(value = "/loginMenu", method = RequestMethod.GET)
	public String loginMenu(Model model) {
		return "loginMenu";
	}

	@RequestMapping(value = "/registerMenu", method = RequestMethod.GET)
	public String registerMenu(Model model) {
		return "registerMenu";
	}
	
	
	 @PostMapping("/loginCheck.do")
	    public String login(@ModelAttribute UserListDTO dto, Model D, HttpSession session) {
		 
	        String userName = userListDAO.loginCheck(dto,session);
	        
	        
	        if (userName.equals("noInfo")  == false && userName.equals("error")== false) {
	        	
	        	
	            D.addAttribute("userName", dto.getUserName());
	            return "loginSuccess"; // Assuming you have a welcome.jsp or welcome.html page
	            
	        } else {
	        	
	        	
	            D.addAttribute("error", "Invalid credentials");
	            return "loginMenu"; // Redirect back to the login page with an error message
	        }
	    }
	 
	 @GetMapping("/logout.do")
	    public String logout(HttpSession session) {
	        userListDAO.logout(session);

	        return "redirect:/loginMenu"; // Redirect to the login page after logout
	    }
	    
	 
//	 @PostMapping("/registerCheck.do")
// 		public String register(@RequestParam String userId, @RequestParam String userPw,  @RequestParam String userName , Model D) {
//		 	
//		 	int result=userListDAO.userRegister(userId,userPw,userName);
//		 	
//		 	if(result==1) {
//		 		
//		 		
//		 		D.addAttribute("message","new user Registered successfully");
//		 		
//		 		return "registerSuccess";
//		 	}
//		 	else {
//		 		D.addAttribute("message","new user Registration failed");
//		 		return "registerMenu";
//		 		
//		 	}
//		 	
// 	
//	 	}
	 @PostMapping("/registerCheck.do")
		public String register(@ModelAttribute UserListDTO dto, Model D) {
		 	
		 	int result=userListDAO.userRegister(dto);
		 	
		 	if(result==1) {
		 		
		 		
		 		D.addAttribute("message","new user Registered successfully");
		 		
		 		return "registerSuccess";
		 	}
		 	else {
		 		D.addAttribute("message","new user Registration failed");
		 		return "registerMenu";
		 		
		 	}
		 	
	
	 	}


	 @RequestMapping("/showAllUserInfo")
	 	public String showAllUserInfo(Model D) {
		 
		 List<UserListDTO> infos=new ArrayList<>();
		 infos=userListDAO.showAllUserList();
		 
		 
		 D.addAttribute("allUserInfo", infos);
		
		 
		 return "showAllUserInfo";
	 }
	 	 
	 	
	    
	   
   
    
}
