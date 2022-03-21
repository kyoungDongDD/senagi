import styled from "@emotion/styled";
import { useEffect } from "react";

function Pagination({ total, limit, page, setPage }) {
  const numPages = Math.ceil(total / limit);

  //페이지 맨위로 올리는 코드
  useEffect(() => {
    window.scrollTo(0, 0);
  }, [page]);

  return (
    <div>
      <Nav>
        <Button onClick={() => setPage(page - 1)} disabled={page === 1}>
          &lt;
        </Button>
        {Array(numPages)
          .fill()
          .map((_, i) => (
            <Button
              key={i + 1}
              onClick={() => setPage(i + 1)}
              aria-current={page === i + 1 ? "page" : null}
            >
              {i + 1}
            </Button>
          ))}
        <Button onClick={() => setPage(page + 1) } disabled={page === numPages}>
          &gt;
        </Button>
      </Nav>
    </div>
  );
}

const Nav = styled.nav`
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
  margin: 16px;
`;

const Button = styled.button`
  border: none;
  border-radius: 3px;
  padding: 10px;
  margin: 0;
  background: white;
  color: black;
  font-size: 1rem;
  box-shadow: 1px 1px 1px 1px gray;

  &:hover {
    background: #F4BA3499;
    cursor: pointer;
    transform: translateY(-2px);
  }

  &[disabled] {
    background: white;
    cursor: revert;
    transform: revert;
  }

  &[aria-current] {
    background: #F4BA3499;
    font-weight: bold;
    cursor: revert;
    transform: revert;
  }
`;

export default Pagination;