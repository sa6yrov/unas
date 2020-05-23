package kg.onlinestore.unas.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.onlinestore.unas.entities.User;
import kg.onlinestore.unas.exceptions.WrongUserException;
import kg.onlinestore.unas.models.UserAuth;
import kg.onlinestore.unas.models.UserModel;
import kg.onlinestore.unas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public ResponseEntity<?> save(@RequestBody UserModel userModel){
        try {
            return new ResponseEntity<>(userService.createUser(userModel), HttpStatus.OK);

        } catch (WrongUserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id){
        return userService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }

    @GetMapping("/check")
    public String check(Principal principal){
        return principal.getName();
    }

    @PostMapping("/auth")
    public String signIn(@RequestBody UserAuth userAuth){
        return userService.getToken(userAuth);
    }

}
