package com.jjh.test;

import java.lang.reflect.InvocationTargetException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

	public interface OnResponseListener<T>{
		public void onResponse(T data);
	}

	public static <T,U> void getContents(final OnResponseListener<U> listener, Class<T> serviceCls, String methodName, Object...args) {
		T serviceObj = getDefaultRetrofitObj().create(serviceCls);
		try {
			Class<?>[] paramCls = new Class[args.length];
			for(int i=0; i<args.length; i++){
				paramCls[i] = args[0].getClass();
			}
			Call<U> call = (Call<U>)serviceCls.getMethod(methodName, paramCls).invoke(serviceObj, args);
			System.out.println(call.request().url());
			call.enqueue(new Callback<U>() {
				public void onFailure(Call<U> arg0, Throwable arg1) {
					System.out.println("ERROR");
				}
				public void onResponse(Call<U> arg0, Response<U> arg1) {
					listener.onResponse(arg1.body());
				}
			});
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	private static Retrofit getDefaultRetrofitObj() {
		Retrofit client = new Retrofit.Builder().baseUrl("http://localhost:8080/")
				.addConverterFactory(GsonConverterFactory.create()).build();
		return client;
	}
}
