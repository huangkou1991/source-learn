package base.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author :lwy
 * @date 2018/6/6 17:26
 */

@Configuration
@ImportResource(locations = {"application-test.xml"})
@ComponentScan(basePackages = {"com.wade"})
public abstract class AbstractTest {
}
