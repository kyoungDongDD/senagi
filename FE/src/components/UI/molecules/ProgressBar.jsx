import React from 'react';

import "./ProgressBar.css"



const ProgressBar =  ({width = 320, percent}) => {
  
    let progress = percent * width;

    return (
      <div className="progress-div" style={{width: width}}>
           <div style={{width: `${progress}px`}}className="progress"/>
      </div>
    )
}

ProgressBar.defaultProps = {
  percent: 0.0
}

export default ProgressBar;