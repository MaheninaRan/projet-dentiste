<%@ page import="dentiste.model.DetailReparation" %>
<%@ page import="java.util.List" %>
<%@ page import="dentiste.model.Patient" %>
<%@ page import="dentiste.model.Dent" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<List<DetailReparation>> details = (List<List<DetailReparation>>) request.getAttribute("details");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        table {
            width: 70%;
            color: black; /* Texte noir */
            border-collapse: collapse;
            margin:auto;
        }
        th{
            text-align: center;
            background-color: #ff4545; /* Fond rouge */

        }
        th, td {

            border: 1px solid black;
            padding: 10px;
        }
        legend{
            text-align: center;
            font-size: 30px;
        }
        hr{
            width: 200px;
        }
    </style>
    <title>Tableau Rouge</title>
</head>
<body>

<table>
    <th>Dent</th>
    <th>Nom</th>
    <th>Traitement</th>
    <th>Prix</th>
    <th>Total</th>
    <tbody>
    <% for (List<DetailReparation> detailReparations : details) { %>
    <tr>
        <td scope="row"><%= detailReparations.get(0).getDent().getCode()%></td>
        <td><%= detailReparations.get(0).getDent().getNom()%></td>
        <td>
            <ul>
                <% for (DetailReparation dr : detailReparations) {%>
                <li><%= dr.getDescription()%></li>
                <% } %>
            </ul>
        </td>
<%--        <td><%= detailReparations.get(0).getCout()%></td>--%>
        <td>
            <ul>

                <%
                    for (DetailReparation dr : detailReparations) {

                %>

                    <li><%= dr.getCout() %></li>
                <% } %>
            </ul>
        </td>



    </tr>
    <% } %>

    <!-- Ajoutez autant de lignes que nécessaire -->
    </tbody>
</table>

</body>
</html>
