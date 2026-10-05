package lk.bookbarlibrarymember.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //request filter
        http.authorizeHttpRequests(
                (authrequest) -> {
                    authrequest
                            .requestMatchers("/","/aboutus", "/librarypolicy", "/membershiptype/valid" ,"/adultmembershiptypes","/adultmembershiptypes/valid","/childmembershiptypes","/childmembershiptypes/valid", "/opac", "/events", "/contactus", "/login","/library.png").permitAll()
                            .requestMatchers("/book/titles","/book/authors","/book/isbns","/book/issns", "/book/series","/book/displaycategories").permitAll()
                            .requestMatchers("/bookcopies/**", "/shelflocations/**").permitAll()
                            .requestMatchers("/dashboard/**").hasAnyAuthority("Member")
                            .requestMatchers("/resources/**","/controljs/**").permitAll()
                            .anyRequest().authenticated();
                }).formLogin((login) -> {
            login.loginPage("/login")
                    // login success
                    .defaultSuccessUrl("/dashboard", true)
                    // login failure
                    .failureUrl("/login?error=usernamepassworderror")
                    .usernameParameter("username")
                    .passwordParameter("password");
        }).logout((logout) -> {
            logout.logoutUrl("/logout")
                    .clearAuthentication(true)
                    // return to login page
                    .logoutSuccessUrl("/login");
        }).exceptionHandling((error) -> {
            error.accessDeniedPage("/errorpage");

            // csrf disable- cross site request forgery
            // all request handle for crud operations by js. only browser url is using for ui.
            // then default csrf is enabled. so we can't acess data by js.
            // we need to access data request in js file. so we disabled csrf.
            // when csrf enabled we can't access third party tools also
        }).csrf((csrf) -> {
            csrf.disable();
        });
        return http.build();


    }
    // password encorder isnstance or bean
    // one way encription. cannot decrypt
    // there is option to match encrypted password and user entered password
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
