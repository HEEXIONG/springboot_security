package com.springboot.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration // IoC 빈(bean)을 등록
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 됩니다.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화, prePostEnabled 어노테이션 활성화, 즉, 컨트롤러의 함수에 직접 권한 설정 하는 방법임. 특정 주소 접근시 권한 및 인증을 위한 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	// 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해 준다.
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

			http.csrf().disable();
			http.authorizeRequests()
			            // user로 권한없이 접근 시 403 접근 거부 에러 발생함
				.antMatchers("/user/**").authenticated()
				// manager로 권한없이 접근 시 403 접근 거부 에러 발생함
				.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
				// admin로 권한없이 접근 시 403 접근 거부 에러 발생함
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				.anyRequest().permitAll()
				.and()
				.formLogin()
				.loginPage("/loginForm")
				//login 주소가 호출이 되면 시큐리티가 낚아채서 로그인을 진행합니다.
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/");
	}
	
	
}
