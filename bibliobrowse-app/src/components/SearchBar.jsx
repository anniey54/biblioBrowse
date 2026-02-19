import { useEffect, useState } from 'react';
import styles from './SearchBar.module.css';

const SearchBar = (handleSearchResult) => {
  const [search, setSearch] = useState("");

  // const getListOfSearchBooks = async () => {
  //   const query = "test";
  //   try {
  //     fetch(`http://localhost:8080/api/books/search?query=${query}`)
  //       .then((response) => {return response.json()})
  //       .then((data) => {
  //         console.log("searched books:", data);
  //       })
  //   } catch (error) {
  //     console.error(error.message);
  //   }
  // }

  // const getListOfSearchCollections = async () => {
  //   const query = "Strawberr";
  //   try {
  //     fetch(`http://localhost:8080/api/collections/search?query=${query}`)
  //       .then((response) => {return response.json()})
  //       .then((data) => {
  //         console.log("searched collections:", data);
  //       })
  //   } catch (error) {
  //     console.error(error.message);
  //   }
  // }

  const handleChangeValue = (event) => {
    setSearch(event.target.value);
  };

  const handleSearch = () => {
    handleSearch();
  };

  return (
    <div className={styles.searchBar}>
      <input type='text' value={search} onChange={handleChangeValue} className={styles.searchBox} placeholder={'Search title or author'}/>
      <button onClick={handleSearch} className={styles.searchButton}>Search</button>
    </div>
  )
}

export default SearchBar