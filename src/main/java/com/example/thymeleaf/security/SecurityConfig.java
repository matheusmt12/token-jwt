package com.example.thymeleaf.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private JwtAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	@Bean
	public SecurityFilterChain config (HttpSecurity http) throws Exception{
		
	      http
	        .csrf(csrf->csrf.disable())
	        .authorizeHttpRequests(request -> request
	                
	                .requestMatchers("/login","/").permitAll()
	                .requestMatchers("/sing-up").permitAll()
                    .requestMatchers(HttpMethod.GET,"/modelo").permitAll()
                    .requestMatchers("access/login").permitAll()
                    .requestMatchers("teste").permitAll()
	                .anyRequest().authenticated())
            // .exceptionHandling(exception -> exception
            //     .accessDeniedHandler(accessDeniedHandler)
            //     .authenticationEntryPoint(authenticationEntryPoint)
            // )
	        .sessionManagement((session)-> session
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
            
            

	      return http.build();
	}

        // Configure o filtro para verificar o token
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, userDetailService);
    }



	   @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:5173")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true);
            }
        };
    }
	
}
