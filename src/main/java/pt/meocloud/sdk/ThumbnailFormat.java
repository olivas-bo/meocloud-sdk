package pt.meocloud.sdk;

public enum ThumbnailFormat {
	
	Jpeg(MeoCloudImpl.THUMBNAIL_FORMAT_JPEG),
	Png(MeoCloudImpl.THUMBNAIL_FORMAT_PNG);
	
	private String format;

	private ThumbnailFormat(String format){
		this.format = format;
	}

	public String getFormat() {
		return format;
	}
	
}
