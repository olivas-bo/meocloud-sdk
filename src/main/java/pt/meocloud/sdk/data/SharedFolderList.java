package pt.meocloud.sdk.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import pt.meocloud.sdk.data.json.JsonObject;

public class SharedFolderList extends JsonObject {
	
	@Expose
	@SerializedName("shared_folders")
	private List<SharedFolder> sharedFolders;
	
	public SharedFolderList(){
		sharedFolders = new ArrayList<>();
	}

	public List<SharedFolder> getSharedFolders() {
		return sharedFolders;
	}

	public void setSharedFolders(List<SharedFolder> sharedFolders) {
		this.sharedFolders = sharedFolders;
	}
	
	public static <T> T fromJson(String json, Class<T> clazz){
		Gson gson = new GsonBuilder().registerTypeAdapter(SharedFolderList.class, new SharedFolderListTypeAdapter()).create();
		return gson.fromJson(json, clazz);
	}
	
	private static class SharedFolderListTypeAdapter extends TypeAdapter<SharedFolderList> {

		@Override
		public SharedFolderList read(JsonReader reader) throws IOException {
			SharedFolderList sharedFolderList = new SharedFolderList();
			reader.beginObject();
			while(reader.peek().compareTo(JsonToken.NAME) == 0){
				//Shared folder object
				SharedFolder sharedFolder = new SharedFolder();
				String id = reader.nextName();
				sharedFolder.setId(id);
				reader.beginObject();
				while(reader.peek().compareTo(JsonToken.NAME) == 0){
					String name = reader.nextName();
					switch(name){
						case SharedFolder.JSON_TAG_FOLDER_TYPE:
							sharedFolder.setFolderType(reader.nextString());
							break;
						case SharedFolder.JSON_TAG_IS_OWNER:
							sharedFolder.setIsOwner(reader.nextBoolean());
							break;
						case SharedFolder.JSON_TAG_SHARED_FOLDER_PATH:
							sharedFolder.setSharedFolderPath(reader.nextString());
							break;
						case SharedFolder.JSON_TAG_USERS:
							sharedFolder.setUsers(readUsers(reader));
							break;
						default:
							reader.skipValue();
							break;
					}
				}
				reader.endObject();
				sharedFolderList.sharedFolders.add(sharedFolder);
			}
			reader.endObject();
			return sharedFolderList;
		}
		
		private List<User> readUsers(JsonReader reader) throws IOException {
			List<User> users = new ArrayList<User>();
			reader.beginArray();
			while(reader.peek().compareTo(JsonToken.BEGIN_OBJECT) == 0){
				reader.beginObject();
				User user = new User();
				while(reader.peek().compareTo(JsonToken.NAME) == 0){
					String name = reader.nextName();
					switch(name){
						case User.JSON_TAG_ID:
							user.setId(reader.nextString());
							break;
						case User.JSON_TAG_EMAIL:
							user.setEmail(reader.nextString());
							break;
						case User.JSON_TAG_NAME:
							user.setName(reader.nextString());
							break;
						case User.JSON_TAG_OWNER:
							user.setOwner(reader.nextBoolean());
							break;
						case User.JSON_TAG_USER:
							user.setUser(reader.nextBoolean());
							break;
						default:
							reader.skipValue();
							break;
					}
				}
				users.add(user);
				reader.endObject();
			}
			reader.endArray();
			return users;
		}

		@Override
		public void write(JsonWriter writter, SharedFolderList list)
				throws IOException {
			// TODO Auto-generated method stub
			
		}
		
	}

}
