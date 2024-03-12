package com.scaler.userservice1.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This class will be service Rest (HTTP) APIS
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/say/{name}/{times}")
    public String sayHello(@PathVariable("name") String name, @PathVariable("times") int times) {
        String ans = "";
        for (int i=0;i<times;i++) {
            ans = ans + "Hello " + name;
            ans = ans + "<br>";
        }

        return ans;
    }
}
