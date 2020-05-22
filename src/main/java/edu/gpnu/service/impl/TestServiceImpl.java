package edu.gpnu.service.impl;

import edu.gpnu.mapper.TestMapper;
import edu.gpnu.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements ITestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public String test() {
        return testMapper.myTest().toString();
    }
}
