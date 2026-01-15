package lu.cnfpcfullstackdev.tfl_api.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import lu.cnfpcfullstackdev.tfl_api.dto.request.RegisterRequestDTO;
import lu.cnfpcfullstackdev.tfl_api.dto.request.LoginRequestDTO;
import lu.cnfpcfullstackdev.tfl_api.dto.response.AuthResponseDTO;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import lu.cnfpcfullstackdev.tfl_api.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> registerUser(@RequestBody @Valid RegisterRequestDTO dto) {
        AuthResponseDTO response = authService.registerUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginUser(@RequestBody @Valid LoginRequestDTO dto) {
        AuthResponseDTO response = authService.loginUser(dto);
        return ResponseEntity.ok(response);
    }
    
}
