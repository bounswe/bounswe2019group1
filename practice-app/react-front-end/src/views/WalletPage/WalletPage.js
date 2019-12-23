import React, {useState} from "react";
// react plugin for creating charts
// @material-ui/core
import {makeStyles} from "@material-ui/core/styles";
import Icon from "@material-ui/core/Icon";
// @material-ui/icons
import Header from "components/Header/Header.js";
import PheaderLinks from "components/ProfileHeader/PheaderLinks";

import Store from "@material-ui/icons/Store";
import AttachMoney from "@material-ui/icons/AttachMoney";
import OutlinedInput from "@material-ui/core/OutlinedInput";
import InputLabel from "@material-ui/core/InputLabel";
import InputAdornment from "@material-ui/core/InputAdornment";
import FormControl from "@material-ui/core/FormControl";
import Parallax from "components/Parallax/Parallax.js";
import DateRange from "@material-ui/icons/DateRange";

// core components
import GridItem from "components/Grid/GridItem.js";
import GridContainer from "components/Grid/GridContainer.js";
import Table from "components/Table/Table.js";
import Card from "components/Card2/Card";
import CardHeader from "components/Card2/CardHeader.js";
import CardIcon from "components/Card2/CardIcon.js";
import CardBody from "components/Card2/CardBody.js";
import CardFooter from "components/Card2/CardFooter.js";
import {getMyWallet} from "service/wallet.service.js";
import styles from "assets/jss/material-dashboard-react/views/dashboardStyle.js";
import {addFund} from "../../service/wallet.service";
import swal from "sweetalert";

const useStyles = makeStyles(styles);

export default function Dashboard(props) {
    const classes = useStyles();
    const [values, setValues] = React.useState({
        amount: "0",
        temp_amount: ""
    });
    var user_id = String(props.history.location.pathname);

    user_id = Number(user_id.substr(user_id.lastIndexOf("/") + 1));
    const [walletValues, setWalletValue] = useState({
        USD: "198527.4300000000",
        Sent_USD: "200000.0000000000",
        Wealth_USD: "200000.0000000000",
        BTC: "0.0000000000",
        ETH: "11.0000000000",
        LTC: "0.0000000000",
        XAG: "0.0000000000",
        XAU: "0.0000000000",
        GOOGL: "0.0000000000",
        AAPL: "0.0000000000",
        GM: "0.0000000000",
        EUR: "0.0000000000",
        GBP: "0.0000000000",
        TRY: "0.0000000000",
        SPY: "0.0000000000",
        IVV: "0.0000000000",
        VTI: "0.0000000000",
    });
    const [tableList, setTableList] = useState(
        [
            ["1", "USD", "100000"],
            ["2", "BTC", "100000"],
            ["3", "ETH", "100000"],
            ["4", "LTC", "100000"],
            ["5", "XAG", "100000"],
            ["6", "XAU", "100000"],
            ["7", "GOOGL", "100000"],
            ["8", "AAPL", "100000"],
            ["9", "GM", "100000"],
            ["10", "EUR", "0"],
            ["11", "GBP", "0"],
            ["12", "TRY", "0"],
            ["13", "SPY", "0"],
            ["14", "IVV", "0"],
            ["15", "VTI", "0"]
        ]
    );
    useState(() =>
        getMyWallet().then(res => {
                setWalletValue(oldValues => ({
                        ...oldValues,
                        Sent_USD: res.Sent_USD,
                        Wealth_USD: res.Wealth_USD,
                        USD: res.USD,
                        BTC: res.BTC,
                        ETH: res.ETH,
                        LTC: res.LTC,
                        XAG: res.XAG,
                        XAU: res.XAU,
                        GOOGL: res.GOOGL,
                        AAPL: res.AAPL,
                        GM: res.GM,
                        EUR: res.EUR,
                        GBP: res.GBP,
                        TRY: res.TRY,
                        SPY: res.SPY,
                        IVV: res.IVV,
                        VTI: res.VTI,
                    }
                ));
                setTableList(
                    [
                        ["1", "USD", String(res.USD)],
                        ["2", "BTC", String(res.BTC)],
                        ["3", "ETH", String(res.ETH)],
                        ["4", "LTC", String(res.LTC)],
                        ["5", "XAG", String(res.XAG)],
                        ["6", "XAU", String(res.XAU)],
                        ["7", "GOOGL", String(res.GOOGL)],
                        ["8", "AAPL", String(res.AAPL)],
                        ["9", "GM", String(res.GM)],
                        ["10", "EUR", String(res.EUR)],
                        ["11", "GBP", String(res.GBP)],
                        ["12", "TRY", String(res.TRY)],
                        ["13", "SPY", String(res.SPY)],
                        ["14", "IVV", String(res.IVV)],
                        ["15", "VTI", String(res.VTI)]
                    ]
                );
                return res;
            }
        )
    );

    const handleChange = prop => event => {
        setValues({...values, [prop]: event.target.value});
    };
    const handleSubmit = prop => event => {
        event.preventDefault();
        addFund(values.temp_amount).then(res => (res.status === 200 ? res : null))
            .then(() => {
                window.location.reload();
                swal("Good job!","A fund is successfully added to your wallet.");
            })
            .catch(error => {
                swal("Oops: ", error.message, "error");
            });
    };
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

            <GridContainer>
                <GridItem xs={6}>
                    <Card>
                        <CardHeader color="success" stats icon>
                            <CardIcon color="success">
                                <Store/>
                            </CardIcon>
                            <p className={classes.cardCategory}>Total</p>
                            <h3 className={classes.cardTitle}>${walletValues.Wealth_USD}</h3>
                        </CardHeader>
                        <CardFooter stats>
                            <div className={classes.stats}>
                                <DateRange/>
                                Just Updated
                            </div>
                        </CardFooter>
                    </Card>
                </GridItem>
                <GridItem xs={4}>
                    <Card>
                        <CardHeader color="danger" stats icon>
                            <CardIcon color="danger">
                                <AttachMoney/>
                            </CardIcon>
                            <div>
                                <Icon onClick={handleSubmit("amount")} color="primary">
                                    add_circle
                                </Icon>
                            </div>

                            <p className={classes.cardCategory}>Add Funds</p>
                            <h3 className={classes.cardTitle}>
                                <FormControl
                                    fullWidth
                                    className={classes.margin}
                                    variant="outlined"
                                >
                                    <InputLabel htmlFor="outlined-adornment-amount">
                                        Amount
                                    </InputLabel>
                                    <OutlinedInput
                                        id="outlined-adornment-amount"
                                        value={values.temp_amount}
                                        onChange={handleChange("temp_amount")}
                                        startAdornment={
                                            <InputAdornment position="start">$</InputAdornment>
                                        }
                                        labelWidth={60}
                                    />
                                </FormControl>
                            </h3>
                        </CardHeader>
                        <CardFooter stats></CardFooter>
                    </Card>
                </GridItem>
            </GridContainer>

            <GridContainer>
                <GridItem xs={12} sm={12} md={6}>
                    <Card>
                        <CardHeader color="warning">
                            <h4 className={classes.cardTitleWhite}>Current Wealth</h4>
                            <p className={classes.cardCategoryWhite}>
                                Updated on 26th November, 2019
                            </p>
                        </CardHeader>
                        <CardBody>
                            <Table
                                tableHeaderColor="warning"
                                tableHead={["ID", "Currency", "Amount"]}
                                tableData={tableList ? tableList : []}
                            />
                        </CardBody>
                    </Card>
                </GridItem>
            </GridContainer>
        </div>
    );
}


/*

<GridItem xs={8}>
        <CustomTabs
          title="Transactions:"
          headerColor="primary"
          tabs={[
            {
              tabName: "Today",

              tabIcon: PlaylistAddCheck,
              tabContent: (
                <Tasks
                  checkedIndexes={[0, 3]}
                  tasksIndexes={[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]}
                  tasks={bugs}
                />
              )
            },

            {
              tabName: "Last week",
              tabIcon: Replay,
              tabContent: (
                <Tasks
                  checkedIndexes={[0]}
                  tasksIndexes={[0, 1]}
                  tasks={website}
                />
              )
            },
            {
              tabName: "Last month",
              tabIcon: Replay30,
              tabContent: (
                <Tasks
                  checkedIndexes={[1]}
                  tasksIndexes={[0, 1, 2]}
                  tasks={server}
                />
              )
            }
          ]}
          />
        </GridItem>


*/
