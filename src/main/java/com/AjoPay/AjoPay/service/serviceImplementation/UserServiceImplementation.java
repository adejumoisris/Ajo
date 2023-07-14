package com.AjoPay.AjoPay.service.serviceImplementation;

import com.AjoPay.AjoPay.dto.request.EmailDto;
import com.AjoPay.AjoPay.dto.request.UserRequestDto;
import com.AjoPay.AjoPay.dto.response.UserResponse;
import com.AjoPay.AjoPay.exceptions.CustomException;
import com.AjoPay.AjoPay.exceptions.ResourceNotFoundException;
import com.AjoPay.AjoPay.exceptions.UserNotFoundException;
import com.AjoPay.AjoPay.model.User;
import com.AjoPay.AjoPay.model.VerificationToken;
import com.AjoPay.AjoPay.repository.UserRepo;
import com.AjoPay.AjoPay.repository.VerificationTokenRepository;
import com.AjoPay.AjoPay.service.SendMailService;
import com.AjoPay.AjoPay.service.UserService;
import com.auth0.jwt.interfaces.Verification;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService, UserDetailsService {
    private final SendMailService sendMailService;
    private final UserRepo userRepo;
    private final VerificationTokenRepository verificationTokenRepository;
     private final PasswordEncoder passwordEncoder;
    // construction injection
    @Override
    public UserResponse createUser(UserRequestDto request) {

        log.info("service:: about setting");
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        user.setEmail(request.getEmail());
        user.setGender(request.getGender());
        user.setUsername(request.getUsername());
        user.setRole(request.getRole());
        user.setPhoneNumber(request.getPhoneNumber());
        log.info("about saving");

        User saveUser = userRepo.save(user);
        log.info("save user");
        return new UserResponse(
                saveUser.getFirstName(),
                saveUser.getLastName(),
                saveUser.getEmail(),
                saveUser.getPassword()

                );
    }

    @Override
    public UserResponse fetchUserById(Long userId) {
        User saveUser = userRepo.findById(userId).get();
        return new UserResponse(saveUser.getFirstName(), saveUser.getLastName(), saveUser.getEmail(), saveUser.getPassword());
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepo.deleteById(userId);

    }

    @Override
    public UserResponse UpdateUserById(Long userId, UserRequestDto request) {
        User userToUpdate = userRepo.findById(userId).get();
        userToUpdate.setFirstName(request.getFirstName());
        userToUpdate.setLastName(request.getLastName());
        userToUpdate.setPassword(request.getPassword());

        User saveUser = userRepo.save(userToUpdate);

        return new UserResponse(
                saveUser.getFirstName(),
                saveUser.getLastName(),
                saveUser.getEmail(),
                saveUser.getPassword()
                );

    }



    // <<<<<<----------Send Registration Confirmation to mail ---------------->>>>>>>>>>>>>>
    private  void SendRegistrationConfirmationToEmail(User user, String email){
        String token = generateVerificationToken(user);
        sendMailService.sendMail(EmailDto.builder()
                .sender("idrisadejumo@gmail.com")
                .subject("please activate your account ")
                .body("Thank you for creating account with us "+
                        "Please click the link to activate your account with us :"+
                        "http://localhost:2020/user/verify-account/" + token)
                .recipient(email)
                .build());



    }

    private String generateVerificationToken(User user){
        log.info("inside generateVerificationToken, generate Token fo {}", user.getEmail());
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);

        log.info("saving token to database");
        verificationTokenRepository.save(verificationToken);
        return token;

    }

    @Override
    public String verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken.isPresent()) {
            Boolean isVerified = verificationToken.get().getUser().getIsVerified();
            if (isVerified) {
                throw new CustomException("token used, please request another activation token", HttpStatus.BAD_REQUEST);
            }
            return fetchUserAndEnable(verificationToken.get());
        }
        throw new CustomException("token invalid");
    }


    private String fetchUserAndEnable(VerificationToken verificationToken) {
        User user = verificationToken.getUser();
        if (user == null) {
            throw new ResourceNotFoundException("User with token not found");
        }
        user.setIsVerified(true);
        userRepo.save(user);
        return "Account verified successfully";
    }


    @Override
    public List<User> fetAllUsers() {
        return userRepo.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("userService loadUserByUserName -email :: [{}] ::", email);
        User user = userRepo.getByusername(email)
                .orElseThrow(
                        ()->{
                            throw new CustomException("incorrect username");
                        }
                );
        Collection<SimpleGrantedAuthority> authorities =
                Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }


    // creating all users




}
