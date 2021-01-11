package vip.zihen.spice.config.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;
import vip.zihen.spice.common.utils.UUIDUtil;
import vip.zihen.spice.common.constants.JwtConstants;

import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

    private static JwtProperties jwtProperties;

    public JwtUtils(JwtProperties jwtProperties) {
        JwtUtils.jwtProperties = jwtProperties;
    }

    private static String clearToken(String token) {
        if (token.startsWith("Bearer")) {
            token = token.replace("Bearer", "").trim();
        }
        return token;
    }

    /**
     * @desc   验证token，通过返回true
     * @create 2021-01-08
     * @params [token]需要校验的串
     **/
    public static boolean verifyToken(String token) {
        String salt = JwtConstants.SALT;
        return verifyToken(token, salt);
    }

    /**
     * @desc   验证token，通过返回true
     * @create 2021-01-08
     * @params [token]需要校验的串
     **/
    public static boolean verifyToken(String token, String salt) {
        token = clearToken(token);
        try {
            Algorithm algorithm = Algorithm.HMAC256(salt);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("token验证失败");
            e.printStackTrace();
            return  false;
        }
    }

    public static String createToken(Integer userId, int role) {
        return createToken(userId, null, role);
    }

    public static String createToken(Integer userId, String openId, int role) {
        String salt = JwtConstants.SALT;
        int duration = JwtConstants.DURATION;
        return createToken(userId, openId, role, salt, duration);
    }

    /**
     * 生成JWT Token
     *
     * @param userId         userId
     * @param openId         openId
     * @param salt           盐值
     * @param expireDuration 过期时间和单位 单位：秒
     * @return token
     */
    public static String createToken(Integer userId, String openId, int role, String salt, int expireDuration) throws NullPointerException {
        if (userId == null) {
            throw new NullPointerException("生成token时，userId 不能为空！");
        }

        try {
            Date expireDate = DateUtils.addSeconds(new Date(), expireDuration);

            // 生成token
            Algorithm algorithm = Algorithm.HMAC256(salt);
            String token = JWT.create()
                    .withClaim(JwtConstants.JWT_USER_ID, userId)
                    .withClaim(JwtConstants.JWT_OPEN_ID, openId)
                    .withClaim(JwtConstants.JWT_ROLE, role)
                    // jwt唯一id
                    .withJWTId(UUIDUtil.getUuid())
                    // 签发人
                    .withIssuer(jwtProperties.getIssuer())
                    // 主题
//                    .withSubject(jwtProperties.getSubject())
                    // 签发的目标
                    .withAudience(jwtProperties.getAudience())
                    // 签名时间
                    .withIssuedAt(new Date())
                    // token过期时间
                    .withExpiresAt(expireDate)
                    // 签名
                    .sign(algorithm);
            return token;
        } catch (Exception e) {
//            log.error("generateToken exception", e);
        }
        return null;
    }

    public static DecodedJWT getJwt(String token) {
        token = clearToken(token);
        return JWT.decode(token);
    }

    public static LoginUser getLoginUser(String token) {
        DecodedJWT decodedJWT = getJwt(token);
        Integer userId = decodedJWT.getClaim(JwtConstants.JWT_USER_ID).asInt();
        String openId = decodedJWT.getClaim(JwtConstants.JWT_OPEN_ID).asString();
        String username = decodedJWT.getClaim(JwtConstants.JWT_USERNAME).asString();
        Integer role = decodedJWT.getClaim(JwtConstants.JWT_ROLE).asInt();
        return LoginUser.builder()
                .openId(openId)
                .userId(userId)
                .username(username)
                .role(role)
                .build();
    }

    public static int getUserId(String token) {
        return getJwt(token).getClaim(JwtConstants.JWT_USER_ID).asInt();
    }

    public static String getOpenId(String token) {
        return getJwt(token).getClaim(JwtConstants.JWT_OPEN_ID).asString();
    }

    public static int getRole(String token) {
        return getJwt(token).getClaim(JwtConstants.JWT_ROLE).asInt();
    }

//    public static
}
