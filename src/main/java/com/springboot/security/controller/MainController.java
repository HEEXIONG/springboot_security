package com.springboot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.security.model.User;
import com.springboot.security.repository.UserRepository;

@Controller // View를 리턴하는 역할을 함
public class MainController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder BCryptPasswordEncoder;
	
	// localhost:9008/
	// localhost:9008
	@GetMapping({ "", "/" })
	public String index() {
		// jsp 기본폴더 webapp/WEB-INF/jsp
		// 뷰리졸버 설정 : /WEB-INF/jsp/, .jsp(suffix) 생략가능!		
		return "index"; 
	}

	@GetMapping("/user")
	public @ResponseBody String user() {
		return "user";
	}
	
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}
	
	// Spring Security에서 우선적으로 인터셉트 실행됨.
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	
	@PostMapping("/join")
	public String join(User user) {
		System.out.println(user);  // Console 창에 출력된 user 내용 확인 바랍니다.
		user.setRole("ROLE_USER");
		String rawPassword = user.getPassword();
		String encPassword = BCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		userRepository.save(user); // 회원가입 잘됨. 비밀번호 : 1234 => 시큐리티로 로그인을 할 수 없음. 이유는 SecurityConfig 설정을 하지 않았음. 설정이 필요함.
		return "redirect:/loginForm";
	}

	@PreAuthorize("hasRole('ROLE_MANAGER')or hasRole('ROLE_ADMIN')") // @PreAuthorize :  : 컨트롤러에 어노테이션 직접 설정할 수 있으며, 여러개 시큐리티 적용할 때 유용한 어노테이션임.
	@GetMapping("/data")
	public @ResponseBody String data() {
		return "데이터 정보";
	}	

	@Secured("ROLE_ADMIN")  // @Secured : 컨트롤러에 어노테이션 직접 설정할 수 있으며, 특정 메소드 한개에 시큐리티 적용할때 유용한 어노테이션임.
	@GetMapping("/info")
	public @ResponseBody String info() {
		return "관리자 정보";
	}

}