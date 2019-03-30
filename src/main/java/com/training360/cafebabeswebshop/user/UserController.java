package com.training360.cafebabeswebshop.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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

    @GetMapping("/user-role")
    public User determineRole(Authentication authentication) {
        if (authentication == null)
            return new User(0, null, null, null, null, 1, "VISITOR", null);
        boolean isUser = false;
        boolean isAdmin = false;
        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                isUser = true;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            }
        }

        User user = userService.getUserByName(authentication.getName());
        if (isAdmin) {
            return new User(user.getId(), null, null, authentication.getName(), null, 1, "ROLE_ADMIN", null);
        } else if (isUser) {
            return new User(user.getId(), user.getName(), null, authentication.getName(), null, 1, "ROLE_USER", null);
        } else {
            return new User(0, null, null, authentication.getName(), null, 1, "VISITOR", null);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResultStatus deleteUserById(@PathVariable long id) {
        userValidator = new UserValidator(userService);
        if (userValidator.deletionWasSuccessFul(id)) {
            return new ResultStatus(ResultStatusEnum.OK, "A felhasználó törlése sikeres volt.");
        }
        return new ResultStatus(ResultStatusEnum.NOT_OK, "A felhasználó törlése sikertelen volt.");
    }

    @PostMapping("/users/{id}")
    public ResultStatus updateUser(@PathVariable long id, @RequestBody User user) {
        userValidator = new UserValidator(userService);
        if (userValidator.userCanBeSaved(user)) {
            userService.updateUser(id, user);
            return new ResultStatus(ResultStatusEnum.OK, "A felhasználó sikeresen módosításra került");
        }
        return new ResultStatus(ResultStatusEnum.NOT_OK, "A módosítás sikertelen volt");
    }

    @PostMapping("/users")
    public ResultStatus createUser(@RequestBody User user) {
        userValidator = new UserValidator(userService);
        if (userValidator.userCanBeSaved(user)) {
            long id;
            try {
                id = userService.insertUserAndGetId(user);
            } catch (DataAccessException sql) {
                sql.printStackTrace();
                return new ResultStatus(ResultStatusEnum.NOT_OK, String.format("\"%s\" már regisztrált felhasználó!", user.getUserName()));
            }
            return new ResultStatus(ResultStatusEnum.OK, String.format("\"%s\" sikeresen mentésre került. ( id: %d )", user.getUserName(), id));
        }
        return new ResultStatus(ResultStatusEnum.NOT_OK, "Üres név vagy jelszó lett megadva");//TODO
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }
}
