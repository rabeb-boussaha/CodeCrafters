package CodeCrafters.CodeCrafters.Controller;

import CodeCrafters.CodeCrafters.Exception.UserNotFoundException;
import CodeCrafters.CodeCrafters.Services.UserServices;
import CodeCrafters.CodeCrafters.Services.UserServicesImpl;
import CodeCrafters.CodeCrafters.model.Article;
import CodeCrafters.CodeCrafters.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServices userServices;


    @BeforeEach
    void setUp() {
    }
@Test
    void testAddUserWithValidData() throws Exception {
        User newUser = new User(1, "John", "Doe", "john@example.com", Arrays.asList(new Article()));
        when(userServices.addUser(newUser)).thenReturn(newUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/User/add-User")
                        .contentType("application/json")
                        .content("{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@example.com\",\"articles\":[{}]}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void testAddUserWithEmptyFields() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/User/add-User")
                        .contentType("application/json")
                        .content("{\"id\":1,\"firstName\":\"\",\"lastName\":\"\",\"email\":\"\",\"articles\":[{}]}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test

    void testGetUser() {
        // Mock du service
        UserServices userServices = Mockito.mock(UserServices.class);
        UserController userController = new UserController(userServices);

        // Création de la liste d'utilisateurs attendue
        List<User> users = Arrays.asList(
                new User(1, "biba", "bibos", "john@example.com", Arrays.asList(new Article())),
                new User(2, "Jane", "Smith", "jane@example.com", Arrays.asList(new Article()))
        );

        // Mock de la méthode All() du service pour retourner la liste d'utilisateurs
        when(userServices.All()).thenReturn(users);

        // Appel de la méthode getUser() du contrôleur
        List<User> retrievedUsers = userController.getUser();

        // Assertions
        // Vérification que la liste retournée n'est pas nulle
        assertNotNull(retrievedUsers);

        // Vérification de la taille de la liste retournée
        assertEquals(users.size(), retrievedUsers.size());

        // Vérification des attributs des utilisateurs
        for (int i = 0; i < users.size(); i++) {
            User expectedUser = users.get(i);
            User actualUser = retrievedUsers.get(i);

            assertEquals(expectedUser.getId(), actualUser.getId());
            assertEquals(expectedUser.getFirstname(), actualUser.getFirstname());
            assertEquals(expectedUser.getLastname(), actualUser.getLastname());
            assertEquals(expectedUser.getMail(), actualUser.getMail());

        }
    }


    @Test

    void testEditUser() {
        // Mock du service
        UserServices userServices = Mockito.mock(UserServices.class);
        UserController userController = new UserController(userServices);

        // Création de l'utilisateur à éditer
        User user = new User(1, "John", "Doe", "john@example.com", Arrays.asList(new Article()));

        // Mock de la méthode editUser() du service pour retourner l'utilisateur édité
        when(userServices.editUser(user)).thenReturn(user);

        // Appel de la méthode editUser() du contrôleur
        User editedUser = userController.editUser(user);

        // Assertions
        // Vérification que l'utilisateur édité n'est pas nul
        assertNotNull(editedUser);

        // Vérification que l'utilisateur édité est le même que celui passé en paramètre
        assertEquals(user, editedUser);

        // Vérification des appels au service
        verify(userServices, times(1)).editUser(user);
    }

    @Test

    void testDeleteUser() {
        // Mock du service
        UserServices userServices = Mockito.mock(UserServices.class);
        UserController userController = new UserController(userServices);

        // ID de l'utilisateur à supprimer
        Long userId = 1L;

        // Simuler le cas où l'utilisateur existe
        when(userServices.isUserExists(userId)).thenReturn(true);

        // Appel de la méthode deleteUser() du contrôleur
        userController.deleteUser(userId);

        // Assertions
        // Vérification que la méthode deleteUser() du service est appelée avec le bon ID d'utilisateur
        verify(userServices, times(1)).deleteUser(userId);

        // Simuler le cas où l'utilisateur n'existe pas
        Long nonExistingUserId = 999L;
        when(userServices.isUserExists(nonExistingUserId)).thenReturn(false);
        // Appel de la méthode deleteUser() du contrôleur pour un utilisateur inexistant
        assertThrows(UserNotFoundException.class, () -> userController.deleteUser(nonExistingUserId));
    }

}