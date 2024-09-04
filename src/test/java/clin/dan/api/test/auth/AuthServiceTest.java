package clin.dan.api.test.auth;

import clin.dan.api.Features.Auth.AuthService;
import clin.dan.api.Features.User.AuthDataDTO;
import clin.dan.api.Features.User.UserModel;
import clin.dan.api.Infra.Security.DataTokenJwtDTO;
import clin.dan.api.Infra.Security.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private AuthenticationManager authManager;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AuthService authService;

    @Test
    void testDoLoginSuccess() {
        AuthDataDTO authDataDTO = new AuthDataDTO("user", "password");
        UserModel user = new UserModel();
        Authentication authentication = mock(Authentication.class);

        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        when(tokenService.generateToken(any(UserModel.class))).thenReturn("mockJwtToken");

        ResponseEntity<DataTokenJwtDTO> response = authService.doLogin(authDataDTO);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("mockJwtToken", response.getBody().token());

        verify(authManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenService, times(1)).generateToken(user);

    }
}
