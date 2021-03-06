package com.hortonworks.cbd;

import com.beust.jcommander.JCommander;
import com.hortonworks.cbd.model.Args;
import com.hortonworks.cbd.util.EncryptDecrypt;
import com.hortonworks.cbd.util.FileUtil;
import com.hortonworks.cbd.util.ScriptExecutor;
import com.microsoft.azure.keyvault.KeyVaultClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class HwxCbdKeyVaultApp {

    private static Logger logger = LoggerFactory.getLogger(HwxCbdKeyVaultApp.class);

    /**
     * App takes 2 params
     * -f Script file location
     * -sk SecretKey for Azure vault
     * @param argv
     */
    public static void main(String[] argv)  {


        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);
        /**
         * Read the property file
         */
        List<String> keysFromPropertyFile = new ArrayList<>();
        try {
            if (null != args.propertyfile) {
                keysFromPropertyFile = FileUtil.readPropertyFile(args.propertyfile);
            }
        }catch (Exception e) {
                e.printStackTrace();
                System.exit(-101);
        }

        Properties props = null;
        try {
            props = EncryptDecrypt.getProperties("/etc/hwxcbdkv/hwxcbdkv.properties");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-101);
        }
        final String clientId = props.getProperty("clientId");
        final String pfxPassword = props.getProperty("pfxPassword");
        final String path = props.getProperty("pathPfx");
        final String vaultUrl = props.getProperty("vaultBaseUrl");

        JavaKeyVaultAuthenticator authenticator = new JavaKeyVaultAuthenticator();
        Map<String, String> env = new HashMap<>();
        try {
            KeyVaultClient kvClient = authenticator.getAuthentication(path, pfxPassword, clientId);

            getEnvFromVault(keysFromPropertyFile,  vaultUrl, env, kvClient);
            getEnvFromVault(args.secretkey, vaultUrl, env, kvClient);

            ScriptExecutor.executeScript(args.file, env);
            logger.debug("DONE");
            System.exit(0);
        }catch(Exception e){
            e.printStackTrace();
            System.exit(-404);
        }
    }

    private static void getEnvFromVault(List<String> secrets, String vaultUrl, Map<String, String> env, KeyVaultClient kvClient) {
        for (String key : secrets) {
            if(null == key){
                continue;
            }
            key = key.trim();
            String secret = AzureVaultService.getSecretFromVault(kvClient, vaultUrl, key);
            env.put(key, secret);
        }
    }
}
