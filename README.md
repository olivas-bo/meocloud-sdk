meocloud-sdk
============

[MEOCloud](http://www.meocloud.pt) Java api library.

##Samples

###Initialize the client:

MeoCloud client =  new MeoCloudImpl(
	"consumer_key",
	"consumer_secret",
	"access_token",
	"access_secret",
	(ApiMode.Meocloud || ApiMode.Sandbox)
);

###File Metadata:

MeoCloudResponse<Metadata> meoCloudResponse = client.metadata("/", 1, null, true, false, null);
System.out.println(String.format("Metadata: %s", meoCloudResponse.getResponse().toJson()));