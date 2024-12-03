package net.engineeringdigest.journalApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class myclass {


    @GetMapping("abc") // anotation 
    public String sayhello(){
        return "Jay Ganesh";

    }
}
// in this way we can make a controller or api in spring boot projet