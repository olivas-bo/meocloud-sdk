package pt.meocloud.sdk.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import pt.meocloud.sdk.data.json.JsonObject;

public class User extends JsonObject {
	
	public static final String JSON_TAG_ID = "id";
	public static final String JSON_TAG_OWNER = "owner";
	public static final String JSON_TAG_USER = "user";
	public static final String JSON_TAG_EMAIL = "email";
	public static final String JSON_TAG_NAME = "name";
	
	@Expose
	@SerializedName(JSON_TAG_ID)
	private String id;
	@Expose
	@SerializedName(JSON_TAG_OWNER)
	private Boolean owner;
	@Expose
	@SerializedName(JSON_TAG_USER)
	private Boolean user;
	@Expose
	@SerializedName(JSON_TAG_EMAIL)
	private String email;
	@Expose
	@SerializedName(JSON_TAG_NAME)
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getOwner() {
		return owner;
	}
	public void setOwner(Boolean owner) {
		this.owner = owner;
	}
	public Boolean getUser() {
		return user;
	}
	public void setUser(Boolean user) {
		this.user = user;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
