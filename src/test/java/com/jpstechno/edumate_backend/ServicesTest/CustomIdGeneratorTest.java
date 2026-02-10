package com.jpstechno.edumate_backend.ServicesTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpstechno.edumate_backend.KeyGenerator.CustomIdGeneratorService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class CustomIdGeneratorTest {

    @Autowired
    private CustomIdGeneratorService generateur;

    @Test
    public void KeyGeneratorTest() {
        String key1 = generateur.nextId("DA");
        String key2 = generateur.nextId("DA");

        System.out.println(key2);
        System.out.println(key2);

    }

}
