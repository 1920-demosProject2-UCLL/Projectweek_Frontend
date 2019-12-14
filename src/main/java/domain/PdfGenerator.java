package domain;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.tagging.StandardRoles;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


public class PdfGenerator {

    public PdfGenerator() {
    }

    public ByteArrayOutputStream buildPdfDocument(List<Student> students) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(baos));
        Document doc = new Document(pdfDoc);

        Paragraph title = new Paragraph("Demo Projectweek")
                .setFontColor(new DeviceRgb(8, 73, 117))
                .setFontSize(20f);
        title.getAccessibilityProperties().setRole(StandardRoles.H1);
        doc.add(title);

        Table table = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();

        table.addHeaderCell("Name");
        table.addHeaderCell("First name");
        table.addHeaderCell("GitHub account");
        table.addHeaderCell("r-number");

        for (Student student : students) {
            table.addCell(student.getName());
            table.addCell(student.getFirstName());
            table.addCell(student.getGithubAccount());
            table.addCell(student.getrNumber());
        }
        doc.add(table);

        doc.close();
        return baos;
    }

}
