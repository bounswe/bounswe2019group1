import React, {useEffect, useState} from "react";
// @material-ui/core components
import {makeStyles} from "@material-ui/core/styles";
import {Link} from "react-router-dom";

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
import TextAnnotation from "../../components/Annotation/TextAnnotation";
import avatar from "assets/img/faces/marc.jpg";
import image from "assets/img/dollar-hd.jpg";
import Paper from "@material-ui/core/Paper";
import Typography from "@material-ui/core/Typography";
import Quote from "components/Typography/Quote.js";
import swal from "sweetalert";
import Image from 'react-image-resizer';

import {getArticleById} from "service/article.service.js";
import {getAnnotationsBySource} from "service/annotation.service.js";
import {
    createComment,
    listCommentByArticleId
} from "service/comment.service.js";
import {TokenAnnotator, TextAnnotator} from 'react-text-annotate'
import imageTest from "assets/img/examples/ppp2.jpg";

import articleThumbnail from "assets/img/examples/investor.jpeg";
import ButtonBase from "@material-ui/core/ButtonBase";
import ImageAnnotation from "../../components/Annotation/ImageAnnotation";


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
        textAlign: "left"
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
    const {...rest} = props;

    var article_id = String(props.history.location.pathname);
    article_id = Number(article_id.substr(article_id.lastIndexOf("/") + 1));

    const [annotations, setAnnotations] = useState([]);
    const [articleValues, setArticleValues] = useState({
        title: "",
        content: "",
        created_date: "",
        is_public: "",
        author: {},
        image: ""
    });

    const handleToUpdate = annotation => {
        var annotations = annotations;
        annotations.push(annotation);
        setAnnotations(
            annotations
        );
    };
    const [imageAnnotations, setImageAnnotations] = useState([]);
    const [textAnnotations, setTextAnnotations] = useState([]);

    useState(() => {
        getAnnotationsBySource("http://www.khajiittraders.tk/article/" + article_id).then(res => {
                setTextAnnotations(res.filter(a => a.target.type.toLowerCase() === "text").sort(function (a, b) {
                        return parseFloat(a.target.selector.refinedBy.start) - parseFloat(b.target.selector.refinedBy.start)
                    }
                ));
                setImageAnnotations(res.filter(a => a.target.type.toLowerCase() === "image"));
            }
        );
    });
    useState(() => {
        getArticleById(article_id).then(res =>
            setArticleValues({
                title: res.title,
                content: res.content,
                created_date: res.created_date,
                is_public: res.is_public,
                author: res.author,
                image: res.image
            })
        );
    });

    const [values, setValues] = useState({
        comments: [],
        addcomment: ""
    });

    useState(() =>
        listCommentByArticleId(article_id).then(res => setValues({comments: res.results}))
    );
    const items = [];
    if (values.comments) {
        for (const [index, value] of values.comments.entries()) {
            items.push(
                <div className={classes.typo}>
                    <div className={classes.note}>{value.user.created_date}</div>
                    <Link
                        to={{
                            pathname: "/user/" + value.user.id,
                            state: {id: value.user.id}
                        }}
                    >
                        <Quote
                            text={value.text}
                            author={
                                "Author: " + value.user.first_name + " " + value.user.last_name
                            }
                        />
                    </Link>
                </div>
            );
        }
    }

    const handleChange = event => {
        event.persist();
        setValues(oldValues => ({
            ...oldValues,
            [event.target.id]: event.target.value
        }));
    };
    const handleSubmit = event => {
        event.preventDefault();
        createComment({text: values.addcomment, article_id: article_id})
            .then(res => (res.status === 200 ? res : null))
            .then(function () {
                if (localStorage.getItem("currentUser")) {
                    props.history.push(`/article/${article_id}`);
                }
                //location.reload();
                window.location.reload();
                swal("Good job!", "Comment is successfully created.", "Success");
            })
            .catch(error => {
                swal("Oops: ", error.message, "error");
            });
    };

    var article_image = <GridItem xs={12} sm={12} md={8}>
        <GridContainer>
            <GridItem xs={12} sm={12} md={12}>
                <Paper className={classes.root} style={{padding: "32px"}}>
                    <ImageAnnotation
                        src={articleValues.image ? "http://35.163.120.227:8000" + articleValues.image : imageTest}
                        article_id={article_id}
                        showAnnotations={true}
                        annotations={imageAnnotations}
                        handleToUpdate={handleToUpdate}
                    />
                     {/* <Image
                            src={articleValues.image ? "http://35.163.120.227:8000" + articleValues.image : imageTest}
                            height={620}
                            width={620}
                            /> */}
                </Paper>
            </GridItem>
        </GridContainer>
    </GridItem>;

    let MapOrForm;

    MapOrForm = (
        <GridContainer justify="center">
            <GridItem xs={12} sm={12} md={12}>
                <Card profile>
                    <CardBody profile>
                        <GridContainer>
                            <GridItem xs={12} sm={12} md={12}>
                            </GridItem>
                        </GridContainer>
                        <Link
                            to={{
                                pathname: "/user/" + articleValues.author.id,
                                state: {id: articleValues.author.id}
                            }}
                        >
                            <h3 className={classes.cardTitle}>
                                Author: {articleValues.author.first_name}{" "}
                                {articleValues.author.last_name}
                            </h3>
                        </Link>
                        <h3 className={classes.cardTitle}>{articleValues.author.title}</h3>

                        <h5>
                            {" "}
                            <center></center>
                            {" "}
                        </h5>
                        <Paper className={classes.root}>
                            <h5>{articleValues.created_date}</h5>
                            <Typography variant="h5" component="h3">
                                <center>{articleValues.title}</center>
                            </Typography>
                            {article_image}
                           
                            <Typography component="p">
                                <TextAnnotation
                                    text={articleValues.content}
                                    article_id={article_id}
                                    showAnnotations={true}
                                    annotations={textAnnotations}
                                    handleToUpdate={handleToUpdate}
                                />
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
                        <Paper className={classes.root}>{items}</Paper>
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
                                <InputLabel style={{color: "#AAAAAA"}}>Be polite!</InputLabel>
                                <CustomInput
                                    id="addcomment"
                                    value={values.addcomment}
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
                        <Button color="primary" onClick={handleSubmit}>
                            Send comment
                        </Button>
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
                rightLinks={<PheaderLinks/>}
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
                <Footer whiteFont/>
            </div>
        </div>
    );
}
