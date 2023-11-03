package org.stevi.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Arrays;

public class WordMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key,
                    Text value,
                    OutputCollector<Text, IntWritable> output,
                    Reporter reporter) {

        var line = value.toString();

        Arrays.stream(line.split(" "))
                .filter(word -> !word.isEmpty())
                .forEach(word -> {
                    try {
                        output.collect(new Text(word), new IntWritable(1));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
