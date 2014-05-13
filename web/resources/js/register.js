/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $r = [
        'Iquique',
        'Antofagasta',
        'Copiapó',
        'La Serena',
        'Valparaíso',
        'Rancagua',
        'Talca',
        'Concepción',
        'Temuco',
        'Puerto Montt',
        'Puerto Aysén',
        'Punta Arenas',
        'Santiago',
        'Arica',
        'Valdivia'
    ]
    
    $('#regionSelectContainer select option').click(function(){
       $region = $('#regionSelectContainer select').val();
       $city = $r[$region - 1];
       $('#communeSelectContainer select option').text($city);
       $('#communeSelectContainer select option').val(9);
    });
})

