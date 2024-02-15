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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import userList.UserListDAO;
import userList.UserListDTO;


// 함수 사용 및 리디렉션 , Controller
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
    private UserListDAO userListDAO;
	
	
	String loginFailCnt="0";
	int failCnt=Integer.parseInt(loginFailCnt);

	@RequestMapping(value = "/" , method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "home";
	}
	@RequestMapping(value = "/home" , method = RequestMethod.GET)
	public String home1(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "home";
	}
	
	
	
	
	
	@RequestMapping(value = "/loginMenu", method = RequestMethod.GET)
	public String loginMenu(Model model, HttpSession session) {
	 	session.setAttribute("loginFailCnt", loginFailCnt);

		return "loginMenu";
	}

	@RequestMapping(value = "/registerMenu", method = RequestMethod.GET)
	public String registerMenu(Model model,HttpSession session) {
		 boolean redundancyResult = true;
	     session.setAttribute("redundancyResult", redundancyResult);

		return "registerMenu";
	}
	
	
	 @PostMapping("/loginCheck.do")
	    public String login(@ModelAttribute UserListDTO dto, Model D, HttpSession session) {
		 	
     		
		 	session.setAttribute("loginFailCnt", loginFailCnt);
	        String userName = userListDAO.loginCheck(dto,session);
	        
	        
	        if (userName.equals("noInfo")  == false && userName.equals("error")== false) {
	        	System.out.println(session.getAttribute("loginFailCnt"));
	        	
	        	failCnt=0;
	        	loginFailCnt=Integer.toString(failCnt);  
	        	session.setAttribute("loginFailCnt", loginFailCnt);
	        	
	        	D.addAttribute("message", "login successful");
	            session.setAttribute("userName",  userName);
	            
	            return "home"; // Assuming you have a welcome.jsp or welcome.html page
	            
	        } else {
	        	
	        	failCnt=failCnt+1;
	        	loginFailCnt=Integer.toString(failCnt);  
	        	session.setAttribute("loginFailCnt", loginFailCnt);
	            D.addAttribute("message", "Invalid credentials");
	            
	        	if(failCnt>=5) {
	        		session.setAttribute("locker", "locked");
	        		return "loginMenu";
	        	}

	            return "loginMenu"; // Redirect back to the login page with an error message
	        }
	    }
	 
	 
	 
	 @GetMapping("/logout.do")
	    public String logout(HttpSession session) {
	        userListDAO.logout(session);

	        return "redirect:/loginMenu"; // Redirect to the login page after logout
	    }
	    
	 @PostMapping("/invalidateLocker.do")
	    public String invalidateLocker(HttpSession session) {
	        // Invalidate the "locker" attribute
	        session.removeAttribute("locker");
	        failCnt=0;
        	loginFailCnt=Integer.toString(failCnt);  
        	session.setAttribute("loginFailCnt", loginFailCnt);
	        return "loginMenu";
	    }
	 
	 @PostMapping("/registerCheck.do")
		public String register(@ModelAttribute UserListDTO dto, Model D) {
		 	
		 	int result=userListDAO.userRegister(dto);
		 	
		 	if(result==1) {

		 		D.addAttribute("message","new user Registered successfully please login");
		 		
		 		return "loginMenu";
		 	}
		 	else {
		 		D.addAttribute("message","new user Registration failed");
		 		return "registerMenu";
		 		
		 	}
		 	
	
	 	}
	 
	 
	 
	 @PostMapping("/userIdRedundancyCheck.do")
	 public @ResponseBody String userIdRedundancyCheck(@RequestParam String id, HttpSession session) {
	     int redundancyResult = userListDAO.userIdRedundancyCheck(id);
	     System.out.println("RedundancyResult: " + redundancyResult);
	     session.setAttribute("redundancyResult", redundancyResult);
	     
	     if (redundancyResult == 0) {
	         return "false";
	     } else {
	         return "true";
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
