package org.lining.hadoop.pair;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.lining.hadoop.common.utils.MRDPUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * description:
 * date 2018-02-13
 *
 * @author lining1
 * @version 1.0.0
 */
public class MinMaxCount {

    private static class MinMaxCountMapper extends Mapper<Object, Text, Text, MinMaxCountTuple> {
        private Text outUserId = new Text();
        private MinMaxCountTuple outTuple = new MinMaxCountTuple();
        private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

        @Override
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            Map<String, String> parsed = MRDPUtils.transformToMap(value.toString());
            String strDate = parsed.get("Creationdate");
            String userId = parsed.get("UserId");
            Date creationDate = new Date();
            try {
                creationDate = format.parse(strDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
            outTuple.setCount(1);
            outUserId.set(userId);
            context.write(outUserId, outTuple);

        }
    }

    private static class MinMaxCountReducer extends Reducer<Text, MinMaxCountTuple, Text, MinMaxCountTuple> {
        private MinMaxCountTuple result = new MinMaxCountTuple();

        @Override
        public void reduce(Text key, Iterable<MinMaxCountTuple> values, Context context) throws IOException, InterruptedException {
            result.setMin(null);
            result.setMax(null);
            result.setCount(0);
            int sum = 0;

            for (MinMaxCountTuple value : values) {
                if (result.getMin() == null || value.getMin().compareTo(result.getMin()) < 0) {
                    result.setMin(value.getMin());
                }
                if (result.getMin() == null || value.getMax().compareTo(result.getMax()) > 0) {
                    result.setMax(value.getMax());
                }
                sum += result.getCount();
            }
            result.setCount(sum);
            context.write(key, result);
        }
    }
}
