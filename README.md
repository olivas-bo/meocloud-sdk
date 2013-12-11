Meocloud-sdk
============

All options supported by the API are supported by the library. The library at this point does not
exhange the oauth tokens for you, you have to take care of that on your own for now.

[MEOCloud](http://www.meocloud.pt) Java api library.

##Documentation

Comming soon

##Samples

All methods return a MeoCloudResponseObject.

###Initialize the client:

	MeoCloud client =  new MeoCloudImpl(
		"consumer_key",
		"consumer_secret",
		"access_token",
		"access_secret",
		(ApiMode.Meocloud || ApiMode.Sandbox)
	);

###File Metadata:

The file Metadata

	MeoCloudResponse<Metadata> meoCloudResponse = client.metadata("/", 1, null, true, false, null);
	System.out.println(String.format("Metadata: %s", meoCloudResponse.getResponse().toJson()));

###Metadata Share:
	
	String shareId = "shareId";
	String sharePath = "/file";
	MeoCloudResponse<Metadata> meoCloudResponse = client.metadataShare(shareId, sharePath, null);
	System.out.println(String.format("Metadata Share: %s", meoCloudResponse.getResponse().toJson()));
	
###List Links:

	MeoCloudResponse<List<Link>> meoCloudResponse = client.listLinks();
	List<Link> links = meoCloudResponse.getResponse();
	for(Link l: links){
		System.out.println(String.format("Link: %s", l.toJson()));
	}
	
###Delete Link:

	MeoCloudResponse<Boolean> meoCloudResponse = client.deleteLink("LinkId");
	System.out.println(String.format("Delete: %s", meoCloudResponse.getResponse().toString()));
	
###Shares:

	MeoCloudResponse<ShareLink> meoCloudResponse = client.shares("FileToShare");
	System.out.println(String.format("Share File: %s", meoCloudResponse.getResponse().toJson()));
	
###List Shared Folders:

	MeoCloudResponse<SharedFolderList> meoCloudResponse = client.listSharedFolders();
	SharedFolderList sharedFolders = meoCloudResponse.getResponse();
	System.out.println(String.format("Shared Folders: %s", sharedFolders.toJson()));
	
###Thumbnails:

	MeoCloudResponse<Thumbnail> meoCloudResponse = client.thumbnails("PictureFilePath", ThumbnailFormat.Jpeg, ThumbnailSize.L);
	Thumbnail t = meoCloudResponse.getResponse();
	t.writeThumbnail("TestThumbnail.jpg");
	
###Search:

	MeoCloudResponse<List<Metadata>> meoCloudResponse = client.search("Path", "Query", null, null, false);
	List<Metadata> searchResult = meoCloudResponse.getResponse();
	for(Metadata m: searchResult){
		System.out.println(String.format("Search result: %s", m.toJson()));
	}
	
###File Download:
	
	MeoCloudResponse<File> meoCloudResponse = client.downloadFile("FileToDownload", null);
	
###File Upload

	MeoCloudResponse<Metadata> meoCloudResponse = client.uploadFile("PathToFileOnDisk", "RemotePath", null, null);
	Metadata fileUploadMeta = meoCloudResponse.getResponse();
	System.out.println(String.format("File Upload Response: %s", fileUploadMeta.toJson()));
	
###Account Info:

	MeoCloudResponse<AccountInfo> meoCloudResponse = client.accountInfo();
	System.out.println(String.format("Acount Info: %s", meoCloudResponse.getResponse().toJson()));
	
## License
Copyright (c) 2013 Bruno Oliveira. Licensed under the MIT license.