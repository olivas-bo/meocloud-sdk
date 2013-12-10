package pt.meocloud.sdk;

public enum MediaProtocol {
	
	Hls(MeoCloudImpl.MEDIA_PROTOCOL_HLS),
	Rtmp(MeoCloudImpl.MEDIA_PROTOCOL_RTMP),
	Rtsp(MeoCloudImpl.MEDIA_PROTOCOL_RTSP),
	Ss(MeoCloudImpl.MEDIA_PROTOCOL_SS);
	
	private String mediaProtocol;
	
	private MediaProtocol(String mediaProtocol){
		this.mediaProtocol = mediaProtocol;
	}
	
	public String getMediaProtocol(){
		return mediaProtocol;
	}

}
