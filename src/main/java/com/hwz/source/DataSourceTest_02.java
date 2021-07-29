package com.hwz.source;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author yoohhwz
 * @create 2021-07-01 21:08
 */
public class DataSourceTest_02 {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> dataStream = env.readTextFile("D:\\study\\Software\\IDEAProjects\\fink_exercise\\data\\sensorreading.txt");

        dataStream.print();

        env.execute();
    }
}
