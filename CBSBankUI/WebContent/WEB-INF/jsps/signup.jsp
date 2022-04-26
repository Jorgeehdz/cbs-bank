<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<style>
.login{
	border: 1px #000 solid; 
	border-radius: 20px;
	padding: 10px;
	margin-top: 100px;
	
}
</style>
</head>
<body>
<div class="row">
	<div class="col-sm-3"></div>
	<div class="col-sm-6">
		<div class="login">
			<h3>Sign-Up</h3>
		 	<form action="<%=request.getContextPath() %>/process-sign-up" method="post"> 
			<label class="form-label">Name
			</label>
  			<input type="text" class="form-control" name="name">
  			<br> 
			<label class="form-label">Username/Email address
			</label>
  			<input type="email" class="form-control" name="username">
  			<br> 
  			<label class="form-label">Password
			</label>
  			<input type="password" class="form-control" name="password">
  			
  			<hr>
  			<label class="form-label">Type of Account
			</label>
			<select name="accountType" class="form-control">
			<option value="">---select account type---</option>
			<option value="savings">Savings</option>
			<option value="business">Business</option>
			</select>
			<br>
			<label class="form-label">Security Question
			</label>
  			<input type="text" class="form-control" name="question">
  			<br> 
  			<label class="form-label">Security Answer
			</label>
  			<input type="text" class="form-control" name="answer">
  			<br> 
  			<input type="submit" class="btn btn-primary" value="SIGN UP" />
  			</form>
  			
  			<hr>
  			<a href="<%=request.getContextPath() %>/">Login</a>
		</div>
	</div>
</div>
</body>
 
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
 
</html>