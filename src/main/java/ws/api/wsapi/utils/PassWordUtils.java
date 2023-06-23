package ws.api.wsapi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassWordUtils {
    private PassWordUtils(){}
    public static String encode(String passWord){
        return new BCryptPasswordEncoder().encode(passWord);
    }
    public static boolean matches(CharSequence passWord,String encodePass){
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        return encode.matches(passWord,encodePass);
    }
}
