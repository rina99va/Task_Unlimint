package com.example.demo;

import com.example.demo.parsers.ParserApi;
import com.example.demo.parsers.impl.ParserResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ParserApplication implements CommandLineRunner {

    @Autowired
    private ParserApi parser;

    public static void main(String[] args) {
        SpringApplication.run(ParserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final List<ParserResult> parse = parser.parse(args);
        final ObjectMapper mapper = new ObjectMapper();
        parse.stream().map(result -> {
            try {
                return mapper.writeValueAsString(result);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return "";
            }
        }).forEach(System.out::println);
    }
}
