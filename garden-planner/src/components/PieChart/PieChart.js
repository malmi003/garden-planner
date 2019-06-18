import React from "react";
import CanvasJSReact from './canvasjs/canvasjs.react';
const CanvasJSChart = CanvasJSReact.CanvasJSChart;

export default class PieChart extends React.Component {
    constructor(props) {
        super(props)
      }
      componentDidMount = () => {
        this.chart.render();
      }


    createDataPoints = () =>{
        let dataPoints =[];
        if (this.props.yields) {
            Object.keys(this.props.yields).forEach(y=>{
                if(this.props.yields[y] > 0) {
                    dataPoints.push( { y: this.props.yields[y], label: y },)
                }
            })
        }
        return dataPoints;
    }

    render() {
        const options = {
            theme: "dark2",
            animationEnabled: true,
            exportFileName: "name",
            exportEnabled: true,
            title: {
                text: this.props.yields? "My Plant Mix at Harvest":"No plants in your mix yet",
                fontFamily: "'Roboto', sans-serif",
                padding: "10px",
            },
            data: [{
                type: "pie",
                showInLegend: true,
                legendText: "{label}",
                toolTipContent: "{label}: {y} lbs",
                indexLabel: "{y} lbs",
                indexLabelPlacement: "inside",
                dataPoints: this.createDataPoints()
            }],
        }
        return (
            <div>
                <CanvasJSChart options={options}
                    onRef={ref => this.chart = ref}
                />
            </div>
        );
    }
} 