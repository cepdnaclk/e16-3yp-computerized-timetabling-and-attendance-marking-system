package Group10.example.API.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AuthenticationServiceProvider implements AuthenticationProvider {

    @Autowired
    StudentDetailsService studentDetailsService;

    @Autowired
    AdminDetailsService adminDetailsService;

    @Autowired
    LecturerDetailsService lecturerDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken = null;
        String userName = authentication.getName();
        String password=authentication.getCredentials().toString();

        StudentDetails stud = (StudentDetails) studentDetailsService.loadUserByUsername(userName);
        AdminDetails admin = (AdminDetails) adminDetailsService.loadUserByUsername(userName);
        LecDetails lec = (LecDetails) lecturerDetailsService.loadUserByUsername(userName);

        System.out.println(userName);

        if(stud!=null){

            if (stud.getUsername().equals(userName) && BCrypt.checkpw(password,stud.getPassword())){
                Collection<? extends GrantedAuthority> auth =  stud.getAuthorities();
                authenticationToken =new UsernamePasswordAuthenticationToken(userName,password,auth);

            }
            else if (!BCrypt.checkpw(password,stud.getPassword())){
                throw new AuthenticationException("Incorrect Password") {
                    @Override
                    public String getMessage() {
                        return super.getMessage();
                    }
                };
            }

        } else if(admin!=null){


            if (admin.getUsername().equals(userName) && BCrypt.checkpw(password,admin.getPassword())){
                Collection<? extends GrantedAuthority> auth = admin.getAuthorities();
                authenticationToken =new UsernamePasswordAuthenticationToken(userName,password,auth);

            }else if (!BCrypt.checkpw(password,admin.getPassword())){
                throw new AuthenticationException("Incorrect Password") {
                    @Override
                    public String getMessage() {
                        return super.getMessage();
                    }
                };
            }

        }else if(lec!=null){

            if (lec.getUsername().equals(userName) && BCrypt.checkpw(password,lec.getPassword())){
                Collection<? extends GrantedAuthority> auth =  lec.getAuthorities();
                authenticationToken =new UsernamePasswordAuthenticationToken(userName,password,auth);

            }else if (!BCrypt.checkpw(password,lec.getPassword())){
                throw new AuthenticationException("Incorrect Password") {
                    @Override
                    public String getMessage() {
                        return super.getMessage();
                    }
                };
            }

        }
        else{
            throw new UsernameNotFoundException("Could Not Find User");
        }
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
