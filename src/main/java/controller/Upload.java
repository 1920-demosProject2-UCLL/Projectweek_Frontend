package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Upload extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("test");
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();

        File uploads = new File("/webapp/img/" + fileName);

        FileOutputStream outputStream = new FileOutputStream(uploads);

        int read;
        byte[] bytes = new byte[1024];

        while ((read = fileContent.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }



        System.out.println(fileName);
        System.out.println("test");
        return "index.jsp";
    }
}
