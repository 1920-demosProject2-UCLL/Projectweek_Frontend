package controller;

import domain.StudentService;

public class ControllerFactory {

    public RequestHandler getController(String key, StudentService model) {
        return createHandler(key, model);
    }

    private RequestHandler createHandler(String handlerName, StudentService model) {
        RequestHandler handler;
        try {
            Class<?> handlerClass = Class.forName("controller." + handlerName);
            Object handlerObject = handlerClass.newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setModel(model);
        } catch (Exception e) {
            throw new RuntimeException("Deze pagina bestaat niet!");
        }
        return handler;
    }

}
