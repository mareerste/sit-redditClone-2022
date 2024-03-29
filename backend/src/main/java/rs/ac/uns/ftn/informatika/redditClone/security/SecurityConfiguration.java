package rs.ac.uns.ftn.informatika.redditClone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuthentication(
            AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {

        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean()
            throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter
                .setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        //Naglasavamo browser-u da ne cache-ira podatke koje dobije u header-ima
        //detaljnije: https://www.baeldung.com/spring-security-cache-control-headers
        httpSecurity.headers().cacheControl().disable();
        //Neophodno da ne bi proveravali autentifikaciju kod Preflight zahteva
        httpSecurity.cors();
        //sledeca linija je neophodna iskljucivo zbog nacina na koji h2 konzola komunicira sa aplikacijom
        httpSecurity.headers().frameOptions().disable();
        httpSecurity.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET, "/user").permitAll()
                .antMatchers(HttpMethod.GET, "/user/username").permitAll()
                .antMatchers(HttpMethod.GET, "/user/{username}").permitAll()
                .antMatchers(HttpMethod.POST, "/user/login").permitAll()
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers(HttpMethod.GET, "/image").permitAll()
                .antMatchers(HttpMethod.POST, "/image").permitAll()
                .antMatchers(HttpMethod.GET, "/image/{path}").permitAll()
                .antMatchers(HttpMethod.GET, "/image/get").permitAll()
                .antMatchers(HttpMethod.GET, "/post").permitAll()
                .antMatchers(HttpMethod.GET, "/post/all").permitAll()
                .antMatchers(HttpMethod.GET, "/post/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/post/{id}/community").permitAll()
                .antMatchers(HttpMethod.GET, "/post/{id}/comments").permitAll()
                .antMatchers(HttpMethod.GET, "/post/title/{title}").permitAll()
                .antMatchers(HttpMethod.GET, "/post/flair/{flair}").permitAll()
                .antMatchers(HttpMethod.GET, "/post/text/{text}").permitAll()
                .antMatchers(HttpMethod.GET, "/post/range/{min}/{max}").permitAll()
                .antMatchers(HttpMethod.GET, "/post/comments/{min}/{max}").permitAll()
                .antMatchers(HttpMethod.GET, "/post/search/{searchType}/{title}/{text}/{min}/{max}").permitAll()
                .antMatchers(HttpMethod.GET, "/community/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/community/{id}/posts").permitAll()
                .antMatchers(HttpMethod.GET, "/community/name/{name}").permitAll()
                .antMatchers(HttpMethod.GET, "/community/description/{description}").permitAll()
                .antMatchers(HttpMethod.GET, "/community/rule/{rule}").permitAll()
                .antMatchers(HttpMethod.GET, "/community/karma/{min}/{max}").permitAll()
                .antMatchers(HttpMethod.GET, "/community/posts/{min}/{max}").permitAll()
                .antMatchers(HttpMethod.GET, "/community/search/{searchType}/{name}/{description}/{rules}/{minPost}/{maxPost}/{minKarma}/{maxKarma}").permitAll()
//                .antMatchers(HttpMethod.POST, "/community/{id}/posts").permitAll()//obrisati
                .antMatchers(HttpMethod.GET, "/reaction/post/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/reaction/post/{id}/karma").permitAll()
                .antMatchers(HttpMethod.GET, "/reaction/comment/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/reaction/comment/{id}/karma").permitAll()
                .antMatchers(HttpMethod.GET, "/reaction").permitAll()
                .anyRequest().authenticated();
        //hasAnyRole("USER","ADMIN")
        //hasRole("USER")
        //autorization("ROLE_USER")

        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

}
