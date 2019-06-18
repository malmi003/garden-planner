import React from "react";
import RGL, { WidthProvider } from "react-grid-layout";
const ReactGridLayout = WidthProvider(RGL);

export default class Garden extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
      gardenId: parseInt(window.location.href.split(":").pop()),

    }
  }
  assignClassName = (pgo) => {
    let cn = "";
    
    this.props.currentCompanions.forEach(c => {
      if (pgo.i === c.i) {
        cn = "highlightPlantGreen";
        return cn;
      }
    })
    this.props.currentIncompatables.forEach(c => {
      if (pgo.i === c.i) {
        cn = "highlightPlantRed";
        return cn;
      }
    })
    return cn;
  }

  render() {
    return (
      <div className="white-background">
        <div id="garden-container">
          <div id="garden-grid-overlay">
          <p className="has-text-white pl-1">each tile = one square foot of garden</p>
            {this.props.layouts ?
              <ReactGridLayout
                className="plant"
                layout={this.props.layouts}
                cols={48}
                rowHeight={12}
                compactType={null}
                preventCollision={true}
                onLayoutChange={layouts => this.props.handleUpdatePGO(layouts, this.state.gardenId)}
                onDragStart={(l, i) => {
                  this.props.getAllRelevantPlantFromPGOId(i.i)
                }}
                onDragStop={() => this.props.resetCompsIncomps()}
                onResizeStart={(l, i) => {
                  this.props.getAllRelevantPlantFromPGOId(i.i)
                }}
                onResizeStop={() => this.props.resetCompsIncomps()}
              >
              
                {
                  this.props.layouts.map(pgo => {
                    return (
                      <div key={pgo.i}
                        style={{
                          padding: "5px",
                          background: `url("/images/${pgo.icon}") round content-box, rgba(0,0,0,.1)`,
                          backgroundSize: pgo.minW*12*1.25,
                        }}
                        className={this.assignClassName(pgo)}
                      >
                        <button
                          className="delete is-small delete-btn"
                          onClick={() => {this.props.handleDeletePGO(pgo.id, this.state.gardenId)}}
                        ></button>
                        <div className="lightNumberIcon">
                          <div className="left-left-tooltip">
                            {pgo.plantCount}
                            <div className="tooltiptext">
                              plant count
                        </div>
                          </div>
                        </div>
                      </div>
                    )
                  })
                }

              </ReactGridLayout>
              :
              <div className="p-5 level-item">
                <span className="icon has-text-info is-large">
                  <i className="fas fa-spinner fa-pulse fa-3x"></i>
                </span>
              </div>
            }
          </div>
        </div>
      </div>
    )
  }


}