<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DevPaymentPage</title>
</head>
<body>
<form action="DevPaymentPage" method="get">
<table>
<tr>
	<td>ORDER ID</td>
	<td><input type="text" name="Order_Id" value=<%= request.getAttribute("ORDER_ID") %> ></td>
</tr>
<tr>
	<td>MERCHANT ID</td>
	<td><input type="text" name="Merchant_id" value=<%= request.getAttribute("MERCHANT_ID") %> ></td>
</tr>
<tr>
	<td>AMOUNT</td>
	<td><input type="text" name="Amount" value=<%= request.getAttribute("AMOUNT") %> ></td>
</tr>
</table>


<input type="submit" value="Submit" />
 

</form>

</body>
</html>