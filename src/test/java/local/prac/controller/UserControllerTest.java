package local.prac.controller;

import local.prac.entity.AppRole;
import local.prac.entity.AppUser;
import local.prac.service.UserService;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mvc;

    @Before
    public void before() {


        List<AppUser> userList = Arrays.asList(
                new AppUser(1, "A", Arrays.asList(
                        new AppRole(1, "admin")
                )),
                new AppUser(2, "B", Arrays.asList(
                        new AppRole(2, "normalUser")
                ))
        );


    }

    private List<AppUser> mockUserList() {
        return Arrays.asList(
                new AppUser(1, "A", Arrays.asList(
                        new AppRole(1, "admin")
                )),
                new AppUser(2, "B", Arrays.asList(
                        new AppRole(2, "normalUser")
                ))
        );
    }

    @Test
    public void findUser() throws Exception {
        /*
        //Mockito version

        when(userService.findAll())
                .thenReturn(mockUserList()));
        */


        //BDDMockito (Extension of Mockito and provides better readability of method names)
        given(userService.findAll())
                .willReturn(mockUserList());

        mvc.perform(
                    get("/users")
                    .accept(MediaType.APPLICATION_JSON) //just to demo, actually not required
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", IsCollectionWithSize.hasSize(2)))
                .andExpect(jsonPath("$[0].name", Is.is("A")))
                .andExpect(jsonPath("$[1].name", Is.is("B")))
                .andExpect(jsonPath("$[0].roles[0].name", Is.is("admin")))
                ;


    }

    @Test
    public void findUserWithId() throws Exception {

        given(userService.findById(ArgumentMatchers.eq(1L)))
                .willReturn(mockUserList().get(0));


        mvc.perform(get("/users/1")
                        .accept(MediaType.APPLICATION_JSON) //just to demo, actually not required
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", Is.is("A")))
                .andExpect(jsonPath("roles[0].name", Is.is("admin")))
                ;
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}