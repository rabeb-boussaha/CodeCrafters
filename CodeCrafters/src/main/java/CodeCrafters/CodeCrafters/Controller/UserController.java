package CodeCrafters.CodeCrafters.Controller;

import CodeCrafters.CodeCrafters.Exception.UserNotFoundException;
import CodeCrafters.CodeCrafters.Services.UserServices;
import CodeCrafters.CodeCrafters.model.Article;
import CodeCrafters.CodeCrafters.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/User")
public class UserController {

    private   UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

/*
    @PostMapping("/add-User")
    public User addUser(@RequestBody User u) {
        return userServices.addUser(u);
    }*/
@PostMapping("/add-User")
public ResponseEntity<User> addUser(@RequestBody User u) {
    if (u.getFirstname() == null || u.getFirstname().isEmpty()) {
        return ResponseEntity.badRequest().body(null);
    }

    try {
        User addedUser = userServices.addUser(u);
        return ResponseEntity.ok(addedUser);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}


    // affiche all user
    @GetMapping("/users")
    public List<User> getUser() {
        return userServices.All();
    }


// edit user
    @PutMapping("/edit-user")
    public User editUser(@RequestBody User u) {
        return userServices.editUser(u);
    }

    //delete user
    @DeleteMapping("/delete-user/{idUser}")
    public void deleteUser(@PathVariable("idUser") Long id) {
        // Vérifier si l'utilisateur existe avant de le supprimer
        if (userServices.isUserExists(id)) {
            userServices.deleteUser(id);
        } else {
            throw new UserNotFoundException(id); // Initialisez l'exception avec l'ID de l'utilisateur
        }
    }


}


