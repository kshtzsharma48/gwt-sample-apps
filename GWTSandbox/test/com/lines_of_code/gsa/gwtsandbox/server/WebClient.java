package com.lines_of_code.gsa.gwtsandbox.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class WebClient {

	public String getContent(URL url) {
		StringBuffer content = new StringBuffer();
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			InputStream is = connection.getInputStream();
			byte[] buffer = new byte[2048];
			int count;
			while(-1 != (count = is.read(buffer))) {
				content.append(new String(buffer, 0, count));
			}
		}
		catch(IOException e) {
			return null;
		}
		return content.toString();
	}
	
	public void postFile(URL url, File f) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url.toString());
		FileEntity reqEntitiy = new FileEntity(f, "application/excel");
		
		// TODO: fix boundary
		reqEntitiy.setContentType("multipart/form-data; boundary=boundary42");
		reqEntitiy.setChunked(true);
		
		httpPost.setEntity(reqEntitiy);
		System.out.println("executing request " + httpPost.getRequestLine());
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity resEntity = response.getEntity();
		
		System.out.println(response.getStatusLine());
		
		if (resEntity != null) {
			resEntity.consumeContent();
		}
		
		httpClient.getConnectionManager().shutdown();
	}
}
