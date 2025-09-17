import React, { useState, useEffect } from 'react';
import styles from './Carousel.module.css';
import BookCard from './bookCard';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faChevronRight, faChevronLeft, faArrowRight } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';

const Carousel = ({itemList}) => {
  const [isBookFavourite, setIsBookFavourite] = useState(false);
  const [currentIndex, setCurrentIndex] = useState(1);
  const [lastIndex, setLastIndex] = useState(Math.ceil(itemList.length/3));
  const [isNextDisabled, setNextDisabled] = useState(false);
  const [isPrevDisabled, setPrevDisabled] = useState(false);

  useEffect(() => {
    currentIndex == 1 ? setPrevDisabled(true) : setPrevDisabled(false)
    currentIndex == lastIndex ? setNextDisabled(true) : setNextDisabled(false)
    console.log("currentIndex", currentIndex)
  }, [currentIndex])

  const nextSlide = () => {
    if (currentIndex < lastIndex) {
      setCurrentIndex(currentIndex + 1);
      console.log("currentIndex + 1", currentIndex + 1)
    }
  }

  const prevSlide = () => {
    if (currentIndex > 1) {
      setCurrentIndex(currentIndex - 1);
      console.log("currentIndex + 1", currentIndex - 1)
    }
  }

  return (
    <div className={styles.carousel}>
      <div className={styles.slider}>
        <div className={styles.sliderContent}>
          <BookCard 
          title={'The Hunger Games'}
          imageUrl={'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg'}
          author={'1Suzanne Collins'}
          rating={4.5}
          isFavourite={isBookFavourite}
          toggleFavourite={() => setIsBookFavourite(!isBookFavourite)}
        />
        <BookCard 
          title={'The Hunger Games'}
          imageUrl={'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg'}
          author={'2Suzanne Collins'}
          rating={4.5}
          isFavourite={isBookFavourite}
          toggleFavourite={() => setIsBookFavourite(!isBookFavourite)}
        />
        <BookCard 
          title={'The Hunger Games'}
          imageUrl={'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg'}
          author={'3Suzanne Collins'}
          rating={4.5}
          isFavourite={isBookFavourite}
          toggleFavourite={() => setIsBookFavourite(!isBookFavourite)}
        />
        <BookCard 
          title={'The Hunger Games'}
          imageUrl={'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg'}
          author={'4Suzanne Collins'}
          rating={4.5}
          isFavourite={isBookFavourite}
          toggleFavourite={() => setIsBookFavourite(!isBookFavourite)}
        />
        <BookCard 
          title={'The Hunger Games'}
          imageUrl={'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg'}
          author={'5Suzanne Collins'}
          rating={4.5}
          isFavourite={isBookFavourite}
          toggleFavourite={() => setIsBookFavourite(!isBookFavourite)}
        />
        </div>
        <div className={styles.arrowButtons}>
          <button onClick={prevSlide} disabled={isPrevDisabled} className={isPrevDisabled ?
                    styles.disabledButton : ""}>
            <FontAwesomeIcon icon={faChevronLeft}/>
          </button>
          <button onClick={nextSlide} disabled={isNextDisabled} className={isNextDisabled ?
                    styles.disabledButton : ""}>
            <FontAwesomeIcon icon={faChevronRight}/>
          </button>
        </div>
      </div>
      <Link to={'/books'}>
        <button className={styles.viewMoreButton}>
          <p>View More</p>
          <FontAwesomeIcon icon={faArrowRight}/>
        </button>
      </Link>
    </div>
  )
}

export default Carousel