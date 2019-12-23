import React, {useState} from "react";
// @material-ui/core components
import {makeStyles} from "@material-ui/core/styles";
// core components
import Header from "components/Header/Header.js";
import PheaderLinks from "components/ProfileHeader/PheaderLinks";
import Parallax from "components/Parallax/Parallax.js";
import classNames from "classnames";
import GridItem from "components/Grid/GridItem.js";
import GridContainer from "components/Grid/GridContainer.js";
import Table from "components/Table/Table.js";
import Card from "components/Card/Card.js";
import CardHeader from "components/Card/CardHeader.js";
import CardBody from "components/Card/CardBody.js";
import FormLabel from "@material-ui/core/FormLabel";
import FormControl from "@material-ui/core/FormControl";
import FormGroup from "@material-ui/core/FormGroup";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import FormHelperText from "@material-ui/core/FormHelperText";
import Checkbox from "@material-ui/core/Checkbox";
import Button from "@material-ui/core/Button";
import {getTEValue} from "../../service/equipment.service";
//import { AnnotationCallout } from "react-annotation";

const styles = {
    cardCategoryWhite: {
        "&,& a,& a:hover,& a:focus": {
            color: "rgba(255,255,255,.62)",
            margin: "0",
            fontSize: "14px",
            marginTop: "0",
            marginBottom: "0"
        },
        "& a,& a:hover,& a:focus": {
            color: "#FFFFFF"
        }
    },
    formControl: {
        minWidth: 120
    },
    selectEmpty: {},
    cardTitleWhite: {
        color: "#FFFFFF",
        marginTop: "0px",
        minHeight: "auto",
        fontWeight: "300",
        fontFamily: "'Roboto', 'Helvetica', 'Arial', sans-serif",
        marginBottom: "3px",
        textDecoration: "none",
        "& small": {
            color: "#777",
            fontSize: "65%",
            fontWeight: "400",
            lineHeight: "1"
        }
    }
};

const useStyles = makeStyles(styles);

export default function TableList() {
    const classes = useStyles();

    const [state, setState] = React.useState({
        TRY_EUR: true,
        USD_JPY: true,
        GBP_TRY: true,
        EUR_USD: true,
        USD_TRY: true,
        EUR_TRY: true,
        GBP_USD: true,
        BTC_TRY: true,
        XMR_USD: true,
        GOOGL_USD: true,
        AGTHX_USD: true
    });

    const [list, setList] = React.useState([]);
    var initial_list = [["EUR/USD", "Euro/US Dollar", "0.1574"],
        ["GBP/USD", "British Pound Sterling/US Dollar", "108.7480"],
        ["TRY/USD", "Turkish Lira/US Dollar", "7.4000"],
        ["XAG/USD", "Silver/US Dollar", "1.1074"],
        ["XAU/USD", "Gold/US Dollar", "5.7182"],
        ["BTC/USD", "Bitcoin/US Dollar", "6.3329"],
        ["ETH/USD", "Etherium/US Dollar", "1.2952"],
        ["LTC/USD", "Litecoin/US Dollar", "46235.9183"],
        ["GOOGL/USD", "Google/US Dollar", "58.0200"],
        ["AAPL/USD", "Apple/US Dollar", "1296.4000"],
        ["GM/USD", "General Motors / US Dollar", "52.4900"],
        ["SPY/USD", "Standard & Poor's Depozitary / US Dollar", "1232"],
        ["IVV/USD", "Ishares S&P 500 / US Dollar", "123213"],
        ["VTI/USD", "Vanguard Total Stock Market Index Fund / US Dollar", "12312"]];
    useState(() => {
        getTEValue("EUR").then(res => {
                initial_list[0][2] = res.te_value;
                setList(initial_list);
                return res;
            }
        );
    });
    useState(() => {
        getTEValue("GBP").then(res => {
                initial_list[1][2] = res.te_value;
                setList(initial_list);
                return res;
            }
        );
    });
    useState(() => {
        getTEValue("TRY").then(res => {
                initial_list[2][2] = res.te_value;
                setList(initial_list);
                return res;
            }
        );
    });
    useState(() => {
            getTEValue("XAG").then(res => {
                    initial_list[3][2] = res.te_value;
                    setList(initial_list);
                    return res;
                }
            );
        }
    );
    useState(() => {
        getTEValue("XAU").then(res => {
                initial_list[4][2] = res.te_value;
                setList(initial_list);
                return res;
            }
        );
    });
    useState(() => {
        getTEValue("BTC").then(res => {
                initial_list[5][2] = res.te_value;
                setList(initial_list);
                return res;
            }
        );
    });
    useState(() => {
        getTEValue("ETH").then(res => {
                initial_list[6][2] = res.te_value;
                setList(initial_list);
                return res;
            }
        );
    });
    useState(() => {
        getTEValue("LTC").then(res => {
                initial_list[7][2] = res.te_value;
                setList(initial_list);
                return res;
            }
        );
    });
    useState(() => {
        getTEValue("GOOGL").then(res => {
                initial_list[8][2] = res.te_value;
                setList(initial_list);
                return res;
            }
        );
    });
    useState(() => {
        getTEValue("AAPL").then(res => {
                initial_list[9][2] = res.te_value;
                setList(initial_list);
                return res;
            }
        );
    });
    useState(() => {
        getTEValue("GM").then(res => {
                initial_list[10][2] = res.te_value;
                setList(initial_list);
                return res;
            }
        );
    });
    useState(() => {
        getTEValue("SPY").then(res => {
                initial_list[11][2] = res.te_value;
                setList(initial_list);
                return res;
            }
        );
    });
    useState(() => {
        getTEValue("IVV").then(res => {
                initial_list[12][2] = res.te_value
                setList(initial_list);
                return res;
            }
        );
    });
    useState(() => {
        getTEValue("VTI").then(res => {
                initial_list[13][2] = res.te_value;
                setList(initial_list);
                return res;
            }
        );
    });

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
            />
            <Parallax small filter image={require("assets/img/dollar-hd.jpg")}/>
            <div className={classNames(classes.main, classes.mainRaised)}>
                <div>
                    <div className={classes.container}>
                        <GridContainer justify="center">
                            <GridItem xs={12} sm={12} md={20}>
                                <div className={classes.root}>
                                    <GridContainer>
                                        <GridItem item xs={120} sm container>
                                            <Card>
                                                <CardHeader color="primary">
                                                    <h4 className={classes.cardTitleWhite}>Market</h4>
                                                    <p className={classes.cardCategoryWhite}>
                                                        Equipments that is listed
                                                    </p>
                                                </CardHeader>
                                                <CardBody>
                                                    <Table
                                                        tableHeaderColor="primary"
                                                        tableHead={[
                                                            "Equipment Symbol",
                                                            "Equipment Long Name",
                                                            "Parity"
                                                        ]}
                                                        tableData={list}
                                                    />
                                                </CardBody>
                                            </Card>
                                        </GridItem>
                                        <GridItem xs={12} sm={12} md={12}>
                                            <Card plain>

                                                <CardBody>
                                                </CardBody>
                                            </Card>
                                        </GridItem>
                                    </GridContainer>
                                </div>
                            </GridItem>
                        </GridContainer>
                    </div>
                </div>
            </div>
        </div>
    );
}
