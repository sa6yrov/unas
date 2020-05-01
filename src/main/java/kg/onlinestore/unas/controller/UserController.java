package kg.onlinestore.unas.controller;

import kg.onlinestore.unas.entities.User;
import kg.onlinestore.unas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll(){
    return userService.getAll();
    }

    @PostMapping
    public User save(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id){
        return userService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
}
