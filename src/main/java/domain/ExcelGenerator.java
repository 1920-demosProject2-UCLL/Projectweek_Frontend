package domain;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ExcelGenerator {

    public ExcelGenerator() {
    }

    public ByteArrayOutputStream buildExcelDocument(List<Student> students) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("Students");
        Map<Integer, Student> newData = new HashMap<Integer, Student>();
        int counter = 1;
    for(Student student: students){
        newData.put(counter, student);
        counter++;
    }
        Set<Integer> newRows = newData.keySet();
        int rownum = sheet.getLastRowNum();
        for (Integer key : newRows) {
            Row row = sheet.createRow(rownum++);
            Student objArr = newData.get(key);
            int cellnum = 0;

            Cell cell = row.createCell(cellnum++);
            cell.setCellValue(objArr.getFirstName());
            Cell cell2 = row.createCell(cellnum++);
            cell2.setCellValue(objArr.getName());
            Cell cell3 = row.createCell(cellnum++);
            cell3.setCellValue(objArr.getrNumber());
            Cell cell4 = row.createCell(cellnum++);
            cell4.setCellValue(objArr.getGithubAccount());

        }
        book.write(baos);

        return baos;

        // open an OutputStream to save written data into Excel file
    }

}
