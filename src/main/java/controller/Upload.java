package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Upload extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();

//        File uploads = new File("C:\\Users\\arneb\\Desktop\\projectweek\\" + fileName);
//        FileOutputStream outputStream = new FileOutputStream(uploads);
//        int read;
//        byte[] bytes = new byte[1024];
//        while ((read = fileContent.read(bytes)) != -1) {
//            outputStream.write(bytes, 0, read);
//        }

        uploadToDatabase(fileName, fileContent);
        return "index.jsp";
    }

    private void uploadToDatabase(String fileName, InputStream fileContent) {
        String sql = "insert into projektweek.image(filename, img) values (?,?)";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
            System.out.println("Connected to the PostgreSQL server successfully.");
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, fileName);
            statement.setBinaryStream(2, fileContent);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }
}
