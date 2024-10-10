<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dentiste-Index</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            height: 100vh;
            background-color: #f0f0f0;
        }

        h1 {
            color: #e74c3c;
            font-family: 'Arial', sans-serif;
            font-size: 28px;
            text-shadow: 2px 2px 2px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        .container {
            display: flex;
            justify-content: space-between;
            width: 700px;
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            margin-top: 20px;
        }

        .box {
            width: 180px;
            height: 180px;
            background-color: #3498db;
            border-radius: 10px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #ffffff;
            font-size: 18px;
        }

        .box.red {
            background-color: #e74c3c;
        }
    </style>
</head>
<body>
<h1 style="font-size: 50px;">Dentiste</h1>
<div class="container">
    <a href="consultation"><div class="box red">Consultation</div> </a>
    <a href="traitement"><div class="box red">Voir resultat</div> </a>
    <a href="alea.jsp"><div class="box red">Alea</div> </a>
</div>
</body>
</html>
