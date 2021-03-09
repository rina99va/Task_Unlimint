package com.example.demo.fileparser.impl;

import com.example.demo.fileparser.FileParserApi;
import com.example.demo.fileparser.filetype.FileType;
import com.example.demo.fileparser.filetype.impl.CSV;
import com.example.demo.parsers.impl.ParserResult;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVFileParser implements FileParserApi {

    private static final int ID_POSITION = 0;
    private static final int SUM_POSITION = 1;
    private static final int CURRENCY_POSITION = 2;
    private static final int COMMENT_POSITION = 3;

    private static final FileType TYPE = new CSV();

    @Override
    public List<ParserResult> parse(final String file) {
        final List<ParserResult> result = new ArrayList<ParserResult>();
        try (final Reader reader = Files.newBufferedReader(Paths.get(file));
             final CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            int lineNumber = 1;
            for (final CSVRecord csvRecord : csvParser) {
                final String id = csvRecord.get(ID_POSITION);
                final String sum = csvRecord.get(SUM_POSITION);
                final String currency = csvRecord.get(CURRENCY_POSITION);
                final String comment = csvRecord.get(COMMENT_POSITION);

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
