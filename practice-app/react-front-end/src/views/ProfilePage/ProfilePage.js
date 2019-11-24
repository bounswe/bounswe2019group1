import React, { useState } from "react";
// nodejs library that concatenates classes
import classNames from "classnames";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
// @material-ui/icons
import Camera from "@material-ui/icons/Camera";
//import Palette from "@material-ui/icons/Palette";
//import Button from "components/CustomButtons/Button.js";

import Event from "@material-ui/icons/Event";
import Favorite from "@material-ui/icons/Favorite";
// core components
import Header from "components/Header/Header.js";
import Footer from "components/Footer/Footer.js";
import GridContainer from "components/Grid/GridContainer.js";
import GridItem from "components/Grid/GridItem.js";
import NavPills from "components/NavPills/NavPills.js";
import Parallax from "components/Parallax/Parallax.js";
import { Link } from "react-router-dom";
import profile from "assets/img/faces/ai.jpg";

import portfolio1 from "assets/img/examples/ppp1.jpeg";
import portfolio2 from "assets/img/examples/po2.jpeg";
import portfolio3 from "assets/img/examples/ppp3.jpg";
import portfolio4 from "assets/img/examples/BTC17-1.jpg";
import portfolio5 from "assets/img/examples/ppp4.jpeg";

import event1 from "assets/img/examples/ev1.jpeg";
import event2 from "assets/img/examples/ev2.jpeg";
import event3 from "assets/img/examples/ev3.jpeg";
import event4 from "assets/img/examples/ev4.jpg";
import event5 from "assets/img/examples/ev5.jpeg";
import Paper from "@material-ui/core/Paper";
import Grid from "@material-ui/core/Grid";

import styles from "assets/jss/material-kit-react/views/profilePage.js";
import { getProfileInfo } from "../../service/profileinformation.service";
import PheaderLinks from "components/ProfileHeader/PheaderLinks";
import { Apps, CloudDownload } from "@material-ui/icons";
import CustomDropdown from "components/CustomDropdown/CustomDropdown.js";

const useStyles = makeStyles(styles);

export default function ProfilePage(props) {
  const classes = useStyles();
  const { ...rest } = props;
  const [profileValues, setProfileValues] = useState({
    username: "",
    email: "",
    first_name: "",
    last_name: "",
    location: "",
    phone_number: 0,
    biography: "",
    title: "",
    photo: "",
    is_public: "",
    groups: []
  });
  const imageClasses = classNames(
    classes.imgRaised,
    classes.imgRoundedCircle,
    classes.imgFluid
  );
  useState(() => {
    getProfileInfo().then(res =>
      setProfileValues({
        username: res.username,
        first_name: res.first_name,
        last_name: res.last_name,
        location: res.location,
        phone_number: res.phone_number,
        biography: res.biography,
        title: res.title,
        photo: res.photo,
        is_public: res.is_public,
        groups: res.groups
      })
    );
  });
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
            <GridContainer justify="center">
              <GridItem xs={12} sm={12} md={6}>
                <div className={classes.profile}>
                  <div>
                    <img src={profile} alt="..." className={imageClasses} />
                  </div>
                  <div className={classes.name}>
                    <h3 className={classes.title}>
                      {profileValues.first_name} {profileValues.last_name}
                    </h3>
                    <h6>{profileValues.title}</h6>
                  </div>
                  <div className={classes.root}>
                    <Grid container spacing={3}>
                      <Grid item xs>
                        <Paper className={classes.paper}>Followers</Paper>
                      </Grid>
                      <Grid item xs></Grid>
                      <Grid item xs>
                        <Paper className={classes.paper}>Following</Paper>
                      </Grid>
                    </Grid>
                    <Grid container spacing={3}>
                      <Grid item xs>
                        <Paper className={classes.paper}>35</Paper>
                      </Grid>
                      <Grid item xs={6}></Grid>
                      <Grid item xs>
                        <Paper className={classes.paper}>34</Paper>
                      </Grid>
                    </Grid>
                  </div>
                </div>
              </GridItem>
            </GridContainer>
            <div className={classes.description}>
              <p>{profileValues.biography} </p>
            </div>
            <GridContainer justify="center">
              <GridItem xs={12} sm={12} md={8} className={classes.navWrapper}>
                <NavPills
                  alignCenter
                  color="primary"
                  tabs={[
                    {
                      tabButton: "Portfolios",
                      tabIcon: Camera,
                      tabContent: (
                        <GridContainer justify="center">
                          <GridItem xs={12} sm={12} md={4}>
                            <img
                              alt="..."
                              src={portfolio1}
                              className={navImageClasses}
                            />
                            <img
                              alt="..."
                              src={portfolio5}
                              className={navImageClasses}
                            />
                          </GridItem>
                          <GridItem xs={12} sm={12} md={4}>
                            <img
                              alt="..."
                              src={portfolio4}
                              className={navImageClasses}
                            />
                            <img
                              alt="..."
                              src={portfolio2}
                              className={navImageClasses}
                            />
                            <img
                              alt="..."
                              src={portfolio3}
                              className={navImageClasses}
                            />
                          </GridItem>
                        </GridContainer>
                      )
                    },
                    {
                      tabButton: "Events",
                      tabIcon: Event,
                      tabContent: (
                        <GridContainer justify="center">
                          <GridItem xs={12} sm={12} md={4}>
                            <img
                              alt="..."
                              src={event1}
                              className={navImageClasses}
                            />
                            <img
                              alt="..."
                              src={event2}
                              className={navImageClasses}
                            />
                            <img
                              alt="..."
                              src={event3}
                              className={navImageClasses}
                            />
                          </GridItem>
                          <GridItem xs={12} sm={12} md={4}>
                            <img
                              alt="..."
                              src={event4}
                              className={navImageClasses}
                            />
                            <img
                              alt="..."
                              src={event5}
                              className={navImageClasses}
                            />
                          </GridItem>
                        </GridContainer>
                      )
                    },
                    {
                      tabButton: "Likes",
                      tabIcon: Favorite,
                      tabContent: (
                        <GridContainer justify="center">
                          <GridItem xs={12} sm={12} md={4}>
                            <img
                              alt="..."
                              src={event4}
                              className={navImageClasses}
                            />
                            <img
                              alt="..."
                              src={portfolio3}
                              className={navImageClasses}
                            />
                          </GridItem>
                          <GridItem xs={12} sm={12} md={4}>
                            <img
                              alt="..."
                              src={event2}
                              className={navImageClasses}
                            />

                            <img
                              alt="..."
                              src={portfolio1}
                              className={navImageClasses}
                            />
                          </GridItem>
                        </GridContainer>
                      )
                    }
                  ]}
                />
              </GridItem>
            </GridContainer>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}
