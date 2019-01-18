package local.prac.controller;

import local.prac.entity.AppUser;
import local.prac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<AppUser> findUser() {
        return  userService.findAll();
    }

    @GetMapping("/{id}")
    public AppUser findUser(@PathVariable("id") Long id) {
        return  userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //201
    public AppUser create(@RequestBody AppUser user) {
        return userService.add(user);
    }

    @PutMapping
    //@ResponseStatus(HttpStatus.OK) //200 //it is default
    public void update(@RequestBody AppUser user) {
        userService.update(user);
    }

    @DeleteMapping("/{id}")
    //@ResponseStatus(HttpStatus.OK) //200 //it is default
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
