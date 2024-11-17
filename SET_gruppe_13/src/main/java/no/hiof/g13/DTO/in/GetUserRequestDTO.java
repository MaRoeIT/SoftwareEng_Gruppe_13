package no.hiof.g13.DTO.in;

import no.hiof.g13.models.Address;
import no.hiof.g13.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserRequestDTO {
    private int userId;
    private String firstName;
    private String lastName;
    private int status_id;
    private String mobile;
    private String email;
    private Address address;
    private int userLevel;

    public User toUser() {
            User user = new User();
            user.setBruker_id(this.userId);
            user.setFornavn(this.firstName);
            user.setEtternavn(this.lastName);
            user.setStatus_id(this.status_id);
            user.setMobil(this.mobile);
            user.setEpost(this.email);
            user.setAddress(new Address(address.getAdresse_id(), address.getAdresse(), address.getPostnummer()));
            user.setUserLevel(this.userLevel);
            return user;
    }

    public static GetUserRequestDTO fromResult(ResultSet rs) throws SQLException {
        GetUserRequestDTO dto = new GetUserRequestDTO();
        dto.userId = rs.getInt("bruker_id");
        dto.firstName = rs.getString("fornavn");
        dto.lastName = rs.getString("etternavn");
        dto.status_id = rs.getInt("status_id");
        dto.mobile = rs.getString("mobil");
        dto.email = rs.getString("epost");
        dto.address = new Address(rs.getInt("adresse_id"), rs.getString("adresse"), rs.getString("postnummer"));
        dto.userLevel = rs.getInt("user_level");
        return dto;
    }
}
