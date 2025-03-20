package com.booking.api.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.booking.api.dto.RestaurantDTO;

@Service
public class RestaurantServiceClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String restaurantServiceUrl = "http://localhost:5000/restaurants"; // Adjust URL

    public RestaurantDTO getRestaurantById(Long restaurantId) {
        String url = restaurantServiceUrl + "/" + restaurantId;
        return restTemplate.getForObject(url, RestaurantDTO.class);
    }

    public List<RestaurantDTO> getAllRestaurants() {
    RestaurantDTO[] restaurants = restTemplate.getForObject(restaurantServiceUrl, RestaurantDTO[].class);
        return restaurants != null ? Arrays.asList(restaurants) : Collections.emptyList();
    }

}
