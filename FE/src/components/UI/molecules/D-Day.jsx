import styled from '@emotion/styled';

const Dday = ({ dday }) => {
  return <DayCount className="day">D-{dday}</DayCount>;
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
  justify-content: left;
  font-size: 17px;
  color: orange;
`;
