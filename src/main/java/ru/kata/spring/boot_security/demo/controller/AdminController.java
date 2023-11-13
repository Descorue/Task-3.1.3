package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getAllUsers(ModelMap modelMap) {
        modelMap.addAttribute("usersList", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/create")
    public String create(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "create";
    }

    @PostMapping("/create")
    public String addUser(@ModelAttribute(value = "user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(value = "id", required = false) Integer id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.findUserById(id));
        return "/edit";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute(value = "user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(value = "id", required = false) Integer id) {
        userService.deleteUserById(id);

        return "redirect:/admin";
    }
}
