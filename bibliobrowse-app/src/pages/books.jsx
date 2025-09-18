import React, { useState } from 'react';
import BookCard from '../components/bookCard';
import CollectionCard from '../components/CollectionCard';
import Carousel from '../components/Carousel';

export default function books() {
  const [isBookFavourite, setIsBookFavourite] = useState(false);
  const [isCollectionFavourite, setIsCollectionFavourite] = useState(true);

  return (
    <div>
      <BookCard 
        title={'The Hunger Games'}
        imageUrl={'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg'}
        author={'Suzanne Collins'}
        rating={4.5}
        isFavourite={isBookFavourite}
        toggleFavourite={() => setIsBookFavourite(!isBookFavourite)}
      />
      <CollectionCard
        title={'My Favourite Books'}
        imageUrls={['https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
          'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
          'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
          'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
          'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
          'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
          'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg'
        ]}
        author={'UserName 123'}
        numBooks={25}
        isFavourite={isCollectionFavourite}
        toggleFavourite={() => setIsCollectionFavourite(!isCollectionFavourite)}
      />
      <Carousel viewMoreLink={'/books'}
        cardType={'book'}
        itemList={[
          {
            title: 'The Hunger Games',
            imageUrl: 'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
            author: '1Suzanne Collins',
            rating: 4.5,
            isFavourite: false
          }, 
          {
            title: 'The Hunger Games',
            imageUrl: 'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
            author: '2Suzanne Collins',
            rating: 4.5,
            isFavourite: true
          }, 
          {
            title: 'The Hunger Games',
            imageUrl: 'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
            author: '3Suzanne Collins',
            rating: 4.5,
            isFavourite: false
          }, 
          {
            title: 'The Hunger Games',
            imageUrl: 'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
            author: '4Suzanne Collins',
            rating: 4.5,
            isFavourite: true
          }, 
          {
            title: 'The Hunger Games',
            imageUrl: 'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
            author: '5Suzanne Collins',
            rating: 4.5,
            isFavourite: false
          }, 
          // {
          //   title: 'The Hunger Games',
          //   imageUrl: 'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
          //   author: '6Suzanne Collins',
          //   rating: 4.5,
          //   isFavourite: true
          // }, 
        ]} 
      />
            <Carousel viewMoreLink={'/collections'}
        cardType={'collection'}
        itemList={[
          {
            title: '1My Favourite Books',
            imageUrls: ['https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
              'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
              'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
              'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
              'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
              'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
              'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg'
            ],
            author: 'UserName 123',
            numBooks: 25,
            isFavourite: false
          }, 
          {
            title: '2My Favourite Books',
            imageUrls: ['https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
              'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
              'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
            ],
            author: 'UserName 123',
            numBooks: 25,
            isFavourite: true
          }, 
          {
            title: '3My Favourite Books',
            imageUrls: ['https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
            ],
            author: 'UserName 123',
            numBooks: 25,
            isFavourite: false
          }, 
          {
            title: '4My Favourite Books',
            imageUrls: ['https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
              'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
              'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
              'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
              'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
            ],
            author: 'UserName 123',
            numBooks: 25,
            isFavourite: true
          }, 
          {
            title: '5My Favourite Books',
            imageUrls: ['https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
              'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
            ],
            author: 'UserName 123',
            numBooks: 25,
            isFavourite: false
          }, 
          // {
          //   title: '6My Favourite Books',
          //   imageUrls: ['https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
          //     'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
          //     'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
          //     'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
          //     'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
          //     'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
          //   ],
          //   author: 'UserName 123',
          //   numBooks: 25,
          //   isFavourite: true
          // }, 
        ]} 
      />

    </div>
  )
}
