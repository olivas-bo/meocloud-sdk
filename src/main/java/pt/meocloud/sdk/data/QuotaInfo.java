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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import pt.meocloud.sdk.data.json.JsonObject;

public class QuotaInfo extends JsonObject {
	
	public static final String JSON_TAG_SHARED = "shared";
	public static final String JSON_TAG_QUOTA = "quota";
	public static final String JSON_TAG_NORMAL = "normal";
	
	@Expose
	@SerializedName(JSON_TAG_SHARED)
	private Long shared;
	@Expose
	@SerializedName(JSON_TAG_QUOTA)
	private Long quota;
	@Expose
	@SerializedName(JSON_TAG_NORMAL)
	private Long normal;
	
	public Long getShared() {
		return shared;
	}
	public void setShared(Long shared) {
		this.shared = shared;
	}
	public Long getQuota() {
		return quota;
	}
	public void setQuota(Long quota) {
		this.quota = quota;
	}
	public Long getNormal() {
		return normal;
	}
	public void setNormal(Long normal) {
		this.normal = normal;
	}

}
