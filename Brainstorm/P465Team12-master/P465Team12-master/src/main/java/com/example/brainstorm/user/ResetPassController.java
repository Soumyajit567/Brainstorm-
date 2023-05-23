//package com.example.brainstorm.user;
//
//import net.bytebuddy.utility.RandomString;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.query.Param;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Controller;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import javax.servlet.http.HttpServletRequest;
//import java.io.UnsupportedEncodingException;
//
//@Controller
//public class ResetPassController {
//    /**
//     * A controller specifically for resetting a user's password.
//     */
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Autowired
//    private UserService userService;
//
//    /**
//     * Shows the forgotten password page.
//     *
//     * @return forgotten password page
//     */
//    @GetMapping("/forgot_pass")
//    public String showForgotPassForm(){
//        return "forgot_pass_form";
//    }
//
//    /**
//     * Generates a random reset token for a user in order to reset their password.
//     * Calls sendEmail() to send a link containing the token to the user's given email.
//     *
//     * @return forgotten password page
//     */
//    @PostMapping("/forgot_pass")
//    public String processForgotPass(HttpServletRequest request, Model model){
//        String email = request.getParameter("email");
//        String token = RandomString.make(30);
//        String siteURL = request.getRequestURL().toString();
//        siteURL = siteURL.replace(request.getServletPath(),"");
//
//        try {
//            userService.updateResetToken(token,email);
//            String resetPassLink = siteURL + "/reset_pass?token=" + token;
//            sendEmail(email, resetPassLink);
//            model.addAttribute("message", "Please check your email for a link to reset your password.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "forgot_pass_form";
//    }
//
//    /**
//     * Sends an email to the user containing a link to reset their password.
//     *
//     * @param sendTo the email to send the email to
//     * @param link the link contained within the email
//     * @throws MessagingException
//     * @throws UnsupportedEncodingException
//     */
//    public void sendEmail(String sendTo, String link)
//    throws MessagingException, UnsupportedEncodingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//        helper.setFrom("brainstormp465@gmail.com", "BrainStorm");
//        helper.setTo(sendTo);
//
//        String subject = "BrainStorm Password Reset";
//
//        String content = "<p>Hello,</p>"
//                + "<p>Someone requested to reset your BrainStorm password.</p>"
//                + "<p>Click the link below to change your password:</p>"
//                + "<p><a href=\"" + link + "\">Change my password</a></p>"
//                + "<br>"
//                + "<p>Ignore this email if you do remember your password, "
//                + "or you have not made the request.</p>";
//
//        helper.setSubject(subject);
//        helper.setText(content,true);
//        mailSender.send(message);
//    }
//
//    /**
//     * Shows the reset password page... provided that the user has a valid reset token.
//     *
//     * @return reset password page
//     */
//    @GetMapping("/reset_pass")
//    public String showResetPassForm(@Param(value="token") String token, Model model){
//        Users user = userService.getByResetToken(token);
//        model.addAttribute("token", token);
//
//        if (user == null){
//            model.addAttribute("message","Invalid Token");
//            return "reset_success";
//        }
//
//        return "reset_pass_form";
//    }
//
//    /**
//     * Allows user to reset their password.
//     *
//     * @return
//     */
//    @PostMapping("/reset_pass")
//    public String processResetPass(HttpServletRequest request, Model model){
//        String token = request.getParameter("token");
//        String pass = request.getParameter("password");
//
//        Users user = userService.getByResetToken(token);
//        model.addAttribute("title", "Reset your password");
//
//        if (user == null){
//            model.addAttribute("message","Invalid Token");
//            return "reset_success";
//        } else {
//            userService.updatePassword(user,pass);
//            model.addAttribute("message", "You have successfully changed your password.");
//        }
//
//        return "reset_success";
//    }
//}
