package cord.eoeo.momentwo.user.adapter.in;

import cord.eoeo.momentwo.config.security.jwt.adapter.out.TokenResponseDto;
import cord.eoeo.momentwo.user.adapter.dto.in.RefreshTokenRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.in.SignOutRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.in.UserLoginRequestDto;
import cord.eoeo.momentwo.user.application.port.in.UserStatusUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class UserStatusController {

    private final UserStatusUseCase userStatusUseCase;

    // 로그인
    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<TokenResponseDto> signIn(@RequestBody @Valid UserLoginRequestDto userLoginRequestDto) {
        return userStatusUseCase.signIn(userLoginRequestDto);
    }

    // 로그아웃
    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(@RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        userStatusUseCase.blackListToken(jwtToken);
    }

    // 회원 탈퇴
    @DeleteMapping("/signout")
    @ResponseStatus(HttpStatus.OK)
    public void signOut(@ModelAttribute @Valid SignOutRequestDto signOutRequestDto) {
        userStatusUseCase.signOut(signOutRequestDto);
    }

    // 토큰 재발급
    @PostMapping("/reissue")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponseDto reissue(@ModelAttribute @Valid RefreshTokenRequestDto refreshTokenRequestDto) {
        return userStatusUseCase.reissue(refreshTokenRequestDto);
    }
}
