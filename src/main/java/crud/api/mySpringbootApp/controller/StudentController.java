package crud.api.mySpringbootApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Student controller.
 *
 * @author Jitendra Rawat
 */

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @GetMapping("/students")
    public String home(){
        return "Hello World!";
    }
}
