package ru.itis.dis403.lab1_06;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
    В БД храним хеши паролей
    hash(пароль + соль)

    {username, salt, hash}

 */
public class BCryptService {

    /*

        $2a$10$vbTbv1q9.wzvcdZG0/bH/ur7q82gCHjhziHaEfJv9HRHtoMWfzIQO

        $2a$
        10$ 2^10
        salt 22 Base64 код соли
        hash 31 Base64 код хеша (пароль + соли)
    */

    public static void main(String[] args) {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        //System.out.println(bCrypt.encode("password"));

        System.out.println(bCrypt.matches(
                "password1",
                "$2a$10$eVd7gUyIfgYKYWW0/eUwoek9h1LqwMXDgzPK7liSY79CtzPCDjTNi"
        ));

/*
        String salt = BCrypt.gensalt(10);
        System.out.println(salt);
        String hash = BCrypt.hashpw("password", salt);
        System.out.println(hash);
        $2a$10$eVd7gUyIfgYKYWW0/eUwoek9h1LqwMXDgzPK7liSY79CtzPCDjTNi
        $2a$10$eh8/xxDh0RVh3Jz/7K9zUOEEYPHTdLBSAd/6tRRVP/2YNjB8mc8Hq

*/


    }



}
/*
        String salt = BCrypt.gensalt(10);
        System.out.println(salt);
        String hash = BCrypt.hashpw("password", salt);
        System.out.println(hash);

        System.out.println(bCrypt.encode("password"));

        System.out.println(bCrypt.matches("password",
                "$2a$10$gxCDGtAffcdxxK.P4.DuSu8weUIzoa75vcmcbK.m5sispqoA.WLKW"));

 */
