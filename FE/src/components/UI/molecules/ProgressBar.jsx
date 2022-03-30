
import styled from "@emotion/styled";



const ProgressBar =  ({width, percent}) => {
  
    let progress = percent * width;

    return (
      <ProgressDiv style={{width: width}}>
           <Progress style={{width: `${progress}px`}}/>
      </ProgressDiv>
    )
}

ProgressBar.defaultProps = {
  percent: 0.0
}

export default ProgressBar;

const ProgressDiv = styled.div`
  background-color: rgb(233, 233, 233);
  border-radius: .5rem;
  margin-top: 10px; /* bar 상하 간격 */
`

const Progress = styled.div`
  background-color: #F4BA3499;
  height: 10px;
  border-radius: 1rem;
`