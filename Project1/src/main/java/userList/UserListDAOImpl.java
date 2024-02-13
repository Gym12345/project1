package userList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
//  함수정의   Model
@Repository
public class UserListDAOImpl implements UserListDAO {

    private JdbcTemplate jt;
    Lock sessionUpdateLock = new ReentrantLock();

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jt) {
        this.jt = jt;
    }
    
    
    @Override
    
    
    public String loginCheck(UserListDTO dto, HttpSession session) {
    		
    	
        try {	
        	
        	sessionUpdateLock.lock();
            String userName = jt.queryForObject("SELECT USERNAME FROM USERLIST WHERE USERID=? AND USERPW=?", new UserListMapper(), dto.getUserId(), dto.getUserPw());
            
            System.out.println("Lock acquired by thread: " + Thread.currentThread().getId());
            
            if (userName.equals(null) == false) {
                // 세션 변수 저장
            	
            	System.out.println("userName: " +userName); //잘찍힘
            	
                session.setAttribute("userId", dto.getUserId()); 
                session.setAttribute("name", userName);
                
                return userName;
            }
            
            else {
            	return "noInfo";
            }
            
        } catch (EmptyResultDataAccessException e) {
            // Log or handle the case where the query returns no result
            // For example, you can return a specific value or throw a custom exception
            return "error";
        } catch (Exception e) {
            // Log or handle other exceptions
            e.printStackTrace();
            throw e;
        }finally {
        	
        	sessionUpdateLock.unlock();
            System.out.println("Lock released by thread: " + Thread.currentThread().getId());
            
        	
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

    
	public int userRegister(UserListDTO dto) {
	    int result = 0;
	    try {
	        
	        result = jt.update("INSERT INTO USERLIST (ULID, USERID, USERPW, USERNAME, JOIN_DATE, LASTLOGINTIME) VALUES (userList_seq.nextval, ?, ?, ?, sysdate, CURRENT_TIMESTAMP)",  
	        		 dto.getUserId(), dto.getUserPw(), dto.getUserName());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return result;
	}



	
	public List<UserListDTO> showAllUserList() {
	    List<UserListDTO> allUserInfo = new ArrayList<>();

	    try {
	        allUserInfo = jt.query("SELECT * FROM UserList", new UserListMapper2());

	    } catch (Exception e) {
	        // Handle exception (log, rethrow, etc.)
	        System.err.println("Error fetching user list: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return allUserInfo;
	}

	

}
