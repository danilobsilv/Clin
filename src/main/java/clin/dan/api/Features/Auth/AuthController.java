package clin.dan.api.Features.Auth;

import clin.dan.api.Features.User.AuthDataDTO;
import clin.dan.api.Infra.Security.DataTokenJwtDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping
    public ResponseEntity<DataTokenJwtDTO> doLogin(@RequestBody @Valid AuthDataDTO authDataDTO){
        return authService.doLogin(authDataDTO);
    }
}
