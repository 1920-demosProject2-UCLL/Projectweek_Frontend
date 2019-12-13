package controller;

import domain.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Controller")
@MultipartConfig
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StudentService model = new StudentService();
    private ControllerFactory controllerFactory = new ControllerFactory();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String destination = "index.jsp";
        RequestHandler handler;
        if (action != null) {
            handler = controllerFactory.getController(action, model);
            try {
                destination = handler.handleRequest(request, response);
            } catch (Exception e) {
                RequestDispatcher view = request.getRequestDispatcher(destination);
                view.forward(request, response);
            }
            if (destination != null) {
                RequestDispatcher view = request.getRequestDispatcher(destination);
                view.forward(request, response);
            }
        }
    }

}
