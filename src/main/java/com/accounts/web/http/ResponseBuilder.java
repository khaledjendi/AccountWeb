package com.accounts.web.http;

import com.accounts.web.view.AccountsBean;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ResponseBuilder {
    private static final Logger LOGGER = Logger.getLogger(ResponseBuilder.class.getName());

    public static Reader buildReponse(String method, String contentType, String methodURL) {
        LOGGER.info("checkTransactionService is triggered");

        try {
            URL url = new URL(AccountsBean.getBaseURL() + methodURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);
            con.setRequestProperty("Content-Type", contentType);

            int status = con.getResponseCode();

            Reader streamReader = null;

            if (status > 299) {
                return null;
            } else {
                return new InputStreamReader(con.getInputStream());
            }
        } catch (MalformedURLException e) {
            LOGGER.error("checkTransactionService - MalformedURLException: " + e);
        } catch (ProtocolException e) {
            LOGGER.error("checkTransactionService - ProtocolException: " + e);
        } catch (IOException e) {
            LOGGER.error("checkTransactionService - IOException: " + e);
        }

        return null;
    }
}
