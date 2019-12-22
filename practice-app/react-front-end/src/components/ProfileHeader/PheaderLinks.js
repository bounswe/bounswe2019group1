import React, { useState } from "react";
// react components for routing our app without refresh
import { Link } from "react-router-dom";

// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import { getAllUsers } from "service/user.service.js";
import SearchIcon from "@material-ui/icons/Search";
import Autosuggest from 'react-autosuggest';

// @material-ui/icons
import { Apps, CloudDownload, Assignment, AttachMoney } from "@material-ui/icons";
// core components
import CustomDropdown from "components/CustomDropdown/CustomDropdown.js";
import Button from "components/CustomButtons/Button.js";
import { logout } from "service/authentication.service.js";
import { fade } from "@material-ui/core/styles";




const handleSubmit = event => {
  event.preventDefault();
  
};

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
    width: theme.spacing(16),
    height: "10%",
    position: "absolute",
    display: "flex",
    alignItems: "flex-end",
    justifyContent: "flex-end",
    marginLeft : theme.spacing(5)
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
        
        <Searchh/>
            
          
            <Link to="/search-results">
              <div className={classes.searchIcon}>
              <SearchIcon />
              </div>
            </Link>
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
        <Link to="/equipments" color="transparent">
          <Button className={classes.button} color="primary" target="_self">
            <AttachMoney className={classes.icons} /> Parity
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


// Imagine you have a list of languages that you'd like to autosuggest.
const languages = [
  {
    name: 'mete',
    year: 1972
  },
  {
    name: 'ufuk',
    year: 1972
  },
  {
    name: 'altay',
    year: 1972
  },

  {
    name: 'emirhan',
    year: 2012
  },
  {
    name: 'abdullah',
    year: 1972
  },
  {
    name: 'eray',
    year: 1972
  },
  {
    name: 'irem',
    year: 1972
  },
  {
    name: 'ceren',
    year: 1972
  },
  {
    name: 'omer',
    year: 1972
  },
  {
    name: 'ilker',
    year: 1972
  },
  
];

// Teach Autosuggest how to calculate suggestions for any given input value.
const getSuggestions = value => {
  const inputValue = value.trim().toLowerCase();
  const inputLength = inputValue.length;

  return inputLength === 0 ? [] : languages.filter(lang =>
    lang.name.toLowerCase().slice(0, inputLength) === inputValue
  );
};

// When suggestion is clicked, Autosuggest needs to populate the input
// based on the clicked suggestion. Teach Autosuggest how to calculate the
// input value for every given suggestion.
const getSuggestionValue = suggestion => suggestion.name;

// Use your imagination to render suggestions.
const renderSuggestion = suggestion => (
  <div>
   
    {suggestion.name}
    
  </div>
);

class Searchh extends React.Component {
  constructor() {
    super();

    // Autosuggest is a controlled component.
    // This means that you need to provide an input value
    // and an onChange handler that updates this value (see below).
    // Suggestions also need to be provided to the Autosuggest,
    // and they are initially empty because the Autosuggest is closed.
    this.state = {
      value: '',
      suggestions: []
    };
  }

  onChange = (event, { newValue }) => {
    this.setState({
      value: newValue
    });
  };
  
  // Autosuggest will call this function every time you need to update suggestions.
  // You already implemented this logic above, so just use it.
  onSuggestionsFetchRequested = ({ value }) => {
    this.setState({
      suggestions: getSuggestions(value)
    });
  };


  // Autosuggest will call this function every time you need to clear suggestions.
  onSuggestionsClearRequested = () => {
    this.setState({
      suggestions: []
    });
  };

  render() {
    const { value, suggestions } = this.state;
    
    // Autosuggest will pass through all these props to the input.
    const inputProps = {
      placeholder: 'Type to search',
      value,
      onChange: this.onChange
    };
    

    // Finally, render it!
    return (
      <Autosuggest
        suggestions={suggestions}
        onSuggestionsFetchRequested={this.onSuggestionsFetchRequested}
        onSuggestionsClearRequested={this.onSuggestionsClearRequested}
        getSuggestionValue={getSuggestionValue}
        renderSuggestion={renderSuggestion}
        inputProps={inputProps}
      />
      
    );
  }
}