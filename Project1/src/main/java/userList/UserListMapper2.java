package userList;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserListMapper2 implements RowMapper<UserListDTO> {

	@Override
    public UserListDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserListDTO userListDTO = new UserListDTO();
        userListDTO.setUlid(rs.getInt("ULID"));
        userListDTO.setUserId(rs.getString("USERID"));
        userListDTO.setUserPw(rs.getString("USERPW"));
        userListDTO.setUserName(rs.getString("USERNAME"));
        userListDTO.setJoinDate(rs.getDate("JOIN_DATE").toLocalDate());
        userListDTO.setLastLoginTime(rs.getTimestamp("LASTLOGINTIME").toLocalDateTime());
        return userListDTO;
    }
	
//    public UserListDTO mapRow2(ResultSet rs, int rowNum) throws SQLException {
//        UserListDTO user = new UserListDTO();
//        user.setUserId(rs.getString("USERID"));
//        user.setUserName(rs.getString("USERNAME"));
//        // Add more fields as needed
//
//        return user;
//    }

}
 