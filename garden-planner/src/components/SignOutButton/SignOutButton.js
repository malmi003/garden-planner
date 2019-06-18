import React from 'react';
import { Link } from "react-router-dom";


import { withFirebase } from '../Firebase';

const SignOutButton = ({ firebase }) => (
  <Link
    onClick={firebase.doSignOut}
    to="/"
    className="button is-dark">
    Sign Out
  </Link>
);

export default withFirebase(SignOutButton);