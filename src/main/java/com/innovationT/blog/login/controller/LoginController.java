package com.innovationT.blog.login.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innovationT.blog.common.enumpkg.ServiceResult;
import com.innovationT.blog.member.entity.Member;
import com.innovationT.blog.member.service.MemberService;
import com.innovationT.blog.security.auth.MemberPrincipalDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
	
	private final MemberService service;
	
	private final PasswordEncoder passwordEncoder;
	
    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        
        log.info("인증 권한 관련 데이터 확인 : {}",authentication);
        
        return authentication.isAuthenticated();
    }
    
    
    @GetMapping("/") 
    public String welcomeLoginPage() {
    	return "login/login";
    }
    
    @GetMapping("/member/login/loginForm")
    public String login(HttpServletRequest request,
                        @AuthenticationPrincipal MemberPrincipalDetails memberPrincipalDetails) {
        HttpSession session = request.getSession();
        String msg = (String) session.getAttribute("loginErrorMessage");
        session.setAttribute("loginErrorMessage", msg != null ? msg : "");

        if(isAuthenticated()) {
            if(memberPrincipalDetails == null)
                return "redirect:/login/logout";
            return "redirect:/board/boardList";
        }
        return "login/login";
    }

    
    @GetMapping("/login/mypage")
    public String text(@AuthenticationPrincipal MemberPrincipalDetails memberPrincipalDetails
                        ,Model model) {

        Member member = memberPrincipalDetails.getMember();

        model.addAttribute("member", member);
        return "text/text";
    }
    
    @GetMapping("/login/signUpForm")
    public String signUpForm() {
    	
        return "login/signup";
    }
    
    @PostMapping("/login/signup")
    @ResponseBody
    public HashMap<String, Object> signUp(
    	 @RequestBody Member member
    	) {
    	
    	ServiceResult res;
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	try {
    		 res = service.createNewUser(member);
    		 
    		 if(res.equals(ServiceResult.OK)) {
    			 map.put("result",res);
    			 map.put("message","회원가입 등록 성공 ");
    		 }else if(res.equals(ServiceResult.DUPLICATED)) {
    			 map.put("result",res);
    			 map.put("message","중복된 아이디입니다. ");
    		 }else {
    			 map.put("result",res);
    			 map.put("message","회원가입 등록 실패 "); 
    		 }
    		 
		} catch (Exception e) {
			e.printStackTrace();
		}

    	return map;
    }
}