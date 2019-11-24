$(document).ready(function(){  

    images = ['https://images-na.ssl-images-amazon.com/images/I/41etyGVwOwL._SX326_BO1,204,203,200_.jpg','https://images-na.ssl-images-amazon.com/images/I/41LPBRNaVCL._SX355_BO1,204,203,200_.jpg','https://images-na.ssl-images-amazon.com/images/I/51OnU2r3LeL._SX329_BO1,204,203,200_.jpg','https://images-na.ssl-images-amazon.com/images/I/51skjDgMoCL._SX330_BO1,204,203,200_.jpg','https://images-na.ssl-images-amazon.com/images/I/41k66TFC43L._SX351_BO1,204,203,200_.jpg','https://images-na.ssl-images-amazon.com/images/I/41zqDXuSQEL._SX332_BO1,204,203,200_.jpg','https://images-na.ssl-images-amazon.com/images/I/41zqDXuSQEL._SX332_BO1,204,203,200_.jpg','https://images-na.ssl-images-amazon.com/images/I/61k34d72ChL._SX327_BO1,204,203,200_.jpg','https://images-na.ssl-images-amazon.com/images/I/51jGJglnMSL._SX314_BO1,204,203,200_.jpg','https://images-na.ssl-images-amazon.com/images/I/41ar83oNumL._SX320_BO1,204,203,200_.jpg','https://images-na.ssl-images-amazon.com/images/I/51NAWYoB-IL._SX331_BO1,204,203,200_.jpg']
    links = ['030758836X','0439023483','0375831002','038536315X','0439023513','0316055433','0385537859','0007444117','147674355X','0399159347']
    for(var i=0 ; i<images.length; i++) {
        //put everything in array and extract values here.
      $('<div class="item"><a href= "https:/amazon.com/dp/'+links[i]+'"><img src="'+images[i]+'" class="img-responsive center-block"></a><div class="carousel-caption"><h2>iPhone 11<h2><p>Rating 5 stars</p><p>Total Reviews:7894</p></div></div>').appendTo('.carousel-inner');
      $('<li data-target="#carousel-example-generic" data-slide-to="'+i+'"></li>').appendTo('.carousel-indicators')
  
    }
    $('.item').first().addClass('active');
    $('.carousel-indicators > li').first().addClass('active');
    $('#myCarousel').carousel();
  });