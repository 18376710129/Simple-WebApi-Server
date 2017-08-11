package cn.zry.modules.security.utils;

import cn.zry.modules.security.common.LoginPara;
import cn.zry.modules.security.common.WechatLoginPara;
import cn.zry.modules.security.config.Constant;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Date;

/**
 * jwt 加解密
 * Created by qianlu on 2017/5/5.
 */
@Component
public class JwtService {

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public SecretKey generalKey() {
        String stringKey = Constant.profiles + Constant.JWT_SECRET;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 创建jwt
     *
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     * @throws Exception
     */
    public String createJWT(String id, String subject, long ttlMillis) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public Claims parseJWT(String jwt) throws Exception {
        SecretKey key = generalKey();
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    /**
     * 生成subject信息
     *
     * @param sid
     * @return
     */
    public static String generalSubject(String sid) throws IOException {
        LoginPara bean = new LoginPara();
        bean.setSid(sid);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(bean);
    }


    /**
     * 生成微信端 subject
     *
     * @param wp
     * @return
     * @throws IOException
     */
    public static String generalSubject(WechatLoginPara wp) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(wp);
    }


    /**
     * 逆向重构subject json
     *
     * @param subjectJson
     * @return
     */
    public static <T> T reverseSubject(String subjectJson, Class<T> t) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(subjectJson, t);
    }

}
