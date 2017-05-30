package com.jjh.test;

import retrofit2.http.Query;

public abstract class BaseVo {
	private boolean result;
	
	public boolean getResult(){
		return this.result;
	}
	
	public void setResult(@Query(value = "query1") boolean result){
		this.result = result;
	}
	
}
