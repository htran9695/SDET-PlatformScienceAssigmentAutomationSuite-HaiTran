package utils;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.*;


/**
 * Main class to handle all http transactions
 */
public class HttpBuilder {
    private final CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpResponse httpresponse;
    HttpUriRequest httpmethod;
    private JSONObject output;

    private final String uri;
    private final String protocol;


    /**
     * Builder that take in user infos to populate token, list of team Ids and user Id
     *
     * @param uri String of URI defined by the user
     * @param protocol String of protocol used defined by the user
     */
    public HttpBuilder(String uri, String protocol){
        this.uri = uri;
        this.protocol = protocol;
    }

     /**
     * Method that take in users input to form a request e.g: builder.sendRequest(GET,"/sample/","{"id":"sample_id"}")
     *
     * @param method string define the http/https method
     * @param path path to the endpoint
     * @param payLoad body of the request
     * @return
     * @throws IOException
     */
    public JSONObject sendRequest(String method, String path, String payLoad) throws IOException {
        method = method.toLowerCase();
        RequestBuilder reqbuilder;
        //Setting URI and parameters
        switch (method) {
            case "post":
                reqbuilder = RequestBuilder.post();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + method);
        }
        reqbuilder.setUri(buildUrl(path))
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .setHeader(HttpHeaders.ACCEPT, "*/*");
        if (payLoad != null) {
            StringEntity entity = new StringEntity(payLoad);
            reqbuilder.setEntity(entity);
        }
        httpmethod = reqbuilder.build();
        this.httpresponse = httpclient.execute(httpmethod);
        //Printing the status and the contents of the response
        String contentType = httpresponse.getFirstHeader("Content-Type") == null ? null : httpresponse.getFirstHeader("Content-Type").toString();
        String responseBody= "";
        try {
            responseBody = EntityUtils.toString(httpresponse.getEntity());
        }
        catch (Exception e){
            System.out.println("Cannot parse response body, set to empty");
            responseBody = "";
        }
        output = new JSONObject()
                .put("response code",httpresponse.getStatusLine().getStatusCode())
                .put("response body", responseBody)
                .put("content-type", contentType);
        System.out.println(output.getString("response body"));
        System.out.println(output.getInt("response code"));
        return output;

    }


    /**
     * This is a private method that is used to build the URL String
     *
     * @param path combine the protocal and uri with the string path defined by the user
     * @return
     */
    private String buildUrl(String path){
        return protocol + "://" + uri + path;
    }

}
