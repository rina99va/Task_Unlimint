package com.example.demo.fileparser.filetype.impl;

import com.example.demo.fileparser.filetype.FileType;

public class JSON implements FileType {

    private static final String TYPE = "JSON";

    @Override
    public boolean isApplicable(String type) {
        return TYPE.equalsIgnoreCase(type);
    }
}
