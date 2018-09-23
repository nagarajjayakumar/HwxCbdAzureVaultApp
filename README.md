# KeyVault App - Cloudbreak 

## Azure KeyVault integration with Cloudbreak receipe

Azure Vault integration using PFX authentication. 
Gets the Value from Vault and pass to the Shell script dynamically and execute the script.

Place your property file in the location "/etc/hwxcbdkv/hwxcbdkv.properties". 
Property must have following properties.

```
clientId=ENC(encryptedClientID)
pfxPassword=your_pfx_password
vaultBaseUrl=https://yourvaultlocation/
pathPfx=your_app_pfx_location

```

App usage:

```
/usr/java/default/bin/java -jar HwxCbdAzureVaultApp.jar  -f <ScriptFileName> -sk <VaultKeyCSV>

Options ::

-f or --file  # Script file location, Complete path required [ex: /a/b/c.sh]

-sk or --secretkeys # List of secret key to be get from Azure vault. [ex: key1,key2...]

```

SIGN: NAGA JAY