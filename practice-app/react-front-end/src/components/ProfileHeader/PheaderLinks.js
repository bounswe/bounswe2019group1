import React, { useState } from "react";
// react components for routing our app without refresh
import { Link } from "react-router-dom";

// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import { getAllUsers } from "service/user.service.js";
import SearchIcon from "@material-ui/icons/Search";
import Tooltip from "@material-ui/core/Tooltip";
import Autocomplete from "@material-ui/lab/Autocomplete";

// @material-ui/icons
import { Apps, CloudDownload, Assignment } from "@material-ui/icons";

// core components
import CustomDropdown from "components/CustomDropdown/CustomDropdown.js";
import Button from "components/CustomButtons/Button.js";
import { logout } from "service/authentication.service.js";
import { fade } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";

// const handleSearchChange = event => {
//   event.persist();
//   setSearchValues({
//     input: event.target.value
//   });
// };

const useStyles = makeStyles(theme => ({
  grow: {
    flexGrow: 1
  },
  menuButton: {
    marginRight: theme.spacing(2)
  },
  title: {
    display: "none",
    [theme.breakpoints.up("sm")]: {
      display: "block"
    }
  },
  search: {
    position: "relative",
    borderRadius: theme.shape.borderRadius,
    backgroundColor: fade(theme.palette.common.white, 0.15),
    "&:hover": {
      backgroundColor: fade(theme.palette.common.white, 0.25)
    },
    marginRight: theme.spacing(2),
    marginLeft: 0,
    width: "100%",
    [theme.breakpoints.up("sm")]: {
      marginLeft: theme.spacing(3),
      width: "auto"
    }
  },
  searchIcon: {
    width: theme.spacing(7),
    height: "100%",
    position: "absolute",
    pointerEvents: "none",
    display: "flex",
    alignItems: "center",
    justifyContent: "flex-end",
    marginLeft : theme.spacing(25)
  },
  inputRoot: {
    color: "inherit"
  },
  inputInput: {
    padding: theme.spacing(1, 1, 1, 7),
    transition: theme.transitions.create("width"),
    width: "100%",
    [theme.breakpoints.up("md")]: {
      width: 200
    }
  },
  sectionDesktop: {
    display: "none",
    [theme.breakpoints.up("md")]: {
      display: "flex"
    }
  },
  sectionMobile: {
    display: "flex",
    [theme.breakpoints.up("md")]: {
      display: "none"
    }
  }
}));

export default function PheaderLinks() {
  const [value, setValue] = useState(null);
  const classes = useStyles();
  const [users, setUsers] = useState([]);
  const defaultProps = {
    options: users,
    getOptionLabel: option => option.first_name + " " + option.last_name,
  };
  useState(() => getAllUsers().then(res => setUsers(res.results)));
  return (
    <List className={classes.list}>
      <ListItem className={classes.listItem}>
        <div className={classes.search}>
          <div className={classes.searchIcon}>
              <SearchIcon />
            </div>
          <Autocomplete
            id="free-solo-2-demo"
            {...defaultProps}
            onChange={event => setValue(event.target.value)}
            style={{ width: 300 }}
            renderInput={params => (
              <TextField
                {...params}
                variant="outlined"
                fullWidth
                value={value}
                InputProps={{ ...params.InputProps, type: "search" }}
              />
            )}
          />
        </div>
        <Link to="/articles" color="transparent">
          <Button className={classes.button} color="primary" target="_self">
            <CloudDownload className={classes.icons} /> Articles
          </Button>
        </Link>
        <Link to="/market" className={classes.button} color="transparent">
          <Button className={classes.button} color="primary" target="_self">
            <Assignment className={classes.icons} /> Market
          </Button>
        </Link>

        <CustomDropdown
          noLiPadding
          buttonText="Settings"
          color="primary"
          buttonProps={{
            className: classes.navLink,
            color: "primary"
          }}
          buttonIcon={Apps}
          dropdownList={[
            <Link to="/profile-page" key="" className={classes.dropdownLink}>
              Profile
            </Link>,
            <Link to="/edit-profile" className={classes.dropdownLink} key="">
              Edit Profile
            </Link>,
            <Link to="/Notifications" className={classes.dropdownLink} key="">
              Notifications
            </Link>,
            <Link
              to="/"
              className={classes.dropdownLink}
              key=""
              onClick={logout}
            >
              Logout
            </Link>
          ]}
        />
      </ListItem>
    </List>
  );
}
