package com.example.demo.controllers;

/*
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
*/
import com.example.demo.exeption.StudentAlreadyRegisterException;
import com.example.demo.exeption.StudentNotFoundException;
import com.example.demo.exeption.UsernameAlreadyTakenException;
import com.example.demo.models.users.Account;
import com.example.demo.services.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
/*
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
*/
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/admin/getAccount")
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok().body(accountService.getAccounts());
    }

    @PostMapping("/admin/addRoleToAccount")
    public ResponseEntity<?> addRoleStudentToAccount(@RequestBody RoleToAccountForm form) {
        form.setRoleName("ROLE_STUDENT");
        accountService.addRoleToAccount(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/account/save")
    public ResponseEntity<Account> saveAccount(@RequestBody Account account) {
        ResponseEntity<Account> response;
        Account newAccount;
        try {
            newAccount = accountService.saveStudentAccount(account);
            response = ResponseEntity.ok().body(newAccount);
        } catch (StudentNotFoundException ex) {
            response = ResponseEntity.status(513).build();
        } catch (StudentAlreadyRegisterException ey) {
            response = ResponseEntity.status(514).build();
        } catch (UsernameAlreadyTakenException ez) {
            response = ResponseEntity.status(515).build();
        }
        return response;
    }

//    static class LoginRequest {
//        public String username;
//        public String password;
//
//        public LoginRequest(String username, String password) {
//            this.username = username;
//            this.password = password;
//        }
//    }
//
//    @PostMapping("/account/login")
//    public void loginAccount(@RequestBody LoginRequest loginRequest) {
//        log.info(loginRequest.username);
//        log.info(loginRequest.password);
//
//        String username = loginRequest.username;
//
//        //Abbiamo finalmente i dati inseriti nel form di login
//
//        try {
//            UserDetails userDetails = accountService.loadUserByUsername(username);
//            System.out.println(userDetails);
//
//            SimpleGrantedAuthority authorityStudent = new SimpleGrantedAuthority("ROLE_STUDENT");
//            SimpleGrantedAuthority authorityAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");
//
//            if (userDetails.getAuthorities().contains(authorityStudent)){
//                System.out.println("Sono uno studente");
//                // Vado alla pagina di feedback
//
//            } else if (userDetails.getAuthorities().contains(authorityAdmin)) {
//                System.out.println("Sono un amministratore");
//                // Vado alla pagina admin
//
//            }
//
//        } catch (UsernameNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//
//
//
//        //Ora dobbiamo fare il login
//
//
//    }
//

//    @GetMapping("token/refresh")
//    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        //Controllo se abbiamo l'header di autorizzazione all'interno della richiesta da parte del client
//        String authorizationHeader = request.getHeader(AUTHORIZATION);
//
//        //Controllo che non sia null e che inizi con "Bearer ".
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            try {
//                //Rimuovo la stringa iniziale "Bearer "
//                String refreshToken = authorizationHeader.substring("Bearer ".length());
//                //Ottengo l'algoritmo con usando la stessa chiave
//                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//                //Ottengo il verificatore per verificare il Token
//                JWTVerifier verifier = JWT.require(algorithm).build();
//                //Decodifico il Token
//                DecodedJWT decodedJWT = verifier.verify(refreshToken);
//                //Ottengo l'username dal Token decodificato
//                String username = decodedJWT.getSubject();
//                //Trovo l'account vero e proprio con quell'username dal db
//                Account account = accountService.getAccountByUsername(username);
//
//                String accessToken = JWT.create()
//                        .withSubject(account.getUsername()) //Utilizzo l'username per creare il token
//                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) //Imposto 10 minuti di validità del Token
//                        .withIssuer(request.getRequestURL().toString())
//                        .withClaim("roles", account.getRole().toString()) //Imposto il ruolo
//                        .sign(algorithm); //Genero la firma con l'algoritmo
//
//
//                Map<String, String> tokens = new HashMap<>();
//                tokens.put("access_token", accessToken);
//                tokens.put("refresh_token", refreshToken);
//
//                response.setContentType(APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
//
//            } catch (Exception e) {
//                response.setHeader("error", e.getMessage());
//                response.setStatus(FORBIDDEN.value());
////                    response.sendError(FORBIDDEN.value());
//                Map<String, String> error = new HashMap<>();
//                error.put("error_message", e.getMessage());
//                response.setContentType(APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(), error);
//            }
//        } else {
//            throw new RuntimeException("Refresh token is missing");
//
//        }
//    }

}






