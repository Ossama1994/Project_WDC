package com.youcode.wdcmanager;

import com.youcode.wdcmanager.entity.*;
import com.youcode.wdcmanager.repository.PersonRepository;
import com.youcode.wdcmanager.repository.VerificationTokenRepository;
import com.youcode.wdcmanager.service.ParticipantService;
import com.youcode.wdcmanager.service.PersonService;
import com.youcode.wdcmanager.service.ResponsibleService;
import com.youcode.wdcmanager.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@SpringBootApplication
@RequiredArgsConstructor
public class WdcmanagerApplication {
	private final PersonService personService;
	private final RoleService roleService;
	private final VerificationTokenRepository verificationTokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final ResponsibleService responsibleService;
	private final ParticipantService participantService;


	public static void main(String[] args) {
		SpringApplication.run(WdcmanagerApplication.class, args);
	}

	@Bean
	public CommandLineRunner populateDatabase(){
		return args -> {
		System.out.println("------------------- Populating database... -------------------");
			Role admin = roleService.create(Role.builder().name("ADMIN").build());
		Role responsible = roleService.create(Role.builder().name("RESPONSIBLE").build());
		Role participant = roleService.create(Role.builder().name("PARTICIPANT").build());

		ResponsibleType responsibleType1 = ResponsibleType.builder().name("responsable 1").build();


		Person person1 = Person.builder().email("admin@gmail.com").password(passwordEncoder.encode("12345678")).username("admin").firstName("adminFirstName").lastName("adminLastNAme").phone("+212600000000").status(false).role(admin).birthday(LocalDate.now()).loginExpireAt(Instant.ofEpochMilli(1643985906L)).gender(PersonGender.MALE).build();
		Participant participant1 = Participant.builder().domain("IT").email("participant@gmail.com").password(passwordEncoder.encode("12345678")).username("participant").firstName("participantFirstName").lastName("participantLastNAme").phone("+212600000000").status(true).role(participant).birthday(LocalDate.now()).loginExpireAt(Instant.ofEpochMilli(1643985906L)).gender(PersonGender.FEMALE).build();
		Responsible responsible1 = Responsible.builder().type(responsibleType1).domain("Management").email("responsible@gmail.com").password(passwordEncoder.encode("12345678")).username("responsible").firstName("responsibleFirstName").lastName("responsibleLastNAme").phone("+212600000000").status(true).role(responsible).birthday(LocalDate.now()).loginExpireAt(Instant.ofEpochMilli(1643985906L)).gender(PersonGender.MALE).build();


			System.out.println("------------------------------------");
			System.out.println(responsible1);

		personService.create(person1);
		participantService.create(participant1);
		responsibleService.create(responsible1);

		VerificationToken verificationToken1 = VerificationToken.builder()
				.token(UUID.randomUUID())
				.person(person1)
				.expireAt(LocalDateTime.now().plusDays(1))
				.build();
		verificationTokenRepository.save(verificationToken1);

		System.out.println("--------------------------------------------------------------");
		};
	}

}