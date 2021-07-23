<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Register</title>
    <link rel="stylesheet" href="styles.css" type="text/css">
  </head>
  <nav>
    <div class="logo">
    </div>

      <div class="topnav">
        <a href="index.jsp">Login</a>
        <a href="register.jsp">Sign Up</a>
      </div>
  </nav>
  <body>

<form class="" action="registrationServlet" method="post">

  <div class="title">
    <h2>Register</h2>
    <h4>Please enter all of the information below to create an Account.</h4>
  </div>

  <label for="firstName"><b>First Name</b></label>
  <input type="text" name="firstName" id="firstName" placeholder="First Name" required><br/>
  <label for="lastName"><b>Last Name</b></label>
  <input type="text" name="lastName" id="lastName" placeholder="Last Name" required><br/>

  <label for="email"><b>Email</b></label>
  <input type="text" name="email" id="email" placeholder="email@example.com" required><br/>

  <label for="phoneNum"><b>Phone Number</b></label>
  <input type="text" name="phoneNum" id="phoneNum" placeholder="123-456-7890" required><br/>

  <label for="password"><b>Password</b></label>
  <input type="password" name="password" id="password" placeholder="Password" id="password" required><br/>

  <label>What type of user are you? </label>
  <input type="radio" name="userType" id="userType" value="Guardian" checked>Guardian
  <input type="radio" name="userType" id="userType" value="Staff">Staff<br/>

  <input type="submit" value="Submit" id="submit">
</form>

<p> ${infoMessage} </p>

  </body>
</html>
