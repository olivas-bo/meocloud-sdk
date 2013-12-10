package pt.meocloud.sdk.data;

import java.io.Serializable;


public class MeoCloudResponse<E> implements Serializable {

	private static final long serialVersionUID = -6555774243911636390L;
	
	private Integer code;
	private String message;
	private String error;
	private Object response;
	
	public MeoCloudResponse(){
		
	}
	
	public MeoCloudResponse(Integer code, String error, String message, E response){
		this.code = code;
		this.error = error;
		this.message = message;
		this.response = response;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@SuppressWarnings("unchecked")
	public E getResponse() {
		return (E) response;
	}

	public void setResponse(E response) {
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
