<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Forgot Password</title>
        <link rel="stylesheet" href="styles.css" type="text/css">
  </head>
  <nav style="margin-bottom: 50px">
    <div class="logo">
        <a href="#" type="image"></a>
    </div>
      <div class="topnav">
        <a href="index.jsp">Login</a>
      </div>
  </nav>
<body>
<p>Please enter your email, and if you are registered for Lineup, we will send you a temporary recovery password.</p>

	<form class="forgotPassword" action="forgotPasswordServlet" method="Post">     
       <label for="email"><b>Email</b></label>
          <input type="text" name="email" id="email" placeholder="Email" required>
      <input type="submit" name="forgotPassword" value="Send me my Password!">
    </form>
<p> ${infoMessage} </p>

</body>
</html>