package com.example.hobby.service.impl;
import com.example.hobby.model.entity.User;
import com.example.hobby.model.entity.UserRole;
import com.example.hobby.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HobbyUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public HobbyUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + s + " not found"));
        return mapToUserDetails(user);
    }

    private static UserDetails mapToUserDetails(User user) {
        List<GrantedAuthority> grantedAuthorities =
                user.
                        getRoles().
                        stream().
                        map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name())).
                        collect(Collectors.toList());

        return new HobbyUserImpl(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities
        );
    }
    private UserPrincipal getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserPrincipal)authentication.getDetails();
    }


}
