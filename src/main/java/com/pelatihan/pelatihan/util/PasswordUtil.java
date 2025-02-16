package com.pelatihan.pelatihan.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtil {
    PasswordUtil(){};

    public static String hash(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public static boolean check(String password,String hashed){
        return BCrypt.checkpw(password, hashed);
    }
}
