import React, {useState, useEffect} from 'react';
import logo from './logo.svg';
import './App.css';
import axios from "axios";

const UserProfiles() = {} => {
  const fetchUserProfiles () => {
    axios.get("http://localhost:8080/api/v1/user-profile").then(res => {
      console.log(res);
    });
  }
  useEffect
}

function App() {
  return (
    <div className="App"> </div>
  );
}

export default App;
