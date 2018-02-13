package org.lining.hadoop.basic;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;
import org.lining.hadoop.basic.map.MaxTemperatureMapper;

import java.io.IOException;

/**
 * description:
 * date 2018-02-13
 *
 * @author lining1
 * @version 1.0.0
 */
public class MaxTemperatureMapperTest {

    @Test
    public void testMaxTemperatureMapper() throws IOException, InterruptedException {
        Text value = new Text("0067011990999991950051507004+68750+023550FM-12+038299999V0203301N00671220001CN9999999N9+00001+99999999999");
        new MapDriver<LongWritable, Text, Text, IntWritable>()
                .withMapper(new MaxTemperatureMapper())
                .withInput(new LongWritable(0), value)
                .withOutput(new Text("1950"), new IntWritable(0))
                .runTest();

    }
}
