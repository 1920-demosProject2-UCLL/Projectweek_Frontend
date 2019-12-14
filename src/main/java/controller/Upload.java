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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Upload extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();

        File uploads = new File("C:\\Users\\arneb\\Desktop\\projectweek\\" + fileName);

        FileOutputStream outputStream = new FileOutputStream(uploads);

        int read;
        byte[] bytes = new byte[1024];

        while ((read = fileContent.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }

        uploadToDatabase(uploads.getAbsolutePath());
        return "index.jsp";
    }

    private void uploadToDatabase(String absolutePath) {
        if (absolutePath == null || absolutePath.isEmpty()){
            throw new IllegalArgumentException("geef path");
        }
        String sql = "insert into projektweek.image(path, id) values (?,?)";
       try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
           System.out.println("Connected to the PostgreSQL server successfully.");
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println(statement);
            statement.setString(1, absolutePath);
            statement.setInt(2,5);
            statement.execute();
        } catch (Exception e) {
           System.out.println(e.getMessage());
            throw new IllegalArgumentException(e);
        }


    }
}
