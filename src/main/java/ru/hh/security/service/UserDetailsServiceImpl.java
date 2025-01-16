package ru.hh.security.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hh.security.dto.UserRegistrationDto;
import ru.hh.security.model.Authority;
import ru.hh.security.model.User;
import ru.hh.security.model.UserDetailsImpl;
import ru.hh.security.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  public void save(UserRegistrationDto registrationDto) {
    User user = new User(registrationDto.getLogin(), passwordEncoder.encode(registrationDto.getPassword()), Authority.USER.getRole());
    userRepository.save(user);
  }

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByLogin(username);
    return user.map(UserDetailsImpl::new)
        .orElseThrow(() -> new UsernameNotFoundException(username + " not found in repo."));
  }

}