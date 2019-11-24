import React, { useState } from "react";

// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";

// @material-ui/icons

// core components
import GridContainer from "components/Grid/GridContainer.js";
import GridItem from "components/Grid/GridItem.js";
import CustomInput from "components/CustomInput/CustomInput.js";
import Button from "components/CustomButtons/Button.js";
import styles from "assets/jss/material-kit-react/views/landingPageSections/workStyle.js";
import swal from "sweetalert";
import { createArticle } from "service/article.service.js";
const useStyles = makeStyles(styles);

export default function Adding(props) {
  const classes = useStyles();
  const [values, setValues] = React.useState({
    title: "",
    content: "",
    is_public: true
  });
  const handleSubmit = event => {
    event.preventDefault();
    createArticle(values)
      .then(res =>
        res.status === 200 ? props.history.push("/articles") : null
      )
      .then(function() {
        props.history.push("/articles");
        swal("Good job!", "Created the article successfully.", "Success");
      })
      .catch(error => {
        swal("Oops: ", error.message, "error");
      });
  };
  const handleChange = event => {
    event.persist();
    setValues(oldValues => ({
      ...oldValues,
      [event.target.id]: event.target.value
    }));
  };
  return (
    <div className={classes.section}>
      <GridContainer justify="center">
        <GridItem cs={12} sm={12} md={8}>
          <h2 className={classes.title}>Share with us</h2>
          <h4 className={classes.description}>
            The articles you share on this page can be read by the people who
            follow you.
          </h4>
          <form>
            <GridContainer>
              <GridItem xs={12} sm={12} md={6}>
                <CustomInput
                  labelText="Title"
                  id="title"
                  value={values.title}
                  formControlProps={{
                    fullWidth: true
                  }}
                  onChange={handleChange}
                />
              </GridItem>

              <CustomInput
                labelText="Body of the article"
                id="content"
                value={values.content}
                formControlProps={{
                  fullWidth: true,
                  className: classes.textArea
                }}
                inputProps={{
                  multiline: true,
                  rows: 10
                }}
                onChange={handleChange}
              />
              <GridContainer xs={12} justify="center">
                <GridItem xs={12} sm={12} md={4} className={classes.textCenter}>
                  <Button color="primary" onClick={handleSubmit}>Submit</Button>
                </GridItem>
              </GridContainer>
            </GridContainer>
          </form>
        </GridItem>
      </GridContainer>
    </div>
  );
}
