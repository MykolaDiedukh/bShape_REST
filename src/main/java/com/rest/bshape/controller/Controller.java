package com.rest.bshape.controller;


import com.rest.bshape.entity.User;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
@RequestMapping("/users")
public class Controller {

    // ustawianie headera w endpoint
    // przekazanie sesji,ciasteczka itp.
    @ResponseBody
    @GetMapping("/getUserWithHeader/{id}")
    public User getUserWithHeader(@PathVariable() int id, @RequestHeader("nazwaHeadera") String session){
        User user = new User(1, "112", "Marek", "markowski");
        if (id == user.getId()) {
            System.out.println(session);
            return user;
        } else {
            return new User();
        }

    }



    /*
    pobieranie Usera przekazując jego id w param


     */

    @ResponseBody
    @GetMapping("/getUser/{id}")
    public User getUsers(@PathVariable() int id) {
        User user = new User(1, "112", "Marek", "markowski");
        if (id == user.getId()) {
            return user;
        } else {
            return new User();
        }
    }

    /*
    GetMapping - zwraca dane
    PostMapping - dodaje dane
    DeleteMapping - usuwa dane
    PutMapping - updateuje dane
     */

    /*
    1 sposób, w postamanie musze
    dodać
    {
"id": 1,
"name": "marek",
"lastName": "jakis",
"phoneNumber": "1000"
}
     */

    @ResponseBody
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return user;
    }


    /*
    Przekazanie wymagań do server
    np:

     */
    @ResponseBody
    @PostMapping("/addUser1")
    public void getUserByUserType(@RequestParam String userType) {

    }

    // wywoływanie api backendu z poziomu javy




}