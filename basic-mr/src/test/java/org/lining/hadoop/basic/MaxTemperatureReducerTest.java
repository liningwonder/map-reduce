package org.lining.hadoop.basic;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Test;
import org.lining.hadoop.basic.reduce.MaxTemperatureReducer;

import java.io.IOException;
import java.util.Arrays;

/**
 * description:
 * date 2018-02-13
 *
 * @author lining1
 * @version 1.0.0
 */
public class MaxTemperatureReducerTest {

    @Test
    public void testMaxTemperatureReducer() throws IOException, InterruptedException {
        new ReduceDriver<Text, IntWritable, Text, IntWritable>()
                .withReducer(new MaxTemperatureReducer())
                .withInput(new Text("1950"), Arrays.asList(new IntWritable(10), new IntWritable(5)))
                .withOutput(new Text("1950"), new IntWritable(10))
                .runTest();
    }
}
