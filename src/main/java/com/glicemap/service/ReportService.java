package com.glicemap.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ReportService {
// https://www.codejava.net/frameworks/spring-boot/pdf-export-example
    public File exportPDF(String documentNumber, String dateBegin, String dateEnd) {

        return new File("a");
    }
}
