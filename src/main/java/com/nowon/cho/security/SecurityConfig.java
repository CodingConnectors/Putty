package com.nowon.cho.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

	/*
	 * 패스워드 저장시 암호화를 해주는 메서드
	 * 회원가입 Service에서 사용 할 수 있도록 Bean에 등록
	 * return 객체에 있는 생성자는 강도를 나타냄 Spring.io에서는 10 이상을 권장 
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(18);
	}
	
	
	/*
	 * 로그인 시 입력한 username이 DB에 있는 username과 같은지 인증하는 메서드
	 * DB에 같은 username이 있다면 DB에서 해당하는 객체를 생성(username, password, roll)하고 반환
	 */
	@Bean 
	MemberDetailsService memberDetailsService() { return new MemberDetailsService(); }
	 
	
	
	/* 
	 * .authorizeHttpRequests를 통해서 인증없이 접근할 수 있는 페이지, css, js, images,를 지정
	 * .formLogin를 통해서 인증이 필요한 페이지 접근 시 로그인 페이지로 유도
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
					
					.antMatchers("/**").permitAll()
				/*
				 * .antMatchers("/css/**","/img/**","/js/**").permitAll()//접근가능
				 * .antMatchers("/","/signin","/signup","/find_email","/find_email_after",
				 * "/find_password","/find_password_after").permitAll()//접근가능
				 * .antMatchers("/basket").permitAll()//접근가능
				 */					
				/*
				 * //유저권한 접근 .antMatchers("/member/**").hasAnyRole("USER") //관리자권한 접근
				 * .antMatchers("/admin/**").hasAnyRole("ADMIN")
				 * 
				 * .anyRequest().authenticated()
				 *///나머지는 인증(로그인)해야해요
					)
			//.formLogin(Customizer.withDefaults())
			
			/*
			 * .formLogin(formLogin -> formLogin .loginPage("/signin")
			 * .loginProcessingUrl("/signin") .usernameParameter("email")
			 * //default=username--form .passwordParameter("password")
			 * //default=password--form .permitAll() )
			 */
					
		;
		return http.build();
	}
	
}
