package org.lining.hadoop.pair;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description:
 * date 2018-02-13
 *
 * @author lining1
 * @version 1.0.0
 */
public class MinMaxCountTuple implements Writable {

    private Date min = new Date();
    private Date max = new Date();
    private long count = 0;

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(min.getTime());
        dataOutput.writeLong(max.getTime());
        dataOutput.writeLong(count);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        min = new Date(dataInput.readLong());
        max = new Date(dataInput.readLong());
        count = dataInput.readLong();
    }

    public Date getMin() {
        return min;
    }

    public void setMin(Date min) {
        this.min = min;
    }

    public Date getMax() {
        return max;
    }

    public void setMax(Date max) {
        this.max = max;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return format.format(min) + "\t" + format.format(max) + "\t" + count;
    }
}
