package com.gy.server.http.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Splitter;

public interface HttpRequest {


	public String getMethod();
	
	public String getProtocol();
	
	public Map<String, List<String>> getParameterMap() ;
	public Iterator<String> getParameterNames();
	public List<String> getParameterValue(String parameterName);
	
	public String getRequestUri();
	public String getRemoteAddr();
	
	public Map<String, String> getHeaderMap() ;
	public Date getTimeStamp();
	
	public boolean isNormal() ;
	public boolean hasQueryString();
	public String getHeader(String headerName);
	
	
}
