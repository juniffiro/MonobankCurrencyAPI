package ua.juniffiro.currency.api.currency.test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 04/03/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class JsonRequestTest {

    @Test
    public void testJsonRequest() throws IOException {
        OkHttpClient httpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.monobank.ua/bank/currency")
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            String json = response.body().string();
            System.out.println(json);
        }
    }
}
