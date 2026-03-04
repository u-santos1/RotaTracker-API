package RotaTrackerAPI.RotaTrackerAPI.controller;

import RotaTrackerAPI.RotaTrackerAPI.domain.User;
import RotaTrackerAPI.RotaTrackerAPI.dtos.requests.AuthenticationDTO;
import RotaTrackerAPI.RotaTrackerAPI.infra.security.TokenService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid AuthenticationDTO dto){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());

        var authentication = manager.authenticate(authenticationToken);

        var user = (User) authentication.getPrincipal();
        var tokenJWT = tokenService.gerarToken(user);

        return ResponseEntity.ok(tokenJWT);
    }
}
