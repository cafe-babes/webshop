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
@Sql(scripts = "/init_users_table.sql")
public class UsersTests {


    @Autowired
    private UserController userController;


    @Test
    public void contextLoads() {

        // Given (having a user list)
        List<User> users = userController.listUsers();

        assertEquals(4, users.size());

        // When (adding a user)
        userController.insertUser(new User(5L, "Ciara Doe", "a@eee.com","cccdoe","asdf",1,
                "ROLE_USER", "ACTIVE"));

        //Then (size of user is increased by one)
        users = userController.listUsers();
        assertEquals(5, users.size());
    }

    @Test
    public void testDeleteUserById(){

        // Given (having a user list)
        List<User> users = userController.listUsers();

        assertEquals(4, users.size());

        long id = users.get(0).getId();

        // When (deleting a user)
        userController.deleteUserById(id);

        //Then (size of user list is decreased by one)
        users = userController.listUsers();
        assertEquals(3, users.size());

    }

    @Test
    public void testUpdateUserBy(){

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
