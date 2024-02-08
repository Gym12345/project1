-error log-

1.HttpSession  
  함수 매개변수에 HttpSession 을 써줘야 사용가능함
  
  EX)
  public String loginCheck(String userId, String userPw, HttpSession session) { //이런식으로
    		
    	
        try {
        	
        	System.out.println("userId: "+userId);
            String userName = jt.queryForObject("SELECT USERNAME FROM USERLIST WHERE USERID=? AND USERPW=?", new UserListMapper(), userId, userPw);
            
            if (userName.equals(null) == false) {
                // 세션 변수 저장
            	
            	System.out.println("userName: " +userName); //잘찍힘
            	
                session.setAttribute("userId", userId); 
                session.setAttribute("name", userName);
                
                return userName;
            }
            
            else {
            	return "noInfo";
            }
            
        } catch (EmptyResultDataAccessException e) {
            // Log or handle the case where the query returns no result
            // For example, you can return a specific value or throw a custom exception
            return "noInfo";
        } catch (Exception e) {
            // Log or handle other exceptions
            e.printStackTrace();
            throw e;
        }
    }
    
