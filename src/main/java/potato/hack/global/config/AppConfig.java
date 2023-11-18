package potato.hack.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    /**
     * 회원가입 시, 비밀번호 처리를 위해 BCryptPasswordEncoder를 반환하는 passwordEncode()를 bean으로 등록한다.
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() { //패스워드 암호호 인코더
        return new BCryptPasswordEncoder();
    }
}
