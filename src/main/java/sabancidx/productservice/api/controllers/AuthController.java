package sabancidx.productservice.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sabancidx.productservice.business.concretes.RoleService;
import sabancidx.productservice.business.concretes.UserService;
import sabancidx.productservice.entities.concretes.Role;
import sabancidx.productservice.entities.concretes.RoleName;
import sabancidx.productservice.entities.concretes.User;
import sabancidx.productservice.payload.LoginRequest;
import sabancidx.productservice.payload.SignUpRequest;
import sabancidx.productservice.security.JwtTokenProvider;


import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    final
    private AuthenticationManager authenticationManager;
    final
    private UserService userService;
    final
    private RoleService roleService;
    final
    private PasswordEncoder passwordEncoder;
    final
    private JwtTokenProvider tokenProvider;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, RoleService roleService, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (Boolean.TRUE.equals(userService.existsUser(signUpRequest.getUsername(), "userName"))) {
            return ResponseEntity.badRequest().body("User name zaten kullanımda");
        }

        if (Boolean.TRUE.equals(userService.existsUser(signUpRequest.getEmail(), "email"))) {
            return ResponseEntity.badRequest().body("Email adresi zaten kullanımda");
        }

        User user = new User(signUpRequest.getName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword())); // burdaki passwordEncoder benim encoderım
        Role userRole = roleService.getRoleByName(RoleName.ROLE_USER);

        user.setRoles(Collections.singleton(userRole));
        User result = userService.userSave(user);

        return ResponseEntity.ok().body("User registered successfully");
    }
}
