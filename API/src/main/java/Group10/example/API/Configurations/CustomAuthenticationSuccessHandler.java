package Group10.example.API.Configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String url="";

        if(response.isCommitted()){
            return;
        }

        Collection<? extends GrantedAuthority> auth = authentication.getAuthorities();
        List <String> roles = new ArrayList<String>();
        for(GrantedAuthority a: auth){
            roles.add(a.getAuthority());
        }

        if(roles.contains("ADMIN")){
            url = "/admin";
        }
        else if(roles.contains("STUDENT")){
            url = "/student";
        }
        else if(roles.contains("LECTURER")){
            url = "/lecturer";
        }
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request,response,url);
    }
}
