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

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.DefaultApi10a;
import org.scribe.builder.api.SapoApi;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.ParameterList;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.scribe.utils.OAuthEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.meocloud.sdk.data.AccountInfo;
import pt.meocloud.sdk.data.CopyRef;
import pt.meocloud.sdk.data.Empty;
import pt.meocloud.sdk.data.Link;
import pt.meocloud.sdk.data.MediaLink;
import pt.meocloud.sdk.data.MeoCloudResponse;
import pt.meocloud.sdk.data.Metadata;
import pt.meocloud.sdk.data.RequestId;
import pt.meocloud.sdk.data.ShareLink;
import pt.meocloud.sdk.data.SharedFolderList;
import pt.meocloud.sdk.data.Thumbnail;
import pt.meocloud.sdk.scribe.builder.api.MeoCloudOAuth10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class MeoCloudImpl implements MeoCloud {
	
	static final Logger log = LoggerFactory.getLogger(MeoCloudImpl.class);
	
	/**
	 * API Scheme
	 * */
	public static final String MEOCLOUD_SCHEME = "https";
	/**
	 * API Endpoints
	 * */
	public static final String MEOCLOUD_API_ENDPOINT = "publicapi.meocloud.pt";
	public static final String MEOCLOUD_API_CONTENT_ENDPOINT = "api-content.meocloud.pt";
	/**
	 * API Version
	 * */
	public static final String MEOCLOUD_API_VERSION = "1";
	/**
	 * API Modes
	 * */
	public static final String MEOCLOUD_LIVE_MODE = "meocloud";
	public static final String MEOCLOUD_SANDBOX_MODE = "sandbox";
	/**
	 * API Methods
	 * */
	public static final String MEOCLOUD_API_METHOD_METADATA = "Metadata";
	public static final String MEOCLOUD_API_METHOD_METADATA_SHARE = "MetadataShare";
	public static final String MEOCLOUD_API_METHOD_LIST_LINKS = "ListLinks";
	public static final String MEOCLOUD_API_METHOD_LIST_UPLOAD_LINKS = "ListUploadLinks";
	public static final String MEOCLOUD_API_METHOD_DELETE_LINK = "DeleteLink";
	public static final String MEOCLOUD_API_METHOD_SHARES = "Shares";
	public static final String MEOCLOUD_API_METHOD_UPLOAD_LINK = "UploadLink";
	public static final String MEOCLOUD_API_METHOD_SHARE_FOLDER = "ShareFolder";
	public static final String MEOCLOUD_API_METHOD_LIST_SHARED_FOLDERS = "ListSharedFolders";
	public static final String MEOCLOUD_API_METHOD_THUMBNAILS = "Thumbnails";
	public static final String MEOCLOUD_API_METHOD_SEARCH = "Search";
	public static final String MEOCLOUD_API_METHOD_REVISIONS = "Revisions";
	public static final String MEOCLOUD_API_METHOD_RESTORE = "Restore";
	public static final String MEOCLOUD_API_METHOD_MEDIA = "Media";
	public static final String MEOCLOUD_API_METHOD_FILES = "Files";
	public static final String MEOCLOUD_API_METHOD_DELTA = "Delta";
	public static final String MEOCLOUD_API_METHOD_COPY = "Fileops/Copy";
	public static final String MEOCLOUD_API_METHOD_COPY_REF = "CopyRef";
	public static final String MEOCLOUD_API_METHOD_DELETE = "Fileops/Delete";
	public static final String MEOCLOUD_API_METHOD_MOVE = "Fileops/Move";
	public static final String MEOCLOUD_API_METHOD_CREATE_FOLDER = "Fileops/CreateFolder";
	public static final String MEOCLOUD_API_METHOD_UNDELETE_TREE = "UndeleteTree";
	public static final String MEOCLOUD_API_METHOD_ACOUNT_INFO = "Account/Info";
	/**
	 * Methods parameters
	 * */
	public static final String FILE_LIMIT_PARAM = "file_limit";
	public static final String HASH_PARAM = "hash";
	public static final String LIST_PARAM = "list";
	public static final String INCLUDE_DELETED_PARAM = "include_deleted";
	public static final String REV_PARAM = "rev";
	public static final String SHARE_ID_PARAM = "shareid";
	public static final String TO_EMAIL_PARAM = "to_email";
	public static final String FORMAT_PARAM = "format";
	public static final String SIZE_PARAM = "size";
	public static final String QUERY_PARAM = "query";
	public static final String MIME_TYPE_PARAM = "mime_type";
	public static final String REVLIMIT_PARAM = "rev_limit";
	public static final String DOWNLOAD_FALLBACK_PARAM = "download_fallback";
	public static final String PROTOCOL_PARAM = "protocol";
	public static final String OVERWRITE_PARAM = "overwrite";
	public static final String PARENT_REV_PARAM = "parent_rev";
	public static final String ROOT_PARAM = "root";
	public static final String FROM_PATH_PARAM = "from_path";
	public static final String TO_PATH_PARAM = "to_path";
	public static final String FROM_COPY_REF_PARAM = "from_copy_ref";
	public static final String PATH_PARAM = "path";
	public static final String TTL_PARAM = "ttl";
	/**
	 * Thumbnail formats
	 * */
	public static final String THUMBNAIL_FORMAT_JPEG = "jpeg";
	public static final String THUMBNAIL_FORMAT_PNG = "png";
	/**
	 * Thumbnail sizes
	 * */
	public static final String THUMBNAIL_SIZE_XS = "xs";
	public static final String THUMBNAIL_SIZE_S = "s";
	public static final String THUMBNAIL_SIZE_M = "m";
	public static final String THUMBNAIL_SIZE_L = "l";
	public static final String THUMBNAIL_SIZE_XL = "xl";
	/**
	 * Media Protocols
	 * */
	public static final String MEDIA_PROTOCOL_HLS = "hls";
	public static final String MEDIA_PROTOCOL_RTMP = "rtmp";
	public static final String MEDIA_PROTOCOL_RTSP = "rtsp";
	public static final String MEDIA_PROTOCOL_SS = "ss";
	
	private ApiMode apiMode;
	private OAuthService service;
	private Token token;
	
	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessSecret;
	
	public MeoCloudImpl(String consumerKey, String consumerSecret, String accessToken, String accessSecret, ApiMode apiMode){
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.accessToken = accessToken ;
		this.accessSecret = accessSecret;
		this.apiMode = apiMode;
		service = new ServiceBuilder()
			.provider(MeoCloudOAuth10.class)
			.apiKey(consumerKey)
			.apiSecret(consumerSecret)
			.debug()
			.build();
		token = new Token(this.accessToken, this.accessSecret);
	}
	
	/**
	 * Static method to convert an input stream to a string. This method is useful when receiving for example
	 * the response from an HttpRequest.
	 * @param is the input stream to convert.
	 * @return String representation of the input stream data.
	 */
	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	/**
	 * Signs the http client request.
	 */
	private void signHttpRequest(HttpRequest request, String method, String completeUrl, List<NameValuePair> parameters){
		DefaultApi10a api = new SapoApi();
		String oauthHeader = null;
		Map<String, String> oauthParameters = new HashMap<>();
		oauthParameters.put(OAuthConstants.TOKEN, token.getToken());
		oauthParameters.put(OAuthConstants.TIMESTAMP, api.getTimestampService().getTimestampInSeconds());
		oauthParameters.put(OAuthConstants.NONCE, api.getTimestampService().getNonce());
		oauthParameters.put(OAuthConstants.CONSUMER_KEY, consumerKey);
		oauthParameters.put(OAuthConstants.SIGN_METHOD, api.getSignatureService().getSignatureMethod());
		oauthParameters.put(OAuthConstants.VERSION, "1.0");
		/**
		 * Signature generation
		 * */
		String verb = OAuthEncoder.encode(method);
		String url = OAuthEncoder.encode(completeUrl.replaceAll("\\?.*", "").replace("\\:\\d{4}", ""));
		ParameterList params = new ParameterList();
		params.addAll(new ParameterList(oauthParameters));
		for(NameValuePair param: parameters)
			params.add(param.getName(), param.getValue());
		String paramsStr = params.sort().asOauthBaseString();
		String baseString = String.format("%s&%s&%s", verb, url, paramsStr);
		String signature = api.getSignatureService().getSignature(baseString, consumerSecret, accessSecret);
		oauthParameters.put(OAuthConstants.SIGNATURE, signature);
		StringBuilder oauthHeaderBuilder = new StringBuilder(oauthParameters.size() * 20); //20 corresponds to estimated param length
		String preamble = "OAuth ", paramSeparator = ", ";
		oauthHeaderBuilder.append(preamble);
		for(Map.Entry<String, String> entry: oauthParameters.entrySet()){
			if(oauthHeaderBuilder.length() > preamble.length())
				oauthHeaderBuilder.append(paramSeparator);
			oauthHeaderBuilder.append(String.format("%s=\"%s\"", entry.getKey(), OAuthEncoder.encode(entry.getValue())));
		}
		oauthHeader = oauthHeaderBuilder.toString();
		log.debug("OAuth signature header: {}", oauthHeader);
		request.addHeader(OAuthConstants.HEADER, oauthHeader);
	}
	
	private Response performRequest(Boolean content, String method, String aditionalPath, List<NameValuePair> parameters, Verb verb, boolean supportsMode){
		try{
			String path = String.format("/%s/%s", MEOCLOUD_API_VERSION, method);
			if(supportsMode)
				path = String.format("%s/%s", path, apiMode.getCode());
			if(aditionalPath != null)
				path = String.format("%s%s", path, aditionalPath);
			URIBuilder builder = new URIBuilder();
			builder.setScheme(MEOCLOUD_SCHEME);
			builder.setHost(content ? MEOCLOUD_API_CONTENT_ENDPOINT : MEOCLOUD_API_ENDPOINT);
			builder.setPath(path);
			if(verb == Verb.GET && parameters != null && parameters.size() > 0)
				builder.addParameters(parameters);
			String url = builder.build().toString();
			OAuthRequest request = new OAuthRequest(verb, url);
			if(verb == Verb.POST && parameters != null){
				for(NameValuePair parameter: parameters){
					request.addBodyParameter(parameter.getName(), parameter.getValue());
				}
			}
			service.signRequest(token, request);
			Response response = request.send();
			log.debug("Requested MeoCloudApi: {}", url);
			log.debug("Response code: {}", response.getCode());
			return response;
		} catch(URISyntaxException e){
			log.error("Error generating url.", e);
		}
		return null;
	}

	private <E> void process(MeoCloudResponse<E> meoCloudResponse, Integer responseCode){
		switch(responseCode){
			case HttpStatus.SC_BAD_REQUEST:
				break;
			case HttpStatus.SC_UNAUTHORIZED:
				meoCloudResponse.setError("Unauthorized request");
				break;
			case HttpStatus.SC_NOT_MODIFIED:
				meoCloudResponse.setMessage("Unmodified Content");
				break;
			case HttpStatus.SC_FORBIDDEN:
				break;
			case HttpStatus.SC_NOT_FOUND:
				break;
			case HttpStatus.SC_METHOD_NOT_ALLOWED:
				break;
			case HttpStatus.SC_NOT_ACCEPTABLE:
				meoCloudResponse.setMessage("Too many entries");
				break;
			case HttpStatus.SC_INTERNAL_SERVER_ERROR:
				break;
			case HttpStatus.SC_INSUFFICIENT_STORAGE:
				break;
			default:
				break;
		}
	}
	
	private String getResponseBody(Response response){
		String responseBody = null;
		try{
			responseBody = response.getBody();
		} catch(IllegalArgumentException e){
			log.error("Error caught getting response body.", e);
		}
		return responseBody;
	}
	
	/**
	 * Returns all metadata regarding a file or folder.
	 * @param pathName file or folder to retrieve the metadata
	 * @param fileLimit maximum entries returned. This method haves a limit of 25.000 entries, the default value is 10.000
	 * @param hash used to check if the file or folder contents have changed.
	 * @param includeDeleted specifies if the response includes deleted files and folders
	 * @param rev specifies a revision for a file or folder
	 * @return {@link MeoCloudResponse}
	 * */
	@Override
	public MeoCloudResponse<Metadata> metadata(String pathName, Integer fileLimit, String hash,
		Boolean list, Boolean includeDeleted, String rev) {
		List<NameValuePair> queryParameters = new ArrayList<>();
		if(fileLimit != null)
			queryParameters.add(new BasicNameValuePair(FILE_LIMIT_PARAM, fileLimit.toString()));
		if(hash != null)
			queryParameters.add(new BasicNameValuePair(HASH_PARAM, hash));
		if(list != null)
			queryParameters.add(new BasicNameValuePair(LIST_PARAM, list.toString()));
		if(includeDeleted != null)
			queryParameters.add(new BasicNameValuePair(INCLUDE_DELETED_PARAM, includeDeleted.toString()));
		if(rev != null)
			queryParameters.add(new BasicNameValuePair(REV_PARAM, rev));
		Response response = performRequest(false, MEOCLOUD_API_METHOD_METADATA, pathName, queryParameters, Verb.GET, true);
		if(response != null){
			String responseBody = getResponseBody(response);
			log.debug("Metadata response: {}", responseBody);
			MeoCloudResponse<Metadata> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null)
				meoCloudResponse.setResponse(Metadata.fromJson(responseBody, Metadata.class));
			else
				process(meoCloudResponse, response.getCode());
			return meoCloudResponse;
		}
		return null;
	}

	/**
	 * Returns the metadata relative to a shared file or folder.
	 * @param shareId the unique share identifier
	 * @param path path inside the share
	 * @param fileLimit limit the response from the server
	 * @return {@link MeoCloudResponse}
	 * 
	 * */
	@Override
	public MeoCloudResponse<Metadata> metadataShare(String shareId, String path, Integer fileLimit) {
		List<NameValuePair> queryParameters = new ArrayList<>();
		if(fileLimit != null)
			queryParameters.add(new BasicNameValuePair(FILE_LIMIT_PARAM, String.valueOf(fileLimit)));
		Response response = performRequest(false, MEOCLOUD_API_METHOD_METADATA_SHARE, String.format("/%s%s", shareId, path), queryParameters, Verb.GET, false);
		if(response != null){
			String responseBody = getResponseBody(response);
			log.debug("MetadataShare response: {}", responseBody);
			MeoCloudResponse<Metadata> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null)
				meoCloudResponse.setResponse(Metadata.fromJson(responseBody, Metadata.class));
			else
				process(meoCloudResponse, response.getCode());
			return meoCloudResponse;
		}
		return null;
	}

	@Override
	public MeoCloudResponse<List<Link>> listLinks() {
		Response response = performRequest(false, MEOCLOUD_API_METHOD_LIST_LINKS, null, null, Verb.GET, false);
		if(response != null){
			String responseBody = getResponseBody(response);
			log.debug("ListLinks response: {}", responseBody);
			MeoCloudResponse<List<Link>> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null){
				Type listOfLinkType = new TypeToken<List<Link>>(){}.getType();
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
				List<Link> links = gson.fromJson(response.getBody(), listOfLinkType);
				meoCloudResponse.setResponse(links);
			} else {
				process(meoCloudResponse, response.getCode());
			}
			return meoCloudResponse;
		}
		return null;
	}
	
	@Override
	public MeoCloudResponse<Boolean> deleteLink(String linkId){
		List<NameValuePair> parameters = new ArrayList<>();
		if(linkId != null)
			parameters.add(new BasicNameValuePair(SHARE_ID_PARAM, linkId));
		Response response = performRequest(false, MEOCLOUD_API_METHOD_DELETE_LINK, null, parameters, Verb.POST, false);
		if(response != null){
			MeoCloudResponse<Boolean> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			if(response.getCode() == HttpStatus.SC_OK)
				meoCloudResponse.setResponse(true);
			else
				process(meoCloudResponse, response.getCode());
			return meoCloudResponse;
		}
		return null;
	}
	
	@Override
	public MeoCloudResponse<ShareLink> shares(String pathName){
		Response response = performRequest(false, MEOCLOUD_API_METHOD_SHARES, pathName, null, Verb.POST, true);
		if(response != null){
			MeoCloudResponse<ShareLink> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			String responseBody = getResponseBody(response);
			log.debug("Shares response: {}", responseBody);
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null)
				meoCloudResponse.setResponse(ShareLink.fromJson(responseBody, ShareLink.class));
			else
				process(meoCloudResponse, response.getCode());
			return meoCloudResponse;
		}
		return null;
	}
	
	@Override
	public MeoCloudResponse<RequestId> shareFolder(String pathName, String email){
		List<NameValuePair> parameters = new ArrayList<>();
		if(email != null)
			parameters.add(new BasicNameValuePair(TO_EMAIL_PARAM, email));
		Response response = performRequest(false, MEOCLOUD_API_METHOD_SHARE_FOLDER, pathName, parameters, Verb.POST, true);
		if(response != null){
			MeoCloudResponse<RequestId> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			String responseBody = getResponseBody(response);
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null)
				meoCloudResponse.setResponse(RequestId.fromJson(responseBody, RequestId.class));
			else
				process(meoCloudResponse, response.getCode());
			return meoCloudResponse;
		}
		return null;
	}
	
	@Override
	public MeoCloudResponse<SharedFolderList> listSharedFolders(){
		Response response = performRequest(false, MEOCLOUD_API_METHOD_LIST_SHARED_FOLDERS, null, null, Verb.GET, false);
		if(response != null){
			MeoCloudResponse<SharedFolderList> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			String responseBody = getResponseBody(response);
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null){
				log.debug("ListSharedFolders response: {}", responseBody);
				meoCloudResponse.setResponse(SharedFolderList.fromJson(responseBody, SharedFolderList.class));
			} else
				process(meoCloudResponse, response.getCode());
			return meoCloudResponse;
		}
		return null;
	}

	@Override
	public MeoCloudResponse<Thumbnail> thumbnails(String path, ThumbnailFormat format, ThumbnailSize size) {
		List<NameValuePair> parameters = new ArrayList<>();
		if(format != null)
			parameters.add(new BasicNameValuePair(FORMAT_PARAM, format.getFormat()));
		if(size != null)
			parameters.add(new BasicNameValuePair(SIZE_PARAM, size.getSize()));
		Response response = performRequest(true, MEOCLOUD_API_METHOD_THUMBNAILS, path, parameters, Verb.GET, true);
		if(response != null){
			MeoCloudResponse<Thumbnail> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			InputStream is = response.getStream();
			if(response.getCode() == HttpStatus.SC_OK && is != null){
				try {
					byte[] thumbnailBytes = IOUtils.toByteArray(is);
					Thumbnail t = new Thumbnail(thumbnailBytes);
					meoCloudResponse.setResponse(t);
				} catch (IOException e) {
					log.error("Could not extract thumbnail data.", e);
				}
			} else {
				process(meoCloudResponse, response.getCode());
			}
			return meoCloudResponse;
		}
		return null;
	}

	@Override
	public MeoCloudResponse<List<Metadata>> search(String path, String query,
			String mimeType, Integer fileLimit, Boolean includeDeleted) {
		List<NameValuePair> parameters = new ArrayList<>();
		if(query != null)
			parameters.add(new BasicNameValuePair(QUERY_PARAM, query));
		if(mimeType != null)
			parameters.add(new BasicNameValuePair(MIME_TYPE_PARAM, mimeType));
		if(fileLimit != null)
			parameters.add(new BasicNameValuePair(FILE_LIMIT_PARAM, String.valueOf(fileLimit)));
		if(includeDeleted != null)
			parameters.add(new BasicNameValuePair(INCLUDE_DELETED_PARAM, String.valueOf(includeDeleted)));
		Response response = performRequest(false, MEOCLOUD_API_METHOD_SEARCH, path, parameters, Verb.GET, true);
		if(response != null){
			MeoCloudResponse<List<Metadata>> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			String responseBody = getResponseBody(response);
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null){
				Type listOfLinkMetadata = new TypeToken<List<Metadata>>(){}.getType();
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
				List<Metadata> searchResults = gson.fromJson(responseBody, listOfLinkMetadata);
				meoCloudResponse.setResponse(searchResults);
			} else
				process(meoCloudResponse, response.getCode());
			return meoCloudResponse;
		}
		return null;
	}

	@Override
	public MeoCloudResponse<List<Metadata>> revisions(String path,
			Integer revLimit) {
		List<NameValuePair> parameters = new ArrayList<>();
		if(revLimit != null){
			parameters.add(new BasicNameValuePair(REVLIMIT_PARAM, String.valueOf(revLimit)));
		}
		Response response = performRequest(false, MEOCLOUD_API_METHOD_REVISIONS, path, parameters, Verb.GET, true);
		if(response != null){
			MeoCloudResponse<List<Metadata>> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			String responseBody = getResponseBody(response);
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null){
				Type listOfMetadata = new TypeToken<List<Metadata>>(){}.getType();
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
				List<Metadata> revisions = gson.fromJson(responseBody, listOfMetadata);
				meoCloudResponse.setResponse(revisions);
			} else
				process(meoCloudResponse, response.getCode());
			return meoCloudResponse;
		}
		return null;
	}
	
	public MeoCloudResponse<Metadata> restore(String path, String revision){
		List<NameValuePair> parameters = new ArrayList<>();
		if(revision != null)
			parameters.add(new BasicNameValuePair(REV_PARAM, revision));
		Response response = performRequest(false, MEOCLOUD_API_METHOD_RESTORE, path, parameters, Verb.POST, true);
		if(response != null){
			MeoCloudResponse<Metadata> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			String responseBody = getResponseBody(response);
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null){
				Metadata metadata = Metadata.fromJson(responseBody, Metadata.class);
				meoCloudResponse.setResponse(metadata);
			} else
				process(meoCloudResponse, response.getCode());
			return meoCloudResponse;
		}
		return null;
	}
	
	public MeoCloudResponse<MediaLink> media(String path, Boolean downloadFallback, MediaProtocol protocol){
		List<NameValuePair> parameters = new ArrayList<>();
		if(downloadFallback != null)
			parameters.add(new BasicNameValuePair(DOWNLOAD_FALLBACK_PARAM, String.valueOf(downloadFallback)));
		if(protocol != null)
			parameters.add(new BasicNameValuePair(PROTOCOL_PARAM, protocol.getMediaProtocol()));
		Response response = performRequest(false, MEOCLOUD_API_METHOD_MEDIA, path, parameters, Verb.POST, true);
		if(response != null){
			MeoCloudResponse<MediaLink> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			String responseBody = getResponseBody(response);
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null){
				MediaLink mediaLink = MediaLink.fromJson(responseBody, MediaLink.class);
				meoCloudResponse.setResponse(mediaLink);
			} else
				process(meoCloudResponse, response.getCode());
			return meoCloudResponse;
		}
		return null;
	}

	@Override
	public MeoCloudResponse<File> downloadFile(String path, String revision) {
		try{
			String builderPath = String.format("/%s/%s/%s%s", MEOCLOUD_API_VERSION, MEOCLOUD_API_METHOD_FILES, apiMode.getCode(), path);
			URIBuilder builder = new URIBuilder();
			builder.setScheme(MEOCLOUD_SCHEME);
			builder.setHost(MEOCLOUD_API_CONTENT_ENDPOINT);
			builder.setPath(builderPath);
			List<NameValuePair> parameters = new LinkedList<>();
			if(revision != null){
				builder.addParameter(REV_PARAM, revision);
				parameters.add(new BasicNameValuePair(REV_PARAM, revision));
			}
			String apiCall = builder.build().toString();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet getRequest = new HttpGet(apiCall);
			signHttpRequest(getRequest, "GET", apiCall, parameters);
			HttpResponse httpResponse = client.execute(getRequest);
			Integer statusCode = httpResponse.getStatusLine().getStatusCode();
			MeoCloudResponse<File> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(statusCode);
			if(statusCode == HttpStatus.SC_OK){
				InputStream is = httpResponse.getEntity().getContent();
				File tempFile = new File(UUID.randomUUID().toString());
				FileOutputStream fos = new FileOutputStream(tempFile);
				byte[] buffer = new byte[1024 * 100];
				int nBytes = -1;
				while((nBytes = is.read(buffer)) != -1){
					fos.write(buffer, 0, nBytes);
					fos.flush();
				}
				is.close();
				fos.close();
				meoCloudResponse.setResponse(tempFile);
			} else
				process(meoCloudResponse, statusCode);
			return meoCloudResponse;
		} catch(Exception e){
			log.error("Exception caught downloading file.", e);
		}
		return null;
	}

	/**
	 * Max file size: 2048MB
	 * Disallowed characters: +*<>:"/\|?*
	 * */
	@Override
	public MeoCloudResponse<Metadata> uploadFile(String filePath, String path,
			Boolean overwrite, String parentRevision) {
		try{
			String builderPath = String.format("/%s/%s/%s%s", MEOCLOUD_API_VERSION, MEOCLOUD_API_METHOD_FILES, apiMode.getCode(), path);
			URIBuilder builder = new URIBuilder();
			builder.setScheme(MEOCLOUD_SCHEME);
			builder.setHost(MEOCLOUD_API_CONTENT_ENDPOINT);
			builder.setPath(builderPath);
			List<NameValuePair> parameters = new LinkedList<>();
			if(overwrite != null){
				builder.addParameter(OVERWRITE_PARAM, overwrite.toString());
				parameters.add(new BasicNameValuePair(OVERWRITE_PARAM, overwrite.toString()));
			}
			if(parentRevision != null){
				builder.addParameter(PARENT_REV_PARAM, parentRevision);
				parameters.add(new BasicNameValuePair(PARENT_REV_PARAM, parentRevision));
			}
			String apiCall = builder.build().toString();
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(apiCall);
			signHttpRequest(post, "POST", apiCall, parameters);
			FileEntity fileEntity = new FileEntity(new File(filePath));
//			fileEntity.setChunked(true);
			post.setEntity(fileEntity);
			HttpResponse response = client.execute(post);
			Integer statusCode = response.getStatusLine().getStatusCode();
			MeoCloudResponse<Metadata> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(statusCode);
			if(statusCode == HttpStatus.SC_OK){
				String responseBody = convertStreamToString(response.getEntity().getContent());
				Metadata metatada = Metadata.fromJson(responseBody, Metadata.class);
				meoCloudResponse.setResponse(metatada);
			} else
				process(meoCloudResponse, statusCode);
		} catch(Exception e){
			log.error("Exception caught uploading file.", e);
		}
		return null;
	}

	@Override
	public MeoCloudResponse<Metadata> copy(String fromPath, String toPath,
			String fromCopyRef) {
		List<NameValuePair> parameters = new ArrayList<>();
		parameters.add(new BasicNameValuePair(ROOT_PARAM, this.apiMode.getCode()));
		if(fromPath != null)
			parameters.add(new BasicNameValuePair(FROM_PATH_PARAM, fromPath));
		if(toPath != null)
			parameters.add(new BasicNameValuePair(TO_PATH_PARAM, toPath));
		if(fromCopyRef != null)
			parameters.add(new BasicNameValuePair(FROM_COPY_REF_PARAM, fromCopyRef));
		Response response = performRequest(false, MEOCLOUD_API_METHOD_COPY, null, parameters, Verb.POST, false);
		if(response != null){
			MeoCloudResponse<Metadata> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			String responseBody = getResponseBody(response);
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null){
				Metadata metadata = Metadata.fromJson(responseBody, Metadata.class);
				meoCloudResponse.setResponse(metadata);
			} else 
				process(meoCloudResponse, response.getCode());
			return meoCloudResponse;
		}
		return null;
	}

	@Override
	public MeoCloudResponse<CopyRef> copyRef(String path) {
		Response response = performRequest(false, MEOCLOUD_API_METHOD_COPY_REF, path, null, Verb.GET, true);
		if(response != null){
			MeoCloudResponse<CopyRef> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			String responseBody = getResponseBody(response);
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null){
				CopyRef copyRef = CopyRef.fromJson(responseBody, CopyRef.class);
				meoCloudResponse.setResponse(copyRef);
			} else
				process(meoCloudResponse, response.getCode());
			return meoCloudResponse;
		}
		return null;
	}

	@Override
	public MeoCloudResponse<Metadata> delete(String path) {
		List<NameValuePair> parameters = new ArrayList<>();
		parameters.add(new BasicNameValuePair(ROOT_PARAM, apiMode.getCode()));
		if(path != null)
			parameters.add(new BasicNameValuePair(PATH_PARAM, path));
		Response response = performRequest(false, MEOCLOUD_API_METHOD_DELETE, null, parameters, Verb.POST, false);
		if(response != null){
			MeoCloudResponse<Metadata> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			String responseBody = getResponseBody(response);
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null){
				Metadata metadata = Metadata.fromJson(responseBody, Metadata.class);
				meoCloudResponse.setResponse(metadata);
			} else
				process(meoCloudResponse, response.getCode());
			return meoCloudResponse;
		}
		return null;
	}

	@Override
	public MeoCloudResponse<Metadata> move(String fromPath, String toPath) {
		List<NameValuePair> parameters = new ArrayList<>();
		parameters.add(new BasicNameValuePair(ROOT_PARAM, this.apiMode.getCode()));
		if(fromPath != null)
			parameters.add(new BasicNameValuePair(FROM_PATH_PARAM, fromPath));
		if(toPath != null)
			parameters.add(new BasicNameValuePair(TO_PATH_PARAM, toPath));
		Response response = performRequest(false, MEOCLOUD_API_METHOD_MOVE, null, parameters, Verb.POST, false);
		if(response != null){
			MeoCloudResponse<Metadata> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			String responseBody = getResponseBody(response);
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null){
				Metadata metadata = Metadata.fromJson(responseBody, Metadata.class);
				meoCloudResponse.setResponse(metadata);
			} else 
				process(meoCloudResponse, response.getCode());
			return meoCloudResponse;
		}
		return null;
	}

	@Override
	public MeoCloudResponse<Metadata> createFolder(String path) {
		List<NameValuePair> parameters = new ArrayList<>();
		parameters.add(new BasicNameValuePair(ROOT_PARAM, apiMode.getCode()));
		if(path != null)
			parameters.add(new BasicNameValuePair(PATH_PARAM, path));
		Response response = performRequest(false, MEOCLOUD_API_METHOD_CREATE_FOLDER, null, parameters, Verb.POST, false);
		if(response != null){
			MeoCloudResponse<Metadata> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			String responseBody = getResponseBody(response);
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null){
				Metadata metadata = Metadata.fromJson(responseBody, Metadata.class);
				meoCloudResponse.setResponse(metadata);
			} else
				process(meoCloudResponse, response.getCode());
			return meoCloudResponse;
		}
		return null;
	}

	@Override
	public MeoCloudResponse<Metadata> undeleteTree(String path) {
		List<NameValuePair> parameters = new ArrayList<>();
		parameters.add(new BasicNameValuePair(ROOT_PARAM, apiMode.getCode()));
		if(path != null)
			parameters.add(new BasicNameValuePair(PATH_PARAM, path));
		Response response = performRequest(false, MEOCLOUD_API_METHOD_UNDELETE_TREE, null, parameters, Verb.POST, false);
		if(response != null){
			MeoCloudResponse<Metadata> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			String responseBody = getResponseBody(response);
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null){
				Metadata metadata = Metadata.fromJson(responseBody, Metadata.class);
				meoCloudResponse.setResponse(metadata);
			} else
				process(meoCloudResponse, response.getCode());
			return meoCloudResponse;
		}
		return null;
	}

	@Override
	public MeoCloudResponse<AccountInfo> accountInfo() {
		Response response = performRequest(false, MEOCLOUD_API_METHOD_ACOUNT_INFO, null, null, Verb.GET, false);
		if(response != null){
			MeoCloudResponse<AccountInfo> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			String responseBody = getResponseBody(response);
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null){
				log.debug("Response body: {}", responseBody);
				AccountInfo accountInfo = AccountInfo.fromJson(responseBody, AccountInfo.class);
				meoCloudResponse.setResponse(accountInfo);
			} else
				process(meoCloudResponse, response.getCode());
			return meoCloudResponse;
		}
		return null;
	}

	@Override
	public MeoCloudResponse<List<Link>> listUploadLinks() {
		Response response = performRequest(false, MEOCLOUD_API_METHOD_LIST_UPLOAD_LINKS, null, null, Verb.GET, false);
		if(response != null){
			String responseBody = getResponseBody(response);
			log.debug("ListLinks response: {}", responseBody);
			MeoCloudResponse<List<Link>> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null){
				Type listOfLinkType = new TypeToken<List<Link>>(){}.getType();
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
				List<Link> links = gson.fromJson(response.getBody(), listOfLinkType);
				meoCloudResponse.setResponse(links);
			} else {
				process(meoCloudResponse, response.getCode());
			}
			return meoCloudResponse;
		}
		return null;
	}

	@Override
	public MeoCloudResponse<ShareLink> uploadLink(String pathName, Long linkTtl) {
		List<NameValuePair> parameters = new ArrayList<>();
		if(linkTtl != null)
			parameters.add(new BasicNameValuePair(TTL_PARAM, linkTtl.toString()));
		Response response = performRequest(false, MEOCLOUD_API_METHOD_SHARE_FOLDER, pathName, parameters, Verb.POST, true);
		if(response != null){
			MeoCloudResponse<ShareLink> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			String responseBody = getResponseBody(response);
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null)
				meoCloudResponse.setResponse(ShareLink.fromJson(responseBody, ShareLink.class));
			else
				process(meoCloudResponse, response.getCode());
			return meoCloudResponse;
		}
		return null;
	}

	@Override
	public MeoCloudResponse<Empty> setLinkTtl(Long linkTtl, String linkId) {
		List<NameValuePair> parameters = new ArrayList<>();
		if(linkTtl != null)
			parameters.add(new BasicNameValuePair(TTL_PARAM, linkTtl.toString()));
		if(linkId != null)
			parameters.add(new BasicNameValuePair(SHARE_ID_PARAM, linkId));
		Response response = performRequest(false, MEOCLOUD_API_METHOD_SHARE_FOLDER, null, parameters, Verb.POST, true);
		if(response != null){
			MeoCloudResponse<Empty> meoCloudResponse = new MeoCloudResponse<>();
			meoCloudResponse.setCode(response.getCode());
			String responseBody = getResponseBody(response);
			if(response.getCode() == HttpStatus.SC_OK && responseBody != null)
				meoCloudResponse.setResponse(Empty.fromJson(responseBody, Empty.class));
			else
				process(meoCloudResponse, response.getCode());
			return meoCloudResponse;
		}
		return null;
	}

}
