import logo from './logo.svg';
import './App.css';
import {Route, Routes} from 'react-router-dom';
import LandingPage from "./routes/LandingPage";
import Coins from "./routes/Coins";
import Details from "./routes/Details";
import Navbar from "./components/Navbar";
import React from "react";

function App() {
  return (
      <div className="App">
      <Navbar/>
      <Routes>
          <Route path="/" exact element={<LandingPage/>}/>
          <Route path="/coins" exact element={<Coins/>}/>
          <Route path="/coins/:id" element={<Details/>}/>
      </Routes>
      </div>
  );
}

export default App;
