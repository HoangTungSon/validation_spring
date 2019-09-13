package source.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import source.model.RegisterForm;

@Controller
public class RegisterController {
    @GetMapping("/")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("regitForm", new RegisterForm());
        return modelAndView;
    }

    @PostMapping("/result")
    public ModelAndView checkValidation(@Validated @ModelAttribute("regitForm") RegisterForm registerForm, BindingResult bindingResult, Model model) {
        new RegisterForm().validate(registerForm, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("index");
        } else {
            ModelAndView modelAndView = new ModelAndView("result");
            model.addAttribute("firstName", registerForm.getFirstName());
            model.addAttribute("lastName", registerForm.getLastName());
            model.addAttribute("age", registerForm.getAge());
            model.addAttribute("email", registerForm.getEmail());
            return modelAndView;
        }
    }
}
