/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//ARCHIVO JS 
$.get("http://localhost:4567/listaEquipos", function(data, status) {
	if (status === 'success') {
		var dataAgain = JSON.parse(data);
		for (var i = 0; i < dataAgain.length; i++) {
			$("#listaEquipos").append(
					'<li>' + dataAgain[i].nombreEquipo + '</li>')
		}
	} else {
		console.log('Error');
	}
});
