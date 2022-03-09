package com.rbnico.spring;

import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class MovieAdvisorHelp {
    private String help;

    @PostConstruct
    public void init() {
        try {
            help = Files
                    .lines(Paths.get(ResourceUtils.getFile("classpath:help.txt").toURI()))
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public String getHelp() {
        return help;
    }
}
