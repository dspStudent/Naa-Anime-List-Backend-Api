package backend.anime.Controller.useRelatedControlls;
import backend.anime.Repostory.UserRepostory;
import backend.anime.email.EmailSevice;
import backend.anime.entites.usersEntities.User;
import backend.anime.entites.usersEntities.UserModal;
import backend.anime.exception.exceptionImp.*;
import backend.anime.security.Auth.AuthRequest;
import backend.anime.security.Auth.AuthResponse;
import backend.anime.security.Auth.AuthService;
import backend.anime.security.Auth.SignUpResponse;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/auth")
public class UserAuthControll {

    @Autowired
    EmailSevice emailSevice;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    UserRepostory userRepostory;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    AuthService authService;
//    @RequestMapping("/")
//    public String index(){
//        return "redirect:/animes/home";
//    }
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody UserModal userModal) throws UserIsAlredyExistedException {
        Optional<UserDetails> userDetails=userRepostory.findByEmail(userModal.getEmail());
        if(userDetails.isPresent())throw new UserIsAlredyExistedException("user is presnt alredy");
        return ResponseEntity.ok(authService.signUpUser(userModal));
    }

    @PostMapping("/loginUser")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest authRequest) throws UserNotVerfiedException {
        return ResponseEntity.ok(authService.authenicateUser(authRequest));
    }
    @GetMapping("/loginOauth")
    public String loginOauth(){
        return "hlo";
    }
//    @GetMapping("/loginCustom")
//    public String login(){
//        return "/loginUser";
//    }

    @GetMapping("/verfiy")
    public ResponseEntity<String> verfiy(@RequestParam(required = true) String email) throws MessagingException {
        return ResponseEntity.ok(authService.verifyUser(email));
    }
    @PostMapping("/enterPin")
    public ResponseEntity<String> enterPinCode(@RequestParam(required = true) String email,
                                               @RequestParam(required = true) String pinCode) throws MessagingException, UserNotFoundException,  UserVerificationPinIsWrongException, UserVerficationPinExpiredException {
        Query query=new Query(Criteria.where("email").is(email));
        List<User>users=mongoTemplate.find(query, User.class);
        System.out.println(users);
        if(users.isEmpty())throw new UserNotFoundException("user not existed");
        var user=users.get(0);
        if(!user.getPinCode().getPinCodeNumber().equals(pinCode)){
            throw new UserVerificationPinIsWrongException("user pin wrong");
        }
        if(user.getPinCode().getExpiration().isBefore(LocalDateTime.now())){
            throw new UserVerficationPinExpiredException( "user Verfication is Expired");
        }
        Update update=new Update().set("enabled", true);
        update.unset("pinCode");
        mongoTemplate.updateFirst(query, update, User.class);
        emailSevice.mailSendTo(email, "Verification", "verfiedSuccefully");
        return ResponseEntity.ok("verfication succefull");
    }
    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestParam(required = true) String email,
                                                 @RequestParam(required = true) String password,
                                                 @RequestParam(required = true) String confirmPassword) throws PasswordAndConfrimPasswordsAreWrongException {
        if(!password.equals(confirmPassword))
            throw new PasswordAndConfrimPasswordsAreWrongException("password and confrim password should be same");
        return ResponseEntity.ok(authService.upDatePassword(email, password));
    }
    @GetMapping("/generateRefreshToken")
    public ResponseEntity<AuthResponse> genrateNewToken(@RequestParam(required = true) String token){
        return ResponseEntity.ok(authService.newToken(token));
    }
}
