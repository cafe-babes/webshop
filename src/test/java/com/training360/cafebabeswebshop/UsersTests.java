package com.training360.cafebabeswebshop;

import com.training360.cafebabeswebshop.user.User;
import com.training360.cafebabeswebshop.user.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/init.sql")
public class UsersTests {


    @Autowired
    private UserController userController;


    @Test
    public void testCreateUserAndListUsers() {

        // Given (having a user list)
        List<User> users = userController.listUsers();

//        assertEquals(7, users.size());

        // When (adding a user)
        userController.createUser(new User(5L, "Ciara Doe", "a@eee.com","cccdoe","asdf",1,
                "ROLE_USER", "ACTIVE"));

        //Then (size of user is increased by one)
        assertEquals(users.size()+1, userController.listUsers().size());
    }

    @Test
    public void testDeleteUserById(){

        // Given (having a user list)
        List<User> users = userController.listUsers();

        long id = users.get(0).getId();

        // When (deleting a user)
        userController.deleteUserById(id);

        //Then (size of user list is decreased by one)
        assertEquals(users.size()-1, userController.listUsers().size());

    }

    @Test
    public void testUpdateUserById(){

        // Given (having a user list)
        List<User> users = userController.listUsers();


        User exampleUser = users.get(0);
        long exampleUserId = users.get(0).getId();

        assertThat(users.contains(exampleUser), equalTo(true));

        // When (modifying a user by ID)
        userController.updateUser(exampleUserId, (new User(5L, "Ciara Doe", "a@eee.com","cccdoe","asdf",1,
                "ROLE_USER", "ACTIVE")));

        //Then (example user can not be found any more in the list)
        users = userController.listUsers();
        assertThat(users.contains(exampleUser), equalTo(false));

        // Ciara Doe can be found in the list and it's userName is cccdoe

        User updatedUser = users.stream().filter((user)-> user.getName().equals("Ciara Doe")).findFirst().get();
        String updatedUsersUserName = "cccdoe";
        assertEquals(updatedUsersUserName, updatedUser.getUserName());
    }
}
