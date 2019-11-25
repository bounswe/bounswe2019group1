import React, { useState } from "react";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import InputLabel from "@material-ui/core/InputLabel";
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
import PheaderLinks from "components/ProfileHeader/PheaderLinks";
import avatar from "assets/img/faces/marc.jpg";
import image from "assets/img/dollar-hd.jpg";
import Paper from "@material-ui/core/Paper";
import Typography from "@material-ui/core/Typography";
import Quote from "components/Typography/Quote.js";

import { getArticleById } from "service/article.service.js";

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

export default function Article(props) {
  const classes = useStyles();
  const { ...rest } = props;
  // const handleChange = event => {
  //   event.persist();
  //   setProfileValues(oldValues => ({
  //     ...oldValues,
  //     [event.target.id]: event.target.value
  //   }));
  // };
  var article_id = String(props.history.location.pathname);
  article_id = Number(article_id.substr(article_id.lastIndexOf("/") + 1));

  const [articleValues, setArticleValues] = useState({
    title: "",
    content: "",
    created_date: "",
    is_public: "",
    author: {}
  });
  useState(() => {
    getArticleById(article_id).then(res =>
      setArticleValues({
        title: res.title,
        content: res.content,
        created_date: res.created_date,
        is_public: res.is_public,
        author: res.author
      })
    );
  });
  let MapOrForm;

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
            <h3 className={classes.cardTitle}>
              {articleValues.author.first_name} {articleValues.author.last_name}
            </h3>
            <h3 className={classes.cardTitle}>{articleValues.author.title}</h3>

            <h5>
              {" "}
              <center> </center>{" "}
            </h5>
            <Paper className={classes.root}>
              <h5>{articleValues.created_date}</h5>
              <Typography variant="h5" component="h3">
                <center>{articleValues.title}</center>
              </Typography>
              <Typography component="p">{articleValues.content}</Typography>
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
                <Quote text="Amazing." author=" NY Times" />
              </div>
              <div className={classes.typo}>
                <div className={classes.note}></div>
                <Quote text="Unforgettable." author=" NY Times" />
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
                  value="biography"
                  formControlProps={{
                    fullWidth: true
                  }}
                  inputProps={{
                    multiline: true,
                    rows: 5
                  }}
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
