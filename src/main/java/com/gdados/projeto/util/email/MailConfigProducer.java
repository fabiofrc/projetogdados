/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdados.projeto.util.email;


import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.SimpleMailConfig;
import java.io.IOException;
import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 *
 * @author frc
 */
public class MailConfigProducer {

    @Produces
    @ApplicationScoped
    public SessionConfig getMailConfig() throws IOException {
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream("/email.properties"));

        SimpleMailConfig config = new SimpleMailConfig();
        config.setServerHost(props.getProperty("mail.server.host"));
        config.setServerPort(Integer.parseInt(props.getProperty("mail.server.port")));
        config.setEnableSsl(Boolean.parseBoolean(props.getProperty("mail.enable.ssl")));
        config.setAuth(Boolean.parseBoolean(props.getProperty("mail.auth")));
        config.setUsername(props.getProperty("mail.username"));
        config.setPassword(props.getProperty("mail.password"));

        return config;
    }
}
