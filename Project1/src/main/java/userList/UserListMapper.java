package userList;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserListMapper implements RowMapper<String> {

    @Override
    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
        String userName = rs.getString("USERNAME");
        return userName;
    }
    
    public UserListDTO mapRowForRegister(ResultSet rs, int rowNum) throws SQLException {
    	UserListDTO userListDTO = new UserListDTO();
    	userListDTO.setUlid(rs.getInt("ULID"));
        userListDTO.setUserId(rs.getString("USERID"));
        userListDTO.setUserPw(rs.getString("USERPW"));
        userListDTO.setUserName(rs.getString("USERNAME"));
        userListDTO.setJoinDate(rs.getDate("JOIN_DATE").toLocalDate());
        userListDTO.setLastLoginTime(rs.getTimestamp("LASTLOGINTIME").toLocalDateTime());
        return userListDTO;
    }
    
//    public ArrayList<UserListDTO> mapRowsForShowAllUserList(ResultSet rs) throws SQLException { // 안됨 jdbctemplate 의 query() 메소드가 List<String> arr   이타입으로 리턴해서 그런듯
//    	ArrayList<UserListDTO> userList = new ArrayList<>();
//        
//        while (rs.next()) {
//            UserListDTO userListDTO = new UserListDTO();
//            userListDTO.setUlid(rs.getInt("ULID"));
//            userListDTO.setUserId(rs.getString("USERID"));
//            userListDTO.setUserPw(rs.getString("USERPW"));
//            userListDTO.setUserName(rs.getString("USERNAME"));
//            userListDTO.setJoinDate(rs.getDate("JOIN_DATE").toLocalDate());
//            userListDTO.setLastLoginTime(rs.getTimestamp("LASTLOGINTIME").toLocalDateTime());
//            userList.add(userListDTO);
//        }
//
//        return userList;
//    }
    
//    public List<UserListDTO> mapRowsForShowAllUserList(ResultSet rs) throws SQLException {
//        List<UserListDTO> userList = new ArrayList<>();
//        
//        while (rs.next()) {
//            UserListDTO userListDTO = new UserListDTO();
//            userListDTO.setUlid(rs.getInt("ULID"));
//            userListDTO.setUserId(rs.getString("USERID"));
//            userListDTO.setUserPw(rs.getString("USERPW"));
//            userListDTO.setUserName(rs.getString("USERNAME"));
//            userListDTO.setJoinDate(rs.getDate("JOIN_DATE").toLocalDate());
//            userListDTO.setLastLoginTime(rs.getTimestamp("LASTLOGINTIME").toLocalDateTime());
//            userList.add(userListDTO);
//        }
//
//        return userList;
//    }


}
 