package pl.dashboard.nbp;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

import static java.net.http.HttpResponse.BodyHandlers.ofString;

class FxRateProvider {
    String forDate(String date) throws IOException, InterruptedException {
        var nbpApiUrl = String.format("http://api.nbp.pl/api/exchangerates/tables/C/%s/", date);
        var httpResponse = createHttpClient().send(createHttpRequest(nbpApiUrl), ofString());
        if (httpResponse.statusCode() != 200) {
            throw new RuntimeException(String.format("Received %s : %s from %s", httpResponse.statusCode(), httpResponse.body(), nbpApiUrl));
        }
        return httpResponse.body();
    }

    private HttpRequest createHttpRequest(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(30))
                .header("Content-Type", "application/xml")
                .GET()
                .build();
    }

    private HttpClient createHttpClient() {
        return HttpClient.newBuilder()
                .build();
    }

}
