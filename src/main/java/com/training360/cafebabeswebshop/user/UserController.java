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
    public List<User> listUsers() {
        return userService.listUsers();
    }

    @DeleteMapping("/users/{id}")
    public ResultStatus deleteUserById(@PathVariable long id) {
        int sizeOfUserListBeforeDeletion = listUsers().size();
        userService.deleteUserById(id);
        int sizeOfUserListAfterDeletingAUser = listUsers().size();
        if (sizeOfUserListBeforeDeletion > sizeOfUserListAfterDeletingAUser) {
            return new ResultStatus(ResultStatusEnum.OK, "A felhasználó törlése sikeres volt.");
        }
        return new ResultStatus(ResultStatusEnum.NOT_OK, "A felhasználó törlése sikertelen volt.");

    }

    @PostMapping("/users/{id}")
    public ResultStatus updateUser(@PathVariable long id, @RequestBody User user) {
        userValidator = new UserValidator(userService);
        if (userValidator.userCanBeUpdated(user)) {
            userService.updateUser(id, user);
            return new ResultStatus(ResultStatusEnum.OK, "A felhasználó sikeresen módosításra került");
        }
        return new ResultStatus(ResultStatusEnum.NOT_OK, "A módosítás sikertelen volt");
    }

    @PostMapping("/users")
    public ResultStatus insertUser(@RequestBody User user) {
        userValidator = new UserValidator(userService);
        if (userValidator.userCanBeSaved(user)) {
            long id = userService.insertUserAndGetId(user);
            return new ResultStatus(ResultStatusEnum.OK, String.format("%s sikeresen mentésre került. ( id = %d )", user.getUserName(), id));
        }
        return new ResultStatus(ResultStatusEnum.NOT_OK, String.format("%s már regisztrált felhasználó!", user.getUserName()));
    }

}
