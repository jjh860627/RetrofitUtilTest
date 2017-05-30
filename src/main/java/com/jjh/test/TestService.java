package com.jjh.test;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TestService {
	
	@GET("JSONAPI/json.json")
	public Call<TestVo> getContents(@Query("test") String a);
}
