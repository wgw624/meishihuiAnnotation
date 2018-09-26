function calMainPage(){
 var h = $(document).height();
    var w = $(document).width();
    console.log("h:"+h+"  w:"+w);
    $("#leftMenu").css("height",h);
    $("#rightMenu").css("height",h-2);
    
    $("#topHead").css("width",w-200);
    $("#bodyContent").css("width",w-200-2);
    $("#bodyContent").css("height",h-40-2);

}