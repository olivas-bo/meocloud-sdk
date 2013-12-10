package pt.meocloud.sdk.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import pt.meocloud.sdk.data.json.JsonObject;

public class Link extends JsonObject {
	
	public static final String JSON_TAG_URL = "url";
	public static final String JSON_TAG_PATH = "path";
	public static final String JSON_TAG_SHARE_ID = "shareid";
	public static final String JSON_TAG_METADATA = "metadata";
	
	@Expose
	@SerializedName(JSON_TAG_URL)
	private String url;
	@Expose
	@SerializedName(JSON_TAG_PATH)
	private String path;
	@Expose
	@SerializedName(JSON_TAG_SHARE_ID)
	private String shareId;
	@Expose
	@SerializedName(JSON_TAG_METADATA)
	private Metadata metadata;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getShareId() {
		return shareId;
	}
	public void setShareId(String shareId) {
		this.shareId = shareId;
	}
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

}
