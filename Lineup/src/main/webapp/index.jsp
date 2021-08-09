<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>LineUp</title>
    <link rel="stylesheet" href="styles.css" type="text/css">
  </head>
  <nav>
    <div class="logo">
    </div>

      <div class="topnav">
        <a href="register.jsp">Sign Up</a>
      </div>
  </nav>
  <header>
    <h1>Welcome to Lineup</h1>
    <h4>Please sign in below to access a student's daily sign-in/sign-out.</h4>
  </header>
  <body>
        <div>
        <form class="signIn" action="loginServlet" method="post">

          <div class="title">
            <h2>Login</h2>
          </div>

          <label for="email"><b>Email</b></label>
          <input type="text" name="email" id="email" placeholder="Email" required>
          <br>
          <label for="password"><b>Password</b></label>
          <input type="password" name="password" id="password" placeholder="Password" required>
          <br>
          <input type="submit" value="Login">
        </form>
      </div>
        <div class="">
          <a href="forgotPassword.jsp">Forgot Password</a>
        </div>

<p> ${errorMessage} </p>

  </body>
</html>