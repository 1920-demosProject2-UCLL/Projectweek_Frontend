package controller;

import domain.ExcelGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;


public class GenerateExcel extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Content-Disposition", "attachment; filename=Students.xlsx");
        response.setHeader("Content-Type", "application/xlsx");

        try {
            ExcelGenerator excelGenerator = new ExcelGenerator();
            ByteArrayOutputStream baos = excelGenerator.buildExcelDocument(getService().getStudents());
            baos.writeTo(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "index.jsp";
    }
}
