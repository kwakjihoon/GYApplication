package com.jh.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Splitter;
import com.google.common.base.Splitter.MapSplitter;

public class TestMaiinApplication{
		
	public static void main(String args [])  {
		String queryString = "tmp=123&tmp2=123&tmp=3";
		List<String> paramList = Splitter.on('&')
				.trimResults().splitToList(queryString);
		Map<String,List<String>> parameterMap = new HashMap<>();
		for (String param : paramList) {
			String key	=  param.split("=",2)[0];
			String value = param.split("=",2)[1];
			if (parameterMap.containsKey(key)) {
				parameterMap.get(key).add(value);
			}else {
				parameterMap.put(key,new ArrayList<String>());
				parameterMap.get(key).add(value);
			}
		}
		System.out.println(parameterMap);
		
		
	}
}
