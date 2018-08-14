package com.learn.dozer;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author :lwy
 * @date 2018/6/1 12:50
 */
public class DozerIntegrationTest {

    private DozerBeanMapper mapper;

    @Before
    public void initMapper() {
        mapper = new DozerBeanMapper();
    }

    /**
     * 当我们创建一个Source对象时，我们可以将它直接映射到Dest对象上
     */
    @Test
    public void getDozerSourceObject() {
        Source source = new Source("wade", 35);
        DozerSource dest = mapper.map(source, DozerSource.class);
        assertEquals(dest.getName(), "wade");
        assertEquals(dest.getAge(), 35);

        assertEquals(dest.getAge(), source.getAge());
        assertEquals(dest.getName(), source.getName());
    }

    @Test
    public void givenSourceObjectAndDestObject_whenMapsSameNameFieldsCorrectly_thenCorrect() {
        Source source = new Source("Baeldung", 10);
        DozerSource dest = new DozerSource();
        mapper.map(source, dest);

        assertEquals(dest.getName(), "Baeldung");
        assertEquals(dest.getAge(), 10);
    }

    /**
     * Source2与Dest2属性名称相同，但其数据类型不同。
     * 在源类中，id是一个String，而points是double，而在目标类中，id和points都是整数
     */
    @Test
    public void givenDozerSource2() {
        Source2 source = new Source2("320", 15.2);
        DozerSource2 dest = mapper.map(source, DozerSource2.class);

        assertEquals(dest.getId(), 320);
        assertEquals(dest.getPoints(), 15);
    }


    //读取配置文件
    private void configureMapper(String... configure) {
        mapper.setMappingFiles(Arrays.asList(configure));
    }
    //根据配置文件来进行bean的装换
    @Test
    public void giveObjectByXMl() {
        configureMapper("dozer_mapping.xml");
        Person a=new Person("a","b",2);
        People b=mapper.map(a,People.class);
        assertEquals(b.getPeopleName(),"a");
        assertEquals(b.getPeopleAddress(),"b");
        assertEquals(b.getAge(),2);
    }

}
