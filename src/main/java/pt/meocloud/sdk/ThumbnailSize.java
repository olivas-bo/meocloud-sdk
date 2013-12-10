package pt.meocloud.sdk;

public enum ThumbnailSize {
	
	XS(MeoCloudImpl.THUMBNAIL_SIZE_XS),
	S(MeoCloudImpl.THUMBNAIL_SIZE_S),
	M(MeoCloudImpl.THUMBNAIL_SIZE_M),
	L(MeoCloudImpl.THUMBNAIL_SIZE_L),
	XL(MeoCloudImpl.THUMBNAIL_SIZE_XL);
	
	private String sizeCode;
	
	private ThumbnailSize(String sizeCode){
		this.sizeCode = sizeCode;
	}

	public String getSize() {
		return sizeCode;
	}

}
