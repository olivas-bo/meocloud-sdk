package pt.meocloud.sdk.data.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class JsonObject {
	
	public static <T> T fromJson(String json, Class<T> clazz){
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(json, clazz);
	}
	
	public String toJson(){
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(this);
	}

}
