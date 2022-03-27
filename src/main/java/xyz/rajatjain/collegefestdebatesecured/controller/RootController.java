package xyz.rajatjain.collegefestdebatesecured.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rajatjain on - 27-03-2022
 * @project CollegeFestDebateSecured
 */
@Controller
public class RootController {

    @RequestMapping("/")
    public String showList() {
        return "redirect:/student/list";
    }

}
