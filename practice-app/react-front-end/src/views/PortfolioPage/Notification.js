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
    NOT_1: true,
    NOT_2: true,
    NOT_3: true,
    NOT_4: false,
    NOT_5: false,
    NOT_6: false,
    NOT_7: false,
    NOT_8: false,
    NOT_9: false,
    NOT_10: false,
    NOT_11: false
  });

  const [list, setListState] = React.useState([
    ["07.12.2019", "You have succesfully created article", "Khaji-it"],
    ["07.12.2019", "You have successfully updated your portfolio", "Khaji-it"],
    ["07.12.2019", "Mete Kurt has followed you", "metekurt"],
  ]);

  const handleChange = name => event => {
    setState({ ...state, [name]: event.target.checked });
  };

  const onApplyChange = event => {
    let altay = [];
    if (state.NOT_1) {
      altay.push(["07.12.2019", "You have succesfully created article", "Khaji-it"]);
    }

    if (state.NOT_2) {
      altay.push(["07.12.2019", "You have successfully updated your portfolio", "Khaji-it"]);
    }

    if (state.NOT_3) {
      altay.push(["07.12.2019", "Mete Kurt has followed you", "metekurt"]);
    }

    setListState(altay);
  };

  const {
    NOT_1,
    NOT_2,
    NOT_3,
    NOT_4,
    NOT_5,
    NOT_6,
    NOT_7,
    NOT_8,
    NOT_9,
    NOT_10,
    NOT_11
  } = state;
  const error =
    [
      NOT_1,
      NOT_2,
      NOT_3,
      NOT_4,
      NOT_5,
      NOT_6,
      NOT_7,
      NOT_8,
      NOT_9,
      NOT_10,
      NOT_11
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
                          <h4 className={classes.cardTitleWhite}>Notifications</h4>
                          <p className={classes.cardCategoryWhite}>
                            Unseen notifications
                          </p>
                        </CardHeader>
                        <CardBody>
                          <Table
                            tableHeaderColor="primary"
                            tableHead={[
                              "Date",
                              "Description",
                              "From"
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
                            Set as read
                          </h4>
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
                                    checked={NOT_1}
                                    onChange={handleChange("NOT_1")}
                                    value="NOT_1"
                                  />
                                }
                                label="Notification 1"
                              />
                              <FormControlLabel
                                control={
                                  <Checkbox
                                    checked={NOT_2}
                                    onChange={handleChange("NOT_2")}
                                    value="NOT_2"
                                  />
                                }
                                label="Notification 2"
                              />
                              <FormControlLabel
                                control={
                                  <Checkbox
                                    checked={NOT_3}
                                    onChange={handleChange("NOT_3")}
                                    value="NOT_3"
                                  />
                                }
                                label="Notification 3"
                              />
                              <FormControlLabel
                                control={
                                  <Checkbox
                                    checked={NOT_4}
                                    onChange={handleChange("NOT_4")}
                                    value="NOT_4"
                                  />
                                }
                                label="Notification 4"
                              />
                              <FormControlLabel
                                control={
                                  <Checkbox
                                    checked={NOT_5}
                                    onChange={handleChange("NOT_5")}
                                    value="NOT_5"
                                  />
                                }
                                label="Notification 5"
                              />
                              <FormControlLabel
                                control={
                                  <Checkbox
                                    checked={NOT_6}
                                    onChange={handleChange("NOT_6")}
                                    value="NOT_6"
                                  />
                                }
                                label="Notification 6"
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
                                  checked={NOT_7}
                                  onChange={handleChange("NOT_7")}
                                  value="NOT_7"
                                />
                              }
                              label="Notification 7"
                            />
                            <FormControlLabel
                              control={
                                <Checkbox
                                  checked={NOT_8}
                                  onChange={handleChange("NOT_8")}
                                  value="NOT_8"
                                />
                              }
                              label="Notification 8"
                            />
                            <FormControlLabel
                              control={
                                <Checkbox
                                  checked={NOT_9}
                                  onChange={handleChange("NOT_9")}
                                  value="NOT_9"
                                />
                              }
                              label="Notification 9"
                            />
                            <FormControlLabel
                              control={
                                <Checkbox
                                  checked={NOT_10}
                                  onChange={handleChange("NOT_10")}
                                  value="NOT_10"
                                />
                              }
                              label="Notification 10"
                            />
                            <FormControlLabel
                              control={
                                <Checkbox
                                  checked={NOT_11}
                                  onChange={handleChange("NOT_11")}
                                  value="NOT_11"
                                />
                              }
                              label="Notification 11"
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
