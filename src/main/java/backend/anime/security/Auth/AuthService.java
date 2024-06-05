package backend.anime.security.Auth;
import backend.anime.Repostory.UserRepostory;
import backend.anime.email.EmailSevice;
import backend.anime.entites.usersEntities.PinCode;
import backend.anime.entites.usersEntities.User;
import backend.anime.entites.usersEntities.UserModal;
import backend.anime.exception.exceptionImp.UserNotVerfiedException;
import backend.anime.security.jwt.JwtService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    UserRepostory userRepostory;
    @Autowired
    EmailSevice emailSevice;
    @Autowired
    PasswordEncoder passwordEncoder;
    public SignUpResponse signUpUser(UserModal userModal) throws MessagingException {
        var user= User.builder()
                .firstName(userModal.getFirstName())
                .secondName(userModal.getSecondName())
                .email(userModal.getEmail())
                .role("User")
                .password(passwordEncoder.encode(userModal.getPassword()))
                .build();
        PinCode pinCode=verifyUser(userModal.getEmail());
        user.setPinCode(pinCode);
        userRepostory.save(user);
        emailSevice.mailSendTo(userModal.getEmail(), "verfiy you mail", pinCode.getPinCodeNumber()+"     "+"https://naa-anime-list-backend-api.onrender.com/animes/enterPin");
        return SignUpResponse.builder()
                .message("veyfi your mail")
                .url("https//localhost:8080/auth/verfiy")
                .build();
    }
    public AuthResponse authenicateUser(AuthRequest authRequest) throws UserNotVerfiedException {


        UserDetails userDetails=userDetailsService.loadUserByUsername(authRequest.getUserName());
        if(!userDetails.isEnabled()){
            throw new UserNotVerfiedException("user must verfiy there Email before logging in");
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword(), userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        log.info(authRequest.toString());
        return AuthResponse.builder()
                .token(jwtService.genrateToken(userDetails))
                .refreshToken(jwtService.genrateRefreshToken(userDetails))
                .build();
    }

    public PinCode verifyUser(String mail) {
        String pinCodeString=generatePinCode();
        return  PinCode.builder()
                .pinCodeNumber(pinCodeString).
                issuedAt(LocalDateTime.now())
                .expiration(LocalDateTime.now().plusSeconds(300))
                .build();



    }

    private String generatePinCode() {
        String hash="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder pinCode= new StringBuilder();
        for(int i=0;i<6;i++){
            SecureRandom secureRandom=new SecureRandom();
            int randNumber=secureRandom.nextInt(58);
            pinCode.append(hash.charAt(randNumber));
        }
        return pinCode.toString();
    }
    public String upDatePassword(String email, String password) {
        Query query=new Query(Criteria.where("email").is(email));
        Update update=new Update().set("password", passwordEncoder.encode(password));
        mongoTemplate.updateFirst(query, update, User.class);
        return "password Updated succefully";
    }

    public AuthResponse newToken(String token) {
        return jwtService.newTokenGenrate(token);
    }
}
