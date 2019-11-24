import React, { useState, Component } from "react";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import InputLabel from "@material-ui/core/InputLabel";
import InputAdornment from "@material-ui/core/InputAdornment";
import Icon from "@material-ui/core/Icon";
// core components
import GridItem from "components/Grid/GridItem.js";
import GridContainer from "components/Grid/GridContainer.js";
import CustomInput from "components/CustomInput/CustomInput.js";
import Button from "components/CustomButtons/Button.js";
import Card from "components/Card/Card.js";
import CardHeader from "components/Card/CardHeader.js";
import CardAvatar from "components/Card/CardAvatar.js";
import CardBody from "components/Card/CardBody.js";
import CardFooter from "components/Card/CardFooter.js";
import Footer from "components/Footer/Footer.js";
import Header from "components/Header/Header.js";
import LocationPicker from "react-location-picker";
import PheaderLinks from "components/ProfileHeader/PheaderLinks";
import { Select } from "@material-ui/core";
import FormControl from "@material-ui/core/FormControl";
import MenuItem from "@material-ui/core/MenuItem";
import FormHelperText from "@material-ui/core/FormHelperText";
import avatar from "assets/img/faces/marc.jpg";
import image from "assets/img/dollar-hd.jpg";
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Quote from "components/Typography/Quote.js";



import { getProfileInfo } from "../../service/profileinformation.service";

const styles = {
  container: {
    "@media (min-width: 576px)": {
      maxWidth: "540px"
    },
    "@media (min-width: 768px)": {
      maxWidth: "720px"
    },
    "@media (min-width: 992px)": {
      maxWidth: "960px"
    },
    "@media (min-width: 1200px)": {
      maxWidth: "1140px"
    },
    paddingRight: "15px",
    paddingLeft: "15px",
    marginRight: "auto",
    marginLeft: "auto",
    width: "100%",
    zIndex: "2",
    position: "relative",
    paddingTop: "20vh",
    color: "#FFFFFF",
    paddingBottom: "200px",
    opacity: "0.88"
  },
  cardCategoryWhite: {
    color: "rgba(255,255,255,.62)",
    margin: "0",
    fontSize: "14px",
    marginTop: "0",
    marginBottom: "0"
  },
  cardTitle: {
    textAlign: "center"
  },
  cardTitleWhite: {
    color: "#FFFFFF",
    marginTop: "0px",
    minHeight: "auto",
    fontWeight: "300",
    fontFamily: "'Roboto', 'Helvetica', 'Arial', sans-serif",
    marginBottom: "3px",
    textDecoration: "none"
  }
};

const useStyles = makeStyles(styles);

export default function EditProfile(props) {
  const classes = useStyles();
  const { ...rest } = props;
  const [profileValues, setProfileValues] = useState({
    username: "",
    email: "",
    first_name: "",
    last_name: "",
    location: {
      address: ""
    },
    phone_number: 0,
    usertype: "Trader",
    biography: "",
    title: "",
    photo: "",
    is_public: "",
    groups: []
  });
  useState(() => {
    // Update the document title using the browser API
    getProfileInfo().then(res =>
      setProfileValues({
        username: res.username,
        email: res.email,
        first_name: res.first_name,
        last_name: res.last_name,
        location: { address: res.location },
        phone_number: res.phone_number,
        biography: res.biography,
        usertype: res.groups[0],
        title: res.title,
        photo: res.photo,
        is_public: res.is_public,
        groups: res.groups
      })
    );
  });


  const handleChange = event => {
    event.persist();
    setProfileValues(oldValues => ({
      ...oldValues,
      [event.target.id]: event.target.value
    }));
  };
  const [isTraderUserSelected, setIsTraderUserSelected] = React.useState({
    selected: false
  });

  const handleChangeForm = event => {
    if (event.target.value === "basic") {
      setIsTraderUserSelected({ selected: false });
      setProfileValues(oldValues => ({
        ...oldValues,
        [event.target.name]: event.target.value,
        iban: ""
      }));
    } else {
      setIsTraderUserSelected({ selected: true });
      setProfileValues(oldValues => ({
        ...oldValues,
        [event.target.name]: event.target.value
      }));
    }
  };
  const handleLocChange = event => {
    event.persist();
    setProfileValues(oldValues => ({
      ...oldValues,
      [event.target.id]: {
        address: event.target.value,
        position: {}
      }
    }));
  };
  const [isLocationSelected, setIsLocationSelected] = React.useState({
    selected: false
  });
  let MapOrForm;
  if (isLocationSelected.selected) {
    MapOrForm = (
      <LocationPickerMap
        values={profileValues}
        setValues={setProfileValues}
        setIsLocationSelected={setIsLocationSelected}
      />
    );
  } else {
    MapOrForm = (
      <GridContainer justify="center">
        <GridItem xs={12} sm={12} md={12}>
          <Card profile>
            <CardAvatar profile>
              <a href="#pablo" onClick={e => e.preventDefault()}>
                <img src={avatar} alt="..." />
              </a>
            </CardAvatar>
            <CardBody profile>
              <h6 className={classes.cardCategory}>{profileValues.title}</h6>
              <h3 className={classes.cardTitle}>
                {profileValues.first_name} {profileValues.last_name}

              </h3>
              <h5> <center> Student - Boun </center> </h5>
              <Paper className={classes.root}>
              <h5>November 24, 2019</h5>
                <Typography variant="h5" component="h3">
                   <center>The Key Traits of Patient and Successful Investors</center>
                </Typography>
                <Typography component="p">
                  <br></br>
                  <p>According to Entrepreneur Network partner Phil Town, one of the
                  most valuable traits an investor can have is patience. If you
                  are a patient investor and decide on good businesses, Town says
                  there is virtually no scenario where you will not make money.
                  Here are some of the traits of patient investors ... and thus,
                  investors who will be prepared for retirement:</p>
                  <ul>
                  <li>They have long-term goals.</li>
                  <li>They are slow to buy. Most times, the people who hold off on
                   buying companies are also taking time to do their research.
                   Town recommends investing in no more than 10 companies -- like
                   you're hoping to reach the limit on a punch card with 10 slots.</li>
                  <li>They are confident. These investors are sure they will make
                  money on their investments, and act accordingly. </li>
                  <li>They are rational. Fear and greed are two emotions that
                   throw a previously smart investor onto the wrong track;
                   emotional influence can be an investor downfall. In this sense,
                   patient investors often prove to also be rational investors. </li>
                  </ul>
                  Click the video to hear more from Phil Town about patient investing.
                  <br></br>
                  Related:
                  <a href="https://www.entrepreneur.com/video/341533">How to
                  Approach Your Finances If You Want to Retire Stress-Free</a>
                  <br></br>
                  <br></br>
                  <p>
                    <a href="https://www.entrepreneur.com/video/341533">
                    Entrepreneur Network
                    </a>
                  &nbsp;
                  is a premium video network providing
                  entertainment, ewitducation and inspiration from successful
                  entrepreneurs and thought leaders. We provide expertise and
                  opportunities to accelerate brand growth and effectively
                  monetize video and audio content distributed across all
                  digital platforms for the business genre.
                  </p>

                </Typography>

              </Paper>
            </CardBody>
          </Card>
        </GridItem>

        <GridItem xs={12} sm={12} md={12}>
          <Card profile>
          <CardHeader color="primary">
            <h4 className={classes.cardTitleWhite}>Comments</h4>
          </CardHeader>
            <CardBody profile>
              <Paper className={classes.root}>
                <div className={classes.typo}>
                  <div className={classes.note}></div>
                  <Quote
                    text="Amazing."
                    author=" NY Times"
                  />
                </div>
                <div className={classes.typo}>
                  <div className={classes.note}></div>
                  <Quote
                    text="Unforgettable."
                    author=" NY Times"
                  />
                </div>
                <div className={classes.typo}>
                  <div className={classes.note}></div>
                  <Quote
                    text="Once in a life-time experience."
                    author=" NY Times"
                  />
                </div>
              </Paper>
            </CardBody>
          </Card>
        </GridItem>


        <GridItem xs={12} sm={12} md={12}>
          <Card>
            <CardHeader color="primary">
              <h4 className={classes.cardTitleWhite}>Add your comment</h4>
            </CardHeader>
            <CardBody>
              <GridContainer>
                <GridItem xs={12} sm={12} md={12}>
                  <InputLabel style={{ color: "#AAAAAA" }}>Be polite!</InputLabel>
                  <CustomInput
                    id="biography"
                    value={profileValues.biography}
                    formControlProps={{
                      fullWidth: true
                    }}
                    inputProps={{
                      multiline: true,
                      rows: 5
                    }}
                    onChange={handleChange}
                  />
                </GridItem>
              </GridContainer>
            </CardBody>
            <CardFooter>
              <Button color="primary">Send comment</Button>
            </CardFooter>
          </Card>
        </GridItem>
      </GridContainer>
    );
  }
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
      <div
        className={classes.pageHeader}
        style={{
          backgroundImage: "url(" + image + ")",
          backgroundSize: "cover",
          backgroundPosition: "top center"
        }}
      >
        <div className={classes.container}>{MapOrForm}</div>
        <Footer whiteFont />
      </div>
    </div>
  );
}

class LocationPickerMap extends Component {
  constructor(props) {
    super(props);
    this.state = {
      address: "Istanbul, Turkey",
      position: {
        lat: 41.040578365183514,
        lng: 29.092968749999955
      }
    };
    this.props = props;
    this.handleLocationChange = this.handleLocationChange.bind(this);
  }

  handleLocationChange({ position, address, places }) {
    // Set new location
    this.props.setValues(oldValues => ({
      ...oldValues,
      location: { position: position, address: address }
    }));

    // this.props.setLocation({ position: position, address: address });
    // eslint-disable-next-line react/prop-types
    this.props.setIsLocationSelected({ selected: false });
  }

  render() {
    return (
      <div>
        <h1>{this.state.address}</h1>
        <div>
          <LocationPicker
            containerElement={<div style={{ height: "100%" }} />}
            mapElement={<div style={{ height: "500px" }} />}
            defaultPosition={this.state.position}
            onChange={this.handleLocationChange}
          />
        </div>
      </div>
    );
  }
}
