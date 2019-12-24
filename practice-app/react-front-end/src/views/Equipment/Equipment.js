import React, {useState} from "react";
// react plugin for creating charts
import ChartistGraph from "react-chartist";
import CanvasJSReact from "../../assets/canvasjs.react";

// @material-ui/core
import {makeStyles} from "@material-ui/core/styles";
import Icon from "@material-ui/core/Icon";
// @material-ui/icons
import LocalOffer from "@material-ui/icons/LocalOffer";
import ArrowUpward from "@material-ui/icons/ArrowUpward";
import ArrowDownward from "@material-ui/icons/ArrowDownward";
import AccessTime from "@material-ui/icons/AccessTime";
import Button from "components/CustomButtons/Button.js";
import Parallax from "components/Parallax/Parallax.js";
import Header from "components/Header/Header.js";
import PheaderLinks from "components/ProfileHeader/PheaderLinks";
import ShoppingCart from "@material-ui/icons/ShoppingCart";
import MonetizationOn from "@material-ui/icons/MonetizationOn";

// core components
import GridItem from "components/Grid/GridItem.js";
import GridContainer from "components/Grid/GridContainer.js";
import Card from "components/Card2/Card";
import CardHeader from "components/Card2/CardHeader.js";
import CardIcon from "components/Card2/CardIcon.js";
import CardBody from "components/Card2/CardBody.js";
import CardFooter from "components/Card2/CardFooter.js";
import InputLabel from "@material-ui/core/InputLabel";
import InputAdornment from "@material-ui/core/InputAdornment";
import OutlinedInput from "@material-ui/core/OutlinedInput";
import FormControl from "@material-ui/core/FormControl";

import styles from "assets/jss/material-dashboard-react/views/dashboardStyle.js";
import {getTEHistory, getTEValue} from "../../service/equipment.service";
import {buyEquipment, sellEquipment} from "../../service/wallet.service";
import swal from "sweetalert";

var CanvasJSChart = CanvasJSReact.CanvasJSChart;
const useStyles = makeStyles(styles);


export default function Equipment(props) {
    const classes = useStyles();
    var equipment_name = String(props.history.location.pathname);
    const [equipmentValue, setEquipmentValues] = useState({
        te_value: 0.0,
        changed_date: ""
    });
    const [equipmentHistory, setEquipmentHistory] = useState(
        {
            x: [],
            y: []
        }
    )


    equipment_name = String(equipment_name.substr(equipment_name.lastIndexOf("/") + 1)).toUpperCase();
  
    
console.log(equipmentValue.te_value)

    
  
    useState(() => {
        getTEHistory(equipment_name).then(res =>
            setEquipmentHistory({
                x: res.dates,
                y: res.values
               
            })
            
        );
    });


    useState(() => {
        getTEValue(equipment_name).then(res =>
            setEquipmentValues({
                te_value: res.te_value,
                changed_date: res.changed_date
            })
        );
    });
    const [values, setValues] = React.useState({
        state: "Nothing",
        sell_amount: "",
        buy_amount: "",
        temp_amount: ""
    });
    const handleChange = prop => event => {
        setValues({...values, [prop]: event.target.value});
    };

    const handlePurchase = prop => event => {
        event.preventDefault();
        buyEquipment({
            name: equipment_name,
            amount: values.buy_amount
        }).then(res => (res.status === 200 ? res : null))
            .then(() => {
                swal("Good job!", "The trading equipment is successfully bought by using USD");
            })
            .catch(error => {
                swal("Oops: ", error.message, "error");
            });
    };

    const handleSell = prop => event  => {
        event.preventDefault();
        sellEquipment({
            name: equipment_name,
            amount: values.sell_amount
        }).then(res => (res.status === 200 ? res : null))
            .then(() => {
                swal("Good job!", "The trading equipment is successfully sold.");
            })
            .catch(error => {
                swal("Oops: ", error.message, "error");
            });
    };

    const upPredict = prop => event => {
        event.preventDefault();
        setValues({...values, [prop]: "It will increase !"});
    };
    const downPredict = prop => event => {
        event.preventDefault();
        setValues({...values, [prop]: "It will decrease !"});
    };

    const [options, setOptions] = useState({
        
        theme: "light2",
        animationEnabled: true,
        exportEnabled: true,
        title: {
            text: equipment_name +"/USD"
        },
        axisY: {
            title: equipment_name +"/USD",
            includeZero: false
        },
        data: [
            {
                type: "area",
                xValueFormatString: "YYYY,MM,DD",
                yValueFormatString: "#,##0.####" + "USD",
                //last fifteen days
                dataPoints: [
                    {x: new Date(2019, 11,24), y: 0.1680}, 
                    {x: new Date(2019, 11,23), y: 0.1685}, 
                    {x: new Date(2019, 11,22), y: 0.1684}, 
                    {x: new Date(2019, 11,21), y: 0.1684}, 
                    {x: new Date(2019, 11,20), y: 0.1685}, 
                    {x: new Date(2019, 11,19), y: 0.1683}, 
                    {x: new Date(2019, 11,18), y: 0.1698}, 
                    {x: new Date(2019, 11,17), y: 0.1709}, 
                    {x: new Date(2019, 11,16), y: 0.1711}, 
                    {x: new Date(2019, 11,15), y: 0.1717}, 
                    {x: new Date(2019, 11,14), y: 0.1721}, 
                    {x: new Date(2019, 11,13), y: 0.1729}, 
                    {x: new Date(2019, 11,12), y: 0.1722}, 
                    {x: new Date(2019, 11,11), y: 0.1721}, 
                    {x: new Date(2019, 11,10), y: 0.1723},
                    {x: new Date(2019, 11,9), y: 0.1730}, 
                    {x: new Date(2019, 11,8), y: 0.1731}, 
                    {x: new Date(2019, 11,7), y: 0.1730},  

                     
                ]
            }
        ]
    });

    return (
        <div>
            <Header
                color="transparent"
                brand="Khaji-it Traders Platform"
                rightLinks={<PheaderLinks/>}
                fixed
                changeColorOnScroll={{
                    height: 200,
                    color: "white"
                }}
            />
            <Parallax small filter image={require("assets/img/dollar-hd.jpg")}/>
            <GridContainer>
                <GridItem xs={12} sm={12} md={4}>
                    <Card chart>
                        <CardHeader color="success">
                            <CanvasJSChart options={options}/>
                        </CardHeader>
                        <CardBody>
                            <h4 className={classes.cardTitle}>
                                Your Prediction: {values.state}
                            </h4>
                            <p className={classes.cardCategory}>

                                <Button color="transparent" onClick={upPredict("state")}>
                  <span className={classes.successText}>
                    <ArrowUpward className={classes.upArrowCardCategory}/> Up
                  </span>
                                </Button>
                                <Button color="transparent" onClick={downPredict("state")}>
                  <span className={classes.failText}>
                    <ArrowDownward className={classes.upArrowCardCategory}/>{" "}
                      Down
                  </span>
                                </Button>
                                {""}

                                <FormControl
                                    fullWidth
                                    className={classes.margin}
                                    variant="outlined"
                                >
                                    <InputLabel htmlFor="outlined-adornment-amount">
                                        Amount
                                    </InputLabel>
                                    <OutlinedInput
                                        id="outlined-adornment-amount"
                                        value={values.buy_amount}
                                        onChange={handleChange("buy_amount")}
                                        startAdornment={
                                            <InputAdornment position="start">$</InputAdornment>
                                        }
                                        labelWidth={60}
                                    />
                                </FormControl>
                                <p align="center">
                                    <Button margin-left="30px" color="transparent" onClick={handlePurchase()}>
                                      <span className={classes.buyText}>
                                        <ShoppingCart className={classes.upArrowCardCategory}/>{" "}
                                          Purchase
                                      </span>
                                    </Button>
                                </p>

                                <FormControl
                                    fullWidth
                                    className={classes.margin}
                                    variant="outlined"
                                >
                                    <InputLabel htmlFor="outlined-adornment-amount">
                                        Amount
                                    </InputLabel>
                                    <OutlinedInput
                                        id="outlined-adornment-amount"
                                        value={values.sell_amount}
                                        onChange={handleChange("sell_amount")}
                                        startAdornment={
                                            <InputAdornment position="start">$</InputAdornment>
                                        }
                                        labelWidth={60}
                                    />
                                </FormControl>
                                <p align="center">
                                    <Button margin-left="30px" color="transparent" onClick={handleSell()}>
                                      <span className={classes.buyText}>
                                        <MonetizationOn className={classes.upArrowCardCategory}/>{" "}
                                          Sell
                                      </span>
                                    </Button>
                                </p>
                            </p>
                        </CardBody>
                        <CardFooter chart>
                            <div className={classes.stats}>
                                <AccessTime/> updated 4 minutes ago
                            </div>
                        </CardFooter>
                    </Card>
                </GridItem>
                <GridItem>
                    {/*<h3>{equipmentValue.te_value}</h3>*/}
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
