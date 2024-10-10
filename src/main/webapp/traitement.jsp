<%@ page import="dentiste.model.Patient" %>
<%@ page import="java.util.List" %>
<%@ page import="dentiste.model.Priorisation" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  List<Patient> patients = (List<Patient>) request.getAttribute("patients");
  List<Priorisation> priorisations = (List<Priorisation>) request.getAttribute("priorisations");
%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
  <meta charset="UTF-8" />
  <title>Traitement</title>
  <link rel="stylesheet" href="assets/style.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <style>
    #container .treatment-form {
      background: white; /* Fond noir */
      padding: 20px;
      border-radius: 10px;
      width: 70%;
      max-width: 600px;
      margin: auto;
      box-shadow: 0 0 10px rgba(255, 69, 69, 0.5); /* Ombre rouge */
    }

    #container label {
      display: block;
      margin-bottom: 8px;
      font-weight: 600;
      color: #ff4545; /* Texte rouge */
    }

    #container .form-control {
      width: 100%;
      padding: 8px;
      margin-bottom: 15px;
      border: 1px solid #ff4545; /* Bordure rouge */
      border-radius: 4px;
      color: #ff4545; /* Texte rouge */
      background-color: #ffffff; /* Fond blanc */
    }

    #container .form-group {
      margin-bottom: 15px;
    }

    #container .submit-button {
      background: #1a1a1a; /* Fond noir */
      color: #ff4545; /* Texte rouge */
      padding: 10px 20px;
      border: 1px solid #ff4545; /* Bordure rouge */
      border-radius: 4px;
      cursor: pointer;
    }
  </style>
</head>
<body>

<div id="container">
  <form action="${pageContext.request.contextPath}/traitement" method="post" class="treatment-form">
    <div class="form-group">
      <label for="selectPatient">Patient :</label>
      <select id="selectPatient" name="patient" class="form-control">
        <% for (Patient p : patients) { %>
        <option value="<%=p.getId()%>"><%= p.getNom()%></option>
        <% } %>
      </select>
    </div>

    <div class="form-group">
      <label for="selectPriority">Choix Priorité :</label>
      <select id="selectPriority" name="priorite" class="form-control">
        <% for (Priorisation p : priorisations) { %>
        <option value="<%=p.getId()%>"><%= p.getNom()%></option>
        <% } %>
      </select>
    </div>

    <div class="form-group">
      <label for="amount">Montant:</label>
      <input type="number" id="amount" name="amount" class="form-control" />
    </div>

    <div class="form-group">
      <input type="submit" value="Valider" class="submit-button" />
    </div>
  </form>
</div>

<div class="button">
  <a href="#container"><i class="fa-solid fa-arrow-up"></i></a>
</div>

</body>
</html>
