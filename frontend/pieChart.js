function drawPieChart(width, height, margin) {
    // The radius of the pieplot is half the width or half the height (smallest one). I subtract a bit of margin.
    var radius = Math.min(width, height) / 2 - margin;

    // append the svg object to the div called 'my_dataviz'
    var svg = d3.select("#my_dataviz")
        .append("svg")
        .attr("width", width)
        .attr("height", height)
        .append("g")
        .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

    var mydata = {};
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/students',
        contentType: "application/json",
        dataType: 'json',
        async: false,
        success: function(data) {
            $.each(data, function(key, value) {
                console.log(value.firstName, value.nationality);
                var national = value.nationality.toLowerCase()
                if (national in mydata){
                    mydata[national]++
                }
                else{
                    mydata[national] = 1
                }
            });
        }
    });
    console.log(mydata);


    // set the color scale
    var color = d3.scaleOrdinal()
        .domain(mydata)
        .range(d3.schemeSet2);

    // Compute the position of each group on the pie:
    var pie = d3.pie()
        .value(function(d) {return d.value; });
    var data_ready = pie(d3.entries(mydata));
    // Now I know that group A goes from 0 degrees to x degrees and so on.

    // shape helper to build arcs:
    var arcGenerator = d3.arc()
        .innerRadius(100)
        .outerRadius(radius);

    // Build the pie chart: Basically, each part of the pie is a path that we build using the arc function.
    svg
        .selectAll('mySlices')
        .data(data_ready)
        .enter()
        .append('path')
        .attr('d', arcGenerator)
        .attr('fill', function(d){ return(color(d.data.key)) })
        .attr("stroke", "black")
        .style("stroke-width", "2px")
        .style("opacity", 0.7);

    // Now add the annotation. Use the centroid method to get the best coordinates
    svg
        .selectAll('mySlices')
        .data(data_ready)
        .enter()
        .append('text')
        .text(function(d){ return d.data.key})
        .attr("transform", function(d) { return "translate(" + arcGenerator.centroid(d) + ")";  })
        .style("text-anchor", "middle")
        .style("font-size", 17);

    console.log("Successfully rendered pie chart");
}