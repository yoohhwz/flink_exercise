package com.hwz.source;

import com.hwz.beans.SensorReading;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

/**
 * @author yoohhwz
 * @create 2021-07-01 21:08
 */
public class DataSourceTest_01 {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

//        env.setParallelism(1);

        //从集合读取数据
        DataStream<SensorReading> fromCollectionStream = env.fromCollection(Arrays.asList(
                new SensorReading("sensor_1", 1547718199L, 35.8),
                new SensorReading("sensor_6", 1547718201L, 15.4),
                new SensorReading("sensor_7", 1547718202L, 6.7),
                new SensorReading("sensor_10", 1547718205L, 38.1)
        ));

        DataStreamSource<Integer> fromElementsStream = env.fromElements(1, 2, 3, 54, 5);

        fromCollectionStream.print("fromCollectionStream");
        fromElementsStream.print("fromElementsStream");

        env.execute();
    }
}
