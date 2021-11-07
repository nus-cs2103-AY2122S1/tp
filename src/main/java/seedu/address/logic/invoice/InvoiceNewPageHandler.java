package seedu.address.logic.invoice;

import java.io.IOException;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

public class InvoiceNewPageHandler implements IEventHandler {
    /**
     * Handles an event to add invoice header and page numbers
     * <p>
     * Solution adapted from itext7 Knowledge base tutorial
     * https://kb.itextpdf.com/home/it7kb/ebooks/itext-7-jump-start-tutorial-for-java/
     * chapter-3-using-renderers-and-event-handlers.
     *
     * @param event An event that the handler should perform an action.
     */
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        int pageNumber = pdfDoc.getPageNumber(page);

        PdfFont font = null;
        try {
            font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Rectangle pageSize = page.getPageSize();

        PdfCanvas pdfCanvas = new PdfCanvas(
                page.newContentStreamBefore(), page.getResources(), pdfDoc);

        // Add header and footer
        pdfCanvas.beginText()
                .setFontAndSize(font, 9)
                .moveText(pageSize.getWidth() / 2 - 20, pageSize.getTop() - 20)
                .showText("INVOICE")
                .moveText(20, -pageSize.getTop() + 30)
                .showText(String.valueOf(pageNumber))
                .endText();

        pdfCanvas.release();

    }
}
