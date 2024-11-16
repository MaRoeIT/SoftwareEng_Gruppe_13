package no.hiof.g13.DTO.out;

public class AuthenticateUserResponseDTO {
    private final boolean authenticated;
    private final String message;
    private final int userId;

    public AuthenticateUserResponseDTO(boolean authenticated, String message, int userId) {
        this.authenticated = authenticated;
        this.message = message;
        this.userId = userId;
    }

    public static AuthenticateUserResponseDTO successLogin(int userId) {
        return new AuthenticateUserResponseDTO(true, "Login successful", userId);
    }

    public static AuthenticateUserResponseDTO failedLogin(String message) {
        return new AuthenticateUserResponseDTO(false, message, 0);
    }


    public boolean isAuthenticated() {
        return authenticated;
    }

    public String getMessage() {
        return message;
    }

    public int getUserId() {
        return userId;
    }
}
