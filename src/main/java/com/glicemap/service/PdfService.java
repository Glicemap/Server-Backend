package com.glicemap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PdfService {

//    private static final String PDF_RESOURCES = "/pdf-resources/";
//
//    public File generatePdf() throws IOException, DocumentException {
//        Context context = getContext();
//        String html = loadAndFillTemplate(context);
//        return renderPdf(html);
//    }
//
//    private File renderPdf(String html) throws IOException, DocumentException {
//        File file = File.createTempFile("students", ".pdf");
//        OutputStream outputStream = new FileOutputStream(file);
//        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
//        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
//        renderer.layout();
//        renderer.createPDF(outputStream);
//        outputStream.close();
//        file.deleteOnExit();
//        return file;
//    }
//
//    private Context getContext() {
//        Context context = new Context();
//        context.setVariable("students", studentService.getStudents());
//        return context;
//    }
//
//    private String loadAndFillTemplate(Context context) {
//        return templateEngine.process("pdf_students", context);
//    }


}
