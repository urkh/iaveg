$(document).ready(function() {
	
	$('#buscarCedula').click(function(){
		var cedula = $("#cedula").val();
        $.getJSON('/buscarCedula?busqueda='+cedula, function(data) {
        	for (var i in data) {
        		$('input[name="'+i+'"]').val(data[i]);
            }
        }).error(function(data) {
            window.alert('Error: ' + data.responseText);
        });    
	});
    
});



