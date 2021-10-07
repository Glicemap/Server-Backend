package com.glicemap.service;

import com.glicemap.dto.DailyMeasuresDTO;
import com.glicemap.dto.MeasureDTO;
import com.glicemap.enumerates.MeasureSituations;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    public ReportService() {
    }

    private void writeTableHeader(PdfPTable table){
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
        for (int i = 0; i < 5; i++){
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

        for (DailyMeasuresDTO dailyMeasures : measures){
            cell.setColspan(1);
            cell.setPhrase(new Phrase(dailyMeasures.getDate().split("-")[2]+"/"+dailyMeasures.getDate().split("-")[1]+"/"+dailyMeasures.getDate().split("-")[0], font));
            table.addCell(cell);

            boolean found = false;
            for (MeasureDTO measure : dailyMeasures.getMeasures()){
                if (MeasureSituations.ANTES_CAFE.equals(MeasureSituations.getEnum(measure.getSituation()))){
                    cell.setPhrase(new Phrase(measure.getSugarLevel(), font));
                    table.addCell(cell);
                    found = true;
                    break;
                }
            }
            if (!found){
                cell.setPhrase(new Phrase("-", font));
                table.addCell(cell);
            }

            found = false;
            for (MeasureDTO measure : dailyMeasures.getMeasures()){
                if (MeasureSituations.DEPOIS_CAFE.equals(MeasureSituations.getEnum(measure.getSituation()))){
                    cell.setPhrase(new Phrase(measure.getSugarLevel(), font));
                    table.addCell(cell);
                    found = true;
                    break;
                }
            }
            if (!found){
                cell.setPhrase(new Phrase("-", font));
                table.addCell(cell);
            }

            found = false;
            for (MeasureDTO measure : dailyMeasures.getMeasures()){
                if (MeasureSituations.ANTES_LANCHE_MANHA.equals(MeasureSituations.getEnum(measure.getSituation()))){
                    cell.setPhrase(new Phrase(measure.getSugarLevel(), font));
                    table.addCell(cell);
                    found = true;
                    break;
                }
            }
            if (!found){
                cell.setPhrase(new Phrase("-", font));
                table.addCell(cell);
            }

            found = false;
            for (MeasureDTO measure : dailyMeasures.getMeasures()){
                if (MeasureSituations.DEPOIS_LANCHE_MANHA.equals(MeasureSituations.getEnum(measure.getSituation()))){
                    cell.setPhrase(new Phrase(measure.getSugarLevel(), font));
                    table.addCell(cell);
                    found = true;
                    break;
                }
            }
            if (!found){
                cell.setPhrase(new Phrase("-", font));
                table.addCell(cell);
            }

            found = false;
            for (MeasureDTO measure : dailyMeasures.getMeasures()){
                if (MeasureSituations.ANTES_ALMOCO.equals(MeasureSituations.getEnum(measure.getSituation()))){
                    cell.setPhrase(new Phrase(measure.getSugarLevel(), font));
                    table.addCell(cell);
                    found = true;
                    break;
                }
            }
            if (!found){
                cell.setPhrase(new Phrase("-", font));
                table.addCell(cell);
            }

            found = false;
            for (MeasureDTO measure : dailyMeasures.getMeasures()){
                if (MeasureSituations.DEPOIS_ALMOCO.equals(MeasureSituations.getEnum(measure.getSituation()))){
                    cell.setPhrase(new Phrase(measure.getSugarLevel(), font));
                    table.addCell(cell);
                    found = true;
                    break;
                }
            }
            if (!found){
                cell.setPhrase(new Phrase("-", font));
                table.addCell(cell);
            }

            found = false;
            for (MeasureDTO measure : dailyMeasures.getMeasures()){
                if (MeasureSituations.ANTES_LANCHE_TARDE.equals(MeasureSituations.getEnum(measure.getSituation()))){
                    cell.setPhrase(new Phrase(measure.getSugarLevel(), font));
                    table.addCell(cell);
                    found = true;
                    break;
                }
            }
            if (!found){
                cell.setPhrase(new Phrase("-", font));
                table.addCell(cell);
            }

            found = false;
            for (MeasureDTO measure : dailyMeasures.getMeasures()){
                if (MeasureSituations.DEPOIS_LANCHE_TARDE.equals(MeasureSituations.getEnum(measure.getSituation()))){
                    cell.setPhrase(new Phrase(measure.getSugarLevel(), font));
                    table.addCell(cell);
                    found = true;
                    break;
                }
            }
            if (!found){
                cell.setPhrase(new Phrase("-", font));
                table.addCell(cell);
            }

            found = false;
            for (MeasureDTO measure : dailyMeasures.getMeasures()){
                if (MeasureSituations.ANTES_JANTAR.equals(MeasureSituations.getEnum(measure.getSituation()))){
                    cell.setPhrase(new Phrase(measure.getSugarLevel(), font));
                    table.addCell(cell);
                    found = true;
                    break;
                }
            }
            if (!found){
                cell.setPhrase(new Phrase("-", font));
                table.addCell(cell);
            }

            found = false;
            for (MeasureDTO measure : dailyMeasures.getMeasures()){
                if (MeasureSituations.DEPOIS_JANTAR.equals(MeasureSituations.getEnum(measure.getSituation()))){
                    cell.setPhrase(new Phrase(measure.getSugarLevel(), font));
                    table.addCell(cell);
                    found = true;
                    break;
                }
            }
            if (!found){
                cell.setPhrase(new Phrase("-", font));
                table.addCell(cell);
            }

            cell.setColspan(2);
            found = false;
            for (MeasureDTO measure : dailyMeasures.getMeasures()){
                if (MeasureSituations.ANTES_DORMIR.equals(MeasureSituations.getEnum(measure.getSituation()))){
                    cell.setPhrase(new Phrase(measure.getSugarLevel(), font));
                    table.addCell(cell);
                    found = true;
                    break;
                }
            }
            if (!found){
                cell.setPhrase(new Phrase("-", font));
                table.addCell(cell);
            }
        }
    }

    public void export(HttpServletResponse response, List<DailyMeasuresDTO> measures) throws DocumentException, IOException {
        Document document = null;
        try {
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
            table.setWidths(new float[] { 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 });
            writeTableHeader(table);
            writeTableData(table, measures);

            document.add(table);

        } catch (IOException errorIO){
            //TODO - logar erro e gerar erro próprio
            throw errorIO;
        } catch (DocumentException errorDoc){
            //TODO -logar erro e gerar erro próprio
            throw errorDoc;
        } finally {
            if (document != null) document.close();
        }
    }
}
