google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawMultSeries);

function drawMultSeries() {
    // productname = ['Gone Girl','Hunger Game','the book theif','Sycamore Row','mocking jay','The goldfinch','inferno','Allegeiant','Hopeless','The husbands Secret'];
      var data = google.visualization.arrayToDataTable([
        ['Name', '5 star reviews', '1 star reviews'],
        ['Magula UpLULA', 1038, 4],
        ['Howard Leight', 719,443],
        ['Rothco 550lb', 630, 17],
        ['SE FS374', 463, 50],
        ['Hoppes Boresnake Viper', 416, 11],
        ['OFF SET TACTICAL', 401, 392],
        ['Solomone Cavalli',398,114],
        ['Mylar Mens Emergency', 388, 5]
        
      ]);

      var options = {
        title: 'Rating of the Top Sports',
        chartArea: {width: '50%'},
        hAxis: {
          title: 'Number of reviews',
          minValue: 0
        },
        vAxis: {
          title: 'Name of the book'
        }
      };

      var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
      chart.draw(data, options);
    }