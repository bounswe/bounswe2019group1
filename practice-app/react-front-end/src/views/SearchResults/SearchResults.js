/*eslint-disable*/
import React from "react";
// nodejs library to set properties for components
import PropTypes from "prop-types";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
// @material-ui/icons
import AddAlert from "@material-ui/icons/AddAlert";
// core components
import Header from "components/Header/Header.js";
import Footer from "components/Footer/Footer.js";
import PheaderLinks from "components/ProfileHeader/PheaderLinks";
import Parallax from "components/Parallax/Parallax.js";
import GridItem from "components/Grid/GridItem.js";
import GridContainer from "components/Grid/GridContainer.js";
import Button from "components/CustomButtons/Button.js";
import SnackbarContent from "components/Snackbar/SnackbarContent.js";
import Snackbar from "components/Snackbar/Snackbar.js";
import Card from "components/Card/Card.js";
import CardHeader from "components/Card/CardHeader.js";
import CardBody from "components/Card/CardBody.js";
import classNames from "classnames";


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
  cardTitleWhite: {
    color: "#FFFFFF",
    marginTop: "50px",
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

export default function SearchResults() {
  
  const classes = useStyles();
  const [tl, setTL] = React.useState(false);
  const [tc, setTC] = React.useState(false);
  const [tr, setTR] = React.useState(false);
  const [bl, setBL] = React.useState(false);
  const [bc, setBC] = React.useState(false);
  const [br, setBR] = React.useState(false);
  React.useEffect(() => {
    // Specify how to clean up after this effect:
    return function cleanup() {
      // to stop the warning of calling setState of unmounted component
      var id = window.setTimeout(null, 0);
      while (id--) {
        window.clearTimeout(id);
      }
    };
  });
  const showNotification = place => {
    switch (place) {
      case "tl":
        if (!tl) {
          setTL(true);
          setTimeout(function() {
            setTL(false);
          }, 6000);
        }
        break;
      case "tc":
        if (!tc) {
          setTC(true);
          setTimeout(function() {
            setTC(false);
          }, 6000);
        }
        break;
      case "tr":
        if (!tr) {
          setTR(true);
          setTimeout(function() {
            setTR(false);
          }, 6000);
        }
        break;
      case "bl":
        if (!bl) {
          setBL(true);
          setTimeout(function() {
            setBL(false);
          }, 6000);
        }
        break;
      case "bc":
        if (!bc) {
          setBC(true);
          setTimeout(function() {
            setBC(false);
          }, 6000);
        }
        break;
      case "br":
        if (!br) {
          setBR(true);
          setTimeout(function() {
            setBR(false);
          }, 6000);
        }
        break;
      default:
        break;
    }
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
      
     
      <CardHeader color="primary">
        <h4 className={classes.cardTitleWhite}>Search Results</h4>
        <p className={classes.cardCategoryWhite}>
          List all related users, articles and events 
          {/* {" "}
         
          
          . Please checkout the{" "}
          <a href="#pablo" target="_blank">
            full documentation
          </a> */}
          .
        </p>
      </CardHeader>
      
      <CardBody>
        <GridContainer>
          <GridItem xs={12} sm={12} md={6}>
            <h5>Advanced Search</h5>
            <br />
            <SnackbarContent message={"User1"} />
            <SnackbarContent message={"User2"} />
            <SnackbarContent message={"Article1"} />
            <SnackbarContent message={"Article2"} />
            <SnackbarContent message={"Article3"} />
            <SnackbarContent message={"Article4"} />
          </GridItem>
          
        </GridContainer>
        <br />
        <br />
        
       
      </CardBody>
      <Footer />
    
    </div>
  );
}
