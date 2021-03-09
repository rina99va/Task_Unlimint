package com.example.demo.parsers;

import com.example.demo.parsers.impl.ParserResult;

import java.util.List;

public interface ParserApi {

    List<ParserResult> parse(final String... file);
}
