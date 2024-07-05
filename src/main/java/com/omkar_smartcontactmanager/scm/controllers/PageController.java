package com.omkar_smartcontactmanager.scm.controllers;


import com.omkar_smartcontactmanager.scm.entities.User;
import com.omkar_smartcontactmanager.scm.entities.message;
import com.omkar_smartcontactmanager.scm.entities.messageType;
import com.omkar_smartcontactmanager.scm.forms.UserForm;
import com.omkar_smartcontactmanager.scm.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;




@Controller
public class PageController {

    private final UserService userService;

    public PageController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/home")
    public String home() {
            System.out.println("In home page handler");
            return "home";
    }
    //about route
    @RequestMapping("/about")
    public String aboutPage() {
        System.out.println(" About Page loading");
        return "about";
    }
    //services
    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("Services Page loading");
        return "services";
    }
    //contact page
    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }
    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();
        userForm.setName("Omkar");
        model.addAttribute("userForm",userForm);
        return"register";
    }

    //processing regiter
    @RequestMapping(value="/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session) {
        System.out.println("Processing registration");
        //fetch the form data
        //user form
        System.out.println(userForm);
        //validate data
        if(rBindingResult.hasErrors()) {
            return "register";
        }


        //save to db
//        User user = User.builder()
//                .name(userForm.getName())
//                .email(userForm.getEmail())
//                .password(userForm.getPassword())
//                .about(userForm.getAbout())
//                .phoneNumber(userForm.getPhoneNumber())
//                .profilePic("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.vecteezy.com%2Ffree-vector%2Fdefault-profile-picture&psig=AOvVaw34FhluuG3ADRoJY8XhOVhl&ust=1720180966420000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOC4m9GrjYcDFQAAAAAdAAAAABAE")
//                .build();
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.vecteezy.com%2Ffree-vector%2Fdefault-profile-picture&psig=AOvVaw34FhluuG3ADRoJY8XhOVhl&ust=1720180966420000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOC4m9GrjYcDFQAAAAAdAAAAABAE");

        User savedUser =  userService.saveUser(user);

        System.out.println("USER SAVED");
        //mesage = registrastion sucesss
        message msg = message.builder().content("Registration Successful").type(messageType.green).build();
        session.setAttribute("message",msg);

        //redirect tot login
        return "redirect:/register";
    }
}
