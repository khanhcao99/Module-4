package com.example.saveuser.controller;

import com.example.saveuser.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class SaveUserController {
    @ModelAttribute("user")
    public User setUpForm(){
        return new User();
    }

    @RequestMapping
    public String display(@CookieValue(value = "setUser", defaultValue = "") String setUser, Model model){
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("cookieValue", cookie);
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(@ModelAttribute("user") User user, Model model, @CookieValue(value = "setUser", defaultValue = "") String setUser, HttpServletResponse response, HttpServletRequest request){
        if (user.getEmail().equals("khanhboy1324@gmail.com") & user.getPassword().equals("123456")){
            setUser = user.getEmail();
            Cookie cookie = new Cookie("setUser", setUser);
            cookie.setMaxAge(600);
            response.addCookie(cookie);

            Cookie[] cookies = request.getCookies();
            for (Cookie ck : cookies){
                if (ck.getName().equals("setUser")){
                    model.addAttribute("cookie", ck);
                    break;
                }else {
                    ck.setValue("");
                    model.addAttribute("cookie", ck);
                    break;
                }
            }
            model.addAttribute("message", "Đăng nhập thành công");
        }else {
            user.setEmail("");
            Cookie cookie = new Cookie("setUser", setUser);
            model.addAttribute("cookieValue", cookie);
            model.addAttribute("message", "Login failed. Try again.");
        }
        return "login";
    }
}
