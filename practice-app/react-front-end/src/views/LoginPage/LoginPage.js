import React, { useState } from "react";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import InputAdornment from "@material-ui/core/InputAdornment";
import Icon from "@material-ui/core/Icon";
// @material-ui/icons
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
import { Link } from "react-router-dom";
import styles from "assets/jss/material-kit-react/views/loginPage.js";
import image from "assets/img/dollar-hd.jpg";
import { login } from "../../service/authentication.service.js";
import swal from "sweetalert";
import { Redirect } from "react-router-dom";

import ReactDOM from "react-dom";
import GoogleLogin from "react-google-login";

//altay
const responseGoogleSuccess = response => {
  window.location.href = "http://localhost:3000/profile-page";
  console.log(response);
};

const responseGoogleFailure = response => {
  console.log(response.getStatusCode());
  swal("Oops", "Incorrect username or password!", "error");
};

const useStyles = makeStyles(styles);
export default function LoginPage(props) {
  const [cardAnimaton, setCardAnimation] = useState("cardHidden");
  setTimeout(function() {
    setCardAnimation("");
  }, 700);
  const [values, setValues] = useState({
    username: "",
    pass: ""
  });
  const classes = useStyles();
  const { ...rest } = props;

  const handleChange = event => {
    event.persist();
    setValues(oldValues => ({
      ...oldValues,
      [event.target.id]: event.target.value
    }));
  };
  const handleSubmit = event => {
    event.preventDefault();
    login(values.username, values.pass).then(
      function(response) {
        if (localStorage.getItem("currentUser")) {
          props.history.push("/profile-page");
        }
      },
      function() {
        swal("Oops", "Incorrect username or password!", "error");
      }
    );
  };
  return (
    <div>
      <Header
        absolute
        color="transparent"
        brand="Khaji-it Traders Platform"
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
              <Card className={classes[cardAnimaton]}>
                <form className={classes.form}>
                  <CardHeader color="primary" className={classes.cardHeader}>
                    <h4>
                      <b>Login</b>
                    </h4>
                    <div className={classes.socialLine}>
                                            
                      <GoogleLogin
                      clientId="510505564353-67arm3s7fpa87aumuktnak7eto3kq4nc.apps.googleusercontent.com"
                      //buttonText=""
                      onSuccess={responseGoogleSuccess}
                      onFailure={responseGoogleFailure}
                      cookiePolicy={"single_host_origin"}
                    />
                    </div>
                  </CardHeader>
                  <CardBody>
                    <CustomInput
                      labelText="Username"
                      id="username"
                      formControlProps={{
                        fullWidth: true
                      }}
                      inputProps={{
                        type: "username",
                        endAdornment: (
                          <InputAdornment position="end">
                            <Icon className={classes.inputIconsColor}>
                              perm_identity
                            </Icon>
                          </InputAdornment>
                        )
                      }}
                      onChange={handleChange}
                    />
                    <CustomInput
                      labelText="Password"
                      id="pass"
                      formControlProps={{
                        fullWidth: true
                      }}
                      inputProps={{
                        type: "password",
                        endAdornment: (
                          <InputAdornment position="end">
                            <Icon className={classes.inputIconsColor}>
                              lock_outline
                            </Icon>
                          </InputAdornment>
                        ),
                        autoComplete: "off"
                      }}
                      onChange={handleChange}
                    />
                  </CardBody>
                  <CardFooter className={classes.cardFooter}>
                    <Button
                      simple
                      color="primary"
                      size="lg"
                      onClick={handleSubmit}
                    >
                      Login
                    </Button>
                    
                  </CardFooter>
                  <CardFooter className={classes.cardFooter}>
                    New to Khaji-it?
                    <Link to="/sign-up"> Sign up now</Link>
                  </CardFooter>
                </form>
              </Card>
            </GridItem>
          </GridContainer>
        </div>
        <Footer whiteFont />
      </div>
    </div>
  );
}
