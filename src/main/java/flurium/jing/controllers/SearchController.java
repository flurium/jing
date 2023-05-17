package flurium.jing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @GetMapping("/")
    public String search(
        @RequestParam List<String> categories,
        @RequestParam Double min,
        @RequestParam Double max,
        @RequestParam String contains
    ) {

        

        if(categories != null && categories.size() > 0) {

        }

        return "search";
    }
}
