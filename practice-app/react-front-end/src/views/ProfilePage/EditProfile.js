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
import swal from "sweetalert";

import { getProfileInfo } from "../../service/profileinformation.service";
import { editProfile } from "service/edit-profile-service.js";
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
  const handleSubmit = event => {
    event.preventDefault();

    editProfile(profileValues).then(
      function() {
        props.history.push("/profile-page");
      },
      function() {
        swal("Oops", "Incorrect username or password!", "error");
      }
    );
  };
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
              <h5 className={classes.description}>
                <b>Biography:</b>
                {profileValues.biography}
              </h5>
            </CardBody>
          </Card>
        </GridItem>

        <GridItem xs={12} sm={12} md={12}>
          <Card>
            <CardHeader color="primary">
              <h4 className={classes.cardTitleWhite}>Edit Profile</h4>
              <p className={classes.cardCategoryWhite}>Complete your profile</p>
            </CardHeader>
            <CardBody>
              <GridContainer>
                <GridItem xs={12} sm={12} md={5}>
                  <CustomInput
                    labelText="Username"
                    id="username"
                    value={profileValues.username}
                    formControlProps={{
                      fullWidth: true
                    }}
                    inputProps={{
                      disabled: true
                    }}
                  />
                </GridItem>
                <GridItem xs={12} sm={12} md={7}>
                  <CustomInput
                    labelText="Email address"
                    id="email"
                    value={profileValues.email}
                    formControlProps={{
                      fullWidth: true
                    }}
                    inputProps={{
                      disabled: true
                    }}
                  />
                </GridItem>
              </GridContainer>
              <GridContainer>
                <GridItem xs={12} sm={12} md={4}>
                  <CustomInput
                    labelText="First Name"
                    id="first_name"
                    value={profileValues.first_name}
                    formControlProps={{
                      fullWidth: true
                    }}
                    onChange={handleChange}
                  />
                </GridItem>
                <GridItem xs={12} sm={12} md={4}>
                  <CustomInput
                    labelText="Last Name"
                    id="last_name"
                    value={profileValues.last_name}
                    formControlProps={{
                      fullWidth: true
                    }}
                    onChange={handleChange}
                  />
                </GridItem>
                <GridItem xs={12} sm={12} md={4}>
                  <CustomInput
                    labelText="Title"
                    id="title"
                    value={profileValues.title}
                    formControlProps={{
                      fullWidth: true
                    }}
                    onChange={handleChange}
                  />
                </GridItem>
              </GridContainer>
              <GridContainer>
                <GridItem xs={12} sm={12} md={12}>
                  <CustomInput
                    labelText="Location"
                    id="location"
                    formControlProps={{
                      fullWidth: true
                    }}
                    value={profileValues.location.address}
                    inputProps={{
                      type: "location",
                      endAdornment: (
                        <InputAdornment position="end">
                          <Icon
                            className={classes.inputIconsColor}
                            onClick={() => {
                              setIsLocationSelected({ selected: true });
                            }}
                          >
                            room
                          </Icon>
                        </InputAdornment>
                      )
                    }}
                    onChange={handleLocChange}
                  />
                </GridItem>
              </GridContainer>
              <GridContainer>
                <GridItem xs={12} sm={12} md={12}>
                  <InputLabel style={{ color: "#AAAAAA" }}>About me</InputLabel>
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
              <FormControl className={classes.formControl} fullWidth="true">
                <Select
                  value={profileValues.usertype}
                  inputProps={{
                    name: "usertype"
                  }}
                  onChange={handleChangeForm}
                >
                  <MenuItem value={"Basic"}>Basic</MenuItem>
                  <MenuItem value={"Trading"}>Trading</MenuItem>
                </Select>
                <FormHelperText>Select User Type</FormHelperText>
              </FormControl>

              <FormControl className={classes.formControl} fullWidth="true">
                <Select
                  value={profileValues.is_public}
                  inputProps={{
                    name: "is_public"
                  }}
                  onChange={handleChangeForm}
                >
                  <MenuItem value={"true"}>Public</MenuItem>
                  <MenuItem value={"false"}>Private</MenuItem>
                </Select>
                <FormHelperText>Select Privacy Mode</FormHelperText>
              </FormControl>
            </CardBody>
            <CardFooter>
              <Button color="primary" onClick={handleSubmit}>
                Update Profile
              </Button>
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
