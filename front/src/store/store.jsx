import { createStore, applyMiddleware, compose } from 'redux'
import thunk from 'redux-thunk'
import reducers from '../reducers'

const createStoreWithMiddleware = compose(
  applyMiddleware(thunk),
  window.devToolsExtension && window.devToolsExtension(),
)(createStore);
const store = createStoreWithMiddleware(reducers);

store.subscribe(() => {})
export default store;
