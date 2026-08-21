package com.nnk.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Khang Nguyen.
 * Email: khang.nguyen@banvien.com
 * Date: 09/03/2019
 * Time: 11:26 AM
 */
@SpringBootTest
public class PasswordEncodeTest {

    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void testPassword() {
        String pw = encoder.encode("123456");
        System.out.println("[ " + pw + " ]");
    }
}
