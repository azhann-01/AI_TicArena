package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDAO {

    public boolean registerUser(
            String username,
            String password
    ){
        try{

            Connection conn = DBConnection.getConnection();

            String query = "INSERT INTO users(username,password) VALUES(?,?)";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);

            ps.executeUpdate();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            
            return false;
        }
    }
}