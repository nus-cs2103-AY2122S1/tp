package seedu.address.logic.invoice;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import seedu.address.model.chargeable.Chargeable;
import seedu.address.model.guest.Guest;

public class Invoice {
    public static final String BASE_PATH = "./Invoices/";

    /**
     * Generates an invoice for the guests stay.
     * Invoice includes a flat fee for the hotel stay and the sum of all chargeable services that they have consumed.
     *
     * @param g Guest to be charged
     * @throws IOException If invalid file path or fail to add rows to table.
     */
    public static void generateInvoicePdf(Guest g) throws IOException {
        // Invoice is only generated for guests who have charges
        assert !g.getChargeableUsed().isEmpty();

        // Create invoice folder
        File invoiceFolder = new File(BASE_PATH);
        invoiceFolder.mkdir();

        String dest = BASE_PATH + generatePdfFileName(g);

        // Initialize document
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);

        // Add headers and page numbers to every page
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new InvoiceNewPageHandler());

        addGuestBillingDetailsToPdf(document, g, boldFont);
        addInvoiceTableToPdf(document, g, font, boldFont);
        addThankYouParagraphToPdf(document, boldFont);

        // Close document
        document.close();
    }


    /**
     * Generates a file name for PDF based on guest's passport number and current datetime.
     *
     * The file name is in the format GuestPassportNumber yyyy-MM-dd HH-mm-ss.pdf
     * yyyy: current year
     * MM: current month in the range 1 - 12 (1 being January)
     * dd: current day of the month in the range 1 - 31
     * HH: current hour in 24-hour clock format
     * mm: current minute
     * ss: the current second
     *
     * Data time format is adapted from java tutorial point
     * https://www.javatpoint.com/java-get-current-date
     *
     * @param g Guest to generate invoice for.
     * @return A string representing the filename for the PDF invoice.
     */
    private static String generatePdfFileName(Guest g) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();

        return g.getPassportNumber().toString() + " " + now.format(dtf) + ".pdf";
    }

    /**
     * Add guest billing details to the PDF document.
     * Billing details include guest name and room number
     *
     * @param doc PDF document to add billing details to.
     * @param g Guest to bill invoice to.
     * @param boldFont Bold font used by text.
     */
    private static void addGuestBillingDetailsToPdf(Document doc, Guest g, PdfFont boldFont) {
        Paragraph guestDetails = new Paragraph("Bill to: " + g.getName() + "\nRoom Number: " + g.getRoomNumber());
        guestDetails.setTextAlignment(TextAlignment.CENTER).setFont(boldFont);
        doc.add(guestDetails);
    }

    /**
     * Add table containing charges to PDF.
     *
     * @param doc PDF document to add invoice table to.
     * @param g Guest to charge.
     * @param font Normal font used by text in table.
     * @param boldFont Bold font used by text in table.
     */
    private static void addInvoiceTableToPdf(Document doc, Guest g, PdfFont font, PdfFont boldFont) {
        Table invoiceTable = createInvoiceTableOfChargeables(g, font, boldFont);
        doc.add(invoiceTable);
    }

    /**
     * Creates a table containing all the Chargeables used by the guest.
     * The table contains 6 columns, as well as a box containg the total cost of all services at the end of the table
     *
     * Column 1: Item number, an index of the number of items in the invoice
     * Column 2: Vendor name, the name of the vendor used as well as their vendor ID
     * Column 3: Service, the type of service employed
     * Column 4: The cost for 1 quantity of the service
     * Column 5: Quantity, the total number of the service charged
     * Column 6: Line cost, the total cost for that service (quantity multiplied by cost for 1 quantity of service)
     *
     * Solution adapted from iText7 Core tutorial
     * https://kb.itextpdf.com/home/it7kb/ebooks/
     * itext-7-jump-start-tutorial-for-java/chapter-3-using-renderers-and-event-handlers
     *
     * @param g Guest to get charges from.
     * @param font Normal font used by text in table.
     * @param boldFont Bold font used by text in table.
     * @return Table containing charges.
     */
    private static Table createInvoiceTableOfChargeables(Guest g, PdfFont font, PdfFont boldFont) {
        Table table = new Table(new float[]{1, 4, 2, 1, 3, 3});
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);

        final String[] headerText = new String[]{"ITEM NUM", "VENDOR NAME",
            "SERVICE", "COST", "QUANTITY", "LINE COST"};

        // Ensures that header is not changed during development, as there is a dependency in the code that each
        // row is 6 cells long
        assert headerText.length == 6;

        // Add header rows
        for (String header : headerText) {
            table.addHeaderCell(
                    new Cell().add(
                            new Paragraph(header).setFont(boldFont)));
        }

        // Iterate through processed vendors and add to table
        int itemCount = 1;
        double totalCost = 0;
        for (Chargeable charge : g.getChargeableUsed()) {
            double lineCost = Double.parseDouble(charge.getCost().toString())
                    * Double.parseDouble(charge.getQuantity().toString());

            String[] details = new String[] {
                    String.valueOf(itemCount),
                    charge.getName().toString() + " [" + charge.getVendorId().toString() + "]",
                    charge.getServiceName().toString(),
                    charge.getCost().toString(),
                    charge.getQuantity().toString(),
                    String.valueOf(lineCost)
            };

            List<Cell> cells = createListOfCells(details, font);
            addCellsToTable(cells, table);

            totalCost += lineCost;
            itemCount++;
        }

        // Round off total cost to 2 d.p
        // Adapted from Bharat Sinha
        // https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java
        totalCost = Math.round(totalCost * 100.0) / 100.0;

        // Add total cost row
        addCostRowToTable(table, String.valueOf(totalCost), boldFont, headerText.length);

        return table;
    }

    private static List<Cell> createListOfCells(String[] texts, PdfFont font) {
        ArrayList<Cell> cells = new ArrayList<>();
        for (String text : texts) {
            Paragraph paragraph = new Paragraph(text).setFont(font);
            Cell cell = new Cell();
            cell.add(paragraph);
            cells.add(cell);
        }

        return cells;
    }

    private static void addCellsToTable(List<Cell> cells, Table table) {
        for (Cell cell : cells) {
            addCellToTable(cell, table);
        }
    }

    private static void addCellToTable(Cell cell, Table table) {
        table.addCell(cell);
    }

    /**
     * Adds total cost row at the bottom of the table.
     * For a table with n columns, the total cost will be put in the right most column. The n - 1 cells to its left
     * will be hidden.
     *
     * Solution adapted from Samuel Huylebroeck
     * https://stackoverflow.com/questions/41607891/itext-7-borderless-table-no-border.
     *
     * @param table           Table to add total cost row.
     * @param totalCost       Total cost.
     * @param boldFont        Bold font used by text in table.
     * @param numberOfColumns Number of columns in table.
     */
    private static void addCostRowToTable(Table table, String totalCost, PdfFont boldFont, int numberOfColumns) {
        for (int i = 0; i < numberOfColumns; i++) {
            Cell cell = new Cell();
            if (i != numberOfColumns - 1) {
                cell.add(new Paragraph(""));
                cell.setBorder(Border.NO_BORDER);
            } else {
                cell.add(new Paragraph(totalCost).setFont(boldFont));
            }
            table.addCell(cell);
        }
    }

    /**
     * Adds a text at the end of the invoice to say "Thank you"
     *
     * @param document PDF to add text to
     * @param boldFont Bold font for text
     */
    private static void addThankYouParagraphToPdf(Document document, PdfFont boldFont) {
        Paragraph thankYouParagraph = new Paragraph("THANK YOU FOR YOUR VISIT!");
        thankYouParagraph.setTextAlignment(TextAlignment.CENTER).setFont(boldFont);
        document.add(thankYouParagraph);
    }
}
