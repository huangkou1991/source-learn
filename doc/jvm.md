## jvm诊断

### 1.jps
- jps的输出信息包括 Java 进程的进程 ID 以及主类名，追加参数打印额外信息 例如，-l将打印模块名以及包名，-v将打印传递给 Java 虚拟机的参数，-m将打印传递给主类的参数。

```
$ jps -mlv
18331 org.example.Foo Hello World
18332 jdk.jcmd/sun.tools.jps.Jps -mlv -Dapplication.home=/Library/Java/JavaVirtualMachines/jdk-11.jdk/Contents/Home -Xms8m -Djdk.module.main=jdk.jcmd

```

### 2.jstat
- jstat命令可用来打印目标 Java 进程的性能数据，包括多条子命令
```
$ jstat -options
-class
-compiler
-gc
-gccapacity
-gccause
-gcmetacapacity
-gcnew
-gcnewcapacity
-gcold
-gcoldcapacity
-gcutil
-printcompilation

```

### 3.jmap
- 分析 Java 虚拟机堆中的对象
  
 jmap同样包括多条子命令。 
 -clstats，该子命令将打印被加载类的信息
 -finalizerinfo，该子命令将打印所有待 finalize的对象 
 -histo，该子命令将统计各个类的实例数目以及占用内存，并按照内存使用量从多至少的顺序排列。此外，-histo:live只统计堆中的存活对象   
 -dump,该子命令将导出java虚拟机的堆的快照，同样,-dump:live将只保存堆中存货的对象                                                             
                                                          
                                            
                                           
jmap -dump:live,format=b,file=filename.bin导出到一个文件中

### 4.jinfo
- jinfo命令可用来查看目标Java进程的参数，如传入的虚拟机参数

如 jinfo 15532

### 5.jstack
- jstack命令（帮助文档）可以用来打印目标 Java 进程中各个线程的轨迹，以及这些线程所持有的锁

### 6.jcmd
- 类似于jstat


