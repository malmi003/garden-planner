import React from "react";

class PGOBar extends React.Component {
    constructor(props) {
        super(props)

        this.state = {
            gardenId: parseInt(window.location.href.split(":").pop())
        }
    }

    componentDidMount = () => {
    }

    render() {
        return (
            <div className="">
                <div className="scrolling-wrapper">
                    {this.props.plants.map(p => {
                        return (
                            <PGOIcon
                                imgPath={`/images/${p.icon}`}
                                id={p.id}
                                imgName={p.commonName}
                                onClick={() =>
                                    this.props.handleCreateNewPGO(p.id, p.icon, this.state.gardenId)}
                                plantsPerFt={p.plantsPerSquareFoot}
                                layouts={this.props.layouts}
                                key={p.id}
                            />
                        )
                    })}
                </div>
            </div>
        )
    }
}
export default PGOBar


class PGOIcon extends React.Component {
    constructor(props) {
        super(props)
    }

    calcPlantCount = id => {
        let count = 0;
        if (this.props.layouts) {
            this.props.layouts.forEach(pgo => {
                if (pgo.plantId.id === this.props.id) {
                    count += pgo.plantCount
                }
            })
        }
        return count;
    }

    render() {
        return (
            <figure className="image is-64x64 p-2 br-2 plantIcon" id={this.props.id} key={this.props.id}>
                <img
                    src={this.props.imgPath}
                    alt={this.props.imgName}
                    onClick={this.props.onClick}
                />
                <div className="plantCountIcon">
                    <div className="left-left-tooltip">
                        {this.calcPlantCount(this.props.id)}
                        <div className="tooltiptext">
                            total plants in garden
                        </div>
                    </div>
                </div>
                <div className="numberIcon">
                    <div className="left-left-tooltip">
                        {this.props.plantsPerFt}
                        <div className="tooltiptext">
                            plants per ft<sup>2</sup>
                        </div>
                    </div>
                </div>
                <div className="info-icon">
                    <div className="icon has-text-info right-tooltip">
                        <i className="fas fa-info-circle"></i>
                        <div className="tooltiptext">
                            {this.props.imgName}
                        </div>
                    </div>
                </div>
            </figure>
        )
    }
}

export { PGOIcon };