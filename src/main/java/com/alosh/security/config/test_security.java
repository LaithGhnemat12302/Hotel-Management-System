package com.alosh.security.config;

public class test_security {
//      @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                //disables Cross-Site Request Forgery (CSRF) protection
//                .csrf(AbstractHttpConfigurer::disable)
//                // permit public access to specific endpoints
//                .authorizeHttpRequests(req ->
//                        req.requestMatchers(WHITE_LIST_URL)
//                                .permitAll()
//                                .requestMatchers(PUT,"/api/v1/reservations").permitAll()
////                                .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), CUSTOMER_READ.name())
////                                .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), CUSTOMER_CREATE.name())
////                                .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), CUSTOMER_UPDATE.name())
////                                .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), CUSTOMER_DELETE.name())
//                                .anyRequest()
//                                //users must be authenticated to access these resources
//                                .authenticated()
//                )
//                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
//               // specifies the user details service and password encoder for authentication.
//                .authenticationProvider(authenticationProvider)
//                //adds the JwtAuthFilter before the UsernamePasswordAuthenticationFilter.
//                // This filter is responsible for processing JWT tokens and authenticating users.
//                //add the JwtAuthenticationFilter before the UsernamePasswordAuthenticationFilter.
//                // The JwtAuthenticationFilter is a custom filter that is responsible for processing JWT tokens and authenticating users.
//                // By adding it before the UsernamePasswordAuthenticationFilter,
//                // the JwtAuthenticationFilter will be executed first when a request is made to the application.
//                //The UsernamePasswordAuthenticationFilter is a default filter provided by Spring Security that is responsible for authenticating users based on their username and password. By adding the JwtAuthenticationFilter before it, the application can first check if the incoming request contains a valid JWT token. If a valid token is found, the user will be authenticated using the token, and the UsernamePasswordAuthenticationFilter will not be executed. If no valid token is found, the UsernamePasswordAuthenticationFilter will be executed to authenticate the user using their username and password.
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .logout(logout ->
//                        logout.logoutUrl("/api/v1/auth/logout")
//                                .addLogoutHandler(logoutHandler)
//                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
//                )
//        ;
//
//        return http.build();
//    }
//}
}
