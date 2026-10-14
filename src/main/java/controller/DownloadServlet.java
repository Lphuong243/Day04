package controller;

import java.io.IOException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import murach.business.User;
import murach.data.UserIO;
import murach.util.CookieUtil;

public class DownloadServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "viewAlbums"; // default action
        }

        // perform action and set URL to appropriate page
        String url = "/index.jsp";
        if (action.equals("viewAlbums")) {
            url = "/index.jsp";
        } else if (action.equals("checkUser")) {
            url = checkUser(request, response);
        } else if (action.equals("viewCookies")) {
            url = "/cookies.jsp";
        } else if (action.equals("deleteCookies")) {
            url = deleteCookies(request, response);
        }

        // forward to the view
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "viewAlbums";
        }

        // perform action and set URL to appropriate page
        String url = "/index.jsp";
        if (action.equals("registerUser")) {
            url = registerUser(request, response);
        } else if (action.equals("viewAlbums")) {
            url = "/index.jsp";
        } else if (action.equals("checkUser")) {
            url = checkUser(request, response);
        } else if (action.equals("viewCookies")) {
            url = "/cookies.jsp";
        } else if (action.equals("deleteCookies")) {
            url = deleteCookies(request, response);
        }

        // forward to the view
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String checkUser(HttpServletRequest request, HttpServletResponse response) {
        String productCode = request.getParameter("productCode");
        HttpSession session = request.getSession();

        if (productCode != null && !productCode.isEmpty()) {
            session.setAttribute("productCode", productCode);
        } else {
            productCode = (String) session.getAttribute("productCode");
            if (productCode == null) {
                productCode = "8601";
                session.setAttribute("productCode", productCode);
            }
        }

        User user = (User) session.getAttribute("user");
        String url;

        // if User object doesn't exist, check email cookie
        if (user == null) {
            Cookie[] cookies = request.getCookies();
            String emailAddress = CookieUtil.getCookieValue(cookies, "userEmail");
            if (emailAddress == null || emailAddress.isEmpty()) {
                emailAddress = CookieUtil.getCookieValue(cookies, "emailCookie");
            }

            // if cookie doesn't exist, go to Registration page
            if (emailAddress == null || emailAddress.isEmpty()) {
                url = "/register.jsp";
            } else {
                // if cookie exists, create User object and go to Downloads page
                ServletContext sc = getServletContext();
                String path = sc.getRealPath("/WEB-INF/EmailList.txt");
                user = UserIO.getUser(emailAddress, path);
                if (user == null) {
                    user = new User();
                    user.setEmail(emailAddress);
                }
                session.setAttribute("user", user);
                url = "/" + productCode + "_download.jsp";
            }
        } else {
            // if User object exists, go to Downloads page
            url = "/" + productCode + "_download.jsp";
        }
        return url;
    }

    private String registerUser(HttpServletRequest request, HttpServletResponse response) {
        // get the user data
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        // store the data in a User object
        User user = new User(firstName, lastName, email);

        // write the User object to a file
        ServletContext sc = getServletContext();
        String path = sc.getRealPath("/WEB-INF/EmailList.txt");
        try {
            UserIO.add(user, path);
        } catch (IOException e) {
            log("Error writing user to file", e);
        }

        // store the User object as a session attribute
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        // add cookies that store the user's email to browser
        // userEmail (3 years for Exercise 7-1)
        Cookie userEmailCookie = new Cookie("userEmail", email);
        userEmailCookie.setMaxAge(60 * 60 * 24 * 365 * 3);
        userEmailCookie.setPath("/");
        response.addCookie(userEmailCookie);

        // emailCookie (2 years for Chapter 7)
        Cookie emailCookie = new Cookie("emailCookie", email);
        emailCookie.setMaxAge(60 * 60 * 24 * 365 * 2);
        emailCookie.setPath("/");
        response.addCookie(emailCookie);

        // create and return a URL for the appropriate Download page
        String productCode = (String) session.getAttribute("productCode");
        if (productCode == null || productCode.isEmpty()) {
            productCode = "8601";
            session.setAttribute("productCode", productCode);
        }
        String url = "/" + productCode + "_download.jsp";
        return url;
    }

    private String deleteCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "/delete_cookies.jsp";
    }
}
