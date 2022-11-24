package com.example.project.controller;

import com.example.project.model.AccountOpenRequest;
import com.example.project.model.AccountOpenStatus;
import com.example.project.model.User;
import com.example.project.service.AccountRequestService;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService uService;

    @Autowired
    private AccountRequestService rService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView prepareRegister() {
        ModelAndView mav = new ModelAndView("user-register");
        mav.addObject("user", new User());
        mav.addObject("accountRequest", new AccountOpenRequest());
        return mav;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView newRegister(@ModelAttribute("user") @Valid User user, BindingResult result, @ModelAttribute("accountRequest") AccountOpenRequest accountRequest) {
        // validation
        if (result.hasErrors()) return new ModelAndView("user-register");
        // Encrypt password
        String encryptedPwd = DigestUtils.md5DigestAsHex(user.getPwd().getBytes());
        user.setPwd(encryptedPwd);
        // Create User
        User newUser = uService.createUser(user);

        // Generate account request if the user would like to open an account
        if (accountRequest.getType() != null) {
            rService.createRequest(accountRequest, newUser, AccountOpenStatus.PENDING);
        }
        ModelAndView mav = new ModelAndView("user-register-success");
        return mav;
    }

}
