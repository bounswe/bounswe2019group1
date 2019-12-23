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
