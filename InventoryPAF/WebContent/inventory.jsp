 <%@ page import="model.Inventory" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
    <% 
    session.setAttribute("statusMsg","");
    System.out.println("Trying to process....");
    
    
    if (request.getParameter("appName") != null) 
    { 
    	Inventory inventoryObj = new Inventory();
    	 String stsMsg = ""; 
    	//Insert--------------------------
    	if (request.getParameter("hidInventoryIDSave") == "") 
    	 { 
    	 stsMsg = inventoryObj.insertInventory(request.getParameter("appName"), 
    	 request.getParameter("appSize"), 
    	 request.getParameter("reviews"), 
    	 request.getParameter("appDes"),
    	 request.getParameter("appType"),
    	 request.getParameter("downloads"));
    	
    	 		 
    	 } 
    	
    	//Update----------------------
    	else
    	 { 
    	 stsMsg = inventoryObj.updateInventory(request.getParameter("hidInventoryIDSave"), 
    			 request.getParameter("appName"),
    			 request.getParameter("appSize"), 
    	    	 request.getParameter("reviews"), 
    	    	 request.getParameter("appDes"),
    	    	 request.getParameter("appType"),
    	    	 request.getParameter("downloads"));
    	 } 
    	 session.setAttribute("statusMsg", stsMsg); 
    	} 
    	//Delete-----------------------------
    	if (request.getParameter("hidInventoryIDDelete") != null) 
    	{ 
    	 Inventory inventoryObj  = new Inventory();
    	 String stsMsg = 
    	 inventoryObj.deleteInventory(request.getParameter("hidInventoryIDDelete")); 
    	 session.setAttribute("statusMsg", stsMsg); 
    	} %>
    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/inventory.js"></script>
</head>
<body>

	<div class="container">
 		<div class="row">
 		<div class="col-8">
 			
 				<h1 class="m-3">Payment Details</h1>
 
		<form id="formInventory" name="formInventory" method="post" action="inventory.jsp">
			App Name:
			<input id="appName" name="appName" type="text"
 			class="form-control form-control-sm">
 			
			<br> 
			App Size:
			<input id="appSize" name="appSize" type="text"
 			class="form-control form-control-sm">
		
			<br>
			Reviews:
			<input id="reviews" name="reviews" type="text"
			 class="form-control form-control-sm">

			<br> 
			App Description:
			<input id="appDes" name="appDes" type="text"
			class="form-control form-control-sm">
			
			<br> 
			App Type:
			<input id="appType" name="appType" type="text"
			class="form-control form-control-sm">
			
			<br> 
			Downloads:
			<input id="downloads" name="downloads" type="text"
			class="form-control form-control-sm">
			
			
			
			<br>
			<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
			<input type="hidden" id="hidInventoryIDSave" name="hidInventoryIDSave" value="">
			
		</form>
		
		<div id="alertSuccess" class="alert alert-success">
			<%
				out.print(session.getAttribute("statusMsg"));
			%>
		</div>
			
		
		
		<br>
		<div>
		
		<% 
		Inventory inventoryObj = new Inventory();
		 out.print(inventoryObj.readadmintb());
		 %>
		 
		 </div>
		
 		</div>
 		</div>

 <br>
 <div class="row">
 <div class="col-12" id="colStudents">

 </div>
 </div>
</div>


</body>
</html>