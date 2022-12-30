package by.epam.ivanchenko.client_part;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MeasurementAddingClient {
    public static void main(String[] args) {

        String sensorName = "Sensor 123";
        registerSensor(sensorName);

        Random random = new Random();

        double minTemperature = 0.0;
        double maxTemperature = 50.0;

        for (int i = 0; i < 500; i++) {
            System.out.println(i);

           addMeasurement(random.nextDouble() * maxTemperature,random.nextBoolean(), sensorName);                                // nextDouble() return number between 0..1
        }
    }

    private static void registerSensor(String sensorName) {
        String urlRegisterSensor = "http://localhost:8080/sensors/registration";
        Map<String, Object> jsonNewSensor = new HashMap<>();
        jsonNewSensor.put("name", sensorName);

        makePostRequest(jsonNewSensor, urlRegisterSensor);
    }

    private static void makePostRequest(Map<String, Object> jsonNewSensor, String urlRegisterSensor) {
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> jsonToSend = new HttpEntity<>(jsonNewSensor, headers);

       try {
           restTemplate.postForObject(urlRegisterSensor, jsonToSend, String.class);
           System.out.println("Changes are sended to server");
       } catch (HttpClientErrorException exception) {
           System.out.println("Error! " + exception.getMessage());
       }
    }

    private static  void addMeasurement(double temperature, boolean raining, String sensorName) {
        String urlAddMeasurement = "http://localhost:8080/measurements/add";

        Map<String, Object> jsonAddMeasurement = new HashMap<>();
        jsonAddMeasurement.put("value", temperature);
        jsonAddMeasurement.put("raining", raining);
        jsonAddMeasurement.put("sensor", Map.of("name", sensorName));

        makePostRequest(jsonAddMeasurement, urlAddMeasurement);
    }
}
