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
	
	
	
	
	
	@RequestMapping(value = "/loginMenu.do", method = RequestMethod.GET)
	public String loginMenu(Model model) {
		return "loginMenu";
	}
	
	
	
	 @PostMapping("/loginCheck.do")
	    public String login(@RequestParam String userId, @RequestParam String userPw, Model model, HttpSession session) {
		 
	        String userName = userListDAO.loginCheck(userId, userPw);
	        
	        if (userName != null) {
	            model.addAttribute("userName", userName);
	            return "loginSuccess"; // Assuming you have a welcome.jsp or welcome.html page
	        } else {
	        	
	        	
	            model.addAttribute("error", "Invalid credentials");
	            return "loginMenu.do"; // Redirect back to the login page with an error message
	        }
	    }

	    @GetMapping("/logout")
	    public String logout(HttpSession session) {
	        userListDAO.logout(session);
	        return "redirect:/loginMenu.do"; // Redirect to the login page after logout
	    }
   
    
}
