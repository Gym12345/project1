package userList;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    //static int RedundancyResult=0;
    
    
    @Override
    public String loginCheck(UserListDTO dto, HttpSession session) {
        try {
            sessionUpdateLock.lock(); 
            
            String hashedPassword = jt.queryForObject("SELECT USERPW FROM USERLIST WHERE USERID=?", new UserListMapper3(), dto.getUserId()); 
            
            if (hashedPassword != null) {//the password verification is done separately using the  BCryptPasswordEncoderensures that the entered password (dto.getUserPw()) matches the stored hashed password in the database (hashedPassword). This is a secure way to authenticate users.
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                
                if (encoder.matches(dto.getUserPw(), hashedPassword)) {//this line holds the security    
                    String userName = jt.queryForObject("SELECT USERNAME FROM USERLIST WHERE USERID=?", new UserListMapper(), dto.getUserId());
                    //String userName = jt.queryForObject("SELECT USERNAME FROM USERLIST WHERE USERID=? AND USERPW=?", new UserListMapper(), dto.getUserId() ,hashedPassword);//보안상 더 취약 위보다
                    //The strength of the security lies in how passwords are stored and verified
                    if (userName != null) {
                        session.setAttribute("userId", dto.getUserId());
                        session.setAttribute("userName", userName);
                        
                        return userName;
                    } else {
                        return "noInfo";
                    }
                } else {
                    return "noInfo";
                }
            } else {
                return "noInfo";
            }
        } catch (EmptyResultDataAccessException e) {
            return "noInfo";
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            sessionUpdateLock.unlock();
        }
    }


    
    @Override
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
	public int userRegister(UserListDTO dto) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
    	System.out.println("encoded pw:"+encoder.encode(dto.getUserPw()));
    	
	    int result = 0;
	    try {
	    	
	        result = jt.update("INSERT INTO USERLIST (ULID, USERID, USERPW, USERNAME, JOIN_DATE, LASTLOGINTIME) VALUES (userList_seq.nextval, ?, ?, ?, sysdate, CURRENT_TIMESTAMP)",  
	        		 dto.getUserId(), encoder.encode(dto.getUserPw()), dto.getUserName());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return result;
	}

	@Override
		public int userIdRedundancyCheck(String userId) {
			int RedundancyResult=0;
			String userName;
			try {
				userName=jt.queryForObject("SELECT USERNAME FROM USERLIST WHERE USERID=?",new UserListMapper() ,userId);
				System.out.println("userName"+userName);
				if(userName != null) { //중복 
					RedundancyResult=1;
				}
				else {  //중복X
					RedundancyResult=0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return RedundancyResult;
		}
	
	
	
	
	
	
	@Override
	public List<UserListDTO> showAllUserList() {
	    List<UserListDTO> allUserInfo = new ArrayList<>();

	    try {
	        allUserInfo = jt.query("SELECT * FROM USERLIST ORDER BY USERLIST.ULID ASC", new UserListMapper2());

	    } catch (Exception e) {
	        // Handle exception (log, rethrow, etc.)
	        System.err.println("Error fetching user list: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return allUserInfo;
	}



	
	

}
