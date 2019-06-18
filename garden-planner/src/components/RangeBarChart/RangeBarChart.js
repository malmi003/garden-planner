import React from "react";
import CanvasJSReact from "../PieChart/canvasjs/canvasjs.react";
const CanvasJS = CanvasJSReact.CanvasJS;
const CanvasJSChart = CanvasJSReact.CanvasJSChart;

CanvasJS.addColorSet("set1", 
[
    "#7bdcbf",
    "#99a1c3",
    "#c8a4b6",
]);

export default class RangeBarChart extends React.Component {
    constructor(props) {
        super(props)
    }
    componentDidMount = () => {
        this.chart.render();
    }

    getNumberOfWeek = function (date) {
        date = new Date(date);
        const firstDayOfYear = new Date(date.getFullYear(), 0, 1);
        const pastDaysOfYear = (date - firstDayOfYear) / 86400000;

        return Math.ceil((pastDaysOfYear + firstDayOfYear.getDay() + 1) / 7);
    }
    convertLabelToMonths = e => {
        const MONTHS = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];
        return MONTHS[e - 1];
    }
    convertWeekToMonth = w => {
        // console.log(w, Math.round(w) / 4)
        return Math.round(w - 4) / 4;
    }
    createDataPoints = () => {
        let dataPoints = [];
        if (this.props.plant && this.props.user) {
            let p = this.props.plant;
            let springFrost = this.getNumberOfWeek(this.props.user.hardinessZone_zipcode.lastSpringFrost);
            let fallFrost = this.getNumberOfWeek(this.props.user.hardinessZone_zipcode.firstFallFrost);

            let springDateO = (springFrost - p.startOutdoorsWeekCountS);
            let fallDateO = (fallFrost - p.startOutdoorsWeekCountF);
            dataPoints.push({ label: "Plant Outdoors", x: 2, y: [this.convertWeekToMonth(springDateO), this.convertWeekToMonth(fallDateO)] })

            let harvestDateS = (springFrost - p.firstHarvestDateAfterStartOutdoors);
            let harvestDateF = (fallFrost - p.lastHarvestDateBeforeFreeze);
            dataPoints.push({
                label: "Harvest", x: 1, y: [this.convertWeekToMonth(harvestDateS), this.convertWeekToMonth(harvestDateF)]
            })

            if (p.startIndoorsWeekCountS > 0) {
                let springDateI = (springFrost - p.startIndoorsWeekCountS);
                let fallDateI = springDateI - 1;
                if (p.startIndoorsWeekCountF > 0) {
                    fallDateI = (fallFrost - p.startIndoorsWeekCountF);
                }
                dataPoints.push({ label: "Plant Indoors", x: 3, y: [this.convertWeekToMonth(springDateI), this.convertWeekToMonth(fallDateI)] })
            } else if (p.startIndoorsWeekCountF > 0) {
                let fallDateI = (fallFrost - p.startIndoorsWeekCountF);
                let springDateI = (springFrost - p.startIndoorsWeekCountS);
                dataPoints.push({ label: "Plant Indoors", x: 3, y: [this.convertWeekToMonth(springDateI), this.convertWeekToMonth(fallDateI)] })
            }

        }
        return dataPoints;
    }

    render() {
        const p = this.props.plant;
        const options = {
            animationEnabled: true,
            theme: "dark2",
            title: {
                text: p.commonName,
                fontFamily: "'Roboto', sans-serif",
                // fontSize:"1.5rem"
            },
            axisY: {
                interval: 1,
                labelFormatter: e => this.convertLabelToMonths(e.value + 1),
                maximum: 11,
            },
            toolTip: {
                contentFormatter: e => `${e.entries[0].dataPoint.label}`
            },
            axisX: {
                lineThickness: 1,
            },
            height: 100,
            // colorSet: [
            //     "#c8a4b6",
            //     "#99a1c3",
            //     "#7bdcbf"
            // ],
            data: [{
                type: "rangeBar",
                dataPoints: this.createDataPoints()
            }]
        }

        return (
            <div>
                <CanvasJSChart options={options}
                    onRef={ref => this.chart = ref}
                />
            </div >
        );
    }
} 