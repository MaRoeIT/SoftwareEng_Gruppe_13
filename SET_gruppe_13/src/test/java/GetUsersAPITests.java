import io.javalin.Javalin;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import no.hiof.g13.API.GetUsersAPI;
import no.hiof.g13.models.Address;
import no.hiof.g13.models.User;
import no.hiof.g13.ports.GetUsersAPI_Port;
import org.json.JSONException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GetUsersAPITests {

    @Mock
    private GetUsersAPI_Port apiPort;
    private Javalin app;

    @BeforeEach
    void setUp() {
        app = Javalin.create();
        GetUsersAPI api = new GetUsersAPI(apiPort);
        api.getUsers(app);
        app.start(8080);
    }

    @AfterEach
    void tearDown() {
        app.stop();
    }

    @Test
    @DisplayName("getAllUsers returns 200 and list of users in JSON")
    public void getAllUsers_returns_ListOfUsers() throws JSONException {
        // Arrange
        List<User> userList = Arrays.asList(
                new User(10, "Thorman", "Lextorias", 3, "81549300", "thorman.lextorias@pepsimax.com", "321drossap", new Address(34, "Skomakergata 3", "1680"), 1),
                new User(11, "Onkel Skrue", "McDuck", 3, "12345678", "onkelSkrue@hiof.no", "12324asasd", new Address(35, "Andebyveien 3", "1680"), 1),
                new User(12, "Green Mario", "Mario", 3, "524226336", "luigi.mario@gamer.com", "sasafasf", new Address(36, "Mushroom kingdom 10", "1684"), 1)
        );
        Mockito.when(apiPort.getAllUsers()).thenReturn(userList);

        // Act
        HttpResponse<String> response = Unirest.get("http://localhost:" + app.port() + "/api/users").asString();

        // Assert
        Assertions.assertEquals(200, response.getStatus());
        Mockito.verify(apiPort).getAllUsers();
        JSONAssert.assertEquals("""
            [
                {"bruker_id": 10, "fornavn": "Thorman", "etternavn": "Lextorias", "status_id": 3, "mobil": "81549300", "epost": "thorman.lextorias@pepsimax.com", "address": { "adresse_id": 34, "adresse": "Skomakergata 3", "postnummer": "1680" }, "userLevel": 1},
                {"bruker_id": 11, "fornavn": "Onkel Skrue", "etternavn": "McDuck", "status_id": 3, "mobil": "12345678", "epost": "onkelSkrue@hiof.no", "address": { "adresse_id": 35, "adresse": "Andebyveien 3", "postnummer": "1680" }, "userLevel": 1},
                {"bruker_id": 12, "fornavn": "Green Mario", "etternavn": "Mario", "status_id": 3, "mobil": "524226336", "epost": "luigi.mario@gamer.com", "address": { "adresse_id": 36, "adresse": "Mushroom kingdom 10", "postnummer": "1684" }, "userLevel": 1}
             ]""", response.getBody(), true
        );
    }

    @Test
    @DisplayName("getAllUsers returns 404 and empty list when no registered users")
    public void getAllUsers_returnsEmptyListNoRegisteredUsers() throws JSONException {
        // Arrange
        List<User> emptyUserList = new ArrayList<>();
        Mockito.when(apiPort.getAllUsers()).thenReturn(emptyUserList);

        // Act
        HttpResponse<String> response = Unirest.get("http://localhost:" + app.port() + "/api/users").asString();

        // Assert
        Assertions.assertEquals(200, response.getStatus());
        Mockito.verify(apiPort).getAllUsers();
        JSONAssert.assertEquals("[]", response.getBody(), true);
    }

    @Test
    @DisplayName("getUserById returns 200 and correct User in JSON when for given ID")
    public void getUserById_returnsCorrectUserForGivenId() throws JSONException {
        // Arrange
        Address address = new Address(5, "Julebrusveien 15", "1680");
        User user = new User(1, "Ola", "Normann", 3, "+478529634", "ola.normann@hiof.no", "unhashed_password", address, 1);
        Mockito.when(apiPort.getUserById(1)).thenReturn(user);

        // Act
        HttpResponse<String> response = Unirest.get("http://localhost:" + app.port() + "/api/users/id/1").asString();

        // Assert
        Assertions.assertEquals(200, response.getStatus());
        Mockito.verify(apiPort).getUserById(1);
        JSONAssert.assertEquals("""
            {"bruker_id": 1, "fornavn": "Ola", "etternavn": "Normann", "status_id": 3,
            "mobil": "+478529634", "epost": "ola.normann@hiof.no",
            "address": { "adresse_id": 5, "adresse": "Julebrusveien 15", "postnummer": "1680" }, "userLevel": 1}
            """, response.getBody(), true
        );
    }

    @Test
    @DisplayName("getUserById returns 404 and no User found for non existing ID")
    public void getUserById_returnsNoUsersNonExistingId() throws JSONException  {
        // Arrange
        Mockito.when(apiPort.getUserById(999999)).thenReturn(null);

        // Act
        HttpResponse<String> response = Unirest.get("http://localhost:" + app.port() + "/api/users/id/999999").asString();

        // Assert
        Assertions.assertEquals(404, response.getStatus());
        Mockito.verify(apiPort).getUserById(999999);
    }
}