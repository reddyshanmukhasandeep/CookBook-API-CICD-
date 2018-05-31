package com.cts.cde.io.cookbook;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = CookBookApplication.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
public class CookBookApplicationTest {

    @Ignore
    @Test
    public void initSetupTest() {
        assertTrue(true);
    }
}