$(document).ready(function(){


    $("button").click(function(){
      $.get("localhost:8080/shops/", function(data, status){
          if(status!=404){                    
                $(".shopstable").append("<tr><th>id</th>");
                $(".shopstable").append("<td>name</th>");
                $(".shopstable").append("</th>capacity</th></tr>")
            let noteSet = jQuery.parseJSON(data);
            noteSet.forEach(element => {   
                $(".shopstable").append("<td>"+element.id+"</td>");
                $(".shopstable").append("<td>"+element.name+"</td>");
                $(".shopstable").append("</td>"+ element.capacity+"</td>");
          }
              
          });
          $(".shopstable").
      });
    });
  });