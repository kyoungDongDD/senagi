import React from 'react';
import styled from '@emotion/styled';


const Dday =  ({dday}) => {
  

    return (
      <div>
        <DayCount>D-{dday}</DayCount>
      </div>
    )
}

export default Dday;

const DayCount = styled.div`
  display: flex;
  position: absolute;
  left: 72%;
  width:59px;
  height: 18px;
  padding-top : 4px;
  margin: 10px 10px;
  background-color: #F4BA3499;
  justify-content:center;
  align-items:center;
  border-radius: 3px;
`