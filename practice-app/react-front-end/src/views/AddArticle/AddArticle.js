import React, { useState } from "react";
// nodejs library that concatenates classes
import classNames from "classnames";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
// @material-ui/icons

//import Palette from "@material-ui/icons/Palette";
//import Button from "components/CustomButtons/Button.js";


// core components
import Header from "components/Header/Header.js";
import Footer from "components/Footer/Footer.js";
import GridContainer from "components/Grid/GridContainer.js";
import GridItem from "components/Grid/GridItem.js";
import Parallax from "components/Parallax/Parallax.js";

import Paper from "@material-ui/core/Paper";
import Grid from "@material-ui/core/Grid";

import styles from "assets/jss/material-kit-react/views/articlePage.js";
import { getProfileInfo } from "../../service/profileinformation.service";
import PheaderLinks from "components/ProfileHeader/PheaderLinks";

import Adding from "./section/Adding";
import Typography from '@material-ui/core/Typography';
import ButtonBase from '@material-ui/core/ButtonBase';

import articleThumbnail from "assets/img/examples/investor.jpeg";
//import Adding from "./section/Adding";

const useStyles = makeStyles(styles);


export default function ProfilePage(props) {
  const classes = useStyles();
  const { ...rest } = props;
  const [profileValues, setProfileValues] = useState({
    username: "",
    email: "",
    first_name: "",
    last_name: "",
    location: ""
  });
  const imageClasses = classNames(
    classes.imgRaised,
    classes.imgRoundedCircle,
    classes.imgFluid
  );
  getProfileInfo().then(res =>
    setProfileValues({
      username: res.username,
      first_name: res.first_name,
      last_name: res.last_name,
      location: res.location
    })
  );
  const navImageClasses = classNames(classes.imgRounded, classes.imgGallery);
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
        {...rest}
      />
      <Parallax small filter image={require("assets/img/dollar-hd.jpg")} />
      <div className={classNames(classes.main, classes.mainRaised)}>
        <div>
          <div className={classes.container}>

          <div className={classNames(classes.main, classes.mainRaised)}>
                         <div className={classes.container}>
          
                             <Adding />
                         </div>
                      </div>

            
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}
