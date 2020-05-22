package edu.gpnu;

import edu.gpnu.mapper.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest {

    @Autowired
    private TestMapper testMapper;

    @Test
    public void test(){
        System.out.println(testMapper);
        Map test = testMapper.myTest();
        System.out.println(test);
    }
}
