import React from "react";
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
import Parallax from "components/Parallax/Parallax.js";

import styles from "assets/jss/material-kit-react/views/articlePage.js";
import PheaderLinks from "components/ProfileHeader/PheaderLinks";
import Adding from "./section/Adding";

//import Adding from "./section/Adding";

const useStyles = makeStyles(styles);

export default function AddArticle(props) {
  const classes = useStyles();
  const { ...rest } = props;
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
                <Adding history={props.history} />
              </div>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}
