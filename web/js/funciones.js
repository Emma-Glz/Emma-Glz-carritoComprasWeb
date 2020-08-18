/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("tr #btnDelete").click(function(){
       var idp=$(this).parent().find("#idp").val();
       swal({
            title: "Estas seguro de Eliminar?",
            text: "Once deleted, No seras capaz de recuperar!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
      })
      .then((willDelete) => {
                if (willDelete) {
                  eliminar(idp);  
                  swal("Poof! Your imaginary file has been deleted", {
                    icon: "success",
                  }).then((willDelete)=>{
                      if(willDelete){
                          parent.location.href="Controlador?accion=Carrito";
                      }
                  });
                } else {
                  swal("Registro NO eliminado.!");
                }
      });
       
        
    });
        function eliminar(idp){
            var url="Controlador?accion=Delete";
            $.ajax({
            type: 'POST',
            url: url,
            data: "idp="+idp,
            success: function(data,textStatus,jqXHR){
            }
        });
    }
    
});



