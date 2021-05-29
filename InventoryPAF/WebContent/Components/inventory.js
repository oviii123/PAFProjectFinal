$(document).ready(function()
	{ 
		if ($("#alertSuccess").text().trim() == "") 
	{ 
		$("#alertSuccess").hide(); 
	} 
		$("#alertError").hide(); 
	}); 
// SAVE ============================================
		$(document).on("click", "#btnSave", function(event) 
				{ 
// Clear alerts---------------------
					$("#alertSuccess").text(""); 
					$("#alertSuccess").hide(); 
					$("#alertError").text(""); 
					$("#alertError").hide(); 
// Form validation-------------------
					var status = validateInventoryForm(); 
					if (status != true) 
				{ 
						$("#alertError").text(status); 
						$("#alertError").show(); 
					return; 
				} 
					
					var type = ($("#hidInventoryIDSave").val() == "") ? "POST" : "PUT";	
					
					$.ajax(
							{ 
							 url : "InventoryAPI", 
							 type : type, 
							 data : $("#formInventory").serialize(), 
							 dataType : "text", 
							 complete : function(response, status) 
							 { 
							 onInventorySaveComplete(response.responseText, status); 
							 } 
							});
					
			function onInventorySaveComplete(response, status)
					{ 
					if (status == "success") 
					  { 
						var resultSet = JSON.parse(response); 
						if (resultSet.status.trim() == "success") 
					  { 
					    $("#alertSuccess").text("Successfully saved."); 
					    $("#alertSuccess").show(); 
					    $("#divInventoryGrid").html(resultSet.data); 
					  } else if (resultSet.status.trim() == "error") 
					  { 
					    $("#alertError").text(resultSet.data); 
					    $("#alertError").show(); 
					  } 
					  } else if (status == "error") 
					  { 
					    $("#alertError").text("Error while saving."); 
					    $("#alertError").show(); 
					  } else
					  { 
					    $("#alertError").text("Unknown error while saving.."); 
					    $("#alertError").show(); 
					  }
					    $("#hidInventoryIDSave").val(""); 
					    $("#formInventory")[0].reset(); 
					  }
			
			
			$(document).on("click", ".btnRemove", function(event)
					{ 
					 $.ajax( 
					 { 
					 url : "InventoryAPI", 
					 type : "DELETE", 
					 data : "InventoryID=" + $(this).data("id"),
					 dataType : "text", 
					 complete : function(response, status) 
					 { 
					 onInventoryDeleteComplete(response.responseText, status); 
					 } 
					 }); 
					});

			
			function onInventoryDeleteComplete(response, status)
			{ 
			if (status == "success") 
			 { 
			 var resultSet = JSON.parse(response); 
			 if (resultSet.status.trim() == "success") 
			 { 
			 $("#alertSuccess").text("Successfully deleted."); 
			 $("#alertSuccess").show(); 
			 $("#divInventorysGrid").html(resultSet.data); 
			 } else if (resultSet.status.trim() == "error") 
			 { 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
			 } 
			 } else if (status == "error") 
			 { 
			 $("#alertError").text("Error while deleting."); 
			 $("#alertError").show(); 
			 } else
			 { 
			 $("#alertError").text("Unknown error while deleting.."); 
			 $("#alertError").show(); 
			 } 
			}
			
			
			
			
					
					
// If valid------------------------
					$("#formInventory").submit(); 
				}); 
// UPDATE==========================================
					$(document).on("click", ".btnUpdate", function(event) 
				{ 
 $("#hidInventoryIDSave").val($(this).closest("tr").find('#hidInventoryIDUpdate').val()); 
 $("#appName").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#appSize").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#reviews").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#appDes").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#appType").val($(this).closest("tr").find('td:eq(4)').text()); 
 $("#downloads").val($(this).closest("tr").find('td:eq(5)').text()); 
 
 
}); 
// CLIENT-MODEL================================================================
function validateInventoryForm() 
{ 
// CODE
if ($("#appName").val().trim() == "") 
 { 
 return "Insert App Name."; 
 } 
// NAME
if ($("#appSize").val().trim() == "") 
 { 
 return "Insert app size."; 
 } // PRICE-------------------------------
if ($("#reviews").val().trim() == "") 
{ 
return "Insert reviews."; 
} 

//is numerical value
//var tmpPrice = $("#itemPrice").val().trim(); 
//if (!$.isNumeric(tmpPrice)) 
//{ 
//return "Insert a numerical value for Item Price."; 
//} 
//convert to decimal price
//$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2)); 
//DESCRIPTION------------------------
//if ($("#itemDesc").val().trim() == "") 
//{ 
//return "Insert Item Description."; 
//} 
return true; 
}