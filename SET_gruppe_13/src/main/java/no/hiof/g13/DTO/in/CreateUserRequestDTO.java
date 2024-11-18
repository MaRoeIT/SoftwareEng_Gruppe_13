package no.hiof.g13.DTO.in;

import no.hiof.g13.models.Address;

public class CreateUserRequestDTO {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String mobile;
    private final Address address;
    private final int statusId;

    public CreateUserRequestDTO(String firstName, String lastName, String email, String mobile, Address address, int statusId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.statusId = statusId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public Address getAddress() {
        return address;
    }

    public int getStatusId() {
        return statusId;
    }
}
