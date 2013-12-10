package pt.meocloud.sdk.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import pt.meocloud.sdk.data.json.JsonObject;

public class MediaLink extends JsonObject {
	
	public static final String JSON_TAG_URL = "url";
	public static final String JSON_TAG_EXPIRES = "expires";
	
	@Expose
	@SerializedName(JSON_TAG_URL)
	private String url;
	@Expose
	@SerializedName(JSON_TAG_EXPIRES)
	private String expires;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getExpires() {
		return expires;
	}
	public void setExpires(String expires) {
		this.expires = expires;
	}

}
