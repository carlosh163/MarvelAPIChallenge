package com.test.api.marvelchallenge.persistence.integration.marvel;

import com.test.api.marvelchallenge.util.MD5Encoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MarvelAPIConfig {

  private final MD5Encoder md5Encoder;
  private long timestamp = new Date(System.currentTimeMillis()).getTime();
  @Value("${integration.marvel.public-key}")
  private String publicKey;

  @Value("${integration.marvel.private-key}")
  private String privateKey;

  private String getHash() {
    String hashDecoded = Long.toString(timestamp).concat(privateKey).concat(publicKey);
    return md5Encoder.encode(hashDecoded);
  }

  public Map<String, String> getAuthenticationQueryParams(){
    Map<String, String> securityQueryParams = new HashMap<>();

    securityQueryParams.put("ts", Long.toString(timestamp));
    securityQueryParams.put("apikey", publicKey);
    securityQueryParams.put("hash", this.getHash());

    return securityQueryParams;
  }
}
