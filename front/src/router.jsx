/* eslint-disable import/no-named-as-default */
import React from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import { Switch } from 'react-router';
import Home from './components/Home';
import Header from './components/Header';
import ProfileHome from './components/Profile/ProfileHome';

const Router = () => (
  <BrowserRouter>
    <Switch>
      <Route path="/" component={Home} />
      <Route path="/header" component={Header} />
      <Route path="/u/:username" component={ProfileHome} />
      <Switch path="/*" component={() => 'NOT FOUND'} />
    </Switch>
  </BrowserRouter>
);

export default Router;
