package controller;

import domain.PdfGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

public class GeneratePdf extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Content-Disposition", "attachment; filename=Students.pdf");
        response.setHeader("Content-Type", "application/pdf");

        try {
            PdfGenerator pdfGenerator = new PdfGenerator();
            ByteArrayOutputStream baos = pdfGenerator.buildPdfDocument(getService().getStudents());
            baos.writeTo(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "index.jsp";
    }
}
