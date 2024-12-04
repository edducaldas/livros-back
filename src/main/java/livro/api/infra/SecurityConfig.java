package livro.api.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/api/autor/**").permitAll() // Permitir POST para /api/livros
                        .antMatchers("/api/livros/**").permitAll() // Permite todos os métodos para /api/livros
                        .antMatchers("/api/assuntos/**").permitAll() // Permite todos os métodos para /api/livros
                        .antMatchers("/api/relatorios/export").permitAll() // Libera acesso ao endpoint de exportação
                        .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Libera acesso ao Swagger
                        .anyRequest().authenticated() // Qualquer outra rota requer autenticação
                );
        return http.build();
    }


}
