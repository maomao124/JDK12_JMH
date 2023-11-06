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
