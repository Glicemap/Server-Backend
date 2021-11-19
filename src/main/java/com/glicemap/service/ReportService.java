package com.glicemap.service;

import com.glicemap.dto.DailyMeasuresDTO;
import com.glicemap.dto.MeasureDTO;
import com.glicemap.exception.BaseBusinessException;
import com.glicemap.indicator.SituationsIndicator;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    Logger logger = LoggerFactory.getLogger(ReportService.class);

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(10);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(13);

        cell.setColspan(1);
        cell.setPhrase(new Phrase("Data", font));
        table.addCell(cell);

        cell.setColspan(2);
        cell.setPhrase(new Phrase("Café da Manhã", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Lanche da Manhã", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Almoço", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Lanche da Tarde", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Jantar", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Antes de Dormir", font));
        table.addCell(cell);

        cell.setPadding(3);
        font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(10);

        cell.setColspan(1);
        cell.setPhrase(new Phrase("", font));
        table.addCell(cell);
        for (int i = 0; i < 5; i++) {
            cell.setPhrase(new Phrase("Antes", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("2 horas após", font));
            table.addCell(cell);
        }
        cell.setColspan(2);
        cell.setPhrase(new Phrase("Hora de dormir", font));
        table.addCell(cell);
    }


    private void writeTableData(PdfPTable table, List<DailyMeasuresDTO> measures) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(7);

        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(11);

        for (DailyMeasuresDTO dailyMeasures : measures) {
            cell.setColspan(1);
            String[] dateSplited = dailyMeasures.getDate().split("-");
            cell.setPhrase(new Phrase(String.format("%s/%s/%s", dateSplited[0], dateSplited[1], dateSplited[2]), font));
            table.addCell(cell);

            for (SituationsIndicator situation : SituationsIndicator.values()) {
                if (SituationsIndicator.ANTES_DORMIR.equals(situation)) {
                    cell.setColspan(2);
                }
                boolean found = false;
                for (MeasureDTO measure : dailyMeasures.getMeasures()) {
                    if (situation.equals(SituationsIndicator.getEnum(measure.getSituation()))) {
                        cell.setPhrase(new Phrase(measure.getSugarLevel(), font));
                        table.addCell(cell);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    cell.setPhrase(new Phrase("-", font));
                    table.addCell(cell);
                }
            }
        }
    }


    public void export(HttpServletResponse response, List<DailyMeasuresDTO> measures) throws BaseBusinessException {
        Document document = null;
        try {
            logger.info("ReportService- Building document! measures = [{}]", measures);
            document = new Document(PageSize.A4.rotate());

            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            font.setSize(18);
            Paragraph title = new Paragraph("CONTROLE DE GLICEMIA CAPILAR", font);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            PdfPTable table = new PdfPTable(13);
            table.setWidthPercentage(100);
            table.setSpacingBefore(18);
            table.setWidths(new float[]{3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2});
            writeTableHeader(table);
            writeTableData(table, measures);
            document.add(table);

        } catch (IOException errorIO) {
            logger.error("ReportService- IOException error building document! e = [{}]", errorIO.getMessage());
            throw new BaseBusinessException("PDF_EXPORT_ERROR_0001");
        } catch (DocumentException errorDoc) {
            logger.error("ReportService- DocumentException error building document! e = [{}]", errorDoc.getMessage());
            throw new BaseBusinessException("PDF_EXPORT_ERROR_0002");
        } finally {
            if (document != null) document.close();
        }
    }
}
