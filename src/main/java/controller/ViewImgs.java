package controller;


import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class ViewImgs extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map<String, InputStream> imgs = getImgs();
        List<String> imgBase64 = new ArrayList<>();

        try {
            for (Map.Entry<String, InputStream> entry : imgs.entrySet()) {
                String extension = FilenameUtils.getExtension(entry.getKey());
                byte[] encoded = Base64.getEncoder().encode(IOUtils.toByteArray(entry.getValue()));
                imgBase64.add("data:image/" + extension + ";base64, " + new String(encoded));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        request.setAttribute("imageUrlList", imgBase64);
        return "viewImgs.jsp";
    }

    private Map<String, InputStream> getImgs() {
        Map<String, InputStream> imgs = new HashMap<>();

        String sql = "select filename, img from projektweek.image";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
            System.out.println("Connected to the PostgreSQL server successfully.");
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String filename = resultSet.getString("filename");
                InputStream fileContent = resultSet.getBinaryStream("img");
                imgs.put(filename, fileContent);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException(e);
        }
        return imgs;
    }
}
