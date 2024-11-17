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
    public boolean authenticateUser(String email, String password) {

        String mySQL_query = "SELECT bruker_id, epost, passord FROM gruppe13.bruker WHERE epost = ?";
        String updateMySQL_query = "UPDATE bruker SET passord = ? WHERE bruker_id = ?";

        try(Connection connection = MySQLAdapter.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(mySQL_query)) {

            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                int userId = rs.getInt("bruker_id");
                String storedPassword = rs.getString("passord");

                if(!storedPassword.startsWith("$2a$")) {

                    if(!storedPassword.equals(password)) {
                        return false;
                    }

                    try(PreparedStatement updateStatement = connection.prepareStatement(updateMySQL_query)) {
                        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                        updateStatement.setString(1, hashedPassword);
                        updateStatement.setInt(2, userId);
                        updateStatement.executeQuery();
                    }
                    return true;
                }
                return BCrypt.checkpw(password, storedPassword);
            }
        }
        catch(ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage());
        }
        return false;
    }

    @Override
    public int getUserIdByEmail(String email) {
        int userId = -1;
        String mySQL_query = "SELECT bruker_id FROM gruppe13.bruker WHERE epost = ?";

        try (Connection connection = MySQLAdapter.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(mySQL_query)) {

            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("bruker_id");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println(userId);
        return userId;
    }

    @Override
    public String getToken(int userId) {
        String mySQL_query = "SELECT token FROM gruppe13.bruker WHERE bruker_id = ?";

        try(Connection connection = MySQLAdapter.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(mySQL_query)) {

            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getString("token");
            }
        }
        catch (ClassNotFoundException | SQLException e) {

        }
        return null;
    }

    @Override
    public void saveToken(String token, int userId) {
        String mySQL_query = "UPDATE gruppe13.bruker SET token = ? WHERE bruker_id = ?";

        try(Connection connection = MySQLAdapter.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(mySQL_query)) {

            preparedStatement.setString(1, token);
            preparedStatement.setInt(2, userId);

            // Executing the query
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Token saved successfully!");
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Failed to save token", e);
        }
    }

    @Override
    public void removeToken(int userId) {
        String mySQL_query = "UPDATE bruker SET token = NULL WHERE bruker_id = ?";

        try(Connection connection = MySQLAdapter.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(mySQL_query)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.executeQuery();
        }
        catch(ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Remove token failed", e);
        }
    }
}
