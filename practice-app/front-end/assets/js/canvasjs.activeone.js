window.onload = function () {

var chart = new CanvasJS.Chart("chartContainer", {
	exportEnabled: false,
	animationEnabled: true,
	legend:{
		cursor: "pointer",
		itemclick: explodePie
	},
	data: [{
		type: "pie",
		showInLegend: true,
		toolTipContent: "{name}: <strong>{y}%</strong>",
		indexLabel: "{name} - {y}%",
		dataPoints: [
			{ y: 26, name: "Bitcoin", exploded: true },
			{ y: 20, name: "Bitfinex" },
			{ y: 15, name: "Degitalcash" },
			{ y: 10, name: "Stellar" },
			{ y: 7, name: "Ethereum" },
			{ y: 20, name: "Ripple" },
		]
	}]
});

chart.render();
}


function explodePie (e) {
	if(typeof (e.dataSeries.dataPoints[e.dataPointIndex].exploded) === "undefined" || !e.dataSeries.dataPoints[e.dataPointIndex].exploded) {
		e.dataSeries.dataPoints[e.dataPointIndex].exploded = true;
	} else {
		e.dataSeries.dataPoints[e.dataPointIndex].exploded = false;
	}
	e.chart.render();

}