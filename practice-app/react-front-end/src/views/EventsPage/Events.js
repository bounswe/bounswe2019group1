import React from "react";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
// core components
import Header from "components/Header/Header.js";
import PheaderLinks from "components/ProfileHeader/PheaderLinks";
import Parallax from "components/Parallax/Parallax.js";
import classNames from "classnames";
import GridItem from "components/Grid/GridItem.js";
import GridContainer from "components/Grid/GridContainer.js";
import Table from "components/Table/Table.js";
import Card from "components/Card/Card.js";
import CardHeader from "components/Card/CardHeader.js";
import CardBody from "components/Card/CardBody.js";
import FormLabel from "@material-ui/core/FormLabel";
import FormControl from "@material-ui/core/FormControl";
import FormGroup from "@material-ui/core/FormGroup";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import FormHelperText from "@material-ui/core/FormHelperText";
import Checkbox from "@material-ui/core/Checkbox";
import Button from "@material-ui/core/Button";
//import { AnnotationCallout } from "react-annotation";

const styles = {
  cardCategoryWhite: {
    "&,& a,& a:hover,& a:focus": {
      color: "rgba(255,255,255,.62)",
      margin: "0",
      fontSize: "14px",
      marginTop: "0",
      marginBottom: "0"
    },
    "& a,& a:hover,& a:focus": {
      color: "#FFFFFF"
    }
  },
  formControl: {
    minWidth: 120
  },
  selectEmpty: {},
  cardTitleWhite: {
    color: "#FFFFFF",
    marginTop: "0px",
    minHeight: "auto",
    fontWeight: "300",
    fontFamily: "'Roboto', 'Helvetica', 'Arial', sans-serif",
    marginBottom: "3px",
    textDecoration: "none",
    "& small": {
      color: "#777",
      fontSize: "65%",
      fontWeight: "400",
      lineHeight: "1"
    }
  }
};

const useStyles = makeStyles(styles);

export default function TableList() {
  const classes = useStyles();

  const [state, setState] = React.useState({
    TRY_EUR: true,
    USD_JPY: true,
    GBP_TRY: true,
    EUR_USD: true,
    USD_TRY: true,
    EUR_TRY: true,
    GBP_USD: true,
    BTC_TRY: true,
    XMR_USD: true,
    GOOGL_USD: true,
    AGTHX_USD: true
  });

  const [list, setListState] = React.useState([
    ["Private Sector Credit m/m", "AUD", "2019-12-22T19:30:00-05:00" , "Low", "0.2%" , "0.1%"],
    [
           "All Industries Activity m/m",
           "JPY",
           "2019-12-22T23:30:00-05:00",
           "Low",
           "-4.3%",
           "1.5%"
       ],
       [
          "German Import Prices m/m",
          "EUR",
          "2019-12-23T01:48:00-05:00",
          "Low",
          "0.1%",
          "-0.1%"
      ],
      [   "Durable Goods Orders m/m",
          "USD",
          "2019-12-23T08:30:00-05:00",
          "Medium",
          "0.2%",
          "0.6%"
      ],
      [
            "Core Durable Goods Orders m/m",
            "USD",
            "2019-12-23T08:30:00-05:00",
            "Medium",
            "1.5%",
            "0.6%"
        ],
        [
            "GDP m/m",
            "CAD",
            "2019-12-23T08:30:00-05:00",
            "High",
            "0.1%",
            "0.1%"
        ],
        [
            "New Home Sales",
            "USD",
            "2019-12-23T10:00:00-05:00",
            "Low",
            "730K",
            "733K"
        ],
        [
          "Treasury Currency Report",
          "USD",
          "2019-12-23T17:03:00-05:00",
          "Medium",
          "",
          ""
      ],
      [
          "Monetary Policy Meeting Minutes",
          "JPY",
          "2019-12-23T18:50:00-05:00",
          "Low",
          "",
          ""
      ],
      [
        "BOJ Core CPI y/y",
        "JPY",
        "2019-12-24T00:00:00-05:00",
        "Low",
        "0.3%",
        "0.3%"
    ],
    [
        "German Bank Holiday",
        "EUR",
        "2019-12-24T02:02:00-05:00",
        "Holiday",
        "",
        ""
    ],
    [
      "Richmond Manufacturing Index",
      "USD",
      "2019-12-24T10:00:00-05:00",
      "Low",
      "1",
      "-1"
       ],
       [
      "Bank Holiday",
      "NZD",
      "2019-12-24T15:00:00-05:00",
      "Holiday",
      "",
      ""
       ],
     [
      "Bank Holiday",
      "AUD",
      "2019-12-24T16:00:00-05:00",
      "Holiday",
      "",
      ""
       ],
       [
        "SPPI y/y",
        "JPY",
        "2019-12-24T18:50:00-05:00",
        "Low",
        "2.1%",
        "2.1%"
      ],
      [
        "Bank Holiday",
        "CHF",
        "2019-12-25T02:00:00-05:00",
        "Holiday",
        "",
        ""
      ],
      [
      "French Bank Holiday",
      "EUR",
      "2019-12-25T02:01:00-05:00",
      "Holiday",
      "",
      ""
       ],
       [
      "German Bank Holiday",
      "EUR",
      "2019-12-25T02:02:00-05:00",
      "Holiday",
      "",
      ""
       ],
       [
      "Italian Bank Holiday",
      "EUR",
      "2019-12-25T02:03:00-05:00",
      "Holiday",
      "",
      ""
       ],
     [
       "Bank Holiday",
      "GBP",
      "2019-12-25T03:00:00-05:00",
      "Holiday",
      "",
      ""
     ],
     [
      "Bank Holiday",
      "CAD",
      "2019-12-25T07:59:00-05:00",
      "Holiday",
      "",
      ""
     ],
     [
      "Bank Holiday",
      "USD",
      "2019-12-25T08:00:00-05:00",
      "Holiday",
      "",
      ""
     ],
     [
      "Bank Holiday",
      "NZD",
      "2019-12-25T15:00:00-05:00",
      "Holiday",
      "",
      ""
      ],
      [
      "Bank Holiday",
      "AUD",
      "2019-12-25T16:00:00-05:00",
      "Holiday",
      "",
      ""
      ],
      [
      "Housing Starts y/y",
      "JPY",
      "2019-12-26T00:00:00-05:00",
      "Low",
      "-8.1%",
      "-7.4%"
      ],
      [
      "BOJ Gov Kuroda Speaks",
      "JPY",
      "2019-12-26T00:30:00-05:00",
      "Low",
      "",
      ""
  ],
   [
      "Bank Holiday",
      "CHF",
      "2019-12-26T02:00:00-05:00",
      "Holiday",
      "",
      ""
     ],
     [
      "German Bank Holiday",
      "EUR",
      "2019-12-26T02:02:00-05:00",
      "Holiday",
      "",
      ""
     ],
     [
      "Italian Bank Holiday",
      "EUR",
      "2019-12-26T02:03:00-05:00",
      "Holiday",
      "",
      ""
    ],
    [
      "Bank Holiday",
      "GBP",
      "2019-12-26T03:00:00-05:00",
      "Holiday",
      "",
      ""
    ],
    [
      "Bank Holiday",
      "CAD",
      "2019-12-26T07:59:00-05:00",
      "Holiday",
      "",
      ""
    ],
  ]);

  const handleChange = name => event => {
    setState({ ...state, [name]: event.target.checked });
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
      <div className={classNames(classes.main, classes.mainRaised)}>
        <div>
          <div className={classes.container}>
            <GridContainer justify="center">
              <GridItem xs={12} sm={12} md={20}>
                <div className={classes.root}>
                  <GridContainer>
                    <GridItem item xs={120} sm container>
                      <Card>
                        <CardHeader color="primary">
                          <h4 className={classes.cardTitleWhite}>Market</h4>
                          <p className={classes.cardCategoryWhite}>
                            Equipments that is listed
                          </p>
                        </CardHeader>
                        <CardBody>
                          <Table
                            tableHeaderColor="primary"
                            tableHead={[
                              "Title",
                              "Country",
                              "Parity",
                              "Impact",
                              "Forecast",
                              "Previous"
                            ]}
                            tableData={list}
                          />
                        </CardBody>
                      </Card>
                    </GridItem>
                    <GridItem xs={12} sm={12} md={12}>
                      <Card plain>

                        <CardBody>
                        </CardBody>
                      </Card>
                    </GridItem>
                  </GridContainer>
                </div>
              </GridItem>
            </GridContainer>
          </div>
        </div>
      </div>
    </div>
  );
}
