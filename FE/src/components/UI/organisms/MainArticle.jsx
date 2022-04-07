import styled from '@emotion/styled';

function MainArticle() {
  const StyledButton = styled.button`
    background-color: white;
    border: 1px solid orange;
    color: orange;
    border-radius: 50px;
    width: 200px;
    height: 50px;
    font-size: 20px;
    margin-left: 50px;
  `;
  return (
    <div style={{ marginTop: 100 }}>
      <StyledButton>#세나기</StyledButton>
      <StyledButton>#유기동물</StyledButton>
      <StyledButton>#기부</StyledButton>
      <StyledButton>#캠페인</StyledButton>
    </div>
  );
}

export default MainArticle;
