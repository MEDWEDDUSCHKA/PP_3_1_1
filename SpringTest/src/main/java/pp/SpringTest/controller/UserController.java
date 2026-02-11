package pp.SpringTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pp.SpringTest.model.User;
import pp.SpringTest.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAllUsers(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/new")
    public String addNewUsersForm (Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping("/new")
    public String saveUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("email") String email) {
        User user = new User(firstName, lastName, email);
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUsersForm (@RequestParam("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "userForm";
    }

    @PostMapping("/edit")
    public String updateUserFromEdit(@RequestParam("id") Long id, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("email") String email){
        userService.updateUserFromEdit(id, firstName, lastName, email);
        return "redirect:/users";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") Long id, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("email") String email) {
        userService.updateUser(id, firstName, lastName, email);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id")  Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
