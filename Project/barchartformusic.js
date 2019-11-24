google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawMultSeries);

function drawMultSeries() {
    // productname = ['Gone Girl','Hunger Game','the book theif','Sycamore Row','mocking jay','The goldfinch','inferno','Allegeiant','Hopeless','The husbands Secret'];
      var data = google.visualization.arrayToDataTable([
        ['Name', '5 star reviews', '1 star reviews'],
        ['The Massacre 50 Cent', 127, 145],
        ['Get Rich or Die Tryin', 196,75],
        ['The Eminem Show Explicit', 165, 39],
        ['The Marshall Mathers Explicit', 182, 20],
        ['Come Away with me', 159, 31],
        ['The DocumentaryL', 154, 31],
        ['Speakerboxxx',154,27],
        ['The Blueprint Clean Version', 156, 13]
      ]);

      var options = {
        title: 'Rating of the Top Music',
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