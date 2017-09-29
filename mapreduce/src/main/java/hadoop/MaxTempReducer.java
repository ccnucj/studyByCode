package hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * MR:Reduce
 */
public class MaxTempReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	/**
	 * reduce
	 */
	protected void reduce(Text key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {
		//最大值
		int maxValue = Integer.MIN_VALUE;

		//提取年份的最大值
		for (IntWritable value : values) {
			maxValue = Math.max(maxValue, value.get());
		}

		//output key
		context.write(key, new IntWritable(maxValue));
	}
}
