package com.admin.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.dto.AdminDto;
import com.admin.dto.LoginRequest;
import com.admin.entities.Admin;
import com.admin.repository.AdminRepository;
import com.admin.response.JwtResponse;
import com.admin.response.MessageResponse;
import com.admin.security.jwt.JwtUtils;
import com.admin.security.services.UserDetailsImpl;
import com.admin.services.AdminServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/Admin/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	AdminServiceImpl adminServiceImpl;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getAdminId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail() 
												 ));
	}
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody Admin signUpRequest) {
		if (adminRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		if (adminRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		Admin admin = new Admin(signUpRequest.getUsername(),encoder.encode(signUpRequest.getPassword()),
				 signUpRequest.getEmail());

		adminRepository.save(admin);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	 @PostMapping("/changePassword")
	    public String changePassword(@Valid @RequestBody AdminDto adminDto) {
	        return adminServiceImpl.changePassword(adminDto.getUserName(), adminDto.getOldPassword(), adminDto.getNewPassword());
	    }
}
