<html>
<head>
    <link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css"/>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
<#if RequestParameters['error']??>
	<div class="alert alert-danger">
		There was a problem logging in. Please try again.
	</div>
</#if>
	<div class="container">
		<form role="form" action="login" method="post">
		  <div class="form-group">
		    <label for="username">Username:</label>
		    <input type="text" class="form-control" id="username" name="username"/>
		  </div>
		  <div class="form-group">
		    <label for="password">Password:</label>
		    <input type="password" class="form-control" id="password" name="password"/>
		  </div>
		  <input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		  <button type="submit" class="btn btn-primary">Submit</button>
		</form>
		<section>
            <div>
                With Facebook: <a href="login/facebook">click here</a>
            </div>
            <div>
                With Github: <a href="login/github">click here</a>
            </div>
		</section>
	</div>
</body>
</html>