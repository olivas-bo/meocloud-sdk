package pt.meocloud.sdk.data;

import com.google.gson.annotations.SerializedName;

import pt.meocloud.sdk.data.json.JsonObject;

public class CopyRef extends JsonObject {
	
	public static final String JSON_TAG_COPY_REF = "copy_ref";
	public static final String JSON_TAG_EXPIRES = "expires";
	
	@SerializedName(JSON_TAG_COPY_REF)
	private String copyRef;
	@SerializedName(JSON_TAG_EXPIRES)
	private String expires;
	
	public String getCopyRef() {
		return copyRef;
	}
	public void setCopyRef(String copyRef) {
		this.copyRef = copyRef;
	}
	public String getExpires() {
		return expires;
	}
	public void setExpires(String expires) {
		this.expires = expires;
	}

}
