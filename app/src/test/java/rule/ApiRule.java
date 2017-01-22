package rule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import russier.laurent.fr.cleanarchitecture.Api;

import static org.junit.Assert.assertTrue;

public class ApiRule implements TestRule {

    private Api api;
    private MockWebServer mockServer = new MockWebServer();

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                setUp();
                base.evaluate();
                tearDown();
            }
        };
    }

    private void setUp() {
        api = new Api(mockServer.url("/").toString(), new OkHttpClient());
    }

    private void tearDown() throws IOException {
        mockServer.shutdown();
    }

    public String getJsonFromFile(String file) {
        System.out.println("MockWebServer load file : " + file);
        InputStream inputStreamResponse = ApiRule.class.getClassLoader().getResourceAsStream(file);
        int size;
        try {
            size = inputStreamResponse.available();
            byte[] buffer = new byte[size];
            inputStreamResponse.read(buffer);
            inputStreamResponse.close();
            return new String(buffer);
        } catch (IOException e) {
            assertTrue(false);
            return "";
        }
    }

    public MockWebServer getMockServer() {
        return mockServer;
    }

    public Api getApi() {
        return api;
    }
}
