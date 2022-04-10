package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) //수동으로 우리가 이전에 등록한거 일단 뺌
) //@Component 애노테이션 붙은 애들 다 빈으로 등록해줌.
public class AutoAppConfig {

}
