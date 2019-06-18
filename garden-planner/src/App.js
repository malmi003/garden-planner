import React, { Component } from 'react';
import './App.css';
import NavBar from "./components/NavBar";
import DashBoard from "./pages/DashBoard";
import GardenDiagram from "./pages/GardenDiagram";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Login from "./pages/Login";
import SignUp from "./pages/SignUp";
import LandingPage from "./pages/LandingPage";
import { withFirebase } from './components/Firebase';
import Footer from "./components/Footer";
import GardenAPI from "./utils/GardenAPI";
import PlantAPI from "./utils/PlantAPI";
import UserAPI from "./utils/UserAPI";

const INITIAL_STATE = {
  authUser: null,
  gardens: [],
  layouts: null,
  plants: [],
  currentCompanions: [],
  currentIncompatables: [],
  currentPGO: null,
  yields: null,
  allMyPlants: [],
  user: null,
};

class App extends Component {
  constructor(props) {
    super(props);

    this.state = { ...INITIAL_STATE };
  }

  componentDidMount() {
    this.listener = this.props.firebase.auth.onAuthStateChanged(
      authUser => {
        this.setState({
          gardens: [],
          layouts: null,
          yields: null,
          allMyPlants: [],
          user: null,
        });
        authUser
          ? this.setState({ authUser })
          : this.setState({ authUser: null });
        this.handleGetGardens();
        this.handleGetUser();
        this.handleGetUserPlants();
      },
    );
    this.handleGetPlants();
  }

  componentWillUnmount() {
    this.listener();
  }
  handleGetGardens = () => {
    if (this.state.authUser) {
      GardenAPI.getGardensByUserId({
        id: this.state.authUser.uid
      }).then(data => {
        this.setState({
          gardens: data.data,
          yields: null
        })
        this.handleGetYields();
        this.handleGetUserPlants();
      }).catch(er => {
        console.log(er);
        this.setState({
          error: er
        })
      })
    }
  }

  handleCreateNewDiagram = () => {
    GardenAPI.addGarden({
      userIdString: this.state.authUser.uid,
      gridCount: 1,
      year: "2019-05-02"
    }).then(data => {
      this.handleGetGardens();
    }).catch(er => {
      console.log(er);
    })
  }
  handleUpdateGardenName = (gardenId, gardenName) => {
    GardenAPI.updateGarden({
      id: gardenId,
      name: gardenName ? gardenName : ""
    }).then(data => {
      this.handleGetGardens();
    }).catch(er => {
      console.log(er);
    })
  }
  handleDeleteGarden = id => {
    GardenAPI.deleteGarden({
      id: id
    }).then(data => {
      this.handleGetGardens();
    }).catch(er => {
      console.log(er);
      this.setState({
        error: er
      })
    })

  }
  handleGetPlants = () => {
    PlantAPI.getAllPlants()
      .then(data => {
        this.setState({
          plants: data.data,
        })
      }).catch(er => {
        console.log(er);
      })
  }
  resetCompsIncomps = () => {
    this.setState({
      currentCompanions: [],
      currentIncompatables: [],
      currentPGO: null,
    })
  }
  handleGetGardenLayout = gardenId => {
    GardenAPI.getPGOsByGardenId({
      id: gardenId
    }).then(data => {
      data.data.forEach(l => {
        l.i = l.i.toString();
        l.plantCount = Math.round((l.h / 4 * l.w / 4 * l.plantId.plantsPerSquareFoot));
      })
      this.setState({
        layouts: data.data,
      })
      this.resetCompsIncomps();
    }).catch(er => {
      console.log(er);
    })
  }
  handleCreateNewPGO = (plantId, icon, gardenId) => {
    PlantAPI.getPlantById({
      id: plantId
    }).then(data => {
      let ppsf = data.data.plantsPerSquareFoot;
      let minD;
      if (1 / ppsf < .5) {
        minD = 2;
      } else if (1 / ppsf === .5) {
        minD = 3;
      } else if (1 / ppsf <= 1) {
        minD = 4;
      } else if (ppsf === .75) {
        minD = 5;
      } else if (ppsf === .5) {
        minD = 6;
      }

      GardenAPI.createPGO({
        //   pgo data
        x: 0,
        y: 0,
        w: minD,
        h: minD,
        minW: minD,
        minH: minD,
        plantIdInt: plantId,
        gardenDiagramIdInt: gardenId,
        icon: icon
      }).then(data => {
        this.handleGetGardenLayout(gardenId);
      }).catch(er => {
        console.log(er);
      })
    }).catch(er => {
      console.log(er);
    })
  }
  handleUpdatePGO = (pgos, gardenId) => {
    pgos.forEach(pgo => {
      GardenAPI.updatePGO({
        id: pgo.i,
        x: pgo.x,
        y: pgo.y,
        w: pgo.w,
        h: pgo.h,
      }).then(data => {
        this.handleGetGardenLayout(gardenId);
      }).catch(er => {
        console.log(er);
      })
    })
  }
  handleDeletePGO = (pgoId, gardenId) => {
    GardenAPI.deletePGO({
      id: pgoId
    }).then(data => {
      this.handleGetGardenLayout(gardenId);
    }).catch(er => {
      console.log(er);
    })
  }
  getAllRelevantPlantFromPGOId = pgoId => {
    let companions = [];
    let incompatables = [];
    let allPgos = this.state.layouts;

    let currentCompanions = [];
    let currentIncompatables = [];

    GardenAPI.getPGO({
      id: pgoId
    }).then(pgoData => {
      // get companions
      PlantAPI.getCompanionPlants({
        id: pgoData.data.plantId.id
      }).then(compData => {
        companions = compData.data;

        PlantAPI.getIncompatablePlants({
          id: pgoData.data.plantId.id
        }).then(incompData => {
          incompatables = incompData.data;

          allPgos.forEach(pgo => {
            companions.forEach(p => {
              if (pgo.plantId.id === p.id) {
                currentCompanions.push(pgo);
              }
            })
            incompatables.forEach(p => {
              if (pgo.plantId.id === p.id) {
                currentIncompatables.push(pgo);
              }
            })
          })
        })
          .then(() => {
            this.setState({
              currentCompanions: currentCompanions,
              currentIncompatables: currentIncompatables,
            })
          })
      })
    })
      .catch(er => {
        console.log(er);
      })
  }
  handleGetYields = () => {
    let yields = {
      "green onion": 0,
      peas: 0,
      spinach: 0,
      cabbage: 0,
      cauliflower: 0,
      "radish/turnip": 0,
      beets: 0,
      potatoes: 0,
      broccoli: 0,
      "lettuce - leaf": 0,
      carrots: 0,
      chard: 0,
      "green beans - bush": 0,
      "sweet corn": 0,
      cucumbers: 0,
      "squash - summer": 0,
      melons: 0,
      peppers: 0,
      tomatoes: 0,
      okra: 0,
      pumpkins: 0
    }
    this.state.gardens.forEach(g => {
      GardenAPI.getPGOsByGardenId({
        id: g.id
      }).then(data => {
        data.data.forEach(pgo => {
          yields[pgo.plantId.commonName] += (Math.round(pgo.w / 4 * pgo.h / 4 * pgo.plantId.averageYield / 10 / 2) * 2)
        })
        this.setState({
          yields: yields
        })
      }).catch(er => {
        console.log(er);
      })
    })
  }
  handleGetUser = () => {
    if (this.state.authUser) {
      UserAPI.getUserById({
        id: this.state.authUser.uid
      }).then(data => {
        this.setState({
          user: data.data
        })
      }).catch(er => {
        console.log(er);
      })
    }
  }
  handleGetUserPlants = () => {
    if (this.state.authUser) {
      this.handleGetUser();
      PlantAPI.getPlantsByUserId({
        id: this.state.authUser.uid
      }).then(data => {
        this.setState({
          allMyPlants: data.data
        })
      }).catch(er => {
        console.log(er);
      })
    }
  }

  render() {
    return (
      <Router>
        <div>
          <NavBar
            authUser={this.state.authUser}
            handleGetYields={this.handleGetYields}
            handleGetUserPlants={this.handleGetUserPlants}
          />
          <Switch>
            <Route exact path="/" component={LandingPage} />
            <Route path="/login" component={Login} />
            <Route path="/signUp" component={SignUp} />
            <Route path="/dashboard"
              render={() =>
                <DashBoard
                  authUser={this.state.authUser}
                  gardens={this.state.gardens}
                  getLayouts={this.handleGetGardenLayout}
                  updateGarden={this.handleUpdateGardenName}
                  onDeleteGardenClick={this.handleDeleteGarden}
                  redirectTo={this.state.redirectTo}
                  handleCreateNewDiagram={this.handleCreateNewDiagram}
                  yields={this.state.yields}
                  allMyPlants={this.state.allMyPlants}
                  user={this.state.user}
                />}
            />
            <Route path="/gardendiagram:id"
              render={() =>
                <GardenDiagram
                  layouts={this.state.layouts}
                  handleGetPlants={this.handleGetPlants}
                  handleCreateNewPGO={this.handleCreateNewPGO}
                  handleDeletePGO={this.handleDeletePGO}
                  plants={this.state.plants}
                  handleUpdatePGO={this.handleUpdatePGO}
                  resetCompsIncomps={this.resetCompsIncomps}
                  currentCompanions={this.state.currentCompanions}
                  currentIncompatables={this.state.currentIncompatables}
                  currentPGO={this.state.currentPGO}
                  getAllRelevantPlantFromPGOId={this.getAllRelevantPlantFromPGOId}
                />
              } />
            <Route component={Login} />
          </Switch>
          <Footer />
        </div>
      </Router >
    );
  }
}
export default withFirebase(App);
