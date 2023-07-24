package servlets;

import entities.User;
import org.example.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogIn extends HttpServlet {
    private final UserService userService = new UserService();
    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("WEB-INF/Login.jsp");
        dispatcher.forward(httpServletRequest,httpServletResponse);
    }

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ServletContext servlet = getServletContext();
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");

        User logInUser = userService.getUserEntity(username,password);
        if(logInUser != null){
            HttpSession httpSession = httpServletRequest.getSession();
            httpSession.setAttribute("currentUser",username);
            httpSession.setAttribute("currentUserId",logInUser.getId());
            RequestDispatcher homepageDispatcher = httpServletRequest.getRequestDispatcher(
                    "WEB-INF/Homepage.jsp");
            homepageDispatcher.forward(httpServletRequest,httpServletResponse);
        }else{
            RequestDispatcher tryAgainLoginDispatcher = httpServletRequest.getRequestDispatcher(
                    "WEB-INF/TryLoginAgain.jsp");
            tryAgainLoginDispatcher.forward(httpServletRequest,httpServletResponse);
        }
    }
}