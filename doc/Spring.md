
* [x] 1.Spring事件驱动 ApplicationEvent
* [x] 2.Spring扩展接口
 - 1. FactoryBean aop实现的基础
 - 2. BeanPostProcess 在每个bean初始化成前后做操作。
 - 3. InstantiationAwareBeanPostProcessor 在Bean实例化前后做一些操作。
 - 4. BeanNameAware、ApplicationContextAware 和 BeanFactoryAware 针对bean工厂，可以获取上下文，可以获取当前bena的id。
 - 5. BeanFactoryPostProcessor Spring允许在Bean创建之前，读取Bean的元属性，并根据自己的需求对元属性进行改变，比如将Bean的scope从singleton改变为prototype。
 - 6. InitialingBean 在属性设置完毕后做一些自定义操作。 DisposableBean 在关闭容器前做一些操作
 
 - 例子： spring-boot-hystrix   https://mp.weixin.qq.com/s/psTRi1tJ38NpV5LGtY0AYg
 
* [x] 3.Spring反射的使用 ReflectionUtils
* [x] 4.Spring retry组件