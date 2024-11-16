package no.hiof.g13.adapters;

import no.hiof.g13.models.User;
import no.hiof.g13.ports.out.AuthenticateUserAPI_Port;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class AuthenticateUserAPI_RepositoryMySQL implements AuthenticateUserAPI_Port {

    @Override
    public User authenticateUser(String epost, String passord) {

        String mySQL_script = "SELECT bruker_id, epost, passord FROM gruppe13.bruker WHERE epost = ?";

        try(Connection connection = MySQLAdapter.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(mySQL_script)) {

            preparedStatement.setString(1, epost);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                User user = new User(
                  rs.getInt("bruker_id"), rs.getString("epost"), rs.getString("passord")
                );

                if(verifyPassword(passord, user.getPassord())) {
                    return user;
                }
            }

        }
        catch(ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage());
        }
        return null;
    }

    private boolean verifyPassword(String input, String storedPass) {
        return BCrypt.checkpw(input, storedPass);
    }
}
