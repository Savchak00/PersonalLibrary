import styles from './App.module.css';
import { LogIn } from './LogIn/LogIn';
import {Library} from './Library/Library';
import {Route, Routes, BrowserRouter} from 'react-router-dom';

function App() {
  return (
    <div className={styles.App}>
      <BrowserRouter>
        <Routes>
          <Route exact path  = '/'  element={<LogIn/>}></Route>
          <Route path  = '/library' element={<Library/>}></Route>
        </Routes>
      </BrowserRouter>
      <div className={styles.readYourBook}>Read your book</div>
    </div>
  );
}


export default App;
