package mx.com.sfinx.secure_module.monitor.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    companion object {
        private val AUTH_WHITELIST = arrayOf(
            "/css/**",
            "/js/**",
            "/img/**",
        )
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers(*AUTH_WHITELIST).permitAll()
            .antMatchers("/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .permitAll()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
        val userDetails = User.withDefaultPasswordEncoder()
            .username("sfinx")
            .password("1q2w3e4r5T")
            .roles("ADMIN")
            .build()

        return InMemoryUserDetailsManager(userDetails)
    }
}