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

package pt.meocloud.sdk;

import java.io.File;
import java.util.List;

import pt.meocloud.sdk.data.AccountInfo;
import pt.meocloud.sdk.data.CopyRef;
import pt.meocloud.sdk.data.Link;
import pt.meocloud.sdk.data.MediaLink;
import pt.meocloud.sdk.data.MeoCloudResponse;
import pt.meocloud.sdk.data.Metadata;
import pt.meocloud.sdk.data.RequestId;
import pt.meocloud.sdk.data.ShareLink;
import pt.meocloud.sdk.data.SharedFolderList;
import pt.meocloud.sdk.data.Thumbnail;

public interface MeoCloud {
	
	public MeoCloudResponse<Metadata> metadata(String pathName, Integer fileLimit, String hash, Boolean list, Boolean includeDeleted, String rev);
	
	public MeoCloudResponse<Metadata> metadataShare(String shareId, String path, Integer fileLimit);
	
	public MeoCloudResponse<List<Link>> listLinks();
	
	public MeoCloudResponse<Boolean> deleteLink(String linkId);
	
	public MeoCloudResponse<ShareLink> shares(String pathName);

	public MeoCloudResponse<RequestId> shareFolder(String pathName, String email);
	
	public MeoCloudResponse<SharedFolderList> listSharedFolders();
	
	public MeoCloudResponse<Thumbnail> thumbnails(String path, ThumbnailFormat format, ThumbnailSize size);
	
	public MeoCloudResponse<List<Metadata>> search(String path, String query, String mimeType, Integer fileLimit, Boolean includeDeleted);
	
	public MeoCloudResponse<List<Metadata>> revisions(String path, Integer revLimit);
	
	public MeoCloudResponse<Metadata> restore(String path, String revision);
	
	public MeoCloudResponse<MediaLink> media(String path, Boolean downloadFallback, MediaProtocol protocol);
	
	public MeoCloudResponse<File> downloadFile(String path, String revision);
	
	public MeoCloudResponse<Metadata> uploadFile(String filePath, String path, Boolean overwrite, String parentRevision);
	
	public MeoCloudResponse<Metadata> copy(String fromPath, String toPath, String fromCopyRef);
	
	public MeoCloudResponse<CopyRef> copyRef(String path);
	
	public MeoCloudResponse<Metadata> delete(String path);
	
	public MeoCloudResponse<Metadata> move(String fromPath, String toPath);
	
	public MeoCloudResponse<Metadata> createFolder(String path);
	
	public MeoCloudResponse<Metadata> undeleteTree(String path);
	
	public MeoCloudResponse<AccountInfo> accountInfo();
	
}
