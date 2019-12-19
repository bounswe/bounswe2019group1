import React from "react";
// react plugin for creating charts
import ChartistGraph from "react-chartist";
import CanvasJSReact from '../../assets/canvasjs.react';


// @material-ui/core
import { makeStyles } from "@material-ui/core/styles";
import Icon from "@material-ui/core/Icon";
// @material-ui/icons
import LocalOffer from "@material-ui/icons/LocalOffer";
import ArrowUpward from "@material-ui/icons/ArrowUpward";
import ArrowDownward from "@material-ui/icons/ArrowDownward";
import AccessTime from "@material-ui/icons/AccessTime";
import Button from "components/CustomButtons/Button.js";

// core components
import GridItem from "components/Grid/GridItem.js";
import GridContainer from "components/Grid/GridContainer.js";
import Card from "components/Card2/Card";
import CardHeader from "components/Card2/CardHeader.js";
import CardIcon from "components/Card2/CardIcon.js";
import CardBody from "components/Card2/CardBody.js";
import CardFooter from "components/Card2/CardFooter.js";


import styles from "assets/jss/material-dashboard-react/views/dashboardStyle.js";
var CanvasJSChart = CanvasJSReact.CanvasJSChart;
const useStyles = makeStyles(styles);
const options = {
  theme: "light2",
  animationEnabled: true,
  exportEnabled: true,
  title: {
    text: "USD / TRY"
  },
  axisY: {
    title: "USD / TRY",
    includeZero: false,
  },
  data: [
  {
    type: "area",
    xValueFormatString: "YYYY",
    yValueFormatString: "#,##0.## TRY",
    dataPoints: [
      { x: new Date(2019, 11), y: 5.91},
      { x: new Date(2019, 10), y: 5.82},
      { x: new Date(2019, 9), y: 5.75},
      { x: new Date(2019, 8), y: 5.70},
      { x: new Date(2019, 7), y: 5.55},
      { x: new Date(2019, 6), y: 5.42},
      { x: new Date(2019, 5), y: 5.36}
    ]
  }
  ]
}

export default function Equipment() {
  const classes = useStyles();
  return (
    <div>
    <GridContainer>
        
      
        <GridItem xs={12} sm={12} md={4}>
          <Card chart>
            <CardHeader color="success">
              
              <CanvasJSChart options = {options} 
				
		        	/>
            </CardHeader>
            <CardBody>
              <h4 className={classes.cardTitle}>Your Prediction</h4>
              <p className={classes.cardCategory}>
                <Button color="transparent">
                <span className={classes.successText}>
                  <ArrowUpward className={classes.upArrowCardCategory} /> Up
                </span>
                </Button>
                <Button color="transparent">
                <span className={classes.failText}>
                  <ArrowDownward className={classes.upArrowCardCategory} /> Down
                </span>

                </Button>
                {" "}
                
              </p>
            </CardBody>
            <CardFooter chart>
              <div className={classes.stats}>
                <AccessTime /> updated 4 minutes ago
              </div>
            </CardFooter>
          </Card>
        </GridItem>


        {/* <GridItem xs={12} sm={12} md={4}>
          <Card chart>
            <CardHeader color="warning">
            <CanvasJSChart options = {options} 
				
		        	/>
            </CardHeader>
            <CardBody>
              <h4 className={classes.cardTitle}>Email Subscriptions</h4>
              <p className={classes.cardCategory}>Last Campaign Performance</p>
            </CardBody>
            <CardFooter chart>
              <div className={classes.stats}>
                <AccessTime /> campaign sent 2 days ago
              </div>
            </CardFooter>
          </Card>
        </GridItem>
        <GridItem xs={12} sm={12} md={4}>
          <Card chart>
            <CardHeader color="danger">
            <CanvasJSChart options = {options} 
				
                />
            </CardHeader>
            <CardBody>
              <h4 className={classes.cardTitle}>Completed Tasks</h4>
              <p className={classes.cardCategory}>Last Campaign Performance</p>
            </CardBody>
            <CardFooter chart>
              <div className={classes.stats}>
                <AccessTime /> campaign sent 2 days ago
              </div>
            </CardFooter>
          </Card>
        </GridItem> */}
      
      </GridContainer>
      
    </div>
  );
}
