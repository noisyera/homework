package homework03.httpclient;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpClient01 {
    public FullHttpResponse httpclientRequest(String uri){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        FullHttpResponse responses = null;
        try {
            HttpGet httpget = new HttpGet("http://127.0.0.1:8000"+uri);

            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<byte[]> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    byte[] body = EntityUtils.toByteArray(entity);
                    return entity != null ? body : null;
//                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            System.out.println("________________________________+++++++++++++++++++++++++_________________________________");
            System.out.println(responseHandler);
            byte[] body = httpclient.execute(httpget, responseHandler);

            System.out.println("----------------------------------------");

            responses = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));

            responses.headers().set("Content-Type", "application/json");
            responses.headers().set("Transfer-Encoding", "chunked");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responses;
    }
}
