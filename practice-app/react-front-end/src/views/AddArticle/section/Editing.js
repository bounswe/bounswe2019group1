import React from "react";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";

// @material-ui/icons

// core components
import GridContainer from "components/Grid/GridContainer.js";
import GridItem from "components/Grid/GridItem.js";
import CustomInput from "components/CustomInput/CustomInput.js";
import Button from "components/CustomButtons/Button.js";

import styles from "assets/jss/material-kit-react/views/landingPageSections/workStyle.js";

const useStyles = makeStyles(styles);

export default function Adding() {
  const classes = useStyles();
  return (
    <div className={classes.section}>
      <GridContainer justify="center">
        <GridItem cs={12} sm={12} md={8}>
          <h2 className={classes.title}>Edit your article</h2>
          <h4 className={classes.description}>
            The articles you share on this page can be read by the people who
            follow you.
          </h4>
          <form>
            <GridContainer>
              <GridItem xs={12} sm={12} md={6}>
                <CustomInput
                  labelText="Title"
                  id="name"
                  formControlProps={{
                    fullWidth: true
                  }}
                  inputProps={{
                    defaultValue: "The Key Traits of Patient and Successful Investors"
                  }}
                />
              </GridItem>

              <CustomInput
                labelText="Body of the article"
                id="message"
                formControlProps={{
                  fullWidth: true,
                  className: classes.textArea
                }}
                inputProps={{
                  multiline: true,
                  rows: 10,
                  defaultValue: "According to Entrepreneur Network partner Phil Town," +
                   "one of the most valuable traits an investor can have is patience. "+
                   "If you are a patient investor and decide on good businesses, Town"+
                   "says there is virtually no scenario where you will not make money. "+
                   "Here are some of the traits of patient investors ... and thus, investors who will be prepared for retirement:"+
                   "They have long-term goals."+
                   "They are slow to buy. Most times, the people who hold off on buying companies are also taking time to do their research. Town recommends investing in no more than 10 companies -- like you're hoping to reach the limit on a punch card with 10 slots."+
                   "They are confident. These investors are sure they will make money on their investments, and act accordingly."+
                   "They are rational. Fear and greed are two emotions that throw a previously smart investor onto the wrong track; emotional influence can be an investor downfall. In this sense, patient investors often prove to also be rational investors."+
                   "Click the video to hear more from Phil Town about patient investing."+
                   "Related:How to Approach Your Finances If You Want to Retire Stress-Free"+
                   "Entrepreneur Network  is a premium video network providing entertainment,"+
                   "ewitducation and inspiration from successful entrepreneurs and thought leaders."+
                   "We provide expertise and opportunities to accelerate brand growth and effectively"+
                   "monetize video and audio content distributed across all digital platforms for the business genre."
                }}
              />
              <GridContainer xs={12} justify="center">
                <GridItem xs={12} sm={12} md={4} className={classes.textCenter}>
                  <Button color="primary">Edit</Button>
                </GridItem>
              </GridContainer>
            </GridContainer>
          </form>
        </GridItem>
      </GridContainer>
    </div>
  );
}
