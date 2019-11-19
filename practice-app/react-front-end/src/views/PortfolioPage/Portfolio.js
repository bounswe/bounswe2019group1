import React from "react";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
// core components
import GridItem from "components/Grid/GridItem.js";
import GridContainer from "components/Grid/GridContainer.js";
import Table from "components/Table/Table.js";
import Card from "components/Card/Card.js";
import CardHeader from "components/Card/CardHeader.js";
import CardBody from "components/Card/CardBody.js";
import FormLabel from '@material-ui/core/FormLabel';
import FormControl from '@material-ui/core/FormControl';
import FormGroup from '@material-ui/core/FormGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormHelperText from '@material-ui/core/FormHelperText';
import Checkbox from '@material-ui/core/Checkbox';
import Button from '@material-ui/core/Button';

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
    minWidth: 120,
  },
  selectEmpty: {
  },
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
    TRY_EUR : true,
    USD_JPY : true,
    GBP_TRY : true,
    EUR_USD : true,
    USD_TRY : true,
    EUR_TRY : true,
    GBP_USD : false,
    BTC_TRY : false,
    XMR_USD : false,
    GOOGL_USD : false,
    AGTHX_USD : false
 });

 const handleChange = name => event => {
   setState({ ...state, [name]: event.target.checked });
 };

 const { TRY_EUR, USD_JPY, GBP_TRY, EUR_USD, USD_TRY, EUR_TRY, GBP_USD, BTC_TRY, XMR_USD, GOOGL_USD, AGTHX_USD} = state;
 const error = [TRY_EUR, USD_JPY, GBP_TRY, EUR_USD, USD_TRY, EUR_TRY, GBP_USD, BTC_TRY, XMR_USD, GOOGL_USD, AGTHX_USD].filter(v => v).length !== 2;

  return (
    <GridContainer>
      <GridItem xs={12} sm={12} md={12}>
        <Card>
          <CardHeader color="primary">
            <h4 className={classes.cardTitleWhite}>Portfolio</h4>
            <p className={classes.cardCategoryWhite}>
              Equipments you follow
            </p>
          </CardHeader>
          <CardBody>
            <Table
              tableHeaderColor="primary"
              tableHead={["Equipment Symbol", "Equipment Long Name",  "Parity"]}
              tableData={[
                ["TRY/EUR", "Turkish Lira/Euro", "₺0.1574"],
                ["USD/JPY", "United States Dollar/Japanese Yen", "₺108.7480"],
                ["GBP/TRY", "British Pound Sterling/Turkish Lira", "₺7.4000"],
                ["EUR/USD", "Euro/United States Dollar", "₺1.1074"],
                ["USD/TRY", "United States Dollar/Turkish Lira",  "₺5.7182"],
                ["EUR/TRY", "Euro/Japanese Yen", "₺6.3329"]
              ]}
            />
          </CardBody>
        </Card>
      </GridItem>
      <GridItem xs={12} sm={12} md={12}>
        <Card plain>
          <CardHeader plain color="primary">
            <h4 className={classes.cardTitleWhite}>
              Edit Your Portfolio
            </h4>
            <p className={classes.cardCategoryWhite}>
              Follow/Unfollow Equipments
            </p>
          </CardHeader>
          <CardBody>
            <FormControl component="fieldset" className={classes.formControl}>
              <FormGroup>
                <FormControlLabel
                  control={<Checkbox checked={TRY_EUR} onChange={handleChange('TRY_EUR')} value="TRY_EUR" />}
                  label="TRY/EUR"
                />
                <FormControlLabel
                  control={<Checkbox checked={USD_JPY} onChange={handleChange('USD_JPY')} value="USD_JPY" />}
                  label="USD/JPY"
                />
                <FormControlLabel
                  control={
                    <Checkbox checked={GBP_TRY} onChange={handleChange('GBP_TRY')} value="GBP_TRY" />
                  }
                  label="GBP/TRY"
                />
                <FormControlLabel
                  control={<Checkbox checked={EUR_USD} onChange={handleChange('EUR_USD')} value="EUR_USD" />}
                  label="EUR/USD"
                />
                <FormControlLabel
                  control={<Checkbox checked={USD_TRY} onChange={handleChange('USD_TRY')} value="USD_TRY" />}
                  label="USD/TRY"
                />
                <FormControlLabel
                  control={
                    <Checkbox checked={EUR_TRY} onChange={handleChange('EUR_TRY')} value="EUR_TRY" />
                  }
                  label="EUR/TRY"
                />
                </FormGroup>
                </FormControl>
                <FormControl component="fieldset" className={classes.formControl}>
                <FormControlLabel
                  control={
                    <Checkbox checked={GBP_USD} onChange={handleChange('GBP_USD')} value="GBP_USD" />
                  }
                  label="GBP/USD"
                />
                <FormControlLabel
                  control={
                    <Checkbox checked={BTC_TRY} onChange={handleChange('BTC_TRY')} value="BTC_TRY" />
                  }
                  label="BTC/TRY"
                />
                <FormControlLabel
                  control={
                    <Checkbox checked={XMR_USD} onChange={handleChange('XMR_USD')} value="XMR_USD" />
                  }
                  label="XMR/USD"
                />
                <FormControlLabel
                  control={
                    <Checkbox checked={GOOGL_USD} onChange={handleChange('GOOGL_USD')} value="GOOGL_USD" />
                  }
                  label="GOOGL/USD"
                />
                <FormControlLabel
                  control={
                    <Checkbox checked={AGTHX_USD} onChange={handleChange('AGTHX_USD')} value="AGTHX_USD" />
                  }
                  label="AGTHX/USD"
                />
            </FormControl>
            <Button variant="contained" className={classes.button}>
              Apply changes
            </Button>
          </CardBody>

        </Card>
      </GridItem>
    </GridContainer>
  );
}
