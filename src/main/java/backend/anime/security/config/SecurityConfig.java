package backend.anime.security.config;
import backend.anime.security.jwt.JwtFilter;
import backend.anime.security.oathtwo.Oauth2SucsessHandlerimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    Oauth2SucsessHandlerimpl oauth2SucsessHandlerimpl;
    @Autowired
    AuthenticationProvider authenticationProvider;
    @Autowired
    JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth->auth.requestMatchers("/animes/*/*", "/auth/*/*")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .authenticationProvider(authenticationProvider);
        ;
        //jwt filter
        httpSecurity.
                addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        //login form
        httpSecurity
                .formLogin(Customizer.withDefaults());

//        httpSecurity.formLogin(login -> login.successForwardUrl("/animes/"));

//        //login form
//        httpSecurity
//                .formLogin(
//                        login->
//                                login
//                                        .loginPage("/auth/loginCustom")
//                                        .loginProcessingUrl("/auth/loginUser")
//                );

        //oauth2
        httpSecurity
                .oauth2Login(Customizer.withDefaults());
        httpSecurity
                .oauth2Login(oauth->{
                    oauth.successHandler(oauth2SucsessHandlerimpl);
                });
        return httpSecurity.build();
    }
}
