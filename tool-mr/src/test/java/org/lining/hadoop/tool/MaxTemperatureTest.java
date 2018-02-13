package org.lining.hadoop.tool;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Assert;
import org.junit.Test;
import org.lining.hadoop.tool.job.MaxTemperatureDriver;


/**
 * description:
 * date 2018-02-13
 *
 * @author lining1
 * @version 1.0.0
 */
public class MaxTemperatureTest {

    @Test
    public void testMaxTemperature() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");
        conf.set("mapreduce.framework.name", "local");
        conf.setInt("mapreduce.task.io.sort.mb", 1);
        Path input = new Path("input/ncdc/micro");
        Path output = new Path("output");
        FileSystem fs = FileSystem.getLocal(conf);
        fs.delete(output, true);

        MaxTemperatureDriver driver = new MaxTemperatureDriver();
        driver.setConf(conf);

        int exitCode = driver.run(new String[] {input.toString(), output.toString()});
        Assert.assertEquals(exitCode, 0);
    }
}
