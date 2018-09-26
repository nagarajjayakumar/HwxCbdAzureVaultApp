package com.hortonworks.cbd.model;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class Args {

    @Parameter(names = { "--secretkeys", "-sk" }, description = "Vault Secret key ")
    public List<String> secretkey = new ArrayList<>();

    @Parameter(names = { "--file", "-f" }, description = "Script File Location", required=true)
    public String file;

    @Parameter(names = { "--propertyfile", "-pf" }, description = "propertyfile for the Vault Secret Key")
    public String propertyfile;


}