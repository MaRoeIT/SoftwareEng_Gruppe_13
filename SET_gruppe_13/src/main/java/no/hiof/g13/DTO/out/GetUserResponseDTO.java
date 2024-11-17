package no.hiof.g13.DTO.out;

import no.hiof.g13.models.Address;
import no.hiof.g13.models.User;

public class GetUserResponseDTO {
    private int userId;
    private String firstName;
    private String lastName;
    private int status_id;
    private String mobile;
    private String email;
    private Address address;
    private int userLevel;

    public GetUserResponseDTO(int userId, String firstName, String lastName, int status_id, String mobile, String email, Address address, int userLevel) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status_id = status_id;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.userLevel = userLevel;
    }

    public static GetUserResponseDTO fromDomain(User user) {
        return new GetUserResponseDTO(
            user.getBruker_id(), user.getFornavn(), user.getEtternavn(),
                user.getStatus_id(), user.getMobil(), user.getEpost(),
                user.getAddress(), user.getUserLevel()
        );
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getStatus_id() {
        return status_id;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public int getUserLevel() {
        return userLevel;
    }}
