# Refresh token

```java
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false, name="user_id")
    private Long userId;

    private Date expiryDate;
}
```

```java
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUserId(String userId);
}
```

```java
@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public String createRefreshToken(Long userId) {
        RefreshToken tokenEntity = new RefreshToken();
        tokenEntity.setToken(UUID.randomUUID());
        tokenEntity.setUserId(userId);
        tokenEntity.setExpiryDate(new Date(System.currentTimeMillis() + jwtExpiration));

        refreshTokenRepository.save(tokenEntity);
        return refreshToken;
    }

    /**
     * Проверяет валидность refresh token (существует, не истек, не отозван).
     */
    public RefreshToken verifyRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        if (refreshToken.getExpiryDate().before(new Date())) {
            throw new RuntimeException("Refresh token expired");
        }
        return refreshToken;
    }

    /**
     * Удаляет старый refresh token (ротация).
     */
    public void deleteRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
                .ifPresent(refreshTokenRepository::delete);
    }

    /**
     * Отзывает все refresh token пользователя (при смене пароля, глобальном выходе).
     */
    public void revokeAllUserTokens(String userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }
}
```

```java
    String refreshToken = refreshTokenService.createRefreshToken(userId, userAgent);
    
    // Устанавливаем refresh token в HttpOnly cookie
    Cookie cookie = new Cookie("refreshToken", refreshToken);
    cookie.setHttpOnly(true);
    //cookie.setSecure(true); // только HTTPS
    cookie.setPath("/api/auth/refresh");
    cookie.setMaxAge(30 * 24 * 60 * 60); // 30 дней
    cookie.setAttribute("SameSite", "Strict");
    response.addCookie(cookie);
```

```java
 @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(name = "refreshToken", required = false) String refreshToken,
                                    HttpServletResponse response) {
        if (refreshToken != null) {
            refreshTokenService.deleteRefreshToken(refreshToken);
        }
        // Удаляем cookie
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setMaxAge(0);
        cookie.setPath("/api/auth/refresh");
        response.addCookie(cookie);
        return ResponseEntity.ok("Logged out");
    }
```

```js
async function apiRequest(url, options = {}) {
    // Перехватчик запросов
    let response = await fetch(url, {
        ...options,
        headers: {
            ...options.headers,
            'Authorization': `Bearer ${getAccessTokenFromMemory()}`
        }
    });

    // Если Unauthorized
    if (response.status === 401) {
        // Пытаемся обновить токен
        const refreshResponse = await fetch('/api/refresh', {
            method: 'POST',
            credentials: 'include' // Чтобы отправились cookies
        });
        
        if (refreshResponse.ok) {
            const { accessToken } = await refreshResponse.json();
            saveAccessTokenToMemory(accessToken);
            // Повторяем исходный запрос с новым токеном
            return fetch(url, {
                ...options,
                headers: {
                    ...options.headers,
                    'Authorization': `Bearer ${accessToken}`
                }
            });
        } else {
            // Если обновить не удалось -> редирект на логин
            window.location.href = '/login';
        }
    }
    return response;
}
```