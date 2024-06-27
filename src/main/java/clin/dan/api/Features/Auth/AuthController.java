package clin.dan.api.Features.Auth;

import clin.dan.api.Features.Usuario.DadosAuthDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAuthDTO dadosAuthDTO){
        var token = new UsernamePasswordAuthenticationToken(dadosAuthDTO.login(), dadosAuthDTO.senha());
        var auth = authManager.authenticate(token);

        return ResponseEntity.ok().build();

    }
}
