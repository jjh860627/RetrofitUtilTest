package com.jjh.test;

import com.jjh.test.RestClient.OnResponseListener;

public class Test {
	public static void main(String[] args) {
		RestClient.getContents(new OnResponseListener<TestVo>() {
			
			public void onResponse(TestVo data) {
				System.out.println(data.getResult() + "/" + data.getMsg());
			}
		}, TestService.class, "getContents", "TT");
		
		
		
	}
}


