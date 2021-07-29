package com.hwz.transform;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author yoohhwz
 * @create 2021-07-01 21:55
 */
public class TransformTest_01 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> dataStream = env.readTextFile("D:\\study\\Software\\IDEAProjects\\fink_exercise\\data\\sensorreading.txt");

        // map操作
        DataStream<Integer> mapStram01 = dataStream.map(new MapFunction<String, Integer>() {
            @Override
            public Integer map(String s) throws Exception {
                return s.length();
            }
        });
        mapStram01.print("mapStram01");

        // map操作使用 Lambda表达式
        DataStream<Integer> mapStram02 = dataStream.map(line -> line.length());
        mapStram02.print("mapStram02");


        //filter 操作
        SingleOutputStreamOperator<String> filterStream01 = dataStream.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String s) throws Exception {
                return s.startsWith("sensor_1");
            }
        });
        filterStream01.print("filterStream01");

        //filter 操作 使用 Lambda表达式
        SingleOutputStreamOperator<String> filterStream02 = dataStream.filter(line -> line.startsWith("sensor_1"));
        filterStream02.print("filterStream02");

        //flatMap操作
        SingleOutputStreamOperator<String> flatMapStream = dataStream.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String line, Collector<String> collector) throws Exception {
                String[] splits = line.split(",");
                for (String split : splits) {
                    collector.collect(split);
                }
            }
        });
        flatMapStream.print("flatMapStream");




        env.execute();
    }
}
