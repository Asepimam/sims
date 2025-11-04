package com.sims.sims.shared.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

@Configuration
public class JwtKeyConfig {

    @Value("classpath:keys/private-pkcs8.pem")
    private Resource privateKeyResource;

    @Value("classpath:keys/public.pem")
    private Resource publicKeyResource;

    @Bean
    public PrivateKey privateKey() throws Exception {
        try (InputStream in = privateKeyResource.getInputStream()) {
            return RsaKeyUtils.readPrivateKey(in);
        }
    }

    @Bean
    public PublicKey publicKey() throws Exception {
        try (InputStream in = publicKeyResource.getInputStream()) {
            return RsaKeyUtils.readPublicKey(in);
        }
    }
}
