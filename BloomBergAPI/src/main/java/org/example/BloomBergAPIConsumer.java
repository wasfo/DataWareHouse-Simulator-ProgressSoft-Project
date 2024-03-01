package org.example;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class BloomBergAPIConsumer {
    private final static List<String> ISOCodes;
    private static final Logger LOGGER = LoggerFactory.getLogger(BloomBergAPIConsumer.class);
    private final static RestTemplate restTemplate = new RestTemplate();

    static {
        try {
            LOGGER.info("Fetching ISO Codes");
            ISOCodes = scrapeISOCodes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(fixedRate = 10000)
    public void sendFxDealsToLoadBalancer() throws IOException {

        FxDeal fxDeal = getRandomMockFxDeal();

        String url = "http://load-balancer:8085/loadBalancer/save/fxDeal";

        LOGGER.info("Sending FxDeals with id {} with URL {}", fxDeal.getUniqueId(), url);

        HttpEntity<FxDeal> entity = new HttpEntity<>(fxDeal);
        restTemplate.exchange(url,
                HttpMethod.POST,
                entity,
                Void.class);

    }

    public FxDeal getRandomMockFxDeal() throws IOException {
        Random random = new Random();
        int rand1 = random.nextInt(ISOCodes.size());
        int rand2 = random.nextInt(ISOCodes.size());
        int rand3 = random.nextInt(5000);
        return new FxDeal(UUID.randomUUID().toString(),
                ISOCodes.get(rand1),
                ISOCodes.get(rand2),
                LocalDateTime.now(),
                rand3);
    }

    static public List<String> scrapeISOCodes() throws IOException {
        List<String> list = new ArrayList<>();
        String url = "https://docs.1010data.com/1010dataReferenceManual/DataTypesAndFormats/currencyUnitCodes.html";
        Document doc = Jsoup.connect(url)
                .timeout(10000).get();

        Elements body = doc.select("tbody.tbody");
        for (Element e : body.select("tr")) {
            String text = e.select("td").get(2).text();
            list.add(text);
        }
        return list;
    }

}
