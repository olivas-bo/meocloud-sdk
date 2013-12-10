package pt.meocloud.sdk.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import pt.meocloud.sdk.data.json.JsonObject;

public class RequestId extends JsonObject {
	
	public static final String JSON_TAG_REQUEST_ID = "req_id";
	
	@Expose
	@SerializedName(JSON_TAG_REQUEST_ID)
	private String requestId;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

}
