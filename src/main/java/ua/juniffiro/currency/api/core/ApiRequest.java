package ua.juniffiro.currency.api.core;

import okhttp3.Request;
import okhttp3.Response;
import ua.juniffiro.currency.api.utils.HttpUtils;

import java.io.IOException;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 24/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class ApiRequest {

    private final String url;

    public ApiRequest(String url) {
        this.url = url;
    }

    /**
     * Send the request by URL and get
     * the data in the callback.
     *
     * @param callback
     *        Where we get the data and
     *        further handle it here
     * @throws IOException
     *         Thrown in case of an unsuccessful
     *         request to the service
     */
    public void send(Callback<String> callback) throws IOException {
        Request req = new Request.Builder()
                .url(url)
                .get()
                .build();
        try (Response response = HttpUtils.HTTP_CLIENT.newCall(req).execute()) {
            callback.handle(response.body().string());
        }
    }
}
