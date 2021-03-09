package com.example.demo.fileparser.filetype.impl;

import com.example.demo.fileparser.filetype.FileType;

public class CSV implements FileType {

    private static final String TYPE = "CSV";

    @Override
    public boolean isApplicable(String type) {
        return TYPE.equalsIgnoreCase(type);
    }
}
