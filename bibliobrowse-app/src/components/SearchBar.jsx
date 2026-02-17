import { useEffect, useState } from 'react';
import styles from './SearchBar.module.css';

const SearchBar = () => {
  const [search, setSearch] = useState("");

  /*
  useEffect(() => {
    const getPopularBooks = async () => {
      try {
        fetch('http://localhost:8080/api/books')
          .then((response) => {return response.json()})
          .then((data) => {
            const sortedData = data.sort((a, b) => a.id - b.id);
            setPopularBooks(sortedData.slice(0, 3));
            console.log("books:", sortedData.slice(0, 4));
          })
      } catch (error) {
        console.error(error.message);
      }
    }

    getPopularBooks();
  },[]);
  */

  const handleChangeValue = (event) => {
    setSearch(event.target.value);
  };

  const handleSearch = () => {
    
  };

  return (
    <div className={styles.searchBar}>
      <input type='text' value={search} onChange={handleChangeValue} className={styles.searchBox} placeholder={'Search title or author'}/>
      <button onClick={handleSearch} className={styles.searchButton}>Search</button>
    </div>
  )
}

export default SearchBar