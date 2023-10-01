import styles from './App.module.css';
import { LogIn } from './LogIn/LogIn';
import {useState} from 'react';

function App() {
  return (
    <div className={styles.App}>
      <LogIn/>
      <div className={styles.readYourBook}>Read your book</div>
    </div>
  );
}


export default App;
