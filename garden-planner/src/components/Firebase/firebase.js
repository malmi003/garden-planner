import app from "firebase/app";
import 'firebase/auth';

const config = {
    apiKey: "AIzaSyAyNFIHHBOK2tVkcY1yASU8tNxCqyvh3lw",
    authDomain: "gardenplanner-11ece.firebaseapp.com",
    databaseURL: "https://gardenplanner-11ece.firebaseio.com",
    projectId: "gardenplanner-11ece",
    storageBucket: "gardenplanner-11ece.appspot.com",
    messagingSenderId: "206839447911",
    appId: "1:206839447911:web:7cfedeea1496ceac"
  };

  class Firebase {
    constructor() {
      app.initializeApp(config);
      this.auth = app.auth();
    }

      // *** Auth API ***

  doCreateUserWithEmailAndPassword = (email, password) =>
  this.auth.createUserWithEmailAndPassword(email, password);
  
  doSignInWithEmailAndPassword = (email, password) =>
  this.auth.signInWithEmailAndPassword(email, password);

  doSignOut = () => this.auth.signOut();
}
  
  export default Firebase;