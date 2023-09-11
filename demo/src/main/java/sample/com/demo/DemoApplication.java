package sample.com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import java.net.InetAddress;
import java.io.*;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping(value = "/microservice/health-status")
    public String welcome() {
        return "Hi!";
    }
    @GetMapping("/microservice/notification/info")
    public String notificationStatic(){
        String ip = null;
        String hostname = null;
        try{

            ip = InetAddress.getLocalHost().getHostAddress();
            hostname = InetAddress.getLocalHost().getHostName();

        }catch(Exception e){
            e.printStackTrace();
        }
        String finalresult = "notification Service IP Address" + ip + "\n"+"Hostname"+hostname;
        return finalresult;
    }

    // @PostMapping(value = "/microservice/put")
    // public String putDevice(@RequestBody ) {
    //     // Define the URL of the external API you want to call
    //     String apiUrl = System.getenv("put_url");

    //     // Create a RestTemplate instance
    //     RestTemplate restTemplate = new RestTemplate();

    //     // Make an HTTP POST request to the external API with the request body
    //     String response = restTemplate.postForObject(apiUrl, requestBody, String.class);

    //     // Return the response from the external API
    //     return "Response from external API: " + response;
    // }

   @GetMapping(value = "/microservice/get")
    public String getDevice() {
        // Define the URL of the external API you want to call
        String apiUrl = System.getenv("get_url");;

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Make an HTTP GET request to the external API
        String response = restTemplate.getForObject(apiUrl, String.class);

        // Return the response from the external API
        return "Response from external API: " + response;
    }
}
