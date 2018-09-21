package com.hortonworks.cbd.util;

import org.apache.commons.configuration.ConfigurationException;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

import java.io.FileInputStream;
import java.util.Properties;

public class EncryptDecrypt {


    public static String decryptPropertyValue(String encryptedPropertyValue, String msec) throws ConfigurationException {

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(msec);
        String decryptedPropertyValue = encryptor.decrypt(encryptedPropertyValue);
        return decryptedPropertyValue;
    }

    public static String encryptPropertyValue(String propertyValue, String msec) throws ConfigurationException {

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(msec);
        String encryptPropertyValue = encryptor.encrypt(propertyValue);
        return encryptPropertyValue;
    }

    public static Properties getProperties() throws Exception {

        return getProperties("src/main/resources/azure.properties");

    }

    public static Properties getProperties(String propertyFileLoc) throws Exception {

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        Properties props = new Properties();
        props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("msec.properties"));


        encryptor.setPassword(props.getProperty("msec")); // could be got from web, env variable...

        Properties eprops = new EncryptableProperties(encryptor);
        eprops.load(new FileInputStream(propertyFileLoc));


        return eprops;

    }
}