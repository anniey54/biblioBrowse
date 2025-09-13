import React, { useState } from 'react';
import styles from './Footer.module.css';
import Logo from './logo';
import { Link } from 'react-router-dom';
import AccordionLink from './AccordionLink';


const Footer = () => {
  const [isQuickLinkOpen, setIsQuickLinkOpen] = useState(false);
  const [isBookGenreOpen, setIsBookGenreOpen] = useState(false);

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
        <Logo fontColour='white' isFooter={true}/>
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
      <AccordionLink toggleItemFunct={() => setIsQuickLinkOpen(!isQuickLinkOpen)} 
        label={"Quick links"} 
        itemList={[
          {label: 'Home', url: '/'},
          {label: 'Explore books', url: '/books'},
          {label: 'Explore collections', url: '/collections'},
          {label: 'Sign in', url: '/signin'},
          {label: 'Contact us', url: '/'}
        ]} 
        isMenuOpen={isQuickLinkOpen} 
        styleMenu={styles.mobileMenu} 
        styleLabel={styles.menuLabel} 
        styleItemList={styles.mobileMenuItem}
      />
      <AccordionLink toggleItemFunct={() => setIsBookGenreOpen(!isBookGenreOpen)} 
        label={"Book genres"} 
        itemList={[
          {label: 'Fantasy', url: '/'},
          {label: 'Romance', url: '/books'},
          {label: 'Mystery', url: '/collections'},
          {label: 'Science Fiction', url: '/signin'},
          {label: 'Thriller', url: '/'}
        ]} 
        isMenuOpen={isBookGenreOpen} 
        styleMenu={styles.mobileMenu} 
        styleLabel={styles.menuLabel} 
        styleItemList={styles.mobileMenuItem}
      />
    </div>
  )
}

export default Footer