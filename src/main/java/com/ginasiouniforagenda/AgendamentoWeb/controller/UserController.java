package com.ginasiouniforagenda.AgendamentoWeb.controller;

import com.ginasiouniforagenda.AgendamentoWeb.domain.User;
import com.ginasiouniforagenda.AgendamentoWeb.repository.UserRepository;
import com.ginasiouniforagenda.AgendamentoWeb.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/agendamento")
public class UserController {

    @Autowired
    private HelloWorldService helloWorldService;

    //    public HelloWorldController(HelloWorldService helloWorldService){
    //        this.helloWorldService = helloWorldService;
    //    }

    // GET /hello-world
    @GetMapping("/introducao")
    public String helloWorld(){
        return helloWorldService.HelloWorld("Bea");
    }

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/cadastro")
    public String helloWorldPost(@RequestBody User body)
//                                 @RequestParam(value = "filter") String filter,
                                 {

        User savedUser = userRepository.save(body);

        return "Hello World!\n" +
                savedUser.toString() + "\n";
//                filter;
    }

    @GetMapping("/{id}")
    public String pegarUser(@PathVariable("id") Long id){

        Optional<User> userOptional = userRepository.findById(id);

        return userOptional.get().toString();
    }

}
