package board.board;

import board.board.interceptor.LoginInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BoardApplication implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new LoginInterceptor())
//				.order(1)
//				.addPathPatterns("/**")
//				.excludePathPatterns("/css/**", "/board/{[0-9]}", "/members/**", "/login/**", "/logout", "/");
	}



	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}


}
