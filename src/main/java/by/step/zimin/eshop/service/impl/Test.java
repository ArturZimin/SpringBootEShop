package by.step.zimin.eshop.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class Test {

    public String test() throws MalformedURLException {
        URL url= new URL("https://www.google.com/maps/place/%D1%83%D0%BB%D0%B8%D1%86%D0%B0+%D0%9A%D0%B0%D0%BB%D0%B8%D0%BD%D0%BE%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE+111,+%D0%9C%D0%B8%D0%BD%D1%81%D0%BA/@53.9456529,27.6466378,17z/data=!4m5!3m4!1s0x46dbced6368b33b9:0x264439617d5e8da!8m2!3d53.9456056!4d27.6488694?hl=ru");
        RestTemplate restTemplate = new RestTemplate();


    }

}
