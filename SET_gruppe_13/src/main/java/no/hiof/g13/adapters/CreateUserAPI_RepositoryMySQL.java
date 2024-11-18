package no.hiof.g13.adapters;

import no.hiof.g13.models.Address;
import no.hiof.g13.ports.CreateUserAPI_Port;

import java.sql.*;

public class CreateUserAPI_RepositoryMySQL implements CreateUserAPI_Port {

    @Override
    public int createUser(String firstName, String lastName, int statusId, String mobile, String email, String hashPass, String address, String postnumber) {

        String mySQL_query = "BEGIN;" +
                "INSERT INTO bruker (fornavn, etternavn, status_id, mobil, epost, passord) VALUES(?, ?, ?, ?, ?, ?);" +
                "INSERT INTO adresse (adresse, postnummer) VALUES(?, ?);" +
                "COMMIT;";

        try (Connection connection = MySQLAdapter.getConnection();
             PreparedStatement statement = connection.prepareStatement(mySQL_query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, statusId);
            statement.setString(4, mobile);
            statement.setString(5, email);
            statement.setString(6, hashPass);
            statement.setString(7, address);
            statement.setString(8, postnumber);

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0) {
                throw new RuntimeException("Creating new user failed");
            }

            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()) {
                return rs.getInt(1);
            }
            else {
                throw new RuntimeException("Creating new user failed");
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Creating new user failed", e);
        }
    }

    @Override
    public boolean userExists(String email) {
        String mySQL_query = "SELECT COUNT(*) FROM bruker WHERE epost = ?";

        try(Connection connection = MySQLAdapter.getConnection();
            PreparedStatement statement = connection.prepareStatement(mySQL_query)) {

            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Could not search for existing accounts", e);
        }
        return false;
    }
}
