package com.example.demo.user;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
@RestController
public class UserTableService {
    private final UsertableRepo userTableRepo;

    @Autowired
    public UserTableService(UsertableRepo usertableRepo) {
        this.userTableRepo = usertableRepo;
    }

    @GetMapping("api/user")
    public List<UserTable> allUsers(){
        return userTableRepo.findAll();
    }

    @GetMapping("api/user/{id}")
    //path variable prendi la bvariabile dal percorso(come un form con action get)
    public Optional<UserTable> userById(@PathVariable Long id){
        return userTableRepo.findById(id);
    }

    @PostMapping("api/user")
    //request body lo prendi dall'esterno come un file json(come un form con action post)
    public UserTable newUser(@RequestBody UserTable newUser){
        return userTableRepo.save(newUser);
    }

    @DeleteMapping("api/user/{id}")
    public void deleteById(@PathVariable Long id){
        userTableRepo.delete(userTableRepo.findById(id).orElseThrow());
    }

    @PutMapping("api/user/{id}")
    public UserTable updateUser(@PathVariable Long id, @RequestBody UserTable newUser){
        UserTable userTable = userTableRepo.findById(id).orElseThrow();
        userTable.setName(newUser.getName());
        userTable.setAge(newUser.getAge());
        return userTableRepo.save(userTable);
    }
}
