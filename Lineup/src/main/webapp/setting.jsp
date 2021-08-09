<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Settings</title>
    <link rel="stylesheet" href="styles.css" type="text/css">
  </head>
  <nav>
    <div class="logo">
    </div>

      <div class="topnav">
        <a href="home.jsp">Home</a>
        <a href="setting.jsp">Settings</a>
        <a href="index.jsp">SignOut</a>
      </div>
  </nav>
  <body>
    <h1>Settings</h1>

    <h4>Please provide all of the following information.</h4>

    <form class="" action="settingServlet" method="post">
      <label for="firstName"><b>First Name</b></label>
      <input type="text" name="firstName" id="firstName" placeholder="First Name" required><br/>

      <label for="lastName"><b>Last Name</b></label>
      <input type="text" name="lastName" id="lastName" placeholder="Last Name" required><br/>

      <label for="newEmail"><b>Email</b></label>
      <input type="text" name="newEmail" id="newEmail" placeholder="email@example.com" required><br/>

      <label for="phoneNum"><b>Phone Number</b></label>
      <input type="text" name="phoneNum" id="phoneNum" placeholder="123-456-7890" required><br/>

      <label for="newPassword"><b>Password</b></label>
      <input type="password" name="newPassword" id="newPassword" placeholder="Password" required><br/>

      <input type="checkbox" name="agree" id="agree" required>
      <label for="agree"><sub>I agree that the information provided above is correct</sub></label><br/>

      <input type="submit" name="update" value="Update">
    </form>
    <br>

    <form id="delete" action="deleteServlet" method="get">
      <input type="submit" name="delete" id="delete" value="Delete Account"/>
    </form>

    <p> ${infoMessage} </p>

  </body>
</html>