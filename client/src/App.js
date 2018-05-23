import React, { Component } from 'react';
import { HashRouter, Route, Switch } from 'react-router-dom';
import './App.css';
// Styles
// Import Flag Icons Set
import 'flag-icon-css/css/flag-icon.min.css';
// Import Font Awesome Icons Set
import 'font-awesome/css/font-awesome.min.css';
// Import Simple Line Icons Set
import 'simple-line-icons/css/simple-line-icons.css';
// Import Main styles for this application
import './scss/style.css'
// import '../node_modules/@coreui/styles/scss/_dropdown-menu-right.scss';

// Containers
import { Full } from './containers';
// Pages
import { Login, Page404, Page500, Register } from './views/Pages';

// import { renderRoutes } from 'react-router-config';

const initialState = {
  route: 'home',
  isSignedIn: false,
  user: {
		name:"",
    email:"",
    phone_number:"",
		username:"",
		password:"",
		favorite1:"",
		favorite2:"",
		favorite3:""
    }
}

class App extends Component {

  constructor() {
    super();
    this.state = initialState;
  }

  loadUser = (data) => {
    this.setState({user: {
      name:data.name,
      email:data.email,
      phone_number:data.phone_number,
      username:data.username,
      password:data.password,
      favorite1:data.favorite1,
      favorite2:data.favorite2,
      favorite3:data.favorite3
    }})
  }

  onRouteChange = (route) => {
    if (route === 'signout') {
      this.setState(initialState)
    } else if (route === 'home') {
      this.setState({isSignedIn: true})
    }
    this.setState({route: route});
  }

  render() {
    const { isSignedIn, route } = this.state;
    return (
      <div className="App">
      { route === 'home'
          ? (
            <HashRouter>
              <Switch>
                <Route exact path="/login" name="Login Page" component={Login} />
                <Route exact path="/register" name="Register Page" component={Register} />
                <Route exact path="/404" name="Page 404" component={Page404} />
                <Route exact path="/500" name="Page 500" component={Page500} />
                <Route path="/" name="Home" component={Full} user={this.state.user}/>
              </Switch>
            </HashRouter>
          )
          : (
             route === 'signin'
             ? <Login loadUser={this.loadUser} onRouteChange={this.onRouteChange}/>
             : <Register loadUser={this.loadUser} onRouteChange={this.onRouteChange}/>
            )
        }
      </div>
    );
  }
}

export default App;
