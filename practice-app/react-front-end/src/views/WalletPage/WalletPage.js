import React, { useState } from "react";
// react plugin for creating charts
// @material-ui/core
import { makeStyles } from "@material-ui/core/styles";
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
import { getMyWallet} from "service/wallet.service.js";
import styles from "assets/jss/material-dashboard-react/views/dashboardStyle.js";

const useStyles = makeStyles(styles);

export default function Dashboard(props) {
  const classes = useStyles();
  const [values, setValues] = React.useState({
    amount: "0",
    temp_amount: ""
  });
  var user_id = String(props.history.location.pathname);

  user_id = Number(user_id.substr(user_id.lastIndexOf("/") + 1));
  const [followValues, setWalletValue] = useState({
    wealth: {},
    wealthCount: 0
    
  });
  useState(() =>
    getMyWallet().then(res =>
      setWalletValue(oldValues => ({
        ...oldValues,
        wealth: res.Wealth_USD,
        wealthCount: res.list
      }))
    )
  );
  const handleChange = prop => event => {
    setValues({ ...values, [prop]: event.target.value });
  };
  const handleSubmit = prop => event => {
    event.preventDefault();
    setValues({ ...values, [prop]: values.temp_amount });
  };
  console.log(values.wealth)
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
      />
      <Parallax small filter image={require("assets/img/dollar-hd.jpg")} />

      <GridContainer>
        <GridItem xs={6}>
          <Card>
            <CardHeader color="success" stats icon>
              <CardIcon color="success">
                <Store />
              </CardIcon>
              <p className={classes.cardCategory}>Total</p>
              <h3 className={classes.cardTitle}>${values.amount}</h3>
            </CardHeader>
            <CardFooter stats>
              <div className={classes.stats}>
                <DateRange />
                Just Updated
              </div>
            </CardFooter>
          </Card>
        </GridItem>
        <GridItem xs={4}>
          <Card>
            <CardHeader color="danger" stats icon>
              <CardIcon color="danger">
                <AttachMoney />
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
                tableData={[
                  ["1", "BTC", "0.00000000"],
                  ["2", "ETH", "0.00000000"],
                  ["3", "LTC", "0.00000000"],
                  ["4", "XAG", "0.00000000"],
                  ["5", "XAU", "0.00000000"],
                  ["6", "GOOGL", "0.00000000"],
                  ["7", "AAPL", "0.00000000"],
                  ["8", "GM", "0.00000000"],
                  ["9", "EUR", "0.00000000"],
                  ["10", "GBP", "0.00000000"],
                  ["11", "TRY", "0.00000000"],
                  ["12", "SPY", "0.00000000"],
                  ["13", "IVV", "0.00000000"],
                  ["14", "VTI", "0.00000000"]
                ]}
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
