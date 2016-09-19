package framework.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

public abstract class HttpClient {
    public boolean getRequest() throws Exception {
        String apiUri = getAPIUri();
        if (apiUri.isEmpty()) {
            return false;
        }
        Logger.debug("Request Url:\r\n" + apiUri);

        String responseString = HttpUtils.GetRequest(new HttpGet(apiUri), (HttpResponse httpResponse) -> {
            return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        });

        Logger.debug("Response Data:\r\n" + responseString);

        return parseResponse(responseString);
    }

    public boolean postRequest(String postData) throws Exception {
        String apiUri = getAPIUri();
        if (apiUri.isEmpty()) {
            return false;
        }
        Logger.debug("Request Url:\r\n" + apiUri);

        Logger.debug("Reqest Data:\r\n" + postData);

        HttpPost httpPost = new HttpPost(apiUri);
        StringEntity postEntity = new StringEntity(postData, "UTF-8");
        httpPost.setEntity(postEntity);

        String responseString = new String();
        try {
            responseString = HttpUtils.PostRequest(httpPost, (HttpResponse httpResponse) -> {
                return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            });
        }
        finally {
            httpPost.abort();
        }

        Logger.debug("Response Data:\r\n" + responseString);

        return parseResponse(responseString);
    }

    protected abstract String getAPIUri();

    protected boolean parseResponse(String responseString) throws Exception {
        return true;
    }
}
