import React, { Component } from "react";
import "./App.css";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import AddProject from "./components/Project/AddProject";
import Dashboard from "./components/Dashboard";

class App extends Component {
  render() {
    return (
      <Router>
        <div className="App">
          <Header />

          <Routes>
            <Route exact path="/addProject" element={<AddProject />}></Route>
            <Route exact path="/dashboard" element={<Dashboard />}></Route>
          </Routes>
        </div>
      </Router>
    );
  }
}

export default App;
