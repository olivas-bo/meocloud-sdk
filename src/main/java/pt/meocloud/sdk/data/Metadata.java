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

public class Metadata extends JsonObject {
	
	public static final String JSON_TAG_IS_OWNER = "is_owner";
	public static final String JSON_TAG_REV = "rev";
	public static final String JSON_TAG_THUMB_EXISTS = "thumb_exists";
	public static final String JSON_TAG_BYTES = "bytes";
	public static final String JSON_TAG_MODIFIED = "modified";
	public static final String JSON_TAG_BOTTOM_CURSOR = "bottom_cursor";
	public static final String JSON_TAG_TOP_CURSOR = "top_cursor";
	public static final String JSON_TAG_PATH = "path";
	public static final String JSON_TAG_IS_DIR = "is_dir";
	public static final String JSON_TAG_SIZE = "size";
	public static final String JSON_TAG_ROOT = "root";
	public static final String JSON_TAG_HASH = "hash";
	public static final String JSON_TAG_IS_LINK = "is_link";
	public static final String JSON_TAG_ICON = "icon";
	public static final String JSON_TAG_MIME_TYPE = "mime_type";
	public static final String JSON_TAG_IS_UPLOAD = "is_upload";
	public static final String JSON_TAG_FOLDER_TYPE = "folder_type";
	public static final String JSON_TAG_CONTENTS = "contents";
	
	@Expose
	@SerializedName(JSON_TAG_IS_OWNER)
	private Boolean isOwner;
	@Expose
	@SerializedName(JSON_TAG_REV)
	private String rev;
	@Expose
	@SerializedName(JSON_TAG_THUMB_EXISTS)
	private Boolean thumbExists;
	@Expose
	@SerializedName(JSON_TAG_BYTES)
	private Long bytes;
	@Expose
	@SerializedName(JSON_TAG_MODIFIED)
	private String modified;
	@Expose
	@SerializedName(JSON_TAG_BOTTOM_CURSOR)
	private String bottomCursor;
	@Expose
	@SerializedName(JSON_TAG_TOP_CURSOR)
	private String topCursor;
	@Expose
	@SerializedName(JSON_TAG_PATH)
	private String path;
	@Expose
	@SerializedName(JSON_TAG_IS_DIR)
	private Boolean isDir;
	@Expose
	@SerializedName(JSON_TAG_SIZE)
	private String size;
	@Expose
	@SerializedName(JSON_TAG_ROOT)
	private String root;
	@Expose
	@SerializedName(JSON_TAG_HASH)
	private String hash;
	@Expose
	@SerializedName(JSON_TAG_IS_LINK)
	private Boolean isLink;
	@Expose
	@SerializedName(JSON_TAG_ICON)
	private String icon;
	@Expose
	@SerializedName(JSON_TAG_MIME_TYPE)
	private String mimeType;
	@Expose
	@SerializedName(JSON_TAG_IS_UPLOAD)
	private Boolean isUpload;
	@Expose
	@SerializedName(JSON_TAG_FOLDER_TYPE)
	private String folderType;
	@Expose
	@SerializedName(JSON_TAG_CONTENTS)
	private List<Metadata> contents;
	
	public Metadata(){
		
	}
	
	public Boolean getIsOwner() {
		return isOwner;
	}
	public void setIsOwner(Boolean isOwner) {
		this.isOwner = isOwner;
	}
	public String getRev() {
		return rev;
	}
	public void setRev(String rev) {
		this.rev = rev;
	}
	public Boolean getThumbExists() {
		return thumbExists;
	}
	public void setThumbExists(Boolean thumbExists) {
		this.thumbExists = thumbExists;
	}
	public Long getBytes() {
		return bytes;
	}
	public void setBytes(Long bytes) {
		this.bytes = bytes;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getBottomCursor() {
		return bottomCursor;
	}
	public void setBottomCursor(String bottomCursor) {
		this.bottomCursor = bottomCursor;
	}
	public String getTopCursor() {
		return topCursor;
	}
	public void setTopCursor(String topCursor) {
		this.topCursor = topCursor;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Boolean getIsDir() {
		return isDir;
	}
	public void setIsDir(Boolean isDir) {
		this.isDir = isDir;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getRoot() {
		return root;
	}
	public void setRoot(String root) {
		this.root = root;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public Boolean getIsLink() {
		return isLink;
	}
	public void setIsLink(Boolean isLink) {
		this.isLink = isLink;
	}
	public List<Metadata> getContents() {
		return contents;
	}
	public void setContents(List<Metadata> contents) {
		this.contents = contents;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Boolean getIsUpload() {
		return isUpload;
	}
	public void setIsUpload(Boolean isUpload) {
		this.isUpload = isUpload;
	}
	public String getFolderType() {
		return folderType;
	}
	public void setFolderType(String folderType) {
		this.folderType = folderType;
	}

}
