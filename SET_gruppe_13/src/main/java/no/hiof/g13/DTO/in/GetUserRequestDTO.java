package no.hiof.g13.DTO.in;

import no.hiof.g13.models.Address;
import no.hiof.g13.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserRequestDTO {
    private int bruker_id;
    private String fornavn;
    private String etternavn;
    private int status_id;
    private String mobil;
    private String epost;
    private Address address;
    private int userLevel;

    public User toUser() {
            User user = new User();
            user.setBruker_id(this.bruker_id);
            user.setFornavn(this.fornavn);
            user.setEtternavn(this.etternavn);
            user.setStatus_id(this.status_id);
            user.setMobil(this.mobil);
            user.setEpost(this.epost);
            user.setAddress(new Address(address.getAdresse_id(), address.getAdresse(), address.getPostnummer()));
            user.setUserLevel(this.userLevel);
            return user;
    }

    public static GetUserRequestDTO fromResult(ResultSet rs) throws SQLException {
        GetUserRequestDTO dto = new GetUserRequestDTO();
        dto.bruker_id = rs.getInt("bruker_id");
        dto.fornavn = rs.getString("fornavn");
        dto.etternavn = rs.getString("etternavn");
        dto.status_id = rs.getInt("status_id");
        dto.mobil = rs.getString("mobil");
        dto.epost = rs.getString("epost");
        dto.address = new Address(rs.getInt("adresse_id"), rs.getString("adresse"), rs.getString("postnummer"));
        dto.userLevel = rs.getInt("user_level");
        return dto;
    }
}
