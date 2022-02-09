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
import {useEffect, useState} from "react";
import {UserContext} from "./components/UserContext"


function App() {
    let [isLoggedIn, setIsLoggedIn] = useState(null);

    useEffect(() => {
        setIsLoggedIn(userLoggedIn());
    }, [])

    function userLoggedIn() {
        return sessionStorage?.getItem("user-id") !== null;
    }


    return (
      <div className="App" id="outer-container">
          <div id="outer-container">
          <UserContext.Provider value={[isLoggedIn, setIsLoggedIn]}>
      <Navbar/>
      <Routes>
          <Route path="/" exact element={<LandingPage/>}/>
          <Route path="/coins" exact element={<Coins/>}/>
          <Route path="/coins/:id" element={<Details/>}/>
          <Route path="/coins/:id/buy" element={<Buy/>}/>
          <Route path="/coins/:id/sell" element={<Sell/>}/>
          <Route path="/portfolio" element={<Portfolio/>}/>
          <Route path={"/register"} element={<Register/>}/>
          <Route path={"/login"} element={<Login />}/>
          <Route path="*" element={<Error/>}/>
      </Routes>
          </UserContext.Provider>
          </div>
      </div>
  );
}

export default App;
