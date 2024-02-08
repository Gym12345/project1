package userList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserListDAOImpl implements UserListDAO {

    private JdbcTemplate jt;
  
    

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jt) {
        this.jt = jt;
    }
    
    
    @Override
    public String loginCheck(String userId, String userPw, HttpSession session) {
    		
    	
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
    

    public void logout(HttpSession session) {
        session.invalidate(); // 세션 초기화
    }

  //ULID	NUMBER	No		1	
  //USERID	VARCHAR2(50 BYTE)	No		2	
  //USERPW	VARCHAR2(100 BYTE)	No		3	
  //USERNAME	VARCHAR2(100 BYTE)	No		4	
  //JOIN_DATE	DATE	Yes	sysdate	5	
  //LASTLOGINTIME	TIMESTAMP(6)	Yes		6	
	@Override
	
	public int userRegister(String userId, String userPw, String userName) {
	    int result = 0;
	    try {
	        System.out.println("userInfos: " + userId + userPw + userName);
	        result = jt.update("INSERT INTO USERLIST (ULID, USERID, USERPW, USERNAME, JOIN_DATE, LASTLOGINTIME) VALUES (userList_seq.nextval, ?, ?, ?, sysdate, null)",  //query error
	                userId, userPw, userName);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return result;
	}

}
