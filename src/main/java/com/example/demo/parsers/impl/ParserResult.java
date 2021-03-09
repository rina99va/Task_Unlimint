package com.example.demo.parsers.impl;

import com.example.demo.SumSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

@Getter
public class ParserResult {

    private final int id;

    @JsonSerialize(using = SumSerializer.class)
    private final double amount;

    private final String currency;

    private final String comment;

    @JsonProperty("filename")
    private final String fileName;

    private final int line;

    private final String result;

    public ParserResult(final String id, final String sum, final String currency, final String comment,
                        final String fileName, final int line) {
        int parsedId;
        double parsedSum;
        String parsingResult;
        try {
            parsedId = Integer.parseInt(id);
            parsedSum = Double.parseDouble(sum);
            parsingResult = "OK";
        } catch (final NumberFormatException e) {
            parsingResult = "Wrong number format, displayed minValue instead";
            parsedId = Integer.MIN_VALUE;
            parsedSum = Double.MIN_VALUE;
        }
        this.id = parsedId;
        this.amount = parsedSum;
        this.currency = currency;
        this.comment = comment;
        this.fileName = fileName;
        this.line = line;
        this.result = parsingResult;
    }
}
