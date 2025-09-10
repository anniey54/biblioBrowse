import { useEffect, useState } from "react";
import Logo from "./logo";
import styles from './Navbar.module.css';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars, faXmark } from "@fortawesome/free-solid-svg-icons";

const Navbar = () => {
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);


  return (
    <>
      <nav className={styles.navbar}>
          <Logo />
          {/* desktop */}
          <div className={styles.desktop}>
            <Link to={'/'}>Home</Link>
            <Link to={'/books'}>Books</Link>
            <Link to={'/collections'}>Collections</Link>
            <Link to={'/signin'}>Sign in</Link>
          </div>
          {/* mobile */}
          <div className={styles.mobile}>
            <button className={styles.mobile} onClick={() => {setIsMobileMenuOpen(!isMobileMenuOpen)}}>
              {isMobileMenuOpen 
                ? <FontAwesomeIcon icon={faXmark} size="xl"/>
                : <FontAwesomeIcon icon={faBars} size="xl"/>
              }
            </button>
          </div>
      </nav>
      {isMobileMenuOpen &&
        <div className={styles.mobileMenu}>
          <Link to={'/'} onClick={() => {setIsMobileMenuOpen(false)}}>Home</Link>
          <Link to={'/books'} onClick={() => {setIsMobileMenuOpen(false)}}>Books</Link>
          <Link to={'/collections'} onClick={() => {setIsMobileMenuOpen(false)}}>Collections</Link>
          <Link to={'/signin'} onClick={() => {setIsMobileMenuOpen(false)}}>Sign in</Link>
        </div>
      }
    </>
  )
}

export default Navbar