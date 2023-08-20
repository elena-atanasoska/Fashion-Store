package onlineshopapp.fashionstore.web.controller;

import onlineshopapp.fashionstore.model.User;
import onlineshopapp.fashionstore.model.enumerations.Role;

import onlineshopapp.fashionstore.model.exceptions.*;
import onlineshopapp.fashionstore.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;



    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String email) {
        try {
            User user = this.userService.register(name, username, password, repeatedPassword, Role.ROLE_USER, email);
            return "redirect:/home";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException | UsernameAlreadyExistsException |
                 InvalidEmailException | EmailAlreadyExistsException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
