package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




@Configuration
public class BcryptConfig  {

	
    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
   
   
    

}