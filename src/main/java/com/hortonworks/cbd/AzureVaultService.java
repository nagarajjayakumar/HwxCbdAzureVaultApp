package com.hortonworks.cbd;

import com.microsoft.azure.keyvault.KeyVaultClient;
import com.microsoft.azure.keyvault.models.SecretBundle;

public class AzureVaultService {

   
    public static String getSecretFromVault(KeyVaultClient kvClient,
                                            String vaultBaseUrl,
                                            String secretKey) {
        SecretBundle secretBundle = kvClient.getSecret(vaultBaseUrl, secretKey);

        if (null == secretBundle) {
            throw new RuntimeException("[FATAL] :: UNABLE TO GET THE SECRET FROM VAULT ");
        }

        return secretBundle.value();

    }


}
