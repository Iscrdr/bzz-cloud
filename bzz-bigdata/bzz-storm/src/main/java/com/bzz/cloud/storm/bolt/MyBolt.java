package com.bzz.cloud.storm.bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

public class MyBolt implements IRichBolt {
	private static int num = 0;
	private static String log;
	private OutputCollector outputCollector;
	public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
		this.outputCollector = outputCollector;
	}
	
	public void execute(Tuple tuple) {
		try{
			//Thread.sleep(3000);
			log = tuple.getStringByField("log");
			if(null != log){
				num ++;
				System.err.println(Thread.currentThread().getName()+",line:"+ num+",line:"+log);
			}
			outputCollector.ack(tuple);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void cleanup() {
	
	}
	
	public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
	
	}
	
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}
}
