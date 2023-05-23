import React, { useEffect, useState } from "react";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
// core components
import GridItem from "components/Grid/GridItem.js";
import GridContainer from "components/Grid/GridContainer.js";
import Table from "components/Table/Table.js";
import Card from "components/Card/Card.js";
import CardHeader from "components/Card/CardHeader.js";
import CardBody from "components/Card/CardBody.js";

const styles = {
  cardCategoryWhite: {
    "&,& a,& a:hover,& a:focus": {
      color: "rgba(255,255,255,.62)",
      margin: "0",
      fontSize: "14px",
      marginTop: "0",
      marginBottom: "0",
    },
    "& a,& a:hover,& a:focus": {
      color: "#FFFFFF",
    },
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
      lineHeight: "1",
    },
  },
};

const useStyles = makeStyles(styles);

export default function advSearch() {
  const classes = useStyles();
  const [search, setSearch] = useState(null);
  const [newSearch, setNewSearch] = useState(false);

  const updateSearches = () => {
    setNewSearch(true);
  };

  const searchResults = (e) => {
    console.log("use effect has occurred");
    fetch("https://brainstormbackend.herokuapp.com/search/{7}/{" + e + "}")
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        console.log(data);
        setSearch(data);
      });
  };
  return (
    <GridContainer>
      <GridItem xs={12} sm={12} md={12}>
        <Card>
          <CardHeader color="danger">
            <h4 className={classes.cardTitleWhite}>Search Results</h4>
            <p className={classes.cardCategoryWhite}>
              View your search results below.
            </p>
          </CardHeader>
          <CardBody>
            <Table
              tableHeaderColor="primary"
              tableHead={["Found"]}
              tableData={[["A"], ["B"], ["C"], ["A"], ["B"], ["A"]]}
            />
          </CardBody>
        </Card>
      </GridItem>
    </GridContainer>
  );
}
