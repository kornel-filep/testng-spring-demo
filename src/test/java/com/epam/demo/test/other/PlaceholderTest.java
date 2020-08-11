package com.epam.demo.test.other;

import com.epam.demo.TestBase;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class PlaceholderTest extends TestBase {
    @Test
    void testThings() {
        System.out.println(spotifyAuthHelper.getAccessToken());
        MatcherAssert.assertThat(true, Matchers.is(true));
    }
}
