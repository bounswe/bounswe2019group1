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
import {getTEValue} from "../../service/equipment.service";

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
        includeZero: false
    },
    data: [
        {
            type: "area",
            xValueFormatString: "YYYY",
            yValueFormatString: "#,##0.## TRY",
            dataPoints: [
                {x: new Date(2019, 11), y: 5.91},
                {x: new Date(2019, 10), y: 5.82},
                {x: new Date(2019, 9), y: 5.75},
                {x: new Date(2019, 8), y: 5.7},
                {x: new Date(2019, 7), y: 5.55},
                {x: new Date(2019, 6), y: 5.42},
                {x: new Date(2019, 5), y: 5.36}
            ]
        }
    ]
};

export default function Equipment(props) {
    const classes = useStyles();
    var equipment_name = String(props.history.location.pathname);
    const [equipmentValue, setEquipmentValues] = useState({
        te_value: 0.0,
        changed_date: ""
    });
    equipment_name = String(equipment_name.substr(equipment_name.lastIndexOf("/") + 1));

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
        temp_amount: ""
    });
    const handleChange = prop => event => {
      setValues({ ...values, [prop]: event.target.value });
    };

  const buy = prop => event => {
    event.preventDefault();
    //setValues({ ...values, [prop]: "Satın alındı !" });
  };
  const upPredict = prop => event => {
    event.preventDefault();
    setValues({...values, [prop]: "It will increase !"});
};
const downPredict = prop => event => {
  event.preventDefault();
  setValues({...values, [prop]: "It will decrease !"});
};

  return (
    <div>
      <Header
        color="transparent"
        brand="Khaji-it Traders Platform"
        rightLinks={<PheaderLinks />}
        fixed
        changeColorOnScroll={{
          height: 200,
          color: "white"
        }}
      />
      <Parallax small filter image={require("assets/img/dollar-hd.jpg")} />
      <GridContainer>
        <GridItem xs={12} sm={12} md={4}>
          <Card chart>
            <CardHeader color="success">
              <CanvasJSChart options={options} />
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
                                    value={values.temp_amount}
                                    onChange={handleChange("temp_amount")}
                                    startAdornment={
                                      <InputAdornment position="start">$</InputAdornment>
                                    }
                                    labelWidth={60}
                                  />
                                </FormControl>
                                   <p align="center">
                                   <Button margin-left="30px" color="transparent" onClick={buy("state")}>
                                      <span className={classes.buyText}>
                                        <ShoppingCart className={classes.upArrowCardCategory} />{" "}
                                        Purchase
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
                  {equipmentValue.te_value}
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
