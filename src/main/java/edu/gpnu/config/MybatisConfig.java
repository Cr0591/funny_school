package edu.gpnu.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan(value={"edu.gpnu.mapper*"})
public class MybatisConfig {


    
   
}
