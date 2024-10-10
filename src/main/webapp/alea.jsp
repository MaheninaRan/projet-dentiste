  <%@ page import="java.util.List" %>
  <%@ page import="dentiste.model.Dent" %>
  <%@ page import="dentiste.model.Patient" %>
  <%@ page import="dentiste.model.persist.DBConnect" %>
  <%@ page import="java.sql.Connection" %>
  <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%
    Connection connection = new DBConnect().getConnection();
    List<Patient> patients = Patient.findAll(connection);
  %>
  <!DOCTYPE html>
  <html lang="en" dir="ltr">
  <head>
    <meta charset="UTF-8" />
    <title>Consultation</title>
    <link rel="stylesheet" href="assets/style.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <style>
      body {
        background-color: #ffffff;
        color: #000000;
        font-family: Arial, sans-serif;
      }

      #container .consultation-form {
        background: #ff0000;
        padding: 20px;
        border-radius: 10px;
        width: 70%;
        max-width: 600px;
        margin: auto;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      }

      #container label {
        display: block;
        margin-bottom: 8px;
        font-weight: 600;
        color: #ffffff;
      }

      #container .form-control {
        width: 100%;
        padding: 8px;
        margin-bottom: 15px;
        border: 1px solid #000000;
        border-radius: 4px;
      }

      #container .form-row {
        display: flex;
        justify-content: space-between;
      }

      #container .form-group {
        margin-bottom: 15px;
      }

      #container .add-button {
        background: #ff0000;
        color: #ffffff;
        padding: 8px 15px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
      }

      #container .submit-button {
        background: #000000;
        color: #ffffff;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
      }

    </style>
  </head>
  <body>

  <div id="container">
    <form action="${pageContext.request.contextPath}/alea1.jsp" method="post" class="consultation-form">
      <label for="selectPatient">Patient :</label>
      <select id="selectPatient" name="patient" class="form-control">
        <% for (Patient p : patients) { %>
        <option value="<%=p.getId()%>"><%=p.getNom()%></option>
        <% } %>
      </select>
      <div class="form-group">
        <input type="submit" value="Valider" class="submit-button" />
      </div>
    </form>
  </div>

  <div class="button">
    <a href="#container"><i class="fa-solid fa-arrow-up"></i></a>
  </div>

  <script>
    function addFields() {
      const container = document.getElementById("dynamicFields");
      const newField = container.cloneNode(true);
      container.parentNode.insertBefore(newField, document.querySelector('.submit-button').parentNode);
    }
  </script>

  </body>
  </html>
