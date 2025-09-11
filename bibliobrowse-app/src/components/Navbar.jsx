import { useState } from "react";
import Logo from "./logo";
import styles from './Navbar.module.css';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars, faXmark, faChevronDown, faChevronUp } from "@fortawesome/free-solid-svg-icons";
import { Menu, MenuItem, MenuButton } from '@szhsin/react-menu';
import '@szhsin/react-menu/dist/index.css';
import '@szhsin/react-menu/dist/transitions/zoom.css';

const Navbar = () => {
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);
  const [isAccountMenuOpen, setIsAccountMenuOpen] = useState(false);
  const user = {
    name: 'UserName 123',
    profileURL: 'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
    isAdmin: true
  }

  /* reset both menus in mobile navbar to false */
  const resetMobileMenus = () => {
    setIsMobileMenuOpen(false); 
    setIsAccountMenuOpen(false);
  }

  return (
    <>
      <nav className={styles.navbar}>
          <Logo />
          {/* desktop */}
          <div className={styles.desktop}>
            <Link to={'/'}>Home</Link>
            <Link to={'/books'}>Books</Link>
            <Link to={'/collections'}>Collections</Link>

            {/* signin or user profile and menu */}
            {Object.keys(user).length > 0
              ? <Menu 
                  transition
                  menuButton={<MenuButton>
                    <div className={styles.profile}>
                      <img src={user.profileURL} />
                      <p>{user.name}</p>
                      <FontAwesomeIcon icon={faChevronDown} size="lg"/>
                    </div>
                  </MenuButton>}
                >
                <MenuItem className={styles.menuItem} href='/'>Profile</MenuItem>
                <MenuItem className={styles.menuItem} href='/books'>Create Collection</MenuItem>
                { user.isAdmin && <MenuItem className={styles.menuItem} href='/'>Create Book</MenuItem> }
                <MenuItem className={styles.menuItem}>Logout</MenuItem>
              </Menu>
              : <Link to={'/signin'}>Sign in</Link>
            }
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
        <div className={styles.mobileNav}>
          <Link to={'/'} onClick={() => {resetMobileMenus()}}>Home</Link>
          <Link to={'/books'} onClick={() => {resetMobileMenus()}}>Books</Link>
          <Link to={'/collections'} onClick={() => {resetMobileMenus()}}>Collections</Link>

          {/* signin or user profile and menu */}
          {Object.keys(user).length > 0
              ? <div className={styles.mobileAccountMenu}>
                  <div className={styles.accountName} onClick={() => {setIsAccountMenuOpen(!isAccountMenuOpen)}}>
                    <p>{user.name}</p>
                    <FontAwesomeIcon icon={isAccountMenuOpen ? faChevronUp : faChevronDown} size="lg"/>
                  </div>
                  {isAccountMenuOpen &&
                     <div className={styles.mobileNav}>
                      <Link to={'/'} onClick={() => {resetMobileMenus()}}>Profile</Link>
                      <Link to={'/'} onClick={() => {resetMobileMenus()}}>Create Collection</Link>
                      { user.isAdmin && <Link to={'/'} onClick={() => {resetMobileMenus()}}>Create Book</Link> }
                      <Link to={'/'} onClick={() => {resetMobileMenus()}}>Logout</Link>
                    </div>
                  }
                </div>
              : <Link to={'/signin'} onClick={() => {resetMobileMenus()}}>Sign in</Link>
            }
        </div>
      }
    </>
  )
}

export default Navbar