/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package secretkey;

import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

/**
 *
 * @author pratham
 */
public class JwtUtil {
    private static final String SECRET = "my-secret-key-for-Human-resource-management-system";

    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static SecretKey getSecretKey() {
        return SECRET_KEY;
    }
}