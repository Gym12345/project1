package com.GymCompany.myfirstApp;





import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import userList.UserListDAO;
import userList.UserListDTO;

import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@CrossOrigin
public class ControllerForClient {
     @Autowired
    private UserListDAO userListDAO;
    
    @RequestMapping(value = "/test", produces = "application/json")
    @ResponseBody // Ensure that the response is serialized to JSON
    public ArrayList<String> test() {
        System.out.println("signal coming in /test");
        ArrayList<String> dataContainer = new ArrayList<String>();
        dataContainer.add("data1");
        dataContainer.add("data2");
        dataContainer.add("data3");
        return dataContainer;    
    }
    
    @RequestMapping(value = "/test1", produces = "application/json")
    @ResponseBody // Ensure that the response is serialized to JSON
    public String test1() {
        System.out.println("signal coming in /test1");
        String str = "hello1~";
        return str;    
    }
    
    @RequestMapping(value = "/test2")
    @ResponseBody // Ensure that the response is serialized to JSON
    public String test2() {
        System.out.println("signal coming in /test2");
        return "hello2~";    
    }
    
   
    
   
    @PostMapping(value="/login" , produces = "application/json")
    @ResponseBody
    public String login(@RequestBody Map<String, String> credentials,HttpSession session) {
        String userId = credentials.get("userId");
        String userPw = credentials.get("password");
        System.out.println("Login attempt with Username: " + userId + " Password: " + userPw);
        UserListDTO dto=new UserListDTO();
        dto.setUserId(userId); dto.setUserPw(userPw);
        String userName=userListDAO.loginCheck(dto, session);
        System.out.println(userName);
        

        return userName;
        
        
    }
    
// // Generate a secure key // for jwt 
//    private static final byte[] SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();
//    
//    
//    @PostMapping(value="/login" , produces = "application/json")
//    @ResponseBody
//    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials, HttpSession session) {
//        String userId = credentials.get("userId");
//        String userPw = credentials.get("password");
//        System.out.println("Login attempt with Username: " + userId + " Password: " + userPw);
//        UserListDTO dto=new UserListDTO();
//        dto.setUserId(userId); 
//        dto.setUserPw(userPw);
//        String userName = userListDAO.loginCheck(dto, session);
//        System.out.println(userName);
//        
//        if (userName != null && userName!="noInfo" ) {
//            // Generate token
//            String token = Jwts.builder()
//                    .setSubject(userName)
//                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // You should replace "secretKey" with a secure secret key
//                    .compact();
//            return ResponseEntity.ok(token);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//
//    
//    
//    
//    
//    }
}
