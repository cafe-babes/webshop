package com.training360.cafebabeswebshop.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private UserValidator userValidator;

    @GetMapping("/users")
    public List<User> listUsers(){
        return userService.listUsers();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable long id) {
        userService.deleteUserById(id);
    }

    @PostMapping("/users/{id}")
    public void updateUser(@PathVariable long id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @PostMapping("/users")
    public ResultStatus insertUserAndGetId(@RequestBody User user) {
        userValidator = new UserValidator(userService);
            if(userValidator.userCanBeSaved(user)){
                long id = userService.insertUserAndGetId(user);
                return new ResultStatus(ResultStatusEnum.OK, "Felhasználó sikeresen elmentve");
            }
        return new ResultStatus(ResultStatusEnum.NOT_OK, "Ez a felhasználó már regisztrált!");
    }

}
