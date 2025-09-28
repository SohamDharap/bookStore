package com.book.store.serviceImpl;

import com.book.store.beans.CreateAccountRequest;
import com.book.store.entity.Role;
import com.book.store.entity.User;
import com.book.store.repository.RolesDao;
import com.book.store.repository.UsersDao;
import com.book.store.service.AuthService;
import com.book.store.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    RolesDao roleRepository;

    @Autowired
    UsersDao userRepository;
    private final JwtService jwtService;


    @Override
    public User createAccount(CreateAccountRequest request) {
        // Check if user already exists
        if (userRepository.findByEmailOrMobileNo(request.getEmail(), request.getMobileNumber()).isPresent()) {
            throw new RuntimeException("User with this email or mobile number already exists.");
        }

        // Find the role (e.g., "CUSTOMER") from the database
        Role userRole = roleRepository.findByRoleName(request.getRoleName())
                .orElseThrow(() -> new RuntimeException("Role not found: " + request.getRoleName()));

        // Create the new user entity
        User newUser = new User();
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setMobileNo(request.getMobileNumber());
        newUser.setRole(userRole); // Associate the role

        // Save the new user to the database
        return userRepository.save(newUser);
    }

    @Override
    public String generateOtp(String email, String mobileNumber) {
        // Find user by whichever identifier is provided
        User user = userRepository.findByEmailOrMobileNo(email, mobileNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with the provided identifier."));

        // Generate a 6-digit OTP
        String otp = String.format("%06d", new Random().nextInt(999999));

        // Set OTP and expiry time (e.g., 5 minutes from now)
        user.setOtp(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));

        userRepository.save(user);

        return otp;
    }

    @Override
    public String verifyOtpAndLogin(String email, String mobileNumber, String otp) {
        User user = userRepository.findByEmailOrMobileNo(email, mobileNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with the provided identifier."));

        if (user.getOtp() == null || !user.getOtp().equals(otp)) {
            throw new RuntimeException("Invalid OTP provided.");
        }

        if (user.getOtpExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP has expired. Please request a new one.");
        }

        // OTP is valid, so we clear it from the database to prevent reuse
        user.setOtp(null);
        user.setOtpExpiry(null);
        userRepository.save(user);

        // User is authenticated, generate and return the JWT
        return jwtService.generateToken(user);
    }
}