

## 微基准测试套件

### 概述

JMH，即Java Microbenchmark Harness，是专门用于代码微基准测试的工具套件。何谓Micro Benchmark呢？ 简单的来说就是基于方法层面的基准测试，精度可以达到微秒级。当你希望进一步优化方法执行性能的时候，就可以使用JMH对优化的结果进行量化的分析

此功能为JDK源代码添加了一套微基准测试套件，简化了现有微基准测试的运行和新基准测试的创建过程

使开发人员可以轻松运行现有的微基准测试并创建新的基准测试，其目标在于提供一个稳定且优化过的基准。 它基于Java Microbenchmark Harness（JMH），可以轻松测试JDK性能，支持JMH更新



### 应用场景

* 想定量地知道某个方法需要执行多长时间，以及执行时间和输入 n 的相关性
* 一个接口有两种不同实现(例如实现 A 使用了 FixedThreadPool，实现 B 使用了 ForkJoinPool)，不知道哪种实现性能更好





### 使用

添加Maven依赖：

```xml
<dependencies>
        <dependency>
            <groupId>org.openjdk.jmh</groupId>
            <artifactId>jmh-core</artifactId>
            <version>1.14.1</version>
        </dependency>
        <dependency>
            <groupId>org.openjdk.jmh</groupId>
            <artifactId>jmh-generator-annprocess</artifactId>
            <version>1.14.1</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>
```





```java
package mao;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Project name(项目名称)：JDK12_JMH
 * Package(包名): mao
 * Class(类名): Test1
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/11/6
 * Time(创建时间)： 16:51
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class Test1
{
    @Benchmark
    public int testSleep01()
    {
        try
        {
            Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Benchmark
    public int testSleep02()
    {
        try
        {
            Thread.sleep(50);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) throws RunnerException
    {
        Options options = new OptionsBuilder()
                .include(Test1.class.getSimpleName())
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(5)
                .build();
        new Runner(options).run();
    }
}
```





```sh
# JMH 1.14.1 (released 2603 days ago, please consider updating!)
# VM version: JDK 17.0.8.1, VM 17.0.8.1+8-LTS
# VM invoker: D:\Java\corretto-17.0.8.1\bin\java.exe
# VM options: -javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2021.2.2\lib\idea_rt.jar=53444:D:\Program Files\JetBrains\IntelliJ IDEA 2021.2.2\bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, 1 s each
# Measurement: 5 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: mao.Test1.testSleep01

# Run progress: 0.00% complete, ETA 00:00:20
# Fork: 1 of 1
# Warmup Iteration   1: 108.301 ms/op
# Warmup Iteration   2: 109.491 ms/op
# Warmup Iteration   3: 108.524 ms/op
# Warmup Iteration   4: 108.661 ms/op
# Warmup Iteration   5: 108.864 ms/op
Iteration   1: 108.768 ms/op
Iteration   2: 108.916 ms/op
Iteration   3: 109.086 ms/op
Iteration   4: 108.530 ms/op
Iteration   5: 108.879 ms/op


Result "testSleep01":
  108.836 ±(99.9%) 0.792 ms/op [Average]
  (min, avg, max) = (108.530, 108.836, 109.086), stdev = 0.206
  CI (99.9%): [108.044, 109.628] (assumes normal distribution)


# JMH 1.14.1 (released 2603 days ago, please consider updating!)
# VM version: JDK 17.0.8.1, VM 17.0.8.1+8-LTS
# VM invoker: D:\Java\corretto-17.0.8.1\bin\java.exe
# VM options: -javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2021.2.2\lib\idea_rt.jar=53444:D:\Program Files\JetBrains\IntelliJ IDEA 2021.2.2\bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, 1 s each
# Measurement: 5 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: mao.Test1.testSleep02

# Run progress: 50.00% complete, ETA 00:00:11
# Fork: 1 of 1
# Warmup Iteration   1: 61.879 ms/op
# Warmup Iteration   2: 62.118 ms/op
# Warmup Iteration   3: 62.348 ms/op
# Warmup Iteration   4: 62.352 ms/op
# Warmup Iteration   5: 62.270 ms/op
Iteration   1: 62.190 ms/op
Iteration   2: 62.123 ms/op
Iteration   3: 61.935 ms/op
Iteration   4: 62.081 ms/op
Iteration   5: 62.007 ms/op


Result "testSleep02":
  62.067 ±(99.9%) 0.382 ms/op [Average]
  (min, avg, max) = (61.935, 62.067, 62.190), stdev = 0.099
  CI (99.9%): [61.685, 62.449] (assumes normal distribution)


# Run complete. Total time: 00:00:22

Benchmark          Mode  Cnt    Score   Error  Units
Test1.testSleep01  avgt    5  108.836 ± 0.792  ms/op
Test1.testSleep02  avgt    5   62.067 ± 0.382  ms/op
```







### 注解

* **@BenchmarkMode**：对应Mode选项，可用于类或者方法上， 需要注意的是，这个注解的value是一个数组，可以把几种Mode集合在一起 执行，还可以设置为Mode.All，即全部执行一遍
* **@State**：类注解，JMH测试类必须使用@State注解，State定义了一个类实例的生命周期，可以类比Spring Bean的Scope。由 于JMH允许多线程同时执行测试
    * **Scope.Thread**：默认的State，每个测试线程分配一个实例
    *  **Scope.Benchmark**：所有测试线程共享一个实 例，用于测试有状态实例在多线程共享下的性能
    * **Scope.Group**：每个线程组共享一个实例
* **@OutputTimeUnit**：benchmark 结果所使用的时间单位，可用于类或者方法注解，使用java.util.concurrent.TimeUnit中的标准时间单位
* **@Benchmark**：方法注解，表示该方法是需要进行 benchmark 的对象





### 方法

* **include**：代表我要测试的是哪个类的方法
* **exclude**：代表测试的时候需要排除哪个类的方法
* **forks**：指的是做几轮测试，在一轮测试无法得出最满意的结果时,可以多测几轮以便得出更全面的测试结果，而每一轮都是先预热，再正式计量
* **warmupIterations**：代表先预热几次
* **measurementIterations**：正式运行测试几次





### 参数

* **Mode**：表示 JMH 进行 Benchmark 时所使用的模式。通常是测量的维度不同，或是测量的方式不同。目前 JMH 共有四种模式
    * **Throughput**: 吞吐量，一段时间内可执行的次数,每秒可执行次数
    * **AverageTime**: 每次调用的平均耗时时间
    * **SampleTime**: 随机进行采样执行的时间，最后输出取样结果的分布
    * **SingleShotTime**: 在每次执行中计算耗时,以上模式都是默认一次 iteration 是 1s，只有 SingleShotTime 是只运 行一次
* **Iteration**：JMH 进行测试的最小单位。在大部分模式下，一次 iteration 代表的是一秒，JMH 会在这一秒内不断调用需要 benchmark 的方法，然后根据模式对其采样，计算吞吐量，计算平均执行时间等
* **Warmup**：Warmup 是指在实际进行 benchmark 前先进行预热的行为。为什么需要预热？因为 JVM 的 JIT 机制的存在，如果某个函数被调用多次之后，JVM 会尝试将其编译成为机器码从而提高执行速度。为了让 benchmark 的结果更加接近真 实情况就需要进行预热





