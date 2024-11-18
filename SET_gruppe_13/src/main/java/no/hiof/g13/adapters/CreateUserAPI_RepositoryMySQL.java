package no.hiof.g13.adapters;

import no.hiof.g13.ports.CreateUserAPI_Port;

import java.sql.*;

public class CreateUserAPI_RepositoryMySQL implements CreateUserAPI_Port {

    @Override
    public int createUser(String firstName, String lastName, Integer statusId, String mobile, String email, String hashPass, String address, String postnumber, Integer userLevel) {
        Connection connection = null;
        int userId;
        try {
            connection = MySQLAdapter.getConnection();
            connection.setAutoCommit(false);

            String mySQL_query = "INSERT INTO bruker (fornavn, etternavn, status_id, mobil, epost, passord, user_level) VALUES(?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(mySQL_query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setInt(3, statusId);
                statement.setString(4, mobile);
                statement.setString(5, email);
                statement.setString(6, hashPass);
                statement.setInt(7, userLevel);

                int affectedRows = statement.executeUpdate();

                if(affectedRows == 0) {
                    throw new RuntimeException("Creating user failed, no rows affected.");
                }

                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    userId = rs.getInt(1);
                    System.out.println("Generated user ID: " + userId);
                } else {
                    throw new RuntimeException("Creating new user failed, could not get ID");
                }
            }

            String addressQuery = "INSERT INTO adresse (adresse_id, adresse, postnummer) VALUES(?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(addressQuery)) {
                statement.setInt(1, userId);
                statement.setString(2, address);
                statement.setString(3, postnumber);
                statement.executeUpdate();
            }

            connection.commit();
            return userId;

        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.err.println("Rollback failed: " + ex.getMessage());
                }
            }
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Creating new user failed: " + e.getMessage(), e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
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
