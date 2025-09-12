import React, { useState } from 'react';
import styles from './Footer.module.css';
import Logo from './logo';
import { Link } from 'react-router-dom';


const Footer = () => {
  const [mobileView, setMobileView] = useState(false);
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);

  const LinkColumn = ({header, linkList}) => {
    return(
      <div>
        <p>{header}</p>
        <div className={styles.linkColumnList}>
          {linkList.map((link, index) => (
            <Link to={link.url} key={index}>{link.name}</Link>
          )) }
        </div>
      </div>
    )
  }
    
  return (
    <div className={styles.footer}>
      <div className={styles.logoAbout}>
        <Logo fontColour='white'/>
        <div>
          <h3>What We're All About</h3>
          <p>Our book gallery offers a wide selection across all fiction genres to keep your reading journey fress and exciting. Whether you're a casual reader or an avid bookworm, we've got something special just for you.</p>
        </div>
      </div>
      <div className={styles.links}>
        <LinkColumn header={'Quick links'} linkList={[
          {name: 'Home', url: '/'},
          {name: 'Explore books', url: '/books'},
          {name: 'Explore collections', url: '/collections'},
          {name: 'Sign in', url: '/signin'},
          {name: 'Contact us', url: '/'}
        ]}/>
        <LinkColumn header={'Book genre'} linkList={[
          {name: 'Fantasy', url: '/'},
          {name: 'Romance', url: '/'},
          {name: 'Mystery', url: '/'},
          {name: 'Science Fiction', url: '/'},
          {name: 'Thriller', url: '/'}
        ]}/>
      </div>
    </div>
  )
}

export default Footer