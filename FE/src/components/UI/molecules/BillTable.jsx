import { useEffect, useState } from 'react';
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
} from '@mui/material';

function createData(name, amount) {
  return { name, amount };
}

function BillTable({ subResults, totalAmount = 0 }) {
  const [rows, setRows] = useState([]);

  useEffect(() => {
    // console.log(totalAmount);
    // console.log('BillTable-subResults', subResults);
    if (subResults) {
      // console.log('subResults', subResults);
      const length = subResults.length;
      for (let i = 0; i < length; i++) {
        let subResult = subResults[i];
        let name = subResult.name.text;
        let price = subResult.price.price.text;
        let a = createData(name, price);
        setRows((rows) => [...rows, a]);
      }
    }
    // console.log('rows', rows);
  }, [subResults]);

  return (
    <TableContainer component={Paper}>
      <Table aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>항목</TableCell>
            <TableCell align="center">금액</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <TableRow key={row.name} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
              <TableCell component="th" scope="row">
                {row.name}
              </TableCell>
              <TableCell align="center">{row.amount}</TableCell>
            </TableRow>
          ))}
          <TableRow>
            <TableCell>합계:</TableCell>
            <TableCell align="right">{totalAmount}원</TableCell>
          </TableRow>
        </TableBody>
      </Table>
    </TableContainer>
  );
}

export default BillTable;
