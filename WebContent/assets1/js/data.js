

$( document ).ready(function() {
        $.ajax({
        url : 'assets/my.json',
        type : 'json',
        method : 'GET',
        success : function(data){
            console.log(data);
        }
    });
    //console.log(mydata);
});