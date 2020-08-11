package com.epam.demo;

import com.epam.demo.helper.SpotifyAuthHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@SpringBootTest
public class TestBase extends AbstractTestNGSpringContextTests {
    @Autowired
    protected SpotifyAuthHelper spotifyAuthHelper;
}
