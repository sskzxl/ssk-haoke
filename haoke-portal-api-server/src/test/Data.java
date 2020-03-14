import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Data {
    private RestClient restClient;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Before
    public void init(){
        RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost("192.168.19.131",9200),
                new HttpHost("192.168.19.131",9201)
        );
        this.restClient = restClientBuilder.build();
    }
    @After
    public void close() throws IOException {
        restClient.close();
    }
    @Test
    public void tesBulk() throws Exception {
        Request request = new Request("POST", "/haoke/house/_bulk");
        List<String> lines = FileUtils.readLines(new File("C:\\Users\\ssk\\Desktop\\tb_house_resources.json"),
                "UTF-8");
        String createStr = "{\"index\": {\"_index\":\"haoke\",\"_type\":\"house\"}}";
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (String line : lines) {
                sb.append(createStr + "\n" + line + "\n");
                if (count >= 100) {
                    request.setJsonEntity(sb.toString());
                    Response response = this.restClient.performRequest(request);
                    System.out.println("请求完成 -> " + response.getStatusLine());
                    System.out.println(EntityUtils.toString(response.getEntity()));
                    count = 0;
                    sb = new StringBuilder();
                }
                count++;
            }
        }


}
