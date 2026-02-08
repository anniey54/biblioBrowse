import { useEffect, useState } from "react";
import Logo from "./logo";
import styles from './Navbar.module.css';
import { Link, useLocation } from 'react-router-dom';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars, faXmark, faChevronDown } from "@fortawesome/free-solid-svg-icons";
import { Menu, MenuItem, MenuButton } from '@szhsin/react-menu';
import '@szhsin/react-menu/dist/index.css';
import '@szhsin/react-menu/dist/transitions/zoom.css';
import AccordionLink from './AccordionLink';

const Navbar = () => {
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);
  const [isAccountMenuOpen, setIsAccountMenuOpen] = useState(false);
  const [isHomePage, setIsHomePage] = useState(true);
  const [isTopOfScreen, setIsTopOfScreen] = useState(true);
  const location = useLocation();
  const user = {
    name: 'UserName 123',
    profileURL: 'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
    isAdmin: true
  }

  // check if current path is home
  useEffect(() => {
    if (window.location.pathname === '/') {
      setIsHomePage(true);
    }
    else {
      setIsHomePage(false);
    }
  }, [location.pathname]);

  // check if screen scrolled down
  useEffect(() => {
    const handleScroll = () => {
      if (window.scrollY >= 50) {
        setIsTopOfScreen(false);
      } else {
        setIsTopOfScreen(true);
      } 
    };
    
    window.addEventListener('scroll', handleScroll);
    handleScroll();
    
    return () => {
      window.removeEventListener('scroll', handleScroll);
    };
  }, []);

  /* reset both menus in mobile navbar to false */
  const resetMobileMenus = () => {
    setIsMobileMenuOpen(false); 
    setIsAccountMenuOpen(false);
  }

  return (
    <>
      <nav className={isHomePage && isTopOfScreen ?styles.homeNavbar :styles.navbar}>
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
              ? <AccordionLink toggleLabelFunct={() => setIsAccountMenuOpen(!isAccountMenuOpen)} 
                  toggleItemFunct={() => resetMobileMenus()}
                  label={user.name} 
                  itemList={[
                    {label: 'Profile', url: '/'},
                    {label: 'Create Collection', url: '/'},
                    {label: 'Create Book', url: '/'},
                    {label: 'Logout', url: '/'}
                  ]} 
                  itemCond={user.isAdmin}
                  isMenuOpen={isAccountMenuOpen} 
                  styleMenu={styles.mobileAccountMenu} 
                  styleLabel={styles.accountName} 
                  styleItemList={styles.mobileNav}
                />
              : <Link to={'/signin'} onClick={() => {resetMobileMenus()}}>Sign in</Link>
            }
        </div>
      }
    </>
  )
}

export default Navbar