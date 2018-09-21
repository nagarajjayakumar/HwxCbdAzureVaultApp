package com.hortonworks.cbd.model;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class Args {

    @Parameter(names = { "--secretkey", "-sk" }, description = "Script File Location")
    public List<String> secretkey = new ArrayList<>();

    @Parameter(names = { "--file", "-f" }, description = "Script File Location")
    public String file;

}