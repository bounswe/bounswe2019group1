import React from "react";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import InputAdornment from "@material-ui/core/InputAdornment";
import Icon from "@material-ui/core/Icon";
// @material-ui/icons
import Email from "@material-ui/icons/Email";
// core components
import Header from "components/Header/Header.js";
import HeaderLinks from "components/Header/HeaderLinks.js";
import Footer from "components/Footer/Footer.js";
import GridContainer from "components/Grid/GridContainer.js";
import GridItem from "components/Grid/GridItem.js";
import Button from "components/CustomButtons/Button.js";
import Card from "components/Card/Card.js";
import CardBody from "components/Card/CardBody.js";
import CardHeader from "components/Card/CardHeader.js";
import CardFooter from "components/Card/CardFooter.js";
import CustomInput from "components/CustomInput/CustomInput.js";

import styles from "assets/jss/material-kit-react/views/loginPage.js";

//Altay home page importlari.
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Typography from '@material-ui/core/Typography';

import image from "assets/img/dollar-hd.jpg";
import cardimage from "assets/img/pricehistory.jpg";
import cardimage2 from "assets/img/handshake.jpg";




const useStyles = makeStyles(styles);
export default function LoginPage(props) {
  const [cardAnimaton, setCardAnimation] = React.useState("cardHidden");
  setTimeout(function() {
    setCardAnimation("");
  }, 700);
  const classes = useStyles();
  const { ...rest } = props;
  return (
    <div>
      <Header
        absolute
        color="transparent"
        brand="Material Kit React"
        rightLinks={<HeaderLinks />}
        {...rest}
      />
      <div
        className={classes.pageHeader}
        style={{
          backgroundImage: "url(" + image + ")",
          backgroundSize: "cover",
          backgroundPosition: "top center"
        }}
      >
        <div className={classes.container}>
          <GridContainer justify="center">
            <GridItem xs={12} sm={12} md={4}>
            <Card className={classes.card}>
              <CardActionArea>
                <CardMedia
                  component="img"
                  alt="Contemplative Reptile"
                  height="140"
                  image={cardimage}
                  title="Contemplative Reptile"
                />
                <CardContent>
                  <Typography gutterBottom variant="h5" component="h2">
                    <center>Welcome</center>
                  </Typography>
                  <Typography variant="body2" color="textSecondary" component="p">
                    Login to check trending financial news.
                  </Typography>
                </CardContent>
              </CardActionArea>
              <CardActions>
                <Button
                size="small"
                color="primary"
                href="http://localhost:3000/login-page"
                className={classes.button}>
                  Login
                </Button>
              </CardActions>
            </Card>
            <Card className={classes.card2}>
              <CardActionArea>
                <CardMedia
                  component="img"
                  alt="Contemplative Reptile"
                  height="140"
                  image={cardimage2}
                  title="Contemplative Reptile"
                />
                <CardContent>
                  <Typography gutterBottom variant="h5" component="h2">
                    <center>Not a member?</center>
                  </Typography>
                  <Typography variant="body2" color="textSecondary" component="p">
                    Sign up to become a member of Khaji-it family.
                  </Typography>
                </CardContent>
              </CardActionArea>
              <CardActions>
                <Button
                size="small"
                color="primary"
                href="http://localhost:3000/sign-up"
                className={classes.button}
                >
                Sign Up
                </Button>
              </CardActions>
            </Card>
            </GridItem>
          </GridContainer>

        </div>
        <Footer whiteFont />
      </div>
    </div>
  );
}
