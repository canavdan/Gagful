/* eslint-disable import/no-named-as-default */
import React from 'react'
import { BrowserRouter, Route, Switch } from 'react-router-dom'
import Home from './components/Home'
import Header from './components/Header'
import ProfileHome from './components/Profile/ProfileHome'

const Router = () => (
  <BrowserRouter>
    <Switch>
      <Route path="/:postType" component={Home} />
      <Route path="/" component={Home} />
      <Route path="/c/:categoryName" component={Home} />
      <Route path="/header" component={Header} />
      <Route path="/u/:username" component={ProfileHome} />
      <Switch path="/*" component={() => 'NOT FOUND'} />
    </Switch>
  </BrowserRouter>
)

export default Router
