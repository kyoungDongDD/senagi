import { Dialog, DialogActions, DialogContent, DialogTitle, Button, Grid} from '@mui/material';
import Text from '../../atoms/Text';
import BillTable from '../../molecules/BillTable';
import { makeStyles } from '@mui/styles';
import styled from '@emotion/styled';

const useStyles = makeStyles((theme) => {
  return {
    root: {
      "&.MuiButton-root": {
        border: "1px black solid",
      },
      "&.MuiDialogActions": {
        justifyContent: "center"
      }
    },
    withdrawlButton: {
      "&.MuiButton-root": {
        border: "1px white solid",
        "&:hover": {
          border: "1px #f4ba34 solid"
        }
      },
    },
    withdrawlText: {
      "&.MuiDialogTitle-root": {
        margin: "auto",
      },
    },
  };
});

const WithdrawalButton = styled(Button)`
  background-color: #f4ba34;
  &:hover {
    background-color: white;
    color: #f4ba34;
  }
  color: white;
  margin: auto;
  width: 200px;
`

const CancelButton = styled(Button)`
  background-color: white;
  &:hover {
    background-color: black;
    color: white;
  }
  color: black;
  margin: auto;
  width: 200px;
`
const AttachButton = styled(Button)`
  background-color: white;
  &:hover {
    background-color: black;
    color: white;
  }
  color: black;
  margin: auto;
`

function WithdrawModal(props) {
  const classes = useStyles();

  return (
    <div>
      <Dialog
        open={props.isOpen}
        onClose={props.handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title" className={classes.withdrawlText}>
          <Text className="header1" text="출금 신청하기"/>
        </DialogTitle>
        <DialogContent>
          <Grid container spacing={2}>
            <Grid item xs={6} md={4}>
              <img src="" alt="" />
              <AttachButton className={classes.root} fullWidth>영수증 첨부</AttachButton>
            </Grid>
            <Grid item xs={6} md={8}>
              <BillTable />
            </Grid>
          </Grid>
        </DialogContent>
        <DialogActions
          className={classes.root}
          >
          <WithdrawalButton
            className={classes.withdrawlButton}
            onClick={props.handleClose}>
            출금신청
          </WithdrawalButton>
          <CancelButton
            variant="outlined"
            className={classes.root}
            onClick={props.handleClose}>
            취소
          </CancelButton>
        </DialogActions>
      </Dialog>
    </div>
  );
}


export default WithdrawModal;