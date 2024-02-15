package userList;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface UserListDAO {
	public String loginCheck(UserListDTO dto,HttpSession session);
	public void logout(HttpSession session);
	public int userRegister(UserListDTO dto);
	public List<UserListDTO> showAllUserList();
	public int userIdRedundancyCheck(String userId);
}
