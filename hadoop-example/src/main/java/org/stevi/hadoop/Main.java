package org.stevi.hadoop;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputFormat;
import org.apache.hadoop.mapred.Reducer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        JobConf jobConf = new JobConf(Main.class);
        jobConf.setJobName("Count words job");
        jobConf.setOutputKeyClass(Text.class);
        jobConf.setOutputValueClass(IntWritable.class);
        jobConf.setMapperClass(WordMapper.class);
        jobConf.setCombinerClass(Reducer.class);
        jobConf.setReducerClass(Reducer.class);
        jobConf.setInputFormat(InputFormat.class);
        jobConf.setOutputFormat(OutputFormat.class);

        FileInputFormat.setInputPaths(jobConf, new Path(args[0]));
        FileOutputFormat.setOutputPath(jobConf, new Path(args[1]));

        JobClient.runJob(jobConf);
    }
}