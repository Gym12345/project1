package com.GymCompany.myfirstApp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import userList.UserListDAO;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
    private UserListDAO userListDAO;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	
	
	
	@RequestMapping(value = "/loginMenu", method = RequestMethod.GET)
	public String loginMenu(Model model) {
		return "loginMenu";
	}
//	
//	@RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
//	public String loginSuccess(Model model) {
//		return "loginSuccess";
//	}
	
	
	 @PostMapping("/loginCheck.do")
	    public String login(@RequestParam String userId, @RequestParam String userPw, Model D, HttpSession session) {
		 
	        String userName = userListDAO.loginCheck(userId, userPw,session);
	        System.out.println("homecontroller userName:"+userName);
	        
	        if (userName.equals("noInfo") == false) {
	        	
//	        	
//	        	session.setAttribute("userId", userId); 
//	            session.setAttribute("name", userName);
//	            
	            D.addAttribute("userName", userName);
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
   
    
}
