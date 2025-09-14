import React from 'react';
import styles from './BookCard.module.css';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar, faHeart } from "@fortawesome/free-solid-svg-icons";
import {faHeart as emptyHeart} from "@fortawesome/free-regular-svg-icons"

const BookCard = ({title, imageUrl, author, rating, isFavourite, toggleFavourite}) => {
  return (
    <div className={styles.bookCard}>
      <img src={imageUrl} alt='book cover' />
      <div className={styles.cardContent}>
        <div className={styles.cardText}>
          <p className={styles.title}>{title}</p>
          <p>{author}</p>
        </div>
        <div className={styles.ratingAndButtons}>
          <div className={styles.rating}>
            <p>{rating}</p>
            <FontAwesomeIcon icon={faStar} color='var(--star)'/>
          </div>
          <div className={styles.buttons}>
            <FontAwesomeIcon icon={isFavourite ? faHeart : emptyHeart} color='var(--heart)' onClick={toggleFavourite}/>
            <img src='./../../public/addOnCollectionIcon.svg'/>
          </div>
        </div>
      </div>
    </div>
  )
}

export default BookCard