package no.hiof.g13.adapters;

import no.hiof.g13.DTO.in.GetUserRequestDTO;
import no.hiof.g13.models.Address;
import no.hiof.g13.models.User;
import no.hiof.g13.ports.GetUsersAPI_Port;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetUsersAPI_RepositoryMySQL implements GetUsersAPI_Port {

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        String mySQL_query = "SELECT bruker_id, fornavn, etternavn, status_id, mobil, epost, user_level, adresse_id, adresse, postnummer FROM bruker JOIN adresse a ON adresse_id = bruker_id";

        try(Connection connection = MySQLAdapter.getConnection();
            PreparedStatement statement = connection.prepareStatement(mySQL_query)) {
            ResultSet rs = statement.executeQuery();

                while(rs.next()) {
                    GetUserRequestDTO dto = new GetUserRequestDTO();

                    dto.setBruker_id(rs.getInt("bruker_id"));
                    dto.setFornavn(rs.getString("fornavn"));
                    dto.setEtternavn(rs.getString("etternavn"));
                    dto.setStatus_id(rs.getInt("status_id"));
                    dto.setEpost(rs.getString("epost"));
                    dto.setMobil(rs.getString("mobil"));
                    dto.setAddress(new Address(rs.getInt("adresse_id"), rs.getString("adresse"), rs.getString("postnummer")));
                    dto.setUserLevel(rs.getInt("user_level"));

                    users.add(dto.toDomain());
                }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("No users found");
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getUserById(int userId) {

        String mySQL_query = "SELECT bruker_id, fornavn, etternavn, status_id, mobil, epost, user_level, adresse_id, adresse, postnummer FROM bruker JOIN adresse a ON adresse_id = bruker_id WHERE bruker_id = ?";

        try (Connection connection = MySQLAdapter.getConnection();
             PreparedStatement statement = connection.prepareStatement(mySQL_query)) {

            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                GetUserRequestDTO dto = new GetUserRequestDTO();

                dto.setBruker_id(rs.getInt("bruker_id"));
                dto.setFornavn(rs.getString("fornavn"));
                dto.setEtternavn(rs.getString("etternavn"));
                dto.setStatus_id(rs.getInt("status_id"));
                dto.setEpost(rs.getString("epost"));
                dto.setMobil(rs.getString("mobil"));
                dto.setAddress(new Address(rs.getInt("adresse_id"), rs.getString("adresse"), rs.getString("postnummer")));
                dto.setUserLevel(rs.getInt("user_level"));

                return dto.toDomain();
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("No user found with ID: " + userId);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {

        String mySQL_query = "SELECT bruker_id, fornavn, etternavn, status_id, mobil, epost, user_level, adresse_id, adresse, postnummer FROM bruker JOIN adresse a ON adresse_id = bruker_id WHERE epost = ?";

        try (Connection connection = MySQLAdapter.getConnection();
             PreparedStatement statement = connection.prepareStatement(mySQL_query)) {

            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                GetUserRequestDTO dto = new GetUserRequestDTO();

                dto.setBruker_id(rs.getInt("bruker_id"));
                dto.setFornavn(rs.getString("fornavn"));
                dto.setEtternavn(rs.getString("etternavn"));
                dto.setStatus_id(rs.getInt("status_id"));
                dto.setEpost(rs.getString("epost"));
                dto.setMobil(rs.getString("mobil"));
                dto.setAddress(new Address(rs.getInt("adresse_id"), rs.getString("adresse"), rs.getString("postnummer")));
                dto.setUserLevel(rs.getInt("user_level"));

                return dto.toDomain();
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("No user found with email: " + email);
            e.printStackTrace();
        }
        return null;
    }
}