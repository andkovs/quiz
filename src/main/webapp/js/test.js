$(document).ready(function(){
    $("#loginDiv").show("slow");
    $("#login").show("slow");

    $("#login").click(function (){
        $("#loginDiv").hide("slow");
        $("#login").hide("slow");
        $("#chooseTestDiv").show("slow");
        $("#choose").show("slow");
    });

    $("#choose").click(function (){
        $("#chooseTestDiv").hide("slow");
        $("#choose").hide("slow");
        $("#processTestDiv").show("slow");
        $("#prev").show("slow");
        $("#next").show("slow");
        $("#send").show("slow");
    });
});



