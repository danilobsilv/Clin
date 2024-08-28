package clin.dan.api.Features.Auth;

import clin.dan.api.Features.User.AuthDataDTO;
import clin.dan.api.Features.User.UserModel;
import clin.dan.api.Infra.Security.DataTokenJwtDTO;
import clin.dan.api.Infra.Security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    public ResponseEntity<DataTokenJwtDTO> doLogin(@RequestBody @Valid AuthDataDTO authDataDTO){
        var authToken = new UsernamePasswordAuthenticationToken(authDataDTO.login(), authDataDTO.password());

        var auth = authManager.authenticate(authToken);

        var jwtToken = tokenService.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.ok(new DataTokenJwtDTO(jwtToken));
    }

}
