package pt.meocloud.sdk.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import pt.meocloud.sdk.data.json.JsonObject;

public class InvitesInfo extends JsonObject {
	
	public static final String JSON_TAG_INVITES_SENT = "invites_sent";
	public static final String JSON_TAG_QUOTA_INVITES = "quota_invites";
	
	@Expose
	@SerializedName(JSON_TAG_INVITES_SENT)
	private Integer invitesSent;
	@Expose
	@SerializedName(JSON_TAG_QUOTA_INVITES)
	private Integer quotaInvites;
	
	public Integer getInvitesSent() {
		return invitesSent;
	}
	public void setInvitesSent(Integer invitesSent) {
		this.invitesSent = invitesSent;
	}
	public Integer getQuotaInvites() {
		return quotaInvites;
	}
	public void setQuotaInvites(Integer quotaInvites) {
		this.quotaInvites = quotaInvites;
	}

}
