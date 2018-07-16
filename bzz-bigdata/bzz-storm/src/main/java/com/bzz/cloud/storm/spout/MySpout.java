package com.bzz.cloud.storm.spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Map;

public class MySpout implements IRichSpout {
	
	private FileInputStream fis;
	private InputStreamReader irs;
	private BufferedReader br;
	private SpoutOutputCollector collector;
	
	public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
		try {
			this.fis = new FileInputStream("track.log");
			this.irs = new InputStreamReader(fis);
			this.br  = new BufferedReader(irs);
			this.collector = spoutOutputCollector;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
	
	}
	
	public void activate() {
	
	}
	
	public void deactivate() {
	
	}
	private String str;
	public void nextTuple() {
		try {
			while ((str =this.br.readLine()) != null){
				collector.emit(new Values(str));
				Thread.sleep(3000);
			}
		}catch (Exception e){
		
		}
		
	}
	
	public void ack(Object o) {
	
	}
	
	public void fail(Object o) {
	
	}
	
	public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
		outputFieldsDeclarer.declare(new Fields("log"));
	}
	
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}
}
