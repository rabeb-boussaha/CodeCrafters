package CodeCrafters.CodeCrafters.Services;

import CodeCrafters.CodeCrafters.Repository.UserRepository;
import CodeCrafters.CodeCrafters.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServicesImpl implements UserServices {

    private  UserRepository userRepository;

// add user
    @Override
    public User addUser(User u) {
        if (u.getFirstname() == null || u.getFirstname().isEmpty()) {
            throw new IllegalArgumentException("User firstname is not empty");
        }
        try {
            return userRepository.save(u);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add user ", e);
        }


    }

    // affiche all user

    @Override
    public List<User> All() {
        return userRepository.findAll();
    }

    //edit user

    @Override
    public User editUser(User u) throws RuntimeException {
        if (u.getId() == null) {
            throw new IllegalArgumentException("User ID cannot be null for editing");
        }
        try {
            // Vérifiez si l'utilisateur existe dans la base de données
            User existingUser = userRepository.findById(Long.valueOf(u.getId()))
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            // Mettez à jour les champs de l'utilisateur existent avec les nouvelles valeurs
            userRepository.updateUser(Long.valueOf(u.getId()), u.getFirstname(), u.getLastname()); // Exemple d'une méthode updateUser définie dans UserRepository

            // Retournez l'utilisateur mis à jour
            return existingUser;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user", e);
        }
    }
// delete user
@Override
public void deleteUser(Long idUser) {
    Optional<User> user = userRepository.findById(idUser);

    user.ifPresent(u -> {
        userRepository.delete(u);
        log.info("User with id " + idUser + " has been deleted");
    });

}

}
