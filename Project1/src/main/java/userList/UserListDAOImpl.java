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
}
