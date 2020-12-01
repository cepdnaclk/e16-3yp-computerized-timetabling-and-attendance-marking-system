package Group10.example.API.Service;

import Group10.example.API.Model.Lecturer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LecDetails implements UserDetails {

    private Lecturer lecturer;

    public LecDetails(Lecturer lecturer){
        this.lecturer = lecturer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = lecturer.getRole();
        List<SimpleGrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(role));
        return auth;
    }

    @Override
    public String getPassword() {
        return lecturer.getPassword();
    }

    @Override
    public String getUsername() {
        return lecturer.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
