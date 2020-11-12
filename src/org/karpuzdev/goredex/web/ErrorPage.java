package org.karpuzdev.goredex.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Controller
public class ErrorPage implements ErrorController {

    public static String errorResource;

    static {
        InputStream is = ErrorPage.class.getClassLoader().getResourceAsStream("static/error.html");
        if (is == null) {
            errorResource = "<html><body><h2>Error Page</h2><div>Error Details: <b>{{asd}}</b></div>"
                    +    "<body></html>";
        } else {
            try (InputStreamReader streamReader =
                         new InputStreamReader(is, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(streamReader)) {

                StringBuilder sb = new StringBuilder();
                String sd;
                while ((sd = reader.readLine()) != null) {
                    sb.append(sd).append("\n");
                }

                errorResource = sb.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        String sd = statusCode + " - ";
        sd += (exception==null? "N/A": exception.getMessage());
        return errorResource.replace("{{asd}}", sd);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}