package dentiste.controller;

import dentiste.model.DetailReparation;
import dentiste.model.Patient;
import dentiste.model.Priorisation;
import dentiste.model.persist.DBConnect;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "traitement", value = "/traitement")
public class Traitement extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = new DBConnect().getConnection();
            List<Patient> patients = Patient.findAll(connection);
            List<Priorisation> priorisations = Priorisation.findAll(connection);
            connection.close();

            request.setAttribute("patients", patients);
            request.setAttribute("priorisations", priorisations);
        }catch (SQLException | ClassNotFoundException ignored) { }

        request.getRequestDispatcher("traitement.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = new DBConnect().getConnection();
            connection.setAutoCommit(false);
            Patient patient = Patient.findById(connection, request.getParameter("patient"));
            int preference = Integer.parseInt(request.getParameter("priorite"));
            double amount = Double.parseDouble(request.getParameter("amount"));

            List<List<DetailReparation>> details = patient.traiter(amount, preference, connection);

            request.setAttribute("details", details);

            connection.commit();
            connection.close();

            request.getRequestDispatcher("details.jsp").forward(request, response);

        }catch (SQLException | ClassNotFoundException ignored) {
            throw new RuntimeException(ignored);
        }
    }
}
