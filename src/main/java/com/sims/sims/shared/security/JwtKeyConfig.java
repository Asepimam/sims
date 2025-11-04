package com.sims.sims.shared.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.security.PrivateKey;
import java.security.PublicKey;

@Configuration
public class JwtKeyConfig {
    //path to keys
    @Value("classpath:keys/private-pkcs8.pem") 
    private Resource privateKeyResource;

    @Value("classpath:keys/public.pem")
    private Resource publicKeyResource;

    @Bean
    public PrivateKey privateKey() throws Exception {
        return RsaKeyUtils.readPrivateKey(privateKeyResource.getFile().getAbsolutePath());
    }

    @Bean
    public PublicKey publicKey() throws Exception {
        return RsaKeyUtils.readPublicKey(publicKeyResource.getFile().getAbsolutePath());
    }
}