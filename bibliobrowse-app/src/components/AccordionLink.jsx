import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { faChevronDown, faChevronUp } from "@fortawesome/free-solid-svg-icons"
import { Link } from "react-router-dom"

const AccordionLink = ({toggleItemFunct, toggleLabelFunct = toggleItemFunct, label, itemList, itemCond=true, isMenuOpen, styleMenu, styleLabel, styleItemList}) => {
  return (
    <div className={styleMenu}>
        <div className={styleLabel} onClick={toggleLabelFunct}>
          <p>{label}</p>
          <FontAwesomeIcon icon={isMenuOpen ? faChevronUp : faChevronDown} size="lg"/>
        </div>
        {isMenuOpen &&
          <div className={styleItemList}>
            {itemList.map((item, index) => (
              <>{
                (item.label === 'Create Book' && itemCond) || (item.label != 'Create Book')
                ? <Link to={item.url} onClick={toggleItemFunct} key={index}>{item.label}</Link>
                : <></>
              }</>
            ))}
          </div>
        }
      </div>
  )
}

export default AccordionLink