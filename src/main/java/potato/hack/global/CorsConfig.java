package potato.hack.global;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() { //CorsConfigurationSource 인터페이스 구현
        CorsConfiguration configuration = new CorsConfiguration(); // Bean 생성
        configuration.setAllowedOriginPatterns(Arrays.asList("*")); //요청을 보내는 모든 도메인 허용
        configuration.setAllowedMethods(
                Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS")); //허용할 요청 메소드 설정

        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type")); //요청 허용할 헤더
        configuration.setAllowCredentials(true); //인증 정보를 담은 요청도 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); //CorsConfiguration 객체를 URL 패턴에 매핑하여 CORS 구성을 지원하는 클래스
        source.registerCorsConfiguration("/**", configuration); //모든 경로에 대한 CORS 설정을 위에서 만든 CorsConfiguration 객체로 등록

        return source;
    }
}
