
## MySQL关注点



#### 1.Mysql结构

- 2层


- 服务层:连接器，缓存器，分析器，优化器，执行器

- 存储服务:各种存储服务

- 一条查询语句如何执行 select * from T where id=1;

#### 2.redo日志与Binlog日志

- 一条更新语句如何执行 update T set c=c+1 where c=1;

- redo日志具有cash-safe的能力，它属于InnoDB引擎都有，是物理日志，只记录操作结果

- binlog是mysql服务层提供的，是逻辑日志，一般用于业务回滚，主备复制

- redo日志是通过二阶段提交进行的，保证与binlog数据一致

##### binlog写入机制

 - 1.事务执行过程中，先把日志写入到binlog cache中，事务提交的时候，再把binlog cache写到binlog文件中
 - 2.每一个线程都有自己的binlog cache，但是会共用同一份binlog文件
 - 3.通过fsync持久化到磁盘(io瓶颈)
 
 binlog主要的格式有statement 和row格式  (statement可能造成主备不一样，row不会)
 
##### redo log写入机制

 - 1.事务执行过程中，生成的redo log先写入到redo log buffer中
 - 2.写到磁盘，没有持久化，写入到文件系统 page cache中
 - 3.持久化到磁盘(io瓶颈)


#### 3.事务

- mysql事务支持是在存储引擎层，不是所有的存储引擎都支持事务
- ACID 原子性，一致性，隔离性，持久性

##### 3.1隔离性

- 多个事务同时执行，可能出现脏读，不可重复读取、幻读等，因此出现了隔离级别

- 读未提交（read uncommitted）、读提交（read committed),可重复读(repeatable read),串行化(serializable)

- 隔离级别是如何实现的？ 通过创建视图的情况来实现(可重复读，读提交)，读未提交直接返回最新值，串行化是加锁的方式避免并发

- 实现依赖于MVCC(实际上每条记录在更新的时候都会同时记录一条回滚操作(回滚日志)。记录上的最新的值，通过回滚操作，都可以的得到前一个状态的值)


#### 4.索引


#### 5.锁


#### 6.mysql 主备同步

核心功臣 binlog






