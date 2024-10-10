package dentiste.controller;

import dentiste.model.Dent;
import dentiste.model.Patient;
import dentiste.model.persist.DBConnect;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "consultation", value = "/consultation")
public class Consultation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Connection connection = new DBConnect().getConnection();
            List<Dent> dents = Dent.findAll(connection);
            List<Patient> patients = Patient.findAll(connection);
            connection.close();

            request.setAttribute("dents", dents);
            request.setAttribute("patients", patients);
        }catch (SQLException | ClassNotFoundException ignored) { }

        request.getRequestDispatcher("consultation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] dents = request.getParameterValues("dents[]");
        String[] degats = request.getParameterValues("degats[]");

        try {
            Connection connection = new DBConnect().getConnection();
            Patient p = Patient.findById(connection, request.getParameter("patient"));
            p.insererEtatDent(new Date(new java.util.Date().getTime()), dents, degats, connection);
            connection.close();
            response.sendRedirect("consultation");
        }catch (SQLException | ClassNotFoundException ignored) {}
    }
}
