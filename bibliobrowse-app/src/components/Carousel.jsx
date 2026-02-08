import React, { useState, useEffect } from 'react';
import styles from './Carousel.module.css';
import BookCard from './bookCard';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faChevronRight, faChevronLeft, faArrowRight } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';
import CollectionCard from './CollectionCard';

const Carousel = ({itemList, viewMoreLink, cardType}) => {
  // erase these later
  const [isBookFavourite, setIsBookFavourite] = useState(false);
  const [isCollectionFavourite, setIsCollectionFavourite] = useState(true);
  
  const [currentIndex, setCurrentIndex] = useState(0);
  const [numberOfItems, setNumberOfItems] = useState(window.innerWidth <= 780 ? 1 : window.innerWidth <= 1100 ? 2 : 3);
  const [lastIndex, setLastIndex] = useState();
  const [isNextDisabled, setNextDisabled] = useState(false);
  const [isPrevDisabled, setPrevDisabled] = useState(false);
  const [mobileViewButtons, setMobileViewButtons] = useState(false);

  useEffect(() => {
    // change the number of items displayed on slider at once
    // edit multiple states when the screen size changes
    const changeNumberOfItem = () => {
      if (window.innerWidth <= 780) {
        setNumberOfItems(1);
        setLastIndex(itemList.length);
      } 
      else if (window.innerWidth <= 1100) {
        setNumberOfItems(2);
        setLastIndex(Math.ceil(itemList.length/2));
        if (numberOfItems != 2) setCurrentIndex(Math.floor(currentIndex/2));
      } 
      else {
        setNumberOfItems(3);
        setLastIndex(Math.ceil(itemList.length/3));
        if (numberOfItems != 3) setCurrentIndex(Math.floor(currentIndex/3));
      }

      // change position of arrow and view more buttons on mobile view
      if (window.innerWidth <= 460) setMobileViewButtons(true);
      else setMobileViewButtons(false);
    }
    window.addEventListener("resize", changeNumberOfItem);

		changeNumberOfItem();

		return () => {
			window.removeEventListener("resize", changeNumberOfItem);
		};
  }, []);

  // disable/enable arrow buttons based on the current index
  useEffect(() => {
    currentIndex == 0 ? setPrevDisabled(true) : setPrevDisabled(false)
    numberOfItems != 1 && currentIndex >= lastIndex || numberOfItems == 1 && currentIndex + 1 >= lastIndex ? setNextDisabled(true) : setNextDisabled(false)
  }, [currentIndex, numberOfItems])

  const nextSlide = () => {
    if ((numberOfItems == 1 && currentIndex + 1 < lastIndex) || (numberOfItems != 1 && currentIndex < lastIndex)) {
      setCurrentIndex(currentIndex + numberOfItems);
    }
  }

  const prevSlide = () => {
    if (currentIndex > 0) {
      setCurrentIndex(currentIndex - numberOfItems);
    }
  }

  const RenderCard = ({item, index}) => {
    if (cardType === 'book') {
      return <BookCard key={index}
        title={item.title}
        imageUrl={item.imageUrl}
        author={item.author}
        rating={item.rating}
        isFavourite={item.isBookFavourite}
        toggleFavourite={() => setIsBookFavourite(!isBookFavourite)}
      />
    } else {
      return <CollectionCard key={index}
        title={item.title}
        imageUrls={item.imageUrls}
        author={item.author}
        numBooks={item.numBooks}
        isFavourite={item.isBookFavourite}
        toggleFavourite={() => setIsCollectionFavourite(!isCollectionFavourite)}
      />
    }
  }

  return (
    <div className={styles.carousel}>
      <div className={styles.slider}>
        {/* prev button */}
        {! mobileViewButtons && itemList.length > numberOfItems && 
          <button onClick={prevSlide} disabled={isPrevDisabled} className={isPrevDisabled ?
                  styles.disabledButton : ""}>
            <FontAwesomeIcon icon={faChevronLeft}/>
          </button>
        }
        {/* slider */}
        <div className={styles.sliderContent}>
          {itemList.slice(currentIndex, currentIndex + numberOfItems).map((item, index) => (
            <RenderCard item={item} index={index}/>
          ))}
        </div>
        {/* next button */}
        {! mobileViewButtons && itemList.length > numberOfItems && 
          <button onClick={nextSlide} disabled={isNextDisabled} className={isNextDisabled ?
                    styles.disabledButton : ""}>
            <FontAwesomeIcon icon={faChevronRight}/>
          </button>
        }
      </div>
      {/* mobile view of arrow and view more buttons */}
      {mobileViewButtons && itemList.length > numberOfItems
        ? <div className={styles.mobileButtons}>
          <button onClick={prevSlide} disabled={isPrevDisabled} id='mobilePrevButton' className={isPrevDisabled ?
                    styles.disabledButton : ""}>
            <FontAwesomeIcon icon={faChevronLeft}/>
          </button>
          <Link to={viewMoreLink} className={styles.viewMoreButton}>
            <button>
              <p>View More</p>
              <FontAwesomeIcon icon={faArrowRight}/>
            </button>
          </Link>
          <button onClick={nextSlide} disabled={isNextDisabled} id='mobileNextButton' className={isNextDisabled ?
                    styles.disabledButton : ""}>
            <FontAwesomeIcon icon={faChevronRight}/>
          </button>
        </div>
        : <Link to={viewMoreLink} className={styles.viewMoreButton}>
          <button>
            <p>View More</p>
            <FontAwesomeIcon icon={faArrowRight}/>
          </button>
        </Link>
      }
    </div>
  )
}

export default Carousel