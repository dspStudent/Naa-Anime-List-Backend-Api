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
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

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

                .cors(cors->cors.configurationSource(configuration()))

                .authorizeHttpRequests(
                        auth->auth.requestMatchers("/animes/**","auth/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .authenticationProvider(authenticationProvider);
        ;


        //jwt filter
        httpSecurity.
                addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//        login form
        httpSecurity
                .formLogin(login->
                        login.loginPage("https://anime-html-13pe.vercel.app").loginProcessingUrl("/auth/loginUser"));


//        oauth2
        httpSecurity
                .oauth2Login(outh->outh.loginPage("/auth/loginCustom"));
        httpSecurity
                .oauth2Login(oauth->{
                    oauth.successHandler(oauth2SucsessHandlerimpl);
                });
        return httpSecurity.build();

    }
    @Bean
    public  CorsConfigurationSource configuration(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("https://anime-html-13pe.vercel.app")); // Replace with your exact frontend origin
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow common HTTP methods
        config.setAllowedHeaders(List.of("Content-Type", "Authorization", "X-Requested-With")); // Allow necessary headers
        config.setAllowCredentials(true); // Allow sending cookies if needed (be cautious)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Apply CORS configuration to all paths
        return source;
    }
}
