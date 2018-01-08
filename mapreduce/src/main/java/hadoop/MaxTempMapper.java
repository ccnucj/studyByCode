package hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * MR:Map
 */
public class MaxTempMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	//缺失常量
	private static final int MISSING = 9999;

	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		//取得一整行文本
		String line = value.toString();
		//提取年份值
		String year = line.substring(15, 19);
		//定义气温变量
		int airTemperature;
		if (line.charAt(87) == '+') {
			airTemperature = Integer.parseInt(line.substring(88, 92));
		} else {
			airTemperature = Integer.parseInt(line.substring(87, 92));
		}

		//提取质量
		String quality = line.substring(92, 93);

		if (airTemperature != MISSING && quality.matches("[01459]")) {
			context.write(new Text(year), new IntWritable(airTemperature));
		}
	}
}
