package by.fmpibsu.stogram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/{path:(?!api)[^.]*}")
    public String index(@PathVariable String path) {
        return "forward:/index.html";
    }
}
