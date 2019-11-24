google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawMultSeries);

function drawMultSeries() {
    // productname = ['Gone Girl','Hunger Game','the book theif','Sycamore Row','mocking jay','The goldfinch','inferno','Allegeiant','Hopeless','The husbands Secret'];
      var data = google.visualization.arrayToDataTable([
        ['Name', '5 star reviews', '1 star reviews'],
        ['Gone Girl', 6233, 1207],
        ['Hunger Game', 6454, 263],
        ['The book theif', 4674, 190],
        ['Sycamore Row', 4463, 190],
        ['Mocking Jay', 3958, 482],
        ['The goldfinch', 3521, 784],
        // ['inferno', 3697, 587],
        // ['Allegeiant',2778,1043],
        ['Hopeless', 3591, 134],
        // ['The husbands Secret', 3432, 223]
      ]);

      var options = {
        title: 'Rating of the Top Books',
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