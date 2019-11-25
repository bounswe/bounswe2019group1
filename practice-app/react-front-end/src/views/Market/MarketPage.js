import React, { useState } from "react";
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

export default function MarketPage(props) {
  const classes = useStyles();

  const [state, setState] = React.useState({
    TRY_EUR: true,
    USD_JPY: true,
    GBP_TRY: true,
    EUR_USD: true,
    USD_TRY: true,
    EUR_TRY: true,
    GBP_USD: false,
    BTC_TRY: false,
    XMR_USD: false,
    GOOGL_USD: false,
    AGTHX_USD: false
  });

  const [currency, setCurrency] = useState({
    EUR: 0.1574,
    GBP: 0.2133,
    TRY: 7.4
  });
  const [cryptoCurrency, setCryptoCurrency] = useState({
    BTC: 1.1074,
    ETH: 5.7182,
    LTC: 6.3329
  });
  const [stock, setStock] = useState({
    GOOGL: "1308.0200000000",
    AAPL: "265.2150000000",
    GM: "35.7250000000"
  });
  const [tradeIndices, setTradeIndices] = useState({
    DJI: "28024.8000000000",
    IXIC: "7963.0000000000",
    INX: "3131.0900000000"
  });
  const [etfs, setETFs] = useState({
    SPY: {
      id: 1,
      price: "$310.96",
      assets: "$281,588.21"
    },
    IVV: {
      id: 2,
      price: "$312.74",
      assets: "$195,496.89"
    },
    VTI: {
      id: 3,
      price: "$158.14",
      assets: "$131,664.40"
    }
  });

  const handleChange = name => event => {
    setState({ ...state, [name]: event.target.checked });
  };




  const onApplyChange = event => {
    let altay = [];
    if (state.TRY_EUR) {
      altay.push(["TRY/EUR", "Turkish Lira/Euro", "0.1574"]);
    }
    if (state.USD_JPY) {
      altay.push(["USD/JPY", "United States Dollar/Japanese Yen", "108.7480"]);
    }
    if (state.GBP_TRY) {
      altay.push(["GBP/TRY", "British Pound Sterling/Turkish Lira", "7.4000"]);
    }

    if (state.EUR_USD) {
      altay.push(["EUR/USD", "Euro/United States Dollar", "1.1074"]);
    }

    if (state.USD_TRY) {
      altay.push(["USD/TRY", "United States Dollar/Turkish Lira", "5.7182"]);
    }

    if (state.EUR_TRY) {
      altay.push(["EUR/TRY", "Euro/Japanese Yen", "6.3329"]);
    }
    if (state.GBP_USD) {
      altay.push([
        "GBP/USD",
        "British Pound Sterling / United States Dollar",
        "1.2952"
      ]);
    }

    if (state.BTC_TRY) {
      altay.push(["BTC/TRY", "Bitcoin / Turkish Lira", "46235.9183"]);
    }

    if (state.XMR_USD) {
      altay.push(["XMR/USD", "Monero / United States Dollar", "58.0200"]);
    }

    if (state.GOOGL_USD) {
      altay.push([
        "GOOGL/USD",
        "Alphabet Inc. / United States Dollar",
        "1296.4000"
      ]);
    }

    if (state.AGTHX_USD) {
      altay.push([
        "AGTHX/USD",
        "American Funds The Growth Fund of America Class A / United States Dollar",
        "52.4900"
      ]);
    }
    setListState(altay);
  };

  const {
    TRY_EUR,
    USD_JPY,
    GBP_TRY,
    EUR_USD,
    USD_TRY,
    EUR_TRY,
    GBP_USD,
    BTC_TRY,
    XMR_USD,
    GOOGL_USD,
    AGTHX_USD
  } = state;
  const error =
    [
      TRY_EUR,
      USD_JPY,
      GBP_TRY,
      EUR_USD,
      USD_TRY,
      EUR_TRY,
      GBP_USD,
      BTC_TRY,
      XMR_USD,
      GOOGL_USD,
      AGTHX_USD
    ].filter(v => v).length !== 2;

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
                          <h4 className={classes.cardTitleWhite}>Portfolio</h4>
                          <p className={classes.cardCategoryWhite}>
                            Equipments you follow
                          </p>
                        </CardHeader>
                        <CardBody>
                          <Table
                            tableHeaderColor="primary"
                            tableHead={[
                              "Equipment Symbol",
                              "Equipment Long Name",
                              "Parity"
                            ]}
                            tableData={list}
                          />
                        </CardBody>
                      </Card>
                    </GridItem>
                    <GridItem xs={12} sm={12} md={12}>
                      <Card plain>
                        <CardHeader plain color="primary">
                          <h4 className={classes.cardTitleWhite}>
                            Edit Your Portfolio
                          </h4>
                          <AnnotationCallout
                            x={150}
                            y={170}
                            dy={117}
                            dx={162}
                            color={"#9610ff"}
                            className="show-bg"
                            editMode={true}
                            note={{
                              title: "Annotations :)",
                              label: "Longer text to show text wrapping",
                              lineType: "horizontal",
                              bgPadding: {
                                top: 15,
                                left: 10,
                                right: 10,
                                bottom: 10
                              },
                              padding: 15,
                              titleColor: "#59039c"
                            }}
                          />

                          <p className={classes.cardCategoryWhite}>
                            Follow/Unfollow Equipments
                          </p>
                        </CardHeader>
                        <CardBody>
                          <FormControl
                            component="fieldset"
                            className={classes.formControl}
                          >
                            <FormGroup>
                              <FormControlLabel
                                control={
                                  <Checkbox
                                    checked={TRY_EUR}
                                    onChange={handleChange("TRY_EUR")}
                                    value="TRY_EUR"
                                  />
                                }
                                label="TRY/EUR"
                              />
                              <FormControlLabel
                                control={
                                  <Checkbox
                                    checked={USD_JPY}
                                    onChange={handleChange("USD_JPY")}
                                    value="USD_JPY"
                                  />
                                }
                                label="USD/JPY"
                              />
                              <FormControlLabel
                                control={
                                  <Checkbox
                                    checked={GBP_TRY}
                                    onChange={handleChange("GBP_TRY")}
                                    value="GBP_TRY"
                                  />
                                }
                                label="GBP/TRY"
                              />
                              <FormControlLabel
                                control={
                                  <Checkbox
                                    checked={EUR_USD}
                                    onChange={handleChange("EUR_USD")}
                                    value="EUR_USD"
                                  />
                                }
                                label="EUR/USD"
                              />
                              <FormControlLabel
                                control={
                                  <Checkbox
                                    checked={USD_TRY}
                                    onChange={handleChange("USD_TRY")}
                                    value="USD_TRY"
                                  />
                                }
                                label="USD/TRY"
                              />
                              <FormControlLabel
                                control={
                                  <Checkbox
                                    checked={EUR_TRY}
                                    onChange={handleChange("EUR_TRY")}
                                    value="EUR_TRY"
                                  />
                                }
                                label="EUR/TRY"
                              />
                            </FormGroup>
                          </FormControl>
                          <FormControl
                            component="fieldset"
                            className={classes.formControl}
                          >
                            <FormControlLabel
                              control={
                                <Checkbox
                                  checked={GBP_USD}
                                  onChange={handleChange("GBP_USD")}
                                  value="GBP_USD"
                                />
                              }
                              label="GBP/USD"
                            />
                            <FormControlLabel
                              control={
                                <Checkbox
                                  checked={BTC_TRY}
                                  onChange={handleChange("BTC_TRY")}
                                  value="BTC_TRY"
                                />
                              }
                              label="BTC/TRY"
                            />
                            <FormControlLabel
                              control={
                                <Checkbox
                                  checked={XMR_USD}
                                  onChange={handleChange("XMR_USD")}
                                  value="XMR_USD"
                                />
                              }
                              label="XMR/USD"
                            />
                            <FormControlLabel
                              control={
                                <Checkbox
                                  checked={GOOGL_USD}
                                  onChange={handleChange("GOOGL_USD")}
                                  value="GOOGL_USD"
                                />
                              }
                              label="GOOGL/USD"
                            />
                            <FormControlLabel
                              control={
                                <Checkbox
                                  checked={AGTHX_USD}
                                  onChange={handleChange("AGTHX_USD")}
                                  value="AGTHX_USD"
                                />
                              }
                              label="AGTHX/USD"
                            />
                          </FormControl>
                          <Button
                            variant="contained"
                            className={classes.button}
                            onClick={onApplyChange}
                          >
                            Apply changes
                          </Button>
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
