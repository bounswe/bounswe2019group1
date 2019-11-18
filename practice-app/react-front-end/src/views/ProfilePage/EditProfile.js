import React, { useEffect, useState, Component } from "react";
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
import LocationPicker from "react-location-picker";
import avatar from "assets/img/faces/marc.jpg";
import { getProfileInfo } from "../../service/profileinformation.service";

const styles = {
  cardCategoryWhite: {
    color: "rgba(255,255,255,.62)",
    margin: "0",
    fontSize: "14px",
    marginTop: "0",
    marginBottom: "0"
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

export default function EditProfile() {
  const classes = useStyles();

  const [profileValues, setProfileValues] = useState({
    username: "",
    email: "",
    first_name: "",
    last_name: "",
    location: {
      address: ""
    },
    phone_number: 0,
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
      <GridContainer>
        <GridItem xs={12} sm={12} md={8}>
          <Card>
            <CardHeader color="primary">
              <h4 className={classes.cardTitleWhite}>Edit Profile</h4>
              <p className={classes.cardCategoryWhite}>Complete your profile</p>
            </CardHeader>
            <CardBody>
              <GridContainer>
                <GridItem xs={12} sm={12} md={5}>
                  <CustomInput
                    labelText="Company (disabled)"
                    id="company-disabled"
                    formControlProps={{
                      fullWidth: true
                    }}
                    inputProps={{
                      disabled: true
                    }}
                  />
                </GridItem>
                <GridItem xs={12} sm={12} md={3}>
                  <CustomInput
                    labelText="Username(Cannot Change)"
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
                <GridItem xs={12} sm={12} md={4}>
                  <CustomInput
                    labelText="Email address"
                    id="email"
                    value={profileValues.email}
                    formControlProps={{
                      fullWidth: true
                    }}
                    onChange={handleChange}
                  />
                </GridItem>
              </GridContainer>
              <GridContainer>
                <GridItem xs={12} sm={12} md={6}>
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
                <GridItem xs={12} sm={12} md={6}>
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
                    id="Biography"
                    value={profileValues.biography}
                    formControlProps={{
                      fullWidth: true
                    }}
                    inputProps={{
                      multiline: true,
                      rows: 5
                    }}
                    onChange={handleLocChange}
                  />
                </GridItem>
              </GridContainer>
            </CardBody>
            <CardFooter>
              <Button color="primary">Update Profile</Button>
            </CardFooter>
          </Card>
        </GridItem>
        <GridItem xs={12} sm={12} md={4}>
          <Card profile>
            <CardAvatar profile>
              <a href="#pablo" onClick={e => e.preventDefault()}>
                <img src={avatar} alt="..." />
              </a>
            </CardAvatar>
            <CardBody profile>
              <h6 className={classes.cardCategory}>{profileValues.title}</h6>
              <h4 className={classes.cardTitle}>
                {profileValues.first_name} {profileValues.last_name}
              </h4>
              <p className={classes.description}>{profileValues.biography}</p>
              <Button color="primary" round>
                Follow
              </Button>
            </CardBody>
          </Card>
        </GridItem>
      </GridContainer>
    );
  }
  return <div>{MapOrForm}</div>;
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
