/*
The MIT License (MIT)

Copyright (c) 2013 Bruno Oliveira

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE. 

*/

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
