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
  const user = {
    name: 'User Name Name Name User Name Name Name User Name Name Name User Name Name Name User Name Name Name',
    profileURL: 'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
    isAdmin: false
  }

  /* user profile menu styling  */
  const menuItemStyle = ({ hover }) => hover 
    ? styles.menuItemHover
    : styles.menuItem;

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
                <MenuItem className={menuItemStyle} href='/'>Profile</MenuItem>
                <MenuItem className={menuItemStyle} href='/books'>Create Collection</MenuItem>
                { user.isAdmin && <MenuItem className={menuItemStyle} href='/'>Create Book</MenuItem> }
                <MenuItem className={menuItemStyle}>Logout</MenuItem>
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