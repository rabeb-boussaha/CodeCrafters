package CodeCrafters.CodeCrafters.Controller;

import CodeCrafters.CodeCrafters.Services.UserServices;
import CodeCrafters.CodeCrafters.model.Article;
import CodeCrafters.CodeCrafters.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/User")
public class UserController {

    private   UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }


    @PostMapping("/add-User")
    public User addUser(@RequestBody User u) {
        return userServices.addUser(u);
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
        userServices.deleteUser(id);
    }
}


