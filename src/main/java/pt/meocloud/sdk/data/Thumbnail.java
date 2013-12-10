package pt.meocloud.sdk.data;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Thumbnail {
	
	private byte[] thumbnailData;
	
	public Thumbnail(byte[] thumbnailData){
		this.thumbnailData = thumbnailData;
	}

	public byte[] getThumbnailData() {
		return thumbnailData;
	}

	public void setThumbnailData(byte[] thumbnailData) {
		this.thumbnailData = thumbnailData;
	}
	
	public void writeThumbnail(String path){
		try {
			java.io.File f = new java.io.File(path);
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
			bos.write(thumbnailData);
			bos.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}

}
