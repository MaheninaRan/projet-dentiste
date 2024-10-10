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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Alea", value = "/Alea")
public class Alea extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str_dents = request.getParameter("dent");
        String str_degat = request.getParameter("degat");

        List<Integer> dents_int_array = new ArrayList<>();
        List<Integer> degats_int_array = new ArrayList<>();

        if (str_dents.contains("-")) {
            String[] dents_temp = str_dents.split("-");
            int dent_min = Integer.parseInt(dents_temp[0].trim());
            int dent_max = Integer.parseInt(dents_temp[1].trim());
            int actu = dent_min;

            while (actu<=dent_max) {
                dents_int_array.add(actu);
                degats_int_array.add(Integer.parseInt(str_degat));
                actu = actu+1;

            }

        }else if (str_dents.contains(";")){
            String[] dents_temp = str_dents.split(";");
            String[] degat_temp = str_degat.split(";");

            for (int i=0;i<degat_temp.length; i++) {
                dents_int_array.add(Integer.parseInt(dents_temp[i].trim()));
                degats_int_array.add(Integer.parseInt(degat_temp[i].trim()));
            }
        } else {
            dents_int_array.add(Integer.parseInt(str_dents));
            degats_int_array.add(Integer.parseInt(str_degat));
        }

        try {
            Connection connection = new DBConnect().getConnection();
            Patient p = Patient.findById(connection, request.getParameter("patient"));
            p.insererEtatDent(new Date(new java.util.Date().getTime()), dents_int_array, degats_int_array, connection);
            connection.close();
            response.sendRedirect("consultation");
        }catch (SQLException | ClassNotFoundException ignored) {}

    }


}
