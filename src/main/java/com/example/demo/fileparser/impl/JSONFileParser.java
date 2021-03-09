package com.example.demo.fileparser.impl;

import com.example.demo.fileparser.FileParserApi;
import com.example.demo.fileparser.filetype.FileType;
import com.example.demo.fileparser.filetype.impl.JSON;
import com.example.demo.parsers.impl.ParserResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class JSONFileParser implements FileParserApi {

    private final static String ID_FIELD_NAME = "orderId";
    private final static String SUM_FIELD_NAME = "amount";
    private final static String CURRENCY_FIELD_NAME = "currency";
    private final static String COMMENT_FIELD_NAME = "comment";

    private static final FileType TYPE = new JSON();

    @Override
    public List<ParserResult> parse(final String file) {
        final List<ParserResult> result = new ArrayList<ParserResult>();
        try (final Reader reader = Files.newBufferedReader(Paths.get(file))) {
            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode jsonNode = mapper.readTree(reader);
            int lineNumber = 1;
            for (final JsonNode node : jsonNode) {
                final String id = node.get(ID_FIELD_NAME).asText();
                final String sum = node.get(SUM_FIELD_NAME).asText();
                final String currency = node.get(CURRENCY_FIELD_NAME).asText();
                final String comment = node.get(COMMENT_FIELD_NAME).asText();

                result.add(new ParserResult(id, sum, currency, comment, file, lineNumber));
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public FileType getType() {
        return TYPE;
    }
}
