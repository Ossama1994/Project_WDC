package com.youcode.wdcmanager.controller;

import com.youcode.wdcmanager.dto.auth.PersonInfoDto;
import com.youcode.wdcmanager.dto.auth.RegisterPersonDto;
import com.youcode.wdcmanager.entity.Person;
import com.youcode.wdcmanager.service.AuthService;
import com.youcode.wdcmanager.service.PersonService;
import com.youcode.wdcmanager.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController @RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final PersonService personService;
    private final AuthService authService;
    private final ModelMapper modelMapper;
    private final VerificationTokenService verificationTokenService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterPersonDto person) {
        Person mappedPerson = modelMapper.map(person, Person.class);
        return authService.register(mappedPerson);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Person person) {
        return ResponseEntity.ok(authService.login(person));
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(Principal principal){
        if(principal == null) ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        authService.logout(Long.valueOf(principal.getName()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/userinfo")
    public ResponseEntity<PersonInfoDto> userInfo(Principal principal){
        if(principal == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        Person user = personService.findById(principal.getName());
        PersonInfoDto personInfo = modelMapper.map(user, PersonInfoDto.class);
        return ResponseEntity.ok(personInfo);
    }

    @GetMapping("/confirm/{token}")
    public ResponseEntity<Object> confirmAccount(@PathVariable UUID token){
        if(verificationTokenService.isTokenValid(token)) return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

//    @PostMapping("/resendconfirm/{id}")
//    public void resendconfirm(@RequestParam Long id){
//        authService.sendRegisterConfirm(Person.builder().id(id).build());
//    }
}
