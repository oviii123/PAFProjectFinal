package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
//For REST Service
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Inventory;

	@Path("/Inventory")
		public class InventoryService
		{
			Inventory inventoryObj = new Inventory();
@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readadmintb()
			{
				return inventoryObj.readadmintb() ;
			}


@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)

			public String insertInventory(
					@FormParam("appName") String appName,
					@FormParam("appSize") String appSize,
					@FormParam("reviews") String reviews,
					@FormParam("appDes") String appDes,
					@FormParam("appType") String appType,
					@FormParam("downloads") String downloads)
			
					{
						String output = inventoryObj.insertInventory(appName, appSize, reviews, appDes,appType,downloads);
						return output;
					}


@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)

			public String updateInventory(String inventoryData)
				{
//Convert the input string to a JSON object
					JsonObject inventoryObject = new JsonParser().parse(inventoryData).getAsJsonObject();
//Read the values from the JSON object
							String id = inventoryObject.get("id").getAsString();
							String appName = inventoryObject.get("appName").getAsString();
							String appSize = inventoryObject.get("appSize").getAsString();
							String reviews = inventoryObject.get("reviews").getAsString();
							String appDes = inventoryObject.get("appDes").getAsString();
							String appType = inventoryObject.get("appType").getAsString();
							String downloads = inventoryObject.get("downloads").getAsString();
							
							String output = inventoryObj.updateInventory(id, appName, appSize, reviews, appDes,appType,downloads);
							
							return output;
				}

@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)

			public String deleteInventory(String inventoryData)
				{
//Convert the input string to an XML document
					Document doc = Jsoup.parse(inventoryData, "", Parser.xmlParser());

//Read the value from the element <itemID>
							String id = doc.select("id").text();
							String output = inventoryObj.deleteInventory(id);
return output;
}


}
