google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawMultSeries);

function drawMultSeries() {
    // productname = ['Gone Girl','Hunger Game','the book theif','Sycamore Row','mocking jay','The goldfinch','inferno','Allegeiant','Hopeless','The husbands Secret'];
      var data = google.visualization.arrayToDataTable([
        ['Name', '5 star reviews', '1 star reviews'],
        ['PlayStation 4 500GB', 721, 81],
        ['PlayStation 3 Dualshock', 624, 28],
        ['The Last of Us', 494, 23],
        ['Xbox Live GOLD', 452, 23],
        ['Wii', 419, 28],
        ['Halo', 401, 40],
        ['Xbox 1600 points', 421, 4],
      ]);

      var options = {
        title: 'Rating of the Top Video Games',
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