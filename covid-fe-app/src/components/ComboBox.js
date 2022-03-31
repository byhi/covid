import { Box, InputLabel } from '@mui/material';
import React, { useEffect, useState }  from 'react';

import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

import {
    LineChart,
    Line,
    AreaChart,
    Area,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    BarChart, Bar,
    Legend
} from "recharts";
 

  export default function BasicSelect() {
    const [chartType, setChartType] = React.useState('');
    const [covidStatus, setCovidStatus] =  useState('');
    const [chart, setChart] = useState('');
    
 
    useEffect(() => {
      const apiUrl = `http://localhost:8081/api/covid/status`;
      fetch(apiUrl)
        .then((res) => res.json())
        .then((repos) => {
          setCovidStatus(repos);
        });
    }, [setCovidStatus]);


    const handleChange = (event) => {
      console.log(event.target.value,);
      console.log(chartType);
      setChartType(event.target.value);
      console.log(chartType);
      switch(event.target.value) {
        case "BarChart":
          return  setChart(getBarChart(covidStatus));
        case "LineChart":
          return  setChart(getLineChart(covidStatus));
        case "AreaChart":
          return  setChart(getAreaChart(covidStatus));
        default:
          return  setChart('');
      }
    };
  
    return (
        
        
      <Box sx={{ minWidth: 120 }}>
        <p>
            Please select a chart!
        </p>
        <FormControl fullWidth>
          <InputLabel id="demo-simple-select-label">Chart type</InputLabel>
          <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            value={chartType}
            label="Chart type"
            onChange={handleChange}
          >
            <MenuItem value={"BarChart"}>Bar chart</MenuItem>
            <MenuItem value={"LineChart"}>Line chart</MenuItem>
            <MenuItem value={"AreaChart"}>Area chart</MenuItem>
          </Select>
        </FormControl>
            {chart}
      </Box>
      
    );
  }

 function getBarChart(data) {
    return (
        <BarChart width={730}
        height={250} data={data}>
          <Bar dataKey="infected" fill="#8884d8" />
          <Bar dataKey="deceased" stroke="#82ca9d" />
          <Bar dataKey="recovered" stroke="#cab282" />
          <Bar dataKey="quarantined" stroke="#ca8282" />
          <Bar dataKey="tested" stroke="#a8ca82" />
        </BarChart>
      );
}

function getLineChart(data) {
    return (
        <LineChart
          width={730}
          height={250}
          data={data}
          margin={{ top: 10, right: 30, left: 0, bottom: 0 }}>
        >
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="lastUpdatedAtApify" />
          <YAxis />
          <Tooltip />
          <Legend />
          <Line
            type="monotone"
            dataKey="infected"
            stroke="#8884d8"
            activeDot={{ r: 8 }}
          />
          <Line type="monotone" dataKey="deceased" stroke="#82ca9d" />
          <Line type="monotone" dataKey="recovered" stroke="#cab282" />
          <Line type="monotone" dataKey="quarantined" stroke="#ca8282" />
          <Line type="monotone" dataKey="tested" stroke="#a8ca82" />
        </LineChart>
      );
}

function getAreaChart(data) {
    return (
        <AreaChart width={730} height={250} data={data}
        margin={{ top: 10, right: 30, left: 0, bottom: 0 }}>
        <defs>
            <linearGradient id="colorInf" x1="0" y1="0" x2="0" y2="1">
            <stop offset="5%" stopColor="#8884d8" stopOpacity={0.8}/>
            <stop offset="95%" stopColor="#8884d8" stopOpacity={0}/>
            </linearGradient>
            <linearGradient id="colorDec" x1="0" y1="0" x2="0" y2="1">
              <stop offset="5%" stopColor="#82ca9d" stopOpacity={0.8}/>
              <stop offset="95%" stopColor="#82ca9d" stopOpacity={0}/>
            </linearGradient>
            <linearGradient id="colorRec" x1="0" y1="0" x2="0" y2="1">
              <stop offset="5%" stopColor="#cab282" stopOpacity={0.8}/>
              <stop offset="95%" stopColor="#cab282" stopOpacity={0}/>
            </linearGradient>
            <linearGradient id="colorQua" x1="0" y1="0" x2="0" y2="1">
              <stop offset="5%" stopColor="#ca8282" stopOpacity={0.8}/>
              <stop offset="95%" stopColor="#ca8282" stopOpacity={0}/>
            </linearGradient>
            <linearGradient id="colorTes" x1="0" y1="0" x2="0" y2="1">
              <stop offset="5%" stopColor="#a8ca82" stopOpacity={0.8}/>
              <stop offset="95%" stopColor="#a8ca82" stopOpacity={0}/>
            </linearGradient>
        </defs>
        <XAxis dataKey="lastUpdatedAtApify" />
        <YAxis />
        <CartesianGrid strokeDasharray="3 3" />
        <Tooltip />
        <Area type="monotone" dataKey="infected" stroke="#8884d8" fillOpacity={1} fill="url(#colorInf)" />
        <Area type="monotone" dataKey="deceased" stroke="#82ca9d" fillOpacity={1} fill="url(#colorDec)" />
        <Area type="monotone" dataKey="recovered" stroke="#cab282" fillOpacity={1} fill="url(#colorRec)" />
        <Area type="monotone" dataKey="quarantined" stroke="#ca8282" fillOpacity={1} fill="url(#colorQua)" />
        <Area type="monotone" dataKey="tested" stroke="#a8ca82" fillOpacity={1} fill="url(#colorTes)" />
        </AreaChart>
      );
}