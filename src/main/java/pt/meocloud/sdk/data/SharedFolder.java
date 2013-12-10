package pt.meocloud.sdk.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pt.meocloud.sdk.data.json.JsonObject;

public class SharedFolder extends JsonObject {
	
	public static final String JSON_TAG_ID = "id";
	public static final String JSON_TAG_FOLDER_TYPE = "folder_type";
	public static final String JSON_TAG_IS_OWNER = "is_owner";
	public static final String JSON_TAG_SHARED_FOLDER_PATH = "shared_folder_path";
	public static final String JSON_TAG_USERS = "users";
	
	@Expose
	@SerializedName(JSON_TAG_ID)
	private String id;
	@Expose
	@SerializedName(JSON_TAG_FOLDER_TYPE)
	private String folderType;
	@Expose
	@SerializedName(JSON_TAG_IS_OWNER)
	private Boolean isOwner;
	@Expose
	@SerializedName(JSON_TAG_SHARED_FOLDER_PATH)
	private String sharedFolderPath;
	@Expose
	@SerializedName(JSON_TAG_USERS)
	private List<User> users;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getIsOwner() {
		return isOwner;
	}
	public void setIsOwner(Boolean isOwner) {
		this.isOwner = isOwner;
	}
	public String getSharedFolderPath() {
		return sharedFolderPath;
	}
	public void setSharedFolderPath(String sharedFolderPath) {
		this.sharedFolderPath = sharedFolderPath;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public String getFolderType() {
		return folderType;
	}
	public void setFolderType(String folderType) {
		this.folderType = folderType;
	}
	

}
