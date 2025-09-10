import { useEffect, useState } from "react";
import Logo from "./logo";
import styles from './Navbar.module.css';
import { Link } from 'react-router-dom';

const Navbar = () => {
  return (
    <nav className={styles.navbar}>
        <Logo />
        {/* desktop */}
        <div className={styles.desktop}>
          <Link to={'/'}>Home</Link>
          <Link to={'/books'}>Books</Link>
          <Link to={'/collections'}>Collections</Link>
          <Link to={'/signin'}>Sign in</Link>
        </div>
    </nav>
  )
}

export default Navbar