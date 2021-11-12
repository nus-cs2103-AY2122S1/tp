package seedu.teachbook.commons.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Utility methods related to Excel.
 */
public class ExcelUtil {

    private static Font setHeaderFont(Font headerFont) {
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.BLACK.index);
        return headerFont;
    }

    /**
     * Converts 2d list into an excelSheet
     *
     * @param listOfColumns list of Columns to be printed
     * @return filepath of Excelsheet printec
     * @throws RuntimeException when excel fails to generate
     */
    public static String toExcel(List<List<String>> listOfColumns) throws IOException, SecurityException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sh = workbook.createSheet("Student List");
        String[] columnHeadings = new String[listOfColumns.size() + 1];

        for (int i = 0; i < listOfColumns.size(); i++) {
            columnHeadings[i] = listOfColumns.get(i).get(0);
        }

        Font headerFont = setHeaderFont(workbook.createFont());
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);

        Row headerRow = sh.createRow(0);
        for (int i = 0; i < columnHeadings.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnHeadings[i]);
            cell.setCellStyle(headerStyle);
        }

        for (int i = 1; i < listOfColumns.get(0).size(); i++) {
            Row row = sh.createRow(i);
            for (int j = 0; j < listOfColumns.size(); j++) {
                row.createCell(j).setCellValue(listOfColumns.get(j).get(i));
            }
        }

        for (int i = 0; i < columnHeadings.length; i++) {
            if (!Objects.equals(columnHeadings[i], "")) {
                sh.autoSizeColumn(i);
            }
        }

        String home = System.getProperty("user.home");
        String filePath = Paths.get(home, "Downloads").toString();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH.mm.ss.SS", Locale.ENGLISH);
        FileOutputStream fileOut = new FileOutputStream(String.format("%s/Downloads/%s %s.xls",
                home, sh.getSheetName(), LocalDateTime.now().format(format)));

        workbook.write(fileOut);
        fileOut.close();

        return filePath;
    }
}
