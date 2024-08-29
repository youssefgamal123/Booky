package com.Application.Booky.Security;


import com.Application.Booky.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {


    private UserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return repository.findByemail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }




}
