package bd.edu.seu.retakequiz4.security;

import bd.edu.seu.retakequiz4.model.AppUser;
import bd.edu.seu.retakequiz4.repo.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MongoUserDetailsService implements UserDetailsService {

    private final UserRepository repo;

    public MongoUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser u = repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Not found"));

        var auths = u.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                .collect(Collectors.toList());

        return new User(u.getUsername(), u.getPasswordHash(), auths);
    }
}
