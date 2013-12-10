package pt.meocloud.sdk;

public enum ApiMode {
	
	Meocloud(MeoCloudImpl.MEOCLOUD_LIVE_MODE),
	Sandbox(MeoCloudImpl.MEOCLOUD_SANDBOX_MODE);
	
	private String code;
	
	private ApiMode(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}

}
