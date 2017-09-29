package hadoop.wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * App
 */
public class App2 {

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage: Word Count <input path> <output path>");
			System.exit(-1);
		}
		
		
		//创建配置对象
		Configuration conf = new Configuration();
		
		//删除输出目录
		FileSystem fs = FileSystem.get(conf);
		if(fs.exists(new Path(args[1]))){
			fs.delete(new Path(args[1]),true);
		}
		
		//创建job对象
		Job job = Job.getInstance(conf);
		
		//设置jar搜索类
		//mapreduce.job.jar
		job.setJarByClass(App2.class);
		
		//设置作业名称
		//mapreduce.job.name
		job.setJobName("Word Count");
		
		//添加输入路径
		FileInputFormat.addInputPath(job, new Path(args[0]));
		
		//设置输出路径
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//设置Mapper类型
		job.setMapperClass(WCMapper.class);
		
		//设置Combiner类
		job.setCombinerClass(WCReducer.class);
		
		//设置Reducer类型
		job.setReducerClass(WCReducer.class);
		
		//设置输出Key类型
		job.setOutputKeyClass(Text.class);
		//设置输出Value类型
		job.setOutputValueClass(IntWritable.class);
		
		job.getConfiguration().get("mapreduce.job.map.class");
		
		//设置reduce任务个数,默认是1
		job.setNumReduceTasks(2);
		
//		Configuration conf0 = job.getConfiguration();
//		Iterator<Entry<String,String>> it = conf0.iterator(); 
//		while(it.hasNext()){
//			Entry<String, String> e = it.next();
//			System.out.println(e.getKey() + "=" + e.getValue());
//		}
		
		//设置
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
