package com.example.demo.parsers.impl;

import com.example.demo.fileparser.FileParserApi;
import com.example.demo.fileparser.filetype.FileType;
import com.example.demo.parsers.ParserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Parser implements ParserApi {

    @Autowired
    private List<FileParserApi<? extends FileType>> parsers;

    public List<ParserResult> parse(final String... files) {
        List<ParserResult> parserResults = new ArrayList<>();
        for (final String file : files) {
            final Optional<FileParserApi<? extends FileType>> first = parsers.stream()
                    .filter(fileParserApi -> fileParserApi.isApplicable(file)).findFirst();
            parserResults.addAll(first.orElseThrow(() -> new IllegalStateException("There is no applicable parser for such type"))
                    .parse(file));
        }
        return parserResults;
    }
}
