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

public class AccountInfo extends JsonObject {
	
	public static final String JSON_TAG_DISPLAY_NAME = "display_name";
	public static final String JSON_TAG_UID = "uid";
	public static final String JSON_TAG_LAST_EVENT = "last_event";
	public static final String JSON_TAG_QUOTA_INFO = "quota_info";
	public static final String JSON_TAG_ACTIVE = "active";
	public static final String JSON_TAG_EMAIl = "email";
	public static final String JSON_TAG_REFERRAL_CODE = "referral_code";
	public static final String JSON_TAG_CREATED_ON = "created_on";
	public static final String JSON_TAG_SEGMENT = "segment";
	public static final String JSON_TAG_REFERRAL_LINK = "referral_link";
	public static final String JSON_TAG_INVITES = "invites";
	
	@Expose
	@SerializedName(JSON_TAG_DISPLAY_NAME)
	private String displayName;
	@Expose
	@SerializedName(JSON_TAG_UID)
	private String uid;
	@Expose
	@SerializedName(JSON_TAG_LAST_EVENT)
	private String lastEvent;
	@Expose
	@SerializedName(JSON_TAG_QUOTA_INFO)
	private QuotaInfo quotaInfo;
	@Expose
	@SerializedName(JSON_TAG_ACTIVE)
	private Boolean active;
	@Expose
	@SerializedName(JSON_TAG_EMAIl)
	private String email;
	@Expose
	@SerializedName(JSON_TAG_REFERRAL_CODE)
	private String referralCode;
	@Expose
	@SerializedName(JSON_TAG_CREATED_ON)
	private String createdOn;
	@Expose
	@SerializedName(JSON_TAG_SEGMENT)
	private String segment;
	@Expose
	@SerializedName(JSON_TAG_REFERRAL_LINK)
	private String referralLink;
	@Expose
	@SerializedName(JSON_TAG_INVITES)
	private InvitesInfo invites;
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getLastEvent() {
		return lastEvent;
	}
	public void setLastEvent(String lastEvent) {
		this.lastEvent = lastEvent;
	}
	public QuotaInfo getQuotaInfo() {
		return quotaInfo;
	}
	public void setQuotaInfo(QuotaInfo quotaInfo) {
		this.quotaInfo = quotaInfo;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getReferralCode() {
		return referralCode;
	}
	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public String getReferralLink() {
		return referralLink;
	}
	public void setReferralLink(String referralLink) {
		this.referralLink = referralLink;
	}
	public InvitesInfo getInvites() {
		return invites;
	}
	public void setInvites(InvitesInfo invites) {
		this.invites = invites;
	}

}