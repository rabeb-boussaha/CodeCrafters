package CodeCrafters.CodeCrafters.Services;

import CodeCrafters.CodeCrafters.model.User;

import java.util.List;

public interface UserServices {
    User addUser(User u);

    List<User> All();

    //edit user
    User editUser(User u)  throws RuntimeException;

    // delete user
    void deleteUser(Long idUser);
}
