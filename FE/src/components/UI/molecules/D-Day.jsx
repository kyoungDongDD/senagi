import React from 'react';
import styled from '@emotion/styled';

const Dday = ({ dday }) => {
  return <DayCount className="body3">D-{dday}</DayCount>;
};

export default Dday;

// const DayCount = styled.span`
//   display: flex;
//   position: absolute;
//   left: 72%;
//   width: 59px;
//   height: 18px;
//   padding-top: 4px;
//   margin: 10px 10px;
//   background-color: #f4ba3499;
//   justify-content: center;
//   align-items: center;
//   border-radius: 3px;
// `;

const DayCount = styled.div`
  display: flex;
  width: 100%;
  background-color: #f4ba34;
  justify-content: center;
  align-items: center;
  border-radius: 3px;
  text-align: center;
  color: white;
`;
