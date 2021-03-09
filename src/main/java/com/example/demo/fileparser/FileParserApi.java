package com.example.demo.fileparser;

import com.example.demo.fileparser.filetype.FileType;
import com.example.demo.parsers.impl.ParserResult;

import java.util.List;

public interface FileParserApi<T extends FileType> {

    List<ParserResult> parse(final String file);

    FileType getType();

    default boolean isApplicable(final String name) {
        final String type = name.split("\\.")[1];
        return getType().isApplicable(type);
    }
}
