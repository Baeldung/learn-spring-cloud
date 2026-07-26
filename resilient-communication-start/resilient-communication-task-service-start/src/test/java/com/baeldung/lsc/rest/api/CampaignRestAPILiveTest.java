package com.baeldung.lsc.rest.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import com.baeldung.lsc.web.dto.CampaignDto;

@SpringBootTest
public class CampaignRestAPILiveTest {

    private static final String BASE_URL = "http://localhost:8080/campaigns";
    private static final Random random = new Random();

    private RestClient restClient = RestClient.create();

    @Test
    public void givenCampaignExists_whenGet_thenSuccess() {
        ResponseEntity<CampaignDto> response = restClient.get()
                .uri(BASE_URL + "/1")
                .retrieve()
                .toEntity(CampaignDto.class);

        assertSame(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void givenNewCampaign_whenCreated_thenSuccess() {
        int index = random.nextInt(10000);
        String code = "C" + index;
        String name = "Campaign " + index;
        String description = "Description of Campaign " + index;
        CampaignDto newCampaign = new CampaignDto(null, code, name, description);
        ResponseEntity<CampaignDto> response = restClient.post()
                .uri(BASE_URL)
                .body(newCampaign)
                .retrieve()
                .toEntity(CampaignDto.class);

        assertSame(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(code, response.getBody().code());
    }

}
