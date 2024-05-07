package com.GymCompany.myfirstApp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import userList.SessionDTO;
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
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
	 	
	 	logger.info("Displaying test section.");
		return "test";
	}
	
	
	@RequestMapping(value = "/loginMenu", method = RequestMethod.GET)
	public String loginMenu(Model model, HttpSession session) {
	 	session.setAttribute("loginFailCnt", loginFailCnt);
	 	 logger.info("Displaying login menu.");
		return "loginMenu";
	}

	@RequestMapping(value = "/registerMenu", method = RequestMethod.GET)
	public String registerMenu(Model model,HttpSession session) {
		 boolean redundancyResult = true;
	     session.setAttribute("redundancyResult", redundancyResult);
	     logger.info("Displaying registerMenu");
		return "registerMenu";
	}
	


	
    @PostMapping("/loginCheck.do")
    public String login(@ModelAttribute UserListDTO dto, Model D, HttpSession session, HttpServletRequest request) {
        session.setAttribute("loginFailCnt", loginFailCnt);
        String userName = userListDAO.loginCheck(dto, session);

        if (!userName.equals("noInfo") && !userName.equals("error")) {
            System.out.println(session.getAttribute("loginFailCnt"));
            logger.info("successfully logged in: " + dto.getUserId());
            failCnt = 0;
            loginFailCnt = Integer.toString(failCnt);
        	session.setAttribute("loginFailCnt", loginFailCnt);
            session.setAttribute("userName",userName);


           
            D.addAttribute("message", "login successful");
            return "home";

        } else {
            failCnt++;
            loginFailCnt = Integer.toString(failCnt);
            session.setAttribute("loginFailCnt", loginFailCnt);
            D.addAttribute("message", "Invalid credentials");
            logger.info("bad login try as: " + dto.getUserId());

            if (failCnt >= 5) {
                session.setAttribute("locker", "locked");
                logger.info("locked (bad UserId): " + dto.getUserId());
                return "loginMenu";
            }

            return "loginMenu"; // Redirect back to the login page with an error message
        }
    }

	 
	 
	 
	 @RequestMapping("/logout.do")
	    public String logout(HttpSession session,UserListDTO dto) {
		 	logger.info("User is being Logout:"+dto.getUserId());
	        userListDAO.logout(session);
	        
	        return "loginMenu"; // Redirect to the login page after logout
	    }
	    
	 @PostMapping("/invalidateLocker.do")
	    public String invalidateLocker(HttpSession session) {
	        // Invalidate the "locker" attribute
		 	logger.info("unlocking (bad UserId)");
	        session.removeAttribute("locker");
	        failCnt=0;
        	loginFailCnt=Integer.toString(failCnt);  
        	session.setAttribute("loginFailCnt", loginFailCnt);
        	
        	
	        return "loginMenu";
	    }
	 
	 @PostMapping("/registerCheck.do")
		public String register(@ModelAttribute UserListDTO dto,HttpServletRequest request, Model D)  {
		 	
		 
		 	int result=userListDAO.userRegister(dto);
		 	
		 	if(result==1) {

		 		D.addAttribute("message","new user Registered successfully please login");
		 		logger.info("newly registered:"+dto.getUserId());
		 		return "loginMenu";
		 	}
		 	else {
		 		D.addAttribute("message","new user Registration failed");
		 		logger.info("error occured during process(register) as:"+dto.getUserId());
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
		
		 logger.info("showing whole info");
		 return "showAllUserInfo";
	 }
	 	 
	 
	 	private LinkedHashMap<String, String> sessionMap = new LinkedHashMap<>(); // key - value
	    private LinkedHashMap<String, SessionDTO> sessionArr = new LinkedHashMap<>(); // sessionId as key - (key,value) as value
	   
	    @PostMapping("/generateJDBCSession.do")
	    public void springSessionJdbcTest(@RequestParam String userName, @RequestParam String data, HttpSession session) {
	        
	        if(sessionMap.containsKey(userName)) {//hashmap does not allow redundancy of key
	           
	            
	        } else {
	            sessionMap.put(userName, data);

	            Iterator<Map.Entry<String, String>> it = sessionMap.entrySet().iterator();
	            SessionDTO sdto = null;
	    	    String sessionId = null;
	    	    
	    	    
	           

	            while (it.hasNext()) { // the outcome will refers last one of sessionMap (sdto)
	                Map.Entry<String, String> entry = it.next(); 

	                sdto = new SessionDTO();

	              //  sessionId = UUID.randomUUID().toString();
	                sessionId=session.getId();
	                sdto.setKey(entry.getKey());
	                sdto.setValue(entry.getValue());
	            }  
	            
	            sessionArr.put(sessionId, sdto);
	            addSessionAttribute(session, sessionId, sdto);

	          
	        }
	    }

	    @PostMapping("/showSessions.do")
	    public void showSessions( HttpSession session) {
	    	  Iterator<Map.Entry<String, SessionDTO>> it = sessionArr.entrySet().iterator();
	            System.out.println("size:" + sessionArr.size());

	            while (it.hasNext()) {
	                Map.Entry<String, SessionDTO> entry = it.next();
	                String sessionId = entry.getKey();
	               
	                System.out.println("sessionId:"+ sessionId +"  data:"+session.getAttribute(sessionId));
	            }
	    
	    }
	    
	    private void addSessionAttribute(HttpSession session, String sessionId, Object keyValuePair) {
	        session.setAttribute(sessionId, keyValuePair);
	    }
		
//	    @Autowired
//	    private RedisTemplate<String, Object> redisTemplate;
//
//	    @PostMapping("/generateRedisSession.do")
//	    public void generateRedisSession(@RequestParam String userName, @RequestParam String data) {
//	        String sessionId = UUID.randomUUID().toString();
//	        SessionDTO sessionDTO = new SessionDTO();
//	        sessionDTO.setKey(userName);
//	        sessionDTO.setValue(data);
//
//	        redisTemplate.opsForValue().set(sessionId, sessionDTO);
//	    }
//
//	    @PostMapping("/showRedisSessions.do")
//	    public void showRedisSessions() {
//	        Set<String> keys = redisTemplate.keys("*");
//	        for (String key : keys) {
//	            System.out.println("sessionId: " + key + "  data: " + redisTemplate.opsForValue().get(key));
//	        }
//	    } 
//		 
		 
	 
	    
	   
   
    
}
