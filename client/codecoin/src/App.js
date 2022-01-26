import {Route, Routes} from 'react-router-dom';
import LandingPage from "./routes/LandingPage";
import Coins from "./routes/Coins";
import Details from "./routes/Details";
import Buy from "./routes/Buy";
import Sell from "./routes/Sell";
import Error from "./routes/Error";
import Portfolio from "./routes/Portfolio";
import Navbar from "./components/Navbar";
import Register from "./routes/Register";
import Login from "./routes/Login";
import React from "react";


function App() {
    sessionStorage.setItem("user-id", null);
  return (
      <div className="App">
      <Navbar/>
      <Routes>
          <Route path="/" exact element={<LandingPage/>}/>
          <Route path="/coins" exact element={<Coins/>}/>
          <Route path="/coins/:id" element={<Details/>}/>
          <Route path="/coins/:id/buy" element={<Buy/>}/>
          <Route path="/coins/:id/sell" element={<Sell/>}/>
          <Route path="/portfolio" element={<Portfolio/>}/>
          <Route path={"/register"} element={<Register/>}/>
          <Route path={"/login"} element={<Login/>}/>
          <Route path="*" element={<Error/>}/>
      </Routes>
      </div>
  );
}

export default App;
