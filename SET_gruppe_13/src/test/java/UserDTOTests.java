import no.hiof.g13.DTO.UserDTO;
import no.hiof.g13.models.Address;
import no.hiof.g13.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.internal.matchers.Null;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDTOTests {

    @ParameterizedTest
    @MethodSource("userData")
    @DisplayName("DTO toDomain should map correctly")
    public void toDomainMapsCorrectly(int brukerId, String fornavn, String etternavn, int statusId,
                                      String mobil, String epost, String passord, Address address, int userLevel) {
        // Arrange
        UserDTO dto = new UserDTO(brukerId, fornavn, etternavn, statusId,
                mobil, epost, passord, address, userLevel);

        // Act
        User user = dto.toDomain();

        // Assert
        Assertions.assertNotNull(user);
        assertEquals(brukerId, user.getBruker_id());
        assertEquals(fornavn, user.getFornavn());
        assertEquals(etternavn, user.getEtternavn());
        assertEquals(statusId, user.getStatus_id());
        assertEquals(mobil, user.getMobil());
        assertEquals(epost, user.getEpost());
        assertEquals(passord, user.getPassord());
        assertEquals(address, user.getAddress());
        assertEquals(userLevel, user.getUserLevel());
    }

    @ParameterizedTest
    @MethodSource("userData")
    @DisplayName("Domain to DTO should map correctly")
    void toDTOMapsCorrectly(int brukerId, String fornavn, String etternavn, int statusId,
                            String mobil, String epost, String passord, Address address, int userLevel) {
        // Arrange
        User user = new User();
        user.setBruker_id(brukerId);
        user.setFornavn(fornavn);
        user.setEtternavn(etternavn);
        user.setStatus_id(statusId);
        user.setMobil(mobil);
        user.setEpost(epost);
        user.setPassord(passord);
        user.setAddress(address);
        user.setUserLevel(userLevel);

        // Act
        UserDTO dto = UserDTO.userToDTO(user);

        // Assert
        Assertions.assertNotNull(dto);
        assertEquals(user.getBruker_id(), dto.getBruker_id());
        assertEquals(user.getFornavn(), dto.getFornavn());
        assertEquals(user.getEtternavn(), dto.getEtternavn());
        assertEquals(user.getStatus_id(), dto.getStatus_id());
        assertEquals(user.getMobil(), dto.getMobil());
        assertEquals(user.getEpost(), dto.getEpost());
        assertEquals(user.getPassord(), dto.getPassord());
        assertEquals(user.getAddress(), dto.getAddress());
        assertEquals(user.getUserLevel(), dto.getUserLevel());
    }

    private static Stream<Arguments> userData() {
        return Stream.of(

                // Normal use
                Arguments.of(1, "Kjell-Magne", "Larsen", 1,
                        "+4712345678", "kjellmal@hiof.no", "drossap1234",
                        new Address(1, "Fjellveien 15", "1607 Fredrikstad"), 1),

                // Normal use 2
                Arguments.of(2, "Ola", "Normann", 1,
                        "+4798765432", "ola.normann@hiof.no", "passord321",
                        new Address(2, "Jernbanegata 8", "0562 OSLO"), 2),
                // Empty strings
                Arguments.of(3, "", "", 1,
                        "", "", "",
                        new Address(3, "", ""), 1),

                // Special nordic characters
                Arguments.of(4, "Æla", "Normånn", 1,
                        "+4798765432", "æla.normånn@hiof.no", "åæÆÆØÅØå123",
                        new Address(2, "JÄnbanegata 8", "0562 ÅSLØ"), 1),

                // One or few characters only strings
                Arguments.of(5, "A", "B", 2,
                        "1", "æ.@h.no", "æ",
                        new Address(4, "R 8", "1234 F"), 3)
        );
    }

}