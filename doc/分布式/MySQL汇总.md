
## MySQL关注点



#### 1.Mysql结构

- 2层


- 服务层:连接器，缓存器，分析器，优化器，执行器

- 存储服务:各种存储服务

- 一条查询语句如何执行 select * from T where id=1;

#### 2.redo日志与Binlong日志

- 一条更新语句如何执行 update T set c=c+1 where c=1;

- redo日志具有cash-safe的能力，它属于InnoDB引擎都有，是物理日志，只记录操作结果

- binlog是mysql服务层提供的，是逻辑日志，一般用于业务回滚，主备复制

- redo日志是通过二阶段提交进行的，保证与binlog数据一致


#### 3.事务

- mysql事务支持是在存储引擎层，不是所有的存储引擎都支持事务
- ACID 原子性，一致性，隔离性，持久性

##### 3.1隔离性

- 多个事务同时执行，可能出现脏读，不可重复读取、幻读等，因此出现了隔离级别

- 读未提交（read uncommitted）、读提交（read committed),可重复读(repeatable read),串行化(serializable)

- 隔离级别是如何实现的？ 通过创建视图的情况来实现(可重复读，读提交)，读未提交直接返回最新值，串行化是加锁的方式避免并发