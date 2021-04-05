var selectedShop;
$(document).ready(function(){
	loadShops();
	$(".oneshop").click(function(){
	var r = /\d+/;
	var myClass = $(this).attr("id");
	$(".shopstable-div").hide();
	$(".picturestable-div").show();
	loadShopPictures(myClass.match(r));
});

 });

function showForm(){
	
	$(".shopstable-div").hide();
	$(".picturestable-div").hide();
	$(".formulario-div").show();
	$(".formulario-div").css("visibility", "visible");
}
 function loadShops(){
	      $.get("http://localhost:8080/shops/", function(data, status){
          if(status!=404){
			if(data.length!=0){
				
				
			
				let g="<thead><tr><th>id</th><th>name</th><th>capacity</th><th>actions</th><tr></thead>"   ;           
                //$(".shopstable").append("<thread><tr><th>id</th>");
                //$(".shopstable").append("<th>name</th>");
                //$(".shopstable").append("<th>capacity</th></tr></thread>")
            //let noteSet = jQuery.parseJSON(data);
            data.forEach(element => {
				g += "<tr class=' shop"+element.id+"'><td><span>"+element.id+"</span></td>"+"<td><span>"+element.name+"</span></td>"+"<td><span>"+ element.capacity+"</span></td>";
                g += '<td><span><button type="button" onClick="loadShopPictures('+element.id+')"class="oneshop btn btn-warning" id="shop"'/*+element.id*/+'  >Visualizar</button></span>';
                g += '<span><button type="button" onClick="deleteAllPictures('+element.id+')"class="oneshop btn btn-danger" id="shop"'/*+element.id*/+'  >Incendiar cuadros</button></span></td>';
				g +="</tr>";

				//$(".shopstable").append("<tr><td><span>"+element.id+"</span></td>");
                //$(".shopstable").append("<td><span>"+element.name+"</span></td>");
                //$(".shopstable").append("<td><span>"+ element.capacity+"</span></td></tr>");
          });
			$(".shopstable").append(g);
			}
        }else{
          $(".shopstable").html("error")
        } 
          });
}

$("button").click(function(){
	var r = /\d+/;
	var myClass = $(this).attr("id");
	$(".shopstable-div").hide();
	$(".picturestable-div").css("visibilty", "visible");
	loadShopPictures(myClass.match(r))
});


  function loadShopPictures(shopId){
		selectedShop=shopId
		$(".shopstable-div").hide();
		$(".picturestable-div").css("visibility", "visible");
	      $.get("http://localhost:8080/shops/"+shopId+"/pictures", function(data, status){
          if(status!=404){
			if(data.length!=0){
				
				
			
				let g="<thead><tr><th>id</th><th>author</th><th>name</th><th>price</th><th>creation Date</th><th>creation Date</th></tr></thead>"   ;           

            data.forEach(element => {
				g += "<tr class=\"picture"+element.id+"\">"+"<td><span>"+element.id+"</span></td>"+"<td><span>"+element.author+"</span></td>"+"<td><span>"+element.name+"</span></td>"+"<td><span>"+element.price+"</span></td>"+"<td><span>"+ element.createdDate+"</span></td>";
                g += '<td><span><button type="button" class="onepicture btn btn-warning" id="shop'+element.id+'">Modificar</button></span></td>';
				g +="</tr>";

          });
			$(".picturestable").append(g);
			}
        }else{
          $(".picturestable").html("error")
        } 
          });
}
function sendShop(){
	let name=$("#field-name").val();
	let capacity = $("#capacity-field").val();
	/*
	$.post( "http://localhost:8080/shops/", { "name": name, "capacity": capacity } )
		.done(function( data ) {
  		  alert( "Data Loaded: " + data );
  });
*/
	$.ajax("http://localhost:8080/shops/", {
      data: JSON.stringify({name: name, 
                                     capacity: capacity} ),
      method: "POST",
      contentType: "application/json"
   });
location.reload()
}
function sendPicture(){
	let name = $("#field-namepicture").val();
	let price = $("#price-field").val();
	let author = $("author-field").val();
	if(name=="" || price==""){
		return;
	}
	/*
	$.post( "http://localhost:8080/shops/", { "name": name, "capacity": capacity } )
		.done(function( data ) {
  		  alert( "Data Loaded: " + data );
  });
*/
	if(author!=""){
		$.ajax("http://localhost:8080/shops/"+selectedShop+"/pictures", {
      data: JSON.stringify({name: name, 
                                     price: price,
							author: author
} ),
      method: "POST",
      contentType: "application/json"
   });
	}else{
		
	
		$.ajax("http://localhost:8080/shops/", {
		      data: JSON.stringify({name: name, 
		                                     price: price
				} ),
		      method: "POST",
		      contentType: "application/json"
   	});

}
location.reload()
}
function showPictureForm(){
	$(".picturestable-div").hide();
	$("#picureForm-div").css("visibility", "visible");
}

function deletePictures(){
	let shopId=selectedShop;
	$.ajax({
    url: "http://localhost:8080/shops/"+shopId+"/pictures",
    type: 'DELETE',
    success: function(result) {
        // Do something with the result
		alert("Los cuadros están en llamas");
		console.log(result);
		location.reload()
    }
});
}
function deleteAllPictures(shopId){
	$.ajax({
    url: "http://localhost:8080/shops/"+shopId+"/pictures",
    type: 'DELETE',
    success: function(result) {
        // Do something with the result
		alert("Los cuadros están en llamas");
		console.log(result);
		location.reload()
    }
});
}