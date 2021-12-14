import logo from './logo.svg';
import './App.css';
import {Route, Routes} from 'react-router-dom';
import LandingPage from "./routes/LandingPage";
import Navbar from "./components/Navbar";
import Coins from "./routes/Coins";
import React from "react";

function App() {
  return (
      <div className="App">
      <Navbar/>
      <Routes>
          <Route path="/" exact element={<LandingPage/>}/>
          <Route path="/coins" element={<Coins/>}/>
      </Routes>
      </div>
  );
}

export default App;
